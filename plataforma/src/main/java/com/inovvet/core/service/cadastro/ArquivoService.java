package com.inovvet.core.service.cadastro;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.service.AbstractService;
import com.inovvet.app.service.StorageService;
import com.inovvet.app.util.FunctionUtil;
import com.inovvet.core.entity.Arquivo.Arquivo;
import com.inovvet.core.entity.Arquivo.dto.ArquivoCadastroDTO;
import com.inovvet.core.enumerator.EnumArquivo;
import com.inovvet.core.enumerator.EnumModulo;
import com.inovvet.core.enumerator.EnumReferenciaArquivo;
import com.inovvet.core.repository.cadastro.ArquivoRepository;

import lombok.Getter;

@Service
public class ArquivoService extends AbstractService {

	@Autowired
	private ArquivoRepository repository;

	@Autowired
	private StorageService storage;

	private String upload(MultipartFile file, Arquivo arquivo) {
		storage.upload(file, arquivo);
		return storage.getUrl(arquivo);
	}

	private void delete(Arquivo arquivo) {
		storage.delete(arquivo);
	}

	private byte[] download(Arquivo arquivo) {
		return storage.download(arquivo);
	}

	@Transactional
	public void remover(Arquivo arquivo) {

		delete(arquivo);

		try {
			repository.delete(arquivo);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_REMOVER_REGISTRO, "Registro já utilizado não pode ser removido");
		}
	}

	@Transactional
	private Arquivo salvar(Arquivo entity) {
		try {
			return repository.save(entity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO, e.getMessage());
		}
	}

	public Arquivo localizar(Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ServiceException(EnumError.REGISTRO_NAO_ECONTRADO, "Arquivo não encontrado"));
	}

	@Transactional
	public ArquivoCadastroDTO novo(MultipartFile file, EnumModulo modulo, EnumArquivo tipoArquivo, EnumReferenciaArquivo referencia, Integer referenciaId) {

		BigDecimal divisor = new BigDecimal("0.00000095367432");

		Arquivo arquivo = new Arquivo();
		arquivo.setReferencia(referencia);
		arquivo.setReferenciaId(referenciaId);

		arquivo.setTipo(tipoArquivo);
		arquivo.setModulo(modulo);
		arquivo.setChave(FunctionUtil.uUid());
		arquivo.setNome(file.getOriginalFilename());
		arquivo.setTamanho((new BigDecimal(file.getSize()).multiply(divisor).setScale(8, RoundingMode.HALF_UP)));

		arquivo.setUrl(upload(file, arquivo));

		ArquivoCadastroDTO arquivoCadastroDTO = mapper.map(salvar(arquivo), ArquivoCadastroDTO.class);

		return arquivoCadastroDTO;

	}

	public ArquivoCadastroDTO carregar(Integer id) {

		Arquivo arquivo = localizar(id);

		return mapper.map(arquivo, ArquivoCadastroDTO.class);
	}
	
	public List<ArquivoCadastroDTO> carregar(EnumReferenciaArquivo referencia, Integer id) {

		List<Arquivo> arquivos = repository.findByReferenciaAndReferenciaId(referencia, id);

		return mapper.map(arquivos, ArquivoCadastroDTO.class);
	}

	public DataFile download(Integer id) {

		Arquivo arquivo = localizar(id);

		return new DataFile(download(arquivo), arquivo.getNome(), arquivo.getTipo());
	}

	@Transactional
	public void remover(Integer id) {
		Arquivo arquivo = localizar(id);
		remover(arquivo);
	}

	public void remover(Set<Arquivo> arquivos) {
		arquivos.forEach(arquivo -> {
			remover(arquivo);
		});
	}

	@Getter
	public class DataFile {
		private byte[] data;
		private String name;
		private EnumArquivo tipo;

		public DataFile(byte[] data, String name, EnumArquivo tipo) {
			super();
			this.data = data;
			this.name = name;
			this.tipo = tipo;

		}

		public ByteArrayResource getResource() {
			return new ByteArrayResource(this.getData());
		}

		public MediaType getMediaType() {
			switch (this.tipo) {
			case IMAGEM:
				return MediaType.IMAGE_JPEG;
			case BOLETO:
				return MediaType.APPLICATION_PDF;
			default:
				return MediaType.APPLICATION_OCTET_STREAM;
			}
		}

	}

}

package com.inovvet.core.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.inovvet.app.config.Projeto;
import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.util.FunctionUtil;
import com.inovvet.core.entity.Cidade;
import com.inovvet.core.entity.Estado;
import com.inovvet.core.entity.dto.CepDTO;
import com.inovvet.core.entity.dto.EnderecoDTO;
import com.inovvet.core.repository.CidadeRepository;
import com.inovvet.core.repository.EstadoRepository;

@Service
public class CidadeService extends AbstractService {

	@Autowired
	private Projeto projeto;

	@Autowired
	private CidadeRepository repository;

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Estado> listarUfs() {
		return estadoRepository.findAll();
	}

	public List<Cidade> listar(String sigla) {
		return repository.findByUf(sigla);
	}

	public Optional<Cidade> buscar(String codigo) {
		return repository.findByCodigo(codigo);
	}

	public EnderecoDTO buscarCep(String cep) {
		EnderecoDTO enderecoDTO = null;
		RestTemplate rest = new RestTemplate();
		System.out.println(projeto.getCepApi());
		ResponseEntity<CepDTO> resp = rest.getForEntity(projeto.getCepApi().replace("{CEP}",
				FunctionUtil.removerMascaraTexto(FunctionUtil.removerMascaraTexto(cep))), CepDTO.class);

		if (resp != null && resp.getStatusCode() == HttpStatus.OK) {
			CepDTO dto = resp.getBody();

			if (!FunctionUtil.isEmpty(dto.getIbge())) {

				Cidade cidade = repository.findByCodigo(dto.getIbge()).orElseThrow(
						() -> new ServiceException(EnumError.PARAMETROS_INVALIDOS, "Cidade não encontrada"));

				if (cidade != null) {
					enderecoDTO = new EnderecoDTO();
					enderecoDTO.setBairro(dto.getBairro());
					enderecoDTO.setCep(FunctionUtil.removerMascaraTexto(dto.getCep()));
					enderecoDTO.setRua(dto.getLogradouro());
					enderecoDTO.setTipo("RESIDENCIAL");
					enderecoDTO.setCidade(cidade);
				}
			}
		} else {
			throw new ServiceException(EnumError.FALHA_INESPERADA, "Falha ao consultar CEP");
		}

		if (enderecoDTO == null) {
			throw new ServiceException(EnumError.PARAMETROS_INVALIDOS, "Cep não encontrado");
		}

		return enderecoDTO;
	}

}

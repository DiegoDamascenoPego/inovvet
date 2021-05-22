package com.inovvet.core.service.cadastro.pessoa;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.util.FiltroUtil;
import com.inovvet.app.util.FunctionUtil;
import com.inovvet.core.entity.base.dto.EntityDTO;
import com.inovvet.core.entity.base.dto.FiltroDTO;
import com.inovvet.core.entity.cliente.dto.EnderecoDTO;
import com.inovvet.core.entity.cliente.dto.TelefoneDTO;
import com.inovvet.core.entity.pessoa.Fabricante;
import com.inovvet.core.entity.pessoa.Marca;
import com.inovvet.core.entity.pessoa.dto.FabricanteCadastroDTO;
import com.inovvet.core.entity.pessoa.dto.FabricanteDTO;
import com.inovvet.core.entity.pessoa.dto.PessoaFiltroDTO;
import com.inovvet.core.entity.pessoa.dto.PessoaPesquisaDTO;
import com.inovvet.core.enumerator.EnumFinalidadePessoa;
import com.inovvet.core.enumerator.EnumTipoDocumento;
import com.inovvet.core.repository.cadastro.pessoa.FabricanteRepository;
import com.inovvet.core.repository.cadastro.pessoa.MarcaRepository;
import com.inovvet.core.service.base.ServiceImpl;

@Service
public class FabricanteService
		extends ServiceImpl<Fabricante, FabricanteDTO, FabricanteCadastroDTO, PessoaFiltroDTO, FabricanteRepository> {

	@Autowired
	private PessoaService pessoaService;

	@Autowired
	private MarcaRepository marcaRepository;

	private Boolean validarLista(Set<EntityDTO> listaDTO, Integer id) {
		for (EntityDTO dto : listaDTO) {
			if (dto.getId() != null) {
				if (dto.getId().equals(id)) {
					return false;
				}
			}
		}
		return true;
	}

	private Boolean validarTelefone(Set<TelefoneDTO> listaDTO, Integer id) {
		for (TelefoneDTO dto : listaDTO) {
			if (dto.getId() != null) {
				if (dto.getId().equals(id)) {
					return false;
				}
			}
		}
		return true;
	}

	private Boolean validarEndereco(Set<EnderecoDTO> listaDTO, Integer id) {
		for (EnderecoDTO dto : listaDTO) {
			if (dto.getId() != null) {
				if (dto.getId().equals(id)) {
					return false;
				}
			}
		}
		return true;
	}

	private void validar(FabricanteDTO dto, Integer id) {

		if (dto.getTipoDocumento().equals(EnumTipoDocumento.CPF)) {
			if (!FunctionUtil.validarCPF(dto.getDocumento())) {
				throw new ServiceException(EnumError.PARAMETROS_INVALIDOS, "CPF informado inválido");
			}
		} else {
			if (!FunctionUtil.validarCNPJ(dto.getDocumento())) {
				throw new ServiceException(EnumError.PARAMETROS_INVALIDOS, "CNPJ informado inválido");
			}
		}

		if (!dto.getEmail().isEmpty()) {
			if (!FunctionUtil.validarEmail(dto.getEmail())) {
				throw new ServiceException(EnumError.PARAMETROS_INVALIDOS, "email informado inválido");
			}
		}

		Optional<Fabricante> fabricante = localizar(dto.getDocumento());

		if (id == 0) {
			if ((fabricante.isPresent())) {
				throw new ServiceException(EnumError.REGISTRO_DUPLICADO);
			}
		} else {
			fabricante.ifPresent(consumer -> {
				if (!consumer.getId().equals(id)) {
					throw new ServiceException(EnumError.REGISTRO_DUPLICADO);
				}
			});
		}

	}

	@Override
	protected Fabricante salvar(FabricanteDTO dto, Fabricante entity) {

		mapper.copy(dto, entity, "telefones", "enderecos", "marcas");

		entity.getTelefones().removeIf(value -> (validarTelefone(dto.getTelefones(), value.getId())));
		entity.getEnderecos().removeIf(value -> (validarEndereco(dto.getEnderecos(), value.getId())));
		entity.getMarcas().removeIf(marca -> (validarLista(dto.getMarcas(), marca.getId())));

		entity.getEnderecos().addAll(pessoaService.converterEndereco(dto.getEnderecos()));
		entity.getTelefones().addAll(pessoaService.converterTelefone(dto.getTelefones()));

		for (EntityDTO marcaDTO : dto.getMarcas()) {
			if (marcaDTO.getId() == null) {
				Marca marcaNova = new Marca();

				mapper.copy(marcaDTO, marcaNova);

				entity.getMarcas().add(marcaNova);

			} else {
				entity.getMarcas().forEach(marca -> {
					if (marca.getId().equals(marcaDTO.getId()))
						mapper.copy(marcaDTO, marca);
				});
			}

		}

		if (entity.getId() == null) {
			entity.setAtivo(true);
		}

		return salvar(entity);
	}

	public Optional<Fabricante> localizar(String documento) {
		return repository.findByDocumentoAndTipo(documento, EnumFinalidadePessoa.FABRICANTE);
	}

	@Override
	public FabricanteCadastroDTO carregar(Integer id) {
		Fabricante fabricante = localizar(id);

		return mapper.map(fabricante, FabricanteCadastroDTO.class);
	}

	@Override
	@Transactional
	public FabricanteCadastroDTO novo(FabricanteDTO dto) {

		validar(dto, 0);

		Fabricante fabricante = salvar(dto, new Fabricante());

		return mapper.map(fabricante, FabricanteCadastroDTO.class);
	}

	@Override
	@Transactional
	public FabricanteCadastroDTO atualizar(Integer id, FabricanteDTO dto) {
		validar(dto, id);

		Fabricante fabricante = localizar(id);

		salvar(dto, fabricante);

		return mapper.map(fabricante, FabricanteCadastroDTO.class);
	}

	@Transactional
	public void remover(Fabricante fabricante) {
		try {
			repository.delete(fabricante);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_REMOVER_REGISTRO, "Registro já utilizado não pode ser removido");
		}
	}

	@Transactional
	public void remover(Integer id) {
		Fabricante fabricante = localizar(id);

		remover(fabricante);
	}

	@Override
	public List<FabricanteCadastroDTO> listar() {
		return mapper.map(repository.findByTipoAndAtivo(EnumFinalidadePessoa.FABRICANTE, true),
				FabricanteCadastroDTO.class);
	}

	public List<PessoaPesquisaDTO> consultarPorDescricaoOuCNPJ(String valor) {
		if (FunctionUtil.isNumber(valor)) {
			return mapper.map(repository.findByDocumentoContainsAndAtivo(valor, true, EnumFinalidadePessoa.FABRICANTE),
					PessoaPesquisaDTO.class);
		}
		return repository.pesquisaCompleta(valor, true, EnumFinalidadePessoa.FABRICANTE);
	}

	@Override
	public Page<FabricanteCadastroDTO> filtrar(FiltroDTO<PessoaFiltroDTO> filtroDTO) {

		Example<Fabricante> example = FiltroUtil.getByExample(filtroDTO, Fabricante.class);

		Page<Fabricante> list = repository.findAll(example,
				PageRequest.of(filtroDTO.getPage(), filtroDTO.getSize(), Sort.by(Sort.Direction.ASC, "nomeFantasia")));

		return list.map(entity -> {
			return mapper.map(entity, FabricanteCadastroDTO.class);
		});
	}

	public List<EntityDTO> pesquisarMarca(String nome) {
		return mapper.map(marcaRepository.findByNomeContainingAndAtivo(nome, true), EntityDTO.class);
	}

	public Marca carregarMarca(Integer id) {
		return marcaRepository.findById(id)
				.orElseThrow(() -> new ServiceException(EnumError.REGISTRO_NAO_ECONTRADO, "Marca não encontrado"));
	}

}

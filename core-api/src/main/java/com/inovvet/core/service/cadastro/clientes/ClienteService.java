package com.inovvet.core.service.cadastro.clientes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inovvet.app.config.Contexto;
import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.util.FiltroUtil;
import com.inovvet.app.util.FunctionUtil;
import com.inovvet.core.entity.base.dto.FiltroDTO;
import com.inovvet.core.entity.cliente.Cliente;
import com.inovvet.core.entity.cliente.Endereco;
import com.inovvet.core.entity.cliente.Telefone;
import com.inovvet.core.entity.cliente.dto.CidadeDTO;
import com.inovvet.core.entity.cliente.dto.ClienteConsultaDTO;
import com.inovvet.core.entity.cliente.dto.ClienteDTO;
import com.inovvet.core.entity.cliente.dto.ClienteFiltroDTO;
import com.inovvet.core.entity.cliente.dto.EnderecoDTO;
import com.inovvet.core.entity.cliente.dto.TelefoneDTO;
import com.inovvet.core.enumerator.EnumTipoEndereco;
import com.inovvet.core.enumerator.EnumTipoTelefone;
import com.inovvet.core.repository.cadastro.cliente.ClienteRepository;
import com.inovvet.core.service.base.AbstractService;

@Service
public class ClienteService extends AbstractService {

	private Cliente cliente;

	List<Cliente> lista = new ArrayList<>();

	@Autowired
	private ClienteRepository repository;


	private ClienteService validar(ClienteDTO dto) {
		if (dto == null) {
			throw new ServiceException(EnumError.PARAMETROS_INVALIDOS);
		}

		if (!FunctionUtil.validarCPF(dto.getCpf())) {
			throw new ServiceException(EnumError.PARAMETROS_INVALIDOS, "CPF informado inválido");
		}

		if (!dto.getEmail().isEmpty()) {
			if (!FunctionUtil.validarEmail(dto.getEmail())) {
				throw new ServiceException(EnumError.PARAMETROS_INVALIDOS, "email informado inválido");
			}
		}

		return this;
	}

	private ClienteService add() {
		if (cliente != null) {
			lista.add(cliente);
		}
		return this;
	}

	private ClienteDTO mapper() {
		try {
			return mapper.map(cliente, ClienteDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(EnumError.FALHA_CONVERTER_REGISTRO);
		}
	}

	private ClienteService carregarCliente(Integer id) {
		if (FunctionUtil.isEmpty(id)) {
			throw new ServiceException(EnumError.PARAMETROS_INVALIDOS, "O registros do cliente não encontrado");
		}

		try {
			cliente = repository.findById(id).orElseThrow(() -> new ServiceException(EnumError.PARAMETROS_INVALIDOS));
		} catch (Exception e) {
			throw new ServiceException(EnumError.REGISTRO_INVALIDO);
		}

		return this;
	}

	public ClienteService carregarLista(List<Integer> ids) throws ServiceException {

		if (FunctionUtil.isEmpty(ids)) {
			throw new ServiceException(EnumError.PARAMETROS_INVALIDOS);
		}

		lista = new ArrayList<Cliente>();

		ids.forEach(id -> {
			this.carregarCliente(id).add();
		});

		return this;
	}

	private ClienteService validarRegistroCadastrado(ClienteDTO dto) {

		cliente = repository.findByCpf(dto.getCpf());

		if (cliente != null) {
			throw new ServiceException(EnumError.REGISTRO_DUPLICADO);
		}

		return this;
	}

	private ClienteService atualizarCliente(ClienteDTO dto) {

		BeanUtils.copyProperties(dto, cliente, "telefones", "endereco");
		BeanUtils.copyProperties(dto.getEndereco(), cliente.getEndereco());

		cliente.getEndereco().setCliente(cliente);
//		if (cliente.getEndereco() != null) {
//			cliente.getEndereco().setCidade(cidadeService.buscar(dto.getEndereco().getCidade().getCodigo())
//					.orElseThrow(() -> new ServiceException(EnumError.PARAMETROS_INVALIDOS, "Cidade não encontrada")));
//		}
		
		cliente.getTelefones().clear();

		dto.getTelefones().forEach(telefone -> {
			Telefone fone = mapper.map(telefone, Telefone.class);
			cliente.getTelefones().add(fone);
		});

		cliente.setDataAtualizacao(LocalDateTime.now());

		return this;
	}

	private ClienteService newCliente(ClienteDTO dto) {

		cliente = new Cliente();

		mapper.map(dto, cliente);
		cliente.setLoja(Contexto.getLoja());
		cliente.getEndereco().setCliente(cliente);

		return this;
	}

	@Transactional
	private ClienteService salvar() {
		try {
			cliente = repository.save(cliente);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO, e.getMessage());
		}

		return this;
	}

	private ClienteService remover() {
		if (!FunctionUtil.isEmpty(lista)) {
			try {
				repository.deleteAll(lista);
			} catch (DataIntegrityViolationException e) {
				throw new ServiceException(EnumError.FALHA_REMOVER_REGISTRO);
			} catch (Exception e) {
				throw new ServiceException(EnumError.FALHA_REMOVER_REGISTRO);
			}
		}
		return this;
	}

	public Page<ClienteFiltroDTO> filtrar(FiltroDTO<ClienteFiltroDTO> filtro) throws ServiceException {

		Example<Cliente> example = FiltroUtil.getByExample(filtro, Cliente.class);

		Page<Cliente> list = repository.findAll(example,
				PageRequest.of(filtro.getPage(), filtro.getSize(), Sort.by(Sort.Direction.ASC, "nome")));

		return list.map(cliente -> {
			return mapper.map(cliente, ClienteFiltroDTO.class);
		});
	}

	@Transactional
	public ClienteDTO salvar(ClienteDTO dto) {

		return this.validar(dto).validarRegistroCadastrado(dto).newCliente(dto).salvar().mapper();
	}

	@Transactional
	public ClienteDTO atualizar(Integer id, ClienteDTO dto) {

		return this.validar(dto).carregarCliente(id).atualizarCliente(dto).salvar().mapper();
	}

	@Transactional
	public void excluir(Integer id) {

		if (id != null && id > 0) {
			try {
				repository.deleteById(id);
			} catch (Exception e) {
				throw new ServiceException(EnumError.FALHA_INESPERADA, e.getMessage());
			}
		}
	}

	@Transactional
	public void excluir(List<Integer> ids) throws ServiceException {
		this.carregarLista(ids).remover();
	}

	public ClienteDTO buscarPorId(Integer id) {
		return this.carregarCliente(id).mapper();
	}

	public List<ClienteConsultaDTO> consultar(String nome) {

		if (FunctionUtil.isNumber(nome)) {
			return mapper.map(repository.findByCpfContainingIgnoreCase(nome), ClienteConsultaDTO.class);
		} else {
			return mapper.map(repository.findByNomeContainingIgnoreCase(nome), ClienteConsultaDTO.class);
		}

	}

	public Cliente localizar(Integer id) {
		buscarPorId(id);
		return cliente;
	}

	public List<Telefone> listarTelefones(Integer id) {
		return this.repository.listarTelefones(id);
	}

	public List<TelefoneDTO> listarTelefonesDTO(Integer id) {
		List<TelefoneDTO> telefonesDTO = new ArrayList<TelefoneDTO>();
		listarTelefones(id).forEach(tel -> {

			String numero = "";

			if (tel.getTipo().equals(EnumTipoTelefone.CELULAR)) {
				numero = String.valueOf(tel.getNumero()).replaceFirst("(\\d{2})(\\d{5})(\\d+)", "($1) $2-$3");
			} else {
				numero = String.valueOf(tel.getNumero()).replaceFirst("(\\d{2})(\\d{4})(\\d+)", "($1) $2-$3");
			}

			TelefoneDTO dto = new TelefoneDTO();
			dto.setId(tel.getId());
			dto.setNumero(numero);
			dto.setObservacao(tel.getObservacao());
			telefonesDTO.add(dto);
		});

		return telefonesDTO;
	}

	public EnderecoDTO carregarEndereco(Integer id) {
		Endereco end = this.repository.listarEnderecos(id).stream()
				.filter(endereco -> endereco.getTipo().equals(EnumTipoEndereco.RESIDENCIAL)).findFirst().orElse(null);

		EnderecoDTO enderecoDTO = new EnderecoDTO();
		enderecoDTO.setRua(end.getRua());
		enderecoDTO.setBairro(end.getBairro());
		enderecoDTO.setNumero(end.getNumero());
		enderecoDTO.setTipo(end.getTipo());
		enderecoDTO.setComplemento(end.getComplemento());
		enderecoDTO.setCep(end.getCep());
		enderecoDTO.setCidade(mapper.map(end.getCidade(), CidadeDTO.class));
		return enderecoDTO;
	}

}

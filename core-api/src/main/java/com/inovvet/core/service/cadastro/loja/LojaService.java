package com.inovvet.core.service.cadastro.loja;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inovvet.app.config.Contexto;
import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.service.AccountsService;
import com.inovvet.app.util.FiltroUtil;
import com.inovvet.app.util.FunctionUtil;
import com.inovvet.app.util.MappingUtil;
import com.inovvet.core.entity.base.dto.FiltroDTO;
import com.inovvet.core.entity.loja.Loja;
import com.inovvet.core.entity.loja.dto.LojaCadastroDTO;
import com.inovvet.core.entity.loja.dto.LojaDTO;
import com.inovvet.core.entity.loja.dto.LojaFiltroDTO;
import com.inovvet.core.enumerator.EnumTipoDocumento;
import com.inovvet.core.repository.cadastro.loja.LojaRepository;
import com.inovvet.core.service.cadastro.usuario.UsuarioLojaService;

@Service
public class LojaService {

	@Autowired
	private LojaRepository repository;

	@Autowired
	private UsuarioLojaService usuarioLojaService;

	@Autowired
	private AccountsService accountsService;

	@Autowired
	private MappingUtil mapper;

	public Loja salvar(Loja loja) throws ServiceException {
		try {
			return repository.save(loja);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO, e.getMessage());
		}
	}

	private void validarLoja(LojaDTO dto, Integer id) throws ServiceException {

		if (FunctionUtil.isEmpty(dto.getDocumento())) {
			throw new ServiceException(EnumError.PARAMETROS_INVALIDOS,
					dto.getTipoDocumento().equals(EnumTipoDocumento.CNPJ) ? " CNPJ não informado"
							: " CPF não informado");
		}

		if (FunctionUtil.isCpf(dto.getDocumento())) {
			if (!FunctionUtil.validarCPF(dto.getDocumento())) {
				throw new ServiceException(EnumError.PARAMETROS_INVALIDOS, "CPF informado Inválido");
			}
		} else {

			if (!FunctionUtil.validarCNPJ(dto.getDocumento())) {
				throw new ServiceException(EnumError.PARAMETROS_INVALIDOS, "CNPJ informado inválido");
			}
		}

		Loja loja = repository.findByDocumento(dto.getDocumento()).orElse(null);
		if (loja != null && loja.getId() != null && !loja.getId().equals(id)) {
			throw new ServiceException(EnumError.PARAMETROS_INVALIDOS, "Loja já cadastrada");
		}
	}

	@Transactional
	public LojaCadastroDTO salvar(LojaDTO dto) {

		validarLoja(dto, 0);

		Loja loja = new Loja();

		mapper.map(dto, loja);

		Loja lojaCadastrada = accountsService.salvarLoja(loja, Contexto.getConta().getToken());

		loja.setToken(lojaCadastrada.getToken());
		loja.setAtivo(true);
		loja = salvar(loja);

		usuarioLojaService.salvar(Contexto.getUsuario(), loja);

		return mapper.map(loja, LojaCadastroDTO.class);

	}

	private Loja findByToken(String token) {
		return repository.findByToken(token).orElseThrow(() -> new ServiceException(EnumError.PARAMETROS_INVALIDOS));
	}

	@Transactional
	public LojaCadastroDTO atualizar(Integer id, LojaDTO dto) throws ServiceException {
		Loja loja = repository.findById(id)
				.orElseThrow(() -> new ServiceException(EnumError.PARAMETROS_INVALIDOS, "Loja não encontrada"));

		validarLoja(dto, id);

		mapper.map(dto, loja);

		loja = salvar(loja);

		return mapper.map(loja, LojaCadastroDTO.class);

	}

	public Loja carregarLoja(String token) {
		return findByToken(token);
	}

	public Iterable<Loja> carregarLojas() {

		return repository.findAll();

	}

	public Loja localizar(Integer id) {
		return repository.findById(id).orElseThrow(
				() -> new ServiceException(EnumError.REGISTRO_NAO_ECONTRADO, "Registro Loja não encontrado"));
	}

	public LojaCadastroDTO carregar(Integer id) {

		Loja loja = localizar(id);

		return mapper.map(loja, LojaCadastroDTO.class);
	}

	public Page<LojaCadastroDTO> filtrar(FiltroDTO<LojaFiltroDTO> filtro) {

		Example<Loja> example = FiltroUtil.getByExample(filtro, Loja.class);

		Page<Loja> list = repository.findAll(example,
				PageRequest.of(filtro.getPage(), filtro.getSize(), Sort.by(Sort.Direction.ASC, "nomeFantasia")));

		return list.map(entity -> {
			return mapper.map(entity, LojaCadastroDTO.class);
		});

	}

	@Transactional
	public void remover(Integer id) throws ServiceException {
		Loja loja = localizar(id);
		
		usuarioLojaService.removerUsuarios(loja);
		
		repository.delete(loja);
	}
	
}

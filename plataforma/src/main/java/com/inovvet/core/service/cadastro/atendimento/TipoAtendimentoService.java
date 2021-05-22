package com.inovvet.core.service.cadastro.atendimento;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inovvet.app.config.Contexto;
import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.service.AbstractService;
import com.inovvet.app.util.FiltroUtil;
import com.inovvet.core.entity.atendimento.TipoAtendimento;
import com.inovvet.core.entity.atendimento.TipoAtendimentoAgenda;
import com.inovvet.core.entity.atendimento.dto.TipoAtendimentoAgendaDTO;
import com.inovvet.core.entity.atendimento.dto.TipoAtendimentoCadastroDTO;
import com.inovvet.core.entity.atendimento.dto.TipoAtendimentoDTO;
import com.inovvet.core.entity.base.dto.EntityFiltro;
import com.inovvet.core.entity.base.dto.FiltroDTO;
import com.inovvet.core.entity.produto.Produto;
import com.inovvet.core.entity.produto.dto.ProdutoConsultaDTO;
import com.inovvet.core.enumerator.EnumDiaSemana;
import com.inovvet.core.repository.cadastro.atendimento.TipoAtendimentoRepository;
import com.inovvet.core.service.cadastro.CrudService;
import com.inovvet.core.service.cadastro.produto.ProdutoService;

@Service
public class TipoAtendimentoService extends AbstractService
		implements CrudService<TipoAtendimento, TipoAtendimentoDTO, TipoAtendimentoCadastroDTO, EntityFiltro> {

	@Autowired
	private TipoAtendimentoRepository repository;

	@Autowired
	private ProdutoService produtoService;

	@Override
	public TipoAtendimento localizar(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ServiceException(EnumError.REGISTRO_NAO_ECONTRADO,
				"Registro Tipo Atendimento não encontrado"));
	}
	

	@Override
	public TipoAtendimentoCadastroDTO carregar(Integer id) {
		TipoAtendimento tipoAtendimento = localizar(id);
		
		TipoAtendimentoCadastroDTO tipoAtendimentoCadastro = mapper.map(tipoAtendimento,
				TipoAtendimentoCadastroDTO.class);

		converterProduto(tipoAtendimentoCadastro);

		tipoAtendimentoCadastro.getAgenda().sort(Comparator.comparing(TipoAtendimentoAgendaDTO::getHora));

		return tipoAtendimentoCadastro;
	}
	

	public TipoAtendimentoCadastroDTO carregar(Integer id, EnumDiaSemana diaSemana) {
		TipoAtendimento tipoAtendimento = repository
											.carregarAtendimentoAgenda(id, diaSemana)
											.orElseThrow(() 
													-> new ServiceException(EnumError.REGISTRO_NAO_ECONTRADO,  "Registro Tipo Atendimento não encontrado"));
		
		TipoAtendimentoCadastroDTO tipoAtendimentoCadastro = mapper.map(tipoAtendimento,
				TipoAtendimentoCadastroDTO.class);

		converterProduto(tipoAtendimentoCadastro);

		tipoAtendimentoCadastro.getAgenda().sort(Comparator.comparing(TipoAtendimentoAgendaDTO::getHora));

		return tipoAtendimentoCadastro;
	}

	@Override
	public TipoAtendimentoCadastroDTO novo(TipoAtendimentoDTO dto) {

		TipoAtendimento tipoAtendimento = salvar(dto, new TipoAtendimento());

		TipoAtendimentoCadastroDTO tipoAtendimentoCadastro = mapper.map(tipoAtendimento,
				TipoAtendimentoCadastroDTO.class);

		converterProduto(tipoAtendimentoCadastro);

		return tipoAtendimentoCadastro;
	}

	@Override
	public TipoAtendimentoCadastroDTO atualizar(Integer id, TipoAtendimentoDTO dto) {
		TipoAtendimento tipoAtendimento = localizar(id);

		tipoAtendimento = salvar(dto, tipoAtendimento);

		TipoAtendimentoCadastroDTO tipoAtendimentoCadastro = mapper.map(tipoAtendimento,
				TipoAtendimentoCadastroDTO.class);

		converterProduto(tipoAtendimentoCadastro);

		return tipoAtendimentoCadastro;
	}

	@Override
	public void remover(TipoAtendimento entity) {
		try {
			repository.delete(entity);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_REMOVER_REGISTRO, "Registro já utilizado não pode ser removido");
		}

	}

	@Override
	public void remover(Integer id) {
		remover(localizar(id));

	}

	@Override
	public List<TipoAtendimentoCadastroDTO> listar() {
		return mapper.map(repository.findByAtivo(true), TipoAtendimentoCadastroDTO.class);
	}

	@Override
	public Page<TipoAtendimentoCadastroDTO> filtrar(FiltroDTO<EntityFiltro> filtroDTO) {
		Example<TipoAtendimento> example = FiltroUtil.getByExample(filtroDTO, TipoAtendimento.class);

		Page<TipoAtendimento> list = repository.findAll(example,
				PageRequest.of(filtroDTO.getPage(), filtroDTO.getSize(), Sort.by(Sort.Direction.ASC, "nome")));

		return list.map(entity -> {
			return mapper.map(entity, TipoAtendimentoCadastroDTO.class);
		});
	}

	public void converterProduto(TipoAtendimentoCadastroDTO dto) {

		dto.setProdutos(
				dto.getProdutos().stream().map(produto -> produtoService.consultar(Contexto.getLoja(), produto.getId()))
						.collect(Collectors.toSet()));

	}

	private Set<Produto> converterPreco(Set<ProdutoConsultaDTO> produtos) {

		Set<Produto> lista = new HashSet<Produto>();

		produtos.forEach(dto -> {

			lista.add(produtoService.localizar(dto.getId()));
		});

		return lista;
	}

	private TipoAtendimento salvar(TipoAtendimento entity) {
		try {
			return repository.save(entity);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO, e.getMessage());
		}

	}
	
	private Boolean registroExiste(List<TipoAtendimentoAgendaDTO> listaDTO, Integer id) {
		for (TipoAtendimentoAgendaDTO dto : listaDTO) {
			if ( dto.getId() != null) {
				if (dto.getId().equals(id)) {
					return true;
				}
			}
		}
		return false;
	}

	private Set<TipoAtendimentoAgenda> atualizarAgenda(Set<TipoAtendimentoAgenda> agendas,
			List<TipoAtendimentoAgendaDTO> dto) {
		if (agendas == null) {
			agendas = new HashSet<TipoAtendimentoAgenda>();
		}

		if (agendas.size() > 0) {
			agendas.removeIf(value -> !registroExiste(dto, value.getId()));
		}

		for (TipoAtendimentoAgendaDTO tipoAtendimentoAgendaDTO : dto) {	
			
			TipoAtendimentoAgenda registro = null; 
			
			for (TipoAtendimentoAgenda tipoAtendimentoAgenda : agendas) {
				if(tipoAtendimentoAgenda.getId() != null && tipoAtendimentoAgenda.getId().equals(tipoAtendimentoAgendaDTO.getId())) {
					registro = tipoAtendimentoAgenda;
				}
			}
			
			if(registro == null) {
				registro = mapper.map(tipoAtendimentoAgendaDTO, TipoAtendimentoAgenda.class);
			}else {
				mapper.copy(tipoAtendimentoAgendaDTO, registro);
			}
			
			
			agendas.add(registro);
		}

		return agendas;
	}

	private TipoAtendimento salvar(TipoAtendimentoDTO dto, TipoAtendimento tipoAtendimento) {

		Set<Produto> produtos = converterPreco(dto.getProdutos());
		tipoAtendimento.getProdutos().clear();

		tipoAtendimento.setNome(dto.getNome());
		tipoAtendimento.setAtivo(dto.getAtivo());
		tipoAtendimento.setTempo(dto.getTempo());
		tipoAtendimento.getProdutos().addAll(produtos);
		tipoAtendimento.setAgenda(atualizarAgenda(tipoAtendimento.getAgenda(), dto.getAgenda()));

		if (tipoAtendimento.getId() == null) {
			tipoAtendimento.setAtivo(true);
		}

		if (tipoAtendimento.getTempo() == null) {
			tipoAtendimento.setTempo(0);
		}

		return salvar(tipoAtendimento);
	}

}

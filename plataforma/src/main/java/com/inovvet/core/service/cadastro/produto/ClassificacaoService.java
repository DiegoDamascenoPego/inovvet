package com.inovvet.core.service.cadastro.produto;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.core.entity.base.dto.FiltroDTO;
import com.inovvet.core.entity.classificacao.Classificacao;
import com.inovvet.core.entity.classificacao.dto.ClassificacaoCadastroDTO;
import com.inovvet.core.entity.classificacao.dto.ClassificacaoDTO;
import com.inovvet.core.entity.classificacao.dto.ClassificacaoFiltroDTO;
import com.inovvet.core.entity.classificacao.dto.ClassificacaoListaDTO;
import com.inovvet.core.entity.classificacao.dto.OpcaoDTO;
import com.inovvet.core.repository.cadastro.produto.ClassificacaoRepository;
import com.inovvet.core.service.base.AbstractService;

@Service
public class ClassificacaoService extends AbstractService {

	@Autowired
	private ClassificacaoRepository repository;

	@Autowired
	private DepartamentoService departamentoService;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private GrupoService grupoService;

	@Autowired
	private SubGrupoService subGrupoService;

	private Classificacao salvar(Classificacao entity) {
		try {
			return repository.save(entity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO, e.getMessage());
		}

	}

	private Classificacao salvar(ClassificacaoDTO entity, Classificacao classificacao) {

		classificacao.setDepartamento(departamentoService.localizar(entity.getDepartamento().getId()));
		classificacao.setCategoria(categoriaService.localizar(entity.getCategoria().getId()));
		classificacao.setGrupo(grupoService.localizar(entity.getGrupo().getId()));
		classificacao.setSubgrupo(subGrupoService.localizar(entity.getSubgrupo().getId()));
		classificacao.setAtivo(true);

		return salvar(classificacao);
	}

	private void validarRegistroDuplicado(Integer id, ClassificacaoDTO entity) {
		localizar(entity).forEach(classificacao -> {
			if (classificacao != null) {
				if (!classificacao.getId().equals(id)) {
					throw new ServiceException(EnumError.REGISTRO_DUPLICADO);
				}
			}
		});

	}

	private List<OpcaoDTO> carregarClassificacoes(Integer departamentoId) {
		return repository.listarDescricao(departamentoId);
	}

	public Classificacao localizar(Integer id) {
		return repository.findById(id).orElseThrow(
				() -> new ServiceException(EnumError.REGISTRO_NAO_ECONTRADO, "Registro Classificação não encontrado"));
	}

	public List<Classificacao> localizar(ClassificacaoDTO entity) {
		return repository.listarClassificacao(entity.getDepartamento().getId(), entity.getCategoria().getId(),
				entity.getGrupo().getId(), entity.getSubgrupo().getId());
	}

	public ClassificacaoCadastroDTO carregar(Integer id) {

		Classificacao classificacao = localizar(id);

		return mapper.map(classificacao, ClassificacaoCadastroDTO.class);
	}

	@Transactional
	public ClassificacaoCadastroDTO novo(ClassificacaoDTO entity) {
		if (!localizar(entity).isEmpty()) {
			throw new ServiceException(EnumError.REGISTRO_DUPLICADO);
		}

		Classificacao classificacao = salvar(entity, new Classificacao());

		return mapper.map(classificacao, ClassificacaoCadastroDTO.class);
	}

	@Transactional
	public ClassificacaoCadastroDTO atualizar(Integer id, ClassificacaoDTO entity) {
		validarRegistroDuplicado(id, entity);

		Classificacao classificacao = localizar(id);

		classificacao = salvar(entity, classificacao);

		return mapper.map(classificacao, ClassificacaoCadastroDTO.class);
	}

	@Transactional
	public void remover(Classificacao classificacao) {
		try {
			repository.delete(classificacao);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_REMOVER_REGISTRO, "Registro já utilizado não pode ser removido");
		}
	}

	@Transactional
	public void remover(Integer id) {
		Classificacao classificacao = localizar(id);

		remover(classificacao);
	}

	public List<ClassificacaoCadastroDTO> listar() {
		return mapper.map(repository.listarClassificacaoAtiva(), ClassificacaoCadastroDTO.class);

	}

	public Page<ClassificacaoCadastroDTO> filtrar(FiltroDTO<ClassificacaoFiltroDTO> filtro) {

		Page<Classificacao> list = repository.filtrarClassificacao(filtro.getObj(),
				PageRequest.of(filtro.getPage(), filtro.getSize(), Sort.by(Sort.Direction.ASC, "departamento.nome")));

		return list.map(entity -> {
			return mapper.map(entity, ClassificacaoCadastroDTO.class);
		});

	}

	public List<ClassificacaoListaDTO> listarDescricao() {

		List<ClassificacaoListaDTO> listaClassificacao = new ArrayList<>();

		departamentoService.listar().forEach(departamento -> {

			ClassificacaoListaDTO lista = new ClassificacaoListaDTO(departamento.getId(), departamento.getNome(),
					carregarClassificacoes(departamento.getId()));

			listaClassificacao.add(lista);
		});

		return listaClassificacao;
	}
	
	public OpcaoDTO carregarClassificacaoCadastro(Integer id) {
		return repository.carregar(id);
	}

}

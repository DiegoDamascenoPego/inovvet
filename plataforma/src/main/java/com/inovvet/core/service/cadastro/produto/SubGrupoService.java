package com.inovvet.core.service.cadastro.produto;

import java.util.List;
import java.util.Optional;

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
import com.inovvet.core.entity.base.dto.FiltroDTO;
import com.inovvet.core.entity.grupo.dto.GrupoCadastroDTO;
import com.inovvet.core.entity.subgrupo.SubGrupo;
import com.inovvet.core.entity.subgrupo.dto.SubGrupoCadastroDTO;
import com.inovvet.core.entity.subgrupo.dto.SubGrupoDTO;
import com.inovvet.core.entity.subgrupo.dto.SubGrupoFiltroDTO;
import com.inovvet.core.repository.cadastro.produto.SubGrupoRepository;
import com.inovvet.core.service.base.AbstractService;

@Service
public class SubGrupoService extends AbstractService {

	@Autowired
	private SubGrupoRepository repository;

	private SubGrupo salvar(SubGrupo entity) {
		try {
			return repository.save(entity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO, e.getMessage());
		}

	}

	private SubGrupo salvar(SubGrupoDTO entity, SubGrupo subGrupo) {

		mapper.copy(entity, subGrupo);

		if (subGrupo.getId() == null) {
			subGrupo.setAtivo(true);
		}

		return salvar(subGrupo);
	}

	private void validarRegistroDuplicado(Integer id, SubGrupoDTO entity) {
		SubGrupo subGrupo = localizar(entity.getNome()).orElse(null);

		if (subGrupo != null) {
			if (!subGrupo.getId().equals(id)) {
				throw new ServiceException(EnumError.REGISTRO_DUPLICADO);
			}
		}

	}

	public SubGrupo localizar(Integer id) {
		return repository.findById(id).orElseThrow(
				() -> new ServiceException(EnumError.REGISTRO_NAO_ECONTRADO, "Registro SubGrupo não encontrado"));
	}

	public Optional<SubGrupo> localizar(String nome) {
		return repository.findByNome(nome);
	}

	public SubGrupoCadastroDTO carregar(Integer id) {

		SubGrupo subGrupo = localizar(id);

		return mapper.map(subGrupo, SubGrupoCadastroDTO.class);
	}

	@Transactional
	public SubGrupoCadastroDTO novo(SubGrupoDTO entity) {
		if (localizar(entity.getNome()).isPresent()) {
			throw new ServiceException(EnumError.REGISTRO_DUPLICADO);
		}

		SubGrupo subGrupo = salvar(entity, new SubGrupo());

		return mapper.map(subGrupo, SubGrupoCadastroDTO.class);
	}

	@Transactional
	public SubGrupoDTO atualizar(Integer id, SubGrupoDTO entity) {
		validarRegistroDuplicado(id, entity);

		SubGrupo subGrupo = localizar(id);

		subGrupo = salvar(entity, subGrupo);

		return mapper.map(subGrupo, SubGrupoCadastroDTO.class);
	}

	@Transactional
	public void remover(SubGrupo subGrupo) {

		try {
			repository.delete(subGrupo);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_REMOVER_REGISTRO, "Registro já utilizado não pode ser removido");
		}
	}

	@Transactional
	public void remover(Integer id) {
		SubGrupo subGrupo = localizar(id);

		remover(subGrupo);
	}

	public List<GrupoCadastroDTO> listar() {
		return mapper.map(repository.findByAtivo(true), GrupoCadastroDTO.class);

	}

	public Page<SubGrupoCadastroDTO> filtrar(FiltroDTO<SubGrupoFiltroDTO> filtro) {

		Example<SubGrupo> example = FiltroUtil.getByExample(filtro, SubGrupo.class);

		Page<SubGrupo> list = repository.findAll(example,
				PageRequest.of(filtro.getPage(), filtro.getSize(), Sort.by(Sort.Direction.ASC, "nome")));

		return list.map(entity -> {
			return mapper.map(entity, SubGrupoCadastroDTO.class);
		});

	}

}

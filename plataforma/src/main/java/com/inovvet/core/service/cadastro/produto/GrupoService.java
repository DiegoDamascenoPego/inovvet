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
import com.inovvet.core.entity.grupo.Grupo;
import com.inovvet.core.entity.grupo.dto.GrupoCadastroDTO;
import com.inovvet.core.entity.grupo.dto.GrupoDTO;
import com.inovvet.core.entity.grupo.dto.GrupoFiltroDTO;
import com.inovvet.core.repository.cadastro.produto.GrupoRepository;
import com.inovvet.core.service.base.AbstractService;

@Service
public class GrupoService extends AbstractService {

	@Autowired
	private GrupoRepository repository;

	private Grupo salvar(Grupo entity) {
		try {
			return repository.save(entity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO, e.getMessage());
		}

	}

	private Grupo salvar(GrupoDTO entity, Grupo grupo) {

		mapper.copy(entity, grupo);


		if (grupo.getId() == null) {
			grupo.setAtivo(true);
		}

		return salvar(grupo);
	}

	private void validarRegistroDuplicado(Integer id, GrupoDTO entity) {
		Grupo grupo = localizar(entity.getNome()).orElse(null);

		if (grupo != null) {
			if (!grupo.getId().equals(id)) {
				throw new ServiceException(EnumError.REGISTRO_DUPLICADO);
			}
		}

	}

	public Grupo localizar(Integer id) {
		return repository.findById(id).orElseThrow(
				() -> new ServiceException(EnumError.REGISTRO_NAO_ECONTRADO, "Registro Grupo não encontrado"));
	}

	public Optional<Grupo> localizar(String nome) {
		return repository.findByNome(nome);
	}

	public GrupoCadastroDTO carregar(Integer id) {

		Grupo grupo = localizar(id);

		return mapper.map(grupo, GrupoCadastroDTO.class);
	}

	@Transactional
	public GrupoCadastroDTO novo(GrupoDTO entity) {
		if (localizar(entity.getNome()).isPresent()) {
			throw new ServiceException(EnumError.REGISTRO_DUPLICADO);
		}

		Grupo grupo = salvar(entity, new Grupo());

		return mapper.map(grupo, GrupoCadastroDTO.class);
	}

	@Transactional
	public GrupoDTO atualizar(Integer id, GrupoDTO entity) {
		validarRegistroDuplicado(id, entity);

		Grupo grupo = localizar(id);

		grupo = salvar(entity, grupo);

		return mapper.map(grupo, GrupoCadastroDTO.class);
	}

	@Transactional
	public void remover(Grupo grupo) {

		try {
			repository.delete(grupo);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_REMOVER_REGISTRO, "Registro já utilizado não pode ser removido");
		}
	}

	@Transactional
	public void remover(Integer id) {
		Grupo grupo = localizar(id);

		remover(grupo);
	}
	
	public List<GrupoCadastroDTO> listar() {
		return mapper.map(repository.findByAtivo(true), GrupoCadastroDTO.class);

	}

	public Page<GrupoCadastroDTO> filtrar(FiltroDTO<GrupoFiltroDTO> filtro) {

		Example<Grupo> example = FiltroUtil.getByExample(filtro, Grupo.class);

		Page<Grupo> list = repository.findAll(example,
				PageRequest.of(filtro.getPage(), filtro.getSize(), Sort.by(Sort.Direction.ASC, "nome")));

		return list.map(entity -> {
			return mapper.map(entity, GrupoCadastroDTO.class);
		});

	}

}

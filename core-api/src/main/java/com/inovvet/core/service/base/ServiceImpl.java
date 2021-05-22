package com.inovvet.core.service.base;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.util.MappingUtil;
import com.inovvet.core.entity.base.dto.FiltroDTO;

public abstract class ServiceImpl<T, D, C, F, Repository extends JpaRepository<T, Integer>> {

	@Autowired
	protected Repository repository;

	@Autowired
	protected MappingUtil mapper;

	protected T salvar(T entity) {
		try {
			return repository.save(entity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO, e.getMessage());
		}

	}

	protected void remover(T entity) {
		try {
			repository.delete(entity);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_REMOVER_REGISTRO, "Registro já utilizado não pode ser removido");
		}

	}

	protected T localizar(Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ServiceException(EnumError.REGISTRO_NAO_ECONTRADO, "Registro não encontrado"));
	}

	@Transactional
	public void remover(Integer id) {

		remover(localizar(id));
	}

	protected abstract T salvar(D dto, T entity);

	public abstract C carregar(Integer id);

	public abstract C novo(D dto);

	public abstract C atualizar(Integer id, D dto);

	public abstract List<C> listar();

	public abstract Page<C> filtrar(FiltroDTO<F> filtroDTO);

}

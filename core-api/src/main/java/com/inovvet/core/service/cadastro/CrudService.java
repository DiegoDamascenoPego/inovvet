package com.inovvet.core.service.cadastro;

import java.util.List;

import org.springframework.data.domain.Page;

import com.inovvet.core.entity.base.dto.FiltroDTO;

public interface CrudService<T, D, C, F> {

	public T localizar(Integer id);

	public C carregar(Integer id);

	public C novo(D dto);

	public C atualizar(Integer id, D dto);

	public void remover(T entity);

	public void remover(Integer id);

	public List<C> listar();

	public Page<C> filtrar(FiltroDTO<F> filtroDTO);

}

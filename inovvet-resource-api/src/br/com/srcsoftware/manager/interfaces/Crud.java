package br.com.srcsoftware.manager.interfaces;

import java.util.List;

import br.com.srcsoftware.manager.abstracts.AbsctractPO;
import br.com.srcsoftware.manager.exceptions.BackendException;

public interface Crud{

	void inserir( AbsctractPO po ) throws BackendException;

	void alterar( AbsctractPO po ) throws BackendException;

	void excluir( AbsctractPO po ) throws BackendException;

	List filtrar( AbsctractPO po ) throws BackendException;

	AbsctractPO filtrarPorId( String id ) throws BackendException;

}

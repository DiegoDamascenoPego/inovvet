package br.com.srcsoftware.controlstocksolution.moduloproduto.unidade.controller;

import java.util.List;

import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.model.UnidadeSERVICE;
import br.com.srcsoftware.manager.abstracts.AbsctractPO;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.manager.interfaces.Crud;

public class UnidadeFACADE implements Crud{

	private final UnidadeSERVICE SERVICE;

	public UnidadeFACADE(){
		SERVICE = new UnidadeSERVICE();
	}

	@Override
	public void inserir( AbsctractPO po ) throws BackendException {
		SERVICE.inserir( po );

	}

	@Override
	public void alterar( AbsctractPO po ) throws BackendException {
		SERVICE.alterar( po );

	}

	@Override
	public void excluir( AbsctractPO po ) throws BackendException {
		SERVICE.excluir( po );

	}

	@Override
	public List filtrar( AbsctractPO po ) throws BackendException {
		return SERVICE.filtrar( po );
	}

	@Override
	public AbsctractPO filtrarPorId( String id ) throws BackendException {
		return SERVICE.filtrarPorId( id );
	}

}

package br.com.srcsoftware.controlstocksolution.moduloproduto.produto.controller;

import java.util.List;

import br.com.srcsoftware.controlstocksolution.moduloproduto.produto.model.ProdutoSERVICE;
import br.com.srcsoftware.manager.abstracts.AbsctractPO;
import br.com.srcsoftware.manager.exceptions.BackendException;
import br.com.srcsoftware.manager.interfaces.Crud;

/**
 * Classe que representa a ligação entre o front-end e o back-end
 * classe responável por interligar a camda VIEW com a camada de MODEL(negócio);
 *
 *
 * @author Diego Pêgo <diegodamascenopego@hotmail.com.br>
 * @since 24 de jul de 2018 22:27:02
 * @version 1.0
 */
public final class ProdutoFACADE implements Crud{

	private final ProdutoSERVICE SERVICE;

	public ProdutoFACADE(){
		SERVICE = new ProdutoSERVICE();
	}

	@Override
	public void inserir( AbsctractPO po ) throws BackendException {
		System.out.println( "Facade inserir" );
		SERVICE.inserir( po );

	}

	@Override
	public void alterar( AbsctractPO po ) throws BackendException {
		System.out.println( "Facade alterar" );
		SERVICE.alterar( po );

	}

	@Override
	public void excluir( AbsctractPO po ) throws BackendException {
		System.out.println( "Facade excluir" );
		SERVICE.excluir( po );

	}

	@Override
	public List filtrar( AbsctractPO po ) throws BackendException {
		System.out.println( "Facade filtrar" );
		return SERVICE.filtrar( po );
	}

	@Override
	public AbsctractPO filtrarPorId( String id ) throws BackendException {
		System.out.println( "Facade filtrar por id" );
		return SERVICE.filtrarPorId( id );
	}

}

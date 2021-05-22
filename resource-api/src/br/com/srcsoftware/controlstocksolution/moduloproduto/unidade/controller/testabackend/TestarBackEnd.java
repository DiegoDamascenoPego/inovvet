package br.com.srcsoftware.controlstocksolution.moduloproduto.unidade.controller.testabackend;

import java.util.List;

import br.com.srcsoftware.controlstocksolution.moduloproduto.unidade.controller.UnidadeFACADE;
import br.com.srcsoftware.controlstocksolution.moduloproduto.unidade.model.UnidadePO;
import br.com.srcsoftware.manager.exceptions.BackendException;

public class TestarBackEnd{

	public static void main( String[ ] args ) throws BackendException {
		UnidadePO unidade = new UnidadePO();
		unidade.setNome( "Unidade" );
		unidade.setSigla( "UN" );

		new UnidadeFACADE().inserir( unidade );

		List encontrados = new UnidadeFACADE().filtrar( null );
		System.out.println( encontrados );

		UnidadePO unidadePO = (UnidadePO) new UnidadeFACADE().filtrarPorId( "2" );
		unidadePO.setNome( "Litro" );
		unidadePO.setSigla( "LT" );

		new UnidadeFACADE().alterar( unidadePO );

	}

}

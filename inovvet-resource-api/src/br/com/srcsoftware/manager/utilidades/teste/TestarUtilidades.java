package br.com.srcsoftware.manager.utilidades.teste;

import java.math.BigDecimal;

import br.com.srcsoftware.manager.utilidades.Utilidades;

public class TestarUtilidades{

	public static void main( String[ ] args ) {

		System.out.println( Utilidades.parseBigDecimal( new BigDecimal( "1987.96" ), "R$" ) );

	}

}

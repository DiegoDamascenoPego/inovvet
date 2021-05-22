package br.com.srcsoftware.manager.utilidades;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 
 * Classe de utilidades do sistema
 *
 *
 * @author Diego Pêgo <diegodamascenopego@hotmail.com.br>
 * @since 25 de jul de 2018 22:45:22
 * @version 1.0
 */
public abstract class Utilidades{

	public static final String SCHEMA = "control_stock_solution_t34";
	public static final String REGEX_SOMENTE_LETRAS_E_ESPACO = "[À-ú A-z]+";

	public static final LocalDate parseLocaldate( String data ) {
		if ( data == "" || data.isEmpty() || !data.contains( "/" ) ) {
			return null;
		}

		LocalDate novaData = LocalDate.parse( data, DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) );

		return novaData;
	}

	public static final String parseLocaldate( LocalDate data ) {
		if ( data == null ) {
			return null;
		}

		String novaData = data.format( DateTimeFormatter.ofPattern( "dd/MM/yyyy" ) );

		return novaData;
	}

	public static final BigDecimal parseBigDecimal( String valor ) {

		if ( valor == "" || valor.isEmpty() ) {
			return null;
		}

		BigDecimal novoValor = new BigDecimal( valor.replace( ".", "" ).replace( ",", "." ).trim() );

		return novoValor;
	}

	public static final String parseBigDecimal( BigDecimal valor ) {
		if ( valor == null ) {
			return null;
		}

		return String.format( "%,.2f", valor );
	}

	public static final String parseBigDecimal( BigDecimal valor, String moeda ) {
		if ( valor == null ) {
			return null;
		}

		return moeda + " " + String.format( "%,.2f", valor ).trim();
	}

	public static final LocalDateTime parseLocaldateTime( String dataHoraTime ) {
		if ( dataHoraTime == "" || dataHoraTime.isEmpty() || !dataHoraTime.contains( "/" ) || !dataHoraTime.contains( ":" ) ) {
			return null;
		}

		LocalDateTime novaDataHoraTime = LocalDateTime.parse( dataHoraTime, DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss" ) );

		return novaDataHoraTime;
	}

	public static final String parseLocaldateTime( LocalDateTime dataHoraTime ) {
		if ( dataHoraTime == null ) {
			return null;
		}

		String novaDataTime = dataHoraTime.format( DateTimeFormatter.ofPattern( "dd/MM/yyyy HH:mm:ss" ) );

		return novaDataTime;
	}

}

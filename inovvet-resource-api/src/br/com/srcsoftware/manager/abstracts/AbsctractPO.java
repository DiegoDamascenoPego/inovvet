package br.com.srcsoftware.manager.abstracts;

import java.beans.Transient;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import br.com.srcsoftware.manager.utilidades.Utilidades;

/**
 * @MappedSuperclass: Informa ao Hibernate que esta classe não
 *                    possuira Tabela, ela é apenas uma SuperClasse
 * 
 *                    OU SEJA
 * 
 *                    Mapeia esta classe como apenas uma classe de Herança, onde todos
 *                    seu atributos serão implementados nas tabelas que representam as
 *                    classes FILHAS
 *
 *
 * @author Diego Pêgo <diegodamascenopego@hotmail.com.br>
 * @since 6 de ago de 2018 20:26:53
 * @version 1.0
 */

@MappedSuperclass
public abstract class AbsctractPO{

	public AbsctractPO(){
		setDataHoraCadastrado( LocalDateTime.now() );
	}

	@Column( name = "dataHoraCadastro", nullable = false )
	private LocalDateTime dataHoraCadastrado;

	public LocalDateTime getDataHoraCadastrado() {
		return dataHoraCadastrado;
	}

	public void setDataHoraCadastrado( LocalDateTime dataHoraCadastrado ) {
		this.dataHoraCadastrado = dataHoraCadastrado;
	}

	@Transient
	public String getDataHoraCadastroToString() {
		if ( getDataHoraCadastrado() != null ) {
			return Utilidades.parseLocaldateTime( getDataHoraCadastrado() );
		}
		return null;
	}

	public void setDataHoraCadastradoToString( String dataHoraCadastro ) {
		if ( dataHoraCadastro != null && !dataHoraCadastro.isEmpty() ) {
			setDataHoraCadastrado( Utilidades.parseLocaldateTime( dataHoraCadastro ) );
			return;
		}

		setDataHoraCadastrado( null );
	}

	@Override
	public abstract String toString();

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals( Object obj );
}

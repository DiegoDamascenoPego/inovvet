package br.com.srcsoftware.controlstocksolution.moduloproduto.unidade.model;

import java.text.Collator;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.srcsoftware.manager.abstracts.AbsctractPO;

@Entity
@Table( name = "unidades" )
public class UnidadePO extends AbsctractPO implements Comparable< UnidadePO >{

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;

	@Column( length = 20, nullable = false )
	private String nome;

	@Column( length = 2, nullable = false )
	private String sigla;

	public String getSigla() {
		return sigla;
	}

	public void setSigla( String sigla ) {
		this.sigla = sigla;
	}

	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	@Transient
	public String getIdToString() {
		if ( getId() != null ) {
			return getId().toString();
		}
		return null;
	}

	public void setIdToString( String id ) {
		if ( id != null && !id.isEmpty() ) {
			setId( Long.valueOf( id ) );
			return;
		}

		setId( null );
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
		result = prime * result + ( ( sigla == null ) ? 0 : sigla.hashCode() );
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj ) {
			return true;
		}
		if ( obj == null ) {
			return false;
		}
		if ( !( obj instanceof UnidadePO ) ) {
			return false;
		}
		UnidadePO other = (UnidadePO) obj;
		if ( nome == null ) {
			if ( other.nome != null ) {
				return false;
			}
		} else if ( !nome.equals( other.nome ) ) {
			return false;
		}
		if ( sigla == null ) {
			if ( other.sigla != null ) {
				return false;
			}
		} else if ( !sigla.equals( other.sigla ) ) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "UnidadePO \n\t[id=" );
		builder.append( id );
		builder.append( ", \n\tnome=" );
		builder.append( nome );
		builder.append( ", \n\tsigla=" );
		builder.append( sigla );
		builder.append( "]\n" );
		return builder.toString();
	}

	@Override
	public int compareTo( UnidadePO comparar ) {
		Collator ignoraAscentos = Collator.getInstance( new Locale( "pt", "BR" ) );

		return ignoraAscentos.compare( this.getNome(), comparar.getNome() );
	}
}

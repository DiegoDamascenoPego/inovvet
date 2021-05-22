package com.inovvet.core.entity.pessoa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.inovvet.core.enumerator.EnumFinalidadePessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pessoa")
public class Fabricante extends Pessoa {

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, orphanRemoval = true)
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Set<Endereco> enderecos;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, orphanRemoval = true)
	@JoinColumn(name = "pessoa_id", nullable = false)
	private Set<Telefone> telefones;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.REMOVE }, orphanRemoval = true)
	@JoinColumn(name = "fabricante_id", nullable = false)
	private Set<Marca> marcas;

	public Fabricante() {
		this.setTipo(EnumFinalidadePessoa.FABRICANTE);
		this.marcas = new HashSet<Marca>();
		this.enderecos = new HashSet<Endereco>();
		this.telefones = new HashSet<Telefone>();
	}

}

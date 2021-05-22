package com.inovvet.core.entity.animal;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inovvet.app.entity.AbstractEntity;
import com.inovvet.core.entity.cliente.Cliente;
import com.inovvet.core.enumerator.EnumSexo;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Animal")
@Getter
@Setter
public class Animal extends AbstractEntity {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@NotNull(message = "Tipo do Animal não foi informado")
	@Enumerated(EnumType.ORDINAL)
	private EnumTipoAnimal tipo;

	
	@Size(max = 150)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;
	
	@NotNull(message = "Sexo não foi informado")
	@Enumerated(EnumType.ORDINAL)
	private EnumSexo sexo;
	
	@NotNull(message = "Raça não foi informado")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "raca_id")
	private Raca raca;	
	
	@NotNull(message = "Cor não foi informado")
	private String cor;
	
	private Boolean castrado;
	
	@NotNull(message = "Tipo do pêlo não foi informado")
	@Enumerated(EnumType.ORDINAL)
	private EnumTipoPelo pelo;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone = "UTC")
	private LocalDate dataNascimento;
	
	private Boolean ativo;
	
	public Animal() {
		this.cor = "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((castrado == null) ? 0 : castrado.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((pelo == null) ? 0 : pelo.hashCode());
		result = prime * result + ((raca == null) ? 0 : raca.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Animal))
			return false;
		Animal other = (Animal) obj;
		if (castrado == null) {
			if (other.castrado != null)
				return false;
		} else if (!castrado.equals(other.castrado))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (cor == null) {
			if (other.cor != null)
				return false;
		} else if (!cor.equals(other.cor))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (pelo != other.pelo)
			return false;
		if (raca == null) {
			if (other.raca != null)
				return false;
		} else if (!raca.equals(other.raca))
			return false;
		if (sexo != other.sexo)
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}
	
	
	
}

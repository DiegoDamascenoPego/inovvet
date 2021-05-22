package com.inovvet.core.entity.cliente;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inovvet.app.entity.AbstractEntity;
import com.inovvet.core.entity.loja.Loja;
import com.inovvet.core.enumerator.EnumSexo;
import com.inovvet.core.enumerator.EnumTipoPessoa;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente extends AbstractEntity {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loja_id")
	private Loja loja;

	@NotNull(message = "Tipo do Cadastro não foi informado")
	@Enumerated(EnumType.ORDINAL)
	private EnumTipoPessoa tipo;

	@NotNull
	@Size(max = 150)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;

	@CPF(message = "O Campo CPF deve ser informado.")
	private String cpf;

	@Size(max = 150)
	private String email;

	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy", timezone = "UTC")
	private LocalDate dataNascimento;
	
	@Size(max = 255)
	private String observacao;
	
	private Boolean ativo;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private EnumSexo sexo;

	@OneToOne(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Endereco endereco;

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
	@JoinColumn(name = "cliente_id", nullable=false)
	private Set<Telefone> telefones;
	
	public Cliente() {
		this.tipo = EnumTipoPessoa.FISICA;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}

}

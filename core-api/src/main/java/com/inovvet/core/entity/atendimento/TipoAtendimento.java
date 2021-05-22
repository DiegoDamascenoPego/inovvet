package com.inovvet.core.entity.atendimento;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.inovvet.app.entity.AbstractEntity;
import com.inovvet.core.entity.produto.Produto;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TipoAtendimento extends AbstractEntity {

	@Size(max = 50)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;

	@Min(value = 0, message = "Valor inválido")
	@Max(value = 360, message = "Valor inválido")
	private Integer tempo;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tipo_atendimento_produto", joinColumns = {
			@JoinColumn(name = "tipo_atendimento_id") }, inverseJoinColumns = { @JoinColumn(name = "produto_id") })
	private Set<Produto> produtos;
	
	@OrderBy("dia, hora")
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true)
	@JoinColumn(name = "tipo_atendimento_id", nullable=false)
	private Set<TipoAtendimentoAgenda> agenda;

	@NotNull
	private Boolean ativo;

	public TipoAtendimento() {
		super();
		this.produtos = new HashSet<Produto>();
		this.setAtivo(true);
	}

}

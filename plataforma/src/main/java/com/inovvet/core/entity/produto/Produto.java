package com.inovvet.core.entity.produto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.EAN;

import com.inovvet.app.entity.AbstractEntity;
import com.inovvet.core.entity.classificacao.Classificacao;
import com.inovvet.core.entity.pessoa.Marca;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produto")
@Getter
@Setter
public class Produto extends AbstractEntity {

	@NotNull(message = "Tipo do Cadastro não foi informado")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_produto_id")
	private TipoProduto tipo;

	@NotNull(message = "Unidade não foi informado")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unidade_id")
	private Unidade unidade;

	@EAN
	private String ean;

	@Size(min = 3, max = 70)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;

	@Size(min = 3, max = 120)
	@NotBlank(message = "O Campo Descrição  deve ser informado.")
	private String descricao;

	@NotNull(message = "Classificação não foi informado")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "produto_classificacao_id")
	private Classificacao classificacao;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "marca_id")
	private Marca marca;
	
	@OneToMany(fetch = FetchType.LAZY, 
				cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
	@JoinColumn(name = "produto_id", nullable=false)
	private Set<Preco> precos;

	@NotNull
	private Boolean ativo;

	public Produto() {
		super();
		this.precos = new HashSet<Preco>();
	}

}

package com.inovvet.core.entity.produto;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import com.inovvet.app.entity.AbstractEntity;
import com.inovvet.core.entity.loja.Loja;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "produto_preco")
@Getter
@Setter
public class Preco  extends AbstractEntity  {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loja_id")
	private Loja loja;	
	
	@DecimalMin(value = "0", inclusive = false)
	@Digits(integer = 10, fraction = 2)
	private BigDecimal custo;
	
	@Digits(integer = 10, fraction = 2)
	private BigDecimal despesaAdicional;

	@DecimalMax(value = "99.99", inclusive = false)
	@DecimalMin(value = "0", inclusive = false)
	@Digits(integer = 4, fraction = 2)
	private BigDecimal despesaFixa;

	@DecimalMax(value = "99.99", inclusive = false)
	@DecimalMin(value = "0", inclusive = false)
	@Digits(integer = 4, fraction = 2)
	private BigDecimal despesaVariavel;

	@DecimalMin(value = "0", inclusive = false)
	@Digits(integer = 8, fraction = 4)
	private BigDecimal margemLucro;

	@Digits(integer = 4, fraction = 2)
	private BigDecimal preco;

}

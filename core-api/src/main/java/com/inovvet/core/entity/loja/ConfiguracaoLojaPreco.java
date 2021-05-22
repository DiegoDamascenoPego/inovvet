package com.inovvet.core.entity.loja;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import com.inovvet.app.entity.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ConfiguracaoLojaPreco extends AbstractEntity {
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loja_id")
	private Loja loja;
	
	@DecimalMax(value = "99.99", inclusive = false)
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 4, fraction = 2)
	private BigDecimal padraoDespesaFixa;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 8, fraction = 2)
	private BigDecimal padraoMargemLucroServico;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 8, fraction = 2)
	private BigDecimal padraoMargemLucroProduto;

}

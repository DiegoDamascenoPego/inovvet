package com.inovvet.core.entity.produto.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrecoParametroDTO {
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 10, fraction = 2)
	private BigDecimal custo;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 10, fraction = 2)
	private BigDecimal margemLucro;

	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 10, fraction = 2)
	private BigDecimal despesaAdicional;	
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 10, fraction = 2)
	private BigDecimal despesaFixa;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 10, fraction = 2)
	private BigDecimal despesaVariavel;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 10, fraction = 2)
	private BigDecimal preco;
	
	public PrecoParametroDTO() {
		super();
		this.custo = BigDecimal.ZERO;
		this.margemLucro = BigDecimal.ZERO;
		this.despesaFixa = BigDecimal.ZERO;
		this.despesaVariavel = BigDecimal.ZERO;
		this.despesaAdicional = BigDecimal.ZERO;
		this.preco = BigDecimal.ZERO;
	}	
	
	

}

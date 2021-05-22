package com.inovvet.core.entity.produto.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.inovvet.core.entity.loja.dto.LojaCadastroDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrecoDTO {
	
	private Integer id;

	@NotNull
	private LojaCadastroDTO loja;

	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 10, fraction = 2)
	private BigDecimal custo;

	@DecimalMax(value = "99.99", inclusive = false)
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 10, fraction = 2)
	private BigDecimal despesaFixa;

	@DecimalMax(value = "99.99", inclusive = false)
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 4, fraction = 2)
	private BigDecimal despesaVariavel;

	@DecimalMin(value = "0.00", inclusive = false)
	@Digits(integer = 8, fraction = 4)
	private BigDecimal margemLucro;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 4, fraction = 2)
	private BigDecimal despesaAdicional;

	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 4, fraction = 2)
	private BigDecimal preco;
}

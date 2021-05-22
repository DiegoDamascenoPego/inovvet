package com.inovvet.core.entity.loja;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.inovvet.app.entity.AbstractEntity;
import com.inovvet.core.enumerator.EnumRegimeEmpresa;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ConfiguracaoLojaFiscal extends AbstractEntity{
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "loja_id")
	private Loja loja;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 4, fraction = 2)
	private BigDecimal aliquotaIssqn;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 4, fraction = 2)
	private BigDecimal aliquotaPis;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 4, fraction = 2)
	private BigDecimal aliquotaCofins;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 4, fraction = 2)
	private BigDecimal aliquotaSocial;
	
	@DecimalMin(value = "0.0", inclusive = false)
	@Digits(integer = 4, fraction = 2)
	private BigDecimal aliquotaIrpj;
	
	@NotNull(message = "regime não foi informado")
	@Enumerated(EnumType.ORDINAL)
	private EnumRegimeEmpresa regimeEmpresa;
	
	
	
}

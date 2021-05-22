package com.inovvet.core.entity.produto.dto;

import java.math.BigDecimal;

import com.inovvet.core.entity.produto.Despesas;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrecoCalculoDTO implements Despesas {
	
	private BigDecimal custo;
	
	private BigDecimal despesaFixa;
	
	private BigDecimal despesaVariavel;
	
	private BigDecimal despesaAdicional;
	
	private BigDecimal margemLucro;
	
	private BigDecimal preco;
	
	private BigDecimal valorLucro;
	
	private BigDecimal markup;
	
	public PrecoCalculoDTO() {
		this.custo = BigDecimal.ZERO;
		this.despesaAdicional = BigDecimal.ZERO;;
		this.despesaFixa = BigDecimal.ZERO;
		this.despesaVariavel = BigDecimal.ZERO;
		this.preco = BigDecimal.ZERO;
	}
	
	public BigDecimal getValorLucro() {
		
		BigDecimal totalCusto = this.getCusto().add(this.getDespesaAdicional());
		BigDecimal totalTaxa = this.getDespesaFixa().add(this.getDespesaVariavel());
		BigDecimal percentualVenda = this.getPreco().multiply(totalTaxa.divide(new BigDecimal("100")));
		
		return this.getPreco().subtract(percentualVenda).subtract(totalCusto);		 
	}

}

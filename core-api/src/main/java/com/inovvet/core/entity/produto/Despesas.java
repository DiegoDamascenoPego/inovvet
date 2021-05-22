package com.inovvet.core.entity.produto;

import java.math.BigDecimal;

public interface Despesas {

	public BigDecimal getDespesaFixa();

	public BigDecimal getDespesaVariavel();

	public BigDecimal getDespesaAdicional();

	public BigDecimal getMargemLucro();
}

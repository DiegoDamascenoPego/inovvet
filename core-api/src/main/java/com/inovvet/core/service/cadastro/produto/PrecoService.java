package com.inovvet.core.service.cadastro.produto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.inovvet.app.config.Contexto;
import com.inovvet.app.service.AbstractService;
import com.inovvet.core.entity.produto.Despesas;
import com.inovvet.core.entity.produto.dto.PrecoCalculoDTO;
import com.inovvet.core.entity.produto.dto.PrecoParametroDTO;

@Service
public class PrecoService extends AbstractService {
	

	private BigDecimal calcularMarkup(Despesas parametro) {
		
		
		BigDecimal totalDespesas = 
				parametro.getDespesaFixa()
				.add(parametro.getDespesaVariavel())
				.add(parametro.getMargemLucro());
		
		BigDecimal percentual = new BigDecimal("100");	
		
		BigDecimal taxa = percentual.divide(percentual.subtract(totalDespesas), 4, RoundingMode.UP);	
		
		return taxa;

	}

	private BigDecimal calcular(BigDecimal valor, Despesas parametro, BigDecimal markup) {
				
		return valor.add(parametro.getDespesaAdicional()).multiply(markup).setScale(2,
				RoundingMode.HALF_DOWN);

	}

	private Boolean validar(BigDecimal valor, Despesas parametro) {

		if (valor.equals(BigDecimal.ZERO)) {
			return false;
		}

		if (parametro.getMargemLucro().equals(BigDecimal.ZERO)) {
			return false;
		}

		return true;

	}

	private BigDecimal carregarDespesaVariavel() {

		return BigDecimal.ZERO.add(Contexto.getLoja().getFiscal().getAliquotaIssqn())
				.add(Contexto.getLoja().getFiscal().getAliquotaCofins())
				.add(Contexto.getLoja().getFiscal().getAliquotaPis())
				.add(Contexto.getLoja().getFiscal().getAliquotaSocial())
				.add(Contexto.getLoja().getFiscal().getAliquotaIrpj());
	}

	private BigDecimal carregarDespesaFixa() {

		return Contexto.getLoja().getPreco().getPadraoDespesaFixa();
	}

	private PrecoCalculoDTO calcular(PrecoCalculoDTO preco) {

		BigDecimal valor = BigDecimal.ZERO;
		
		BigDecimal markup = calcularMarkup(preco);

		if (validar(preco.getCusto(), preco)) {
			valor = calcular(preco.getCusto(), preco, markup);
		}

		preco.setPreco(valor);
		preco.setMarkup(markup);

		return preco;
	}

	private PrecoCalculoDTO ajustarMargem(PrecoCalculoDTO preco) {

		BigDecimal custoTotal = preco.getCusto().add(preco.getDespesaAdicional());

		BigDecimal totalDespesas = new BigDecimal("100")
				.subtract(preco.getDespesaFixa().add(preco.getDespesaVariavel()));

		BigDecimal parte = BigDecimal.ZERO;

		parte = custoTotal.multiply(new BigDecimal("100")).divide(preco.getPreco(), 4, RoundingMode.HALF_UP);

		BigDecimal margem = totalDespesas.subtract(parte);

		preco.setMargemLucro(margem);
		
		BigDecimal markup = calcularMarkup(preco);
		
		preco.setMarkup(markup);

		return preco;
	}

	public PrecoCalculoDTO calcular(PrecoParametroDTO parametro) {
		PrecoCalculoDTO preco = new PrecoCalculoDTO();

		preco.setCusto(parametro.getCusto());

		preco.setMargemLucro(parametro.getMargemLucro());
		preco.setDespesaAdicional(parametro.getDespesaAdicional());

		preco.setDespesaFixa(parametro.getDespesaFixa());
		preco.setDespesaVariavel(parametro.getDespesaVariavel());
		preco.setPreco(parametro.getPreco());

		if (preco.getPreco().equals(BigDecimal.ZERO)) {
			return calcular(preco);
		} else {
			return ajustarMargem(preco);
		}

	}

	public PrecoParametroDTO novo() {
		PrecoParametroDTO parametro = new PrecoParametroDTO();
		parametro.setDespesaFixa(carregarDespesaFixa());
		parametro.setDespesaVariavel(carregarDespesaVariavel());

		return parametro;

	}

}

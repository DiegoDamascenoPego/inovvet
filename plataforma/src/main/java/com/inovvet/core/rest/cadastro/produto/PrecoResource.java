package com.inovvet.core.rest.cadastro.produto;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inovvet.app.exception.ServiceException;
import com.inovvet.core.entity.produto.dto.PrecoParametroDTO;
import com.inovvet.core.service.cadastro.produto.PrecoService;

@RestController
@RequestMapping("/preco")
public class PrecoResource {
	
	@Autowired
	private PrecoService service;
	
	@GetMapping("/calcular")
	public ResponseEntity<?> calcular(
			@RequestParam BigDecimal custo,
			@RequestParam BigDecimal margemLucro, 
			@RequestParam BigDecimal despesaAdicional, 
			@RequestParam BigDecimal despesaFixa, 
			@RequestParam BigDecimal despesaVariavel, 
			@RequestParam BigDecimal preco) throws ServiceException {
		
		PrecoParametroDTO parametro = new PrecoParametroDTO();
		parametro.setCusto(custo);
		parametro.setMargemLucro(margemLucro);
		parametro.setDespesaAdicional(despesaAdicional);
		parametro.setDespesaFixa(despesaFixa);
		parametro.setDespesaVariavel(despesaVariavel);
		parametro.setPreco(preco);
		return ResponseEntity.ok(service.calcular(parametro));
	}
	
	@GetMapping("/novo")
	public ResponseEntity<?> carregarParametro(){
		return ResponseEntity.ok(service.novo());
	}
}

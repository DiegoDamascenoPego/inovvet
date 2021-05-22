package com.inovvet.core.service.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovvet.app.service.AbstractService;
import com.inovvet.core.entity.cliente.dto.EnderecoDTO;
import com.inovvet.core.entity.loja.Loja;
import com.inovvet.core.entity.loja.dto.LojaContatoDTO;
import com.inovvet.core.entity.produto.dto.ProdutoConsultaDTO;
import com.inovvet.core.entity.vitrine.dto.VitrineDTO;
import com.inovvet.core.service.cadastro.loja.LojaService;
import com.inovvet.core.service.cadastro.produto.ProdutoService;

@Service
public class SiteService extends AbstractService {

	@Autowired
	private LojaService lojaService;

	@Autowired
	private ProdutoService produtoService;
	
	private LojaContatoDTO carregar(Loja loja) {
		LojaContatoDTO dto = new LojaContatoDTO();
		dto.setDocumento(loja.getDocumento());
		dto.setNomeFantasia(loja.getNomeFantasia());		
		dto.setEndereco(mapper.map(loja.getEndereco(), EnderecoDTO.class));
		
		return dto;
	}

	public VitrineDTO carregarVitrine(String tokenLoja, Integer id) {

		Loja loja = lojaService.carregarLoja(tokenLoja);

		ProdutoConsultaDTO produto = produtoService.consultar(loja, id);
		
		VitrineDTO vitrine = new VitrineDTO();
		vitrine.setLoja(carregar(loja));
		vitrine.setProduto(produto);
		return vitrine;
	}
	
	public LojaContatoDTO carregarLoja(String tokenLoja) {

		Loja loja = lojaService.carregarLoja(tokenLoja);		

		return carregar(loja);
	}

}

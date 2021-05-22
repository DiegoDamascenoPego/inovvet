package com.inovvet.core.rest.site;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inovvet.core.service.site.SiteService;

@RestController
@RequestMapping("/site")
public class SiteResource {

	@Autowired
	private SiteService siteService;

	@GetMapping("/vitrine")
	public ResponseEntity<?> listarTipoProduto(@RequestParam(name = "seller-active") String seller,
			@RequestParam Integer sku) throws ServiceException {
		return ResponseEntity.ok(siteService.carregarVitrine(seller, sku));
	}

	@GetMapping("/loja")
	public ResponseEntity<?> CarregarLoja(@RequestParam(name = "seller-active") String seller) throws ServiceException {
		return ResponseEntity.ok(siteService.carregarLoja(seller));
	}
}

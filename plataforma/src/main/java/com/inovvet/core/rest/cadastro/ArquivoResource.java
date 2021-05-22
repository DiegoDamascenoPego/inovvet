package com.inovvet.core.rest.cadastro;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.inovvet.core.enumerator.EnumArquivo;
import com.inovvet.core.enumerator.EnumModulo;
import com.inovvet.core.enumerator.EnumReferenciaArquivo;
import com.inovvet.core.service.cadastro.ArquivoService;
import com.inovvet.core.service.cadastro.ArquivoService.DataFile;

@RestController
@RequestMapping("/arquivo")
public class ArquivoResource {
	
	@Autowired
	private	ArquivoService service;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?>upload(@RequestParam("arquivo") MultipartFile  arquivo, @RequestParam EnumModulo modulo, @RequestParam EnumArquivo tipoArquivo, @RequestParam EnumReferenciaArquivo referencia, @RequestParam Integer referenciaId){
		
		return ResponseEntity.ok(service.novo(arquivo, modulo, tipoArquivo, referencia, referenciaId));		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> carregar(@PathVariable Integer id) throws ServiceException {
		return ResponseEntity.ok(service.carregar(id));
	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<ByteArrayResource> download(@PathVariable Integer id) throws ServiceException {
		DataFile dataFile = service.download(id);
		return ResponseEntity
                .ok()
                .contentLength(dataFile.getData().length)
                .header("Content-type", dataFile.getMediaType().toString())
                .header("Content-disposition", "attachment; filename=\"" + dataFile.getName() + "\"")
                .body(dataFile.getResource());
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) throws ServiceException {
		service.remover(id);
	}

}

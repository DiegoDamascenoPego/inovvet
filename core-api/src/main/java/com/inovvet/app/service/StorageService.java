package com.inovvet.app.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import com.inovvet.core.entity.Arquivo.Arquivo;

public interface StorageService {
	
	public void upload(MultipartFile multipartFile, Arquivo arquivo);
	
	public String getUrl(Arquivo arquivo);
	
	public void delete(Arquivo arquivo);
	
	public byte[] download(Arquivo arquivo);
	
	

}

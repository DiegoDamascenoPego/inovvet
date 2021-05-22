package com.inovvet.app.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.inovvet.app.config.Contexto;
import com.inovvet.app.config.Projeto;
import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.core.entity.Arquivo.Arquivo;

@Service
public class AmazonService implements StorageService {

	@Autowired
	private AmazonS3 amazonS3Client;

	@Autowired
	private Projeto applicationProperties;

	private String getName(Arquivo arquivo) {
		return Contexto.getConta().getToken() + "/" + arquivo.getTipo().getValue().toLowerCase() + "/"
				+ arquivo.getModulo().getValue().toLowerCase() + "/" + arquivo.getChave();
	}

	private String getUrl(String fileName) {
		return amazonS3Client.getUrl(applicationProperties.getBucket(), fileName).toExternalForm();
	}

	private void upload(String fileName, File file) {
		amazonS3Client.putObject(new PutObjectRequest(applicationProperties.getBucket(), fileName, file)
				.withCannedAcl(CannedAccessControlList.PublicRead));

	}

	private S3Object download(String fileName) {
		S3Object object = amazonS3Client.getObject(applicationProperties.getBucket(), fileName);
		return object;

	}

	private byte [] convertObjectToArray(S3Object obj) {
		byte[] content = null;
		S3ObjectInputStream stream = obj.getObjectContent();

		try {
			content = IOUtils.toByteArray(stream);
			obj.close();
		} catch (final IOException ex) {
			new ServiceException(EnumError.FALHA_INESPERADA, "Falha ao realizar conversão do arquivo para download");
		}

		return content;
	}

	private void delete(String fileName) {
		amazonS3Client.deleteObject(applicationProperties.getBucket(), fileName);
	}

	private File convertMultiPartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
		} catch (IOException e) {
//            log.error("Error converting multipartFile to file", e);
		}
		return convertedFile;
	}

	public void upload(MultipartFile multipartFile, Arquivo arquivo) {
		File file = convertMultiPartFileToFile(multipartFile);
		upload(getName(arquivo), file);

	}

	public void delete(Arquivo arquivo) {
		delete(getName(arquivo));
	}

	public String getUrl(Arquivo arquivo) {
		return getUrl(getName(arquivo));
	}

	public byte[] download(Arquivo arquivo) {
		return convertObjectToArray(download(getName(arquivo)));
	}

}

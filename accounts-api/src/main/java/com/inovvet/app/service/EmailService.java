package com.inovvet.app.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
		
	void enviar(String to, String text);
		
	void enviar(String to, String subject, String text);

	void enviar(String to, String subject, SimpleMailMessage template, String... templateArgs);

	void enviar(String to, String subject, String text, String pathToAttachment);

}

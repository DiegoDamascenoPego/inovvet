package com.inovvet.app.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.inovvet.app.entity.enumerator.TipoObjeto;

@Service
public class EmailServiceImp implements EmailService {

	@Autowired
	private LogService logService;

	@Autowired
	public JavaMailSender emailSender;

	@Override
	public void enviar(String to, String text) {
		enviar(to, "Inovvet", text);

	}

	@Async
	public void enviar(String to, String subject, String text) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(to);
			message.setSubject(subject);
			message.setFrom("Inovvet");
			message.setText(text);

			emailSender.send(message);
		} catch (MailException exception) {
			exception.printStackTrace();
			logService.logErro(TipoObjeto.SISTEMA, exception.getMessage());
		}
	}

	@Override
	public void enviar(String to, String subject, SimpleMailMessage template, String... templateArgs) {
		String text = String.format(template.getText(), templateArgs);
		enviar(to, subject, text);
	}

	@Override
	public void enviar(String to, String subject, String text, String pathToAttachment) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			// pass 'true' to the constructor to create a multipart message
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(text);

			FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
			helper.addAttachment("Invoice", file);

			emailSender.send(message);
		} catch (MessagingException e) {
			logService.logErro(TipoObjeto.SISTEMA, e.getMessage());
		}
	}

}

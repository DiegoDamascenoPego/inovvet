package com.inovvet.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovvet.app.config.Projeto;

@Service
public class MensagemService {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private Projeto projeto;

	
	public void ativacao(String email, String username, String token) {
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("Olá, ");
		mensagem.append(username);
		mensagem.append(" seja bem vindo à plataforma Inovvet");
		mensagem.append("\n");
//		mensagem.append("Clique na url abaixo para ter acesso à nossa plataforma.");
//		mensagem.append(projeto.getPlataformaUi());
		mensagem.append("\n");
		mensagem.append("Para ativar a conta clique no link abaixo.");
		mensagem.append("\n");
		mensagem.append(projeto.getPlataformaUi()+"/site/conta/ativar/");
		mensagem.append(token);

		emailService.enviar(email, mensagem.toString());
	}
	
	public void gerarSenha(String email, String username, String token) {
		StringBuilder mensagem = new StringBuilder();
		mensagem.append("Olá, ");
		mensagem.append(username);
		mensagem.append(" seja bem vindo à plataforma Inovvet");
		mensagem.append("\n");
		mensagem.append("Clique na url abaixo para definir a senha de acesso a plataforma.");
		mensagem.append(projeto.getPlataformaUi());
		mensagem.append("\n");
		mensagem.append("Para ativar seu cadastro clique no link abaixo.");
		mensagem.append("\n");
		mensagem.append(projeto.getPlataformaUi()+"/site/conta/usuario/ativar/");
		mensagem.append(token);

		emailService.enviar(email, mensagem.toString());
	}
}

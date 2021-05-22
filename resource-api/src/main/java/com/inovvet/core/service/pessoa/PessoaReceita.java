package com.inovvet.core.service.pessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaReceita {

	private String nome;

	private String nomeFantasia;

	private String cnpj;

	private String email;

	private String complemento;

	private String bairro;

	private String numero;

	private String cep;

	private String municipio;

	private String logradouro;

	public String getNomeFantasia() {
		return this.nomeFantasia != null && !this.nomeFantasia.isEmpty()  ? this.nomeFantasia : this.nome;
	}

}

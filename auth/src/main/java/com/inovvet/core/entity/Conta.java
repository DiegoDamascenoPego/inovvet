package com.inovvet.core.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.inovvet.app.entity.SimpleEntity;
import com.inovvet.app.util.FunctionUtil;

import lombok.Getter;
import lombok.Setter;

@Table(name = "conta")
@Entity
@Getter
@Setter
@SequenceGenerator(name="seq", initialValue=1, allocationSize=100)
public class Conta extends SimpleEntity {

	@NotBlank(message = "campo CPF deve ser informado")
	@CPF(message = "O campo CPF formato inválido")
	private String cpf;

	@NotBlank(message = "O campo Nome deve ser informado")
	private String nome;

	@NotBlank(message = "O campo Email deve ser informado")
	@Email
	private String email;
	
	@NotBlank(message = "O campo Telefone deve ser informado")
	private String telefone;
	
	@NotBlank
	private String token;
	
	@NotBlank
	private LocalDate dataCredito;

	@NotNull
	private Boolean ativo;

	public Conta() {
		this.ativo = true;
	}
	
	public void setCpf(String value) {
		this.cpf = FunctionUtil.removerMascaraTexto(value);
	}
	
	public void setTelefone(String value) {
		this.telefone = FunctionUtil.removerMascaraTexto(value);
	}

}

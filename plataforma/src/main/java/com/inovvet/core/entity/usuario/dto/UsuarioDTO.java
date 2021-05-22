package com.inovvet.core.entity.usuario.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.inovvet.core.entity.loja.dto.LojaLoginDTO;
import com.inovvet.core.enumerator.EnumPerfilUsuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome;

	@CPF(message = "O Campo CPF deve ser informado.")
	private String cpf;

	@NotBlank(message = "O Campo e-mail deve ser informado.")
	private String email;

	@NotNull(message = "O campo deve ser informado")
	private String token;

	private EnumPerfilUsuario perfil;

	List<LojaLoginDTO> lojas;

	List<String> authorities;

	@NotNull
	private Boolean ativo;

	public UsuarioDTO() {
		authorities = new ArrayList<String>();

		lojas = new ArrayList<LojaLoginDTO>();
	}
}

package com.inovvet.core.entity.cliente.dto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inovvet.core.entity.base.dto.AbstractDTO;
import com.inovvet.core.enumerator.EnumSexo;
import com.inovvet.core.enumerator.EnumTipoPessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO extends AbstractDTO {
	
	
	private EnumTipoPessoa tipo;
	
	@NotBlank
	@Size(max = 150)
	private String nome;

	@NotBlank
	private String cpf;
	
	@Size(max = 150)
	private String email;	
	
	@Size(max = 255)
	private String observacao;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	private EnumSexo sexo;
	
	
	EnderecoDTO endereco;
	
	
	private Boolean ativo;

	
	Set<TelefoneDTO> telefones;
	
	public ClienteDTO() {		
		telefones = new HashSet<TelefoneDTO>();
		endereco = new EnderecoDTO();
	}
}

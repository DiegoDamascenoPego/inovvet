package com.inovvet.core.entity.servico.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServicoCadastroDTO extends ServicoDTO {

	private Integer id;

	@Exclude
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dataCadastro;

	@Exclude
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dataAtualizacao;
}

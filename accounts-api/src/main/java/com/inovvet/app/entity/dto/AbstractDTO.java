package com.inovvet.app.entity.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AbstractDTO {

	protected Integer id;

	@Exclude
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dataCadastro;

	@Exclude
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDateTime dataAtualizacao;

}

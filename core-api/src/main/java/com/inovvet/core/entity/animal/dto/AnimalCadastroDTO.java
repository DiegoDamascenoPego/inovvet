package com.inovvet.core.entity.animal.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class AnimalCadastroDTO extends AnimalDTO {

	private Integer id;

	@Exclude
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dataCadastro;

	@Exclude
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dataAtualizacao;

}

package com.inovvet.core.entity.base.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown=true)
public class AbstractDTO extends EntityDTO {	

	@Exclude
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dataCadastro;

	@Exclude
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime dataAtualizacao;

}

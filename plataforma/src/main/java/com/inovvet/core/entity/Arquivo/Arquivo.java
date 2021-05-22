package com.inovvet.core.entity.Arquivo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.inovvet.app.entity.AbstractEntity;
import com.inovvet.core.enumerator.EnumArquivo;
import com.inovvet.core.enumerator.EnumModulo;
import com.inovvet.core.enumerator.EnumReferenciaArquivo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Arquivo extends AbstractEntity {
	
	@NotNull(message = "Origem não  foi informado")
	@Enumerated(EnumType.ORDINAL)
	private EnumReferenciaArquivo referencia;
	
	@NotNull(message = "Origem não  foi informado")
	protected Integer referenciaId;
	
	@Size(max = 100)
	@NotBlank(message = "O Campo Nome deve ser informado.")
	private String nome; 
	
	@NotNull(message = "Tipo do arquivo não  foi informado")
	@Enumerated(EnumType.ORDINAL)
	private EnumArquivo tipo;
	
	@NotNull(message = "Módulo não  foi informado")
	@Enumerated(EnumType.ORDINAL)
	private EnumModulo modulo;	
		

	@NotNull(message = "O Campo tamanho deve ser informado.")
	@DecimalMin(value = "0.00000000", inclusive = false)
	@Digits(integer = 20, fraction = 8)
	private BigDecimal tamanho;
	
	@Size(max = 100)
	@NotBlank(message = "O Campo Chave deve ser informado.")
	private String chave; 	
	
	@Size(max = 255)
	@NotBlank(message = "O Campo url deve ser informado.")
	private String url;
	
}

package com.inovvet.core.entity.Arquivo.dto;

import java.math.BigDecimal;

import com.inovvet.core.enumerator.EnumArquivo;
import com.inovvet.core.enumerator.EnumModulo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArquivoCadastroDTO {
	
	private Integer id;
		
	private String nome; 

	private EnumArquivo tipo;
	
	private EnumModulo modulo;	
		
	private BigDecimal tamanho;

	private String chave; 
	
	private String url;

}

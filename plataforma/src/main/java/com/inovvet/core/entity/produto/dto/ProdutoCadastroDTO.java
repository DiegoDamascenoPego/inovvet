package com.inovvet.core.entity.produto.dto;

import java.util.List;

import com.inovvet.core.entity.Arquivo.dto.ArquivoCadastroDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoCadastroDTO extends ProdutoDTO {

	private Integer id;
	
	private List<ArquivoCadastroDTO> arquivos;

}

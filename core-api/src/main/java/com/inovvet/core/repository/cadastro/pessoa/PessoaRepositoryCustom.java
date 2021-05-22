package com.inovvet.core.repository.cadastro.pessoa;

import java.util.List;

import com.inovvet.core.entity.pessoa.dto.PessoaPesquisaDTO;
import com.inovvet.core.enumerator.EnumFinalidadePessoa;

public interface PessoaRepositoryCustom {

	List<PessoaPesquisaDTO> pesquisaCompleta(String valor, boolean ativo,
			EnumFinalidadePessoa tipo);
	
	List<PessoaPesquisaDTO> findByDocumentoContainsAndAtivo(String documento,  boolean ativo, EnumFinalidadePessoa tipo);

}

package com.inovvet.core.repository.cadastro.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inovvet.core.entity.classificacao.Classificacao;
import com.inovvet.core.entity.classificacao.dto.ClassificacaoFiltroDTO;

public interface ClassificacaoRepositoryCustom {
	
	public Page<Classificacao> filtrarClassificacao(ClassificacaoFiltroDTO dto,	Pageable pageable);

}

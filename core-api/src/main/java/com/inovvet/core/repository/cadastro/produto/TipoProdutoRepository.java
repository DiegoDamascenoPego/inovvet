package com.inovvet.core.repository.cadastro.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.produto.TipoProduto;

@Repository
public interface TipoProdutoRepository extends JpaRepository<TipoProduto, Integer> {
	
	List<TipoProduto> findByAtivo(Boolean ativo);
	
	

}

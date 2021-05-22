package com.inovvet.core.repository.cadastro.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.produto.Produto;
import java.lang.String;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

	List<Produto> findByNomeContainingIgnoreCase(String nome);
}

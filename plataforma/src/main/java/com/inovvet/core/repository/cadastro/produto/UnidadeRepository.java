package com.inovvet.core.repository.cadastro.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.produto.Unidade;

@Repository
public interface UnidadeRepository extends JpaRepository<Unidade, Integer> {
	
	List<Unidade> findByAtivo(Boolean ativo);

}

package com.inovvet.core.repository.cadastro.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.produto.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Integer> {

	@Query("SELECT s FROM Setor as s where s.nome LIKE %:nome% and s.ativo = 1")
	public List<Setor> findByAtivo(@Param("nome") String title);

}

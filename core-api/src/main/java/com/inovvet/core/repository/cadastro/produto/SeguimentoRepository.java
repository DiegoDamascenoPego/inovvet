package com.inovvet.core.repository.cadastro.produto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.produto.Seguimento;

@Repository
public interface SeguimentoRepository extends JpaRepository<Seguimento, Integer> {

	@Query("SELECT s FROM Seguimento as s where s.ativo = 1")
	public List<Seguimento> listarSeguimentos();
}

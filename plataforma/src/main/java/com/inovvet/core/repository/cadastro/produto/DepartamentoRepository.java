package com.inovvet.core.repository.cadastro.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.departamento.Departamento;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

	public Optional<Departamento> findByNome(String nome);

	@Query("SELECT d FROM Departamento as d "
			+ "where d.ativo = 1 ORDER BY d.nome")
	public List<Departamento> listarDepartamentoAtivo();

}

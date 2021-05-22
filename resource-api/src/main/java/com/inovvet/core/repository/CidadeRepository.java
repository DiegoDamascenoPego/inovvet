package com.inovvet.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, String> {
	
	List<Cidade> findByUf(String uf);

	Optional<Cidade> findByCodigo(String codigo);
}

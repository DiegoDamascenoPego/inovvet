package com.inovvet.core.repository.cadastro.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.grupo.Grupo;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

	public Optional<Grupo> findByNome(String nome);
	
	List<Grupo> findByAtivo(Boolean ativo);

	
	
}

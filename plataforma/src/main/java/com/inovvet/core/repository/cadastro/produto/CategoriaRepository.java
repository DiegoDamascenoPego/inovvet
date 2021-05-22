package com.inovvet.core.repository.cadastro.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.categoria.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	
	public Optional<Categoria> findByNome(String nome);
	
	List<Categoria> findByAtivo(Boolean ativo);

	

}

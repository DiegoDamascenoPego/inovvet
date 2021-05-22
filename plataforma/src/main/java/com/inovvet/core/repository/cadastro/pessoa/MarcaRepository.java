package com.inovvet.core.repository.cadastro.pessoa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.pessoa.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer>{
	
	List<Marca> findByNomeContainingAndAtivo(String nome, boolean ativo);
	
}

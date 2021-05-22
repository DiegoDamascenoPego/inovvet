package com.inovvet.core.repository.cadastro.loja;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.loja.Loja;

@Repository
public interface LojaRepository extends JpaRepository<Loja, Integer> {
	Optional<Loja> findByToken(String token);
	
	Optional<Loja> findByDocumento(String documento);
	
}

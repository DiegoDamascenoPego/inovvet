package com.inovvet.core.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.inovvet.core.entity.Conta;

public interface ContaRepository extends CrudRepository<Conta, Integer> {

	Optional<Conta> findByCpfAndAtivo(String cpf, Boolean ativo);
	
	Optional<Conta> findByToken(String token);
	
}

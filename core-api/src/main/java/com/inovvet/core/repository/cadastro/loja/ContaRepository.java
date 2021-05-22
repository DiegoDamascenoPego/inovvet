package com.inovvet.core.repository.cadastro.loja;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.conta.Conta;

@Repository
public interface ContaRepository extends CrudRepository<Conta, Integer> {

	Optional<Conta> findByCpfAndAtivo(String cpf, Boolean ativo);
	
	Optional<Conta> findByToken(String token);
	
}

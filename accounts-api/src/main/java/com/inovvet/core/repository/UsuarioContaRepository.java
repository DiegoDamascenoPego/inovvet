package com.inovvet.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.Conta;
import com.inovvet.core.entity.Usuario;
import com.inovvet.core.entity.UsuarioConta;

@Repository
public interface UsuarioContaRepository extends JpaRepository<UsuarioConta, Integer> {
	
	Optional<UsuarioConta> findByUsuarioAndConta(Usuario usuario, Conta conta);
	
	Optional<UsuarioConta> findByToken(String token);

}

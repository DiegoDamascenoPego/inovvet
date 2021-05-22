package com.inovvet.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.Usuario;
import com.inovvet.core.entity.UsuarioConta;

import java.util.List;

@Repository
public interface UsuarioContaRepository extends JpaRepository<UsuarioConta, Integer> {

	List<UsuarioConta> findByUsuario(Usuario usuario);
	
}

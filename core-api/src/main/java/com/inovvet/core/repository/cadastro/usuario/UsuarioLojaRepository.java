package com.inovvet.core.repository.cadastro.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inovvet.core.entity.loja.Loja;
import com.inovvet.core.entity.usuario.Usuario;
import com.inovvet.core.entity.usuario.UsuarioLoja;

import java.util.List;
import java.util.Optional;

public interface UsuarioLojaRepository extends JpaRepository<UsuarioLoja, Integer> {

	List<UsuarioLoja> findByUsuario(Usuario usuario);

	Optional<UsuarioLoja> findByUsuarioAndLoja(Usuario usuario, Loja loja);
	
	List<UsuarioLoja> findByLoja(Loja loja);

}

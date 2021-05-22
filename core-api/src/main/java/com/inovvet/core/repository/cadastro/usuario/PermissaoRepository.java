package com.inovvet.core.repository.cadastro.usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.permissao.Permissao;
import com.inovvet.core.enumerator.EnumPerfilUsuario;

@Repository
public interface PermissaoRepository  extends JpaRepository<Permissao, Integer> {

	List<Permissao> findByPerfilAndAtivo(EnumPerfilUsuario perfil, boolean ativo);
	
	
}

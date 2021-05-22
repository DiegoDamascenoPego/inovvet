package com.inovvet.core.service.cadastro.usuario;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.inovvet.app.service.AbstractService;
import com.inovvet.core.enumerator.EnumPerfilUsuario;
import com.inovvet.core.repository.cadastro.usuario.PermissaoRepository;

@Service
public class PermissaoService extends AbstractService {

	@Autowired
	private PermissaoRepository repository;

	public List<GrantedAuthority> carregarAuthorities(EnumPerfilUsuario perfil) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		repository.findByPerfilAndAtivo(perfil, true).forEach(permissao -> {
			authorities.add(permissao);
		});

		return authorities;
	}

}

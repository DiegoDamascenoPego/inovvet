package com.inovvet.app.config.security;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import com.inovvet.app.config.Contexto;
import com.inovvet.app.entity.User;
import com.inovvet.core.entity.usuario.Usuario;
import com.inovvet.core.repository.cadastro.usuario.UsuarioRepository;

public class PrincipalExtractorConfig implements PrincipalExtractor {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public Object extractPrincipal(Map<String, Object> map) {

		if (!(boolean) map.get("clientOnly")) {
			@SuppressWarnings("unchecked")
			String token = (String) ((Map<String, Object>) map.get("principal")).get("token");
			Usuario usuario = repository.findByToken(token).orElse(null);
			User user = new User();

			if (usuario != null) {
				user.setPerfil(usuario.getPerfil().toString());
				Contexto.setUsuario(usuario);

			}
			return user;
		}

		return null;

	}

}

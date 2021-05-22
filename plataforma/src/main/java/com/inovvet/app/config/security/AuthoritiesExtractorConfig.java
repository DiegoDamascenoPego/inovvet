package com.inovvet.app.config.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.core.entity.usuario.Usuario;
import com.inovvet.core.repository.cadastro.usuario.UsuarioRepository;
import com.inovvet.core.service.cadastro.usuario.PermissaoService;

public class AuthoritiesExtractorConfig implements AuthoritiesExtractor {

	@Autowired
	private PermissaoService permissaoService;

	@Autowired
	private UsuarioRepository usuarioRepository;


	@Override
	public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
		return AuthorityUtils.commaSeparatedStringToAuthorityList(asAuthorities(map));
	}

	@SuppressWarnings("unchecked")
	private String asAuthorities(Map<String, Object> map) {

		if ((Boolean) (map.get("clientOnly"))) {
			return "";
		}
		String token = (String) ((Map<String, Object>) map.get("principal")).get("token");

		Usuario usuario = usuarioRepository.findByToken(token)
				.orElseThrow(() -> new ServiceException(EnumError.REGISTRO_INVALIDO));

		List<String> authorities = new ArrayList<>();

		permissaoService.carregarAuthorities(usuario.getPerfil()).forEach(permissao -> {
			authorities.add(permissao.getAuthority());
		});
		;

		return String.join(",", authorities);
	}

}

package com.inovvet.core.service.cadastro.usuario;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.inovvet.app.entity.enumerator.TipoObjeto;
import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.service.AbstractService;
import com.inovvet.app.util.FunctionUtil;
import com.inovvet.core.entity.loja.dto.LojaLoginDTO;
import com.inovvet.core.entity.usuario.Usuario;
import com.inovvet.core.entity.usuario.UsuarioLoja;
import com.inovvet.core.entity.usuario.dto.UsuarioDTO;
import com.inovvet.core.enumerator.EnumPerfilUsuario;
import com.inovvet.core.repository.cadastro.usuario.UsuarioRepository;

@Service
public class UsuarioService extends AbstractService {

	@Autowired
	private UsuarioRepository repository;
	

	@Autowired
	private PermissaoService permissaoService;

	@Autowired
	private UsuarioLojaService usuarioLojaService;

	private Usuario salvar(Usuario usuario) {
		try {
			return repository.save(usuario);
		} catch (Exception e) {
			log.logErro(TipoObjeto.USUARIO, e.getMessage());
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO);
		}
	}

	private void validar(UsuarioDTO dto) {
		if (!FunctionUtil.validarCPF(dto.getCpf())) {
			throw new ServiceException(EnumError.PARAMETROS_INVALIDOS);
		}

		if (!FunctionUtil.validarEmail(dto.getEmail())) {
			throw new ServiceException(EnumError.PARAMETROS_INVALIDOS);
		}
	}

	private void adicionarLojaUsuario(Usuario usuario) {
		usuarioLojaService.salvar(usuario);
	}

	@Transactional
	public void salvar(UsuarioDTO dto) {

		validar(dto);

		Usuario usuario = repository.findByToken(dto.getToken()).orElse(new Usuario());

		mapper.map(dto, usuario);
		usuario.setPerfil(EnumPerfilUsuario.ADMINISTRADOR);

		usuario = salvar(usuario);

		adicionarLojaUsuario(usuario);
	}
	
	private List<UsuarioLoja> carregarLojaUsuario(Usuario usuario) {
		return usuarioLojaService.carregar(usuario);
	}

	public UsuarioDTO carregar(String token) {
		Usuario usuario = repository.findByToken(token)
				.orElseThrow(() -> new ServiceException(EnumError.REGISTRO_INVALIDO));

		List<GrantedAuthority> authorities = permissaoService.carregarAuthorities(usuario.getPerfil());

		UsuarioDTO usuarioDTO = mapper.map(usuario, UsuarioDTO.class);

		authorities.forEach(authority -> {
			usuarioDTO.getAuthorities().add(authority.getAuthority());
		});
		
		carregarLojaUsuario(usuario).forEach(usuarioLoja -> {
			
			usuarioDTO.getLojas().add(mapper.map(usuarioLoja.getLoja(), LojaLoginDTO.class));
		});

		return usuarioDTO;
	}
	

}

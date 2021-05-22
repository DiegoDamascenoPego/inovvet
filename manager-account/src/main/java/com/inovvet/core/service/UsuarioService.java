package com.inovvet.core.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.inovvet.app.entity.enumerator.TipoObjeto;
import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.service.AbstractService;
import com.inovvet.app.util.FunctionUtil;
import com.inovvet.core.entity.Conta;
import com.inovvet.core.entity.Usuario;
import com.inovvet.core.entity.UsuarioConta;
import com.inovvet.core.entity.dto.UsuarioDTO;
import com.inovvet.core.entity.dto.UsuarioRegistrarDTO;
import com.inovvet.core.entity.dto.UsuarioSimplesDTO;
import com.inovvet.core.repository.UsuarioContaRepository;
import com.inovvet.core.repository.UsuarioRepository;

@Service
public class UsuarioService extends AbstractService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioContaRepository usuarioContaRepository;

	private Usuario salvar(Usuario usuario) {
		try {
			return repository.save(usuario);
		} catch (Exception e) {
			log.logErro(TipoObjeto.USUARIO, e.getMessage());
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO);
		}
	}

	private void salvarUsuarioConta(UsuarioConta usuarioConta) {
		try {
			usuarioContaRepository.save(usuarioConta);
		} catch (Exception e) {
			log.logErro(TipoObjeto.USUARIO, e.getMessage());
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO);
		}
	}

	@Transactional
	public UsuarioDTO salvar(Conta conta, UsuarioDTO dto) {
		Usuario usuario = repository.findByEmail(dto.getEmail()).orElse(new Usuario());
		mapper.map(dto, usuario);

		usuario = salvar(usuario);

		UsuarioConta usuarioConta = usuarioContaRepository.findByUsuarioAndConta(usuario, conta)
				.orElse(new UsuarioConta(conta, usuario));

		salvarUsuarioConta(usuarioConta);

		UsuarioRegistrarDTO usuarioRegistrar = new UsuarioRegistrarDTO();
		usuarioRegistrar.setCpf(dto.getCpf());
		usuarioRegistrar.setEmail(dto.getEmail());
		usuarioRegistrar.setNome(dto.getNome());
		usuarioRegistrar.setToken(usuarioConta.getToken());
		usuarioRegistrar.setAtivo(true);

		restTemplateService.post("usuario", FunctionUtil.toJson(usuarioRegistrar), conta.getToken());

		mensagemService.gerarSenha(usuarioRegistrar.getEmail(), usuarioRegistrar.getNome(),
				usuarioRegistrar.getToken());

		return mapper.map(usuario, UsuarioDTO.class);

	}

	@Transactional
	public void registrarSenha(String token, String senha) {
		Usuario usuario = carregarUsuarioToken(token).getUsuario();
		usuario.setPassword(passwordEncoder.encode(senha));
		usuario = salvar(usuario);

	}

	public UsuarioSimplesDTO carregar(String token) {
		
	   UsuarioConta	usuarioConta = carregarUsuarioToken(token);
	    
	   UsuarioSimplesDTO dto = new UsuarioSimplesDTO();
		
		dto.setNome(usuarioConta.getUsuario().getNome());
		dto.setEmail(usuarioConta.getUsuario().getEmail());
		dto.setToken(usuarioConta.getToken());
		
		return dto;
	}

	private UsuarioConta carregarUsuarioToken(String token) {
		return usuarioContaRepository.findByToken(token)
				.orElseThrow(() -> new ServiceException(EnumError.PARAMETROS_INVALIDOS, "Usuário Não Encontrado"));
	}

}

package com.inovvet.app.config.seguranca;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.inovvet.core.entity.User;
import com.inovvet.core.entity.Usuario;
import com.inovvet.core.entity.UsuarioConta;
import com.inovvet.core.repository.UsuarioContaRepository;
import com.inovvet.core.repository.UsuarioRepository;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private UsuarioContaRepository usuarioContaRepository;

	private Optional<UsuarioConta> carregarConta(Usuario usuario) {
		List<UsuarioConta> contas =  usuarioContaRepository.findByUsuario(usuario);
		return contas.stream().filter(
				item -> item.getConta().getDataCredito().isAfter(LocalDate.now()) && item.getConta().getAtivo())
				.findFirst();

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(""));

		UsuarioConta usuarioConta = carregarConta(usuario)
				.orElseThrow(() -> new UsernameNotFoundException("Conta do usuário não encontrada"));

		return new User(usuario.getNome(), usuario.getUsername(), usuario.getPassword(), usuario.isAccountNonExpired(),
				usuario.isAccountNonLocked(), usuario.isCredentialsNonExpired(), usuario.isEnabled(), null,
				usuarioConta.getConta().getToken(), usuarioConta.getToken());

	}

}

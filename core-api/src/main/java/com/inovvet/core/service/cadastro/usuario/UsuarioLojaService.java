package com.inovvet.core.service.cadastro.usuario;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.service.AbstractService;
import com.inovvet.core.entity.loja.Loja;
import com.inovvet.core.entity.usuario.Usuario;
import com.inovvet.core.entity.usuario.UsuarioLoja;
import com.inovvet.core.repository.cadastro.usuario.UsuarioLojaRepository;
import com.inovvet.core.service.cadastro.loja.LojaService;

@Service
public class UsuarioLojaService extends AbstractService {

	@Autowired
	private UsuarioLojaRepository repository;

	@Autowired
	private LojaService lojaService;

	private void salvar(UsuarioLoja usuarioLoja) {
		try {
			repository.save(usuarioLoja);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO, e.getMessage());
		}

	}

	public void salvar(Usuario usuario, Loja loja) {
		UsuarioLoja usuarioLoja = repository.findByUsuarioAndLoja(usuario, loja).orElse(new UsuarioLoja());
		usuarioLoja.setLoja(loja);
		usuarioLoja.setUsuario(usuario);
		usuarioLoja.setAtivo(true);

		salvar(usuarioLoja);

	}

	public void salvar(Usuario usuario) {
		lojaService.carregarLojas().forEach(loja -> {
			salvar(usuario, loja);
		});
	}
	
	
	public List<UsuarioLoja> carregar(Usuario usuario){
		return repository.findByUsuario(usuario);
	}
	
	@Transactional
	public void removerUsuarios(Loja loja) {
		List<UsuarioLoja> usuarios = repository.findByLoja(loja);
		repository.deleteInBatch(usuarios);

	}

}

package com.inovvet.core.service.cadastro.clientes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovvet.app.entity.Cidade;
import com.inovvet.app.service.AbstractService;
import com.inovvet.core.repository.cadastro.cliente.CidadeRepository;

@Service
public class CidadeService extends AbstractService {

	@Autowired
	private CidadeRepository repository;

	public Optional<Cidade> buscar(String codigo) {
		return repository.findByCodigo(codigo);
	}

}

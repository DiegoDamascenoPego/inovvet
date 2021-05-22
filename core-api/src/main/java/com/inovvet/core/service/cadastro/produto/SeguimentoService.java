package com.inovvet.core.service.cadastro.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.core.entity.produto.Seguimento;
import com.inovvet.core.entity.produto.dto.SeguimentoPesquisaDTO;
import com.inovvet.core.repository.cadastro.produto.SeguimentoRepository;
import com.inovvet.core.service.base.AbstractService;

@Service
public class SeguimentoService extends AbstractService {
	

	@Autowired
	private SeguimentoRepository repository;
	
	public List<SeguimentoPesquisaDTO> listar() {
		return mapper.map(repository.listarSeguimentos(), SeguimentoPesquisaDTO.class);

	}
	
	public Seguimento localizar(Integer id) {

		return repository.findById(id).orElseThrow(() -> new ServiceException(EnumError.PARAMETROS_INVALIDOS,
				"Registro Seguimento não encontrado"));

	}
}

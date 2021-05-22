package com.inovvet.core.service.cadastro.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.service.AbstractService;
import com.inovvet.core.entity.base.dto.EntityDTO;
import com.inovvet.core.entity.base.dto.FiltroDTO;
import com.inovvet.core.entity.base.dto.SimpleEntityDTO;
import com.inovvet.core.entity.produto.Unidade;
import com.inovvet.core.repository.cadastro.produto.UnidadeRepository;
import com.inovvet.core.service.cadastro.CrudService;

@Service
public class UnidadeService extends AbstractService implements CrudService<Unidade, SimpleEntityDTO, SimpleEntityDTO, EntityDTO>{
	
	@Autowired
	private UnidadeRepository repository;

	@Override
	public Unidade localizar(Integer id) {
		return repository.findById(id).orElseThrow(
				() -> new ServiceException(EnumError.REGISTRO_NAO_ECONTRADO, "Registro Produto não encontrado"));
	}

	@Override
	public SimpleEntityDTO carregar(Integer id) {
		return mapper.map(localizar(id), EntityDTO.class);
	}

	@Override
	public SimpleEntityDTO novo(SimpleEntityDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SimpleEntityDTO atualizar(Integer id, SimpleEntityDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remover(Unidade entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SimpleEntityDTO> listar() {
		return mapper.map(repository.findByAtivo(true), SimpleEntityDTO.class);
	}

	@Override
	public Page<SimpleEntityDTO> filtrar(FiltroDTO<EntityDTO> filtroDTO) {
		// TODO Auto-generated method stub
		return null;
	}
	

}

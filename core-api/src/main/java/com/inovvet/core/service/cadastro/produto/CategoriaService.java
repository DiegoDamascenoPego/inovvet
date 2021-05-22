package com.inovvet.core.service.cadastro.produto;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.util.FiltroUtil;
import com.inovvet.core.entity.base.dto.FiltroDTO;
import com.inovvet.core.entity.categoria.Categoria;
import com.inovvet.core.entity.categoria.dto.CategoriaCadastroDTO;
import com.inovvet.core.entity.categoria.dto.CategoriaDTO;
import com.inovvet.core.entity.categoria.dto.CategoriaFiltro;
import com.inovvet.core.repository.cadastro.produto.CategoriaRepository;
import com.inovvet.core.service.base.AbstractService;

@Service
public class CategoriaService extends AbstractService {

	@Autowired
	private CategoriaRepository repository;

	private Categoria salvar(Categoria entity) {
		try {
			return repository.save(entity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO, e.getMessage());
		}

	}

	private Categoria salvar(CategoriaDTO entity, Categoria categoria) {

		mapper.copy(entity, categoria);	
		
		if (categoria.getId() == null) {
			categoria.setAtivo(true);
		}

		return salvar(categoria);
	}

	private void validarRegistroDuplicado(Integer id, CategoriaDTO entity) {
		Categoria categoria = localizar(entity.getNome()).orElse(null);

		if (categoria != null) {
			if (!categoria.getId().equals(id)) {
				throw new ServiceException(EnumError.REGISTRO_DUPLICADO);
			}
		}

	}

	public Categoria localizar(Integer id) {
		return repository.findById(id).orElseThrow(
				() -> new ServiceException(EnumError.REGISTRO_NAO_ECONTRADO, "Registro Categoria não encontrado"));
	}

	public Optional<Categoria> localizar(String nome) {
		return repository.findByNome(nome);
	}

	public CategoriaCadastroDTO carregar(Integer id) {

		Categoria categoria = localizar(id);

		return mapper.map(categoria, CategoriaCadastroDTO.class);
	}

	@Transactional
	public CategoriaCadastroDTO novo(CategoriaDTO entity) {
		if (localizar(entity.getNome()).isPresent()) {
			throw new ServiceException(EnumError.REGISTRO_DUPLICADO);
		}

		Categoria categoria = salvar(entity, new Categoria());

		return mapper.map(categoria, CategoriaCadastroDTO.class);
	}

	@Transactional
	public CategoriaCadastroDTO atualizar(Integer id, CategoriaDTO entity) {
		validarRegistroDuplicado(id, entity);

		Categoria categoria = localizar(id);

		categoria = salvar(entity, categoria);

		return mapper.map(categoria, CategoriaCadastroDTO.class);
	}

	@Transactional
	public void remover(Categoria categoria) {
		try {
			repository.delete(categoria);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_REMOVER_REGISTRO, "Registro já utilizado não pode ser removido");
		}
	}

	@Transactional
	public void remover(Integer id) {
		Categoria categoria = localizar(id);

		remover(categoria);
	}
	
	public List<CategoriaCadastroDTO> listar() {
		return mapper.map(repository.findByAtivo(true), CategoriaCadastroDTO.class);

	}


	public Page<CategoriaCadastroDTO> filtrar(FiltroDTO<CategoriaFiltro> filtro) {	

		Example<Categoria> example = FiltroUtil.getByExample(filtro, Categoria.class);

		Page<Categoria> list = repository.findAll(example,
				PageRequest.of(filtro.getPage(), filtro.getSize(), Sort.by(Sort.Direction.ASC, "nome")));

		return list.map(entity -> {
			return mapper.map(entity, CategoriaCadastroDTO.class);
		});

	}

}

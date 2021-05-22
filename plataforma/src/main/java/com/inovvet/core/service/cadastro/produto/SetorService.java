package com.inovvet.core.service.cadastro.produto;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.core.entity.produto.Setor;
import com.inovvet.core.entity.produto.dto.SetorCadastroDTO;
import com.inovvet.core.entity.produto.dto.SetorDTO;
import com.inovvet.core.entity.produto.dto.SetorPesquisaDTO;
import com.inovvet.core.repository.cadastro.produto.SetorRepository;
import com.inovvet.core.service.base.AbstractService;

@Service
public class SetorService extends AbstractService {

	@Autowired
	private SetorRepository repository;

	@Transactional
	private Setor salvar(Setor entity) {
		try {
			return repository.save(entity);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO, e.getMessage());
		}

	}

	public Setor localizar(Integer id) {

		return repository.findById(id).orElseThrow(() -> new ServiceException(EnumError.PARAMETROS_INVALIDOS,
				"Registro Setor não encontrado"));

	}

	@Transactional
	public SetorCadastroDTO novo(SetorDTO dto) {

		Setor departamento = new Setor();

		mapper.copy(dto, departamento);

		departamento.setAtivo(true);
		
		departamento = salvar(departamento);

		return mapper.map(departamento, SetorCadastroDTO.class);

	}

	@Transactional
	public SetorCadastroDTO atualizar(SetorDTO dto, Integer id) {

		Setor setor = localizar(id);

		mapper.copy(dto, setor);

		return mapper.map(salvar(setor), SetorCadastroDTO.class);

	}

	@Transactional
	public void remover(Setor departamento) {

		try {
			repository.delete(departamento);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_REMOVER_REGISTRO, e.getMessage());
		}
	}

	@Transactional
	public void remover(Integer id) {
		Setor departamento = localizar(id);

		remover(departamento);
	}

	public List<SetorPesquisaDTO> listar(String nome) {
		return mapper.map(repository.findByAtivo(nome), SetorPesquisaDTO.class);

	}

}

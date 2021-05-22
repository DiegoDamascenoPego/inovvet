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
import com.inovvet.core.entity.departamento.Departamento;
import com.inovvet.core.entity.departamento.dto.DepartamentoCadastroDTO;
import com.inovvet.core.entity.departamento.dto.DepartamentoDTO;
import com.inovvet.core.entity.departamento.dto.DepartamentoFiltro;
import com.inovvet.core.entity.produto.Seguimento;
import com.inovvet.core.repository.cadastro.produto.DepartamentoRepository;
import com.inovvet.core.service.base.AbstractService;

@Service
public class DepartamentoService extends AbstractService {

	@Autowired
	private DepartamentoRepository repository;

	@Autowired
	private SeguimentoService seguimentoService;

	private Departamento salvar(Departamento entity) {
		try {
			return repository.save(entity);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO, e.getMessage());
		}

	}

	private Departamento salvar(DepartamentoDTO entity, Departamento departamento) {

		mapper.copy(entity, departamento);

		Seguimento seguimento = seguimentoService.localizar(entity.getSeguimento().getId());
		departamento.setSeguimento(seguimento);

		if (departamento.getId() == null) {
			departamento.setAtivo(true);
		}

		return salvar(departamento);
	}

	private void validarRegistroDuplicado(Integer id, DepartamentoDTO entity) {
		Departamento departamento = localizar(entity.getNome()).orElse(null);

		if (departamento != null) {
			if (!departamento.getId().equals(id)) {
				throw new ServiceException(EnumError.REGISTRO_DUPLICADO);
			}
		}

	}

	public Departamento localizar(Integer id) {
		return repository.findById(id).orElseThrow(
				() -> new ServiceException(EnumError.REGISTRO_NAO_ECONTRADO, "Registro Departamento não encontrado"));
	}

	public Optional<Departamento> localizar(String nome) {
		return repository.findByNome(nome);
	}

	public DepartamentoCadastroDTO carregar(Integer id) {

		Departamento departamento = localizar(id);

		return mapper.map(departamento, DepartamentoCadastroDTO.class);
	}

	@Transactional
	public DepartamentoCadastroDTO novo(DepartamentoDTO entity) {
		if (localizar(entity.getNome()).isPresent()) {
			throw new ServiceException(EnumError.REGISTRO_DUPLICADO);
		}

		Departamento departamento = salvar(entity, new Departamento());

		return mapper.map(departamento, DepartamentoCadastroDTO.class);
	}

	@Transactional
	public DepartamentoCadastroDTO atualizar(Integer id, DepartamentoDTO entity) {
		validarRegistroDuplicado(id, entity);

		Departamento departamento = localizar(id);

		departamento = salvar(entity, departamento);

		return mapper.map(departamento, DepartamentoCadastroDTO.class);
	}

	@Transactional
	public void remover(Departamento departamento) {
		try {
			repository.delete(departamento);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_REMOVER_REGISTRO, "Registro já utilizado não pode ser removido");
		}
	}

	@Transactional
	public void remover(Integer id) {
		Departamento departamento = localizar(id);

		remover(departamento);
	}

	public List<DepartamentoCadastroDTO> listar() {
		return mapper.map(repository.listarDepartamentoAtivo(), DepartamentoCadastroDTO.class);

	}

	public Page<DepartamentoCadastroDTO> filtrar(FiltroDTO<DepartamentoFiltro> filtro) {

		Example<Departamento> example = FiltroUtil.getByExample(filtro, Departamento.class);

		Page<Departamento> list = repository.findAll(example,
				PageRequest.of(filtro.getPage(), filtro.getSize(), Sort.by(Sort.Direction.ASC, "nome")));

		return list.map(entity -> {
			return mapper.map(entity, DepartamentoCadastroDTO.class);
		});

	}

}

package com.inovvet.core.service.cadastro.clientes;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.service.AbstractService;
import com.inovvet.app.util.FiltroUtil;
import com.inovvet.core.entity.animal.Animal;
import com.inovvet.core.entity.animal.EnumTipoAnimal;
import com.inovvet.core.entity.animal.Raca;
import com.inovvet.core.entity.animal.dto.AnimalCadastroDTO;
import com.inovvet.core.entity.animal.dto.AnimalConsultaDTO;
import com.inovvet.core.entity.animal.dto.AnimalDTO;
import com.inovvet.core.entity.animal.dto.AnimalFiltroDTO;
import com.inovvet.core.entity.animal.dto.AnimalPesquisaDTO;
import com.inovvet.core.entity.animal.dto.CorDTO;
import com.inovvet.core.entity.animal.dto.RacaDTO;
import com.inovvet.core.entity.base.dto.FiltroDTO;
import com.inovvet.core.entity.cliente.Cliente;
import com.inovvet.core.repository.cadastro.cliente.AnimalRepository;
import com.inovvet.core.repository.cadastro.cliente.CorRepository;
import com.inovvet.core.repository.cadastro.cliente.RacaRepository;

@Service
public class AnimalService extends AbstractService {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private RacaRepository racaRepository;

	@Autowired
	private CorRepository corRepository;

	@Autowired
	private AnimalRepository animalRepository;

	@Transactional
	private Animal salvar(Animal animal) {
		try {
			return animalRepository.save(animal);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO, e.getMessage());
		}

	}

	@Transactional
	public void remover(Animal animal) {

		try {
			animalRepository.delete(animal);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_REMOVER_REGISTRO, e.getMessage());
		}
	}

	private void ajustarCorAnimal(Animal animal, List<String> cores) {
		StringBuilder values = new StringBuilder();
		cores.forEach(value -> {
			if (!values.toString().isEmpty()) {
				values.append(",");
			}
			values.append(value);

		});

		animal.setCor(values.toString());
	}

	private void ajustarCorAnimal(AnimalDTO dto, String cores) {

		String values[] = cores.split(",");
		Arrays.asList(values).forEach(value -> {
			dto.getCores().add(value);
		});

	}

	private Animal salvar(AnimalDTO dto, Animal animal) {
		BeanUtils.copyProperties(dto, animal);

		animal.setRaca(mapper.map(carregarRaca(dto.getRaca().getId()), Raca.class));

		animal.setCliente(mapper.map(clienteService.buscarPorId(dto.getClienteId()), Cliente.class));

		ajustarCorAnimal(animal, dto.getCores());

		return salvar(animal);
	}

	private Animal salvar(AnimalDTO dto) {
		Animal animal = new Animal();

		ajustarCorAnimal(animal, dto.getCores());

		return salvar(dto, animal);
	}

	public Animal localizar(Integer id) {
		return animalRepository.findById(id).orElseThrow(
				() -> new ServiceException(EnumError.PARAMETROS_INVALIDOS, "Registro Animal não encontrado"));
	}

	public List<RacaDTO> listarRaca(EnumTipoAnimal tipoAnimal) {
		return mapper.map(racaRepository.findByTipo(tipoAnimal), RacaDTO.class);
	}

	public Raca carregarRaca(Integer id) {
		return racaRepository.findById(id).orElseThrow(() -> new ServiceException(EnumError.PARAMETROS_INVALIDOS));
	}

	public List<CorDTO> listarCor() {
		return mapper.map(corRepository.findAllByOrderByNomeAsc(), CorDTO.class);
	}

	public AnimalCadastroDTO novo(AnimalDTO entity) {

		Animal animal = salvar(entity);

		AnimalCadastroDTO dto = mapper.map(animal, AnimalCadastroDTO.class);

		ajustarCorAnimal(dto, animal.getCor());

		dto.setClienteId(animal.getCliente().getId());

		return dto;
	}

	@Transactional
	public AnimalCadastroDTO atualizar(Integer id, AnimalDTO entity) {

		Animal animal = mapper.map(localizar(id), Animal.class);

		animal = salvar(entity, animal);

		AnimalCadastroDTO dto = mapper.map(animal, AnimalCadastroDTO.class);

		dto.setClienteId(animal.getCliente().getId());

		ajustarCorAnimal(dto, animal.getCor());

		return dto;
	}

	public AnimalCadastroDTO carregar(Integer id) {

		Animal animal = localizar(id);

		AnimalCadastroDTO dto = mapper.map(animal, AnimalCadastroDTO.class);

		ajustarCorAnimal(dto, animal.getCor());

		dto.setClienteId(animal.getCliente().getId());

		return dto;
	}

	@Transactional
	public void remover(Integer id) {
		Animal animal = localizar(id);

		remover(animal);
	}

	public Page<AnimalPesquisaDTO> filtrar(FiltroDTO<AnimalFiltroDTO> filtro) {

		Example<Animal> example = FiltroUtil.getByExample(filtro, Animal.class);

		Page<Animal> list = animalRepository.findAll(example,
				PageRequest.of(filtro.getPage(), filtro.getSize(), Sort.by(Sort.Direction.ASC, "nome")));

		return list.map(animal -> {
			return mapper.map(animal, AnimalPesquisaDTO.class);
		});

	}
	
	public List<AnimalConsultaDTO> listar(Integer clienteId) {		
		return mapper.map(animalRepository.listar(clienteId), AnimalConsultaDTO.class);
	}

}

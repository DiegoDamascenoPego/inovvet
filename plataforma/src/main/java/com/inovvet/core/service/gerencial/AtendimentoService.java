package com.inovvet.core.service.gerencial;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inovvet.app.config.Contexto;
import com.inovvet.app.exception.EnumError;
import com.inovvet.app.exception.ServiceException;
import com.inovvet.app.reports.Relatorio;
import com.inovvet.app.service.AbstractService;
import com.inovvet.core.entity.atendimento.Atendimento;
import com.inovvet.core.entity.atendimento.Endereco;
import com.inovvet.core.entity.atendimento.dto.AtendimentoCadastroDTO;
import com.inovvet.core.entity.atendimento.dto.AtendimentoDTO;
import com.inovvet.core.entity.atendimento.dto.AtendimentoFiltroDTO;
import com.inovvet.core.entity.atendimento.dto.AtendimentoListaDTO;
import com.inovvet.core.entity.atendimento.dto.TipoAtendimentoCadastroDTO;
import com.inovvet.core.entity.atendimento.dto.VagaDTO;
import com.inovvet.core.entity.base.dto.EntityDTO;
import com.inovvet.core.entity.base.dto.FiltroDTO;
import com.inovvet.core.enumerator.EnumDiaSemana;
import com.inovvet.core.enumerator.EnumStatusAtendimento;
import com.inovvet.core.repository.cadastro.atendimento.AtendimentoRepository;
import com.inovvet.core.service.cadastro.CrudService;
import com.inovvet.core.service.cadastro.atendimento.TipoAtendimentoService;
import com.inovvet.core.service.cadastro.clientes.AnimalService;
import com.inovvet.core.service.cadastro.clientes.ClienteService;
import com.inovvet.core.util.ReportsName;

@Service
public class AtendimentoService extends AbstractService
		implements CrudService<Atendimento, AtendimentoDTO, AtendimentoCadastroDTO, AtendimentoFiltroDTO> {

	@Autowired
	private TipoAtendimentoService tipoAtendimentoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private AnimalService animalService;

	@Autowired
	private AtendimentoRepository repository;

	@Autowired
	private Relatorio relatorio;

	private void validarAtendimento(Atendimento atendimento, AtendimentoDTO dto) {

		if (atendimento.getStatus().equals(EnumStatusAtendimento.FINALIZADO)) {
			throw new ServiceException(EnumError.ALTERACAO_NAO_PERMITIDA,
					"Atendimento Finalizado não pode ser Alterado");
		}

		if (!atendimento.getData().equals(dto.getData().atTime(dto.getVaga().getHora()))) {

			if (!vagaDisponivel(dto.getTipoAtendimento(), dto.getData(), dto.getVaga().getHora())) {
				throw new ServiceException(EnumError.DATA_INDISPONIVEL, "Vaga Selecionada Não Está Disponível");
			}
		}
	}

	public List<VagaDTO> listarVagas(Integer tipoAtendimentoId, LocalDate data) {

		DayOfWeek diaSemana = data.getDayOfWeek();

		TipoAtendimentoCadastroDTO tipoAtendimento = tipoAtendimentoService.carregar(tipoAtendimentoId, EnumDiaSemana.get(diaSemana));

		List<VagaDTO> vagas = new ArrayList<VagaDTO>();

		tipoAtendimento.getAgenda().forEach(vaga -> {

			if (vaga.getDia().equals(EnumDiaSemana.get(diaSemana))) {

				Integer quantidadeAtendimentos = 0;

				LocalDateTime dataAgendamento = data.atTime(vaga.getHora());

				quantidadeAtendimentos = repository.quantidadeAtendimentos(tipoAtendimento.getId(), dataAgendamento);

				vagas.add(new VagaDTO(data, vaga.getHora(), vaga.getVaga(), vaga.getVaga() - quantidadeAtendimentos));
			}

		});

		vagas.sort(Comparator.comparing(VagaDTO::getHora));

		return vagas;
	}

	@Override
	public Atendimento localizar(Integer id) {
		return repository.findById(id).orElseThrow(
				() -> new ServiceException(EnumError.REGISTRO_NAO_ECONTRADO, "Registro Atendimento não encontrado"));
	}

	@Override
	public AtendimentoCadastroDTO carregar(Integer id) {
		Atendimento atendimento = localizar(id);

		AtendimentoCadastroDTO atendimentoCadastro = mapper.map(atendimento, AtendimentoCadastroDTO.class);

		atendimentoCadastro
				.setVaga(new VagaDTO(atendimento.getData().toLocalDate(), atendimento.getData().toLocalTime()));

		atendimentoCadastro.setData(atendimento.getData().toLocalDate());

		return atendimentoCadastro;
	}

	@Override
	@Transactional
	public AtendimentoCadastroDTO novo(AtendimentoDTO dto) {

		if (!vagaDisponivel(dto.getTipoAtendimento(), dto.getData(), dto.getVaga().getHora())) {
			throw new ServiceException(EnumError.DATA_INDISPONIVEL, "Vaga Selecionada Não Está Disponível");
		}

		return registrar(dto, new Atendimento());
	}

	@Override
	@Transactional
	public AtendimentoCadastroDTO atualizar(Integer id, AtendimentoDTO dto) {
		Atendimento atendimento = localizar(id);

		validarAtendimento(atendimento, dto);

		return registrar(dto, atendimento);
	}

	@Override
	public void remover(Atendimento entity) {
		try {
			repository.delete(entity);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_REMOVER_REGISTRO, "Registro já utilizado não pode ser removido");
		}
	}

	@Override
	public void remover(Integer id) {
		Atendimento atendimento = localizar(id);

		if (atendimento.getStatus().equals(EnumStatusAtendimento.FINALIZADO)) {
		}

		switch (atendimento.getStatus()) {
		case FINALIZADO:
			throw new ServiceException(EnumError.ALTERACAO_NAO_PERMITIDA,
					"Atendimento Finalizado não pode ser Removido");

		case EXECUCAO:
			throw new ServiceException(EnumError.ALTERACAO_NAO_PERMITIDA,
					"Atendimento Em Execução não pode ser Removido");
		default:
			break;
		}

		remover(atendimento);

	}

	public void finalizar(Integer id) {
		Atendimento atendimento = localizar(id);

		if (atendimento.getData().isAfter(LocalDateTime.now())) {
			throw new ServiceException(EnumError.ALTERACAO_NAO_PERMITIDA,
					"Atendimento não pode ser finalizado antes da data de agendamento");
		}

		atendimento.setStatus(EnumStatusAtendimento.FINALIZADO);

		salvar(atendimento);
	}

	@Override
	public List<AtendimentoCadastroDTO> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<AtendimentoCadastroDTO> filtrar(FiltroDTO<AtendimentoFiltroDTO> filtroDTO) {
		return null;
	}

	public Page<AtendimentoListaDTO> filtrarAtendimento(FiltroDTO<AtendimentoFiltroDTO> filtroDTO) {

		Pageable pageable = PageRequest.of(filtroDTO.getPage(), filtroDTO.getSize(),
				Sort.by(Sort.Direction.ASC, "data"));

		Integer clienteId = 0;
		Integer atendimentoId = 0;

		if (filtroDTO.getObj().getCliente() != null) {
			clienteId = filtroDTO.getObj().getCliente().getId();
		}

		if (filtroDTO.getObj().getTipoAtendimento() != null) {
			atendimentoId = filtroDTO.getObj().getTipoAtendimento().getId();
		}

		Page<Atendimento> list = repository.listarAtendimentos(atendimentoId, clienteId,
				filtroDTO.getObj().getDataInicio().atTime(00, 00), filtroDTO.getObj().getDataFim().atTime(23, 59),
				filtroDTO.getObj().getStatus(), pageable);

		return list.map(entity -> {
			AtendimentoListaDTO atendimentoListaDTO = mapper.map(entity, AtendimentoListaDTO.class);

			clienteService.listarTelefones(atendimentoListaDTO.getCliente().getId()).forEach(tel -> {
				atendimentoListaDTO.setTelefones(tel.getNumero());
			});

			StringBuilder telefones = new StringBuilder();

			clienteService.listarTelefones(atendimentoListaDTO.getCliente().getId())
					.forEach(telefone -> telefones.append(telefone.getNumero()));
			;

			atendimentoListaDTO.setTelefones(telefones.toString());

			return atendimentoListaDTO;
		});

	}

	@Transactional
	private Atendimento salvar(Atendimento entity) {
		try {
			return repository.save(entity);
		} catch (Exception e) {
			throw new ServiceException(EnumError.FALHA_SALVAR_REGISTRO, e.getMessage());
		}

	}

	private boolean enderecoValido(Endereco endereco) {

		if (endereco.getRua().isEmpty()) {
			return false;
		}

		if (endereco.getNumero().isEmpty()) {
			return false;
		}

		if (endereco.getBairro().isEmpty()) {
			return false;
		}

		if (endereco.getCidade().getCodigo() == null) {
			return false;
		}

		return true;
	}

	public AtendimentoCadastroDTO registrar(AtendimentoDTO dto, Atendimento atendimento) {
		Atendimento entity = salvar(dto, atendimento);

		AtendimentoCadastroDTO atendimentoCadastro = mapper.map(entity, AtendimentoCadastroDTO.class);

		atendimentoCadastro.setVaga(new VagaDTO(entity.getData().toLocalDate(), entity.getData().toLocalTime()));

		atendimentoCadastro.setData(entity.getData().toLocalDate());

		return atendimentoCadastro;
	}

	private Atendimento salvar(AtendimentoDTO dto, Atendimento atendimento) {

		atendimento.setCliente(clienteService.localizar(dto.getCliente().getId()));
		atendimento.setAnimal(animalService.localizar(dto.getAnimal().getId()));
		atendimento.setTipoAtendimento(tipoAtendimentoService.localizar(dto.getTipoAtendimento().getId()));
		atendimento.setLoja(Contexto.getLoja());
		atendimento.setUsuario(Contexto.getUsuario());
		atendimento.setObservacao(dto.getObservacao());
		atendimento.setStatus(dto.getStatus());
		atendimento.setData(dto.getData().atTime(dto.getVaga().getHora()));
		atendimento.setTelefone(dto.getTelefones());
		atendimento.setEndereco(mapper.map(dto.getEndereco(), Endereco.class));

		if (!enderecoValido(atendimento.getEndereco())) {
			throw new ServiceException(EnumError.PARAMETROS_INVALIDOS, "Endereço informado inválido");
		}

		return salvar(atendimento);
	}

	private boolean vagaDisponivel(EntityDTO tipoAtendimento, LocalDate data, LocalTime hora) {
		List<VagaDTO> vagas = listarVagas(tipoAtendimento.getId(), data);

		for (VagaDTO vaga : vagas) {
			if (vaga.getHora().equals(hora)) {
				if (vaga.getDisponivel() > 0) {
					return true;
				}
			}
		}
		return false;
	}

	public String relatoriAtendimento() {

		return this.relatorio.configure().pdf().parameters(null).source(ReportsName.RELATORIO_ATENDIMENTO).build()
				.get();

	}

}

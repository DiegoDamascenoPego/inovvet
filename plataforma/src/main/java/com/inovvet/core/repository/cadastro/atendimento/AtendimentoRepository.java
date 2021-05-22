package com.inovvet.core.repository.cadastro.atendimento;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.atendimento.Atendimento;
import com.inovvet.core.enumerator.EnumStatusAtendimento;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Integer> {
	
	@Query("SELECT a from Atendimento a "
			+ " inner join a.tipoAtendimento t"
			+ " where "
			+ " t.id = :tipoAtendimentoId and "
			+ " a.data = :data and "
			+ " a.status <> 5 ")
	public List<Atendimento> listarAtendimentos(
			@Param("tipoAtendimentoId") Integer tipoAtendimentoId,			
			@Param("data") LocalDateTime data);
	
	
	@Query(value =  "SELECT COUNT(*) from atendimento a "
			+ " where "
			+ " a.tipo_atendimento_id = :tipoAtendimentoId and "
			+ " a.data = :dataInicio and "
			+ " a.status <> 5 "	, nativeQuery = true		
			)
	public Integer quantidadeAtendimentos(
			@Param("tipoAtendimentoId") Integer tipoAtendimentoId,			
			@Param("dataInicio") LocalDateTime dataInicio);
	
	
	@Query("SELECT a from Atendimento a "
			+ " inner join a.tipoAtendimento t"
			+ " inner join a.cliente c"
			+ " where "
			+ " (t.id = :tipoAtendimentoId or :tipoAtendimentoId = 0) and "
			+ " (c.id = :clienteId or :clienteId = 0) and "
			+ " a.data BETWEEN :dataInicio and  :dataFim and "
			+ " a.status IN(:status)")
	public Page<Atendimento> listarAtendimentos(
	@Param("tipoAtendimentoId") Integer tipoAtendimentoId,	
	@Param("clienteId") Integer clienteId,	
	@Param("dataInicio") LocalDateTime dataIni,	
	@Param("dataFim") LocalDateTime dataFim,
	@Param("status") List<EnumStatusAtendimento> status,
	Pageable pageable
	);

}

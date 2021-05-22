package com.inovvet.core.repository.cadastro.atendimento;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.atendimento.TipoAtendimento;
import com.inovvet.core.enumerator.EnumDiaSemana;

@Repository
public interface TipoAtendimentoRepository extends JpaRepository<TipoAtendimento, Integer> {

	List<TipoAtendimento> findByAtivo(Boolean ativo);
	
	
	@Query(value = "select t from TipoAtendimento t inner join FETCH  t.agenda a where t.id = :id and a.dia = :diaSemana ")
	Optional<TipoAtendimento> carregarAtendimentoAgenda(Integer id, EnumDiaSemana diaSemana);
	
}

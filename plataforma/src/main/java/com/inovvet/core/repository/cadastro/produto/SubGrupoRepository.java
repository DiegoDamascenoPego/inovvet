package com.inovvet.core.repository.cadastro.produto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.subgrupo.SubGrupo;
import java.lang.Boolean;
import java.util.List;

@Repository
public interface SubGrupoRepository extends JpaRepository<SubGrupo, Integer> {

	public Optional<SubGrupo> findByNome(String nome);
	
	List<SubGrupo> findByAtivo(Boolean ativo);
}

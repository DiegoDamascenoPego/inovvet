package com.inovvet.core.repository.cadastro.pessoa;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.pessoa.Fabricante;
import com.inovvet.core.enumerator.EnumFinalidadePessoa;

@Repository
public interface FabricanteRepository extends JpaRepository<Fabricante, Integer>, PessoaRepositoryCustom {

	List<Fabricante> findByTipoAndAtivo(EnumFinalidadePessoa tipo, boolean ativo);

	List<Fabricante> findByDocumentoLike(String documento);
	
	Optional<Fabricante> findByDocumentoAndTipo(String documento, EnumFinalidadePessoa tipo);
		
}

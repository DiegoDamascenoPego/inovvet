package com.inovvet.core.repository.cadastro.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.animal.EnumTipoAnimal;
import com.inovvet.core.entity.animal.Raca;

@Repository
public interface RacaRepository extends JpaRepository<Raca, Integer> {
	
	List<Raca> findByTipo(EnumTipoAnimal id);
	
}

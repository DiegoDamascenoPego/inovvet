package com.inovvet.core.repository.cadastro.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.animal.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {
	
	@Query("SELECT a from Animal a "
			+ "where "
			+ " a.cliente.id = :cliente_id and "
			+ " a.ativo = 1 order by a.nome")
	public List<Animal> listar(
			@Param("cliente_id") Integer cliente_id);

}

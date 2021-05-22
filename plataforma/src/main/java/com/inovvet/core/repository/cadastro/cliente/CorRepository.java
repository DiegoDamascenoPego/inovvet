package com.inovvet.core.repository.cadastro.cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.animal.Cor;

@Repository
public interface CorRepository extends JpaRepository<Cor, Integer> {
	
	List<Cor> findAllByOrderByNomeAsc();

}

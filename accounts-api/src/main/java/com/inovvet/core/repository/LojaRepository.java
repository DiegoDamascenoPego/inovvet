package com.inovvet.core.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.Loja;

@Repository
public interface LojaRepository extends CrudRepository<Loja, Integer> {
	
	List<Loja> findByContaId(Integer contaid);
	
	Optional<Loja> findByToken(String token);
	
	
	

}

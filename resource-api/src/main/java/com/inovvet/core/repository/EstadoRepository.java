package com.inovvet.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
	
}

package com.inovvet.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.core.entity.LogHandler;

@Repository
public interface LogHandlerRepository extends JpaRepository<LogHandler, Integer> {

}

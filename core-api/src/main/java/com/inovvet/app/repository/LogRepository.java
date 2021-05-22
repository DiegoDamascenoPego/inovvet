package com.inovvet.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inovvet.app.entity.Log;

@Repository
public interface LogRepository extends CrudRepository<Log, Integer> {

}

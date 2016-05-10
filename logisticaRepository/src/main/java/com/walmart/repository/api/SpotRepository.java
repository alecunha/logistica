package com.walmart.repository.api;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.walmart.repository.entity.SpotEntity;

public interface SpotRepository extends CrudRepository<SpotEntity, Long> {

	List<SpotEntity> findAll();
	
	@Query("SELECT s FROM SpotEntity s WHERE s.name = :name")
	SpotEntity findByName(@Param("name") String name);
	
}
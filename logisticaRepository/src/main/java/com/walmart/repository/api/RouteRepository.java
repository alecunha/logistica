package com.walmart.repository.api;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.walmart.repository.entity.RouteEntity;

public interface RouteRepository extends CrudRepository<RouteEntity, Long> {
	
	@Override
	List<RouteEntity> findAll();
	
	@Query("SELECT r FROM RouteEntity r WHERE r.map.name = :nameMap")
	List<RouteEntity> findByNameMap(@Param("nameMap")String nameMap);

}

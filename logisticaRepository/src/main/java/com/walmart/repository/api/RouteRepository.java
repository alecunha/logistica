package com.walmart.repository.api;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.walmart.repository.entity.RouteEntity;

public interface RouteRepository extends CrudRepository<RouteEntity, Long> {
	
	@Override
	List<RouteEntity> findAll();

}

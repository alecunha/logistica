package com.walmart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walmart.repository.api.RouteRepository;
import com.walmart.repository.entity.MapEntity;
import com.walmart.repository.entity.RouteEntity;
import com.walmart.service.api.RouteService;
import com.walmart.service.converter.RouteConverter;
import com.walmart.service.model.Route;

@Service
public class RouteServiceImpl implements RouteService {

	@Autowired
	private RouteRepository routeRepository;
	
	private RouteConverter routerConverter = new RouteConverter();
	
	@Override
	@Transactional
	public void save(List<Route> routes, MapEntity mapEntity) {
		for (Route route : routes) {
			RouteEntity entity = routerConverter.converterTOEntity(route);
			entity.setMap(mapEntity);
			routeRepository.save(entity);
		}
	}

}

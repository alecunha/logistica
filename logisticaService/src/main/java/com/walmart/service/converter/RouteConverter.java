package com.walmart.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.walmart.repository.entity.RouteEntity;
import com.walmart.service.model.Route;
import com.walmart.service.model.Spot;

public class RouteConverter extends ConverterEntityVO<RouteEntity, Route> {

	private SpotConverter sportConverter = new SpotConverter();
	
	@Override
	public Route converterTOVO(RouteEntity entity) {
		if(entity == null) return null;
		return new Route(entity.getId(), 
					     new Spot(entity.getSource().getId(), entity.getSource().getName()), 
					     new Spot(entity.getDestination().getId(), entity.getDestination().getName()), 
						 entity.getWeight());
	}

	@Override
	public RouteEntity converterTOEntity(Route vo) {
		RouteEntity entity = new RouteEntity();
		entity.setId(vo.getId());
		entity.setDestination(sportConverter.converterTOEntity(vo.getDestination()));
		entity.setSource(sportConverter.converterTOEntity(vo.getSource()));
		entity.setWeight(vo.getWeight());
		
		return entity;
	}

	public List<Route> converterTOVO(List<RouteEntity> listEntity) {
		List<Route> listVO = new ArrayList<Route>();
		
		for (RouteEntity entity : listEntity) {
			Route route = converterTOVO(entity);
			listVO.add(route);
		}
		
		return listVO;
	}
	
	public List<RouteEntity> converterTOEntity(List<Route> listVO) {
		List<RouteEntity> listEntity = new ArrayList<RouteEntity>();
		
		for (Route vo : listVO) {
			RouteEntity entity = converterTOEntity(vo);
			listEntity.add(entity);
		}
		
		return listEntity;
	}
	
}

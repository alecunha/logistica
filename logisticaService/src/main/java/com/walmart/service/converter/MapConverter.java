package com.walmart.service.converter;

import com.walmart.repository.entity.MapEntity;
import com.walmart.service.model.Map;

public class MapConverter extends ConverterEntityVO<MapEntity, Map>{

	@Override
	public Map converterTOVO(MapEntity entity) {
		return entity == null ? null : new Map(entity.getId(), entity.getName());
	}

	@Override
	public MapEntity converterTOEntity(Map vo) {
		MapEntity mapEntity = new MapEntity();
		mapEntity.setId(vo.getId());
		mapEntity.setName(vo.getNameMap());
		
		return mapEntity;
	}

	
}

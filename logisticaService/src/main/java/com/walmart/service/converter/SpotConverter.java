package com.walmart.service.converter;

import java.util.ArrayList;
import java.util.List;

import com.walmart.repository.entity.SpotEntity;
import com.walmart.service.model.Spot;

public class SpotConverter extends ConverterEntityVO<SpotEntity, Spot> {

	@Override
	public Spot converterTOVO(SpotEntity entity) {
		return entity == null ? null : new Spot(entity.getId(), entity.getName());
	}

	@Override
	public SpotEntity converterTOEntity(Spot vo) {
		SpotEntity entity = new SpotEntity();
		entity.setId(vo.getId());
		entity.setName(vo.getName());
		return entity;
	}

	public List<Spot> converterTOVO(List<SpotEntity> listEntity) {
		List<Spot> listVO = new ArrayList<Spot>();
		
		for (SpotEntity entity : listEntity) {
			Spot spot = converterTOVO(entity);
			listVO.add(spot);
		}
		return listVO;
	}
}

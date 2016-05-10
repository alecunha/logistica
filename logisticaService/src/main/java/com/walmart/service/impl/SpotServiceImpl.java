package com.walmart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.walmart.repository.api.SpotRepository;
import com.walmart.repository.entity.SpotEntity;
import com.walmart.service.api.SpotService;
import com.walmart.service.converter.SpotConverter;
import com.walmart.service.model.Spot;

@Service
public class SpotServiceImpl implements SpotService {

	@Autowired
	private SpotRepository spotRepository;
	
	@Override
	@Transactional
	public Spot save(Spot spot) {
		SpotEntity spotEntity = spotRepository.findByName(spot.getName());
		if (spotEntity == null) {
			spotEntity = new SpotConverter().converterTOEntity(spot);
			spotEntity = spotRepository.save(spotEntity);
		}
		return new SpotConverter().converterTOVO(spotEntity);
	}

}

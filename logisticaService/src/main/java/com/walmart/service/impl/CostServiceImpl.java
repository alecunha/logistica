package com.walmart.service.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.walmart.service.api.CostService;
import com.walmart.service.model.Cost;

@Service
public class CostServiceImpl implements CostService {
	
	@Override
	public BigDecimal rate(int distance, int autonomy, BigDecimal value) {
		return new Cost().rate(distance, autonomy, value);
	}

}

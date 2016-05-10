package com.walmart.service.api;

import java.math.BigDecimal;

public interface CostService {

	public BigDecimal rate(int distance, int autonomy, BigDecimal value);
	
}

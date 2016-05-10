package com.walmart.service.model;

import java.math.BigDecimal;

public class Cost {

	public BigDecimal rate(int distance, int autonomy, BigDecimal value) {
		return value.multiply(new BigDecimal((double)distance/autonomy));
	}
}

package com.walmart.service.api;

import java.util.LinkedList;

import com.walmart.service.model.Spot;

public interface PathService {

	LinkedList<Spot> minimalDistance(String nameMap, String source, String destination);
	
	int getDistance(String target);
	
}

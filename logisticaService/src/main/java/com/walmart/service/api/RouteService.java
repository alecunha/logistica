package com.walmart.service.api;

import java.util.List;

import com.walmart.repository.entity.MapEntity;
import com.walmart.service.model.Route;

public interface RouteService {

	void save(List<Route> route, MapEntity mapEntity);
}

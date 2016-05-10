package com.walmart.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.repository.api.RouteRepository;
import com.walmart.repository.api.SpotRepository;
import com.walmart.service.api.PathService;
import com.walmart.service.converter.RouteConverter;
import com.walmart.service.converter.SpotConverter;
import com.walmart.service.model.DijkstraAlgorithm;
import com.walmart.service.model.Graph;
import com.walmart.service.model.Route;
import com.walmart.service.model.Spot;

@Service
public class PathServiceImpl implements PathService {
	
	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private SpotRepository spotRepository;
	
	private DijkstraAlgorithm dijkstra;
	
	public LinkedList<Spot> minimalDistance(String nameMap,String source, String target) {
		List<Route> routes = new RouteConverter().converterTOVO(routeRepository.findByNameMap(nameMap));
		Graph graph = new Graph(routes);
		dijkstra = new DijkstraAlgorithm(graph);
		Spot sourceSpot = new SpotConverter().converterTOVO(spotRepository.findByName(source));
		Spot targetSpot = new SpotConverter().converterTOVO(spotRepository.findByName(target));
		
		dijkstra.execute(sourceSpot);
		
		return dijkstra.getPath(targetSpot);
	}

	public int getDistance(String target) {
		Spot targetSpot = new SpotConverter().converterTOVO(spotRepository.findByName(target));
		return dijkstra.getDistance(targetSpot);
	}
}

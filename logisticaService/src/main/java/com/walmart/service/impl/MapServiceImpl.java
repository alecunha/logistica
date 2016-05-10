package com.walmart.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.repository.api.MapRepository;
import com.walmart.repository.entity.MapEntity;
import com.walmart.service.api.MapService;
import com.walmart.service.api.RouteService;
import com.walmart.service.api.SpotService;
import com.walmart.service.model.Route;
import com.walmart.service.model.Spot;

@Service
public class MapServiceImpl implements MapService {

	@Autowired
	private SpotService spotService;
	
	@Autowired
	private RouteService routeService;
	
	@Autowired
	private MapRepository mapRepository;
	
	@Override
	public boolean save(InputStream inputStream, String nameMap) {
		
			MapEntity mapEntity = new MapEntity();
			mapEntity.setName(nameMap);
			
			mapEntity = mapRepository.save(mapEntity);
			
			
			//LER O ARQUIVO
			Scanner scanner = new Scanner(inputStream, "UTF-8");
			scanner.useDelimiter(System.lineSeparator());
			scanner.close();
			
			List<Route> routes = new ArrayList<Route>();
			Long id = new Long(0);
			while (scanner.hasNext()) {
				id++;
				String[] campos = scanner.next().split(" ");
				
				Spot source = new Spot(null, campos[0]);
				Spot destination = new Spot(null, campos[1]);
				int weight = Integer.parseInt(campos[2]);
				
				Route route = new Route(null, spotService.save(source), spotService.save(destination), weight);
				routes.add(route);
				
			}
	
			routeService.save(routes, mapEntity);
			
		return true;
	}

}

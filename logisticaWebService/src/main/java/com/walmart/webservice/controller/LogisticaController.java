package com.walmart.webservice.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.walmart.service.api.CostService;
import com.walmart.service.api.MapService;
import com.walmart.service.api.PathService;
import com.walmart.service.api.RouteService;
import com.walmart.service.api.SpotService;
import com.walmart.service.model.Route;
import com.walmart.service.model.Spot;
import com.walmart.service.util.Util;

@RestController
public class LogisticaController {
	
	@Autowired
	private PathService pathService;
	
	@Autowired
	private CostService costService;
	
	@Autowired
	private MapService mapService;
	
	@RequestMapping(value="/mapa/{map}/origem/{source}/destino/{destination}/autonomia/{autonomyCar}/valorLitro/{fuelValue}",method = RequestMethod.GET)
	public String percusoMenorCusto(@PathVariable String map,
									@PathVariable String source, 
									@PathVariable String destination,
									@PathVariable String autonomyCar,
									@PathVariable String fuelValue) {
		
		LinkedList<Spot> path = pathService.minimalDistance(map, source, destination);
		
		int distance = pathService.getDistance(destination);
		BigDecimal value = Util.formataStringTOValor(fuelValue, "###,##0.00");
		int autonomy = Integer.parseInt(autonomyCar);
		BigDecimal cost = costService.rate(distance, autonomy, value);
		
		return formatResult(path, cost);
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String upload(MultipartFile file,
									   String nameMap,
									   HttpServletResponse response) {             		
		try {
			mapService.save(file.getInputStream(), nameMap);
		} catch (IOException e) {
			e.printStackTrace();
			return "Falhou";
		}

		return "Arquivo " + file.getOriginalFilename() + " salvo";
 
	}
	
	private String formatResult(LinkedList<Spot> path, BigDecimal cost) {
		String result = "A rota ";
		
		for (Spot spot : path) {
			result+= spot.getName() + " ";
		}
		
		result+= "com o custo de " + Util.formataValorTOReal(cost, "R$ ###,##0.00");
		return result;
	}
	
}

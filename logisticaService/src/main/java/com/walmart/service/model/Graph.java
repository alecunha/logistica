package com.walmart.service.model;

import java.util.List;

public class Graph {
	private final List<Route> routes;

	public Graph(List<Route> routes) {
		this.routes = routes;
	}

	public List<Route> getRoutes() {
		return routes;
	}
}
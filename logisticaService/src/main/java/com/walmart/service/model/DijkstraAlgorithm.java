package com.walmart.service.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DijkstraAlgorithm {

	private final List<Route> routes;
	private Set<Spot> settledNodes;
	private Set<Spot> unSettledNodes;
	private Map<Spot, Spot> predecessors;
	private Map<Spot, Integer> distance;

	public DijkstraAlgorithm(Graph graph) {
		this.routes = new ArrayList<Route>(graph.getRoutes());
	}

	public void execute(Spot source) {
		settledNodes = new HashSet<Spot>();
		unSettledNodes = new HashSet<Spot>();
		distance = new HashMap<Spot, Integer>();
		predecessors = new HashMap<Spot, Spot>();
		distance.put(source, 0);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			Spot node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Spot node) {
		List<Spot> adjacentNodes = getNeighbors(node);
		for (Spot target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
				distance.put(target, getShortestDistance(node) + getDistance(node, target));
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}

	}

	private int getDistance(Spot node, Spot target) {
		for (Route route : routes) {
			if (route.getSource().equals(node) && route.getDestination().equals(target)) {
				return route.getWeight();
			}
		}
		throw new RuntimeException("Should not happen");
	}

	private List<Spot> getNeighbors(Spot node) {
		List<Spot> neighbors = new ArrayList<Spot>();
		for (Route route : routes) {
			if (route.getSource().equals(node) && !isSettled(route.getDestination())) {
				neighbors.add(route.getDestination());
			}
		}
		return neighbors;
	}

	private Spot getMinimum(Set<Spot> vertexes) {
		Spot minimum = null;
		for (Spot spot : vertexes) {
			if (minimum == null) {
				minimum = spot;
			} else {
				if (getShortestDistance(spot) < getShortestDistance(minimum)) {
					minimum = spot;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Spot spot) {
		return settledNodes.contains(spot);
	}

	private int getShortestDistance(Spot destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	public LinkedList<Spot> getPath(Spot target) {
		LinkedList<Spot> path = new LinkedList<Spot>();
		Spot step = target;

		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}

		Collections.reverse(path);
		return path;
	}
	
	public int getDistance(Spot target) {
		return distance.get(target);
	}

}

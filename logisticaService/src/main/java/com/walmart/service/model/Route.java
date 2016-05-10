package com.walmart.service.model;

public class Route {
	private final Long id;
	private final Spot source;
	private final Spot destination;
	private final int weight;

	public Route(Long id, Spot source, Spot destination, int weight) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}

	public Long getId() {
		return id;
	}

	public Spot getDestination() {
		return destination;
	}

	public Spot getSource() {
		return source;
	}

	public int getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return source + " " + destination;
	}

}
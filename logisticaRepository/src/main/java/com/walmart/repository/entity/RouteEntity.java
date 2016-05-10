package com.walmart.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tbl_route")
public class RouteEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="source_spot_id", nullable=false, updatable=false)
	private SpotEntity source;
	
	@OneToOne
	@JoinColumn(name="destination_spot_id", nullable=false, updatable=false)
	private SpotEntity destination;
	
	@Column(nullable=false)
	private int weight;
	
	@ManyToOne
	@JoinColumn(name="map_id", nullable=false)
	private MapEntity map;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SpotEntity getSource() {
		return source;
	}

	public void setSource(SpotEntity source) {
		this.source = source;
	}

	public SpotEntity getDestination() {
		return destination;
	}

	public void setDestination(SpotEntity destination) {
		this.destination = destination;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public MapEntity getMap() {
		return map;
	}

	public void setMap(MapEntity map) {
		this.map = map;
	}
}

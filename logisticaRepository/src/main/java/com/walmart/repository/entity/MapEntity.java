package com.walmart.repository.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tbl_map")
public class MapEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	
	@Column(unique=true, nullable=false)
	private String name;
	
	@OneToMany
	private List<SpotEntity> path;
	
	private int dintace;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SpotEntity> getPath() {
		return path;
	}

	public void setPath(List<SpotEntity> path) {
		this.path = path;
	}
	
	public int getDintace() {
		return dintace;
	}

	public void setDintace(int dintace) {
		this.dintace = dintace;
	}
}

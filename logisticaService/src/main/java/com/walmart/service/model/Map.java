package com.walmart.service.model;

import java.io.Serializable;

public class Map implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String nameMap;

	public Map(Long id, String nameMap) {
		super();
		this.id = id;
		this.nameMap = nameMap;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameMap() {
		return nameMap;
	}

	public void setNameMap(String nameMap) {
		this.nameMap = nameMap;
	}
	
	
}

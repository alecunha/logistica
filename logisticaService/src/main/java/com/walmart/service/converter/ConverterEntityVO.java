package com.walmart.service.converter;

public abstract class ConverterEntityVO<E,V> {
	
	public abstract V converterTOVO(E entity);
	
	
	public abstract E converterTOEntity(V vo);

}

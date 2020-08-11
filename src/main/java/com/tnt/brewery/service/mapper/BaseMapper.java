package com.tnt.brewery.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;

//@Mapper(componentModel = "spring")
public interface BaseMapper<D, E> {

	  public D toDto(E entity);
	  
	  public E fromDto(D dto);
	  
	  default List<D> toDtos(List<E> entitties) { return
	  entitties.stream().map(this::toDto).collect(Collectors.toList()); }
	  
	  default List<E> fromDtos(List<D> dtos) { return
	  dtos.stream().map(this::fromDto).collect(Collectors.toList()); }
	 
}

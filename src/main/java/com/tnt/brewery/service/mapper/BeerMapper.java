package com.tnt.brewery.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import com.tnt.brewery.domain.Beer;
import com.tnt.brewery.model.BeerDto;

@Mapper(componentModel = "spring")
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper extends BaseMapper<BeerDto, Beer>  {

	@Override
	public BeerDto toDto(Beer entity);

	@Override
	public Beer fromDto(BeerDto dto);
	
	public BeerDto toDtoWithInventory(Beer entity);
	
	default List<BeerDto> toDtosWithInventory(List<Beer> entitties) {
		return entitties.stream().map(this::toDtoWithInventory).collect(Collectors.toList());
	}
}

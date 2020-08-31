package com.tnt.brewery.service.mapper;

import org.mapstruct.Mapper;

import com.tnt.brewery.domain.Beer;
import com.tnt.brewery.model.BeerDto;

@Mapper(componentModel = "spring")
public interface BeerMapper extends BaseMapper<BeerDto, Beer>  {

	@Override
	public BeerDto toDto(Beer entity);

	@Override
	public Beer fromDto(BeerDto dto);
}

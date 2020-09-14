package com.tnt.brewery.service.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.tnt.brewery.domain.Beer;
import com.tnt.brewery.model.BeerDto;
import com.tnt.brewery.service.inventory.InventoryServiceRestTemplateImpl;

public class BeerMapperDecorator implements BeerMapper {

	@Autowired
	private BeerMapper beerMapper;

	@Autowired
	private InventoryServiceRestTemplateImpl inventoryServiceRestTemplateImpl;

	@Override
	public BeerDto toDto(Beer entity) {
		return beerMapper.toDto(entity);
	}

	@Override
	public Beer fromDto(BeerDto dto) {
		return beerMapper.fromDto(dto);
	}

	@Override
	public BeerDto toDtoWithInventory(Beer entity) {
		BeerDto dto = beerMapper.toDto(entity);
		dto.setQuantityOnHand(inventoryServiceRestTemplateImpl.getOnHandInventory(dto.getId()));
		return dto;
	}

}

package com.tnt.brewery.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tnt.brewery.model.BeerDto;

@Service
public interface BeerService {
	
	public List<BeerDto> getBeers();
	
	public BeerDto getBeer(final UUID beerId);

	public BeerDto createBear(final BeerDto beerDto);
	
	public BeerDto updateBear(final UUID beerId, final BeerDto beerDto);

}
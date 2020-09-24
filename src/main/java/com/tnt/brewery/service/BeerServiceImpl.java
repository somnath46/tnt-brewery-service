package com.tnt.brewery.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.tnt.brewery.domain.Beer;
import com.tnt.brewery.exception.InvalidIdentifierException;
import com.tnt.brewery.model.BeerDto;
import com.tnt.brewery.repository.BeerRepository;
import com.tnt.brewery.service.mapper.BeerMapper;

@Service
public class BeerServiceImpl implements BeerService {

	@Autowired
	private BeerRepository beerRepository;

	@Autowired
	private BeerMapper beerMapper;

	@Cacheable(cacheNames = "beerListCache", condition = "#showQuantityOnHand == false")
	@Override
	public List<BeerDto> getBeers(boolean showQuantityOnHand) {
		System.out.println("getBeers: called");
		if (showQuantityOnHand) {
			return beerMapper.toDtosWithInventory(beerRepository.findAll());
		}
		return beerMapper.toDtos(beerRepository.findAll());
	}

	@Cacheable(cacheNames = "beerCache", condition = "#showQuantityOnHand == false")
	@Override
	public BeerDto getBeer(UUID beerId, boolean showQuantityOnHand) {
		System.out.println("getBeer: called");
		Optional<Beer> beer = beerRepository.findById(beerId);
		if (beer.isEmpty()) {
			throw new InvalidIdentifierException(beerId);
		}
		if (showQuantityOnHand) {
			return beerMapper.toDtoWithInventory(beer.get());
		}
		return beerMapper.toDto(beer.get());
	}

	@Override
	public BeerDto createBear(BeerDto beerDto) {
		Beer createdBeer = beerRepository.save(beerMapper.fromDto(beerDto));
		return beerMapper.toDto(createdBeer);
	}

	@Override
	public BeerDto updateBear(UUID beerId, BeerDto beerDto) {
		if (beerId == null) {
			throw new InvalidIdentifierException("beerId is required");
		} else if (beerRepository.findById(beerId).isEmpty()) {
			throw new InvalidIdentifierException(beerId);
		}
		Beer createdBeer = beerRepository.save(beerMapper.fromDto(beerDto));
		return beerMapper.toDto(createdBeer);
	}
}

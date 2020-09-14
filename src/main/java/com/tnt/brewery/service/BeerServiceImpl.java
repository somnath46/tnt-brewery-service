package com.tnt.brewery.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public List<BeerDto> getBeers() {
		return beerMapper.toDtosWithInventory(beerRepository.findAll());
	}

	@Override
	public BeerDto getBeer(UUID beerId) {
		Optional<Beer> beer = beerRepository.findById(beerId);
		if (beer.isEmpty()) {
			throw new InvalidIdentifierException(beerId);
		}
		return beerMapper.toDtoWithInventory(beer.get());
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

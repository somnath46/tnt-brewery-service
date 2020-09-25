package com.tnt.brewery.repository;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnt.brewery.domain.Beer;

@Transactional
public interface BeerRepository extends JpaRepository<Beer, UUID> {

	public Beer findBeerByUpc(String upc);
}

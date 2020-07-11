package com.tnt.brewery.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tnt.brewery.domain.Beer;

public interface BeerRepository extends JpaRepository<Beer, UUID> {

}

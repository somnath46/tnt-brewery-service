package com.tnt.brewery.web.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tnt.brewery.model.BeerDto;
import com.tnt.brewery.service.mapper.BeerMapper;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

//	@Autowired
//	private BeerMapper beerMapper;

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeer(@PathVariable final UUID beerId) {
		// TODO: Implementation
		return new ResponseEntity<BeerDto>(BeerDto.builder().build(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity createBear(@RequestBody @Valid final BeerDto beerDto) {
		// TODO: Implementation
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@PutMapping("/{beerId}")
	public ResponseEntity updateBear(@PathVariable final UUID beerId, @RequestBody final BeerDto beerDto) {
		// TODO: Implementation
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}

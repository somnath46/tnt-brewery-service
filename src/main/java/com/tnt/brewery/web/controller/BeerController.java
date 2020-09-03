package com.tnt.brewery.web.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tnt.brewery.model.BeerDto;
import com.tnt.brewery.service.BeerService;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

	@Autowired
	private BeerService beerService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BeerDto>> getBeers() {
		return new ResponseEntity<>(beerService.getBeers(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{beerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeerDto> getBeer(@PathVariable final UUID beerId) {
		return new ResponseEntity<>(beerService.getBeer(beerId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<BeerDto> createBear(@RequestBody @Valid final BeerDto beerDto) {
		return new ResponseEntity<>(beerService.createBear(beerDto), HttpStatus.CREATED);
	}

	@PutMapping("/{beerId}")
	public ResponseEntity<BeerDto> updateBear(@PathVariable final UUID beerId, @RequestBody final BeerDto beerDto) {
		return new ResponseEntity<>(beerService.updateBear(beerId, beerDto), HttpStatus.OK);
	}
}

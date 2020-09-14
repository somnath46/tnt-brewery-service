package com.tnt.brewery.service.inventory;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.tnt.brewery.service.inventory.model.BeerInventoryDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class InventoryServiceRestTemplateImpl implements InventoryService {

	@Value("${com.tnt.beer.inventory.host}")
	private String beerInventoryServiceHost;
	
	private static final String INVENTORY_API_PATH = "api/v1/beer/{beerId}/inventory";
	
	private RestTemplate restTemplate;

	public InventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
		restTemplate = restTemplateBuilder.build();
	}

	@Override
	public Integer getOnHandInventory(UUID beerId) {
		log.debug("Calling inventory service to get on hand quantity");
		ResponseEntity<List<BeerInventoryDto>> inventoryDtos = restTemplate.exchange(beerInventoryServiceHost + INVENTORY_API_PATH,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<BeerInventoryDto>>() {},
				(Object) beerId);
		
		// make sum
		Integer onHand = Objects.requireNonNull(inventoryDtos.getBody())
				.stream()
				.mapToInt(BeerInventoryDto::getQuantityOnHand)
				.sum();

		log.debug("on hand quantiry: ", onHand);
		return onHand;
	}

	
}

package com.tnt.brewery.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tnt.brewery.model.BeerDto;
import com.tnt.brewery.model.BeerStyleEnum;
import com.tnt.brewery.service.BeerService;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	BeerService beerService;
	
	// TODO: move it in separate class
	private BeerDto createBasicBeerDto() {
		return BeerDto.builder()
			.beerName("Tuborg")
			.beerStyle(BeerStyleEnum.ALE)
			.upc(123456L)
			.price(BigDecimal.valueOf(150.00))
			.quantityOnHand(100).build();
	}

	@Test
	public void testGetBeerById() throws Exception {
		mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID()))
			.andExpect(status().isOk());
	}

	@Test
	public void createBear() throws Exception {
		String reqJsonString = objectMapper.writeValueAsString(createBasicBeerDto());
		mockMvc.perform(post("/api/v1/beer").contentType(MediaType.APPLICATION_JSON).content(reqJsonString))
			.andExpect(status().isCreated());
	}

	@Test
	public void updateBear() throws Exception {
		String reqJsonString = objectMapper.writeValueAsString(BeerDto.builder().build());
		mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID()).contentType(MediaType.APPLICATION_JSON).content(reqJsonString))
			.andExpect(status().isOk());
	}
}

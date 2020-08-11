package com.tnt.brewery.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.UUID;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tnt.brewery.model.BeerDto;
import com.tnt.brewery.model.BeerStyleEnum;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
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
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
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
			.andExpect(status().isNoContent());
	}
}

package com.tnt.brewery.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

	private UUID id;
	@Null
	private Integer version;
	@Null
	private OffsetDateTime createdDate;
	@Null
	private OffsetDateTime lastModificationDate;
	@NotEmpty
	private String beerName;
	@NotNull
	private BeerStyleEnum beerStyle;
	@Positive
	@NotNull
	private Long upc;
	@Positive
	@NotNull
	private BigDecimal price;
	@Positive
	@NotNull
	private Integer quantityOnHand;
	
}

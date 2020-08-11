package com.tnt.brewery.model;

import java.math.BigDecimal;
import java.time.Instant;
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
import lombok.Setter;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

	private UUID id;
	@Null
	private Integer version;
	@Null
	private Instant createdDate;
	@Null
	private Instant lastModificationDate;
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

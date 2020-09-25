package com.tnt.brewery.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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
public class BeerDto implements Serializable {

	private static final long serialVersionUID = -6327385686981920041L;
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
	private String upc;
	@Positive
	@NotNull
	private BigDecimal price;
	@Positive
	@NotNull
	@JsonInclude(content = Include.NON_NULL, value = Include.NON_NULL)
	private Integer quantityOnHand;
	
}

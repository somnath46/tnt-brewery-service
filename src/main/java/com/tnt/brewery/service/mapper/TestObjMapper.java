package com.tnt.brewery.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.tnt.brewery.domain.TestObj;
import com.tnt.brewery.model.TestObjDto;

@Mapper(componentModel = "spring")
public interface TestObjMapper {

	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	public TestObjDto toDto(TestObj testObj);

	@Mapping(source = "id", target = "id")
	@Mapping(source = "name", target = "name")
	public TestObj fromDto(TestObjDto testObjDto);
}

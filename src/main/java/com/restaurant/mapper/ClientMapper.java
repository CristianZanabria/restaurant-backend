package com.restaurant.mapper;

import com.restaurant.config.MapperCentralConfig;
import com.restaurant.dto.ClientDTO;
import com.restaurant.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperCentralConfig.class)
public interface ClientMapper {

    @Mapping(source = "id", target = "idClient")
    @Mapping(source = "name", target = "nameOfClient")
    @Mapping(source = "phone", target = "phoneNumber")
    @Mapping(source = "email", target = "emailClient")

    ClientDTO toDTO(Client entity);

    @Mapping(source = "idClient", target = "id")
    @Mapping(source = "nameOfClient", target = "name")
    @Mapping(source = "phoneNumber", target = "phone")
    @Mapping(source = "emailClient", target = "email")
    Client toEntity(ClientDTO dto);
}

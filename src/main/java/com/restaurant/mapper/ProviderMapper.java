package com.restaurant.mapper;

import com.restaurant.config.MapperCentralConfig;
import com.restaurant.dto.ProviderDTO;
import com.restaurant.model.Provider;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperCentralConfig.class)
public interface ProviderMapper {
    @Mapping(source = "id", target = "idProvider")
    @Mapping(source = "name", target = "nameProvider")
    @Mapping(source = "phone", target = "phoneProvider")
    @Mapping(source = "email", target = "emailProvider")
    ProviderDTO toDTO(Provider entity);

    @Mapping(source = "idProvider", target = "id")
    @Mapping(source = "nameProvider", target = "name")
    @Mapping(source = "phoneProvider", target = "phone")
    @Mapping(source = "emailProvider", target = "email")
    Provider toEntity(ProviderDTO dto);
}

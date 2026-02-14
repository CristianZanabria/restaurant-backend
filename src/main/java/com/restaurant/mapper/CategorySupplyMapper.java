package com.restaurant.mapper;

import com.restaurant.config.MapperCentralConfig;
import com.restaurant.dto.CategorySupplyDTO;
import com.restaurant.model.CategorySupply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperCentralConfig.class)
public interface CategorySupplyMapper {

    @Mapping(source = "id", target = "idCategory")
    @Mapping(source = "name", target = "nameofCategory")
    CategorySupplyDTO toDTO(CategorySupply entity);

    @Mapping(source = "idCategory", target = "id")
    @Mapping(source = "nameofCategory", target = "name")
    CategorySupply toEntity(CategorySupplyDTO dto);
}

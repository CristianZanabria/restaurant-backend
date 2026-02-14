package com.restaurant.mapper;

import com.restaurant.config.MapperCentralConfig;
import com.restaurant.dto.CategorySupplyDTO;
import com.restaurant.dto.RoleDTO;
import com.restaurant.model.CategorySupply;
import com.restaurant.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperCentralConfig.class)
public interface RoleMapper {


    @Mapping(source = "id", target = "idRole")
    @Mapping(source = "name", target = "nameOfRole")
    RoleDTO toDTO(Role entity);

    @Mapping(source = "idRole", target = "id")
    @Mapping(source = "nameOfRole", target = "name")
    Role toEntity(RoleDTO dto);
}

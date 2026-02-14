package com.restaurant.mapper;

import com.restaurant.config.MapperCentralConfig;
import com.restaurant.dto.WasteDTO;
import com.restaurant.model.Waste;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperCentralConfig.class)
public interface WasteMapper {
    @Mapping(source = "id", target = "idWaste")
    @Mapping(source = "date", target = "dateWaste")
    @Mapping(source = "supply.idSupply", target = "idSupplyWaste")
    @Mapping(source = "quantity", target = "quantityWaste")
    @Mapping(source = "reason", target = "reasonWaste")
    WasteDTO toDTO(Waste entity);

    @Mapping(source = "idWaste", target = "id")
    @Mapping(source = "dateWaste", target = "date")
    @Mapping(source = "idSupplyWaste", target = "supply.idSupply")
    @Mapping(source = "quantityWaste", target = "quantity")
    @Mapping(source = "reasonWaste", target = "reason")
    Waste toEntity(WasteDTO dto);
}

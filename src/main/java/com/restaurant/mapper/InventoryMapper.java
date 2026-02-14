package com.restaurant.mapper;

import com.restaurant.config.MapperCentralConfig;
import com.restaurant.dto.InventoryDTO;
import com.restaurant.model.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperCentralConfig.class)
public interface InventoryMapper {
    @Mapping(source = "id", target = "idInventary")
    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "supply.idSupply", target = "supplyId")
    @Mapping(source = "supply.name", target = "supplyName") // cambia "nombre" si tu campo se llama distinto
    @Mapping(source = "stockInicial", target = "stockInicial")
    @Mapping(source = "stockFinal", target = "stockFinal")
    InventoryDTO toDTO(Inventory entity);

    @Mapping(source = "idInventary", target = "id")
    @Mapping(source = "fecha", target = "fecha")
    @Mapping(source = "supplyId", target = "supply.idSupply")
    @Mapping(source = "stockInicial", target = "stockInicial")
    @Mapping(source = "stockFinal", target = "stockFinal")
    Inventory toEntity(InventoryDTO dto);
}

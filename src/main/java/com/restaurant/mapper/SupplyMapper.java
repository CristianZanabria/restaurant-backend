package com.restaurant.mapper;

import com.restaurant.config.MapperCentralConfig;
import com.restaurant.dto.SupplyDTO;
import com.restaurant.model.CategorySupply;
import com.restaurant.model.Supply;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperCentralConfig.class)
public interface SupplyMapper {


    @Mapping(source = "idSupply", target = "idSupplyDto")
    @Mapping(source = "categorySupply.id", target = "idCategory")
    @Mapping(source = "name", target = "nameSupply")
    @Mapping(source = "unit", target = "unitSupply")
    @Mapping(source = "usefulLive", target = "usefulLiSupply")
    SupplyDTO toDTO(Supply entity);

    @Mapping(source = "idSupplyDto", target = "idSupply")
    @Mapping(source = "idCategory", target = "categorySupply.id")
    @Mapping(source = "nameSupply", target = "name")
    @Mapping(source = "unitSupply", target = "unit")
    @Mapping(source = "usefulLiSupply", target = "usefulLive")
    Supply toEntity(SupplyDTO dto);

    //  conversión automática FK
    default CategorySupply map(Integer idCategory) {
        if (idCategory == null) return null;
        CategorySupply c = new CategorySupply();
        c.setId(idCategory);
        return c;
    }
}

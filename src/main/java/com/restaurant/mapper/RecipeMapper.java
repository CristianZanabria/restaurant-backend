package com.restaurant.mapper;

import com.restaurant.config.MapperCentralConfig;
import com.restaurant.dto.RecipeDTO;
import com.restaurant.model.Recipe;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperCentralConfig.class)
public interface RecipeMapper {
    @Mapping(source = "id", target = "idRecipe")
    @Mapping(source = "dish.id", target = "dishRecipe")
    @Mapping(source = "supply.idSupply", target = "supplyRecipe")
    @Mapping(source = "quantity", target = "quantityRecipe")
    RecipeDTO toDTO(Recipe entity);

    @Mapping(source = "idRecipe", target = "id")
    @Mapping(source = "dishRecipe", target = "dish.id")
    @Mapping(source = "supplyRecipe", target = "supply.idSupply")
    @Mapping(source = "quantityRecipe", target = "quantity")
    Recipe toEntity(RecipeDTO dto);

}

package com.restaurant.mapper;

import com.restaurant.config.MapperCentralConfig;
import com.restaurant.dto.CategoryDishDTO;
import com.restaurant.model.CategoryDish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperCentralConfig.class)
public interface CategoryDishMapper {

    @Mapping(source = "id", target = "idCategoryDish")
    @Mapping(source = "name", target = "nameCategoryDish")
    CategoryDishDTO toDTO(CategoryDish entity);

    @Mapping(source = "idCategoryDish", target = "id")
    @Mapping(source = "nameCategoryDish", target = "name")
    CategoryDish toEntity(CategoryDishDTO dto);
}

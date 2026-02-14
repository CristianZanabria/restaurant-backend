package com.restaurant.mapper;

import com.restaurant.config.MapperCentralConfig;
import com.restaurant.dto.DishDTO;

import com.restaurant.model.CategoryDish;

import com.restaurant.model.Dish;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
@Mapper(config = MapperCentralConfig.class)
public interface DishMapper {


    @Mapping(source = "id", target = "idDish")
    @Mapping(source = "category", target = "categoryDish")
    @Mapping(source = "name", target = "nameDish")
    @Mapping(source = "price", target = "priceDish")
    DishDTO toDTO(Dish entity);

    @Mapping(source = "idDish", target = "id")
    @Mapping(source = "categoryDish", target = "category")
    @Mapping(source = "nameDish", target = "name")
    @Mapping(source = "priceDish", target = "price")
    Dish toEntity(DishDTO dto);

    //  conversión automática FK
    default CategoryDish map(Integer id) {
        if (id == null) return null;
        CategoryDish c = new CategoryDish();
        c.setId(id);
        return c;
    }
}

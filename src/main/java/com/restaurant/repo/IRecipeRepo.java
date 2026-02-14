package com.restaurant.repo;

import com.restaurant.model.Recipe;

import java.util.List;

public interface IRecipeRepo extends IGenericRepo<Recipe, Integer> {
    List<Recipe> findByDish_Id(Integer idDish);
}

package com.restaurant.service.impl;

import com.restaurant.model.Recipe;
import com.restaurant.repo.IGenericRepo;
import com.restaurant.repo.IRecipeRepo;
import com.restaurant.service.IRecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl extends CRUDImpl<Recipe, Integer> implements IRecipeService {

    private final IRecipeRepo repo;
    @Override
    protected IGenericRepo<Recipe, Integer> getRepo() {
        return repo;
    }
}

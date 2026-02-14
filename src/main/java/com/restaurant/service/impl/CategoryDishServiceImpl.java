package com.restaurant.service.impl;


import com.restaurant.model.CategoryDish;
import com.restaurant.repo.ICategoryDishRepo;
import com.restaurant.repo.IGenericRepo;
import com.restaurant.service.ICategoryDishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CategoryDishServiceImpl extends CRUDImpl<CategoryDish, Integer> implements ICategoryDishService {

    private final ICategoryDishRepo repo;
    @Override
    protected IGenericRepo<CategoryDish, Integer> getRepo() {
        return repo;
    }
}

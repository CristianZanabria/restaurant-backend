package com.restaurant.service.impl;


import com.restaurant.model.Dish;
import com.restaurant.repo.IDishRepo;
import com.restaurant.repo.IGenericRepo;
import com.restaurant.service.IDishService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DishServiceImpl extends CRUDImpl<Dish, Integer> implements IDishService {

    private final IDishRepo repo;
    @Override
    protected IGenericRepo<Dish, Integer> getRepo() {
        return repo;
    }
}

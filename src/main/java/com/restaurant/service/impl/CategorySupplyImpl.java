package com.restaurant.service.impl;

import com.restaurant.model.CategorySupply;
import com.restaurant.repo.ICategorySupplyRepo;
import com.restaurant.repo.IGenericRepo;
import com.restaurant.service.ICategorySupplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategorySupplyImpl extends CRUDImpl<CategorySupply, Integer> implements ICategorySupplyService{

    private final ICategorySupplyRepo repo;

    @Override
    protected IGenericRepo<CategorySupply, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<CategorySupply> findByName(String name) {return repo.findByName(name);}

    @Override
    public List<CategorySupply> findByNameLike(String name) {return repo.findByNameLike("%" + name +"%");}


    @Override
    public List<CategorySupply> getNameSQL(String name) {
        return repo.getNameSQL(name);
    }

    @Override
    public Page<CategorySupply> findPage(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public List<CategorySupply> findAllOrder(String param) {
        Sort.Direction direction = param.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        return repo.findAll(Sort.by(direction, "name"));
    }


}

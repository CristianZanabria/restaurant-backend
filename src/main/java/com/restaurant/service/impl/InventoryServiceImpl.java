package com.restaurant.service.impl;


import com.restaurant.model.Inventory;
import com.restaurant.repo.IGenericRepo;
import com.restaurant.repo.IInventoryRepo;
import com.restaurant.service.IInventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl extends CRUDImpl<Inventory,Integer>implements IInventoryService {
    private final IInventoryRepo repo;

    @Override
    protected IGenericRepo<Inventory, Integer> getRepo() {
        return repo;
    }
}

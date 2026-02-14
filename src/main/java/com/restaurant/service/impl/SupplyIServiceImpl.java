package com.restaurant.service.impl;

import com.restaurant.model.Supply;
import com.restaurant.repo.IGenericRepo;
import com.restaurant.repo.ISupplyRepo;
import com.restaurant.service.ISupplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplyIServiceImpl extends CRUDImpl<Supply, Integer> implements ISupplyService {

    private  final ISupplyRepo repo;
    @Override
    protected IGenericRepo<Supply, Integer> getRepo() {
        return repo;
    }
}

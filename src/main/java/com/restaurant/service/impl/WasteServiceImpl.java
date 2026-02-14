package com.restaurant.service.impl;


import com.restaurant.model.Waste;
import com.restaurant.repo.IGenericRepo;
import com.restaurant.repo.IWasteRepo;
import com.restaurant.service.IWasteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WasteServiceImpl extends CRUDImpl<Waste, Integer> implements IWasteService {

    private final IWasteRepo repo;
    @Override
    protected IGenericRepo<Waste, Integer> getRepo() {
        return repo;
    }
}

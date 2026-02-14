package com.restaurant.service.impl;


import com.restaurant.model.Provider;
import com.restaurant.repo.IGenericRepo;
import com.restaurant.repo.IProviderRepo;
import com.restaurant.service.IProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProviderServiceImpl extends CRUDImpl<Provider, Integer> implements IProviderService {
    private final IProviderRepo repo;
    @Override
    protected IGenericRepo<Provider, Integer> getRepo() {
        return repo;
    }
}

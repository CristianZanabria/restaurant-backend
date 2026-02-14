package com.restaurant.service.impl;

import com.restaurant.model.Client;

import com.restaurant.repo.IClientRepo;
import com.restaurant.repo.IGenericRepo;
import com.restaurant.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientImpl extends CRUDImpl<Client, Integer> implements IClientService {

    private final IClientRepo repo;

    @Override
    protected IGenericRepo<Client, Integer> getRepo() {
        return repo;
    }
}

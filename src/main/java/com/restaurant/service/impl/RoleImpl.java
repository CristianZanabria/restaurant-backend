package com.restaurant.service.impl;

import com.restaurant.model.Role;

import com.restaurant.repo.IGenericRepo;
import com.restaurant.repo.IRoleRepo;
import com.restaurant.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleImpl extends CRUDImpl<Role, Integer> implements IRoleService {

    private final IRoleRepo repo;
    @Override
    protected IGenericRepo<Role, Integer> getRepo() {
        return repo;
    }
}

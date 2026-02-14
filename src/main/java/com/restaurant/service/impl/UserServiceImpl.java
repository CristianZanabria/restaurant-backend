package com.restaurant.service.impl;

import com.restaurant.model.User;
import com.restaurant.repo.IGenericRepo;
import com.restaurant.repo.IUserRepo;
import com.restaurant.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CRUDImpl<User, Integer> implements IUserService {

    private final IUserRepo repo;
    @Override
    protected IGenericRepo<User, Integer> getRepo() {
        return repo;
    }
}

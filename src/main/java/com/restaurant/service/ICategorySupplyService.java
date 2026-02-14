package com.restaurant.service;

import com.restaurant.model.CategorySupply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ICategorySupplyService extends ICRUD<CategorySupply,Integer>{


    List<CategorySupply> findByName(String name);
    List<CategorySupply> findByNameLike(String name);

    List<CategorySupply> getNameSQL(@Param("name") String name);

    Page<CategorySupply> findPage(Pageable pageable);
    List<CategorySupply> findAllOrder(String param);
}

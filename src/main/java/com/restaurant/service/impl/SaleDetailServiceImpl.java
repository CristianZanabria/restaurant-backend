package com.restaurant.service.impl;


import com.restaurant.model.SaleDetail;
import com.restaurant.repo.IGenericRepo;
import com.restaurant.repo.ISaleDetailRepo;
import com.restaurant.service.ISaleDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SaleDetailServiceImpl extends CRUDImpl<SaleDetail, Integer> implements ISaleDetailService {

    private final ISaleDetailRepo repo;

    @Override
    protected IGenericRepo<SaleDetail, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<SaleDetail> findBySale(Integer idSale) {
        return repo.findBySale_Id(idSale);
    }

    @Override
    public List<SaleDetail> findByDish(Integer idDish) {
        return repo.findByDish_Id(idDish);
    }

    @Override
    public Integer sumQuantityByDishAndDate(
            Integer idDish,
            LocalDateTime start,
            LocalDateTime end
    ) {
        return repo.sumQuantityByDishAndDate(idDish, start, end);
    }

    @Override
    public BigDecimal sumAmountByDishAndDate(
            Integer idDish,
            LocalDateTime start,
            LocalDateTime end
    ) {
        return repo.sumAmountByDishAndDate(idDish, start, end);
    }
}
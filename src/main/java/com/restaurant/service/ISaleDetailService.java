package com.restaurant.service;

import com.restaurant.model.BuyDetail;
import com.restaurant.model.SaleDetail;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ISaleDetailService extends ICRUD<SaleDetail, Integer> {

    List<SaleDetail> findBySale(Integer idSale);

    List<SaleDetail> findByDish(Integer idDish);

    Integer sumQuantityByDishAndDate(Integer idDish, LocalDateTime start, LocalDateTime end);

    BigDecimal sumAmountByDishAndDate(Integer idDish, LocalDateTime start, LocalDateTime end);

}

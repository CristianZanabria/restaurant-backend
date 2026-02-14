package com.restaurant.service;

import com.restaurant.model.BuyDetail;

import java.time.LocalDateTime;
import java.util.List;

public interface IBuyDetailService extends ICRUD<BuyDetail, Integer>{
    List<BuyDetail> findByBuy(Integer idBuy);

    List<BuyDetail> findBySupply(Integer idSupply);

    Double sumQuantityBySupplyAndDate(Integer idSupply, LocalDateTime start, LocalDateTime end);

    Double sumAmountBySupplyAndDate(Integer idSupply, LocalDateTime start, LocalDateTime end);
}

package com.restaurant.service.impl;

import com.restaurant.model.BuyDetail;
import com.restaurant.repo.IBuyDetailRepo;
import com.restaurant.repo.IGenericRepo;
import com.restaurant.service.IBuyDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyDetailServiceImpl  extends CRUDImpl<BuyDetail, Integer>
        implements IBuyDetailService {

    private final IBuyDetailRepo repo;

    @Override
    protected IGenericRepo<BuyDetail, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<BuyDetail> findByBuy(Integer idBuy) {
        return repo.findByBuy_Id(idBuy);
    }

    @Override
    public List<BuyDetail> findBySupply(Integer idSupply) {
        return repo.findBySupply_IdSupply(idSupply);
    }

    @Override
    public Double sumQuantityBySupplyAndDate(
            Integer idSupply,
            LocalDateTime start,
            LocalDateTime end
    ) {
        return repo.sumQuantityBySupplyAndDate(idSupply, start, end);
    }

    @Override
    public Double sumAmountBySupplyAndDate(
            Integer idSupply,
            LocalDateTime start,
            LocalDateTime end
    ) {
        return repo.sumAmountBySupplyAndDate(idSupply, start, end);
    }
}

package com.restaurant.repo;

import com.restaurant.model.BuyDetail;
import com.restaurant.model.SaleDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDateTime;
import java.util.List;

public interface IBuyDetailRepo extends IGenericRepo<BuyDetail, Integer>{
    // =========================
    // CONSULTAS BÁSICAS
    // =========================

    List<BuyDetail> findByBuy_Id(Integer idBuy);

    List<BuyDetail> findBySupply_IdSupply(Integer idSupply);

    // =========================
    // REPORTES
    // =========================

    @Query("""
        SELECT COALESCE(SUM(d.quantity), 0)
        FROM BuyDetail d
        WHERE d.supply.idSupply = :idSupply
          AND d.buy.dateTime BETWEEN :start AND :end
    """)
    Double sumQuantityBySupplyAndDate(
            @Param("idSupply") Integer idSupply,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("""
        SELECT COALESCE(SUM(d.quantity * d.unitPrice), 0)
        FROM BuyDetail d
        WHERE d.supply.idSupply = :idSupply
          AND d.buy.dateTime BETWEEN :start AND :end
    """)
    Double sumAmountBySupplyAndDate(
            @Param("idSupply") Integer idSupply,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}


package com.restaurant.repo;

import com.restaurant.model.BuyDetail;
import com.restaurant.model.SaleDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ISaleDetailRepo extends IGenericRepo<SaleDetail, Integer> {

    // Básicas
    List<SaleDetail> findBySale_Id(Integer idSale);

    List<SaleDetail> findByDish_Id(Integer idDish);

    // Reportes
    @Query("""
        SELECT COALESCE(SUM(d.quantity), 0)
        FROM SaleDetail d
        WHERE d.dish.id = :idDish
          AND d.sale.dateTime BETWEEN :start AND :end
    """)
    Integer sumQuantityByDishAndDate(
            @Param("idDish") Integer idDish,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("""
        SELECT COALESCE(SUM(d.unitPrice * d.quantity), 0)
        FROM SaleDetail d
        WHERE d.dish.id = :idDish
          AND d.sale.dateTime BETWEEN :start AND :end
    """)
    BigDecimal sumAmountByDishAndDate(
            @Param("idDish") Integer idDish,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}
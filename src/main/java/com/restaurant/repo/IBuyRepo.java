package com.restaurant.repo;

import com.restaurant.model.Buy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface IBuyRepo extends IGenericRepo<Buy, Integer> {

        // Compras por rango de fechas (fecha de compra)
        List<Buy> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);

        // Compras por proveedor
        List<Buy> findByProvider_Id(Integer idProvider);

        // Compras por usuario
        List<Buy> findByUser_Id(Integer idUser);


        // Total comprado en un rango (sum total)
        @Query("""
    SELECT COALESCE(SUM(b.total), 0.00)
    FROM Buy b
    WHERE b.dateTime BETWEEN :start AND :end
""")
        BigDecimal sumTotalBetween(@Param("start") LocalDateTime start,
                                   @Param("end") LocalDateTime end);

        // Compras por proveedor en un rango de fechas
        List<Buy> findByProvider_IdAndDateTimeBetween(
                Integer idProvider,
                LocalDateTime start,
                LocalDateTime end
        );

}

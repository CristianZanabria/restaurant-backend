package com.restaurant.repo;

import com.restaurant.dto.IProcedureDTO;
import com.restaurant.dto.ProcedureDTO;
import com.restaurant.model.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;


public interface ISaleRepo extends IGenericRepo<Sale, Integer>{

    // Ventas por cliente
    List<Sale> findByClient_Id(Integer idClient);

    // Ventas por usuario (mozo)
    List<Sale> findByUser_Id(Integer idUser);

    // Ventas en un rango de fechas
    List<Sale> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);

    // Ventas de un cliente en un rango
    List<Sale> findByClient_IdAndDateTimeBetween(Integer idClient, LocalDateTime start, LocalDateTime end);

    // Total vendido en un rango (suma total)
    // (Si tu IGenericRepo ya extiende JpaRepository, esto funciona)
    @org.springframework.data.jpa.repository.Query("""
        SELECT COALESCE(SUM(s.total), 0)
        FROM Sale s
        WHERE s.dateTime BETWEEN :start AND :end
    """)
    java.math.BigDecimal sumTotalBetween(
            @org.springframework.data.repository.query.Param("start") LocalDateTime start,
            @org.springframework.data.repository.query.Param("end") LocalDateTime end
    );

}

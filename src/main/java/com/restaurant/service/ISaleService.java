package com.restaurant.service;

import com.restaurant.dto.IProcedureDTO;
import com.restaurant.dto.ProcedureDTO;
import com.restaurant.dto.SaleDTO;
import com.restaurant.model.Sale;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface ISaleService extends ICRUD<Sale, Integer> {
    // Registrar venta (calcula totales + actualiza inventario)
    Sale registerSale(SaleDTO dto);

    // Consultas
    List<Sale> findByClient(Integer idClient);

    List<Sale> findByUser(Integer idUser);

    List<Sale> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Sale> findByClientAndDateTimeBetween(Integer idClient, LocalDateTime start, LocalDateTime end);

    // Reporte: total vendido en rango
    BigDecimal sumTotalBetween(LocalDateTime start, LocalDateTime end);
    void deleteSale(Integer idSale);

}

package com.restaurant.service;

import com.restaurant.dto.BuyDTO;
import com.restaurant.model.Buy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface IBuyService extends ICRUD<Buy, Integer> {
    // Registrar compra (cabecera + detalles + inventario)
    Buy registerBuy(BuyDTO dto);

    // Consultas útiles
    List<Buy> findByDateBetween(LocalDateTime start, LocalDateTime end);

    List<Buy> findByProvider(Integer idProvider);

    List<Buy> findByUser(Integer idUser);

    BigDecimal sumTotalBetween(LocalDateTime start, LocalDateTime end);
    List<Buy> findByProviderAndDateTimeBetween(
            Integer idProvider,
            LocalDateTime start,
            LocalDateTime end
    );
}

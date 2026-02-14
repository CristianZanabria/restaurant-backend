package com.restaurant.controller;


import com.restaurant.model.SaleDetail;
import com.restaurant.service.ISaleDetailService;

import lombok.RequiredArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/salesdetails")
@RequiredArgsConstructor
public class SaleDetailController {

    private final ISaleDetailService service;

    //  Detalles por venta
    // GET /sale-details/by-sale/10
    @GetMapping("/by-sale/{idSale}")
    public ResponseEntity<List<SaleDetail>> bySale(@PathVariable Integer idSale) {
        return ResponseEntity.ok(service.findBySale(idSale));
    }

    //  Detalles por plato
    // GET /sale-details/by-dish/5
    @GetMapping("/by-dish/{idDish}")
    public ResponseEntity<List<SaleDetail>> byDish(@PathVariable Integer idDish) {
        return ResponseEntity.ok(service.findByDish(idDish));
    }

    //  Cantidad vendida de un plato en rango
    // GET /sale-details/dish/5/qty?start=2026-02-01T00:00:00&end=2026-02-29T23:59:59
    @GetMapping("/dish/{idDish}/qty")
    public ResponseEntity<Integer> qtyByDishBetween(
            @PathVariable Integer idDish,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    ) {
        Integer qty = service.sumQuantityByDishAndDate(idDish, start, end);
        return ResponseEntity.ok(qty != null ? qty : 0);
    }

    // Monto vendido de un plato en rango
    // GET /sale-details/dish/5/amount?start=2026-02-01T00:00:00&end=2026-02-29T23:59:59
    @GetMapping("/dish/{idDish}/amount")
    public ResponseEntity<BigDecimal> amountByDishBetween(
            @PathVariable Integer idDish,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    ) {
        BigDecimal amount = service.sumAmountByDishAndDate(idDish, start, end);
        return ResponseEntity.ok(amount != null ? amount : BigDecimal.ZERO);
    }
}

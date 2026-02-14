package com.restaurant.controller;

import com.restaurant.model.BuyDetail;
import com.restaurant.service.IBuyDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/buy-details")
@RequiredArgsConstructor
public class BuyDetailController {
    private final IBuyDetailService service;

    // 🔹 Detalles por compra
    // GET /buy-details/by-buy/10
    @GetMapping("/by-buy/{idBuy}")
    public ResponseEntity<List<BuyDetail>> findByBuy(
            @PathVariable Integer idBuy
    ) {
        return ResponseEntity.ok(service.findByBuy(idBuy));
    }

    // 🔹 Detalles por insumo
    // GET /buy-details/by-supply/5
    @GetMapping("/by-supply/{idSupply}")
    public ResponseEntity<List<BuyDetail>> findBySupply(
            @PathVariable Integer idSupply
    ) {
        return ResponseEntity.ok(service.findBySupply(idSupply));
    }

    // 🔹 Cantidad comprada de un insumo en un rango
    // GET /buy-details/quantity?idSupply=5&start=2026-02-01T00:00:00&end=2026-02-28T23:59:59
    @GetMapping("/quantity")
    public ResponseEntity<Double> sumQuantity(
            @RequestParam Integer idSupply,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end
    ) {
        return ResponseEntity.ok(
                service.sumQuantityBySupplyAndDate(idSupply, start, end)
        );
    }

    // 🔹 Monto comprado de un insumo en un rango
    // GET /buy-details/amount?idSupply=5&start=2026-02-01T00:00:00&end=2026-02-28T23:59:59
    @GetMapping("/amount")
    public ResponseEntity<Double> sumAmount(
            @RequestParam Integer idSupply,
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end
    ) {
        return ResponseEntity.ok(
                service.sumAmountBySupplyAndDate(idSupply, start, end)
        );
    }
}
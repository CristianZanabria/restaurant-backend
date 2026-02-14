package com.restaurant.controller;

import com.restaurant.dto.BuyDTO;
import com.restaurant.dto.SaleDTO;
import com.restaurant.mapper.BuyMapper;
import com.restaurant.mapper.SaleMapper;
import com.restaurant.model.Buy;
import com.restaurant.model.Sale;
import com.restaurant.service.IBuyService;
import com.restaurant.service.ISaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buys")
@RequiredArgsConstructor
public class BuyController {
    private final IBuyService service;
    private final BuyMapper mapper;

    //  Registrar compra
    @PostMapping
    public ResponseEntity<BuyDTO> register(@Valid @RequestBody BuyDTO dto) {
        Buy saved = service.registerBuy(dto);
        return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.CREATED);
    }

    //  Listar compras
    @GetMapping
    public ResponseEntity<List<BuyDTO>> readAll() throws Exception {
        List<BuyDTO> list = service.readAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    //  Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<BuyDTO> readById(@PathVariable Integer id) throws Exception {
        Buy buy = service.readById(id);
        return ResponseEntity.ok(mapper.toDto(buy));
    }


    //  Compras por rango de fechas
    // Ejemplo: /buys/by-date?start=2026-02-01T00:00:00&end=2026-02-29T23:59:59
    @GetMapping("/by-date")
    public ResponseEntity<List<BuyDTO>> byDate(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end
    ) {
        List<BuyDTO> list = service.findByDateBetween(start, end)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    //  Total comprado en rango
    // Ejemplo: /buys/total?start=2026-02-01T00:00:00&end=2026-02-29T23:59:59
    @GetMapping("/total")
    public ResponseEntity<BigDecimal> totalBetween(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end
    ) {
        return ResponseEntity.ok(service.sumTotalBetween(start, end));
    }
}
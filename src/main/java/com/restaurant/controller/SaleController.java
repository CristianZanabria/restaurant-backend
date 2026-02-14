package com.restaurant.controller;

import com.restaurant.dto.IProcedureDTO;
import com.restaurant.dto.ProcedureDTO;
import com.restaurant.dto.SaleDTO;
import com.restaurant.mapper.SaleMapper;
import com.restaurant.model.Sale;
import com.restaurant.service.ISaleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {
    private final ISaleService service;
    private final SaleMapper mapper;

    // Registrar venta (calcula totales + inventario)
    @PostMapping
    public ResponseEntity<SaleDTO> register(@Valid @RequestBody SaleDTO dto) {
        Sale saved = service.registerSale(dto);
        return new ResponseEntity<>(mapper.toDto(saved), HttpStatus.CREATED);
    }

    //  Listar ventas
    @GetMapping
    public ResponseEntity<List<SaleDTO>> readAll() throws Exception {
        List<SaleDTO> list = service.readAll()
                .stream()
                .map(mapper::toDto)
                .toList();

        return ResponseEntity.ok(list);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.deleteSale(id);
        return ResponseEntity.noContent().build();
    }

    //  Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> readById(@PathVariable Integer id) throws Exception {
        Sale sale = service.readById(id);
        return ResponseEntity.ok(mapper.toDto(sale));
    }

    //  Ventas por cliente
    @GetMapping("/by-client/{idClient}")
    public ResponseEntity<List<SaleDTO>> byClient(@PathVariable Integer idClient) {
        List<SaleDTO> list = service.findByClient(idClient)
                .stream()
                .map(mapper::toDto)
                .toList();

        return ResponseEntity.ok(list);
    }

    // Ventas por usuario
    @GetMapping("/by-user/{idUser}")
    public ResponseEntity<List<SaleDTO>> byUser(@PathVariable Integer idUser) {
        List<SaleDTO> list = service.findByUser(idUser)
                .stream()
                .map(mapper::toDto)
                .toList();

        return ResponseEntity.ok(list);
    }

    // Ventas entre fechas
    // Ejemplo:
    // /sales/between?start=2026-02-01T00:00:00&end=2026-02-29T23:59:59
    @GetMapping("/between")
    public ResponseEntity<List<SaleDTO>> between(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    ) {
        List<SaleDTO> list = service.findByDateTimeBetween(start, end)
                .stream()
                .map(mapper::toDto)
                .toList();

        return ResponseEntity.ok(list);
    }

    //  Ventas de un cliente entre fechas
    // /sales/client-between?idClient=1&start=...&end=...
    @GetMapping("/client-between")
    public ResponseEntity<List<SaleDTO>> clientBetween(
            @RequestParam Integer idClient,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    ) {
        List<SaleDTO> list = service.findByClientAndDateTimeBetween(idClient, start, end)
                .stream()
                .map(mapper::toDto)
                .toList();

        return ResponseEntity.ok(list);
    }

    //  Total vendido en rango
    // /sales/total?start=...&end=...
    @GetMapping("/total")
    public ResponseEntity<BigDecimal> totalBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end
    ) {
        return ResponseEntity.ok(service.sumTotalBetween(start, end));
    }



}

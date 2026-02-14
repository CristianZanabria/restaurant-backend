package com.restaurant.controller;


import com.restaurant.dto.DishDTO;
import com.restaurant.dto.SupplyDTO;
import com.restaurant.mapper.DishMapper;
import com.restaurant.mapper.SupplyMapper;
import com.restaurant.model.Dish;
import com.restaurant.model.Supply;
import com.restaurant.service.IDishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dish")
@RequiredArgsConstructor
public class DishController {
    private final IDishService service;
    private final DishMapper mapper;

    @GetMapping
    public ResponseEntity<List<DishDTO>> readAll() throws Exception {
        List<DishDTO> list = service.readAll()
                .stream()
                .map(mapper::toDTO)
                .toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishDTO> readById(@PathVariable("id") Integer id) throws Exception {
        DishDTO dto = mapper.toDTO(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DishDTO> create(@Valid @RequestBody DishDTO dto) throws Exception {
        Dish obj = service.save(mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDTO(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishDTO> update(@Valid @RequestBody DishDTO dto,
                                            @PathVariable("id") Integer id) throws Exception {
        Dish obj = service.update(mapper.toEntity(dto), id);
        return new ResponseEntity<>(mapper.toDTO(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<DishDTO>> findPage(Pageable pageable) throws Exception {
        Page<DishDTO> pageSupply = service.getPage(pageable)
                .map(mapper::toDTO);

        return new ResponseEntity<>(pageSupply, HttpStatus.OK);
    }

}


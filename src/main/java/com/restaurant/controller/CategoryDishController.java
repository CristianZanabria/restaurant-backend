package com.restaurant.controller;


import com.restaurant.dto.CategoryDishDTO;

import com.restaurant.mapper.CategoryDishMapper;
import com.restaurant.mapper.RoleMapper;
import com.restaurant.model.CategoryDish;
import com.restaurant.model.Dish;
import com.restaurant.service.ICategoryDishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories/dishes")
@RequiredArgsConstructor
public class CategoryDishController {
    private final ICategoryDishService service;
    private final CategoryDishMapper mapper;

    @GetMapping
    public ResponseEntity<List<CategoryDishDTO>> readAll() throws Exception {
        List<CategoryDishDTO> list = service.readAll()
                .stream()
                .map(mapper::toDTO)
                .toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDishDTO> readById(@PathVariable("id") Integer id) throws Exception {
        CategoryDishDTO dto = mapper.toDTO(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDishDTO> create(@Valid @RequestBody CategoryDishDTO dto) throws Exception {
        CategoryDish obj = service.save(mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDTO(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDishDTO> update(@Valid @RequestBody CategoryDishDTO dto,
                                          @PathVariable("id") Integer id) throws Exception {
        CategoryDish obj = service.update(mapper.toEntity(dto), id);
        return new ResponseEntity<>(mapper.toDTO(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

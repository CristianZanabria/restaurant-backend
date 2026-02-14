package com.restaurant.controller;


import com.restaurant.dto.InventoryDTO;
import com.restaurant.dto.RecipeDTO;
import com.restaurant.mapper.InventoryMapper;
import com.restaurant.mapper.RecipeMapper;
import com.restaurant.model.Inventory;
import com.restaurant.model.Recipe;
import com.restaurant.service.IInventoryService;
import com.restaurant.service.IRecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final IInventoryService service;
    private final InventoryMapper mapper;

    @GetMapping
    public ResponseEntity<List<InventoryDTO>> readAll() throws Exception {
        List<InventoryDTO> list = service.readAll()
                .stream()
                .map(mapper::toDTO)
                .toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> readById(@PathVariable("id") Integer id) throws Exception {
        InventoryDTO dto = mapper.toDTO(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InventoryDTO> create(@Valid @RequestBody InventoryDTO dto) throws Exception {
        Inventory obj = service.save(mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDTO(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> update(@Valid @RequestBody InventoryDTO dto,
                                            @PathVariable("id") Integer id) throws Exception {
        Inventory obj = service.update(mapper.toEntity(dto), id);
        return new ResponseEntity<>(mapper.toDTO(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<InventoryDTO>> findPage(Pageable pageable) throws Exception {
        Page<InventoryDTO> pageSupply = service.getPage(pageable)
                .map(mapper::toDTO);

        return new ResponseEntity<>(pageSupply, HttpStatus.OK);
    }
}

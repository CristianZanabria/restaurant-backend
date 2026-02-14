package com.restaurant.controller;

import com.restaurant.dto.RecipeDTO;
import com.restaurant.mapper.RecipeMapper;
import com.restaurant.model.Recipe;
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
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {

    private final IRecipeService service;
    private final RecipeMapper mapper;

    @GetMapping
    public ResponseEntity<List<RecipeDTO>> readAll() throws Exception {
        List<RecipeDTO> list = service.readAll()
                .stream()
                .map(mapper::toDTO)
                .toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDTO> readById(@PathVariable("id") Integer id) throws Exception {
        RecipeDTO dto = mapper.toDTO(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RecipeDTO> create(@Valid @RequestBody RecipeDTO dto) throws Exception {

        Recipe obj = service.save(mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDTO(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDTO> update(@Valid @RequestBody RecipeDTO dto,
                                          @PathVariable("id") Integer id) throws Exception {
        Recipe obj = service.update(mapper.toEntity(dto), id);
        return new ResponseEntity<>(mapper.toDTO(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<RecipeDTO>> findPage(Pageable pageable) throws Exception {
        Page<RecipeDTO> pageSupply = service.getPage(pageable)
                .map(mapper::toDTO);

        return new ResponseEntity<>(pageSupply, HttpStatus.OK);
    }
}

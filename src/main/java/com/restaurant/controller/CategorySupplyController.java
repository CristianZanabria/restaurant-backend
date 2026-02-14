package com.restaurant.controller;

import com.restaurant.dto.CategorySupplyDTO;
import com.restaurant.mapper.CategorySupplyMapper;
import com.restaurant.model.CategorySupply;
import com.restaurant.service.ICategorySupplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories/supplies")
@RequiredArgsConstructor
public class CategorySupplyController {
    private final ICategorySupplyService service;// = new CategoryService();
    private final CategorySupplyMapper mapper;



    @GetMapping
    public ResponseEntity<List<CategorySupplyDTO>> readAll() throws Exception{
        List<CategorySupplyDTO> list =
                service.readAll().stream()
                        .map(mapper::toDTO)
                        .toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategorySupplyDTO> readById(@PathVariable("id") Integer id) throws Exception{
        CategorySupplyDTO dto = mapper.toDTO(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategorySupplyDTO> create(@Valid @RequestBody CategorySupplyDTO dto) throws Exception {
        CategorySupply obj = service.save(mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDTO(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategorySupplyDTO> update(@Valid @RequestBody CategorySupplyDTO dto, @PathVariable("id") Integer id) throws Exception {
        CategorySupply obj = service.update(mapper.toEntity(dto), id);
        return new ResponseEntity<>(mapper.toDTO(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    ////////////////////////// queries //////////////////////////

    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<CategorySupplyDTO>> findByName(@PathVariable("name") String name){
        List<CategorySupplyDTO> list =
                service.findByName(name).stream()
                        .map(mapper::toDTO)
                        .toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/find/name/like/{name}")
    public ResponseEntity<List<CategorySupplyDTO>> findByNameLike(@PathVariable("name") String name){
        List<CategorySupplyDTO> list =
                service.findByNameLike(name).stream()
                        .map(mapper::toDTO)
                        .toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/get/name/sql/{name}")
    public ResponseEntity<List<CategorySupplyDTO>> getNameSQL(@PathVariable("name") String name){
        List<CategorySupplyDTO> list =
                service.getNameSQL(name).stream()
                        .map(mapper::toDTO)
                        .toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<CategorySupplyDTO>> findPage(Pageable pageable){
        Page<CategorySupplyDTO> pageCategory =
                service.findPage(pageable)
                        .map(mapper::toDTO);

        return new ResponseEntity<>(pageCategory, HttpStatus.OK);
    }

    @GetMapping("/pagination2")
    public ResponseEntity<Page<CategorySupplyDTO>> findPage(
            @RequestParam(name = "p", defaultValue = "0") int page,
            @RequestParam(name = "s", defaultValue = "3") int size
    ){
        PageRequest pageRequest = PageRequest.of(page,size);

        Page<CategorySupplyDTO> pageCategory =
                service.findPage(pageRequest)
                        .map(mapper::toDTO);

        return new ResponseEntity<>(pageCategory, HttpStatus.OK);
    }

    @GetMapping("/order")
    public ResponseEntity<List<CategorySupplyDTO>> findAllOrder(
            @RequestParam(name = "param", defaultValue = "ASC") String param){

        List<CategorySupplyDTO> list =
                service.findAllOrder(param).stream()
                        .map(mapper::toDTO)
                        .toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
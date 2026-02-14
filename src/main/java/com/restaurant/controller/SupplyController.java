package com.restaurant.controller;

import com.restaurant.dto.SupplyDTO;
import com.restaurant.mapper.SupplyMapper;
import com.restaurant.model.Supply;
import com.restaurant.service.ISupplyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplies")
@RequiredArgsConstructor
public class SupplyController {

    private final ISupplyService service;
    private final SupplyMapper mapper;

    @GetMapping
    public ResponseEntity<List<SupplyDTO>> readAll() throws Exception {
        List<SupplyDTO> list = service.readAll()
                .stream()
                .map(mapper::toDTO)
                .toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplyDTO> readById(@PathVariable("id") Integer id) throws Exception {
        SupplyDTO dto = mapper.toDTO(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SupplyDTO> create(@Valid @RequestBody SupplyDTO dto) throws Exception {
        Supply obj = service.save(mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDTO(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplyDTO> update(@Valid @RequestBody SupplyDTO dto,
                                            @PathVariable("id") Integer id) throws Exception {
        Supply obj = service.update(mapper.toEntity(dto), id);
        return new ResponseEntity<>(mapper.toDTO(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<SupplyDTO>> findPage(Pageable pageable) throws Exception {
        Page<SupplyDTO> pageSupply = service.getPage(pageable)
                .map(mapper::toDTO);

        return new ResponseEntity<>(pageSupply, HttpStatus.OK);
    }

}



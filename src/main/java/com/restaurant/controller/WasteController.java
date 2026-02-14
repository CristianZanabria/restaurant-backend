package com.restaurant.controller;


import com.restaurant.dto.WasteDTO;
import com.restaurant.mapper.WasteMapper;
import com.restaurant.model.Waste;
import com.restaurant.service.IWasteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wastes")
@RequiredArgsConstructor
public class WasteController {

    private final IWasteService service;
    private final WasteMapper mapper;

    @GetMapping
    public ResponseEntity<List<WasteDTO>> readAll() throws Exception {
        List<WasteDTO> list = service.readAll()
                .stream()
                .map(mapper::toDTO)
                .toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteDTO> readById(@PathVariable("id") Integer id) throws Exception {
        WasteDTO dto = mapper.toDTO(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WasteDTO> create(@Valid @RequestBody WasteDTO dto) throws Exception {
        Waste obj = service.save(mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDTO(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteDTO> update(@Valid @RequestBody WasteDTO dto,
                                          @PathVariable("id") Integer id) throws Exception {
        Waste obj = service.update(mapper.toEntity(dto), id);
        return new ResponseEntity<>(mapper.toDTO(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<WasteDTO>> findPage(Pageable pageable) throws Exception {
        Page<WasteDTO> pageUser = service.getPage(pageable)
                .map(mapper::toDTO);

        return new ResponseEntity<>(pageUser, HttpStatus.OK);
    }
}

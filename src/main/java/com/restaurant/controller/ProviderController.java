package com.restaurant.controller;

import com.restaurant.dto.ProviderDTO;
import com.restaurant.mapper.ProviderMapper;
import com.restaurant.model.Provider;
import com.restaurant.service.IProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/provider")
@RequiredArgsConstructor
public class ProviderController {
    private final IProviderService service;
    private final ProviderMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProviderDTO>> readAll() throws Exception {
        List<ProviderDTO> list = service.readAll()
                .stream()
                .map(mapper::toDTO)
                .toList();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderDTO> readById(@PathVariable("id") Integer id) throws Exception {
        ProviderDTO dto = mapper.toDTO(service.readById(id));
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProviderDTO> create(@Valid @RequestBody ProviderDTO dto) throws Exception {
        Provider obj = service.save(mapper.toEntity(dto));
        return new ResponseEntity<>(mapper.toDTO(obj), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProviderDTO> update(@Valid @RequestBody ProviderDTO dto,
                                            @PathVariable("id") Integer id) throws Exception {
        Provider obj = service.update(mapper.toEntity(dto), id);
        return new ResponseEntity<>(mapper.toDTO(obj), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<ProviderDTO>> findPage(Pageable pageable) throws Exception {
        Page<ProviderDTO> pageSupply = service.getPage(pageable)
                .map(mapper::toDTO);

        return new ResponseEntity<>(pageSupply, HttpStatus.OK);
    }
}

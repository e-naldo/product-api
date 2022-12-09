package dev.project.product.api.controller;

import dev.project.product.api.dto.ProductGroupCreateDto;
import dev.project.product.api.dto.ProductGroupReadDto;
import dev.project.product.api.dto.ProductGroupUpdateDto;
import dev.project.product.api.service.ProductGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/productGroups")
public class ProductGroupController {

    @Autowired
    private ProductGroupService service;


    @PostMapping
    public ResponseEntity<ProductGroupReadDto> create(@RequestBody ProductGroupCreateDto dto,
                                                      UriComponentsBuilder uriComponentsBuilder) {
        ProductGroupReadDto productGroupReadDto = service.create(dto);
        URI uri = uriComponentsBuilder.path("/api/v1/productGroups/{id}").buildAndExpand((productGroupReadDto.id())).toUri();
        return ResponseEntity.created(uri).body(productGroupReadDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductGroupReadDto> update(@PathVariable Long id,
                                                      @RequestBody ProductGroupUpdateDto dto) {
        if (!id.equals(dto.id())) {
            return ResponseEntity.badRequest().build();
        }
        ProductGroupReadDto productGroupReadDto = service.update(id, dto);
        return ResponseEntity.ok(productGroupReadDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductGroupReadDto>> listAll() {
        List<ProductGroupReadDto> productGroupReadDtoList = service.findAll();
        return ResponseEntity.ok(productGroupReadDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductGroupReadDto> findById(@PathVariable Long id) {
        ProductGroupReadDto dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }


}

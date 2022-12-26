package dev.project.product.api.controller;

import dev.project.product.api.dto.productgroup.ProductGroupCreateDto;
import dev.project.product.api.dto.productgroup.ProductGroupReadDto;
import dev.project.product.api.dto.productgroup.ProductGroupUpdateDto;
import dev.project.product.api.exception.ClientErrorException;
import dev.project.product.api.service.ProductGroupService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ProductGroupReadDto> create(@RequestBody @Valid ProductGroupCreateDto dto,
                                                      UriComponentsBuilder uriComponentsBuilder) {
        ProductGroupReadDto productGroupReadDto = service.create(dto);
        URI uri = uriComponentsBuilder.path("/api/v1/productGroups/{id}").buildAndExpand((productGroupReadDto.id())).toUri();
        return ResponseEntity.created(uri).body(productGroupReadDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductGroupReadDto> update(@PathVariable Long id,
                                                      @RequestBody @Valid ProductGroupUpdateDto dto) {
        if (!id.equals(dto.id())) {
            throw new ClientErrorException("id's não coincidem.");
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

    @GetMapping("/find")
    public ResponseEntity<List<ProductGroupReadDto>> findAllByName(@RequestParam String name) {
        List<ProductGroupReadDto> productGroupReadDtoList = service.findAllByName(name);
        return ResponseEntity.ok(productGroupReadDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLogicalById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }


}

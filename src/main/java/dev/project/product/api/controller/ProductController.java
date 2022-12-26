package dev.project.product.api.controller;

import dev.project.product.api.dto.product.ProductCreateDto;
import dev.project.product.api.dto.product.ProductDetailDto;
import dev.project.product.api.dto.product.ProductQueryDto;
import dev.project.product.api.dto.product.ProductUpdateDto;
import dev.project.product.api.exception.ClientErrorException;
import dev.project.product.api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;


    @PostMapping
    public ResponseEntity<ProductDetailDto> create(@RequestBody @Valid ProductCreateDto dto,
                                                   UriComponentsBuilder uriComponentsBuilder) {
        ProductDetailDto productReadDto = service.create(dto);
        URI uri = uriComponentsBuilder.path("/api/v1/products/{id}").buildAndExpand((productReadDto.id())).toUri();
        return ResponseEntity.created(uri).body(productReadDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDetailDto> update(@PathVariable Long id,
                                                   @RequestBody @Valid ProductUpdateDto dto) {
        if (!id.equals(dto.id())) {
            throw new ClientErrorException("id's não coincidem.");
        }
        ProductDetailDto productReadDto = service.update(id, dto);
        return ResponseEntity.ok(productReadDto);
    }

    @GetMapping
    public ResponseEntity<Page<ProductDetailDto>> listAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        Page<ProductDetailDto> productReadDtoList = service.findAll(pageable);
        return ResponseEntity.ok(productReadDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDto> findById(@PathVariable Long id) {
        ProductDetailDto dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/find")
    public ResponseEntity<Page<ProductDetailDto>> findAllByName(@RequestParam String name,
                                                                Pageable pageable) {
        Page<ProductDetailDto> productReadDtoList = service.findAllByName(name, pageable);
        return ResponseEntity.ok(productReadDtoList);
    }

    @PostMapping("/queryFilter")
    public ResponseEntity<Page<ProductDetailDto>> findByFilter(@RequestBody ProductQueryDto dto,
                                                               Pageable pageable){
        Page<ProductDetailDto> productDtoList = service.findByFilter(dto, pageable);
        return ResponseEntity.ok(productDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLogicalById(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

}

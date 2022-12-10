package dev.project.product.api.controller;

import dev.project.product.api.dto.product.ProductCreateDto;
import dev.project.product.api.dto.product.ProductDetailDto;
import dev.project.product.api.dto.product.ProductUpdateDto;
import dev.project.product.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<ProductDetailDto> create(@RequestBody ProductCreateDto dto,
                                                   UriComponentsBuilder uriComponentsBuilder) {
        ProductDetailDto productReadDto = service.create(dto);
        URI uri = uriComponentsBuilder.path("/api/v1/products/{id}").buildAndExpand((productReadDto.id())).toUri();
        return ResponseEntity.created(uri).body(productReadDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDetailDto> update(@PathVariable Long id,
                                                   @RequestBody ProductUpdateDto dto) {
        if (!id.equals(dto.id())) {
            return ResponseEntity.badRequest().build();
        }
        ProductDetailDto productReadDto = service.update(id, dto);
        return ResponseEntity.ok(productReadDto);
    }

    @GetMapping
    public ResponseEntity<List<ProductDetailDto>> listAll() {
        List<ProductDetailDto> productReadDtoList = service.findAll();
        return ResponseEntity.ok(productReadDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDto> findById(@PathVariable Long id) {
        ProductDetailDto dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/find")
    public ResponseEntity<List<ProductDetailDto>> findAllByName(@RequestParam String name) {
        List<ProductDetailDto> productReadDtoList = service.findAllByName(name);
        return ResponseEntity.ok(productReadDtoList);
    }
}

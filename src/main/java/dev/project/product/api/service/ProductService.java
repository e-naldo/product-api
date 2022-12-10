package dev.project.product.api.service;

import dev.project.product.api.domain.Product;
import dev.project.product.api.dto.product.ProductCreateDto;
import dev.project.product.api.dto.product.ProductDetailDto;
import dev.project.product.api.dto.product.ProductReadDto;
import dev.project.product.api.dto.product.ProductUpdateDto;
import dev.project.product.api.mapper.ProductMapper;
import dev.project.product.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductMapper mapper;


    public ProductDetailDto create(ProductCreateDto productCreateDto) {
        Product product = mapper.toCreateEntity(productCreateDto);
        repository.save(product);
        return mapper.toDetailDto(product);
    }

    public ProductDetailDto update(Long id, ProductUpdateDto dto) {
        Product productFound = repository.findById(id)
                .orElseThrow(RuntimeException::new);

        Product product = mapper.toUpdateEntity(dto, productFound);
        repository.save(product);
        return mapper.toDetailDto(product);
    }

    public List<ProductDetailDto> findAll() {
        List<Product> productList = repository.findAll();
        return mapper.toListDetailDto(productList);
    }

    public ProductDetailDto findById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(RuntimeException::new);
        return mapper.toDetailDto(product);
    }

    public List<ProductDetailDto> findAllByName(String name){
        List<Product> productList = repository.findAllByNameContainingIgnoreCase(name);
        return mapper.toListDetailDto(productList);
    }
}

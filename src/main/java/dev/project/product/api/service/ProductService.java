package dev.project.product.api.service;

import dev.project.product.api.domain.Product;
import dev.project.product.api.dto.product.*;
import dev.project.product.api.exception.ResourceNotFoundException;
import dev.project.product.api.mapper.ProductMapper;
import dev.project.product.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
                .orElseThrow(ResourceNotFoundException::new);

        Product product = mapper.toUpdateEntity(dto, productFound);
        repository.save(product);
        return mapper.toDetailDto(product);
    }

    public Page<ProductDetailDto> findAll(Pageable pageable) {
        Page<Product> productList = repository.findAllByActiveTrue(pageable);
        return mapper.toPageDetailDto(productList);
    }

    public ProductDetailDto findById(Long id) {
        Product product = repository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return mapper.toDetailDto(product);
    }

    public Page<ProductDetailDto> findAllByName(String name, Pageable pageable){
        Page<Product> productList = repository.findAllByNameContainingIgnoreCase(name, pageable);
        return mapper.toListDetailDto(productList);
    }

    public Page<ProductDetailDto> findByFilter(ProductQueryDto dto, Pageable pageable) {
        Page<Product> product = repository.findAllByFilter(
                dto.id(),
                dto.productGroupId(),
                dto.reference(),
                dto.name(),
                dto.active(),
                pageable
        );
        return mapper.toListDetailDto(product);
    }

    public void deleteById(Long id){
        Product product = repository.getReferenceById(id);
        product.inactivate();
        repository.save(product);
    }
}

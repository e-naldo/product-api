package dev.project.product.api.mapper;

import dev.project.product.api.domain.Product;
import dev.project.product.api.dto.product.ProductCreateDto;
import dev.project.product.api.dto.product.ProductDetailDto;
import dev.project.product.api.dto.product.ProductReadDto;
import dev.project.product.api.dto.product.ProductUpdateDto;
import dev.project.product.api.repository.ProductGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    ProductGroupRepository productGroupRepository;

    public Product toCreateEntity(ProductCreateDto dto) {
        Product product = new Product(
                productGroupRepository.findById(dto.productGroupId()).get(),
                dto.reference(),
                dto.name(),
                dto.unity(),
                dto.costPrice());
        return product;
    }

    public ProductReadDto toReadDto(Product product) {
        ProductReadDto dto = new ProductReadDto(
                product.getId(),
                new ProductGroupMapper().toReadDto(product.getProductGroup()),
                product.getReference(),
                product.getName(),
                product.getUnity(),
                product.getSalePrice()
        );
        return dto;
    }

    public Product toUpdateEntity(ProductUpdateDto dto, Product product) {
        product.setProductGroup(productGroupRepository.findById(dto.productGroupId()).get());
        product.setReference(dto.reference());
        product.setName(dto.name());
        product.setUnity(dto.unity());
        product.setCostPrice(dto.costPrice());
        return product;
    }

    public ProductDetailDto toDetailDto(Product product) {
        ProductDetailDto dto = new ProductDetailDto(
                product.getId(),
                new ProductGroupMapper().toReadDto(product.getProductGroup()),
                product.getReference(),
                product.getName(),
                product.getUnity(),
                product.getCostPrice(),
                product.getSalePrice()
        );
        return dto;
    }

    public List<ProductDetailDto> toListDetailDto(List<Product> productGroupList) {
        return productGroupList.stream().map(this::toDetailDto).collect(Collectors.toList());
    }
}

package dev.project.product.api.mapper;

import dev.project.product.api.domain.ProductGroup;
import dev.project.product.api.dto.ProductGroupCreateDto;
import dev.project.product.api.dto.ProductGroupReadDto;
import dev.project.product.api.dto.ProductGroupUpdateDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductGroupMapper {

    public ProductGroup toCreateEntity(ProductGroupCreateDto dto) {
        ProductGroup productGroup = new ProductGroup();
        productGroup.setName(dto.name());
        return productGroup;
    }

    public ProductGroupReadDto toReadDto(ProductGroup productGroup) {
        return new ProductGroupReadDto(productGroup.getId(), productGroup.getName());
    }

    public ProductGroup toUpdateEntity(ProductGroup productGroup, ProductGroupUpdateDto dto) {
        productGroup.setName(dto.name());
        return productGroup;
    }

    public List<ProductGroupReadDto> toListDto(List<ProductGroup> productGroupList) {
        return productGroupList.stream().map(this::toReadDto).collect(Collectors.toList());
    }
}

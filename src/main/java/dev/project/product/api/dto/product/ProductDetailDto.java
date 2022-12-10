package dev.project.product.api.dto.product;

import dev.project.product.api.dto.productgroup.ProductGroupReadDto;

import java.math.BigDecimal;

public record ProductDetailDto(
        Long id,
        ProductGroupReadDto productGroup,
        String reference,
        String name,
        String unity,
        BigDecimal costPrice,
        BigDecimal salePrice
) {
}
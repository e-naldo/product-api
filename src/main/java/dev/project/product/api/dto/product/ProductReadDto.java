package dev.project.product.api.dto.product;

import dev.project.product.api.dto.productgroup.ProductGroupReadDto;

import java.math.BigDecimal;

public record ProductReadDto(
        Long id,
        String productGroup,
        String reference,
        String name,
        String unity,
        BigDecimal salePrice

        // TODO: 16/12/2022 Devolver apenas o name do productGroup
) {
}

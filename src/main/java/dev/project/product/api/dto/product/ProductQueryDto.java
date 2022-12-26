package dev.project.product.api.dto.product;

public record ProductQueryDto(
    Long id,
    Long productGroupId,
    String reference,
    String name,
    Boolean active
){}
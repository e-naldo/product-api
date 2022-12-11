package dev.project.product.api.dto.product;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductCreateDto(
        @NotNull
        @Positive
        Long productGroupId,
        @NotEmpty
        String reference,
        @NotEmpty
        String name,
        @NotEmpty
        String unity,
        @DecimalMin(value = "0.0", inclusive = false)
        @Digits(integer=6, fraction=2)
        BigDecimal costPrice
) {
}

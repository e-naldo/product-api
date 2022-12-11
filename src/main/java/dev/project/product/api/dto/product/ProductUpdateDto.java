package dev.project.product.api.dto.product;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductUpdateDto (
        @NotNull
        @Positive
        Long id,
        @NotNull
        @Positive
        Long productGroupId,
        @NotBlank
        String reference,
        @NotBlank
        String name,
        @NotBlank
        String unity,
        @DecimalMin(value = "0.0", inclusive = false)
        @Digits(integer=6, fraction=2)
        BigDecimal costPrice
) {
}

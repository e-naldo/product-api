package dev.project.product.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductGroupUpdateDto(
        @NotNull @Positive Long id,
        @NotBlank @Size(min = 3, max = 50) String name
) {
}
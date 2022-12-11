package dev.project.product.api.dto.productgroup;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductGroupCreateDto(
        @NotBlank @Size(min = 3, max = 50) String name
) {
}

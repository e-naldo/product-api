package dev.project.product.api.dto;

public record ValidationErrorDto(
        String field,
        String error
) {
}
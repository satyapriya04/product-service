package com.example.product.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Product {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private long productId;

    @NotBlank(message = "Product name is required")
    @Size(max = 100, message = "Name must be under 100 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Name must not contain special characters")
    private String name;

    @Size(max = 255, message = "Description must be under 255 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Description must not contain special characters")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    private double price;

}

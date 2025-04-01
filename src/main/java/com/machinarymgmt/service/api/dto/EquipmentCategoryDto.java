package com.machinarymgmt.service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentCategoryDto {
    private Long id;

    @NotBlank(message = "Category name is required")
    private String categoryName;

    // Add getter explicitly to avoid compilation errors
    public String getCategoryName() {
        return categoryName;
    }
}


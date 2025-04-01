package com.machinarymgmt.service.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentRequestDto {
    @NotNull(message = "Project ID is required")
    private Long projectId;
    
    @NotBlank(message = "Equipment name is required")
    private String equipmentName;
    
    private Long categoryId;
    private Long modelId;
    
    @NotBlank(message = "Asset code is required")
    private String assetCode;
    
    @NotNull(message = "Year of manufacture is required")
    private Integer yearOfManufacture;
}


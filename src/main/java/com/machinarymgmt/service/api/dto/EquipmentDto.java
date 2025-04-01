package com.machinarymgmt.service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDto {
    private Long id;
    private Long projectId;
    private String equipmentName;
    private Long categoryId;
    private Long modelId;
    private String assetCode;
    private Integer yearOfManufacture;
    private ProjectDto project;
    private EquipmentCategoryDto category;
    private ModelDto model;
}


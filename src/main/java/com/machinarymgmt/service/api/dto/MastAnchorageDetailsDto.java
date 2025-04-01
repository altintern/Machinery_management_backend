package com.machinarymgmt.service.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MastAnchorageDetailsDto {
    private Long id;
    
    @NotNull(message = "Project ID is required")
    private Long projectId;
    
    @NotNull(message = "Equipment ID is required")
    private Long equipmentId;
    
    private String status;
    private String location;
    private Integer mastAvailableAtSite;
    private Integer mastFixedAtSite;
    private Integer mastIdleAtSite;
    private Integer totalMastRequirement;
    private Integer anchorageAtSite;
    private Integer anchorageFixedAtSite;
    private Integer anchorageIdleAtSite;
    private Integer totalAnchorageRequirement;
    private String presentHeightOfHoist;
    private String presentBuildingHeight;
    private String totalBuildingHeight;
    private String remarks;
    
    private ProjectDto project;
    private EquipmentDto equipment;
}


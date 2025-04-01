package com.machinarymgmt.service.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentUtilizationDto {
    private Long id;
    
    @NotNull(message = "Equipment ID is required")
    private Long equipmentId;
    
    @NotNull(message = "Project ID is required")
    private Long projectId;
    
    @NotNull(message = "Month is required")
    private Integer month;
    
    @NotNull(message = "Year is required")
    private Integer year;
    
    private BigDecimal targetedHours;
    private BigDecimal startingHoursKms;
    private BigDecimal closingHoursKms;
    private BigDecimal breakdownHours;
    private BigDecimal dieselConsumedLtrs;
    private BigDecimal avgFuelConsumption;
    private BigDecimal availabilityHours;
    private BigDecimal utilizationPercentage;
    private String remarks;
    
    private EquipmentDto equipment;
    private ProjectDto project;
}


package com.machinarymgmt.service.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialsConsumptionTransactionDto {
    private Long id;
    
    @NotNull(message = "Project ID is required")
    private Long projectId;
    
    @NotNull(message = "Issue date is required")
    private LocalDate issueDate;
    
    @NotNull(message = "Equipment ID is required")
    private Long equipmentId;
    
    @NotNull(message = "Item ID is required")
    private Long itemId;
    
    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Integer quantity;
    
    @NotNull(message = "Cost per unit is required")
    @Positive(message = "Cost per unit must be positive")
    private BigDecimal costPerUnit;
    
    @NotNull(message = "Total cost is required")
    private BigDecimal totalCost;
    
    private String remarks;
    private LocalDateTime createdAt;
    
    private ProjectDto project;
    private EquipmentDto equipment;
    private ItemDto item;
}


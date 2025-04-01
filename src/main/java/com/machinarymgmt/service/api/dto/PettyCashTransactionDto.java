package com.machinarymgmt.service.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PettyCashTransactionDto {
    private Long id;
    
    @NotNull(message = "Project ID is required")
    private Long projectId;
    
    @NotNull(message = "Report date is required")
    private LocalDate reportDate;
    
    @NotNull(message = "Equipment ID is required")
    private Long equipmentId;
    
    @NotNull(message = "Item ID is required")
    private Long itemId;
    
    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    private Integer quantity;
    
    @NotNull(message = "Rate is required")
    @Positive(message = "Rate must be positive")
    private BigDecimal rate;
    
    @NotNull(message = "Cumulative total amount is required")
    private BigDecimal cumulativeTotalAmount;
    
    @NotNull(message = "Amount spent is required")
    @Positive(message = "Amount spent must be positive")
    private BigDecimal amountSpent;
    
    private String purposeJustification;
    private String remarks;
    
    private ProjectDto project;
    private EquipmentDto equipment;
    private ItemDto item;
}


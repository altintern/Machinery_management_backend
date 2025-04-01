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
public class StockStatementDto {
    private Long id;
    
    @NotNull(message = "Project ID is required")
    private Long projectId;
    
    @NotNull(message = "Item ID is required")
    private Long itemId;
    
    @NotNull(message = "Equipment ID is required")
    private Long equipmentId;
    
    @NotNull(message = "Month is required")
    private Integer month;
    
    @NotNull(message = "Year is required")
    private Integer year;
    
    @NotNull(message = "Balance is required")
    private BigDecimal balance;
    
    @NotNull(message = "Landed rate is required")
    @Positive(message = "Landed rate must be positive")
    private BigDecimal landedRate;
    
    @NotNull(message = "Landed value is required")
    private BigDecimal landedValue;
    
    private LocalDate lastReceiptOn;
    private LocalDate lastIssueOn;
    
    private ProjectDto project;
    private ItemDto item;
    private EquipmentDto equipment;
}


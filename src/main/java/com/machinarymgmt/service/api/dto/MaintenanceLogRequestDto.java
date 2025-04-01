package com.machinarymgmt.service.api.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceLogRequestDto {
    @NotNull(message = "Equipment ID is required")
    private Long equipmentId;
    
    @NotNull(message = "Date is required")
    private LocalDate date;
    
    @NotNull(message = "Start reading is required")
    private Integer startReading;
    
    @NotNull(message = "Close reading is required")
    private Integer closeReading;
    
    private Integer serviceHours;
    private LocalDate serviceDate;
    private Integer balanceForService;
    private String purposeActivities;
    private String typeOfMaintenance;
    private String breakdownSynopsis;
    
    @NotNull(message = "Operator name is required")
    private String operatorName;
    
    private String operatorSignature;
    private String maintenanceSignature;
    private String feedback;
    private String remarks;
    private List<MaintenancePartUsedRequestDto> partsUsed;
    private MaintenanceReadingRequestDto readings;
}


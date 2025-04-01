package com.machinarymgmt.service.api.dto;

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
public class MaintenanceLogDto {
    private Long id;
    private Long equipmentId;
    private LocalDate date;
    private Integer startReading;
    private Integer closeReading;
    private Integer serviceHours;
    private LocalDate serviceDate;
    private Integer balanceForService;
    private String purposeActivities;
    private String typeOfMaintenance;
    private String breakdownSynopsis;
    private String operatorName;
    private String operatorSignature;
    private String maintenanceSignature;
    private String feedback;
    private String remarks;
    private EquipmentDto equipment;
    private List<MaintenancePartUsedDto> partsUsed;
    private MaintenanceReadingDto readings;
}


package com.machinarymgmt.service.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidentReportDto {
    private Long id;
    
    @NotNull(message = "Equipment ID is required")
    private Long equipmentId;
    
    @NotNull(message = "Project ID is required")
    private Long projectId;
    
    @NotNull(message = "Type ID is required")
    private Long typeId;
    
    @NotBlank(message = "Incident details are required")
    private String incidentDetails;
    
    @NotNull(message = "Incident date is required")
    private LocalDate incidentDate;
    
    @NotBlank(message = "Action taken is required")
    private String actionTaken;
    
    private LocalDate estimatedCompletionDate;
    private LocalDate closedDate;
    
    @NotNull(message = "Status ID is required")
    private Long statusId;
    
    private EquipmentDto equipment;
    private ProjectDto project;
    private IncidentTypeDto type;
    private StatusEntityDto status;
}


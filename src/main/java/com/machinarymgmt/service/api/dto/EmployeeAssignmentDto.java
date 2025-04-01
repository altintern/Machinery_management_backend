package com.machinarymgmt.service.api.dto;

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
public class EmployeeAssignmentDto {
    private Long id;
    
    @NotNull(message = "Employee ID is required")
    private Long employeeId;
    
    @NotNull(message = "Project ID is required")
    private Long projectId;
    
    @NotNull(message = "Equipment ID is required")
    private Long equipmentId;
    
    private LocalDate joiningDate;
    
    private EmployeeDto employee;
    private ProjectDto project;
    private EquipmentDto equipment;
}


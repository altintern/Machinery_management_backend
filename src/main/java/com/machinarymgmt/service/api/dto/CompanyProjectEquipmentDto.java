package com.machinarymgmt.service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyProjectEquipmentDto {
    private Long id;

    @NotNull(message = "Company ID is required")
    private Long companyId;

    @NotNull(message = "Project ID is required")
    private Long projectId;

    @NotNull(message = "Equipment ID is required")
    private Long equipmentId;

    private CompanyDto company;
    private ProjectDto project;
    private EquipmentDto equipment;

    // Add getters explicitly to avoid compilation errors
    public Long getCompanyId() {
        return companyId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public Long getEquipmentId() {
        return equipmentId;
    }
}


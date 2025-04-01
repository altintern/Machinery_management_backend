package com.machinarymgmt.service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;

    @NotBlank(message = "Employee name is required")
    private String employeeName;

    @NotNull(message = "Designation ID is required")
    private Long designationId;

    @NotNull(message = "Department ID is required")
    private Long deptId;

    private String remarks;

    private DesignationDto designation;
    private DepartmentDto department;

    // Add getters explicitly to avoid compilation errors
    public Long getDesignationId() {
        return designationId;
    }

    public Long getDeptId() {
        return deptId;
    }
}


package com.machinarymgmt.service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OvertimeReportDto {
    private Long id;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "Present days is required")
    @Positive(message = "Present days must be positive")
    private Integer presentDays;

    @NotNull(message = "OT hours is required")
    @Positive(message = "OT hours must be positive")
    private Double otHours;

    private String remarks;
    private LocalDateTime createdAt;

    private EmployeeDto employee;

    // Add getter explicitly to avoid compilation errors
    public Double getOtHours() {
        return otHours;
    }
}


package com.machinarymgmt.service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DesignationDto {
    private Long id;

    @NotBlank(message = "Designation name is required")
    private String designationName;

    // Add getter explicitly to avoid compilation errors
    public String getDesignationName() {
        return designationName;
    }
}


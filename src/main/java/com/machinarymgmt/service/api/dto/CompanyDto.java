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
public class CompanyDto {
    private Long id;

    @NotBlank(message = "Company name is required")
    private String companyName;

    // Add getter explicitly to avoid compilation errors
    public String getCompanyName() {
        return companyName;
    }
}


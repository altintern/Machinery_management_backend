package com.machinarymgmt.service.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncidentTypeDto {
    private Long id;
    
    @NotBlank(message = "Type name is required")
    private String typeName;
}


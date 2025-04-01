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
public class StatusEntityDto {
    private Long id;
    
    @NotBlank(message = "Status name is required")
    private String statusName;
}


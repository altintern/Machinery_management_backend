package com.machinarymgmt.service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceReadingDto {
    private Long id;
    private Double oilPressure;
    private Double engineTemperature;
    private Double airPressure;
    private Double hydraulicTemperature;
    private Double hsdUsed;
    private Double engineOil;
    private Double hydraulicOil;
    private Double gearOil;
    private Double greaseUsed;
}


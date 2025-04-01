package com.machinarymgmt.service.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenancePartUsedDto {
    private Long id;
    private Long itemId;
    private Double quantity;
    private ItemDto item;
}


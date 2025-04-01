package com.machinarymgmt.service.api.dto;

import com.machinarymgmt.service.api.data.model.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private Long id;
    private String itemCode;
    private String itemDescription;
    private String uom;
    private Item.ItemType itemType;
}


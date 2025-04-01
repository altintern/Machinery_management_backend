package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Item;
import com.machinarymgmt.service.api.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ItemMapper extends MachinaryMgmtMapper {
    
    @Mapping(source = "code", target = "itemCode")
    @Mapping(source = "description", target = "itemDescription")
    @Mapping(source = "type", target = "itemType")
    ItemDto toDto(Item item);
    
    List<ItemDto> toDtoList(List<Item> items);
    
    @Mapping(source = "itemCode", target = "code")
    @Mapping(source = "itemDescription", target = "description")
    @Mapping(source = "itemType", target = "type")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "stockStatements", ignore = true)
    @Mapping(target = "partsUsed", ignore = true)
    @Mapping(target = "pettyCashTransactions", ignore = true)
    @Mapping(target = "materialsConsumptions", ignore = true)
    Item toEntity(ItemDto dto);
}


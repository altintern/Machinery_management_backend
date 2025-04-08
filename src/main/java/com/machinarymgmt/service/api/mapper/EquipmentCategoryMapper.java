package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.EquipmentCategory;
import  com.machinarymgmt.service.dto.EquipmentCategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface EquipmentCategoryMapper extends MachinaryMgmtMapper {

    EquipmentCategoryDto toDto(EquipmentCategory category);
    
    List<EquipmentCategoryDto> toDtoList(List<EquipmentCategory> categories);

    EquipmentCategory toEntity(EquipmentCategoryDto dto);
}


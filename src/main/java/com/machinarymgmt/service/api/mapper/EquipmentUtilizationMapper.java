package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.data.model.EquipmentUtilization;
import com.machinarymgmt.service.api.data.model.Project;
import com.machinarymgmt.service.dto.EquipmentUtilizationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {EquipmentMapper.class, ProjectMapper.class}
)
public interface EquipmentUtilizationMapper extends MachinaryMgmtMapper {

    EquipmentUtilizationDto toDto(EquipmentUtilization utilization);
    
    List<EquipmentUtilizationDto> toDtoList(List<EquipmentUtilization> utilizations);

    EquipmentUtilization toEntity(EquipmentUtilizationDto dto);

    void updateEntityFromDto(EquipmentUtilizationDto dto, @MappingTarget EquipmentUtilization utilization);
    
    default EquipmentUtilization fromDtoWithReferences(
            EquipmentUtilizationDto dto,
            Equipment equipment,
            Project project) {
        EquipmentUtilization utilization = toEntity(dto);
        utilization.setEquipment(equipment);
        utilization.setProject(project);
        return utilization;
    }
}


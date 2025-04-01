package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.data.model.MastAnchorageDetails;
import com.machinarymgmt.service.api.data.model.Project;
import com.machinarymgmt.service.api.dto.MastAnchorageDetailsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {ProjectMapper.class, EquipmentMapper.class}
)
public interface MastAnchorageDetailsMapper extends MachinaryMgmtMapper {
    
    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "equipment.id", target = "equipmentId")
    MastAnchorageDetailsDto toDto(MastAnchorageDetails details);
    
    List<MastAnchorageDetailsDto> toDtoList(List<MastAnchorageDetails> details);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    MastAnchorageDetails toEntity(MastAnchorageDetailsDto dto);
    
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    void updateEntityFromDto(MastAnchorageDetailsDto dto, @MappingTarget MastAnchorageDetails details);
    
    default MastAnchorageDetails fromDtoWithReferences(
            MastAnchorageDetailsDto dto,
            Project project,
            Equipment equipment) {
        MastAnchorageDetails details = toEntity(dto);
        details.setProject(project);
        details.setEquipment(equipment);
        return details;
    }
}


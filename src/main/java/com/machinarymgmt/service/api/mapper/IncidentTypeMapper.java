package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.IncidentType;
import com.machinarymgmt.service.api.dto.IncidentTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface IncidentTypeMapper extends MachinaryMgmtMapper {
    
    @Mapping(source = "name", target = "typeName")
    IncidentTypeDto toDto(IncidentType incidentType);
    
    List<IncidentTypeDto> toDtoList(List<IncidentType> incidentTypes);
    
    @Mapping(source = "typeName", target = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "incidents", ignore = true)
    IncidentType toEntity(IncidentTypeDto dto);
    
    @Mapping(source = "typeName", target = "name")
    @Mapping(target = "incidents", ignore = true)
    void updateEntityFromDto(IncidentTypeDto dto, @MappingTarget IncidentType incidentType);
}


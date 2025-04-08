package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.IncidentType;
import com.machinarymgmt.service.dto.IncidentTypeDto;
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

    IncidentTypeDto toDto(IncidentType incidentType);
    
    List<IncidentTypeDto> toDtoList(List<IncidentType> incidentTypes);

    IncidentType toEntity(IncidentTypeDto dto);

    void updateEntityFromDto(IncidentTypeDto dto, @MappingTarget IncidentType incidentType);
}


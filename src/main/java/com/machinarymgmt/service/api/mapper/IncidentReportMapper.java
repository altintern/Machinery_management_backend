package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.*;
import com.machinarymgmt.service.api.dto.IncidentReportDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {EquipmentMapper.class, ProjectMapper.class, IncidentTypeMapper.class, StatusEntityMapper.class}
)
public interface IncidentReportMapper extends MachinaryMgmtMapper {
    
    @Mapping(source = "equipment.id", target = "equipmentId")
    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "type.id", target = "typeId")
    @Mapping(source = "status.id", target = "statusId")
    @Mapping(source = "details", target = "incidentDetails")
    IncidentReportDto toDto(IncidentReport incidentReport);
    
    List<IncidentReportDto> toDtoList(List<IncidentReport> incidentReports);
    
    @Mapping(source = "incidentDetails", target = "details")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "status", ignore = true)
    IncidentReport toEntity(IncidentReportDto dto);
    
    @Mapping(source = "incidentDetails", target = "details")
    @Mapping(target = "equipment", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "status", ignore = true)
    void updateEntityFromDto(IncidentReportDto dto, @MappingTarget IncidentReport incidentReport);
    
    default IncidentReport fromDtoWithReferences(
            IncidentReportDto dto,
            Equipment equipment,
            Project project,
            IncidentType type,
            StatusEntity status) {
        IncidentReport incidentReport = toEntity(dto);
        incidentReport.setEquipment(equipment);
        incidentReport.setProject(project);
        incidentReport.setType(type);
        incidentReport.setStatus(status);
        return incidentReport;
    }
}


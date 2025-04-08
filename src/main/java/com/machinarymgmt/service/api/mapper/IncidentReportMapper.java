package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.*;
import com.machinarymgmt.service.dto.IncidentReportDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {EquipmentMapper.class, ProjectMapper.class, IncidentTypeMapper.class}
)
public interface IncidentReportMapper extends MachinaryMgmtMapper {

    IncidentReportDto toDto(IncidentReport incidentReport);

    List<IncidentReportDto> toDtoList(List<IncidentReport> incidentReports);

    IncidentReport toEntity(IncidentReportDto dto);

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

    // ✅ Custom mapping methods inline
    default OffsetDateTime map(LocalDate date) {
        return date != null ? date.atStartOfDay().atOffset(ZoneOffset.UTC) : null;
    }

    default LocalDate map(OffsetDateTime dateTime) {
        return dateTime != null ? dateTime.toLocalDate() : null;
    }

    default String map(StatusEntity status) {
        return status != null ? status.getName() : null;
    }

    default StatusEntity map(String statusName) {
        if (statusName == null) return null;
        StatusEntity entity = new StatusEntity();
        entity.setName(statusName);
        return entity;
    }
}



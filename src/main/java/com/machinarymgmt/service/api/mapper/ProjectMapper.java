package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Project;
import com.machinarymgmt.service.api.dto.ProjectDto;
import com.machinarymgmt.service.api.dto.ProjectRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProjectMapper extends MachinaryMgmtMapper {
    
    @Mapping(source = "name", target = "projectName")
    @Mapping(source = "location", target = "projectLocation")
    ProjectDto toDto(Project project);
    
    List<ProjectDto> toDtoList(List<Project> projects);
    
    @Mapping(source = "projectName", target = "name")
    @Mapping(source = "projectLocation", target = "location")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    @Mapping(target = "stockStatements", ignore = true)
    @Mapping(target = "utilizations", ignore = true)
    @Mapping(target = "incidents", ignore = true)
    @Mapping(target = "employeeAssignments", ignore = true)
    @Mapping(target = "companyAssignments", ignore = true)
    @Mapping(target = "pettyCashTransactions", ignore = true)
    @Mapping(target = "materialsConsumptions", ignore = true)
    @Mapping(target = "mastAnchorageDetails", ignore = true)
    Project toEntity(ProjectRequestDto dto);
    
    @Mapping(source = "projectName", target = "name")
    @Mapping(source = "projectLocation", target = "location")
    @Mapping(target = "equipment", ignore = true)
    @Mapping(target = "stockStatements", ignore = true)
    @Mapping(target = "utilizations", ignore = true)
    @Mapping(target = "incidents", ignore = true)
    @Mapping(target = "employeeAssignments", ignore = true)
    @Mapping(target = "companyAssignments", ignore = true)
    @Mapping(target = "pettyCashTransactions", ignore = true)
    @Mapping(target = "materialsConsumptions", ignore = true)
    @Mapping(target = "mastAnchorageDetails", ignore = true)
    void updateEntityFromDto(ProjectRequestDto dto, @MappingTarget Project project);
}


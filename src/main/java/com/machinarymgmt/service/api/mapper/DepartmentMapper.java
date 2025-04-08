package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Department;
import com.machinarymgmt.service.dto.DepartmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DepartmentMapper extends MachinaryMgmtMapper {

    DepartmentDto toDto(Department department);
    
    List<DepartmentDto> toDtoList(List<Department> departments);

    Department toEntity(DepartmentDto dto);

    void updateEntityFromDto(DepartmentDto dto, @MappingTarget Department department);
}


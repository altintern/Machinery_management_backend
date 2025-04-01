package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Department;
import com.machinarymgmt.service.api.dto.DepartmentDto;
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
    
    @Mapping(source = "name", target = "deptName")
    DepartmentDto toDto(Department department);
    
    List<DepartmentDto> toDtoList(List<Department> departments);
    
    @Mapping(source = "deptName", target = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employees", ignore = true)
    Department toEntity(DepartmentDto dto);
    
    @Mapping(source = "deptName", target = "name")
    @Mapping(target = "employees", ignore = true)
    void updateEntityFromDto(DepartmentDto dto, @MappingTarget Department department);
}


package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Department;
import com.machinarymgmt.service.api.data.model.Designation;
import com.machinarymgmt.service.api.data.model.Employee;
import com.machinarymgmt.service.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {DepartmentMapper.class, DesignationMapper.class}
)
public interface EmployeeMapper extends MachinaryMgmtMapper {

    EmployeeDto toDto(Employee employee);
    
    List<EmployeeDto> toDtoList(List<Employee> employees);

    Employee toEntity(EmployeeDto dto);

    void updateEntityFromDto(EmployeeDto dto, @MappingTarget Employee employee);
    
    default Employee fromDtoWithReferences(
            EmployeeDto dto,
            Designation designation,
            Department department) {
        Employee employee = toEntity(dto);
        employee.setDesignation(designation);
        employee.setDepartment(department);
        return employee;
    }
}


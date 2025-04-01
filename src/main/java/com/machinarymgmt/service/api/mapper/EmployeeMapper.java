package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Department;
import com.machinarymgmt.service.api.data.model.Designation;
import com.machinarymgmt.service.api.data.model.Employee;
import com.machinarymgmt.service.api.dto.EmployeeDto;
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
    
    @Mapping(source = "name", target = "employeeName")
    @Mapping(source = "designation.id", target = "designationId")
    @Mapping(source = "department.id", target = "deptId")
    EmployeeDto toDto(Employee employee);
    
    List<EmployeeDto> toDtoList(List<Employee> employees);
    
    @Mapping(source = "employeeName", target = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "designation", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "assignments", ignore = true)
    @Mapping(target = "overtimeReports", ignore = true)
    Employee toEntity(EmployeeDto dto);
    
    @Mapping(source = "employeeName", target = "name")
    @Mapping(target = "designation", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "assignments", ignore = true)
    @Mapping(target = "overtimeReports", ignore = true)
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


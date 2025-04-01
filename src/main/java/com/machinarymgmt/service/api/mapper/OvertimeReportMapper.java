package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Employee;
import com.machinarymgmt.service.api.data.model.OvertimeReport;
import com.machinarymgmt.service.api.dto.OvertimeReportDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {EmployeeMapper.class}
)
public interface OvertimeReportMapper extends MachinaryMgmtMapper {
    
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "otHours", target = "otHours")
    OvertimeReportDto toDto(OvertimeReport overtimeReport);
    
    List<OvertimeReportDto> toDtoList(List<OvertimeReport> overtimeReports);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(source = "otHours", target = "otHours")
    OvertimeReport toEntity(OvertimeReportDto dto);
    
    @Mapping(target = "employee", ignore = true)
    @Mapping(source = "otHours", target = "otHours")
    void updateEntityFromDto(OvertimeReportDto dto, @MappingTarget OvertimeReport overtimeReport);
    
    default OvertimeReport fromDtoWithReferences(
            OvertimeReportDto dto,
            Employee employee) {
        OvertimeReport overtimeReport = toEntity(dto);
        overtimeReport.setEmployee(employee);
        overtimeReport.setOtHours(BigDecimal.valueOf(dto.getOtHours()));
        return overtimeReport;
    }
    
    default Double mapBigDecimalToDouble(BigDecimal value) {
        return value != null ? value.doubleValue() : null;
    }
    
    default BigDecimal mapDoubleToDecimal(Double value) {
        return value != null ? BigDecimal.valueOf(value) : null;
    }
}


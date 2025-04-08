package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Employee;
import com.machinarymgmt.service.api.data.model.OvertimeReport;
import com.machinarymgmt.service.dto.OvertimeReportDto;
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

    OvertimeReportDto toDto(OvertimeReport overtimeReport);
    
    List<OvertimeReportDto> toDtoList(List<OvertimeReport> overtimeReports);

    OvertimeReport toEntity(OvertimeReportDto dto);

    void updateEntityFromDto(OvertimeReportDto dto, @MappingTarget OvertimeReport overtimeReport);
    
    default OvertimeReport fromDtoWithReferences(
            OvertimeReportDto dto,
            Employee employee) {
        OvertimeReport overtimeReport = toEntity(dto);
        overtimeReport.setEmployee(employee);
//        overtimeReport.setOtHours(BigDecimal.valueOf(dto.getOtHours()));
        return overtimeReport;
    }
    
    default Double mapBigDecimalToDouble(BigDecimal value) {
        return value != null ? value.doubleValue() : null;
    }
    
    default BigDecimal mapDoubleToDecimal(Double value) {
        return value != null ? BigDecimal.valueOf(value) : null;
    }
}


package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.MachineryMaintenanceLog;
import com.machinarymgmt.service.api.data.model.MaintenanceReading;
import com.machinarymgmt.service.dto.MaintenanceReadingDto;
import com.machinarymgmt.service.dto.MaintenanceReadingRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MaintenanceReadingMapper extends MachinaryMgmtMapper {
    
    MaintenanceReadingDto toDto(MaintenanceReading reading);

    MaintenanceReading toEntity(MaintenanceReadingRequestDto dto);
    
    default MaintenanceReading fromDtoWithReferences(
            MaintenanceReadingRequestDto dto,
            MachineryMaintenanceLog log) {
        if (dto == null) {
            return null;
        }
        MaintenanceReading reading = toEntity(dto);
        reading.setMaintenanceLog(log);
        return reading;
    }
    
    default Double mapBigDecimalToDouble(BigDecimal value) {
        return value != null ? value.doubleValue() : null;
    }
    
    default BigDecimal mapDoubleToDecimal(Double value) {
        return value != null ? BigDecimal.valueOf(value) : null;
    }
}


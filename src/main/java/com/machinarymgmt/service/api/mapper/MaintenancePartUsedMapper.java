package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Item;
import com.machinarymgmt.service.api.data.model.MachineryMaintenanceLog;
import com.machinarymgmt.service.api.data.model.MaintenancePartsUsed;
import com.machinarymgmt.service.dto.MaintenancePartUsedDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.math.BigDecimal;
import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {ItemMapper.class}
)
public interface MaintenancePartUsedMapper extends MachinaryMgmtMapper {

    MaintenancePartUsedDto toDto(MaintenancePartsUsed partsUsed);
    
    List<MaintenancePartUsedDto> toDtoList(List<MaintenancePartsUsed> partsUsed);

    MaintenancePartsUsed toEntity(MaintenancePartUsedDto dto);
    
    default MaintenancePartsUsed fromDtoWithReferences(
            MaintenancePartUsedDto dto,
            MachineryMaintenanceLog log,
            Item item) {
        MaintenancePartsUsed partsUsed = toEntity(dto);
        partsUsed.setMaintenanceLog(log);
        partsUsed.setItem(item);
        partsUsed.setQuantity(BigDecimal.valueOf(dto.getQuantity()));
        return partsUsed;
    }
    
    default Double mapBigDecimalToDouble(BigDecimal value) {
        return value != null ? value.doubleValue() : null;
    }
    
    default BigDecimal mapDoubleToDecimal(Double value) {
        return value != null ? BigDecimal.valueOf(value) : null;
    }
}


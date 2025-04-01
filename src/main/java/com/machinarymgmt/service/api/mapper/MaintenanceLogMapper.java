package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.data.model.MachineryMaintenanceLog;
import com.machinarymgmt.service.api.data.model.MaintenancePartsUsed;
import com.machinarymgmt.service.api.data.model.MaintenanceReading;
import com.machinarymgmt.service.api.dto.MaintenanceLogDto;
import com.machinarymgmt.service.api.dto.MaintenanceLogRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {EquipmentMapper.class, MaintenancePartUsedMapper.class, MaintenanceReadingMapper.class}
)
public interface MaintenanceLogMapper extends MachinaryMgmtMapper {
    
    @Mapping(source = "equipment.id", target = "equipmentId")
    MaintenanceLogDto toDto(MachineryMaintenanceLog log);
    
    List<MaintenanceLogDto> toDtoList(List<MachineryMaintenanceLog> logs);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    @Mapping(target = "partsUsed", ignore = true)
    @Mapping(target = "readings", ignore = true)
    MachineryMaintenanceLog toEntity(MaintenanceLogRequestDto dto);
    
    @Mapping(target = "equipment", ignore = true)
    @Mapping(target = "partsUsed", ignore = true)
    @Mapping(target = "readings", ignore = true)
    void updateEntityFromDto(MaintenanceLogRequestDto dto, @MappingTarget MachineryMaintenanceLog log);
    
    default MachineryMaintenanceLog fromDtoWithReferences(
            MaintenanceLogRequestDto dto, 
            Equipment equipment, 
            List<MaintenancePartsUsed> partsUsed,
            MaintenanceReading readings) {
        MachineryMaintenanceLog log = toEntity(dto);
        log.setEquipment(equipment);
        log.setPartsUsed(partsUsed);
        log.setReadings(readings);
        return log;
    }
}


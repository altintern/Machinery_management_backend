package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.data.model.Item;
import com.machinarymgmt.service.api.data.model.MaterialsConsumptionTransaction;
import com.machinarymgmt.service.api.data.model.Project;
import com.machinarymgmt.service.api.dto.MaterialsConsumptionTransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {ProjectMapper.class, EquipmentMapper.class, ItemMapper.class}
)
public interface MaterialsConsumptionTransactionMapper extends MachinaryMgmtMapper {
    
    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "equipment.id", target = "equipmentId")
    @Mapping(source = "item.id", target = "itemId")
    MaterialsConsumptionTransactionDto toDto(MaterialsConsumptionTransaction transaction);
    
    List<MaterialsConsumptionTransactionDto> toDtoList(List<MaterialsConsumptionTransaction> transactions);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    @Mapping(target = "item", ignore = true)
    MaterialsConsumptionTransaction toEntity(MaterialsConsumptionTransactionDto dto);
    
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    @Mapping(target = "item", ignore = true)
    void updateEntityFromDto(MaterialsConsumptionTransactionDto dto, @MappingTarget MaterialsConsumptionTransaction transaction);
    
    default MaterialsConsumptionTransaction fromDtoWithReferences(
            MaterialsConsumptionTransactionDto dto,
            Project project,
            Equipment equipment,
            Item item) {
        MaterialsConsumptionTransaction transaction = toEntity(dto);
        transaction.setProject(project);
        transaction.setEquipment(equipment);
        transaction.setItem(item);
        return transaction;
    }
}


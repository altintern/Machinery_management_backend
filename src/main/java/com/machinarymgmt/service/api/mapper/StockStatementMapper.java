package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.data.model.Item;
import com.machinarymgmt.service.api.data.model.Project;
import com.machinarymgmt.service.api.data.model.StockStatement;
import com.machinarymgmt.service.api.dto.StockStatementDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {ProjectMapper.class, ItemMapper.class, EquipmentMapper.class}
)
public interface StockStatementMapper extends MachinaryMgmtMapper {
    
    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "item.id", target = "itemId")
    @Mapping(source = "equipment.id", target = "equipmentId")
    StockStatementDto toDto(StockStatement stockStatement);
    
    List<StockStatementDto> toDtoList(List<StockStatement> stockStatements);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "item", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    StockStatement toEntity(StockStatementDto dto);
    
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "item", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    void updateEntityFromDto(StockStatementDto dto, @MappingTarget StockStatement stockStatement);
    
    default StockStatement fromDtoWithReferences(
            StockStatementDto dto,
            Project project,
            Item item,
            Equipment equipment) {
        StockStatement stockStatement = toEntity(dto);
        stockStatement.setProject(project);
        stockStatement.setItem(item);
        stockStatement.setEquipment(equipment);
        return stockStatement;
    }
}


package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.data.model.Item;
import com.machinarymgmt.service.api.data.model.Project;
import com.machinarymgmt.service.api.data.model.StockStatement;
import com.machinarymgmt.service.dto.StockStatementDto;
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

    StockStatementDto toDto(StockStatement stockStatement);
    
    List<StockStatementDto> toDtoList(List<StockStatement> stockStatements);

    StockStatement toEntity(StockStatementDto dto);

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


package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.data.model.Item;
import com.machinarymgmt.service.api.data.model.PettyCashTransaction;
import com.machinarymgmt.service.api.data.model.Project;
import com.machinarymgmt.service.dto.PettyCashTransactionDto;
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
public interface PettyCashTransactionMapper extends MachinaryMgmtMapper {

    PettyCashTransactionDto toDto(PettyCashTransaction transaction);
    
    List<PettyCashTransactionDto> toDtoList(List<PettyCashTransaction> transactions);

    PettyCashTransaction toEntity(PettyCashTransactionDto dto);

    void updateEntityFromDto(PettyCashTransactionDto dto, @MappingTarget PettyCashTransaction transaction);
    
    default PettyCashTransaction fromDtoWithReferences(
            PettyCashTransactionDto dto,
            Project project,
            Equipment equipment,
            Item item) {
        PettyCashTransaction transaction = toEntity(dto);
        transaction.setProject(project);
        transaction.setEquipment(equipment);
        transaction.setItem(item);
        return transaction;
    }
}


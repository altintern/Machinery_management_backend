package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.data.model.EquipmentCategory;
import com.machinarymgmt.service.api.data.model.Model;
import com.machinarymgmt.service.api.data.model.Project;
import com.machinarymgmt.service.api.dto.EquipmentDto;
import com.machinarymgmt.service.api.dto.EquipmentRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {ProjectMapper.class, EquipmentCategoryMapper.class, ModelMapper.class}
)
public interface EquipmentMapper extends MachinaryMgmtMapper {
    
    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "model.id", target = "modelId")
    @Mapping(source = "name", target = "equipmentName")
    EquipmentDto toDto(Equipment equipment);
    
    List<EquipmentDto> toDtoList(List<Equipment> equipmentList);
    
    @Mapping(source = "equipmentName", target = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "model", ignore = true)
    @Mapping(target = "stockStatements", ignore = true)
    @Mapping(target = "utilizations", ignore = true)
    @Mapping(target = "incidents", ignore = true)
    @Mapping(target = "employeeAssignments", ignore = true)
    @Mapping(target = "companyAssignments", ignore = true)
    @Mapping(target = "maintenanceLogs", ignore = true)
    @Mapping(target = "pettyCashTransactions", ignore = true)
    @Mapping(target = "materialsConsumptions", ignore = true)
    @Mapping(target = "mastAnchorageDetails", ignore = true)
    Equipment toEntity(EquipmentRequestDto dto);
    
    @Mapping(source = "equipmentName", target = "name")
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "model", ignore = true)
    @Mapping(target = "stockStatements", ignore = true)
    @Mapping(target = "utilizations", ignore = true)
    @Mapping(target = "incidents", ignore = true)
    @Mapping(target = "employeeAssignments", ignore = true)
    @Mapping(target = "companyAssignments", ignore = true)
    @Mapping(target = "maintenanceLogs", ignore = true)
    @Mapping(target = "pettyCashTransactions", ignore = true)
    @Mapping(target = "materialsConsumptions", ignore = true)
    @Mapping(target = "mastAnchorageDetails", ignore = true)
    void updateEntityFromDto(EquipmentRequestDto dto, @MappingTarget Equipment equipment);
    
    default Equipment fromDtoWithReferences(EquipmentRequestDto dto, Project project, EquipmentCategory category, Model model) {
        Equipment equipment = toEntity(dto);
        equipment.setProject(project);
        equipment.setCategory(category);
        equipment.setModel(model);
        return equipment;
    }
}


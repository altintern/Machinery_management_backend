package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Company;
import com.machinarymgmt.service.api.data.model.CompanyProjectEquipment;
import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.data.model.Project;
import com.machinarymgmt.service.api.dto.CompanyProjectEquipmentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {CompanyMapper.class, ProjectMapper.class, EquipmentMapper.class}
)
public interface CompanyProjectEquipmentMapper extends MachinaryMgmtMapper {
    
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "equipment.id", target = "equipmentId")
    CompanyProjectEquipmentDto toDto(CompanyProjectEquipment assignment);
    
    List<CompanyProjectEquipmentDto> toDtoList(List<CompanyProjectEquipment> assignments);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    CompanyProjectEquipment toEntity(CompanyProjectEquipmentDto dto);
    
    @Mapping(target = "company", ignore = true)
    @Mapping(target = "project", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    void updateEntityFromDto(CompanyProjectEquipmentDto dto, @MappingTarget CompanyProjectEquipment assignment);
    
    default CompanyProjectEquipment fromDtoWithReferences(
            CompanyProjectEquipmentDto dto,
            Company company,
            Project project,
            Equipment equipment) {
        CompanyProjectEquipment assignment = toEntity(dto);
        assignment.setCompany(company);
        assignment.setProject(project);
        assignment.setEquipment(equipment);
        return assignment;
    }
}


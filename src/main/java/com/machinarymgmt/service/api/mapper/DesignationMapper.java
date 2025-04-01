package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Designation;
import com.machinarymgmt.service.api.dto.DesignationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DesignationMapper extends MachinaryMgmtMapper {
    
    @Mapping(source = "name", target = "designationName")
    DesignationDto toDto(Designation designation);
    
    List<DesignationDto> toDtoList(List<Designation> designations);
    
    @Mapping(source = "designationName", target = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employees", ignore = true)
    Designation toEntity(DesignationDto dto);
    
    @Mapping(source = "designationName", target = "name")
    @Mapping(target = "employees", ignore = true)
    void updateEntityFromDto(DesignationDto dto, @MappingTarget Designation designation);
}


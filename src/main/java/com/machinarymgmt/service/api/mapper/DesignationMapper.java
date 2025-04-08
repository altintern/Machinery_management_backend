package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Designation;
import com.machinarymgmt.service.dto.DesignationDto;
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

    DesignationDto toDto(Designation designation);
    
    List<DesignationDto> toDtoList(List<Designation> designations);

    Designation toEntity(DesignationDto dto);

    void updateEntityFromDto(DesignationDto dto, @MappingTarget Designation designation);
}


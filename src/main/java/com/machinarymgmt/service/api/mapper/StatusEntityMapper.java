package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.StatusEntity;
import com.machinarymgmt.service.api.dto.StatusEntityDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface StatusEntityMapper extends MachinaryMgmtMapper {
    
    @Mapping(source = "name", target = "statusName")
    StatusEntityDto toDto(StatusEntity statusEntity);
    
    List<StatusEntityDto> toDtoList(List<StatusEntity> statusEntities);
    
    @Mapping(source = "statusName", target = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "incidents", ignore = true)
    StatusEntity toEntity(StatusEntityDto dto);
    
    @Mapping(source = "statusName", target = "name")
    @Mapping(target = "incidents", ignore = true)
    void updateEntityFromDto(StatusEntityDto dto, @MappingTarget StatusEntity statusEntity);
}


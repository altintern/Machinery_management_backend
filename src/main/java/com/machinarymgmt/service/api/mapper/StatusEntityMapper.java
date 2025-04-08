package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.StatusEntity;
import com.machinarymgmt.service.dto.StatusEntityDto;
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

    StatusEntityDto toDto(StatusEntity statusEntity);
    
    List<StatusEntityDto> toDtoList(List<StatusEntity> statusEntities);

    StatusEntity toEntity(StatusEntityDto dto);

    void updateEntityFromDto(StatusEntityDto dto, @MappingTarget StatusEntity statusEntity);
}


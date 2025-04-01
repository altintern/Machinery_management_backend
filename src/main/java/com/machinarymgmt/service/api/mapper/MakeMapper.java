package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Make;
import com.machinarymgmt.service.api.dto.MakeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface MakeMapper extends MachinaryMgmtMapper {
    
    @Mapping(source = "name", target = "makeName")
    MakeDto toDto(Make make);
    
    List<MakeDto> toDtoList(List<Make> makes);
    
    @Mapping(source = "makeName", target = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "models", ignore = true)
    Make toEntity(MakeDto dto);
}


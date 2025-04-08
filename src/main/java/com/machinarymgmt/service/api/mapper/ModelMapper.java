package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Make;
import com.machinarymgmt.service.api.data.model.Model;
import com.machinarymgmt.service.dto.ModelDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    uses = {MakeMapper.class}
)
public interface ModelMapper extends MachinaryMgmtMapper {

    ModelDto toDto(Model model);
    
    List<ModelDto> toDtoList(List<Model> models);

    Model toEntity(ModelDto dto);

    void updateEntityFromDto(ModelDto dto, @MappingTarget Model model);
    
    default Model fromDtoWithReferences(ModelDto dto, Make make) {
        Model model = toEntity(dto);
        model.setMake(make);
        return model;
    }
}


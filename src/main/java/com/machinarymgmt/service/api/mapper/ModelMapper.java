package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Make;
import com.machinarymgmt.service.api.data.model.Model;
import com.machinarymgmt.service.api.dto.ModelDto;
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
    
    @Mapping(source = "make.id", target = "makeId")
    @Mapping(source = "name", target = "modelName")
    ModelDto toDto(Model model);
    
    List<ModelDto> toDtoList(List<Model> models);
    
    @Mapping(source = "modelName", target = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "make", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    Model toEntity(ModelDto dto);
    
    @Mapping(source = "modelName", target = "name")
    @Mapping(target = "make", ignore = true)
    @Mapping(target = "equipment", ignore = true)
    void updateEntityFromDto(ModelDto dto, @MappingTarget Model model);
    
    default Model fromDtoWithReferences(ModelDto dto, Make make) {
        Model model = toEntity(dto);
        model.setMake(make);
        return model;
    }
}


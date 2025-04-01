package com.machinarymgmt.service.api.mapper;

import com.machinarymgmt.service.api.data.model.Company;
import com.machinarymgmt.service.api.dto.CompanyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CompanyMapper extends MachinaryMgmtMapper {
    
    @Mapping(source = "name", target = "companyName")
    CompanyDto toDto(Company company);
    
    List<CompanyDto> toDtoList(List<Company> companies);
    
    @Mapping(source = "companyName", target = "name")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "assignments", ignore = true)
    Company toEntity(CompanyDto dto);
    
    @Mapping(source = "companyName", target = "name")
    @Mapping(target = "assignments", ignore = true)
    void updateEntityFromDto(CompanyDto dto, @MappingTarget Company company);
}


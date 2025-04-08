package com.machinarymgmt.service.api.service;

import com.machinarymgmt.service.api.data.model.IncidentType;
import com.machinarymgmt.service.dto.IncidentTypeDto;

import java.util.List;
import java.util.Optional;

public interface IncidentTypeService {
    List<IncidentType> findAll();
    Optional<IncidentType> findById(Long id);
    Optional<IncidentType> findByName(String name);
    IncidentType save(IncidentType incidentType);
    void deleteById(Long id);
    boolean existsById(Long id);
    boolean existsByName(String name);
    List<IncidentTypeDto> findAllDto();
    Optional<IncidentTypeDto> findDtoById(Long id);
}


package com.machinarymgmt.service.api.service;

import com.machinarymgmt.service.api.data.IncidentTypeRepository;
import com.machinarymgmt.service.api.data.model.IncidentType;
import com.machinarymgmt.service.api.dto.IncidentTypeDto;
import com.machinarymgmt.service.api.mapper.IncidentTypeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncidentTypeServiceImpl implements IncidentTypeService {
    
    private final IncidentTypeRepository incidentTypeRepository;
    private final IncidentTypeMapper incidentTypeMapper;
    
    @Override
    public List<IncidentType> findAll() {
        return incidentTypeRepository.findAll();
    }
    
    @Override
    public Optional<IncidentType> findById(Long id) {
        return incidentTypeRepository.findById(id);
    }
    
    @Override
    public Optional<IncidentType> findByName(String name) {
        return incidentTypeRepository.findByName(name);
    }
    
    @Override
    public IncidentType save(IncidentType incidentType) {
        return incidentTypeRepository.save(incidentType);
    }
    
    @Override
    public void deleteById(Long id) {
        incidentTypeRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return incidentTypeRepository.existsById(id);
    }
    
    @Override
    public boolean existsByName(String name) {
        return incidentTypeRepository.existsByName(name);
    }
    
    @Override
    public List<IncidentTypeDto> findAllDto() {
        return incidentTypeMapper.toDtoList(findAll());
    }
    
    @Override
    public Optional<IncidentTypeDto> findDtoById(Long id) {
        return findById(id).map(incidentTypeMapper::toDto);
    }
}


package com.machinarymgmt.service.api.service;

import com.machinarymgmt.service.api.data.StatusEntityRepository;
import com.machinarymgmt.service.api.data.model.StatusEntity;
import com.machinarymgmt.service.api.dto.StatusEntityDto;
import com.machinarymgmt.service.api.mapper.StatusEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusEntityServiceImpl implements StatusEntityService {
    
    private final StatusEntityRepository statusEntityRepository;
    private final StatusEntityMapper statusEntityMapper;
    
    @Override
    public List<StatusEntity> findAll() {
        return statusEntityRepository.findAll();
    }
    
    @Override
    public Optional<StatusEntity> findById(Long id) {
        return statusEntityRepository.findById(id);
    }
    
    @Override
    public Optional<StatusEntity> findByName(String name) {
        return statusEntityRepository.findByName(name);
    }
    
    @Override
    public StatusEntity save(StatusEntity statusEntity) {
        return statusEntityRepository.save(statusEntity);
    }
    
    @Override
    public void deleteById(Long id) {
        statusEntityRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return statusEntityRepository.existsById(id);
    }
    
    @Override
    public boolean existsByName(String name) {
        return statusEntityRepository.existsByName(name);
    }
    
    @Override
    public List<StatusEntityDto> findAllDto() {
        return statusEntityMapper.toDtoList(findAll());
    }
    
    @Override
    public Optional<StatusEntityDto> findDtoById(Long id) {
        return findById(id).map(statusEntityMapper::toDto);
    }
}


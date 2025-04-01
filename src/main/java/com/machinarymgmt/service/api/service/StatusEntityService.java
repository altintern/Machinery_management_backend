package com.machinarymgmt.service.api.service;

import com.machinarymgmt.service.api.data.model.StatusEntity;
import com.machinarymgmt.service.api.dto.StatusEntityDto;

import java.util.List;
import java.util.Optional;

public interface StatusEntityService {
    List<StatusEntity> findAll();
    Optional<StatusEntity> findById(Long id);
    Optional<StatusEntity> findByName(String name);
    StatusEntity save(StatusEntity statusEntity);
    void deleteById(Long id);
    boolean existsById(Long id);
    boolean existsByName(String name);
    List<StatusEntityDto> findAllDto();
    Optional<StatusEntityDto> findDtoById(Long id);
}


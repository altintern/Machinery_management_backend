package com.machinarymgmt.service.api.service;

import com.machinarymgmt.service.api.data.model.Make;
import com.machinarymgmt.service.api.data.model.Model;
import com.machinarymgmt.service.api.dto.ModelDto;

import java.util.List;
import java.util.Optional;

public interface ModelService {
    List<Model> findAll();
    Optional<Model> findById(Long id);
    List<Model> findByMake(Make make);
    Optional<Model> findByNameAndMake(String name, Make make);
    Model save(Model model);
    void deleteById(Long id);
    boolean existsById(Long id);
    boolean existsByNameAndMake(String name, Make make);
    List<ModelDto> findAllDto();
    Optional<ModelDto> findDtoById(Long id);
    List<ModelDto> findDtoByMakeId(Long makeId);
}


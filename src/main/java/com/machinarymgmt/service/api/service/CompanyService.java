package com.machinarymgmt.service.api.service;

import com.machinarymgmt.service.api.data.model.Company;
import com.machinarymgmt.service.api.dto.CompanyDto;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> findAll();
    Optional<Company> findById(Long id);
    Optional<Company> findByName(String name);
    Company save(Company company);
    void deleteById(Long id);
    boolean existsById(Long id);
    boolean existsByName(String name);
    List<CompanyDto> findAllDto();
    Optional<CompanyDto> findDtoById(Long id);
}


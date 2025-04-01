package com.machinarymgmt.service.api.service;

import com.machinarymgmt.service.api.data.CompanyRepository;
import com.machinarymgmt.service.api.data.model.Company;
import com.machinarymgmt.service.api.dto.CompanyDto;
import com.machinarymgmt.service.api.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    
    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }
    
    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }
    
    @Override
    public Optional<Company> findByName(String name) {
        return companyRepository.findByName(name);
    }
    
    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }
    
    @Override
    public void deleteById(Long id) {
        companyRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return companyRepository.existsById(id);
    }
    
    @Override
    public boolean existsByName(String name) {
        return companyRepository.existsByName(name);
    }
    
    @Override
    public List<CompanyDto> findAllDto() {
        return companyMapper.toDtoList(findAll());
    }
    
    @Override
    public Optional<CompanyDto> findDtoById(Long id) {
        return findById(id).map(companyMapper::toDto);
    }
}


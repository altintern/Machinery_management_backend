package com.machinarymgmt.service.api.service;

import com.machinarymgmt.service.api.data.CompanyProjectEquipmentRepository;
import com.machinarymgmt.service.api.data.model.Company;
import com.machinarymgmt.service.api.data.model.CompanyProjectEquipment;
import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.data.model.Project;
import com.machinarymgmt.service.api.dto.CompanyProjectEquipmentDto;
import com.machinarymgmt.service.api.mapper.CompanyProjectEquipmentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyProjectEquipmentServiceImpl implements CompanyProjectEquipmentService {
    
    private final CompanyProjectEquipmentRepository assignmentRepository;
    private final CompanyProjectEquipmentMapper assignmentMapper;
    
    @Override
    public List<CompanyProjectEquipment> findAll() {
        return assignmentRepository.findAll();
    }
    
    @Override
    public Page<CompanyProjectEquipment> findAll(Pageable pageable) {
        return assignmentRepository.findAll(pageable);
    }
    
    @Override
    public Optional<CompanyProjectEquipment> findById(Long id) {
        return assignmentRepository.findById(id);
    }
    
    @Override
    public List<CompanyProjectEquipment> findByCompany(Company company) {
        return assignmentRepository.findByCompany(company);
    }
    
    @Override
    public List<CompanyProjectEquipment> findByProject(Project project) {
        return assignmentRepository.findByProject(project);
    }
    
    @Override
    public List<CompanyProjectEquipment> findByEquipment(Equipment equipment) {
        return assignmentRepository.findByEquipment(equipment);
    }
    
    @Override
    public CompanyProjectEquipment save(CompanyProjectEquipment assignment) {
        return assignmentRepository.save(assignment);
    }
    
    @Override
    public void deleteById(Long id) {
        assignmentRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return assignmentRepository.existsById(id);
    }
    
    @Override
    public List<CompanyProjectEquipmentDto> findAllDto() {
        return assignmentMapper.toDtoList(findAll());
    }
    
    @Override
    public Page<CompanyProjectEquipmentDto> findAllDto(Pageable pageable) {
        return findAll(pageable).map(assignmentMapper::toDto);
    }
    
    @Override
    public Optional<CompanyProjectEquipmentDto> findDtoById(Long id) {
        return findById(id).map(assignmentMapper::toDto);
    }
}


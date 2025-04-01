package com.machinarymgmt.service.api.service;

import com.machinarymgmt.service.api.data.model.Company;
import com.machinarymgmt.service.api.data.model.CompanyProjectEquipment;
import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.data.model.Project;
import com.machinarymgmt.service.api.dto.CompanyProjectEquipmentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CompanyProjectEquipmentService {
    List<CompanyProjectEquipment> findAll();
    Page<CompanyProjectEquipment> findAll(Pageable pageable);
    Optional<CompanyProjectEquipment> findById(Long id);
    List<CompanyProjectEquipment> findByCompany(Company company);
    List<CompanyProjectEquipment> findByProject(Project project);
    List<CompanyProjectEquipment> findByEquipment(Equipment equipment);
    CompanyProjectEquipment save(CompanyProjectEquipment assignment);
    void deleteById(Long id);
    boolean existsById(Long id);
    List<CompanyProjectEquipmentDto> findAllDto();
    Page<CompanyProjectEquipmentDto> findAllDto(Pageable pageable);
    Optional<CompanyProjectEquipmentDto> findDtoById(Long id);
}


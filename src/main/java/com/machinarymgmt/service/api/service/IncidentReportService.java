package com.machinarymgmt.service.api.service;

import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.data.model.IncidentReport;
import com.machinarymgmt.service.api.data.model.IncidentType;
import com.machinarymgmt.service.api.data.model.Project;
import com.machinarymgmt.service.api.dto.IncidentReportDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IncidentReportService {
    List<IncidentReport> findAll();
    Page<IncidentReport> findAll(Pageable pageable);
    Optional<IncidentReport> findById(Long id);
    List<IncidentReport> findByEquipment(Equipment equipment);
    List<IncidentReport> findByProject(Project project);
    List<IncidentReport> findByType(IncidentType type);
    Page<IncidentReport> findByIncidentDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
    IncidentReport save(IncidentReport incidentReport);
    void deleteById(Long id);
    boolean existsById(Long id);
    List<IncidentReportDto> findAllDto();
    Page<IncidentReportDto> findAllDto(Pageable pageable);
    Optional<IncidentReportDto> findDtoById(Long id);
}


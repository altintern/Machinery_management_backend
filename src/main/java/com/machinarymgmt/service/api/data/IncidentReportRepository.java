package com.machinarymgmt.service.api.data;

import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.data.model.IncidentReport;
import com.machinarymgmt.service.api.data.model.IncidentType;
import com.machinarymgmt.service.api.data.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IncidentReportRepository extends JpaRepository<IncidentReport, Long> {
    List<IncidentReport> findByEquipment(Equipment equipment);
    List<IncidentReport> findByProject(Project project);
    List<IncidentReport> findByType(IncidentType type);
    Page<IncidentReport> findByIncidentDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}


package com.machinarymgmt.service.api.data;

import com.machinarymgmt.service.api.data.model.Company;
import com.machinarymgmt.service.api.data.model.CompanyProjectEquipment;
import com.machinarymgmt.service.api.data.model.Equipment;
import com.machinarymgmt.service.api.data.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyProjectEquipmentRepository extends JpaRepository<CompanyProjectEquipment, Long> {
    List<CompanyProjectEquipment> findByCompany(Company company);
    List<CompanyProjectEquipment> findByProject(Project project);
    List<CompanyProjectEquipment> findByEquipment(Equipment equipment);
}


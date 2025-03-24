package com.machinarymgmt.service.api.data;

import com.machinarymgmt.service.api.data.model.CompanyProjectEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyProjectEquipmentRepository extends JpaRepository<CompanyProjectEquipment, Long> {
}

package com.machinarymgmt.service.api.data;

import com.machinarymgmt.service.api.data.model.EquipmentUtilization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentUtilizationRepository extends JpaRepository<EquipmentUtilization, Long> {
}

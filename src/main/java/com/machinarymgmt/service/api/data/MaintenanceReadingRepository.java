package com.machinarymgmt.service.api.data;

import com.machinarymgmt.service.api.data.model.MaintenanceReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceReadingRepository extends JpaRepository<MaintenanceReading, Long> {
}

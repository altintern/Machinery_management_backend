package com.machinarymgmt.service.api.data;

import com.machinarymgmt.service.api.data.model.MaterialsConsumptionTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialsConsumptionTransactionRepository extends JpaRepository<MaterialsConsumptionTransaction, Long> {
}

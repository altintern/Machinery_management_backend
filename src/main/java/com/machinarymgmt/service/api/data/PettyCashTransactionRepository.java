package com.machinarymgmt.service.api.data;

import com.machinarymgmt.service.api.data.model.PettyCashTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PettyCashTransactionRepository extends JpaRepository<PettyCashTransaction, Long> {
}

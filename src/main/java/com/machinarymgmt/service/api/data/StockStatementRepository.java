package com.machinarymgmt.service.api.data;

import com.machinarymgmt.service.api.data.model.StockStatement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockStatementRepository extends JpaRepository<StockStatement, Long> {
}

package com.machinarymgmt.service.api.data;

import com.machinarymgmt.service.api.data.model.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusEntityRepository extends JpaRepository<StatusEntity, Long> {
}

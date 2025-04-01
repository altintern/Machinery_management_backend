package com.machinarymgmt.service.api.data;

import com.machinarymgmt.service.api.data.model.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusEntityRepository extends JpaRepository<StatusEntity, Long> {
    Optional<StatusEntity> findByName(String name);
    boolean existsByName(String name);
}


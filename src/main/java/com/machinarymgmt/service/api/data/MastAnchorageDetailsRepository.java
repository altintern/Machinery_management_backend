package com.machinarymgmt.service.api.data;

import com.machinarymgmt.service.api.data.model.MastAnchorageDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MastAnchorageDetailsRepository extends JpaRepository<MastAnchorageDetails, Long> {
}

package com.cvicse.repository;

import com.cvicse.domain.Qualitymanagement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Qualitymanagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QualitymanagementRepository extends JpaRepository<Qualitymanagement, Long> {}

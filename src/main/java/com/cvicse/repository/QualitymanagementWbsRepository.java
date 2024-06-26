package com.cvicse.repository;

import com.cvicse.domain.QualitymanagementWbs;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the QualitymanagementWbs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QualitymanagementWbsRepository extends JpaRepository<QualitymanagementWbs, String> {}

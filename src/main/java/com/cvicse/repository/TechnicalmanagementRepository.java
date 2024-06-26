package com.cvicse.repository;

import com.cvicse.domain.Technicalmanagement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Technicalmanagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TechnicalmanagementRepository extends JpaRepository<Technicalmanagement, String> {}

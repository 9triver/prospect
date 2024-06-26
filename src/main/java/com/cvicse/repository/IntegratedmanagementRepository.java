package com.cvicse.repository;

import com.cvicse.domain.Integratedmanagement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Integratedmanagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IntegratedmanagementRepository extends JpaRepository<Integratedmanagement, Long> {}

package com.cvicse.repository;

import com.cvicse.domain.TechnicalmanagementWbs;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TechnicalmanagementWbs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TechnicalmanagementWbsRepository extends JpaRepository<TechnicalmanagementWbs, String> {}

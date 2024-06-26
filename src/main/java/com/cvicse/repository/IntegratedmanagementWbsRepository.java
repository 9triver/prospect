package com.cvicse.repository;

import com.cvicse.domain.IntegratedmanagementWbs;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the IntegratedmanagementWbs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IntegratedmanagementWbsRepository extends JpaRepository<IntegratedmanagementWbs, String> {}

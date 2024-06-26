package com.cvicse.repository;

import com.cvicse.domain.ProgressmanagementWbs;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProgressmanagementWbs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgressmanagementWbsRepository extends JpaRepository<ProgressmanagementWbs, String> {}

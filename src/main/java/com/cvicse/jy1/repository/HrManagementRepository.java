package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.HrManagement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the HrManagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HrManagementRepository extends JpaRepository<HrManagement, Integer> {}

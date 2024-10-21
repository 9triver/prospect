package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.DeviationPermitApplication;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DeviationPermitApplication entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeviationPermitApplicationRepository extends JpaRepository<DeviationPermitApplication, Integer> {}

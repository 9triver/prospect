package com.cvicse.repository;

import com.cvicse.domain.FundsmanagementWbs;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FundsmanagementWbs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FundsmanagementWbsRepository extends JpaRepository<FundsmanagementWbs, String> {}

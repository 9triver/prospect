package com.cvicse.repository;

import com.cvicse.domain.SecuritymanagementWbs;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SecuritymanagementWbs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecuritymanagementWbsRepository extends JpaRepository<SecuritymanagementWbs, String> {}

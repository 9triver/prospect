package com.cvicse.repository;

import com.cvicse.domain.Securitymanagement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Securitymanagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecuritymanagementRepository extends JpaRepository<Securitymanagement, String> {}

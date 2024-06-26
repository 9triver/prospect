package com.cvicse.repository;

import com.cvicse.domain.Outsourcingmanagement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Outsourcingmanagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OutsourcingmanagementRepository extends JpaRepository<Outsourcingmanagement, String> {}

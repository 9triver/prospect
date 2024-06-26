package com.cvicse.repository;

import com.cvicse.domain.Secrecymanagement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Secrecymanagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecrecymanagementRepository extends JpaRepository<Secrecymanagement, String> {}

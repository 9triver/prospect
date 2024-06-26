package com.cvicse.repository;

import com.cvicse.domain.Resourcemanagement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Resourcemanagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResourcemanagementRepository extends JpaRepository<Resourcemanagement, String> {}

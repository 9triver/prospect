package com.cvicse.repository;

import com.cvicse.domain.Fundsmanagement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Fundsmanagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FundsmanagementRepository extends JpaRepository<Fundsmanagement, Long> {}

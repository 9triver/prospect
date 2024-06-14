package com.cvicse.repository;

import com.cvicse.domain.Fundsavailability;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Fundsavailability entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FundsavailabilityRepository extends JpaRepository<Fundsavailability, Long> {}

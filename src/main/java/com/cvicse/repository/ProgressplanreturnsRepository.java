package com.cvicse.repository;

import com.cvicse.domain.Progressplanreturns;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Progressplanreturns entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgressplanreturnsRepository extends JpaRepository<Progressplanreturns, String> {}

package com.cvicse.repository;

import com.cvicse.domain.Safetycheck;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Safetycheck entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SafetycheckRepository extends JpaRepository<Safetycheck, Long> {}

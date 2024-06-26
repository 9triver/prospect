package com.cvicse.repository;

import com.cvicse.domain.Qualityobjectives;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Qualityobjectives entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QualityobjectivesRepository extends JpaRepository<Qualityobjectives, String> {}

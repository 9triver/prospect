package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Qualitytozero;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Qualitytozero entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QualitytozeroRepository extends JpaRepository<Qualitytozero, Integer> {}

package com.cvicse.repository;

import com.cvicse.domain.Wbsbaseline;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Wbsbaseline entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WbsbaselineRepository extends JpaRepository<Wbsbaseline, Long> {}

package com.cvicse.repository;

import com.cvicse.domain.Pbsbaseline;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Pbsbaseline entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PbsbaselineRepository extends JpaRepository<Pbsbaseline, String> {}

package com.cvicse.repository;

import com.cvicse.domain.Progressbaseline;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Progressbaseline entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgressbaselineRepository extends JpaRepository<Progressbaseline, String> {}

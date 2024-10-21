package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Projectdeliverables;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Projectdeliverables entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectdeliverablesRepository extends JpaRepository<Projectdeliverables, Integer> {}

package com.cvicse.repository;

import com.cvicse.domain.Projectwbs;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Projectwbs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectwbsRepository extends JpaRepository<Projectwbs, String> {}

package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Projectwbs;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Projectwbs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectwbsRepository extends JpaRepository<Projectwbs, String> {}

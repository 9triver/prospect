package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Projectpbs;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Projectpbs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectpbsRepository extends JpaRepository<Projectpbs, String> {}

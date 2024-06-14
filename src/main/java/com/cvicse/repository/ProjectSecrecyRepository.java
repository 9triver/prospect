package com.cvicse.repository;

import com.cvicse.domain.ProjectSecrecy;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProjectSecrecy entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectSecrecyRepository extends JpaRepository<ProjectSecrecy, Long> {}

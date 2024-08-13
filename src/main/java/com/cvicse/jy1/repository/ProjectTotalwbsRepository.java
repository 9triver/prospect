package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.ProjectTotalwbs;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProjectTotalwbs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectTotalwbsRepository extends JpaRepository<ProjectTotalwbs, String> {}

package com.cvicse.repository;

import com.cvicse.domain.ProjectHumanresourcesplan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProjectHumanresourcesplan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectHumanresourcesplanRepository extends JpaRepository<ProjectHumanresourcesplan, String> {}

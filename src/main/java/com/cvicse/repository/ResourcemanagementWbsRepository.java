package com.cvicse.repository;

import com.cvicse.domain.ResourcemanagementWbs;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ResourcemanagementWbs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResourcemanagementWbsRepository extends JpaRepository<ResourcemanagementWbs, String> {}

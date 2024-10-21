package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.SystemLevel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SystemLevel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SystemLevelRepository extends JpaRepository<SystemLevel, Integer> {}

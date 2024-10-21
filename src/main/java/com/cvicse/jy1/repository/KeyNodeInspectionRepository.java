package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.KeyNodeInspection;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the KeyNodeInspection entity.
 */
@SuppressWarnings("unused")
@Repository
public interface KeyNodeInspectionRepository extends JpaRepository<KeyNodeInspection, Integer> {}

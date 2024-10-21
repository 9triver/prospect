package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.RegularInspection;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RegularInspection entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RegularInspectionRepository extends JpaRepository<RegularInspection, Integer> {}

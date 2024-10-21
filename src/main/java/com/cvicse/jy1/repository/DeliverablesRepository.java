package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Deliverables;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Deliverables entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliverablesRepository extends JpaRepository<Deliverables, Long> {}

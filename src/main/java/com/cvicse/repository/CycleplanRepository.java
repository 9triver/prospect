package com.cvicse.repository;

import com.cvicse.domain.Cycleplan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Cycleplan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CycleplanRepository extends JpaRepository<Cycleplan, Long> {}

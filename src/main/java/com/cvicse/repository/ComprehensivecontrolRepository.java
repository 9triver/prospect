package com.cvicse.repository;

import com.cvicse.domain.Comprehensivecontrol;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Comprehensivecontrol entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComprehensivecontrolRepository extends JpaRepository<Comprehensivecontrol, Long> {}

package com.cvicse.repository;

import com.cvicse.domain.Comprehensiveledger;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Comprehensiveledger entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ComprehensiveledgerRepository extends JpaRepository<Comprehensiveledger, String> {}

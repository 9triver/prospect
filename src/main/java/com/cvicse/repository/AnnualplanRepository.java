package com.cvicse.repository;

import com.cvicse.domain.Annualplan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Annualplan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnnualplanRepository extends JpaRepository<Annualplan, Long> {}

package com.cvicse.repository;

import com.cvicse.domain.Monthplan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Monthplan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MonthplanRepository extends JpaRepository<Monthplan, Long> {}

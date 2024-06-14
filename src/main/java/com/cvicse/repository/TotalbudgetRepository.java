package com.cvicse.repository;

import com.cvicse.domain.Totalbudget;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Totalbudget entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TotalbudgetRepository extends JpaRepository<Totalbudget, Long> {}

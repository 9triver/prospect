package com.cvicse.repository;

import com.cvicse.domain.Unitbudget;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Unitbudget entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UnitbudgetRepository extends JpaRepository<Unitbudget, String> {}

package com.cvicse.repository;

import com.cvicse.domain.Auditedbudget;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Auditedbudget entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuditedbudgetRepository extends JpaRepository<Auditedbudget, String> {}

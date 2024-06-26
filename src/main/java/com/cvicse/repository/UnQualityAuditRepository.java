package com.cvicse.repository;

import com.cvicse.domain.UnQualityAudit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the UnQualityAudit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UnQualityAuditRepository extends JpaRepository<UnQualityAudit, String> {}

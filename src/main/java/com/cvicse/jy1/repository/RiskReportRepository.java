package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.RiskReport;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RiskReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiskReportRepository extends JpaRepository<RiskReport, String> {}

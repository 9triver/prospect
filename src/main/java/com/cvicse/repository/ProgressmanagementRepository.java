package com.cvicse.repository;

import com.cvicse.domain.Progressmanagement;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Progressmanagement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgressmanagementRepository extends JpaRepository<Progressmanagement, Long> {}

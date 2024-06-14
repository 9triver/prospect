package com.cvicse.repository;

import com.cvicse.domain.Pbsmanage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Pbsmanage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PbsmanageRepository extends JpaRepository<Pbsmanage, Long> {}

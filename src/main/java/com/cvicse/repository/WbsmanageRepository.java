package com.cvicse.repository;

import com.cvicse.domain.Wbsmanage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Wbsmanage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WbsmanageRepository extends JpaRepository<Wbsmanage, Long> {}

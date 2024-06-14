package com.cvicse.repository;

import com.cvicse.domain.Wbssubmanage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Wbssubmanage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WbssubmanageRepository extends JpaRepository<Wbssubmanage, Long> {}

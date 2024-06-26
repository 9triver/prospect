package com.cvicse.repository;

import com.cvicse.domain.Pbssubmanage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Pbssubmanage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PbssubmanageRepository extends JpaRepository<Pbssubmanage, String> {}

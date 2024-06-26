package com.cvicse.repository;

import com.cvicse.domain.Riskreport;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Riskreport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RiskreportRepository extends JpaRepository<Riskreport, String> {}

package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Frontline;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Frontline entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FrontlineRepository extends JpaRepository<Frontline, String> {}

package com.cvicse.repository;

import com.cvicse.domain.Projectcharge;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Projectcharge entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectchargeRepository extends JpaRepository<Projectcharge, Long> {}

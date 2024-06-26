package com.cvicse.repository;

import com.cvicse.domain.Secrecysystem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Secrecysystem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecrecysystemRepository extends JpaRepository<Secrecysystem, String> {}

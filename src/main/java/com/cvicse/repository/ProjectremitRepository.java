package com.cvicse.repository;

import com.cvicse.domain.Projectremit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Projectremit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectremitRepository extends JpaRepository<Projectremit, String> {}

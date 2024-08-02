package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Projectremit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Projectremit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectremitRepository extends JpaRepository<Projectremit, String> {}

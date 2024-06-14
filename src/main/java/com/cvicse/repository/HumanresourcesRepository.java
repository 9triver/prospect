package com.cvicse.repository;

import com.cvicse.domain.Humanresources;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Humanresources entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HumanresourcesRepository extends JpaRepository<Humanresources, Long> {}

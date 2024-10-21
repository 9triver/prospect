package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Archives;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Archives entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ArchivesRepository extends JpaRepository<Archives, String> {}

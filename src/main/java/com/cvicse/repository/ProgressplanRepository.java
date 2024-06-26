package com.cvicse.repository;

import com.cvicse.domain.Progressplan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Progressplan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProgressplanRepository extends JpaRepository<Progressplan, String> {}

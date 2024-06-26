package com.cvicse.repository;

import com.cvicse.domain.Planexecute;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Planexecute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PlanexecuteRepository extends JpaRepository<Planexecute, String> {}

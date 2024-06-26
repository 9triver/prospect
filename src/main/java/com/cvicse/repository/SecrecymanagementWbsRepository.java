package com.cvicse.repository;

import com.cvicse.domain.SecrecymanagementWbs;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SecrecymanagementWbs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecrecymanagementWbsRepository extends JpaRepository<SecrecymanagementWbs, String> {}

package com.cvicse.repository;

import com.cvicse.domain.OutsourcingmanagementWbs;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OutsourcingmanagementWbs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OutsourcingmanagementWbsRepository extends JpaRepository<OutsourcingmanagementWbs, String> {}

package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.DeliveryContent;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DeliveryContent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DeliveryContentRepository extends JpaRepository<DeliveryContent, Integer> {}

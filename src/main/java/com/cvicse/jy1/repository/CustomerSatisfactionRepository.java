package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.CustomerSatisfaction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CustomerSatisfaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomerSatisfactionRepository extends JpaRepository<CustomerSatisfaction, Integer> {}

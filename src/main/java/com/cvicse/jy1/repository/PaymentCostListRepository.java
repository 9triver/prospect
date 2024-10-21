package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.PaymentCostList;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PaymentCostList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentCostListRepository extends JpaRepository<PaymentCostList, Integer> {}

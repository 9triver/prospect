package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.SporadicPurchasePayment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SporadicPurchasePayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SporadicPurchasePaymentRepository extends JpaRepository<SporadicPurchasePayment, Integer> {}

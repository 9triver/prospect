package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.OtherPayment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OtherPayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OtherPaymentRepository extends JpaRepository<OtherPayment, Integer> {}

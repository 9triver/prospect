package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.SharePayment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SharePayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SharePaymentRepository extends JpaRepository<SharePayment, Integer> {}

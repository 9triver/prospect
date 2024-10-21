package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.TransactionPayment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TransactionPayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransactionPaymentRepository extends JpaRepository<TransactionPayment, Integer> {}

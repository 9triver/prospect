package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.ContractPayment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ContractPayment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContractPaymentRepository extends JpaRepository<ContractPayment, Integer> {}

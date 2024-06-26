package com.cvicse.repository;

import com.cvicse.domain.OutsourcingPurchaseExecute;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OutsourcingPurchaseExecute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OutsourcingPurchaseExecuteRepository extends JpaRepository<OutsourcingPurchaseExecute, String> {}

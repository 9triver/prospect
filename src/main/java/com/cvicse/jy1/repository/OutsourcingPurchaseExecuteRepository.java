package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.OutsourcingPurchaseExecute;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OutsourcingPurchaseExecute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OutsourcingPurchaseExecuteRepository extends JpaRepository<OutsourcingPurchaseExecute, String> {}

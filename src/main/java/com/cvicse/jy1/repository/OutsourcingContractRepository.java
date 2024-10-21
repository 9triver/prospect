package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.OutsourcingContract;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OutsourcingContract entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OutsourcingContractRepository extends JpaRepository<OutsourcingContract, Integer> {}

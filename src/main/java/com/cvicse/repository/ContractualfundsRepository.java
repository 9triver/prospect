package com.cvicse.repository;

import com.cvicse.domain.Contractualfunds;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Contractualfunds entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContractualfundsRepository extends JpaRepository<Contractualfunds, String> {}

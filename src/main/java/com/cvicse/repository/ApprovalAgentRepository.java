package com.cvicse.repository;

import com.cvicse.domain.ApprovalAgent;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ApprovalAgent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApprovalAgentRepository extends JpaRepository<ApprovalAgent, Long> {}

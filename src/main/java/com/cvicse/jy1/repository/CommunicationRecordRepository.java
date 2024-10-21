package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.CommunicationRecord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CommunicationRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommunicationRecordRepository extends JpaRepository<CommunicationRecord, Integer> {}

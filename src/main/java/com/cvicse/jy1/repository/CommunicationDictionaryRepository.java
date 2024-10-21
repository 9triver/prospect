package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.CommunicationDictionary;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CommunicationDictionary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommunicationDictionaryRepository extends JpaRepository<CommunicationDictionary, Integer> {}

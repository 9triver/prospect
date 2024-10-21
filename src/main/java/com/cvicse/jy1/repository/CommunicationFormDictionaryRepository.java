package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.CommunicationFormDictionary;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CommunicationFormDictionary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommunicationFormDictionaryRepository extends JpaRepository<CommunicationFormDictionary, Integer> {}

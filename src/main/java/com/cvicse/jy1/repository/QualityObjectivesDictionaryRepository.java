package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.QualityObjectivesDictionary;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the QualityObjectivesDictionary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QualityObjectivesDictionaryRepository extends JpaRepository<QualityObjectivesDictionary, Integer> {}

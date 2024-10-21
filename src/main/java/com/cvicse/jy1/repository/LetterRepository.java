package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Letter;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Letter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LetterRepository extends JpaRepository<Letter, Integer> {}

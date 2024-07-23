package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Officers;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Officers entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OfficersRepository extends JpaRepository<Officers, String> {}

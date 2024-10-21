package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Documentmenu;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Documentmenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentmenuRepository extends JpaRepository<Documentmenu, Integer> {}

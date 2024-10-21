package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.FundSourceList;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FundSourceList entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FundSourceListRepository extends JpaRepository<FundSourceList, Integer> {}

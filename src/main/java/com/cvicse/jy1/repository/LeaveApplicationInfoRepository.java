package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.LeaveApplicationInfo;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the LeaveApplicationInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LeaveApplicationInfoRepository extends JpaRepository<LeaveApplicationInfo, String> {}

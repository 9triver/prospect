package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Permission;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Permission entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PermissionRepository extends JpaRepository<Permission, String> {}

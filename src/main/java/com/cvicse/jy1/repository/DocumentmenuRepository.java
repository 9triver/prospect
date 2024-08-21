package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Documentmenu;
import com.cvicse.jy1.domain.Projectpbs;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.ProjectStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Documentmenu entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentmenuRepository extends JpaRepository<Documentmenu, Integer> {

    @Query("SELECT d FROM Documentmenu d WHERE "
        + " (:menuid IS NULL AND d.menuid IS NULL OR d.menuid = :menuid) "
        + " AND  d.belongtype = :belongtype ")
    List<Documentmenu> findbyMenuid(
            @Param("menuid") String menuid,
            @Param("belongtype") String belongtype
    );
}

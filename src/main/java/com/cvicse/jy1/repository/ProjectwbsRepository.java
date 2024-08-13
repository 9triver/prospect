package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Projectwbs;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.ProjectStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Projectwbs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProjectwbsRepository extends JpaRepository<Projectwbs, String> {

    @Query("SELECT p FROM Projectwbs p WHERE "
            + "(:id IS NULL OR p.id LIKE %:id%) AND "
            + "(:wbsname IS NULL OR p.wbsname LIKE %:wbsname%) AND "
            + "(:parentwbsid IS NULL OR p.parentwbsid = :parentwbsid) AND "
            + "(:secretlevel IS NULL OR p.secretlevel = :secretlevel) AND "
            + "(:starttime IS NULL OR p.starttime >= :starttime) AND "
            + "(:endtime IS NULL OR p.endtime <= :endtime) AND "
            + "(:description IS NULL OR p.description LIKE %:description%) AND "
            + "(:progress IS NULL OR p.progress = :progress) AND "
            + "(:type IS NULL OR p.type = :type) AND "
            + "(:priorty IS NULL OR p.priorty = :priorty) AND "
            + "(:status IS NULL OR p.status = :status) AND "
            + "(:auditStatus IS NULL OR p.auditStatus = :auditStatus)")
    List<Projectwbs> findAllWithToOneRelationship(
            @Param("id") String id,
            @Param("wbsname") String wbsname,
            @Param("parentwbsid") String parentwbsid,
            @Param("secretlevel") Secretlevel secretlevel,
            @Param("starttime") String starttime,
            @Param("endtime") String endtime,
            @Param("description") String description,
            @Param("progress") Integer progress,
            @Param("type") Integer type,
            @Param("priorty") Integer priorty,
            @Param("status") ProjectStatus status,
            @Param("auditStatus") AuditStatus auditStatus
    );

}

package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.ProgressPlan;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.PlanLevel;
import com.cvicse.jy1.domain.enumeration.Planstatus;
import com.cvicse.jy1.domain.enumeration.Progressstatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for the ProgressPlan entity.
 *
 * When extending this class, extend ProgressPlanRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface ProgressPlanRepository extends ProgressPlanRepositoryWithBagRelationships, JpaRepository<ProgressPlan, String> {
    default Optional<ProgressPlan> findOneWithEagerRelationships(String id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<ProgressPlan> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<ProgressPlan> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
    
    @Query("SELECT p FROM ProgressPlan p WHERE "
            + "(:id IS NULL OR p.id LIKE %:id%) AND "
            + "(:planname IS NULL OR p.planname LIKE %:planname%) AND "
            + "(:belongplanid IS NULL OR p.belongplanid = :belongplanid) AND "
            + "(:secretlevel IS NULL OR p.secretlevel = :secretlevel) AND "
            + "(:starttime IS NULL OR p.starttime >= :starttime) AND "
            + "(:endtime IS NULL OR p.endtime <= :endtime) AND "
            + "(:planlevel IS NULL OR p.planlevel = :planlevel) AND "
            + "(:iskey IS NULL OR p.iskey = :iskey) AND "
            + "(:plantype IS NULL OR p.plantype = :plantype) AND "
            + "(:description IS NULL OR p.description LIKE %:description%) AND "
            + "(:progress IS NULL OR p.progress = :progress) AND "
            + "(:progresstype IS NULL OR p.progresstype = :progresstype) AND "
            + "(:status IS NULL OR p.status = :status) AND "
            + "(:auditStatus IS NULL OR p.auditStatus = :auditStatus)")
    List<ProgressPlan> findAllWithToOneRelationship2(
            @Param("id") String id,
            @Param("planname") String planname,
            @Param("belongplanid") String belongplanid,
            @Param("secretlevel") Secretlevel secretlevel,
            @Param("starttime") String starttime,
            @Param("endtime") String endtime,
            @Param("planlevel") PlanLevel planlevel,
            @Param("iskey") Integer iskey,
            @Param("plantype") Integer plantype,
            @Param("description") String description,
            @Param("progress") Integer progress,
            @Param("progresstype") Progressstatus progresstype,
            @Param("status") Planstatus status,
            @Param("auditStatus") AuditStatus auditStatus
    );

    @Query("SELECT p FROM ProgressPlan p WHERE "
            + "(:id IS NULL OR p.id LIKE %:id%) AND "
            + "(:planname IS NULL OR p.planname LIKE %:planname%) AND "
            + "(:secretlevel IS NULL OR p.secretlevel = :secretlevel) AND "
            + "(:progress IS NULL OR p.progress = :progress) AND "
            + "(:plantype IS NULL OR p.plantype = :plantype) AND "
            + "(:status IS NULL OR p.status = :status) AND "
            + "(:auditStatus IS NULL OR p.auditStatus = :auditStatus)")
    List<ProgressPlan> findAllWithToOneRelationship(
            @Param("id") String id,
            @Param("planname") String planname,
            @Param("secretlevel") Secretlevel secretlevel,
            @Param("progress") Integer progress,
            @Param("plantype") Integer plantype,
            @Param("status") Planstatus status,
            @Param("auditStatus") AuditStatus auditStatus
    );
}

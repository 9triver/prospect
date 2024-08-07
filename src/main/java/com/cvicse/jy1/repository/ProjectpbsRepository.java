package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Projectpbs;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.domain.enumeration.ProjectStatus;
import com.cvicse.jy1.domain.enumeration.AuditStatus;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

/**
 * Spring Data JPA repository for the Projectpbs entity.
 *
 * When extending this class, extend ProjectpbsRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface ProjectpbsRepository extends ProjectpbsRepositoryWithBagRelationships, JpaRepository<Projectpbs, String> {
    default Optional<Projectpbs> findOneWithEagerRelationships(String id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<Projectpbs> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<Projectpbs> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }

    @Query("SELECT p FROM Projectpbs p WHERE "
            + "(:id IS NULL OR p.id LIKE %:pbsname%) AND "
            + "(:pbsname IS NULL OR p.pbsname LIKE %:pbsname%) AND "
            + "(:parentpbsid IS NULL OR p.parentpbsid = :parentpbsid) AND "
            + "(:secretlevel IS NULL OR p.secretlevel = :secretlevel) AND "
            + "(:starttime IS NULL OR p.starttime >= :starttime) AND "
            + "(:endtime IS NULL OR p.endtime <= :endtime) AND "
            + "(:productlevel IS NULL OR p.productlevel = :productlevel) AND "
            + "(:ifkey IS NULL OR p.ifkey = :ifkey) AND "
            + "(:ifimporttant IS NULL OR p.ifimporttant = :ifimporttant) AND "
            + "(:description IS NULL OR p.description LIKE %:description%) AND "
            + "(:progress IS NULL OR p.progress = :progress) AND "
            + "(:type IS NULL OR p.type = :type) AND "
            + "(:priorty IS NULL OR p.priorty = :priorty) AND "
            + "(:status IS NULL OR p.status = :status) AND "
            + "(:auditStatus IS NULL OR p.auditStatus = :auditStatus)")
    List<Projectpbs> findAllWithToOneRelationship(
            @Param("id") String id,
            @Param("pbsname") String pbsname,
            @Param("parentpbsid") String parentpbsid,
            @Param("secretlevel") Secretlevel secretlevel,
            @Param("starttime") String starttime,
            @Param("endtime") String endtime,
            @Param("productlevel") Integer productlevel,
            @Param("ifkey") Integer ifkey,
            @Param("ifimporttant") Integer ifimporttant,
            @Param("description") String description,
            @Param("progress") Integer progress,
            @Param("type") Integer type,
            @Param("priorty") Integer priorty,
            @Param("status") ProjectStatus status,
            @Param("auditStatus") AuditStatus auditStatus
    );
}

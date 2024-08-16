package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Officers;
import com.cvicse.jy1.domain.enumeration.OfficersStatus;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Officers entity.
 *
 * When extending this class, extend OfficersRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface OfficersRepository extends OfficersRepositoryWithBagRelationships, JpaRepository<Officers, String> {
    default Optional<Officers> findOneWithEagerRelationships(String id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<Officers> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<Officers> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }

    @Query("SELECT o FROM Officers o WHERE "
            + "(:id IS NULL OR CAST(o.id AS string) LIKE %:id%) AND "
            + "(:name IS NULL OR o.name LIKE %:name%) AND "
            + "(:years IS NULL OR o.years = :years) AND "
            + "(:hiredate IS NULL OR o.hiredate >= :hiredate) AND "
            + "(:status IS NULL OR o.status = :status)")
    List<Officers> findAllWithToOneRelationship(
            @Param("id") String id,
            @Param("name") String name,
            @Param("years") Integer years,
            @Param("hiredate") LocalDate hiredate,
            @Param("status") OfficersStatus status
    );
    
}

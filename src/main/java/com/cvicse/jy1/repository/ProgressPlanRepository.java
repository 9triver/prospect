package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.ProgressPlan;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

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
}

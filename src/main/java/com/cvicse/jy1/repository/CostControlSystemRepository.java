package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.CostControlSystem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CostControlSystem entity.
 *
 * When extending this class, extend CostControlSystemRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface CostControlSystemRepository
    extends CostControlSystemRepositoryWithBagRelationships, JpaRepository<CostControlSystem, String> {
    default Optional<CostControlSystem> findOneWithEagerRelationships(String id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<CostControlSystem> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<CostControlSystem> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}

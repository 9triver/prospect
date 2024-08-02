package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.FundsEstimation;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the FundsEstimation entity.
 *
 * When extending this class, extend FundsEstimationRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface FundsEstimationRepository extends FundsEstimationRepositoryWithBagRelationships, JpaRepository<FundsEstimation, String> {
    default Optional<FundsEstimation> findOneWithEagerRelationships(String id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<FundsEstimation> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<FundsEstimation> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}

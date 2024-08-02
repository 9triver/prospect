package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.OutsourcingPurchasePlan;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OutsourcingPurchasePlan entity.
 *
 * When extending this class, extend OutsourcingPurchasePlanRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface OutsourcingPurchasePlanRepository
    extends OutsourcingPurchasePlanRepositoryWithBagRelationships, JpaRepository<OutsourcingPurchasePlan, String> {
    default Optional<OutsourcingPurchasePlan> findOneWithEagerRelationships(String id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<OutsourcingPurchasePlan> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<OutsourcingPurchasePlan> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}

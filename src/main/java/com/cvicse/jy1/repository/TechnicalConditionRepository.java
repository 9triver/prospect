package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.TechnicalCondition;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the TechnicalCondition entity.
 *
 * When extending this class, extend TechnicalConditionRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface TechnicalConditionRepository
    extends TechnicalConditionRepositoryWithBagRelationships, JpaRepository<TechnicalCondition, String> {
    default Optional<TechnicalCondition> findOneWithEagerRelationships(String id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<TechnicalCondition> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<TechnicalCondition> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}

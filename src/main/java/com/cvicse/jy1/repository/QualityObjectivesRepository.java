package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.QualityObjectives;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the QualityObjectives entity.
 *
 * When extending this class, extend QualityObjectivesRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface QualityObjectivesRepository
    extends QualityObjectivesRepositoryWithBagRelationships, JpaRepository<QualityObjectives, String> {
    default Optional<QualityObjectives> findOneWithEagerRelationships(String id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<QualityObjectives> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<QualityObjectives> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}

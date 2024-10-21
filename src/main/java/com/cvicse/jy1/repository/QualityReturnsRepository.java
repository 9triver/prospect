package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.QualityReturns;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the QualityReturns entity.
 *
 * When extending this class, extend QualityReturnsRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface QualityReturnsRepository extends QualityReturnsRepositoryWithBagRelationships, JpaRepository<QualityReturns, Integer> {
    default Optional<QualityReturns> findOneWithEagerRelationships(Integer id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<QualityReturns> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<QualityReturns> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}

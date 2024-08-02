package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.OutsourcingContractual;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OutsourcingContractual entity.
 *
 * When extending this class, extend OutsourcingContractualRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface OutsourcingContractualRepository
    extends OutsourcingContractualRepositoryWithBagRelationships, JpaRepository<OutsourcingContractual, String> {
    default Optional<OutsourcingContractual> findOneWithEagerRelationships(String id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<OutsourcingContractual> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<OutsourcingContractual> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}

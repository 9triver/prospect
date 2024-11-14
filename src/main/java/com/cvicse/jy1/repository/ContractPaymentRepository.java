package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.ContractPayment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ContractPayment entity.
 *
 * When extending this class, extend ContractPaymentRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface ContractPaymentRepository extends ContractPaymentRepositoryWithBagRelationships, JpaRepository<ContractPayment, Integer> {
    default Optional<ContractPayment> findOneWithEagerRelationships(Integer id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<ContractPayment> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<ContractPayment> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}

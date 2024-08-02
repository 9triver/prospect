package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.OutsourcingContractual;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class OutsourcingContractualRepositoryWithBagRelationshipsImpl implements OutsourcingContractualRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String OUTSOURCINGCONTRACTUALS_PARAMETER = "outsourcingContractuals";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<OutsourcingContractual> fetchBagRelationships(Optional<OutsourcingContractual> outsourcingContractual) {
        return outsourcingContractual.map(this::fetchProjectwbs);
    }

    @Override
    public Page<OutsourcingContractual> fetchBagRelationships(Page<OutsourcingContractual> outsourcingContractuals) {
        return new PageImpl<>(
            fetchBagRelationships(outsourcingContractuals.getContent()),
            outsourcingContractuals.getPageable(),
            outsourcingContractuals.getTotalElements()
        );
    }

    @Override
    public List<OutsourcingContractual> fetchBagRelationships(List<OutsourcingContractual> outsourcingContractuals) {
        return Optional.of(outsourcingContractuals).map(this::fetchProjectwbs).orElse(Collections.emptyList());
    }

    OutsourcingContractual fetchProjectwbs(OutsourcingContractual result) {
        return entityManager
            .createQuery(
                "select outsourcingContractual from OutsourcingContractual outsourcingContractual left join fetch outsourcingContractual.projectwbs where outsourcingContractual.id = :id",
                OutsourcingContractual.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<OutsourcingContractual> fetchProjectwbs(List<OutsourcingContractual> outsourcingContractuals) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, outsourcingContractuals.size()).forEach(index -> order.put(outsourcingContractuals.get(index).getId(), index));
        List<OutsourcingContractual> result = entityManager
            .createQuery(
                "select outsourcingContractual from OutsourcingContractual outsourcingContractual left join fetch outsourcingContractual.projectwbs where outsourcingContractual in :outsourcingContractuals",
                OutsourcingContractual.class
            )
            .setParameter(OUTSOURCINGCONTRACTUALS_PARAMETER, outsourcingContractuals)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

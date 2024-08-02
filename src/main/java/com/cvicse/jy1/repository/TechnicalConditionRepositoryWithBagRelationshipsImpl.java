package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.TechnicalCondition;
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
public class TechnicalConditionRepositoryWithBagRelationshipsImpl implements TechnicalConditionRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String TECHNICALCONDITIONS_PARAMETER = "technicalConditions";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<TechnicalCondition> fetchBagRelationships(Optional<TechnicalCondition> technicalCondition) {
        return technicalCondition.map(this::fetchProjectwbs);
    }

    @Override
    public Page<TechnicalCondition> fetchBagRelationships(Page<TechnicalCondition> technicalConditions) {
        return new PageImpl<>(
            fetchBagRelationships(technicalConditions.getContent()),
            technicalConditions.getPageable(),
            technicalConditions.getTotalElements()
        );
    }

    @Override
    public List<TechnicalCondition> fetchBagRelationships(List<TechnicalCondition> technicalConditions) {
        return Optional.of(technicalConditions).map(this::fetchProjectwbs).orElse(Collections.emptyList());
    }

    TechnicalCondition fetchProjectwbs(TechnicalCondition result) {
        return entityManager
            .createQuery(
                "select technicalCondition from TechnicalCondition technicalCondition left join fetch technicalCondition.projectwbs where technicalCondition.id = :id",
                TechnicalCondition.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<TechnicalCondition> fetchProjectwbs(List<TechnicalCondition> technicalConditions) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, technicalConditions.size()).forEach(index -> order.put(technicalConditions.get(index).getId(), index));
        List<TechnicalCondition> result = entityManager
            .createQuery(
                "select technicalCondition from TechnicalCondition technicalCondition left join fetch technicalCondition.projectwbs where technicalCondition in :technicalConditions",
                TechnicalCondition.class
            )
            .setParameter(TECHNICALCONDITIONS_PARAMETER, technicalConditions)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

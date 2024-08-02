package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.FundsEstimation;
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
public class FundsEstimationRepositoryWithBagRelationshipsImpl implements FundsEstimationRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String FUNDSESTIMATIONS_PARAMETER = "fundsEstimations";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<FundsEstimation> fetchBagRelationships(Optional<FundsEstimation> fundsEstimation) {
        return fundsEstimation.map(this::fetchProjectwbs);
    }

    @Override
    public Page<FundsEstimation> fetchBagRelationships(Page<FundsEstimation> fundsEstimations) {
        return new PageImpl<>(
            fetchBagRelationships(fundsEstimations.getContent()),
            fundsEstimations.getPageable(),
            fundsEstimations.getTotalElements()
        );
    }

    @Override
    public List<FundsEstimation> fetchBagRelationships(List<FundsEstimation> fundsEstimations) {
        return Optional.of(fundsEstimations).map(this::fetchProjectwbs).orElse(Collections.emptyList());
    }

    FundsEstimation fetchProjectwbs(FundsEstimation result) {
        return entityManager
            .createQuery(
                "select fundsEstimation from FundsEstimation fundsEstimation left join fetch fundsEstimation.projectwbs where fundsEstimation.id = :id",
                FundsEstimation.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<FundsEstimation> fetchProjectwbs(List<FundsEstimation> fundsEstimations) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, fundsEstimations.size()).forEach(index -> order.put(fundsEstimations.get(index).getId(), index));
        List<FundsEstimation> result = entityManager
            .createQuery(
                "select fundsEstimation from FundsEstimation fundsEstimation left join fetch fundsEstimation.projectwbs where fundsEstimation in :fundsEstimations",
                FundsEstimation.class
            )
            .setParameter(FUNDSESTIMATIONS_PARAMETER, fundsEstimations)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

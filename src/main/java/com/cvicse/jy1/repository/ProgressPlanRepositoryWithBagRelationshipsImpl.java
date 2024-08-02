package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.ProgressPlan;
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
public class ProgressPlanRepositoryWithBagRelationshipsImpl implements ProgressPlanRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String PROGRESSPLANS_PARAMETER = "progressPlans";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<ProgressPlan> fetchBagRelationships(Optional<ProgressPlan> progressPlan) {
        return progressPlan.map(this::fetchProjectwbs).map(this::fetchProjectRisks);
    }

    @Override
    public Page<ProgressPlan> fetchBagRelationships(Page<ProgressPlan> progressPlans) {
        return new PageImpl<>(
            fetchBagRelationships(progressPlans.getContent()),
            progressPlans.getPageable(),
            progressPlans.getTotalElements()
        );
    }

    @Override
    public List<ProgressPlan> fetchBagRelationships(List<ProgressPlan> progressPlans) {
        return Optional.of(progressPlans).map(this::fetchProjectwbs).map(this::fetchProjectRisks).orElse(Collections.emptyList());
    }

    ProgressPlan fetchProjectwbs(ProgressPlan result) {
        return entityManager
            .createQuery(
                "select progressPlan from ProgressPlan progressPlan left join fetch progressPlan.projectwbs where progressPlan.id = :id",
                ProgressPlan.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<ProgressPlan> fetchProjectwbs(List<ProgressPlan> progressPlans) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, progressPlans.size()).forEach(index -> order.put(progressPlans.get(index).getId(), index));
        List<ProgressPlan> result = entityManager
            .createQuery(
                "select progressPlan from ProgressPlan progressPlan left join fetch progressPlan.projectwbs where progressPlan in :progressPlans",
                ProgressPlan.class
            )
            .setParameter(PROGRESSPLANS_PARAMETER, progressPlans)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    ProgressPlan fetchProjectRisks(ProgressPlan result) {
        return entityManager
            .createQuery(
                "select progressPlan from ProgressPlan progressPlan left join fetch progressPlan.projectRisks where progressPlan.id = :id",
                ProgressPlan.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<ProgressPlan> fetchProjectRisks(List<ProgressPlan> progressPlans) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, progressPlans.size()).forEach(index -> order.put(progressPlans.get(index).getId(), index));
        List<ProgressPlan> result = entityManager
            .createQuery(
                "select progressPlan from ProgressPlan progressPlan left join fetch progressPlan.projectRisks where progressPlan in :progressPlans",
                ProgressPlan.class
            )
            .setParameter(PROGRESSPLANS_PARAMETER, progressPlans)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

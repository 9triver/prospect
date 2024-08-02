package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.OutsourcingPurchasePlan;
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
public class OutsourcingPurchasePlanRepositoryWithBagRelationshipsImpl implements OutsourcingPurchasePlanRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String OUTSOURCINGPURCHASEPLANS_PARAMETER = "outsourcingPurchasePlans";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<OutsourcingPurchasePlan> fetchBagRelationships(Optional<OutsourcingPurchasePlan> outsourcingPurchasePlan) {
        return outsourcingPurchasePlan.map(this::fetchProjectwbs);
    }

    @Override
    public Page<OutsourcingPurchasePlan> fetchBagRelationships(Page<OutsourcingPurchasePlan> outsourcingPurchasePlans) {
        return new PageImpl<>(
            fetchBagRelationships(outsourcingPurchasePlans.getContent()),
            outsourcingPurchasePlans.getPageable(),
            outsourcingPurchasePlans.getTotalElements()
        );
    }

    @Override
    public List<OutsourcingPurchasePlan> fetchBagRelationships(List<OutsourcingPurchasePlan> outsourcingPurchasePlans) {
        return Optional.of(outsourcingPurchasePlans).map(this::fetchProjectwbs).orElse(Collections.emptyList());
    }

    OutsourcingPurchasePlan fetchProjectwbs(OutsourcingPurchasePlan result) {
        return entityManager
            .createQuery(
                "select outsourcingPurchasePlan from OutsourcingPurchasePlan outsourcingPurchasePlan left join fetch outsourcingPurchasePlan.projectwbs where outsourcingPurchasePlan.id = :id",
                OutsourcingPurchasePlan.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<OutsourcingPurchasePlan> fetchProjectwbs(List<OutsourcingPurchasePlan> outsourcingPurchasePlans) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, outsourcingPurchasePlans.size()).forEach(index -> order.put(outsourcingPurchasePlans.get(index).getId(), index));
        List<OutsourcingPurchasePlan> result = entityManager
            .createQuery(
                "select outsourcingPurchasePlan from OutsourcingPurchasePlan outsourcingPurchasePlan left join fetch outsourcingPurchasePlan.projectwbs where outsourcingPurchasePlan in :outsourcingPurchasePlans",
                OutsourcingPurchasePlan.class
            )
            .setParameter(OUTSOURCINGPURCHASEPLANS_PARAMETER, outsourcingPurchasePlans)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

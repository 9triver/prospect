package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.CostControlSystem;
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
public class CostControlSystemRepositoryWithBagRelationshipsImpl implements CostControlSystemRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String COSTCONTROLSYSTEMS_PARAMETER = "costControlSystems";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<CostControlSystem> fetchBagRelationships(Optional<CostControlSystem> costControlSystem) {
        return costControlSystem.map(this::fetchProjectwbs).map(this::fetchContracts);
    }

    @Override
    public Page<CostControlSystem> fetchBagRelationships(Page<CostControlSystem> costControlSystems) {
        return new PageImpl<>(
            fetchBagRelationships(costControlSystems.getContent()),
            costControlSystems.getPageable(),
            costControlSystems.getTotalElements()
        );
    }

    @Override
    public List<CostControlSystem> fetchBagRelationships(List<CostControlSystem> costControlSystems) {
        return Optional.of(costControlSystems).map(this::fetchProjectwbs).map(this::fetchContracts).orElse(Collections.emptyList());
    }

    CostControlSystem fetchProjectwbs(CostControlSystem result) {
        return entityManager
            .createQuery(
                "select costControlSystem from CostControlSystem costControlSystem left join fetch costControlSystem.projectwbs where costControlSystem.id = :id",
                CostControlSystem.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<CostControlSystem> fetchProjectwbs(List<CostControlSystem> costControlSystems) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, costControlSystems.size()).forEach(index -> order.put(costControlSystems.get(index).getId(), index));
        List<CostControlSystem> result = entityManager
            .createQuery(
                "select costControlSystem from CostControlSystem costControlSystem left join fetch costControlSystem.projectwbs where costControlSystem in :costControlSystems",
                CostControlSystem.class
            )
            .setParameter(COSTCONTROLSYSTEMS_PARAMETER, costControlSystems)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    CostControlSystem fetchContracts(CostControlSystem result) {
        return entityManager
            .createQuery(
                "select costControlSystem from CostControlSystem costControlSystem left join fetch costControlSystem.contracts where costControlSystem.id = :id",
                CostControlSystem.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<CostControlSystem> fetchContracts(List<CostControlSystem> costControlSystems) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, costControlSystems.size()).forEach(index -> order.put(costControlSystems.get(index).getId(), index));
        List<CostControlSystem> result = entityManager
            .createQuery(
                "select costControlSystem from CostControlSystem costControlSystem left join fetch costControlSystem.contracts where costControlSystem in :costControlSystems",
                CostControlSystem.class
            )
            .setParameter(COSTCONTROLSYSTEMS_PARAMETER, costControlSystems)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

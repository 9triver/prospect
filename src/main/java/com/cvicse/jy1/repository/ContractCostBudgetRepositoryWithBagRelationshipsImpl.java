package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.ContractCostBudget;
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
public class ContractCostBudgetRepositoryWithBagRelationshipsImpl implements ContractCostBudgetRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String CONTRACTCOSTBUDGETS_PARAMETER = "contractCostBudgets";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<ContractCostBudget> fetchBagRelationships(Optional<ContractCostBudget> contractCostBudget) {
        return contractCostBudget.map(this::fetchProjectwbs);
    }

    @Override
    public Page<ContractCostBudget> fetchBagRelationships(Page<ContractCostBudget> contractCostBudgets) {
        return new PageImpl<>(
            fetchBagRelationships(contractCostBudgets.getContent()),
            contractCostBudgets.getPageable(),
            contractCostBudgets.getTotalElements()
        );
    }

    @Override
    public List<ContractCostBudget> fetchBagRelationships(List<ContractCostBudget> contractCostBudgets) {
        return Optional.of(contractCostBudgets).map(this::fetchProjectwbs).orElse(Collections.emptyList());
    }

    ContractCostBudget fetchProjectwbs(ContractCostBudget result) {
        return entityManager
            .createQuery(
                "select contractCostBudget from ContractCostBudget contractCostBudget left join fetch contractCostBudget.projectwbs where contractCostBudget.id = :id",
                ContractCostBudget.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<ContractCostBudget> fetchProjectwbs(List<ContractCostBudget> contractCostBudgets) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, contractCostBudgets.size()).forEach(index -> order.put(contractCostBudgets.get(index).getId(), index));
        List<ContractCostBudget> result = entityManager
            .createQuery(
                "select contractCostBudget from ContractCostBudget contractCostBudget left join fetch contractCostBudget.projectwbs where contractCostBudget in :contractCostBudgets",
                ContractCostBudget.class
            )
            .setParameter(CONTRACTCOSTBUDGETS_PARAMETER, contractCostBudgets)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.OutsourcingContract;
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
public class OutsourcingContractRepositoryWithBagRelationshipsImpl implements OutsourcingContractRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String OUTSOURCINGCONTRACTS_PARAMETER = "outsourcingContracts";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<OutsourcingContract> fetchBagRelationships(Optional<OutsourcingContract> outsourcingContract) {
        return outsourcingContract.map(this::fetchMilestoneNodes);
    }

    @Override
    public Page<OutsourcingContract> fetchBagRelationships(Page<OutsourcingContract> outsourcingContracts) {
        return new PageImpl<>(
            fetchBagRelationships(outsourcingContracts.getContent()),
            outsourcingContracts.getPageable(),
            outsourcingContracts.getTotalElements()
        );
    }

    @Override
    public List<OutsourcingContract> fetchBagRelationships(List<OutsourcingContract> outsourcingContracts) {
        return Optional.of(outsourcingContracts).map(this::fetchMilestoneNodes).orElse(Collections.emptyList());
    }

    OutsourcingContract fetchMilestoneNodes(OutsourcingContract result) {
        return entityManager
            .createQuery(
                "select outsourcingContract from OutsourcingContract outsourcingContract left join fetch outsourcingContract.milestoneNodes where outsourcingContract.id = :id",
                OutsourcingContract.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<OutsourcingContract> fetchMilestoneNodes(List<OutsourcingContract> outsourcingContracts) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, outsourcingContracts.size()).forEach(index -> order.put(outsourcingContracts.get(index).getId(), index));
        List<OutsourcingContract> result = entityManager
            .createQuery(
                "select outsourcingContract from OutsourcingContract outsourcingContract left join fetch outsourcingContract.milestoneNodes where outsourcingContract in :outsourcingContracts",
                OutsourcingContract.class
            )
            .setParameter(OUTSOURCINGCONTRACTS_PARAMETER, outsourcingContracts)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

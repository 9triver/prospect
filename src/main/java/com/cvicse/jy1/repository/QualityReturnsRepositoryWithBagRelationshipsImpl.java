package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.QualityReturns;
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
public class QualityReturnsRepositoryWithBagRelationshipsImpl implements QualityReturnsRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String QUALITYRETURNS_PARAMETER = "qualityReturns";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<QualityReturns> fetchBagRelationships(Optional<QualityReturns> qualityReturns) {
        return qualityReturns.map(this::fetchQualityObjectives);
    }

    @Override
    public Page<QualityReturns> fetchBagRelationships(Page<QualityReturns> qualityReturns) {
        return new PageImpl<>(
            fetchBagRelationships(qualityReturns.getContent()),
            qualityReturns.getPageable(),
            qualityReturns.getTotalElements()
        );
    }

    @Override
    public List<QualityReturns> fetchBagRelationships(List<QualityReturns> qualityReturns) {
        return Optional.of(qualityReturns).map(this::fetchQualityObjectives).orElse(Collections.emptyList());
    }

    QualityReturns fetchQualityObjectives(QualityReturns result) {
        return entityManager
            .createQuery(
                "select qualityReturns from QualityReturns qualityReturns left join fetch qualityReturns.qualityObjectives where qualityReturns.id = :id",
                QualityReturns.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<QualityReturns> fetchQualityObjectives(List<QualityReturns> qualityReturns) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, qualityReturns.size()).forEach(index -> order.put(qualityReturns.get(index).getId(), index));
        List<QualityReturns> result = entityManager
            .createQuery(
                "select qualityReturns from QualityReturns qualityReturns left join fetch qualityReturns.qualityObjectives where qualityReturns in :qualityReturns",
                QualityReturns.class
            )
            .setParameter(QUALITYRETURNS_PARAMETER, qualityReturns)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

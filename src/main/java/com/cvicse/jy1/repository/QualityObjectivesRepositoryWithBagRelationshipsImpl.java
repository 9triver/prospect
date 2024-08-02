package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.QualityObjectives;
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
public class QualityObjectivesRepositoryWithBagRelationshipsImpl implements QualityObjectivesRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String QUALITYOBJECTIVES_PARAMETER = "qualityObjectives";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<QualityObjectives> fetchBagRelationships(Optional<QualityObjectives> qualityObjectives) {
        return qualityObjectives.map(this::fetchProjectwbs);
    }

    @Override
    public Page<QualityObjectives> fetchBagRelationships(Page<QualityObjectives> qualityObjectives) {
        return new PageImpl<>(
            fetchBagRelationships(qualityObjectives.getContent()),
            qualityObjectives.getPageable(),
            qualityObjectives.getTotalElements()
        );
    }

    @Override
    public List<QualityObjectives> fetchBagRelationships(List<QualityObjectives> qualityObjectives) {
        return Optional.of(qualityObjectives).map(this::fetchProjectwbs).orElse(Collections.emptyList());
    }

    QualityObjectives fetchProjectwbs(QualityObjectives result) {
        return entityManager
            .createQuery(
                "select qualityObjectives from QualityObjectives qualityObjectives left join fetch qualityObjectives.projectwbs where qualityObjectives.id = :id",
                QualityObjectives.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<QualityObjectives> fetchProjectwbs(List<QualityObjectives> qualityObjectives) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, qualityObjectives.size()).forEach(index -> order.put(qualityObjectives.get(index).getId(), index));
        List<QualityObjectives> result = entityManager
            .createQuery(
                "select qualityObjectives from QualityObjectives qualityObjectives left join fetch qualityObjectives.projectwbs where qualityObjectives in :qualityObjectives",
                QualityObjectives.class
            )
            .setParameter(QUALITYOBJECTIVES_PARAMETER, qualityObjectives)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

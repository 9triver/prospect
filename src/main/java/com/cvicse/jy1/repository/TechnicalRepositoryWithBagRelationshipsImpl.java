package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Technical;
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
public class TechnicalRepositoryWithBagRelationshipsImpl implements TechnicalRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String TECHNICALS_PARAMETER = "technicals";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Technical> fetchBagRelationships(Optional<Technical> technical) {
        return technical.map(this::fetchProjectwbs);
    }

    @Override
    public Page<Technical> fetchBagRelationships(Page<Technical> technicals) {
        return new PageImpl<>(fetchBagRelationships(technicals.getContent()), technicals.getPageable(), technicals.getTotalElements());
    }

    @Override
    public List<Technical> fetchBagRelationships(List<Technical> technicals) {
        return Optional.of(technicals).map(this::fetchProjectwbs).orElse(Collections.emptyList());
    }

    Technical fetchProjectwbs(Technical result) {
        return entityManager
            .createQuery(
                "select technical from Technical technical left join fetch technical.projectwbs where technical.id = :id",
                Technical.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Technical> fetchProjectwbs(List<Technical> technicals) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, technicals.size()).forEach(index -> order.put(technicals.get(index).getId(), index));
        List<Technical> result = entityManager
            .createQuery(
                "select technical from Technical technical left join fetch technical.projectwbs where technical in :technicals",
                Technical.class
            )
            .setParameter(TECHNICALS_PARAMETER, technicals)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

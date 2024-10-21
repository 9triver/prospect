package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Projectpbs;
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
public class ProjectpbsRepositoryWithBagRelationshipsImpl implements ProjectpbsRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String PROJECTPBS_PARAMETER = "projectpbs";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Projectpbs> fetchBagRelationships(Optional<Projectpbs> projectpbs) {
        return projectpbs.map(this::fetchRelevantdepartments);
    }

    @Override
    public Page<Projectpbs> fetchBagRelationships(Page<Projectpbs> projectpbs) {
        return new PageImpl<>(fetchBagRelationships(projectpbs.getContent()), projectpbs.getPageable(), projectpbs.getTotalElements());
    }

    @Override
    public List<Projectpbs> fetchBagRelationships(List<Projectpbs> projectpbs) {
        return Optional.of(projectpbs).map(this::fetchRelevantdepartments).orElse(Collections.emptyList());
    }

    Projectpbs fetchRelevantdepartments(Projectpbs result) {
        return entityManager
            .createQuery(
                "select projectpbs from Projectpbs projectpbs left join fetch projectpbs.relevantdepartments where projectpbs.id = :id",
                Projectpbs.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Projectpbs> fetchRelevantdepartments(List<Projectpbs> projectpbs) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, projectpbs.size()).forEach(index -> order.put(projectpbs.get(index).getId(), index));
        List<Projectpbs> result = entityManager
            .createQuery(
                "select projectpbs from Projectpbs projectpbs left join fetch projectpbs.relevantdepartments where projectpbs in :projectpbs",
                Projectpbs.class
            )
            .setParameter(PROJECTPBS_PARAMETER, projectpbs)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

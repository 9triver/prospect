package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Projectwbs;
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
public class ProjectwbsRepositoryWithBagRelationshipsImpl implements ProjectwbsRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String PROJECTWBS_PARAMETER = "projectwbs";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Projectwbs> fetchBagRelationships(Optional<Projectwbs> projectwbs) {
        return projectwbs.map(this::fetchProjectdeliverables).map(this::fetchRelevantdepartments);
    }

    @Override
    public Page<Projectwbs> fetchBagRelationships(Page<Projectwbs> projectwbs) {
        return new PageImpl<>(fetchBagRelationships(projectwbs.getContent()), projectwbs.getPageable(), projectwbs.getTotalElements());
    }

    @Override
    public List<Projectwbs> fetchBagRelationships(List<Projectwbs> projectwbs) {
        return Optional.of(projectwbs)
            .map(this::fetchProjectdeliverables)
            .map(this::fetchRelevantdepartments)
            .orElse(Collections.emptyList());
    }

    Projectwbs fetchProjectdeliverables(Projectwbs result) {
        return entityManager
            .createQuery(
                "select projectwbs from Projectwbs projectwbs left join fetch projectwbs.projectdeliverables where projectwbs.id = :id",
                Projectwbs.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Projectwbs> fetchProjectdeliverables(List<Projectwbs> projectwbs) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, projectwbs.size()).forEach(index -> order.put(projectwbs.get(index).getId(), index));
        List<Projectwbs> result = entityManager
            .createQuery(
                "select projectwbs from Projectwbs projectwbs left join fetch projectwbs.projectdeliverables where projectwbs in :projectwbs",
                Projectwbs.class
            )
            .setParameter(PROJECTWBS_PARAMETER, projectwbs)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    Projectwbs fetchRelevantdepartments(Projectwbs result) {
        return entityManager
            .createQuery(
                "select projectwbs from Projectwbs projectwbs left join fetch projectwbs.relevantdepartments where projectwbs.id = :id",
                Projectwbs.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Projectwbs> fetchRelevantdepartments(List<Projectwbs> projectwbs) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, projectwbs.size()).forEach(index -> order.put(projectwbs.get(index).getId(), index));
        List<Projectwbs> result = entityManager
            .createQuery(
                "select projectwbs from Projectwbs projectwbs left join fetch projectwbs.relevantdepartments where projectwbs in :projectwbs",
                Projectwbs.class
            )
            .setParameter(PROJECTWBS_PARAMETER, projectwbs)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

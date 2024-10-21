package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Workbag;
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
public class WorkbagRepositoryWithBagRelationshipsImpl implements WorkbagRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String WORKBAGS_PARAMETER = "workbags";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Workbag> fetchBagRelationships(Optional<Workbag> workbag) {
        return workbag.map(this::fetchProjectdeliverables).map(this::fetchRelevantdepartments).map(this::fetchWbsids).map(this::fetchWorks);
    }

    @Override
    public Page<Workbag> fetchBagRelationships(Page<Workbag> workbags) {
        return new PageImpl<>(fetchBagRelationships(workbags.getContent()), workbags.getPageable(), workbags.getTotalElements());
    }

    @Override
    public List<Workbag> fetchBagRelationships(List<Workbag> workbags) {
        return Optional.of(workbags)
            .map(this::fetchProjectdeliverables)
            .map(this::fetchRelevantdepartments)
            .map(this::fetchWbsids)
            .map(this::fetchWorks)
            .orElse(Collections.emptyList());
    }

    Workbag fetchProjectdeliverables(Workbag result) {
        return entityManager
            .createQuery(
                "select workbag from Workbag workbag left join fetch workbag.projectdeliverables where workbag.id = :id",
                Workbag.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Workbag> fetchProjectdeliverables(List<Workbag> workbags) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, workbags.size()).forEach(index -> order.put(workbags.get(index).getId(), index));
        List<Workbag> result = entityManager
            .createQuery(
                "select workbag from Workbag workbag left join fetch workbag.projectdeliverables where workbag in :workbags",
                Workbag.class
            )
            .setParameter(WORKBAGS_PARAMETER, workbags)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    Workbag fetchRelevantdepartments(Workbag result) {
        return entityManager
            .createQuery(
                "select workbag from Workbag workbag left join fetch workbag.relevantdepartments where workbag.id = :id",
                Workbag.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Workbag> fetchRelevantdepartments(List<Workbag> workbags) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, workbags.size()).forEach(index -> order.put(workbags.get(index).getId(), index));
        List<Workbag> result = entityManager
            .createQuery(
                "select workbag from Workbag workbag left join fetch workbag.relevantdepartments where workbag in :workbags",
                Workbag.class
            )
            .setParameter(WORKBAGS_PARAMETER, workbags)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    Workbag fetchWbsids(Workbag result) {
        return entityManager
            .createQuery("select workbag from Workbag workbag left join fetch workbag.wbsids where workbag.id = :id", Workbag.class)
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Workbag> fetchWbsids(List<Workbag> workbags) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, workbags.size()).forEach(index -> order.put(workbags.get(index).getId(), index));
        List<Workbag> result = entityManager
            .createQuery("select workbag from Workbag workbag left join fetch workbag.wbsids where workbag in :workbags", Workbag.class)
            .setParameter(WORKBAGS_PARAMETER, workbags)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    Workbag fetchWorks(Workbag result) {
        return entityManager
            .createQuery("select workbag from Workbag workbag left join fetch workbag.works where workbag.id = :id", Workbag.class)
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Workbag> fetchWorks(List<Workbag> workbags) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, workbags.size()).forEach(index -> order.put(workbags.get(index).getId(), index));
        List<Workbag> result = entityManager
            .createQuery("select workbag from Workbag workbag left join fetch workbag.works where workbag in :workbags", Workbag.class)
            .setParameter(WORKBAGS_PARAMETER, workbags)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

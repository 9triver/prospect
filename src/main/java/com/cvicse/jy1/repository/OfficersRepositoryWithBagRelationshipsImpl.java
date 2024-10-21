package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Officers;
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
public class OfficersRepositoryWithBagRelationshipsImpl implements OfficersRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String OFFICERS_PARAMETER = "officers";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Officers> fetchBagRelationships(Optional<Officers> officers) {
        return officers.map(this::fetchDepartments).map(this::fetchFrontlines).map(this::fetchRoles);
    }

    @Override
    public Page<Officers> fetchBagRelationships(Page<Officers> officers) {
        return new PageImpl<>(fetchBagRelationships(officers.getContent()), officers.getPageable(), officers.getTotalElements());
    }

    @Override
    public List<Officers> fetchBagRelationships(List<Officers> officers) {
        return Optional.of(officers)
            .map(this::fetchDepartments)
            .map(this::fetchFrontlines)
            .map(this::fetchRoles)
            .orElse(Collections.emptyList());
    }

    Officers fetchDepartments(Officers result) {
        return entityManager
            .createQuery(
                "select officers from Officers officers left join fetch officers.departments where officers.id = :id",
                Officers.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Officers> fetchDepartments(List<Officers> officers) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, officers.size()).forEach(index -> order.put(officers.get(index).getId(), index));
        List<Officers> result = entityManager
            .createQuery(
                "select officers from Officers officers left join fetch officers.departments where officers in :officers",
                Officers.class
            )
            .setParameter(OFFICERS_PARAMETER, officers)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    Officers fetchFrontlines(Officers result) {
        return entityManager
            .createQuery(
                "select officers from Officers officers left join fetch officers.frontlines where officers.id = :id",
                Officers.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Officers> fetchFrontlines(List<Officers> officers) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, officers.size()).forEach(index -> order.put(officers.get(index).getId(), index));
        List<Officers> result = entityManager
            .createQuery(
                "select officers from Officers officers left join fetch officers.frontlines where officers in :officers",
                Officers.class
            )
            .setParameter(OFFICERS_PARAMETER, officers)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    Officers fetchRoles(Officers result) {
        return entityManager
            .createQuery("select officers from Officers officers left join fetch officers.roles where officers.id = :id", Officers.class)
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Officers> fetchRoles(List<Officers> officers) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, officers.size()).forEach(index -> order.put(officers.get(index).getId(), index));
        List<Officers> result = entityManager
            .createQuery(
                "select officers from Officers officers left join fetch officers.roles where officers in :officers",
                Officers.class
            )
            .setParameter(OFFICERS_PARAMETER, officers)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

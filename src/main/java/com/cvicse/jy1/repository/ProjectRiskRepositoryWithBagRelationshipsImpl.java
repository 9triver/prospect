package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.ProjectRisk;
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
public class ProjectRiskRepositoryWithBagRelationshipsImpl implements ProjectRiskRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String PROJECTRISKS_PARAMETER = "projectRisks";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<ProjectRisk> fetchBagRelationships(Optional<ProjectRisk> projectRisk) {
        return projectRisk.map(this::fetchProjectwbs);
    }

    @Override
    public Page<ProjectRisk> fetchBagRelationships(Page<ProjectRisk> projectRisks) {
        return new PageImpl<>(
            fetchBagRelationships(projectRisks.getContent()),
            projectRisks.getPageable(),
            projectRisks.getTotalElements()
        );
    }

    @Override
    public List<ProjectRisk> fetchBagRelationships(List<ProjectRisk> projectRisks) {
        return Optional.of(projectRisks).map(this::fetchProjectwbs).orElse(Collections.emptyList());
    }

    ProjectRisk fetchProjectwbs(ProjectRisk result) {
        return entityManager
            .createQuery(
                "select projectRisk from ProjectRisk projectRisk left join fetch projectRisk.projectwbs where projectRisk.id = :id",
                ProjectRisk.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<ProjectRisk> fetchProjectwbs(List<ProjectRisk> projectRisks) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, projectRisks.size()).forEach(index -> order.put(projectRisks.get(index).getId(), index));
        List<ProjectRisk> result = entityManager
            .createQuery(
                "select projectRisk from ProjectRisk projectRisk left join fetch projectRisk.projectwbs where projectRisk in :projectRisks",
                ProjectRisk.class
            )
            .setParameter(PROJECTRISKS_PARAMETER, projectRisks)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

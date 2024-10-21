package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.ProjectBudget;
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
public class ProjectBudgetRepositoryWithBagRelationshipsImpl implements ProjectBudgetRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String PROJECTBUDGETS_PARAMETER = "projectBudgets";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<ProjectBudget> fetchBagRelationships(Optional<ProjectBudget> projectBudget) {
        return projectBudget.map(this::fetchProjectwbs);
    }

    @Override
    public Page<ProjectBudget> fetchBagRelationships(Page<ProjectBudget> projectBudgets) {
        return new PageImpl<>(
            fetchBagRelationships(projectBudgets.getContent()),
            projectBudgets.getPageable(),
            projectBudgets.getTotalElements()
        );
    }

    @Override
    public List<ProjectBudget> fetchBagRelationships(List<ProjectBudget> projectBudgets) {
        return Optional.of(projectBudgets).map(this::fetchProjectwbs).orElse(Collections.emptyList());
    }

    ProjectBudget fetchProjectwbs(ProjectBudget result) {
        return entityManager
            .createQuery(
                "select projectBudget from ProjectBudget projectBudget left join fetch projectBudget.projectwbs where projectBudget.id = :id",
                ProjectBudget.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<ProjectBudget> fetchProjectwbs(List<ProjectBudget> projectBudgets) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, projectBudgets.size()).forEach(index -> order.put(projectBudgets.get(index).getId(), index));
        List<ProjectBudget> result = entityManager
            .createQuery(
                "select projectBudget from ProjectBudget projectBudget left join fetch projectBudget.projectwbs where projectBudget in :projectBudgets",
                ProjectBudget.class
            )
            .setParameter(PROJECTBUDGETS_PARAMETER, projectBudgets)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

package com.cvicse.jy1.repository;

import com.cvicse.jy1.domain.Project;
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
public class ProjectRepositoryWithBagRelationshipsImpl implements ProjectRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String PROJECTS_PARAMETER = "projects";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Project> fetchBagRelationships(Optional<Project> project) {
        return project.map(this::fetchProjectpbs).map(this::fetchProjectwbs);
    }

    @Override
    public Page<Project> fetchBagRelationships(Page<Project> projects) {
        return new PageImpl<>(fetchBagRelationships(projects.getContent()), projects.getPageable(), projects.getTotalElements());
    }

    @Override
    public List<Project> fetchBagRelationships(List<Project> projects) {
        return Optional.of(projects).map(this::fetchProjectpbs).map(this::fetchProjectwbs).orElse(Collections.emptyList());
    }

    Project fetchProjectpbs(Project result) {
        return entityManager
            .createQuery("select project from Project project left join fetch project.projectpbs where project.id = :id", Project.class)
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Project> fetchProjectpbs(List<Project> projects) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, projects.size()).forEach(index -> order.put(projects.get(index).getId(), index));
        List<Project> result = entityManager
            .createQuery("select project from Project project left join fetch project.projectpbs where project in :projects", Project.class)
            .setParameter(PROJECTS_PARAMETER, projects)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    Project fetchProjectwbs(Project result) {
        return entityManager
            .createQuery("select project from Project project left join fetch project.projectwbs where project.id = :id", Project.class)
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Project> fetchProjectwbs(List<Project> projects) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, projects.size()).forEach(index -> order.put(projects.get(index).getId(), index));
        List<Project> result = entityManager
            .createQuery("select project from Project project left join fetch project.projectwbs where project in :projects", Project.class)
            .setParameter(PROJECTS_PARAMETER, projects)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

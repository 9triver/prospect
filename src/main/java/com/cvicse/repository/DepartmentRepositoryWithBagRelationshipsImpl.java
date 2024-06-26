package com.cvicse.repository;

import com.cvicse.domain.Department;
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
public class DepartmentRepositoryWithBagRelationshipsImpl implements DepartmentRepositoryWithBagRelationships {

    private static final String ID_PARAMETER = "id";
    private static final String DEPARTMENTS_PARAMETER = "departments";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Department> fetchBagRelationships(Optional<Department> department) {
        return department.map(this::fetchOfficers);
    }

    @Override
    public Page<Department> fetchBagRelationships(Page<Department> departments) {
        return new PageImpl<>(fetchBagRelationships(departments.getContent()), departments.getPageable(), departments.getTotalElements());
    }

    @Override
    public List<Department> fetchBagRelationships(List<Department> departments) {
        return Optional.of(departments).map(this::fetchOfficers).orElse(Collections.emptyList());
    }

    Department fetchOfficers(Department result) {
        return entityManager
            .createQuery(
                "select department from Department department left join fetch department.officers where department.id = :id",
                Department.class
            )
            .setParameter(ID_PARAMETER, result.getId())
            .getSingleResult();
    }

    List<Department> fetchOfficers(List<Department> departments) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, departments.size()).forEach(index -> order.put(departments.get(index).getId(), index));
        List<Department> result = entityManager
            .createQuery(
                "select department from Department department left join fetch department.officers where department in :departments",
                Department.class
            )
            .setParameter(DEPARTMENTS_PARAMETER, departments)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}

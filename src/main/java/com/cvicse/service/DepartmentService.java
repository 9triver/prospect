package com.cvicse.service;

import com.cvicse.domain.Department;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.cvicse.domain.Department}.
 */
public interface DepartmentService {
    /**
     * Save a department.
     *
     * @param department the entity to save.
     * @return the persisted entity.
     */
    Department save(Department department);

    /**
     * Updates a department.
     *
     * @param department the entity to update.
     * @return the persisted entity.
     */
    Department update(Department department);

    /**
     * Partially updates a department.
     *
     * @param department the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Department> partialUpdate(Department department);

    /**
     * Get all the departments.
     *
     * @return the list of entities.
     */
    List<Department> findAll();

    /**
     * Get all the Department where Planstrategy is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Department> findAllWherePlanstrategyIsNull();
    /**
     * Get all the Department where Progressplan is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Department> findAllWhereProgressplanIsNull();
    /**
     * Get all the Department where EvaluationCriteria is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<Department> findAllWhereEvaluationCriteriaIsNull();

    /**
     * Get all the departments with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Department> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" department.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Department> findOne(String id);

    /**
     * Delete the "id" department.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}

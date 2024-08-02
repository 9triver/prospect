package com.cvicse.jy1.service;

import com.cvicse.jy1.domain.Department;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.cvicse.jy1.domain.Department}.
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

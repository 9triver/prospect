package com.cvicse.service.impl;

import com.cvicse.domain.Department;
import com.cvicse.repository.DepartmentRepository;
import com.cvicse.service.DepartmentService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Department}.
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final Logger log = LoggerFactory.getLogger(DepartmentServiceImpl.class);

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department save(Department department) {
        log.debug("Request to save Department : {}", department);
        return departmentRepository.save(department);
    }

    @Override
    public Department update(Department department) {
        log.debug("Request to update Department : {}", department);
        return departmentRepository.save(department);
    }

    @Override
    public Optional<Department> partialUpdate(Department department) {
        log.debug("Request to partially update Department : {}", department);

        return departmentRepository
            .findById(department.getId())
            .map(existingDepartment -> {
                if (department.getDepartmentname() != null) {
                    existingDepartment.setDepartmentname(department.getDepartmentname());
                }
                if (department.getOfficersnum() != null) {
                    existingDepartment.setOfficersnum(department.getOfficersnum());
                }

                return existingDepartment;
            })
            .map(departmentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAll() {
        log.debug("Request to get all Departments");
        return departmentRepository.findAll();
    }

    public Page<Department> findAllWithEagerRelationships(Pageable pageable) {
        return departmentRepository.findAllWithEagerRelationships(pageable);
    }

    /**
     *  Get all the departments where Planstrategy is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Department> findAllWherePlanstrategyIsNull() {
        log.debug("Request to get all departments where Planstrategy is null");
        return StreamSupport.stream(departmentRepository.findAll().spliterator(), false)
            .filter(department -> department.getPlanstrategy() == null)
            .toList();
    }

    /**
     *  Get all the departments where Progressplan is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Department> findAllWhereProgressplanIsNull() {
        log.debug("Request to get all departments where Progressplan is null");
        return StreamSupport.stream(departmentRepository.findAll().spliterator(), false)
            .filter(department -> department.getProgressplan() == null)
            .toList();
    }

    /**
     *  Get all the departments where EvaluationCriteria is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Department> findAllWhereEvaluationCriteriaIsNull() {
        log.debug("Request to get all departments where EvaluationCriteria is null");
        return StreamSupport.stream(departmentRepository.findAll().spliterator(), false)
            .filter(department -> department.getEvaluationCriteria() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Department> findOne(String id) {
        log.debug("Request to get Department : {}", id);
        return departmentRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Department : {}", id);
        departmentRepository.deleteById(id);
    }
}

package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Role;
import com.cvicse.jy1.repository.RoleRepository;
import com.cvicse.jy1.service.RoleService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Role}.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        log.debug("Request to save Role : {}", role);
        return roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        log.debug("Request to update Role : {}", role);
        return roleRepository.save(role);
    }

    @Override
    public Optional<Role> partialUpdate(Role role) {
        log.debug("Request to partially update Role : {}", role);

        return roleRepository
            .findById(role.getId())
            .map(existingRole -> {
                if (role.getRolename() != null) {
                    existingRole.setRolename(role.getRolename());
                }
                if (role.getDescription() != null) {
                    existingRole.setDescription(role.getDescription());
                }

                return existingRole;
            })
            .map(roleRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        log.debug("Request to get all Roles");
        return roleRepository.findAll();
    }

    public Page<Role> findAllWithEagerRelationships(Pageable pageable) {
        return roleRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Role> findOne(String id) {
        log.debug("Request to get Role : {}", id);
        return roleRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Role : {}", id);
        roleRepository.deleteById(id);
    }
}

package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Permission;
import com.cvicse.jy1.repository.PermissionRepository;
import com.cvicse.jy1.service.PermissionService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Permission}.
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    private static final Logger log = LoggerFactory.getLogger(PermissionServiceImpl.class);

    private final PermissionRepository permissionRepository;

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Permission save(Permission permission) {
        log.debug("Request to save Permission : {}", permission);
        return permissionRepository.save(permission);
    }

    @Override
    public Permission update(Permission permission) {
        log.debug("Request to update Permission : {}", permission);
        return permissionRepository.save(permission);
    }

    @Override
    public Optional<Permission> partialUpdate(Permission permission) {
        log.debug("Request to partially update Permission : {}", permission);

        return permissionRepository
            .findById(permission.getId())
            .map(existingPermission -> {
                if (permission.getPermissionname() != null) {
                    existingPermission.setPermissionname(permission.getPermissionname());
                }
                if (permission.getDescription() != null) {
                    existingPermission.setDescription(permission.getDescription());
                }

                return existingPermission;
            })
            .map(permissionRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Permission> findAll() {
        log.debug("Request to get all Permissions");
        return permissionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Permission> findOne(String id) {
        log.debug("Request to get Permission : {}", id);
        return permissionRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Permission : {}", id);
        permissionRepository.deleteById(id);
    }
}

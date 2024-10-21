package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Projectwbs;
import com.cvicse.jy1.domain.Workbag;
import com.cvicse.jy1.repository.WorkbagRepository;
import com.cvicse.jy1.service.WorkbagService;
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
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Workbag}.
 */
@Service
@Transactional
public class WorkbagServiceImpl implements WorkbagService {

    private static final Logger log = LoggerFactory.getLogger(WorkbagServiceImpl.class);

    private final WorkbagRepository workbagRepository;

    public WorkbagServiceImpl(WorkbagRepository workbagRepository) {
        this.workbagRepository = workbagRepository;
    }

    @Override
    public Workbag save(Workbag workbag) {
        log.debug("Request to save Workbag : {}", workbag);
        return workbagRepository.save(workbag);
    }

    @Override
    public Workbag update(Workbag workbag) {
        log.debug("Request to update Workbag : {}", workbag);
        return workbagRepository.save(workbag);
    }

    @Override
    public Optional<Workbag> partialUpdate(Workbag workbag) {
        log.debug("Request to partially update Workbag : {}", workbag);

        return workbagRepository
            .findById(workbag.getId())
            .map(existingWorkbag -> {
                if (workbag.getName() != null) {
                    existingWorkbag.setName(workbag.getName());
                }
                if (workbag.getPbsid() != null) {
                    existingWorkbag.setPbsid(workbag.getPbsid());
                }
                if (workbag.getWorkbagtype() != null) {
                    existingWorkbag.setWorkbagtype(workbag.getWorkbagtype());
                }
                if (workbag.getSupplier() != null) {
                    existingWorkbag.setSupplier(workbag.getSupplier());
                }
                if (workbag.getIskeyimportant() != null) {
                    existingWorkbag.setIskeyimportant(workbag.getIskeyimportant());
                }
                if (workbag.getKeypbsname() != null) {
                    existingWorkbag.setKeypbsname(workbag.getKeypbsname());
                }
                if (workbag.getImportantpbsname() != null) {
                    existingWorkbag.setImportantpbsname(workbag.getImportantpbsname());
                }
                if (workbag.getSecretlevel() != null) {
                    existingWorkbag.setSecretlevel(workbag.getSecretlevel());
                }
                if (workbag.getDescription() != null) {
                    existingWorkbag.setDescription(workbag.getDescription());
                }
                if (workbag.getStarttime() != null) {
                    existingWorkbag.setStarttime(workbag.getStarttime());
                }
                if (workbag.getEndtime() != null) {
                    existingWorkbag.setEndtime(workbag.getEndtime());
                }
                if (workbag.getEstimatedpurchasingtime() != null) {
                    existingWorkbag.setEstimatedpurchasingtime(workbag.getEstimatedpurchasingtime());
                }
                if (workbag.getProgress() != null) {
                    existingWorkbag.setProgress(workbag.getProgress());
                }
                if (workbag.getIssafetywork() != null) {
                    existingWorkbag.setIssafetywork(workbag.getIssafetywork());
                }
                if (workbag.getRemark() != null) {
                    existingWorkbag.setRemark(workbag.getRemark());
                }
                if (workbag.getAuditStatus() != null) {
                    existingWorkbag.setAuditStatus(workbag.getAuditStatus());
                }

                return existingWorkbag;
            })
            .map(workbagRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Workbag> findAll() {
        log.debug("Request to get all Workbags");
        return workbagRepository.findAll();
    }

    public Page<Workbag> findAllWithEagerRelationships(Pageable pageable) {
        return workbagRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Workbag> findAllWithEagerRelationships() {
        log.debug("Request to get all Workbag with eager relationships");
        return workbagRepository.findAllWithEagerRelationships();
    }

    /**
     *  Get all the workbags where OutsourcingContract is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Workbag> findAllWhereOutsourcingContractIsNull() {
        log.debug("Request to get all workbags where OutsourcingContract is null");
        return StreamSupport.stream(workbagRepository.findAll().spliterator(), false)
            .filter(workbag -> workbag.getOutsourcingContract() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Workbag> findOne(String id) {
        log.debug("Request to get Workbag : {}", id);
        return workbagRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Workbag : {}", id);
        workbagRepository.deleteById(id);
    }
}

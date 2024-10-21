package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.KeyNodeInspection;
import com.cvicse.jy1.repository.KeyNodeInspectionRepository;
import com.cvicse.jy1.service.KeyNodeInspectionService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.KeyNodeInspection}.
 */
@Service
@Transactional
public class KeyNodeInspectionServiceImpl implements KeyNodeInspectionService {

    private static final Logger log = LoggerFactory.getLogger(KeyNodeInspectionServiceImpl.class);

    private final KeyNodeInspectionRepository keyNodeInspectionRepository;

    public KeyNodeInspectionServiceImpl(KeyNodeInspectionRepository keyNodeInspectionRepository) {
        this.keyNodeInspectionRepository = keyNodeInspectionRepository;
    }

    @Override
    public KeyNodeInspection save(KeyNodeInspection keyNodeInspection) {
        log.debug("Request to save KeyNodeInspection : {}", keyNodeInspection);
        return keyNodeInspectionRepository.save(keyNodeInspection);
    }

    @Override
    public KeyNodeInspection update(KeyNodeInspection keyNodeInspection) {
        log.debug("Request to update KeyNodeInspection : {}", keyNodeInspection);
        return keyNodeInspectionRepository.save(keyNodeInspection);
    }

    @Override
    public Optional<KeyNodeInspection> partialUpdate(KeyNodeInspection keyNodeInspection) {
        log.debug("Request to partially update KeyNodeInspection : {}", keyNodeInspection);

        return keyNodeInspectionRepository
            .findById(keyNodeInspection.getId())
            .map(existingKeyNodeInspection -> {
                if (keyNodeInspection.getName() != null) {
                    existingKeyNodeInspection.setName(keyNodeInspection.getName());
                }
                if (keyNodeInspection.getWorkbagid() != null) {
                    existingKeyNodeInspection.setWorkbagid(keyNodeInspection.getWorkbagid());
                }
                if (keyNodeInspection.getWorkbagname() != null) {
                    existingKeyNodeInspection.setWorkbagname(keyNodeInspection.getWorkbagname());
                }
                if (keyNodeInspection.getBelongwbsid() != null) {
                    existingKeyNodeInspection.setBelongwbsid(keyNodeInspection.getBelongwbsid());
                }
                if (keyNodeInspection.getProjectlevel() != null) {
                    existingKeyNodeInspection.setProjectlevel(keyNodeInspection.getProjectlevel());
                }
                if (keyNodeInspection.getIskey() != null) {
                    existingKeyNodeInspection.setIskey(keyNodeInspection.getIskey());
                }
                if (keyNodeInspection.getIsimplementationplan() != null) {
                    existingKeyNodeInspection.setIsimplementationplan(keyNodeInspection.getIsimplementationplan());
                }
                if (keyNodeInspection.getIsqualityplan() != null) {
                    existingKeyNodeInspection.setIsqualityplan(keyNodeInspection.getIsqualityplan());
                }
                if (keyNodeInspection.getIstechniqueplan() != null) {
                    existingKeyNodeInspection.setIstechniqueplan(keyNodeInspection.getIstechniqueplan());
                }
                if (keyNodeInspection.getImplementationplanstatus() != null) {
                    existingKeyNodeInspection.setImplementationplanstatus(keyNodeInspection.getImplementationplanstatus());
                }
                if (keyNodeInspection.getIsimplementationplanmaterial() != null) {
                    existingKeyNodeInspection.setIsimplementationplanmaterial(keyNodeInspection.getIsimplementationplanmaterial());
                }
                if (keyNodeInspection.getTechnologyplanstatus() != null) {
                    existingKeyNodeInspection.setTechnologyplanstatus(keyNodeInspection.getTechnologyplanstatus());
                }
                if (keyNodeInspection.getIstechnologymaterial() != null) {
                    existingKeyNodeInspection.setIstechnologymaterial(keyNodeInspection.getIstechnologymaterial());
                }
                if (keyNodeInspection.getFirstcheckstatus() != null) {
                    existingKeyNodeInspection.setFirstcheckstatus(keyNodeInspection.getFirstcheckstatus());
                }
                if (keyNodeInspection.getIsfirstcheckmaterial() != null) {
                    existingKeyNodeInspection.setIsfirstcheckmaterial(keyNodeInspection.getIsfirstcheckmaterial());
                }
                if (keyNodeInspection.getProductioncheckstatus() != null) {
                    existingKeyNodeInspection.setProductioncheckstatus(keyNodeInspection.getProductioncheckstatus());
                }
                if (keyNodeInspection.getIsproductioncheckmaterial() != null) {
                    existingKeyNodeInspection.setIsproductioncheckmaterial(keyNodeInspection.getIsproductioncheckmaterial());
                }
                if (keyNodeInspection.getStatus() != null) {
                    existingKeyNodeInspection.setStatus(keyNodeInspection.getStatus());
                }

                return existingKeyNodeInspection;
            })
            .map(keyNodeInspectionRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<KeyNodeInspection> findAll() {
        log.debug("Request to get all KeyNodeInspections");
        return keyNodeInspectionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<KeyNodeInspection> findOne(Integer id) {
        log.debug("Request to get KeyNodeInspection : {}", id);
        return keyNodeInspectionRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete KeyNodeInspection : {}", id);
        keyNodeInspectionRepository.deleteById(id);
    }
}

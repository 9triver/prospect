package com.cvicse.service.impl;

import com.cvicse.domain.SecrecymanagementWbs;
import com.cvicse.repository.SecrecymanagementWbsRepository;
import com.cvicse.service.SecrecymanagementWbsService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.SecrecymanagementWbs}.
 */
@Service
@Transactional
public class SecrecymanagementWbsServiceImpl implements SecrecymanagementWbsService {

    private final Logger log = LoggerFactory.getLogger(SecrecymanagementWbsServiceImpl.class);

    private final SecrecymanagementWbsRepository secrecymanagementWbsRepository;

    public SecrecymanagementWbsServiceImpl(SecrecymanagementWbsRepository secrecymanagementWbsRepository) {
        this.secrecymanagementWbsRepository = secrecymanagementWbsRepository;
    }

    @Override
    public SecrecymanagementWbs save(SecrecymanagementWbs secrecymanagementWbs) {
        log.debug("Request to save SecrecymanagementWbs : {}", secrecymanagementWbs);
        return secrecymanagementWbsRepository.save(secrecymanagementWbs);
    }

    @Override
    public SecrecymanagementWbs update(SecrecymanagementWbs secrecymanagementWbs) {
        log.debug("Request to update SecrecymanagementWbs : {}", secrecymanagementWbs);
        return secrecymanagementWbsRepository.save(secrecymanagementWbs);
    }

    @Override
    public Optional<SecrecymanagementWbs> partialUpdate(SecrecymanagementWbs secrecymanagementWbs) {
        log.debug("Request to partially update SecrecymanagementWbs : {}", secrecymanagementWbs);

        return secrecymanagementWbsRepository
            .findById(secrecymanagementWbs.getId())
            .map(existingSecrecymanagementWbs -> {
                if (secrecymanagementWbs.getWbsspare1() != null) {
                    existingSecrecymanagementWbs.setWbsspare1(secrecymanagementWbs.getWbsspare1());
                }
                if (secrecymanagementWbs.getWbsspare2() != null) {
                    existingSecrecymanagementWbs.setWbsspare2(secrecymanagementWbs.getWbsspare2());
                }
                if (secrecymanagementWbs.getWbsspare3() != null) {
                    existingSecrecymanagementWbs.setWbsspare3(secrecymanagementWbs.getWbsspare3());
                }
                if (secrecymanagementWbs.getWbsspare4() != null) {
                    existingSecrecymanagementWbs.setWbsspare4(secrecymanagementWbs.getWbsspare4());
                }
                if (secrecymanagementWbs.getWbsspare5() != null) {
                    existingSecrecymanagementWbs.setWbsspare5(secrecymanagementWbs.getWbsspare5());
                }

                return existingSecrecymanagementWbs;
            })
            .map(secrecymanagementWbsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SecrecymanagementWbs> findAll() {
        log.debug("Request to get all SecrecymanagementWbs");
        return secrecymanagementWbsRepository.findAll();
    }

    /**
     *  Get all the secrecymanagementWbs where Secrecymanagement is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SecrecymanagementWbs> findAllWhereSecrecymanagementIsNull() {
        log.debug("Request to get all secrecymanagementWbs where Secrecymanagement is null");
        return StreamSupport.stream(secrecymanagementWbsRepository.findAll().spliterator(), false)
            .filter(secrecymanagementWbs -> secrecymanagementWbs.getSecrecymanagement() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SecrecymanagementWbs> findOne(String id) {
        log.debug("Request to get SecrecymanagementWbs : {}", id);
        return secrecymanagementWbsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete SecrecymanagementWbs : {}", id);
        secrecymanagementWbsRepository.deleteById(id);
    }
}

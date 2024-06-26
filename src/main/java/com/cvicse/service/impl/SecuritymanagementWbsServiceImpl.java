package com.cvicse.service.impl;

import com.cvicse.domain.SecuritymanagementWbs;
import com.cvicse.repository.SecuritymanagementWbsRepository;
import com.cvicse.service.SecuritymanagementWbsService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.SecuritymanagementWbs}.
 */
@Service
@Transactional
public class SecuritymanagementWbsServiceImpl implements SecuritymanagementWbsService {

    private final Logger log = LoggerFactory.getLogger(SecuritymanagementWbsServiceImpl.class);

    private final SecuritymanagementWbsRepository securitymanagementWbsRepository;

    public SecuritymanagementWbsServiceImpl(SecuritymanagementWbsRepository securitymanagementWbsRepository) {
        this.securitymanagementWbsRepository = securitymanagementWbsRepository;
    }

    @Override
    public SecuritymanagementWbs save(SecuritymanagementWbs securitymanagementWbs) {
        log.debug("Request to save SecuritymanagementWbs : {}", securitymanagementWbs);
        return securitymanagementWbsRepository.save(securitymanagementWbs);
    }

    @Override
    public SecuritymanagementWbs update(SecuritymanagementWbs securitymanagementWbs) {
        log.debug("Request to update SecuritymanagementWbs : {}", securitymanagementWbs);
        return securitymanagementWbsRepository.save(securitymanagementWbs);
    }

    @Override
    public Optional<SecuritymanagementWbs> partialUpdate(SecuritymanagementWbs securitymanagementWbs) {
        log.debug("Request to partially update SecuritymanagementWbs : {}", securitymanagementWbs);

        return securitymanagementWbsRepository
            .findById(securitymanagementWbs.getId())
            .map(existingSecuritymanagementWbs -> {
                if (securitymanagementWbs.getWbsspare1() != null) {
                    existingSecuritymanagementWbs.setWbsspare1(securitymanagementWbs.getWbsspare1());
                }
                if (securitymanagementWbs.getWbsspare2() != null) {
                    existingSecuritymanagementWbs.setWbsspare2(securitymanagementWbs.getWbsspare2());
                }
                if (securitymanagementWbs.getWbsspare3() != null) {
                    existingSecuritymanagementWbs.setWbsspare3(securitymanagementWbs.getWbsspare3());
                }
                if (securitymanagementWbs.getWbsspare4() != null) {
                    existingSecuritymanagementWbs.setWbsspare4(securitymanagementWbs.getWbsspare4());
                }
                if (securitymanagementWbs.getWbsspare5() != null) {
                    existingSecuritymanagementWbs.setWbsspare5(securitymanagementWbs.getWbsspare5());
                }

                return existingSecuritymanagementWbs;
            })
            .map(securitymanagementWbsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SecuritymanagementWbs> findAll() {
        log.debug("Request to get all SecuritymanagementWbs");
        return securitymanagementWbsRepository.findAll();
    }

    /**
     *  Get all the securitymanagementWbs where Securitymanagement is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SecuritymanagementWbs> findAllWhereSecuritymanagementIsNull() {
        log.debug("Request to get all securitymanagementWbs where Securitymanagement is null");
        return StreamSupport.stream(securitymanagementWbsRepository.findAll().spliterator(), false)
            .filter(securitymanagementWbs -> securitymanagementWbs.getSecuritymanagement() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SecuritymanagementWbs> findOne(String id) {
        log.debug("Request to get SecuritymanagementWbs : {}", id);
        return securitymanagementWbsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete SecuritymanagementWbs : {}", id);
        securitymanagementWbsRepository.deleteById(id);
    }
}

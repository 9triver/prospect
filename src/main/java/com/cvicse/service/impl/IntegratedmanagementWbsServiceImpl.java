package com.cvicse.service.impl;

import com.cvicse.domain.IntegratedmanagementWbs;
import com.cvicse.repository.IntegratedmanagementWbsRepository;
import com.cvicse.service.IntegratedmanagementWbsService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.IntegratedmanagementWbs}.
 */
@Service
@Transactional
public class IntegratedmanagementWbsServiceImpl implements IntegratedmanagementWbsService {

    private final Logger log = LoggerFactory.getLogger(IntegratedmanagementWbsServiceImpl.class);

    private final IntegratedmanagementWbsRepository integratedmanagementWbsRepository;

    public IntegratedmanagementWbsServiceImpl(IntegratedmanagementWbsRepository integratedmanagementWbsRepository) {
        this.integratedmanagementWbsRepository = integratedmanagementWbsRepository;
    }

    @Override
    public IntegratedmanagementWbs save(IntegratedmanagementWbs integratedmanagementWbs) {
        log.debug("Request to save IntegratedmanagementWbs : {}", integratedmanagementWbs);
        return integratedmanagementWbsRepository.save(integratedmanagementWbs);
    }

    @Override
    public IntegratedmanagementWbs update(IntegratedmanagementWbs integratedmanagementWbs) {
        log.debug("Request to update IntegratedmanagementWbs : {}", integratedmanagementWbs);
        return integratedmanagementWbsRepository.save(integratedmanagementWbs);
    }

    @Override
    public Optional<IntegratedmanagementWbs> partialUpdate(IntegratedmanagementWbs integratedmanagementWbs) {
        log.debug("Request to partially update IntegratedmanagementWbs : {}", integratedmanagementWbs);

        return integratedmanagementWbsRepository
            .findById(integratedmanagementWbs.getId())
            .map(existingIntegratedmanagementWbs -> {
                if (integratedmanagementWbs.getWbsspare1() != null) {
                    existingIntegratedmanagementWbs.setWbsspare1(integratedmanagementWbs.getWbsspare1());
                }
                if (integratedmanagementWbs.getWbsspare2() != null) {
                    existingIntegratedmanagementWbs.setWbsspare2(integratedmanagementWbs.getWbsspare2());
                }
                if (integratedmanagementWbs.getWbsspare3() != null) {
                    existingIntegratedmanagementWbs.setWbsspare3(integratedmanagementWbs.getWbsspare3());
                }
                if (integratedmanagementWbs.getWbsspare4() != null) {
                    existingIntegratedmanagementWbs.setWbsspare4(integratedmanagementWbs.getWbsspare4());
                }
                if (integratedmanagementWbs.getWbsspare5() != null) {
                    existingIntegratedmanagementWbs.setWbsspare5(integratedmanagementWbs.getWbsspare5());
                }

                return existingIntegratedmanagementWbs;
            })
            .map(integratedmanagementWbsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IntegratedmanagementWbs> findAll() {
        log.debug("Request to get all IntegratedmanagementWbs");
        return integratedmanagementWbsRepository.findAll();
    }

    /**
     *  Get all the integratedmanagementWbs where Integratedmanagement is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<IntegratedmanagementWbs> findAllWhereIntegratedmanagementIsNull() {
        log.debug("Request to get all integratedmanagementWbs where Integratedmanagement is null");
        return StreamSupport.stream(integratedmanagementWbsRepository.findAll().spliterator(), false)
            .filter(integratedmanagementWbs -> integratedmanagementWbs.getIntegratedmanagement() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IntegratedmanagementWbs> findOne(String id) {
        log.debug("Request to get IntegratedmanagementWbs : {}", id);
        return integratedmanagementWbsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete IntegratedmanagementWbs : {}", id);
        integratedmanagementWbsRepository.deleteById(id);
    }
}

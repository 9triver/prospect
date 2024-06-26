package com.cvicse.service.impl;

import com.cvicse.domain.OutsourcingmanagementWbs;
import com.cvicse.repository.OutsourcingmanagementWbsRepository;
import com.cvicse.service.OutsourcingmanagementWbsService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.OutsourcingmanagementWbs}.
 */
@Service
@Transactional
public class OutsourcingmanagementWbsServiceImpl implements OutsourcingmanagementWbsService {

    private final Logger log = LoggerFactory.getLogger(OutsourcingmanagementWbsServiceImpl.class);

    private final OutsourcingmanagementWbsRepository outsourcingmanagementWbsRepository;

    public OutsourcingmanagementWbsServiceImpl(OutsourcingmanagementWbsRepository outsourcingmanagementWbsRepository) {
        this.outsourcingmanagementWbsRepository = outsourcingmanagementWbsRepository;
    }

    @Override
    public OutsourcingmanagementWbs save(OutsourcingmanagementWbs outsourcingmanagementWbs) {
        log.debug("Request to save OutsourcingmanagementWbs : {}", outsourcingmanagementWbs);
        return outsourcingmanagementWbsRepository.save(outsourcingmanagementWbs);
    }

    @Override
    public OutsourcingmanagementWbs update(OutsourcingmanagementWbs outsourcingmanagementWbs) {
        log.debug("Request to update OutsourcingmanagementWbs : {}", outsourcingmanagementWbs);
        return outsourcingmanagementWbsRepository.save(outsourcingmanagementWbs);
    }

    @Override
    public Optional<OutsourcingmanagementWbs> partialUpdate(OutsourcingmanagementWbs outsourcingmanagementWbs) {
        log.debug("Request to partially update OutsourcingmanagementWbs : {}", outsourcingmanagementWbs);

        return outsourcingmanagementWbsRepository
            .findById(outsourcingmanagementWbs.getId())
            .map(existingOutsourcingmanagementWbs -> {
                if (outsourcingmanagementWbs.getWbsspare1() != null) {
                    existingOutsourcingmanagementWbs.setWbsspare1(outsourcingmanagementWbs.getWbsspare1());
                }
                if (outsourcingmanagementWbs.getWbsspare2() != null) {
                    existingOutsourcingmanagementWbs.setWbsspare2(outsourcingmanagementWbs.getWbsspare2());
                }
                if (outsourcingmanagementWbs.getWbsspare3() != null) {
                    existingOutsourcingmanagementWbs.setWbsspare3(outsourcingmanagementWbs.getWbsspare3());
                }
                if (outsourcingmanagementWbs.getWbsspare4() != null) {
                    existingOutsourcingmanagementWbs.setWbsspare4(outsourcingmanagementWbs.getWbsspare4());
                }
                if (outsourcingmanagementWbs.getWbsspare5() != null) {
                    existingOutsourcingmanagementWbs.setWbsspare5(outsourcingmanagementWbs.getWbsspare5());
                }

                return existingOutsourcingmanagementWbs;
            })
            .map(outsourcingmanagementWbsRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OutsourcingmanagementWbs> findAll() {
        log.debug("Request to get all OutsourcingmanagementWbs");
        return outsourcingmanagementWbsRepository.findAll();
    }

    /**
     *  Get all the outsourcingmanagementWbs where Outsourcingmanagement is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OutsourcingmanagementWbs> findAllWhereOutsourcingmanagementIsNull() {
        log.debug("Request to get all outsourcingmanagementWbs where Outsourcingmanagement is null");
        return StreamSupport.stream(outsourcingmanagementWbsRepository.findAll().spliterator(), false)
            .filter(outsourcingmanagementWbs -> outsourcingmanagementWbs.getOutsourcingmanagement() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OutsourcingmanagementWbs> findOne(String id) {
        log.debug("Request to get OutsourcingmanagementWbs : {}", id);
        return outsourcingmanagementWbsRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete OutsourcingmanagementWbs : {}", id);
        outsourcingmanagementWbsRepository.deleteById(id);
    }
}

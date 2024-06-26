package com.cvicse.service.impl;

import com.cvicse.domain.Planexecute;
import com.cvicse.repository.PlanexecuteRepository;
import com.cvicse.service.PlanexecuteService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Planexecute}.
 */
@Service
@Transactional
public class PlanexecuteServiceImpl implements PlanexecuteService {

    private final Logger log = LoggerFactory.getLogger(PlanexecuteServiceImpl.class);

    private final PlanexecuteRepository planexecuteRepository;

    public PlanexecuteServiceImpl(PlanexecuteRepository planexecuteRepository) {
        this.planexecuteRepository = planexecuteRepository;
    }

    @Override
    public Planexecute save(Planexecute planexecute) {
        log.debug("Request to save Planexecute : {}", planexecute);
        return planexecuteRepository.save(planexecute);
    }

    @Override
    public Planexecute update(Planexecute planexecute) {
        log.debug("Request to update Planexecute : {}", planexecute);
        return planexecuteRepository.save(planexecute);
    }

    @Override
    public Optional<Planexecute> partialUpdate(Planexecute planexecute) {
        log.debug("Request to partially update Planexecute : {}", planexecute);

        return planexecuteRepository
            .findById(planexecute.getId())
            .map(existingPlanexecute -> {
                if (planexecute.getPlanname() != null) {
                    existingPlanexecute.setPlanname(planexecute.getPlanname());
                }
                if (planexecute.getPlanstarttime() != null) {
                    existingPlanexecute.setPlanstarttime(planexecute.getPlanstarttime());
                }
                if (planexecute.getPlanendtime() != null) {
                    existingPlanexecute.setPlanendtime(planexecute.getPlanendtime());
                }

                return existingPlanexecute;
            })
            .map(planexecuteRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Planexecute> findAll() {
        log.debug("Request to get all Planexecutes");
        return planexecuteRepository.findAll();
    }

    /**
     *  Get all the planexecutes where Monthplan is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Planexecute> findAllWhereMonthplanIsNull() {
        log.debug("Request to get all planexecutes where Monthplan is null");
        return StreamSupport.stream(planexecuteRepository.findAll().spliterator(), false)
            .filter(planexecute -> planexecute.getMonthplan() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Planexecute> findOne(String id) {
        log.debug("Request to get Planexecute : {}", id);
        return planexecuteRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Planexecute : {}", id);
        planexecuteRepository.deleteById(id);
    }
}

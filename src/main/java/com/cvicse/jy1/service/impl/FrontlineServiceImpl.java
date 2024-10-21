package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Frontline;
import com.cvicse.jy1.repository.FrontlineRepository;
import com.cvicse.jy1.service.FrontlineService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Frontline}.
 */
@Service
@Transactional
public class FrontlineServiceImpl implements FrontlineService {

    private static final Logger log = LoggerFactory.getLogger(FrontlineServiceImpl.class);

    private final FrontlineRepository frontlineRepository;

    public FrontlineServiceImpl(FrontlineRepository frontlineRepository) {
        this.frontlineRepository = frontlineRepository;
    }

    @Override
    public Frontline save(Frontline frontline) {
        log.debug("Request to save Frontline : {}", frontline);
        return frontlineRepository.save(frontline);
    }

    @Override
    public Frontline update(Frontline frontline) {
        log.debug("Request to update Frontline : {}", frontline);
        return frontlineRepository.save(frontline);
    }

    @Override
    public Optional<Frontline> partialUpdate(Frontline frontline) {
        log.debug("Request to partially update Frontline : {}", frontline);

        return frontlineRepository
            .findById(frontline.getId())
            .map(existingFrontline -> {
                if (frontline.getName() != null) {
                    existingFrontline.setName(frontline.getName());
                }
                if (frontline.getDescription() != null) {
                    existingFrontline.setDescription(frontline.getDescription());
                }

                return existingFrontline;
            })
            .map(frontlineRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Frontline> findAll() {
        log.debug("Request to get all Frontlines");
        return frontlineRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Frontline> findOne(String id) {
        log.debug("Request to get Frontline : {}", id);
        return frontlineRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Frontline : {}", id);
        frontlineRepository.deleteById(id);
    }
}

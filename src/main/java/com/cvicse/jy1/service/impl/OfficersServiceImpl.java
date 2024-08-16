package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Officers;
import com.cvicse.jy1.repository.OfficersRepository;
import com.cvicse.jy1.service.OfficersService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Officers}.
 */
@Service
@Transactional
public class OfficersServiceImpl implements OfficersService {

    private static final Logger log = LoggerFactory.getLogger(OfficersServiceImpl.class);

    private final OfficersRepository officersRepository;

    public OfficersServiceImpl(OfficersRepository officersRepository) {
        this.officersRepository = officersRepository;
    }

    @Override
    public Officers save(Officers officers) {
        log.debug("Request to save Officers : {}", officers);
        return officersRepository.save(officers);
    }

    @Override
    public Officers update(Officers officers) {
        log.debug("Request to update Officers : {}", officers);
        return officersRepository.save(officers);
    }

    @Override
    public Optional<Officers> partialUpdate(Officers officers) {
        log.debug("Request to partially update Officers : {}", officers);

        return officersRepository
            .findById(officers.getId())
            .map(existingOfficers -> {
                if (officers.getHiredate() != null) {
                    existingOfficers.setHiredate(officers.getHiredate());
                }
                if (officers.getYears() != null) {
                    existingOfficers.setYears(officers.getYears());
                }
                if (officers.getStatus() != null) {
                    existingOfficers.setStatus(officers.getStatus());
                }

                return existingOfficers;
            })
            .map(officersRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Officers> findAll() {
        log.debug("Request to get all Officers");
        return officersRepository.findAll();
    }

    public Page<Officers> findAllWithEagerRelationships(Pageable pageable) {
        return officersRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Officers> findOne(String id) {
        log.debug("Request to get Officers : {}", id);
        return officersRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Officers : {}", id);
        officersRepository.deleteById(id);
    }
}

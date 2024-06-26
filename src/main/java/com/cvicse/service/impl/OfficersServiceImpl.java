package com.cvicse.service.impl;

import com.cvicse.domain.Officers;
import com.cvicse.repository.OfficersRepository;
import com.cvicse.service.OfficersService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Officers}.
 */
@Service
@Transactional
public class OfficersServiceImpl implements OfficersService {

    private final Logger log = LoggerFactory.getLogger(OfficersServiceImpl.class);

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
                if (officers.getOfficersname() != null) {
                    existingOfficers.setOfficersname(officers.getOfficersname());
                }
                if (officers.getPassword() != null) {
                    existingOfficers.setPassword(officers.getPassword());
                }
                if (officers.getEmail() != null) {
                    existingOfficers.setEmail(officers.getEmail());
                }
                if (officers.getPhone() != null) {
                    existingOfficers.setPhone(officers.getPhone());
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

    /**
     *  Get all the officers where Document is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Officers> findAllWhereDocumentIsNull() {
        log.debug("Request to get all officers where Document is null");
        return StreamSupport.stream(officersRepository.findAll().spliterator(), false)
            .filter(officers -> officers.getDocument() == null)
            .toList();
    }

    /**
     *  Get all the officers where Planexecute is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Officers> findAllWherePlanexecuteIsNull() {
        log.debug("Request to get all officers where Planexecute is null");
        return StreamSupport.stream(officersRepository.findAll().spliterator(), false)
            .filter(officers -> officers.getPlanexecute() == null)
            .toList();
    }

    /**
     *  Get all the officers where Projectcharge is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Officers> findAllWhereProjectchargeIsNull() {
        log.debug("Request to get all officers where Projectcharge is null");
        return StreamSupport.stream(officersRepository.findAll().spliterator(), false)
            .filter(officers -> officers.getProjectcharge() == null)
            .toList();
    }

    /**
     *  Get all the officers where ApprovalAgent is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Officers> findAllWhereApprovalAgentIsNull() {
        log.debug("Request to get all officers where ApprovalAgent is null");
        return StreamSupport.stream(officersRepository.findAll().spliterator(), false)
            .filter(officers -> officers.getApprovalAgent() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Officers> findOne(String id) {
        log.debug("Request to get Officers : {}", id);
        return officersRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Officers : {}", id);
        officersRepository.deleteById(id);
    }
}

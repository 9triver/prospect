package com.cvicse.service.impl;

import com.cvicse.domain.Secrecysystem;
import com.cvicse.repository.SecrecysystemRepository;
import com.cvicse.service.SecrecysystemService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Secrecysystem}.
 */
@Service
@Transactional
public class SecrecysystemServiceImpl implements SecrecysystemService {

    private final Logger log = LoggerFactory.getLogger(SecrecysystemServiceImpl.class);

    private final SecrecysystemRepository secrecysystemRepository;

    public SecrecysystemServiceImpl(SecrecysystemRepository secrecysystemRepository) {
        this.secrecysystemRepository = secrecysystemRepository;
    }

    @Override
    public Secrecysystem save(Secrecysystem secrecysystem) {
        log.debug("Request to save Secrecysystem : {}", secrecysystem);
        return secrecysystemRepository.save(secrecysystem);
    }

    @Override
    public Secrecysystem update(Secrecysystem secrecysystem) {
        log.debug("Request to update Secrecysystem : {}", secrecysystem);
        return secrecysystemRepository.save(secrecysystem);
    }

    @Override
    public Optional<Secrecysystem> partialUpdate(Secrecysystem secrecysystem) {
        log.debug("Request to partially update Secrecysystem : {}", secrecysystem);

        return secrecysystemRepository
            .findById(secrecysystem.getId())
            .map(existingSecrecysystem -> {
                if (secrecysystem.getPublishedby() != null) {
                    existingSecrecysystem.setPublishedby(secrecysystem.getPublishedby());
                }
                if (secrecysystem.getDocumentname() != null) {
                    existingSecrecysystem.setDocumentname(secrecysystem.getDocumentname());
                }
                if (secrecysystem.getDocumenttype() != null) {
                    existingSecrecysystem.setDocumenttype(secrecysystem.getDocumenttype());
                }
                if (secrecysystem.getDocumentsize() != null) {
                    existingSecrecysystem.setDocumentsize(secrecysystem.getDocumentsize());
                }
                if (secrecysystem.getSecretlevel() != null) {
                    existingSecrecysystem.setSecretlevel(secrecysystem.getSecretlevel());
                }
                if (secrecysystem.getAuditStatus() != null) {
                    existingSecrecysystem.setAuditStatus(secrecysystem.getAuditStatus());
                }

                return existingSecrecysystem;
            })
            .map(secrecysystemRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Secrecysystem> findAll() {
        log.debug("Request to get all Secrecysystems");
        return secrecysystemRepository.findAll();
    }

    /**
     *  Get all the secrecysystems where ProjectSecrecy is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Secrecysystem> findAllWhereProjectSecrecyIsNull() {
        log.debug("Request to get all secrecysystems where ProjectSecrecy is null");
        return StreamSupport.stream(secrecysystemRepository.findAll().spliterator(), false)
            .filter(secrecysystem -> secrecysystem.getProjectSecrecy() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Secrecysystem> findOne(String id) {
        log.debug("Request to get Secrecysystem : {}", id);
        return secrecysystemRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Secrecysystem : {}", id);
        secrecysystemRepository.deleteById(id);
    }
}

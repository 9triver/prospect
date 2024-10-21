package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.CommunicationFormDictionary;
import com.cvicse.jy1.repository.CommunicationFormDictionaryRepository;
import com.cvicse.jy1.service.CommunicationFormDictionaryService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.CommunicationFormDictionary}.
 */
@Service
@Transactional
public class CommunicationFormDictionaryServiceImpl implements CommunicationFormDictionaryService {

    private static final Logger log = LoggerFactory.getLogger(CommunicationFormDictionaryServiceImpl.class);

    private final CommunicationFormDictionaryRepository communicationFormDictionaryRepository;

    public CommunicationFormDictionaryServiceImpl(CommunicationFormDictionaryRepository communicationFormDictionaryRepository) {
        this.communicationFormDictionaryRepository = communicationFormDictionaryRepository;
    }

    @Override
    public CommunicationFormDictionary save(CommunicationFormDictionary communicationFormDictionary) {
        log.debug("Request to save CommunicationFormDictionary : {}", communicationFormDictionary);
        return communicationFormDictionaryRepository.save(communicationFormDictionary);
    }

    @Override
    public CommunicationFormDictionary update(CommunicationFormDictionary communicationFormDictionary) {
        log.debug("Request to update CommunicationFormDictionary : {}", communicationFormDictionary);
        return communicationFormDictionaryRepository.save(communicationFormDictionary);
    }

    @Override
    public Optional<CommunicationFormDictionary> partialUpdate(CommunicationFormDictionary communicationFormDictionary) {
        log.debug("Request to partially update CommunicationFormDictionary : {}", communicationFormDictionary);

        return communicationFormDictionaryRepository
            .findById(communicationFormDictionary.getId())
            .map(existingCommunicationFormDictionary -> {
                if (communicationFormDictionary.getCommunicationformname() != null) {
                    existingCommunicationFormDictionary.setCommunicationformname(communicationFormDictionary.getCommunicationformname());
                }
                if (communicationFormDictionary.getCommunicationformtype() != null) {
                    existingCommunicationFormDictionary.setCommunicationformtype(communicationFormDictionary.getCommunicationformtype());
                }
                if (communicationFormDictionary.getStatus() != null) {
                    existingCommunicationFormDictionary.setStatus(communicationFormDictionary.getStatus());
                }

                return existingCommunicationFormDictionary;
            })
            .map(communicationFormDictionaryRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommunicationFormDictionary> findAll() {
        log.debug("Request to get all CommunicationFormDictionaries");
        return communicationFormDictionaryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CommunicationFormDictionary> findOne(Integer id) {
        log.debug("Request to get CommunicationFormDictionary : {}", id);
        return communicationFormDictionaryRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete CommunicationFormDictionary : {}", id);
        communicationFormDictionaryRepository.deleteById(id);
    }
}

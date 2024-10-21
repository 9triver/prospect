package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.CommunicationDictionary;
import com.cvicse.jy1.repository.CommunicationDictionaryRepository;
import com.cvicse.jy1.service.CommunicationDictionaryService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.CommunicationDictionary}.
 */
@Service
@Transactional
public class CommunicationDictionaryServiceImpl implements CommunicationDictionaryService {

    private static final Logger log = LoggerFactory.getLogger(CommunicationDictionaryServiceImpl.class);

    private final CommunicationDictionaryRepository communicationDictionaryRepository;

    public CommunicationDictionaryServiceImpl(CommunicationDictionaryRepository communicationDictionaryRepository) {
        this.communicationDictionaryRepository = communicationDictionaryRepository;
    }

    @Override
    public CommunicationDictionary save(CommunicationDictionary communicationDictionary) {
        log.debug("Request to save CommunicationDictionary : {}", communicationDictionary);
        return communicationDictionaryRepository.save(communicationDictionary);
    }

    @Override
    public CommunicationDictionary update(CommunicationDictionary communicationDictionary) {
        log.debug("Request to update CommunicationDictionary : {}", communicationDictionary);
        return communicationDictionaryRepository.save(communicationDictionary);
    }

    @Override
    public Optional<CommunicationDictionary> partialUpdate(CommunicationDictionary communicationDictionary) {
        log.debug("Request to partially update CommunicationDictionary : {}", communicationDictionary);

        return communicationDictionaryRepository
            .findById(communicationDictionary.getId())
            .map(existingCommunicationDictionary -> {
                if (communicationDictionary.getPartiesname() != null) {
                    existingCommunicationDictionary.setPartiesname(communicationDictionary.getPartiesname());
                }
                if (communicationDictionary.getPartiestype() != null) {
                    existingCommunicationDictionary.setPartiestype(communicationDictionary.getPartiestype());
                }
                if (communicationDictionary.getPartiesduty() != null) {
                    existingCommunicationDictionary.setPartiesduty(communicationDictionary.getPartiesduty());
                }

                return existingCommunicationDictionary;
            })
            .map(communicationDictionaryRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommunicationDictionary> findAll() {
        log.debug("Request to get all CommunicationDictionaries");
        return communicationDictionaryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CommunicationDictionary> findOne(Integer id) {
        log.debug("Request to get CommunicationDictionary : {}", id);
        return communicationDictionaryRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete CommunicationDictionary : {}", id);
        communicationDictionaryRepository.deleteById(id);
    }
}

package com.cvicse.service.impl;

import com.cvicse.domain.Document;
import com.cvicse.repository.DocumentRepository;
import com.cvicse.service.DocumentService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Document}.
 */
@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private final Logger log = LoggerFactory.getLogger(DocumentServiceImpl.class);

    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Document save(Document document) {
        log.debug("Request to save Document : {}", document);
        return documentRepository.save(document);
    }

    @Override
    public Document update(Document document) {
        log.debug("Request to update Document : {}", document);
        return documentRepository.save(document);
    }

    @Override
    public Optional<Document> partialUpdate(Document document) {
        log.debug("Request to partially update Document : {}", document);

        return documentRepository
            .findById(document.getId())
            .map(existingDocument -> {
                if (document.getDocumentname() != null) {
                    existingDocument.setDocumentname(document.getDocumentname());
                }
                if (document.getDocumenttype() != null) {
                    existingDocument.setDocumenttype(document.getDocumenttype());
                }
                if (document.getDocumentsize() != null) {
                    existingDocument.setDocumentsize(document.getDocumentsize());
                }
                if (document.getSecretlevel() != null) {
                    existingDocument.setSecretlevel(document.getSecretlevel());
                }
                if (document.getCreatetime() != null) {
                    existingDocument.setCreatetime(document.getCreatetime());
                }
                if (document.getCreatorname() != null) {
                    existingDocument.setCreatorname(document.getCreatorname());
                }

                return existingDocument;
            })
            .map(documentRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Document> findAll() {
        log.debug("Request to get all Documents");
        return documentRepository.findAll();
    }

    /**
     *  Get all the documents where Cycleplan is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Document> findAllWhereCycleplanIsNull() {
        log.debug("Request to get all documents where Cycleplan is null");
        return StreamSupport.stream(documentRepository.findAll().spliterator(), false)
            .filter(document -> document.getCycleplan() == null)
            .toList();
    }

    /**
     *  Get all the documents where Annualplan is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Document> findAllWhereAnnualplanIsNull() {
        log.debug("Request to get all documents where Annualplan is null");
        return StreamSupport.stream(documentRepository.findAll().spliterator(), false)
            .filter(document -> document.getAnnualplan() == null)
            .toList();
    }

    /**
     *  Get all the documents where Monthplan is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Document> findAllWhereMonthplanIsNull() {
        log.debug("Request to get all documents where Monthplan is null");
        return StreamSupport.stream(documentRepository.findAll().spliterator(), false)
            .filter(document -> document.getMonthplan() == null)
            .toList();
    }

    /**
     *  Get all the documents where Progressplanreturns is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Document> findAllWhereProgressplanreturnsIsNull() {
        log.debug("Request to get all documents where Progressplanreturns is null");
        return StreamSupport.stream(documentRepository.findAll().spliterator(), false)
            .filter(document -> document.getProgressplanreturns() == null)
            .toList();
    }

    /**
     *  Get all the documents where Auditedbudget is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Document> findAllWhereAuditedbudgetIsNull() {
        log.debug("Request to get all documents where Auditedbudget is null");
        return StreamSupport.stream(documentRepository.findAll().spliterator(), false)
            .filter(document -> document.getAuditedbudget() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Document> findOne(String id) {
        log.debug("Request to get Document : {}", id);
        return documentRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Document : {}", id);
        documentRepository.deleteById(id);
    }
}

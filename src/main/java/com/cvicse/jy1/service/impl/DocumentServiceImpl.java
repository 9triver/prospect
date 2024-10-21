package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Document;
import com.cvicse.jy1.repository.DocumentRepository;
import com.cvicse.jy1.service.DocumentService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Document}.
 */
@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

    private static final Logger log = LoggerFactory.getLogger(DocumentServiceImpl.class);

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
                if (document.getUrl() != null) {
                    existingDocument.setUrl(document.getUrl());
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

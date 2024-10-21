package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Documentmenu;
import com.cvicse.jy1.repository.DocumentmenuRepository;
import com.cvicse.jy1.service.DocumentmenuService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Documentmenu}.
 */
@Service
@Transactional
public class DocumentmenuServiceImpl implements DocumentmenuService {

    private static final Logger log = LoggerFactory.getLogger(DocumentmenuServiceImpl.class);

    private final DocumentmenuRepository documentmenuRepository;

    public DocumentmenuServiceImpl(DocumentmenuRepository documentmenuRepository) {
        this.documentmenuRepository = documentmenuRepository;
    }

    @Override
    public Documentmenu save(Documentmenu documentmenu) {
        log.debug("Request to save Documentmenu : {}", documentmenu);
        return documentmenuRepository.save(documentmenu);
    }

    @Override
    public Documentmenu update(Documentmenu documentmenu) {
        log.debug("Request to update Documentmenu : {}", documentmenu);
        return documentmenuRepository.save(documentmenu);
    }

    @Override
    public Optional<Documentmenu> partialUpdate(Documentmenu documentmenu) {
        log.debug("Request to partially update Documentmenu : {}", documentmenu);

        return documentmenuRepository
            .findById(documentmenu.getId())
            .map(existingDocumentmenu -> {
                if (documentmenu.getMenuid() != null) {
                    existingDocumentmenu.setMenuid(documentmenu.getMenuid());
                }
                if (documentmenu.getBelongtype() != null) {
                    existingDocumentmenu.setBelongtype(documentmenu.getBelongtype());
                }
                if (documentmenu.getMenuname() != null) {
                    existingDocumentmenu.setMenuname(documentmenu.getMenuname());
                }
                if (documentmenu.getParentmenuid() != null) {
                    existingDocumentmenu.setParentmenuid(documentmenu.getParentmenuid());
                }
                if (documentmenu.getCreatetime() != null) {
                    existingDocumentmenu.setCreatetime(documentmenu.getCreatetime());
                }
                if (documentmenu.getCreatorid() != null) {
                    existingDocumentmenu.setCreatorid(documentmenu.getCreatorid());
                }
                if (documentmenu.getCreatorname() != null) {
                    existingDocumentmenu.setCreatorname(documentmenu.getCreatorname());
                }
                if (documentmenu.getType() != null) {
                    existingDocumentmenu.setType(documentmenu.getType());
                }
                if (documentmenu.getFilenum() != null) {
                    existingDocumentmenu.setFilenum(documentmenu.getFilenum());
                }
                if (documentmenu.getFileurl() != null) {
                    existingDocumentmenu.setFileurl(documentmenu.getFileurl());
                }
                if (documentmenu.getDepartmentid() != null) {
                    existingDocumentmenu.setDepartmentid(documentmenu.getDepartmentid());
                }
                if (documentmenu.getDepartmentname() != null) {
                    existingDocumentmenu.setDepartmentname(documentmenu.getDepartmentname());
                }
                if (documentmenu.getSpare1() != null) {
                    existingDocumentmenu.setSpare1(documentmenu.getSpare1());
                }
                if (documentmenu.getSpare2() != null) {
                    existingDocumentmenu.setSpare2(documentmenu.getSpare2());
                }
                if (documentmenu.getSpare3() != null) {
                    existingDocumentmenu.setSpare3(documentmenu.getSpare3());
                }

                return existingDocumentmenu;
            })
            .map(documentmenuRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Documentmenu> findAll() {
        log.debug("Request to get all Documentmenus");
        return documentmenuRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Documentmenu> findOne(Integer id) {
        log.debug("Request to get Documentmenu : {}", id);
        return documentmenuRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete Documentmenu : {}", id);
        documentmenuRepository.deleteById(id);
    }
}

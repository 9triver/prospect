package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Archives;
import com.cvicse.jy1.repository.ArchivesRepository;
import com.cvicse.jy1.service.ArchivesService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Archives}.
 */
@Service
@Transactional
public class ArchivesServiceImpl implements ArchivesService {

    private static final Logger log = LoggerFactory.getLogger(ArchivesServiceImpl.class);

    private final ArchivesRepository archivesRepository;

    public ArchivesServiceImpl(ArchivesRepository archivesRepository) {
        this.archivesRepository = archivesRepository;
    }

    @Override
    public Archives save(Archives archives) {
        log.debug("Request to save Archives : {}", archives);
        return archivesRepository.save(archives);
    }

    @Override
    public Archives update(Archives archives) {
        log.debug("Request to update Archives : {}", archives);
        return archivesRepository.save(archives);
    }

    @Override
    public Optional<Archives> partialUpdate(Archives archives) {
        log.debug("Request to partially update Archives : {}", archives);

        return archivesRepository
            .findById(archives.getId())
            .map(existingArchives -> {
                if (archives.getTitle() != null) {
                    existingArchives.setTitle(archives.getTitle());
                }
                if (archives.getContent() != null) {
                    existingArchives.setContent(archives.getContent());
                }
                if (archives.getDate() != null) {
                    existingArchives.setDate(archives.getDate());
                }
                if (archives.getPage() != null) {
                    existingArchives.setPage(archives.getPage());
                }
                if (archives.getSecretlevel() != null) {
                    existingArchives.setSecretlevel(archives.getSecretlevel());
                }
                if (archives.getConfidentialityperiod() != null) {
                    existingArchives.setConfidentialityperiod(archives.getConfidentialityperiod());
                }
                if (archives.getConfidentialnumber() != null) {
                    existingArchives.setConfidentialnumber(archives.getConfidentialnumber());
                }
                if (archives.getStorageperiod() != null) {
                    existingArchives.setStorageperiod(archives.getStorageperiod());
                }
                if (archives.getPlannumber() != null) {
                    existingArchives.setPlannumber(archives.getPlannumber());
                }
                if (archives.getRemarks() != null) {
                    existingArchives.setRemarks(archives.getRemarks());
                }
                if (archives.getReceivingnumber() != null) {
                    existingArchives.setReceivingnumber(archives.getReceivingnumber());
                }

                return existingArchives;
            })
            .map(archivesRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Archives> findAll() {
        log.debug("Request to get all Archives");
        return archivesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Archives> findOne(String id) {
        log.debug("Request to get Archives : {}", id);
        return archivesRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Archives : {}", id);
        archivesRepository.deleteById(id);
    }
}

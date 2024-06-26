package com.cvicse.service.impl;

import com.cvicse.domain.Progressplan;
import com.cvicse.repository.ProgressplanRepository;
import com.cvicse.service.ProgressplanService;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.domain.Progressplan}.
 */
@Service
@Transactional
public class ProgressplanServiceImpl implements ProgressplanService {

    private final Logger log = LoggerFactory.getLogger(ProgressplanServiceImpl.class);

    private final ProgressplanRepository progressplanRepository;

    public ProgressplanServiceImpl(ProgressplanRepository progressplanRepository) {
        this.progressplanRepository = progressplanRepository;
    }

    @Override
    public Progressplan save(Progressplan progressplan) {
        log.debug("Request to save Progressplan : {}", progressplan);
        return progressplanRepository.save(progressplan);
    }

    @Override
    public Progressplan update(Progressplan progressplan) {
        log.debug("Request to update Progressplan : {}", progressplan);
        return progressplanRepository.save(progressplan);
    }

    @Override
    public Optional<Progressplan> partialUpdate(Progressplan progressplan) {
        log.debug("Request to partially update Progressplan : {}", progressplan);

        return progressplanRepository
            .findById(progressplan.getId())
            .map(existingProgressplan -> {
                if (progressplan.getProgressname() != null) {
                    existingProgressplan.setProgressname(progressplan.getProgressname());
                }
                if (progressplan.getProgresstype() != null) {
                    existingProgressplan.setProgresstype(progressplan.getProgresstype());
                }
                if (progressplan.getWorkfocus() != null) {
                    existingProgressplan.setWorkfocus(progressplan.getWorkfocus());
                }
                if (progressplan.getCreatetime() != null) {
                    existingProgressplan.setCreatetime(progressplan.getCreatetime());
                }
                if (progressplan.getCreatorname() != null) {
                    existingProgressplan.setCreatorname(progressplan.getCreatorname());
                }
                if (progressplan.getResponsiblename() != null) {
                    existingProgressplan.setResponsiblename(progressplan.getResponsiblename());
                }
                if (progressplan.getStatus() != null) {
                    existingProgressplan.setStatus(progressplan.getStatus());
                }
                if (progressplan.getAuditStatus() != null) {
                    existingProgressplan.setAuditStatus(progressplan.getAuditStatus());
                }

                return existingProgressplan;
            })
            .map(progressplanRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Progressplan> findAll() {
        log.debug("Request to get all Progressplans");
        return progressplanRepository.findAll();
    }

    /**
     *  Get all the progressplans where Comprehensivecontrol is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Progressplan> findAllWhereComprehensivecontrolIsNull() {
        log.debug("Request to get all progressplans where Comprehensivecontrol is null");
        return StreamSupport.stream(progressplanRepository.findAll().spliterator(), false)
            .filter(progressplan -> progressplan.getComprehensivecontrol() == null)
            .toList();
    }

    /**
     *  Get all the progressplans where Progressplanreturns is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<Progressplan> findAllWhereProgressplanreturnsIsNull() {
        log.debug("Request to get all progressplans where Progressplanreturns is null");
        return StreamSupport.stream(progressplanRepository.findAll().spliterator(), false)
            .filter(progressplan -> progressplan.getProgressplanreturns() == null)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Progressplan> findOne(String id) {
        log.debug("Request to get Progressplan : {}", id);
        return progressplanRepository.findById(id);
    }

    @Override
    public void delete(String id) {
        log.debug("Request to delete Progressplan : {}", id);
        progressplanRepository.deleteById(id);
    }
}

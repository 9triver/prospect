package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Subject;
import com.cvicse.jy1.repository.SubjectRepository;
import com.cvicse.jy1.service.SubjectService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Subject}.
 */
@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private static final Logger log = LoggerFactory.getLogger(SubjectServiceImpl.class);

    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject save(Subject subject) {
        log.debug("Request to save Subject : {}", subject);
        return subjectRepository.save(subject);
    }

    @Override
    public Subject update(Subject subject) {
        log.debug("Request to update Subject : {}", subject);
        return subjectRepository.save(subject);
    }

    @Override
    public Optional<Subject> partialUpdate(Subject subject) {
        log.debug("Request to partially update Subject : {}", subject);

        return subjectRepository
            .findById(subject.getId())
            .map(existingSubject -> {
                if (subject.getName() != null) {
                    existingSubject.setName(subject.getName());
                }
                if (subject.getType() != null) {
                    existingSubject.setType(subject.getType());
                }
                if (subject.getParentid() != null) {
                    existingSubject.setParentid(subject.getParentid());
                }
                if (subject.getRemark() != null) {
                    existingSubject.setRemark(subject.getRemark());
                }

                return existingSubject;
            })
            .map(subjectRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Subject> findAll() {
        log.debug("Request to get all Subjects");
        return subjectRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Subject> findOne(Integer id) {
        log.debug("Request to get Subject : {}", id);
        return subjectRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete Subject : {}", id);
        subjectRepository.deleteById(id);
    }
}

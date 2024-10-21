package com.cvicse.jy1.service.impl;

import com.cvicse.jy1.domain.Letter;
import com.cvicse.jy1.repository.LetterRepository;
import com.cvicse.jy1.service.LetterService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.cvicse.jy1.domain.Letter}.
 */
@Service
@Transactional
public class LetterServiceImpl implements LetterService {

    private static final Logger log = LoggerFactory.getLogger(LetterServiceImpl.class);

    private final LetterRepository letterRepository;

    public LetterServiceImpl(LetterRepository letterRepository) {
        this.letterRepository = letterRepository;
    }

    @Override
    public Letter save(Letter letter) {
        log.debug("Request to save Letter : {}", letter);
        return letterRepository.save(letter);
    }

    @Override
    public Letter update(Letter letter) {
        log.debug("Request to update Letter : {}", letter);
        return letterRepository.save(letter);
    }

    @Override
    public Optional<Letter> partialUpdate(Letter letter) {
        log.debug("Request to partially update Letter : {}", letter);

        return letterRepository
            .findById(letter.getId())
            .map(existingLetter -> {
                if (letter.getLettername() != null) {
                    existingLetter.setLettername(letter.getLettername());
                }
                if (letter.getLetternumber() != null) {
                    existingLetter.setLetternumber(letter.getLetternumber());
                }
                if (letter.getLettertype() != null) {
                    existingLetter.setLettertype(letter.getLettertype());
                }
                if (letter.getSecretlevel() != null) {
                    existingLetter.setSecretlevel(letter.getSecretlevel());
                }
                if (letter.getLettercontent() != null) {
                    existingLetter.setLettercontent(letter.getLettercontent());
                }
                if (letter.getLetterstatus() != null) {
                    existingLetter.setLetterstatus(letter.getLetterstatus());
                }
                if (letter.getLettertime() != null) {
                    existingLetter.setLettertime(letter.getLettertime());
                }
                if (letter.getPreviousfile() != null) {
                    existingLetter.setPreviousfile(letter.getPreviousfile());
                }
                if (letter.getDatarecordstatus() != null) {
                    existingLetter.setDatarecordstatus(letter.getDatarecordstatus());
                }

                return existingLetter;
            })
            .map(letterRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Letter> findAll() {
        log.debug("Request to get all Letters");
        return letterRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Letter> findOne(Integer id) {
        log.debug("Request to get Letter : {}", id);
        return letterRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        log.debug("Request to delete Letter : {}", id);
        letterRepository.deleteById(id);
    }
}

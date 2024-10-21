package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.Letter;
import com.cvicse.jy1.repository.LetterRepository;
import com.cvicse.jy1.service.LetterService;
import com.cvicse.jy1.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.cvicse.jy1.domain.Letter}.
 */
@RestController
@RequestMapping("/api/letters")
public class LetterResource {

    private static final Logger log = LoggerFactory.getLogger(LetterResource.class);

    private static final String ENTITY_NAME = "letter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LetterService letterService;

    private final LetterRepository letterRepository;

    public LetterResource(LetterService letterService, LetterRepository letterRepository) {
        this.letterService = letterService;
        this.letterRepository = letterRepository;
    }

    /**
     * {@code POST  /letters} : Create a new letter.
     *
     * @param letter the letter to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new letter, or with status {@code 400 (Bad Request)} if the letter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Letter> createLetter(@RequestBody Letter letter) throws URISyntaxException {
        log.debug("REST request to save Letter : {}", letter);
        if (letter.getId() != null) {
            throw new BadRequestAlertException("A new letter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        letter = letterService.save(letter);
        return ResponseEntity.created(new URI("/api/letters/" + letter.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, letter.getId().toString()))
            .body(letter);
    }

    /**
     * {@code PUT  /letters/:id} : Updates an existing letter.
     *
     * @param id the id of the letter to save.
     * @param letter the letter to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated letter,
     * or with status {@code 400 (Bad Request)} if the letter is not valid,
     * or with status {@code 500 (Internal Server Error)} if the letter couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Letter> updateLetter(@PathVariable(value = "id", required = false) final Integer id, @RequestBody Letter letter)
        throws URISyntaxException {
        log.debug("REST request to update Letter : {}, {}", id, letter);
        if (letter.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, letter.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!letterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        letter = letterService.update(letter);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, letter.getId().toString()))
            .body(letter);
    }

    /**
     * {@code PATCH  /letters/:id} : Partial updates given fields of an existing letter, field will ignore if it is null
     *
     * @param id the id of the letter to save.
     * @param letter the letter to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated letter,
     * or with status {@code 400 (Bad Request)} if the letter is not valid,
     * or with status {@code 404 (Not Found)} if the letter is not found,
     * or with status {@code 500 (Internal Server Error)} if the letter couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Letter> partialUpdateLetter(
        @PathVariable(value = "id", required = false) final Integer id,
        @RequestBody Letter letter
    ) throws URISyntaxException {
        log.debug("REST request to partial update Letter partially : {}, {}", id, letter);
        if (letter.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, letter.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!letterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Letter> result = letterService.partialUpdate(letter);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, letter.getId().toString())
        );
    }

    /**
     * {@code GET  /letters} : get all the letters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of letters in body.
     */
    @GetMapping("")
    public List<Letter> getAllLetters() {
        log.debug("REST request to get all Letters");
        return letterService.findAll();
    }

    /**
     * {@code GET  /letters/:id} : get the "id" letter.
     *
     * @param id the id of the letter to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the letter, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Letter> getLetter(@PathVariable("id") Integer id) {
        log.debug("REST request to get Letter : {}", id);
        Optional<Letter> letter = letterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(letter);
    }

    /**
     * {@code DELETE  /letters/:id} : delete the "id" letter.
     *
     * @param id the id of the letter to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLetter(@PathVariable("id") Integer id) {
        log.debug("REST request to delete Letter : {}", id);
        letterService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

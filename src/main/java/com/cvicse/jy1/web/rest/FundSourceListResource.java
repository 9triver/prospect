package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.FundSourceList;
import com.cvicse.jy1.repository.FundSourceListRepository;
import com.cvicse.jy1.service.FundSourceListService;
import com.cvicse.jy1.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
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
 * REST controller for managing {@link com.cvicse.jy1.domain.FundSourceList}.
 */
@RestController
@RequestMapping("/api/fund-source-lists")
public class FundSourceListResource {

    private static final Logger log = LoggerFactory.getLogger(FundSourceListResource.class);

    private static final String ENTITY_NAME = "fundSourceList";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FundSourceListService fundSourceListService;

    private final FundSourceListRepository fundSourceListRepository;

    public FundSourceListResource(FundSourceListService fundSourceListService, FundSourceListRepository fundSourceListRepository) {
        this.fundSourceListService = fundSourceListService;
        this.fundSourceListRepository = fundSourceListRepository;
    }

    /**
     * {@code POST  /fund-source-lists} : Create a new fundSourceList.
     *
     * @param fundSourceList the fundSourceList to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fundSourceList, or with status {@code 400 (Bad Request)} if the fundSourceList has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<FundSourceList> createFundSourceList(@Valid @RequestBody FundSourceList fundSourceList)
        throws URISyntaxException {
        log.debug("REST request to save FundSourceList : {}", fundSourceList);
        if (fundSourceList.getId() != null) {
            throw new BadRequestAlertException("A new fundSourceList cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fundSourceList = fundSourceListService.save(fundSourceList);
        return ResponseEntity.created(new URI("/api/fund-source-lists/" + fundSourceList.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, fundSourceList.getId().toString()))
            .body(fundSourceList);
    }

    /**
     * {@code PUT  /fund-source-lists/:id} : Updates an existing fundSourceList.
     *
     * @param id the id of the fundSourceList to save.
     * @param fundSourceList the fundSourceList to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fundSourceList,
     * or with status {@code 400 (Bad Request)} if the fundSourceList is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fundSourceList couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<FundSourceList> updateFundSourceList(
        @PathVariable(value = "id", required = false) final Integer id,
        @Valid @RequestBody FundSourceList fundSourceList
    ) throws URISyntaxException {
        log.debug("REST request to update FundSourceList : {}, {}", id, fundSourceList);
        if (fundSourceList.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fundSourceList.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fundSourceListRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        fundSourceList = fundSourceListService.update(fundSourceList);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fundSourceList.getId().toString()))
            .body(fundSourceList);
    }

    /**
     * {@code PATCH  /fund-source-lists/:id} : Partial updates given fields of an existing fundSourceList, field will ignore if it is null
     *
     * @param id the id of the fundSourceList to save.
     * @param fundSourceList the fundSourceList to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fundSourceList,
     * or with status {@code 400 (Bad Request)} if the fundSourceList is not valid,
     * or with status {@code 404 (Not Found)} if the fundSourceList is not found,
     * or with status {@code 500 (Internal Server Error)} if the fundSourceList couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FundSourceList> partialUpdateFundSourceList(
        @PathVariable(value = "id", required = false) final Integer id,
        @NotNull @RequestBody FundSourceList fundSourceList
    ) throws URISyntaxException {
        log.debug("REST request to partial update FundSourceList partially : {}, {}", id, fundSourceList);
        if (fundSourceList.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fundSourceList.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!fundSourceListRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FundSourceList> result = fundSourceListService.partialUpdate(fundSourceList);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fundSourceList.getId().toString())
        );
    }

    /**
     * {@code GET  /fund-source-lists} : get all the fundSourceLists.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fundSourceLists in body.
     */
    @GetMapping("")
    public List<FundSourceList> getAllFundSourceLists() {
        log.debug("REST request to get all FundSourceLists");
        return fundSourceListService.findAll();
    }

    /**
     * {@code GET  /fund-source-lists/:id} : get the "id" fundSourceList.
     *
     * @param id the id of the fundSourceList to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fundSourceList, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FundSourceList> getFundSourceList(@PathVariable("id") Integer id) {
        log.debug("REST request to get FundSourceList : {}", id);
        Optional<FundSourceList> fundSourceList = fundSourceListService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fundSourceList);
    }

    /**
     * {@code DELETE  /fund-source-lists/:id} : delete the "id" fundSourceList.
     *
     * @param id the id of the fundSourceList to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFundSourceList(@PathVariable("id") Integer id) {
        log.debug("REST request to delete FundSourceList : {}", id);
        fundSourceListService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}

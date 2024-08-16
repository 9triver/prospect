package com.cvicse.jy1.web.rest;

import com.cvicse.jy1.domain.Officers;
import com.cvicse.jy1.domain.enumeration.OfficersStatus;
import com.cvicse.jy1.repository.OfficersRepository;
import com.cvicse.jy1.web.rest.errors.BadRequestAlertException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.cvicse.jy1.domain.Officers}.
 */
@RestController
@RequestMapping("/api/officers")
@Transactional
public class OfficersResource {

    private static final Logger log = LoggerFactory.getLogger(OfficersResource.class);

    private static final String ENTITY_NAME = "officers";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OfficersRepository officersRepository;

    public OfficersResource(OfficersRepository officersRepository) {
        this.officersRepository = officersRepository;
    }

    /**
     * {@code POST  /officers} : Create a new officers.
     *
     * @param officers the officers to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new officers, or with status {@code 400 (Bad Request)} if the officers has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Officers> createOfficers(@Valid @RequestBody Officers officers) throws URISyntaxException {
        log.debug("REST request to save Officers : {}", officers);
        if (officers.getId() != null) {
            throw new BadRequestAlertException("A new officers cannot already have an ID", ENTITY_NAME, "idexists");
        }
        officers = officersRepository.save(officers);
        return ResponseEntity.created(new URI("/api/officers/" + officers.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, officers.getId()))
            .body(officers);
    }

    /**
     * {@code PUT  /officers/:id} : Updates an existing officers.
     *
     * @param id the id of the officers to save.
     * @param officers the officers to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated officers,
     * or with status {@code 400 (Bad Request)} if the officers is not valid,
     * or with status {@code 500 (Internal Server Error)} if the officers couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Officers> updateOfficers(
        @PathVariable(value = "id", required = false) final String id,
        @Valid @RequestBody Officers officers
    ) throws URISyntaxException {
        log.debug("REST request to update Officers : {}, {}", id, officers);
        if (officers.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, officers.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!officersRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        officers = officersRepository.save(officers);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, officers.getId()))
            .body(officers);
    }

    /**
     * {@code PATCH  /officers/:id} : Partial updates given fields of an existing officers, field will ignore if it is null
     *
     * @param id the id of the officers to save.
     * @param officers the officers to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated officers,
     * or with status {@code 400 (Bad Request)} if the officers is not valid,
     * or with status {@code 404 (Not Found)} if the officers is not found,
     * or with status {@code 500 (Internal Server Error)} if the officers couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Officers> partialUpdateOfficers(
        @PathVariable(value = "id", required = false) final String id,
        @NotNull @RequestBody Officers officers
    ) throws URISyntaxException {
        log.debug("REST request to partial update Officers partially : {}, {}", id, officers);
        if (officers.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, officers.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!officersRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Officers> result = officersRepository
            .findById(officers.getId())
            .map(existingOfficers -> {
                if (officers.getName() != null) {
                    existingOfficers.setName(officers.getName());
                }
                if (officers.getPassword() != null) {
                    existingOfficers.setPassword(officers.getPassword());
                }
                if (officers.getEmail() != null) {
                    existingOfficers.setEmail(officers.getEmail());
                }
                if (officers.getPhone() != null) {
                    existingOfficers.setPhone(officers.getPhone());
                }
                if (officers.getHiredate() != null) {
                    existingOfficers.setHiredate(officers.getHiredate());
                }
                if (officers.getYears() != null) {
                    existingOfficers.setYears(officers.getYears());
                }
                if (officers.getStatus() != null) {
                    existingOfficers.setStatus(officers.getStatus());
                }

                return existingOfficers;
            })
            .map(officersRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, officers.getId())
        );
    }

    /**
     * {@code GET  /officers} : get all the officers.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of officers in body.
     */
    @GetMapping("")
    public List<Officers> getAllOfficers(@RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload) {
        log.debug("REST request to get all Officers");
        if (eagerload) {
            return officersRepository.findAllWithEagerRelationships();
        } else {
            return officersRepository.findAll();
        }
    }

    /**
     * 条件查询
     *
     */
    @PostMapping(value = "/query" )
    public List<Officers> getOfficersByQuery(@RequestBody Officers officers) {
        log.debug("REST request to get all officers");
        // 提取查询参数
        String id = officers.getId().toString();
        String name = officers.getName();
        // String department = officers.getDepartment();
        Integer years = officers.getYears();
        LocalDate hiredate = (officers.getHiredate() != null) ? officers.getHiredate() : null;
        OfficersStatus status = officers.getStatus(); // 获取 Status 枚举值 

        System.err.println("！！！！！！！！！！！！查询条件：name="+name+"！！！！！！！！！！！！"+officers);
        // 调用 repository 方法
        return officersRepository.findAllWithToOneRelationship(
            id, name, years, hiredate, status
        );
    }

    /**
     * {@code GET  /officers/:id} : get the "id" officers.
     *
     * @param id the id of the officers to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the officers, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Officers> getOfficers(@PathVariable("id") String id) {
        log.debug("REST request to get Officers : {}", id);
        Optional<Officers> officers = officersRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(officers);
    }

    /**
     * {@code DELETE  /officers/:id} : delete the "id" officers.
     *
     * @param id the id of the officers to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOfficers(@PathVariable("id") String id) {
        log.debug("REST request to delete Officers : {}", id);
        officersRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}

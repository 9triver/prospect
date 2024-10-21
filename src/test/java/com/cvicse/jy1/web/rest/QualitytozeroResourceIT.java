package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.QualitytozeroAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.Qualitytozero;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.repository.QualitytozeroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link QualitytozeroResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QualitytozeroResourceIT {

    private static final String DEFAULT_WORKBAGID = "AAAAAAAAAA";
    private static final String UPDATED_WORKBAGID = "BBBBBBBBBB";

    private static final String DEFAULT_BELONGWBSID = "AAAAAAAAAA";
    private static final String UPDATED_BELONGWBSID = "BBBBBBBBBB";

    private static final String DEFAULT_OUTSOURCINGCONTRACTID = "AAAAAAAAAA";
    private static final String UPDATED_OUTSOURCINGCONTRACTID = "BBBBBBBBBB";

    private static final String DEFAULT_QUALITYPROBLEMID = "AAAAAAAAAA";
    private static final String UPDATED_QUALITYPROBLEMID = "BBBBBBBBBB";

    private static final String DEFAULT_QUALITYPROBLEMNAME = "AAAAAAAAAA";
    private static final String UPDATED_QUALITYPROBLEMNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PROBLEMHAPPENTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PROBLEMHAPPENTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PROBLEMRESPONSIBLEPERSON = "AAAAAAAAAA";
    private static final String UPDATED_PROBLEMRESPONSIBLEPERSON = "BBBBBBBBBB";

    private static final String DEFAULT_PROBLEMRESPONSIBLEUNIT = "AAAAAAAAAA";
    private static final String UPDATED_PROBLEMRESPONSIBLEUNIT = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCTTYPE = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCTTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PRODUCTNAME = "AAAAAAAAAA";
    private static final String UPDATED_PRODUCTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROBLEMPHENOMENON = "AAAAAAAAAA";
    private static final String UPDATED_PROBLEMPHENOMENON = "BBBBBBBBBB";

    private static final String DEFAULT_PROBLEMTYPE = "AAAAAAAAAA";
    private static final String UPDATED_PROBLEMTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_QUALITYLEVEL = "AAAAAAAAAA";
    private static final String UPDATED_QUALITYLEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_ZEROTYPE = "AAAAAAAAAA";
    private static final String UPDATED_ZEROTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PROBLEMREASONANALYSIS = "AAAAAAAAAA";
    private static final String UPDATED_PROBLEMREASONANALYSIS = "BBBBBBBBBB";

    private static final String DEFAULT_PROBLEMREASONCATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_PROBLEMREASONCATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_TAKEMEASURES = "AAAAAAAAAA";
    private static final String UPDATED_TAKEMEASURES = "BBBBBBBBBB";

    private static final String DEFAULT_ONEBYONECATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_ONEBYONECATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_VERIFICATIONEFFECT = "AAAAAAAAAA";
    private static final String UPDATED_VERIFICATIONEFFECT = "BBBBBBBBBB";

    private static final String DEFAULT_QUALITYPROJECTREPORT = "AAAAAAAAAA";
    private static final String UPDATED_QUALITYPROJECTREPORT = "BBBBBBBBBB";

    private static final String DEFAULT_QUALITYTOZEROREPORT = "AAAAAAAAAA";
    private static final String UPDATED_QUALITYTOZEROREPORT = "BBBBBBBBBB";

    private static final String DEFAULT_REVIEWOPINION = "AAAAAAAAAA";
    private static final String UPDATED_REVIEWOPINION = "BBBBBBBBBB";

    private static final String DEFAULT_IMPLEMENTATIONVERIFICATIONTABLE = "AAAAAAAAAA";
    private static final String UPDATED_IMPLEMENTATIONVERIFICATIONTABLE = "BBBBBBBBBB";

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.NOT_AUDITED;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.IN_AUDIT;

    private static final String ENTITY_API_URL = "/api/qualitytozeros";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QualitytozeroRepository qualitytozeroRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQualitytozeroMockMvc;

    private Qualitytozero qualitytozero;

    private Qualitytozero insertedQualitytozero;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Qualitytozero createEntity(EntityManager em) {
        Qualitytozero qualitytozero = new Qualitytozero()
            .workbagid(DEFAULT_WORKBAGID)
            .belongwbsid(DEFAULT_BELONGWBSID)
            .outsourcingcontractid(DEFAULT_OUTSOURCINGCONTRACTID)
            .qualityproblemid(DEFAULT_QUALITYPROBLEMID)
            .qualityproblemname(DEFAULT_QUALITYPROBLEMNAME)
            .problemhappentime(DEFAULT_PROBLEMHAPPENTIME)
            .problemresponsibleperson(DEFAULT_PROBLEMRESPONSIBLEPERSON)
            .problemresponsibleunit(DEFAULT_PROBLEMRESPONSIBLEUNIT)
            .producttype(DEFAULT_PRODUCTTYPE)
            .productname(DEFAULT_PRODUCTNAME)
            .problemphenomenon(DEFAULT_PROBLEMPHENOMENON)
            .problemtype(DEFAULT_PROBLEMTYPE)
            .qualitylevel(DEFAULT_QUALITYLEVEL)
            .zerotype(DEFAULT_ZEROTYPE)
            .problemreasonanalysis(DEFAULT_PROBLEMREASONANALYSIS)
            .problemreasoncategory(DEFAULT_PROBLEMREASONCATEGORY)
            .takemeasures(DEFAULT_TAKEMEASURES)
            .onebyonecategory(DEFAULT_ONEBYONECATEGORY)
            .verificationeffect(DEFAULT_VERIFICATIONEFFECT)
            .qualityprojectreport(DEFAULT_QUALITYPROJECTREPORT)
            .qualitytozeroreport(DEFAULT_QUALITYTOZEROREPORT)
            .reviewopinion(DEFAULT_REVIEWOPINION)
            .implementationverificationtable(DEFAULT_IMPLEMENTATIONVERIFICATIONTABLE)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return qualitytozero;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Qualitytozero createUpdatedEntity(EntityManager em) {
        Qualitytozero qualitytozero = new Qualitytozero()
            .workbagid(UPDATED_WORKBAGID)
            .belongwbsid(UPDATED_BELONGWBSID)
            .outsourcingcontractid(UPDATED_OUTSOURCINGCONTRACTID)
            .qualityproblemid(UPDATED_QUALITYPROBLEMID)
            .qualityproblemname(UPDATED_QUALITYPROBLEMNAME)
            .problemhappentime(UPDATED_PROBLEMHAPPENTIME)
            .problemresponsibleperson(UPDATED_PROBLEMRESPONSIBLEPERSON)
            .problemresponsibleunit(UPDATED_PROBLEMRESPONSIBLEUNIT)
            .producttype(UPDATED_PRODUCTTYPE)
            .productname(UPDATED_PRODUCTNAME)
            .problemphenomenon(UPDATED_PROBLEMPHENOMENON)
            .problemtype(UPDATED_PROBLEMTYPE)
            .qualitylevel(UPDATED_QUALITYLEVEL)
            .zerotype(UPDATED_ZEROTYPE)
            .problemreasonanalysis(UPDATED_PROBLEMREASONANALYSIS)
            .problemreasoncategory(UPDATED_PROBLEMREASONCATEGORY)
            .takemeasures(UPDATED_TAKEMEASURES)
            .onebyonecategory(UPDATED_ONEBYONECATEGORY)
            .verificationeffect(UPDATED_VERIFICATIONEFFECT)
            .qualityprojectreport(UPDATED_QUALITYPROJECTREPORT)
            .qualitytozeroreport(UPDATED_QUALITYTOZEROREPORT)
            .reviewopinion(UPDATED_REVIEWOPINION)
            .implementationverificationtable(UPDATED_IMPLEMENTATIONVERIFICATIONTABLE)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return qualitytozero;
    }

    @BeforeEach
    public void initTest() {
        qualitytozero = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedQualitytozero != null) {
            qualitytozeroRepository.delete(insertedQualitytozero);
            insertedQualitytozero = null;
        }
    }

    @Test
    @Transactional
    void createQualitytozero() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Qualitytozero
        var returnedQualitytozero = om.readValue(
            restQualitytozeroMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualitytozero)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Qualitytozero.class
        );

        // Validate the Qualitytozero in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertQualitytozeroUpdatableFieldsEquals(returnedQualitytozero, getPersistedQualitytozero(returnedQualitytozero));

        insertedQualitytozero = returnedQualitytozero;
    }

    @Test
    @Transactional
    void createQualitytozeroWithExistingId() throws Exception {
        // Create the Qualitytozero with an existing ID
        qualitytozero.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQualitytozeroMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualitytozero)))
            .andExpect(status().isBadRequest());

        // Validate the Qualitytozero in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQualitytozeros() throws Exception {
        // Initialize the database
        insertedQualitytozero = qualitytozeroRepository.saveAndFlush(qualitytozero);

        // Get all the qualitytozeroList
        restQualitytozeroMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qualitytozero.getId().intValue())))
            .andExpect(jsonPath("$.[*].workbagid").value(hasItem(DEFAULT_WORKBAGID)))
            .andExpect(jsonPath("$.[*].belongwbsid").value(hasItem(DEFAULT_BELONGWBSID)))
            .andExpect(jsonPath("$.[*].outsourcingcontractid").value(hasItem(DEFAULT_OUTSOURCINGCONTRACTID)))
            .andExpect(jsonPath("$.[*].qualityproblemid").value(hasItem(DEFAULT_QUALITYPROBLEMID)))
            .andExpect(jsonPath("$.[*].qualityproblemname").value(hasItem(DEFAULT_QUALITYPROBLEMNAME)))
            .andExpect(jsonPath("$.[*].problemhappentime").value(hasItem(DEFAULT_PROBLEMHAPPENTIME.toString())))
            .andExpect(jsonPath("$.[*].problemresponsibleperson").value(hasItem(DEFAULT_PROBLEMRESPONSIBLEPERSON)))
            .andExpect(jsonPath("$.[*].problemresponsibleunit").value(hasItem(DEFAULT_PROBLEMRESPONSIBLEUNIT)))
            .andExpect(jsonPath("$.[*].producttype").value(hasItem(DEFAULT_PRODUCTTYPE)))
            .andExpect(jsonPath("$.[*].productname").value(hasItem(DEFAULT_PRODUCTNAME)))
            .andExpect(jsonPath("$.[*].problemphenomenon").value(hasItem(DEFAULT_PROBLEMPHENOMENON)))
            .andExpect(jsonPath("$.[*].problemtype").value(hasItem(DEFAULT_PROBLEMTYPE)))
            .andExpect(jsonPath("$.[*].qualitylevel").value(hasItem(DEFAULT_QUALITYLEVEL)))
            .andExpect(jsonPath("$.[*].zerotype").value(hasItem(DEFAULT_ZEROTYPE)))
            .andExpect(jsonPath("$.[*].problemreasonanalysis").value(hasItem(DEFAULT_PROBLEMREASONANALYSIS)))
            .andExpect(jsonPath("$.[*].problemreasoncategory").value(hasItem(DEFAULT_PROBLEMREASONCATEGORY)))
            .andExpect(jsonPath("$.[*].takemeasures").value(hasItem(DEFAULT_TAKEMEASURES)))
            .andExpect(jsonPath("$.[*].onebyonecategory").value(hasItem(DEFAULT_ONEBYONECATEGORY)))
            .andExpect(jsonPath("$.[*].verificationeffect").value(hasItem(DEFAULT_VERIFICATIONEFFECT)))
            .andExpect(jsonPath("$.[*].qualityprojectreport").value(hasItem(DEFAULT_QUALITYPROJECTREPORT)))
            .andExpect(jsonPath("$.[*].qualitytozeroreport").value(hasItem(DEFAULT_QUALITYTOZEROREPORT)))
            .andExpect(jsonPath("$.[*].reviewopinion").value(hasItem(DEFAULT_REVIEWOPINION)))
            .andExpect(jsonPath("$.[*].implementationverificationtable").value(hasItem(DEFAULT_IMPLEMENTATIONVERIFICATIONTABLE)))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getQualitytozero() throws Exception {
        // Initialize the database
        insertedQualitytozero = qualitytozeroRepository.saveAndFlush(qualitytozero);

        // Get the qualitytozero
        restQualitytozeroMockMvc
            .perform(get(ENTITY_API_URL_ID, qualitytozero.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(qualitytozero.getId().intValue()))
            .andExpect(jsonPath("$.workbagid").value(DEFAULT_WORKBAGID))
            .andExpect(jsonPath("$.belongwbsid").value(DEFAULT_BELONGWBSID))
            .andExpect(jsonPath("$.outsourcingcontractid").value(DEFAULT_OUTSOURCINGCONTRACTID))
            .andExpect(jsonPath("$.qualityproblemid").value(DEFAULT_QUALITYPROBLEMID))
            .andExpect(jsonPath("$.qualityproblemname").value(DEFAULT_QUALITYPROBLEMNAME))
            .andExpect(jsonPath("$.problemhappentime").value(DEFAULT_PROBLEMHAPPENTIME.toString()))
            .andExpect(jsonPath("$.problemresponsibleperson").value(DEFAULT_PROBLEMRESPONSIBLEPERSON))
            .andExpect(jsonPath("$.problemresponsibleunit").value(DEFAULT_PROBLEMRESPONSIBLEUNIT))
            .andExpect(jsonPath("$.producttype").value(DEFAULT_PRODUCTTYPE))
            .andExpect(jsonPath("$.productname").value(DEFAULT_PRODUCTNAME))
            .andExpect(jsonPath("$.problemphenomenon").value(DEFAULT_PROBLEMPHENOMENON))
            .andExpect(jsonPath("$.problemtype").value(DEFAULT_PROBLEMTYPE))
            .andExpect(jsonPath("$.qualitylevel").value(DEFAULT_QUALITYLEVEL))
            .andExpect(jsonPath("$.zerotype").value(DEFAULT_ZEROTYPE))
            .andExpect(jsonPath("$.problemreasonanalysis").value(DEFAULT_PROBLEMREASONANALYSIS))
            .andExpect(jsonPath("$.problemreasoncategory").value(DEFAULT_PROBLEMREASONCATEGORY))
            .andExpect(jsonPath("$.takemeasures").value(DEFAULT_TAKEMEASURES))
            .andExpect(jsonPath("$.onebyonecategory").value(DEFAULT_ONEBYONECATEGORY))
            .andExpect(jsonPath("$.verificationeffect").value(DEFAULT_VERIFICATIONEFFECT))
            .andExpect(jsonPath("$.qualityprojectreport").value(DEFAULT_QUALITYPROJECTREPORT))
            .andExpect(jsonPath("$.qualitytozeroreport").value(DEFAULT_QUALITYTOZEROREPORT))
            .andExpect(jsonPath("$.reviewopinion").value(DEFAULT_REVIEWOPINION))
            .andExpect(jsonPath("$.implementationverificationtable").value(DEFAULT_IMPLEMENTATIONVERIFICATIONTABLE))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingQualitytozero() throws Exception {
        // Get the qualitytozero
        restQualitytozeroMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQualitytozero() throws Exception {
        // Initialize the database
        insertedQualitytozero = qualitytozeroRepository.saveAndFlush(qualitytozero);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualitytozero
        Qualitytozero updatedQualitytozero = qualitytozeroRepository.findById(qualitytozero.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedQualitytozero are not directly saved in db
        em.detach(updatedQualitytozero);
        updatedQualitytozero
            .workbagid(UPDATED_WORKBAGID)
            .belongwbsid(UPDATED_BELONGWBSID)
            .outsourcingcontractid(UPDATED_OUTSOURCINGCONTRACTID)
            .qualityproblemid(UPDATED_QUALITYPROBLEMID)
            .qualityproblemname(UPDATED_QUALITYPROBLEMNAME)
            .problemhappentime(UPDATED_PROBLEMHAPPENTIME)
            .problemresponsibleperson(UPDATED_PROBLEMRESPONSIBLEPERSON)
            .problemresponsibleunit(UPDATED_PROBLEMRESPONSIBLEUNIT)
            .producttype(UPDATED_PRODUCTTYPE)
            .productname(UPDATED_PRODUCTNAME)
            .problemphenomenon(UPDATED_PROBLEMPHENOMENON)
            .problemtype(UPDATED_PROBLEMTYPE)
            .qualitylevel(UPDATED_QUALITYLEVEL)
            .zerotype(UPDATED_ZEROTYPE)
            .problemreasonanalysis(UPDATED_PROBLEMREASONANALYSIS)
            .problemreasoncategory(UPDATED_PROBLEMREASONCATEGORY)
            .takemeasures(UPDATED_TAKEMEASURES)
            .onebyonecategory(UPDATED_ONEBYONECATEGORY)
            .verificationeffect(UPDATED_VERIFICATIONEFFECT)
            .qualityprojectreport(UPDATED_QUALITYPROJECTREPORT)
            .qualitytozeroreport(UPDATED_QUALITYTOZEROREPORT)
            .reviewopinion(UPDATED_REVIEWOPINION)
            .implementationverificationtable(UPDATED_IMPLEMENTATIONVERIFICATIONTABLE)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restQualitytozeroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedQualitytozero.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedQualitytozero))
            )
            .andExpect(status().isOk());

        // Validate the Qualitytozero in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQualitytozeroToMatchAllProperties(updatedQualitytozero);
    }

    @Test
    @Transactional
    void putNonExistingQualitytozero() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitytozero.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualitytozeroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, qualitytozero.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualitytozero))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualitytozero in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQualitytozero() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitytozero.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualitytozeroMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualitytozero))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualitytozero in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQualitytozero() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitytozero.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualitytozeroMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualitytozero)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Qualitytozero in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQualitytozeroWithPatch() throws Exception {
        // Initialize the database
        insertedQualitytozero = qualitytozeroRepository.saveAndFlush(qualitytozero);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualitytozero using partial update
        Qualitytozero partialUpdatedQualitytozero = new Qualitytozero();
        partialUpdatedQualitytozero.setId(qualitytozero.getId());

        partialUpdatedQualitytozero
            .belongwbsid(UPDATED_BELONGWBSID)
            .problemresponsibleunit(UPDATED_PROBLEMRESPONSIBLEUNIT)
            .problemphenomenon(UPDATED_PROBLEMPHENOMENON)
            .problemtype(UPDATED_PROBLEMTYPE)
            .qualitylevel(UPDATED_QUALITYLEVEL)
            .zerotype(UPDATED_ZEROTYPE)
            .problemreasonanalysis(UPDATED_PROBLEMREASONANALYSIS)
            .verificationeffect(UPDATED_VERIFICATIONEFFECT)
            .qualitytozeroreport(UPDATED_QUALITYTOZEROREPORT)
            .implementationverificationtable(UPDATED_IMPLEMENTATIONVERIFICATIONTABLE)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restQualitytozeroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualitytozero.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualitytozero))
            )
            .andExpect(status().isOk());

        // Validate the Qualitytozero in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualitytozeroUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedQualitytozero, qualitytozero),
            getPersistedQualitytozero(qualitytozero)
        );
    }

    @Test
    @Transactional
    void fullUpdateQualitytozeroWithPatch() throws Exception {
        // Initialize the database
        insertedQualitytozero = qualitytozeroRepository.saveAndFlush(qualitytozero);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualitytozero using partial update
        Qualitytozero partialUpdatedQualitytozero = new Qualitytozero();
        partialUpdatedQualitytozero.setId(qualitytozero.getId());

        partialUpdatedQualitytozero
            .workbagid(UPDATED_WORKBAGID)
            .belongwbsid(UPDATED_BELONGWBSID)
            .outsourcingcontractid(UPDATED_OUTSOURCINGCONTRACTID)
            .qualityproblemid(UPDATED_QUALITYPROBLEMID)
            .qualityproblemname(UPDATED_QUALITYPROBLEMNAME)
            .problemhappentime(UPDATED_PROBLEMHAPPENTIME)
            .problemresponsibleperson(UPDATED_PROBLEMRESPONSIBLEPERSON)
            .problemresponsibleunit(UPDATED_PROBLEMRESPONSIBLEUNIT)
            .producttype(UPDATED_PRODUCTTYPE)
            .productname(UPDATED_PRODUCTNAME)
            .problemphenomenon(UPDATED_PROBLEMPHENOMENON)
            .problemtype(UPDATED_PROBLEMTYPE)
            .qualitylevel(UPDATED_QUALITYLEVEL)
            .zerotype(UPDATED_ZEROTYPE)
            .problemreasonanalysis(UPDATED_PROBLEMREASONANALYSIS)
            .problemreasoncategory(UPDATED_PROBLEMREASONCATEGORY)
            .takemeasures(UPDATED_TAKEMEASURES)
            .onebyonecategory(UPDATED_ONEBYONECATEGORY)
            .verificationeffect(UPDATED_VERIFICATIONEFFECT)
            .qualityprojectreport(UPDATED_QUALITYPROJECTREPORT)
            .qualitytozeroreport(UPDATED_QUALITYTOZEROREPORT)
            .reviewopinion(UPDATED_REVIEWOPINION)
            .implementationverificationtable(UPDATED_IMPLEMENTATIONVERIFICATIONTABLE)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restQualitytozeroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualitytozero.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualitytozero))
            )
            .andExpect(status().isOk());

        // Validate the Qualitytozero in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualitytozeroUpdatableFieldsEquals(partialUpdatedQualitytozero, getPersistedQualitytozero(partialUpdatedQualitytozero));
    }

    @Test
    @Transactional
    void patchNonExistingQualitytozero() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitytozero.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualitytozeroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, qualitytozero.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualitytozero))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualitytozero in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQualitytozero() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitytozero.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualitytozeroMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualitytozero))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualitytozero in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQualitytozero() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitytozero.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualitytozeroMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(qualitytozero)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Qualitytozero in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQualitytozero() throws Exception {
        // Initialize the database
        insertedQualitytozero = qualitytozeroRepository.saveAndFlush(qualitytozero);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the qualitytozero
        restQualitytozeroMockMvc
            .perform(delete(ENTITY_API_URL_ID, qualitytozero.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return qualitytozeroRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Qualitytozero getPersistedQualitytozero(Qualitytozero qualitytozero) {
        return qualitytozeroRepository.findById(qualitytozero.getId()).orElseThrow();
    }

    protected void assertPersistedQualitytozeroToMatchAllProperties(Qualitytozero expectedQualitytozero) {
        assertQualitytozeroAllPropertiesEquals(expectedQualitytozero, getPersistedQualitytozero(expectedQualitytozero));
    }

    protected void assertPersistedQualitytozeroToMatchUpdatableProperties(Qualitytozero expectedQualitytozero) {
        assertQualitytozeroAllUpdatablePropertiesEquals(expectedQualitytozero, getPersistedQualitytozero(expectedQualitytozero));
    }
}

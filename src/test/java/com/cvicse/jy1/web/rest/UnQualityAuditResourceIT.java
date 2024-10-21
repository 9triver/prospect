package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.UnQualityAuditAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.UnQualityAudit;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.repository.UnQualityAuditRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link UnQualityAuditResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UnQualityAuditResourceIT {

    private static final String DEFAULT_WORKBAGID = "AAAAAAAAAA";
    private static final String UPDATED_WORKBAGID = "BBBBBBBBBB";

    private static final String DEFAULT_BELONGWBSID = "AAAAAAAAAA";
    private static final String UPDATED_BELONGWBSID = "BBBBBBBBBB";

    private static final String DEFAULT_OUTSOURCINGCONTRACTID = "AAAAAAAAAA";
    private static final String UPDATED_OUTSOURCINGCONTRACTID = "BBBBBBBBBB";

    private static final String DEFAULT_UNQUALITYID = "AAAAAAAAAA";
    private static final String UPDATED_UNQUALITYID = "BBBBBBBBBB";

    private static final String DEFAULT_UNQUALITYNAME = "AAAAAAAAAA";
    private static final String UPDATED_UNQUALITYNAME = "BBBBBBBBBB";

    private static final String DEFAULT_UNQUALITYUNIT = "AAAAAAAAAA";
    private static final String UPDATED_UNQUALITYUNIT = "BBBBBBBBBB";

    private static final String DEFAULT_UNQUALITYTRIALGROUP = "AAAAAAAAAA";
    private static final String UPDATED_UNQUALITYTRIALGROUP = "BBBBBBBBBB";

    private static final String DEFAULT_INSPECTOR = "AAAAAAAAAA";
    private static final String UPDATED_INSPECTOR = "BBBBBBBBBB";

    private static final String DEFAULT_UNQUALITYSTAGE = "AAAAAAAAAA";
    private static final String UPDATED_UNQUALITYSTAGE = "BBBBBBBBBB";

    private static final Integer DEFAULT_UNQUALITYNUMBER = 1;
    private static final Integer UPDATED_UNQUALITYNUMBER = 2;

    private static final String DEFAULT_UNQUALITYINTRODUCTION = "AAAAAAAAAA";
    private static final String UPDATED_UNQUALITYINTRODUCTION = "BBBBBBBBBB";

    private static final String DEFAULT_UNQUALITYCATEGORY = "AAAAAAAAAA";
    private static final String UPDATED_UNQUALITYCATEGORY = "BBBBBBBBBB";

    private static final String DEFAULT_HANDLINGOPINION = "AAAAAAAAAA";
    private static final String UPDATED_HANDLINGOPINION = "BBBBBBBBBB";

    private static final String DEFAULT_APPLICANT = "AAAAAAAAAA";
    private static final String UPDATED_APPLICANT = "BBBBBBBBBB";

    private static final String DEFAULT_APPLICATIONDATE = "AAAAAAAAAA";
    private static final String UPDATED_APPLICATIONDATE = "BBBBBBBBBB";

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.NOT_AUDITED;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.IN_AUDIT;

    private static final String DEFAULT_ATTACHMENT = "AAAAAAAAAA";
    private static final String UPDATED_ATTACHMENT = "BBBBBBBBBB";

    private static final String DEFAULT_DISPOSALMETHOD = "AAAAAAAAAA";
    private static final String UPDATED_DISPOSALMETHOD = "BBBBBBBBBB";

    private static final String DEFAULT_CAUSEANALYSIS = "AAAAAAAAAA";
    private static final String UPDATED_CAUSEANALYSIS = "BBBBBBBBBB";

    private static final String DEFAULT_CORRECTIVEMEASURES = "AAAAAAAAAA";
    private static final String UPDATED_CORRECTIVEMEASURES = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/un-quality-audits";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UnQualityAuditRepository unQualityAuditRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUnQualityAuditMockMvc;

    private UnQualityAudit unQualityAudit;

    private UnQualityAudit insertedUnQualityAudit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UnQualityAudit createEntity(EntityManager em) {
        UnQualityAudit unQualityAudit = new UnQualityAudit()
            .workbagid(DEFAULT_WORKBAGID)
            .belongwbsid(DEFAULT_BELONGWBSID)
            .outsourcingcontractid(DEFAULT_OUTSOURCINGCONTRACTID)
            .unqualityid(DEFAULT_UNQUALITYID)
            .unqualityname(DEFAULT_UNQUALITYNAME)
            .unqualityunit(DEFAULT_UNQUALITYUNIT)
            .unqualitytrialgroup(DEFAULT_UNQUALITYTRIALGROUP)
            .inspector(DEFAULT_INSPECTOR)
            .unqualitystage(DEFAULT_UNQUALITYSTAGE)
            .unqualitynumber(DEFAULT_UNQUALITYNUMBER)
            .unqualityintroduction(DEFAULT_UNQUALITYINTRODUCTION)
            .unqualitycategory(DEFAULT_UNQUALITYCATEGORY)
            .handlingopinion(DEFAULT_HANDLINGOPINION)
            .applicant(DEFAULT_APPLICANT)
            .applicationdate(DEFAULT_APPLICATIONDATE)
            .auditStatus(DEFAULT_AUDIT_STATUS)
            .attachment(DEFAULT_ATTACHMENT)
            .disposalmethod(DEFAULT_DISPOSALMETHOD)
            .causeanalysis(DEFAULT_CAUSEANALYSIS)
            .correctivemeasures(DEFAULT_CORRECTIVEMEASURES)
            .remarks(DEFAULT_REMARKS);
        return unQualityAudit;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UnQualityAudit createUpdatedEntity(EntityManager em) {
        UnQualityAudit unQualityAudit = new UnQualityAudit()
            .workbagid(UPDATED_WORKBAGID)
            .belongwbsid(UPDATED_BELONGWBSID)
            .outsourcingcontractid(UPDATED_OUTSOURCINGCONTRACTID)
            .unqualityid(UPDATED_UNQUALITYID)
            .unqualityname(UPDATED_UNQUALITYNAME)
            .unqualityunit(UPDATED_UNQUALITYUNIT)
            .unqualitytrialgroup(UPDATED_UNQUALITYTRIALGROUP)
            .inspector(UPDATED_INSPECTOR)
            .unqualitystage(UPDATED_UNQUALITYSTAGE)
            .unqualitynumber(UPDATED_UNQUALITYNUMBER)
            .unqualityintroduction(UPDATED_UNQUALITYINTRODUCTION)
            .unqualitycategory(UPDATED_UNQUALITYCATEGORY)
            .handlingopinion(UPDATED_HANDLINGOPINION)
            .applicant(UPDATED_APPLICANT)
            .applicationdate(UPDATED_APPLICATIONDATE)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .attachment(UPDATED_ATTACHMENT)
            .disposalmethod(UPDATED_DISPOSALMETHOD)
            .causeanalysis(UPDATED_CAUSEANALYSIS)
            .correctivemeasures(UPDATED_CORRECTIVEMEASURES)
            .remarks(UPDATED_REMARKS);
        return unQualityAudit;
    }

    @BeforeEach
    public void initTest() {
        unQualityAudit = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedUnQualityAudit != null) {
            unQualityAuditRepository.delete(insertedUnQualityAudit);
            insertedUnQualityAudit = null;
        }
    }

    @Test
    @Transactional
    void createUnQualityAudit() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the UnQualityAudit
        var returnedUnQualityAudit = om.readValue(
            restUnQualityAuditMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(unQualityAudit)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            UnQualityAudit.class
        );

        // Validate the UnQualityAudit in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertUnQualityAuditUpdatableFieldsEquals(returnedUnQualityAudit, getPersistedUnQualityAudit(returnedUnQualityAudit));

        insertedUnQualityAudit = returnedUnQualityAudit;
    }

    @Test
    @Transactional
    void createUnQualityAuditWithExistingId() throws Exception {
        // Create the UnQualityAudit with an existing ID
        unQualityAudit.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUnQualityAuditMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(unQualityAudit)))
            .andExpect(status().isBadRequest());

        // Validate the UnQualityAudit in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUnQualityAudits() throws Exception {
        // Initialize the database
        insertedUnQualityAudit = unQualityAuditRepository.saveAndFlush(unQualityAudit);

        // Get all the unQualityAuditList
        restUnQualityAuditMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unQualityAudit.getId().intValue())))
            .andExpect(jsonPath("$.[*].workbagid").value(hasItem(DEFAULT_WORKBAGID)))
            .andExpect(jsonPath("$.[*].belongwbsid").value(hasItem(DEFAULT_BELONGWBSID)))
            .andExpect(jsonPath("$.[*].outsourcingcontractid").value(hasItem(DEFAULT_OUTSOURCINGCONTRACTID)))
            .andExpect(jsonPath("$.[*].unqualityid").value(hasItem(DEFAULT_UNQUALITYID)))
            .andExpect(jsonPath("$.[*].unqualityname").value(hasItem(DEFAULT_UNQUALITYNAME)))
            .andExpect(jsonPath("$.[*].unqualityunit").value(hasItem(DEFAULT_UNQUALITYUNIT)))
            .andExpect(jsonPath("$.[*].unqualitytrialgroup").value(hasItem(DEFAULT_UNQUALITYTRIALGROUP)))
            .andExpect(jsonPath("$.[*].inspector").value(hasItem(DEFAULT_INSPECTOR)))
            .andExpect(jsonPath("$.[*].unqualitystage").value(hasItem(DEFAULT_UNQUALITYSTAGE)))
            .andExpect(jsonPath("$.[*].unqualitynumber").value(hasItem(DEFAULT_UNQUALITYNUMBER)))
            .andExpect(jsonPath("$.[*].unqualityintroduction").value(hasItem(DEFAULT_UNQUALITYINTRODUCTION)))
            .andExpect(jsonPath("$.[*].unqualitycategory").value(hasItem(DEFAULT_UNQUALITYCATEGORY)))
            .andExpect(jsonPath("$.[*].handlingopinion").value(hasItem(DEFAULT_HANDLINGOPINION)))
            .andExpect(jsonPath("$.[*].applicant").value(hasItem(DEFAULT_APPLICANT)))
            .andExpect(jsonPath("$.[*].applicationdate").value(hasItem(DEFAULT_APPLICATIONDATE)))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].attachment").value(hasItem(DEFAULT_ATTACHMENT)))
            .andExpect(jsonPath("$.[*].disposalmethod").value(hasItem(DEFAULT_DISPOSALMETHOD)))
            .andExpect(jsonPath("$.[*].causeanalysis").value(hasItem(DEFAULT_CAUSEANALYSIS)))
            .andExpect(jsonPath("$.[*].correctivemeasures").value(hasItem(DEFAULT_CORRECTIVEMEASURES)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)));
    }

    @Test
    @Transactional
    void getUnQualityAudit() throws Exception {
        // Initialize the database
        insertedUnQualityAudit = unQualityAuditRepository.saveAndFlush(unQualityAudit);

        // Get the unQualityAudit
        restUnQualityAuditMockMvc
            .perform(get(ENTITY_API_URL_ID, unQualityAudit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(unQualityAudit.getId().intValue()))
            .andExpect(jsonPath("$.workbagid").value(DEFAULT_WORKBAGID))
            .andExpect(jsonPath("$.belongwbsid").value(DEFAULT_BELONGWBSID))
            .andExpect(jsonPath("$.outsourcingcontractid").value(DEFAULT_OUTSOURCINGCONTRACTID))
            .andExpect(jsonPath("$.unqualityid").value(DEFAULT_UNQUALITYID))
            .andExpect(jsonPath("$.unqualityname").value(DEFAULT_UNQUALITYNAME))
            .andExpect(jsonPath("$.unqualityunit").value(DEFAULT_UNQUALITYUNIT))
            .andExpect(jsonPath("$.unqualitytrialgroup").value(DEFAULT_UNQUALITYTRIALGROUP))
            .andExpect(jsonPath("$.inspector").value(DEFAULT_INSPECTOR))
            .andExpect(jsonPath("$.unqualitystage").value(DEFAULT_UNQUALITYSTAGE))
            .andExpect(jsonPath("$.unqualitynumber").value(DEFAULT_UNQUALITYNUMBER))
            .andExpect(jsonPath("$.unqualityintroduction").value(DEFAULT_UNQUALITYINTRODUCTION))
            .andExpect(jsonPath("$.unqualitycategory").value(DEFAULT_UNQUALITYCATEGORY))
            .andExpect(jsonPath("$.handlingopinion").value(DEFAULT_HANDLINGOPINION))
            .andExpect(jsonPath("$.applicant").value(DEFAULT_APPLICANT))
            .andExpect(jsonPath("$.applicationdate").value(DEFAULT_APPLICATIONDATE))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()))
            .andExpect(jsonPath("$.attachment").value(DEFAULT_ATTACHMENT))
            .andExpect(jsonPath("$.disposalmethod").value(DEFAULT_DISPOSALMETHOD))
            .andExpect(jsonPath("$.causeanalysis").value(DEFAULT_CAUSEANALYSIS))
            .andExpect(jsonPath("$.correctivemeasures").value(DEFAULT_CORRECTIVEMEASURES))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS));
    }

    @Test
    @Transactional
    void getNonExistingUnQualityAudit() throws Exception {
        // Get the unQualityAudit
        restUnQualityAuditMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingUnQualityAudit() throws Exception {
        // Initialize the database
        insertedUnQualityAudit = unQualityAuditRepository.saveAndFlush(unQualityAudit);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the unQualityAudit
        UnQualityAudit updatedUnQualityAudit = unQualityAuditRepository.findById(unQualityAudit.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedUnQualityAudit are not directly saved in db
        em.detach(updatedUnQualityAudit);
        updatedUnQualityAudit
            .workbagid(UPDATED_WORKBAGID)
            .belongwbsid(UPDATED_BELONGWBSID)
            .outsourcingcontractid(UPDATED_OUTSOURCINGCONTRACTID)
            .unqualityid(UPDATED_UNQUALITYID)
            .unqualityname(UPDATED_UNQUALITYNAME)
            .unqualityunit(UPDATED_UNQUALITYUNIT)
            .unqualitytrialgroup(UPDATED_UNQUALITYTRIALGROUP)
            .inspector(UPDATED_INSPECTOR)
            .unqualitystage(UPDATED_UNQUALITYSTAGE)
            .unqualitynumber(UPDATED_UNQUALITYNUMBER)
            .unqualityintroduction(UPDATED_UNQUALITYINTRODUCTION)
            .unqualitycategory(UPDATED_UNQUALITYCATEGORY)
            .handlingopinion(UPDATED_HANDLINGOPINION)
            .applicant(UPDATED_APPLICANT)
            .applicationdate(UPDATED_APPLICATIONDATE)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .attachment(UPDATED_ATTACHMENT)
            .disposalmethod(UPDATED_DISPOSALMETHOD)
            .causeanalysis(UPDATED_CAUSEANALYSIS)
            .correctivemeasures(UPDATED_CORRECTIVEMEASURES)
            .remarks(UPDATED_REMARKS);

        restUnQualityAuditMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUnQualityAudit.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedUnQualityAudit))
            )
            .andExpect(status().isOk());

        // Validate the UnQualityAudit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedUnQualityAuditToMatchAllProperties(updatedUnQualityAudit);
    }

    @Test
    @Transactional
    void putNonExistingUnQualityAudit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        unQualityAudit.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUnQualityAuditMockMvc
            .perform(
                put(ENTITY_API_URL_ID, unQualityAudit.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(unQualityAudit))
            )
            .andExpect(status().isBadRequest());

        // Validate the UnQualityAudit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUnQualityAudit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        unQualityAudit.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnQualityAuditMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(unQualityAudit))
            )
            .andExpect(status().isBadRequest());

        // Validate the UnQualityAudit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUnQualityAudit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        unQualityAudit.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnQualityAuditMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(unQualityAudit)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UnQualityAudit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUnQualityAuditWithPatch() throws Exception {
        // Initialize the database
        insertedUnQualityAudit = unQualityAuditRepository.saveAndFlush(unQualityAudit);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the unQualityAudit using partial update
        UnQualityAudit partialUpdatedUnQualityAudit = new UnQualityAudit();
        partialUpdatedUnQualityAudit.setId(unQualityAudit.getId());

        partialUpdatedUnQualityAudit
            .unqualityid(UPDATED_UNQUALITYID)
            .unqualityname(UPDATED_UNQUALITYNAME)
            .unqualitynumber(UPDATED_UNQUALITYNUMBER)
            .unqualityintroduction(UPDATED_UNQUALITYINTRODUCTION)
            .handlingopinion(UPDATED_HANDLINGOPINION)
            .applicant(UPDATED_APPLICANT)
            .applicationdate(UPDATED_APPLICATIONDATE)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .disposalmethod(UPDATED_DISPOSALMETHOD)
            .causeanalysis(UPDATED_CAUSEANALYSIS)
            .correctivemeasures(UPDATED_CORRECTIVEMEASURES)
            .remarks(UPDATED_REMARKS);

        restUnQualityAuditMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUnQualityAudit.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedUnQualityAudit))
            )
            .andExpect(status().isOk());

        // Validate the UnQualityAudit in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertUnQualityAuditUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedUnQualityAudit, unQualityAudit),
            getPersistedUnQualityAudit(unQualityAudit)
        );
    }

    @Test
    @Transactional
    void fullUpdateUnQualityAuditWithPatch() throws Exception {
        // Initialize the database
        insertedUnQualityAudit = unQualityAuditRepository.saveAndFlush(unQualityAudit);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the unQualityAudit using partial update
        UnQualityAudit partialUpdatedUnQualityAudit = new UnQualityAudit();
        partialUpdatedUnQualityAudit.setId(unQualityAudit.getId());

        partialUpdatedUnQualityAudit
            .workbagid(UPDATED_WORKBAGID)
            .belongwbsid(UPDATED_BELONGWBSID)
            .outsourcingcontractid(UPDATED_OUTSOURCINGCONTRACTID)
            .unqualityid(UPDATED_UNQUALITYID)
            .unqualityname(UPDATED_UNQUALITYNAME)
            .unqualityunit(UPDATED_UNQUALITYUNIT)
            .unqualitytrialgroup(UPDATED_UNQUALITYTRIALGROUP)
            .inspector(UPDATED_INSPECTOR)
            .unqualitystage(UPDATED_UNQUALITYSTAGE)
            .unqualitynumber(UPDATED_UNQUALITYNUMBER)
            .unqualityintroduction(UPDATED_UNQUALITYINTRODUCTION)
            .unqualitycategory(UPDATED_UNQUALITYCATEGORY)
            .handlingopinion(UPDATED_HANDLINGOPINION)
            .applicant(UPDATED_APPLICANT)
            .applicationdate(UPDATED_APPLICATIONDATE)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .attachment(UPDATED_ATTACHMENT)
            .disposalmethod(UPDATED_DISPOSALMETHOD)
            .causeanalysis(UPDATED_CAUSEANALYSIS)
            .correctivemeasures(UPDATED_CORRECTIVEMEASURES)
            .remarks(UPDATED_REMARKS);

        restUnQualityAuditMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUnQualityAudit.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedUnQualityAudit))
            )
            .andExpect(status().isOk());

        // Validate the UnQualityAudit in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertUnQualityAuditUpdatableFieldsEquals(partialUpdatedUnQualityAudit, getPersistedUnQualityAudit(partialUpdatedUnQualityAudit));
    }

    @Test
    @Transactional
    void patchNonExistingUnQualityAudit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        unQualityAudit.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUnQualityAuditMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, unQualityAudit.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(unQualityAudit))
            )
            .andExpect(status().isBadRequest());

        // Validate the UnQualityAudit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUnQualityAudit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        unQualityAudit.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnQualityAuditMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(unQualityAudit))
            )
            .andExpect(status().isBadRequest());

        // Validate the UnQualityAudit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUnQualityAudit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        unQualityAudit.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnQualityAuditMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(unQualityAudit)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UnQualityAudit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUnQualityAudit() throws Exception {
        // Initialize the database
        insertedUnQualityAudit = unQualityAuditRepository.saveAndFlush(unQualityAudit);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the unQualityAudit
        restUnQualityAuditMockMvc
            .perform(delete(ENTITY_API_URL_ID, unQualityAudit.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return unQualityAuditRepository.count();
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

    protected UnQualityAudit getPersistedUnQualityAudit(UnQualityAudit unQualityAudit) {
        return unQualityAuditRepository.findById(unQualityAudit.getId()).orElseThrow();
    }

    protected void assertPersistedUnQualityAuditToMatchAllProperties(UnQualityAudit expectedUnQualityAudit) {
        assertUnQualityAuditAllPropertiesEquals(expectedUnQualityAudit, getPersistedUnQualityAudit(expectedUnQualityAudit));
    }

    protected void assertPersistedUnQualityAuditToMatchUpdatableProperties(UnQualityAudit expectedUnQualityAudit) {
        assertUnQualityAuditAllUpdatablePropertiesEquals(expectedUnQualityAudit, getPersistedUnQualityAudit(expectedUnQualityAudit));
    }
}

package com.cvicse.web.rest;

import static com.cvicse.domain.ProgressmanagementAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Progressmanagement;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Progressstatus;
import com.cvicse.domain.enumeration.Progresstype;
import com.cvicse.repository.ProgressmanagementRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ProgressmanagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProgressmanagementResourceIT {

    private static final Long DEFAULT_PROGRESSID = 1L;
    private static final Long UPDATED_PROGRESSID = 2L;

    private static final String DEFAULT_PROGRESSNAME = "AAAAAAAAAA";
    private static final String UPDATED_PROGRESSNAME = "BBBBBBBBBB";

    private static final Progresstype DEFAULT_PROGRESSTYPE = Progresstype.YEAR;
    private static final Progresstype UPDATED_PROGRESSTYPE = Progresstype.MONTH;

    private static final String DEFAULT_WORKFOCUS = "AAAAAAAAAA";
    private static final String UPDATED_WORKFOCUS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATETIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATORNAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATORNAME = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSIBLENAME = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSIBLENAME = "BBBBBBBBBB";

    private static final Progressstatus DEFAULT_STATUS = Progressstatus.Not_start;
    private static final Progressstatus UPDATED_STATUS = Progressstatus.Start;

    private static final Long DEFAULT_BASELINEID = 1L;
    private static final Long UPDATED_BASELINEID = 2L;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/progressmanagements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProgressmanagementRepository progressmanagementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProgressmanagementMockMvc;

    private Progressmanagement progressmanagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Progressmanagement createEntity(EntityManager em) {
        Progressmanagement progressmanagement = new Progressmanagement()
            .progressid(DEFAULT_PROGRESSID)
            .progressname(DEFAULT_PROGRESSNAME)
            .progresstype(DEFAULT_PROGRESSTYPE)
            .workfocus(DEFAULT_WORKFOCUS)
            .createtime(DEFAULT_CREATETIME)
            .creatorname(DEFAULT_CREATORNAME)
            .responsiblename(DEFAULT_RESPONSIBLENAME)
            .status(DEFAULT_STATUS)
            .baselineid(DEFAULT_BASELINEID)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return progressmanagement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Progressmanagement createUpdatedEntity(EntityManager em) {
        Progressmanagement progressmanagement = new Progressmanagement()
            .progressid(UPDATED_PROGRESSID)
            .progressname(UPDATED_PROGRESSNAME)
            .progresstype(UPDATED_PROGRESSTYPE)
            .workfocus(UPDATED_WORKFOCUS)
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .status(UPDATED_STATUS)
            .baselineid(UPDATED_BASELINEID)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return progressmanagement;
    }

    @BeforeEach
    public void initTest() {
        progressmanagement = createEntity(em);
    }

    @Test
    @Transactional
    void createProgressmanagement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Progressmanagement
        var returnedProgressmanagement = om.readValue(
            restProgressmanagementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(progressmanagement)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Progressmanagement.class
        );

        // Validate the Progressmanagement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertProgressmanagementUpdatableFieldsEquals(
            returnedProgressmanagement,
            getPersistedProgressmanagement(returnedProgressmanagement)
        );
    }

    @Test
    @Transactional
    void createProgressmanagementWithExistingId() throws Exception {
        // Create the Progressmanagement with an existing ID
        progressmanagement.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProgressmanagementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(progressmanagement)))
            .andExpect(status().isBadRequest());

        // Validate the Progressmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProgressmanagements() throws Exception {
        // Initialize the database
        progressmanagementRepository.saveAndFlush(progressmanagement);

        // Get all the progressmanagementList
        restProgressmanagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(progressmanagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].progressid").value(hasItem(DEFAULT_PROGRESSID.intValue())))
            .andExpect(jsonPath("$.[*].progressname").value(hasItem(DEFAULT_PROGRESSNAME)))
            .andExpect(jsonPath("$.[*].progresstype").value(hasItem(DEFAULT_PROGRESSTYPE.toString())))
            .andExpect(jsonPath("$.[*].workfocus").value(hasItem(DEFAULT_WORKFOCUS)))
            .andExpect(jsonPath("$.[*].createtime").value(hasItem(DEFAULT_CREATETIME.toString())))
            .andExpect(jsonPath("$.[*].creatorname").value(hasItem(DEFAULT_CREATORNAME)))
            .andExpect(jsonPath("$.[*].responsiblename").value(hasItem(DEFAULT_RESPONSIBLENAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].baselineid").value(hasItem(DEFAULT_BASELINEID.intValue())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getProgressmanagement() throws Exception {
        // Initialize the database
        progressmanagementRepository.saveAndFlush(progressmanagement);

        // Get the progressmanagement
        restProgressmanagementMockMvc
            .perform(get(ENTITY_API_URL_ID, progressmanagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(progressmanagement.getId().intValue()))
            .andExpect(jsonPath("$.progressid").value(DEFAULT_PROGRESSID.intValue()))
            .andExpect(jsonPath("$.progressname").value(DEFAULT_PROGRESSNAME))
            .andExpect(jsonPath("$.progresstype").value(DEFAULT_PROGRESSTYPE.toString()))
            .andExpect(jsonPath("$.workfocus").value(DEFAULT_WORKFOCUS))
            .andExpect(jsonPath("$.createtime").value(DEFAULT_CREATETIME.toString()))
            .andExpect(jsonPath("$.creatorname").value(DEFAULT_CREATORNAME))
            .andExpect(jsonPath("$.responsiblename").value(DEFAULT_RESPONSIBLENAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.baselineid").value(DEFAULT_BASELINEID.intValue()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingProgressmanagement() throws Exception {
        // Get the progressmanagement
        restProgressmanagementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProgressmanagement() throws Exception {
        // Initialize the database
        progressmanagementRepository.saveAndFlush(progressmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the progressmanagement
        Progressmanagement updatedProgressmanagement = progressmanagementRepository.findById(progressmanagement.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProgressmanagement are not directly saved in db
        em.detach(updatedProgressmanagement);
        updatedProgressmanagement
            .progressid(UPDATED_PROGRESSID)
            .progressname(UPDATED_PROGRESSNAME)
            .progresstype(UPDATED_PROGRESSTYPE)
            .workfocus(UPDATED_WORKFOCUS)
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .status(UPDATED_STATUS)
            .baselineid(UPDATED_BASELINEID)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restProgressmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProgressmanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedProgressmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Progressmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProgressmanagementToMatchAllProperties(updatedProgressmanagement);
    }

    @Test
    @Transactional
    void putNonExistingProgressmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressmanagement.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgressmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, progressmanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(progressmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progressmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProgressmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressmanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(progressmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progressmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProgressmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressmanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressmanagementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(progressmanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Progressmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProgressmanagementWithPatch() throws Exception {
        // Initialize the database
        progressmanagementRepository.saveAndFlush(progressmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the progressmanagement using partial update
        Progressmanagement partialUpdatedProgressmanagement = new Progressmanagement();
        partialUpdatedProgressmanagement.setId(progressmanagement.getId());

        partialUpdatedProgressmanagement.progressid(UPDATED_PROGRESSID).progressname(UPDATED_PROGRESSNAME);

        restProgressmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProgressmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProgressmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Progressmanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProgressmanagementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProgressmanagement, progressmanagement),
            getPersistedProgressmanagement(progressmanagement)
        );
    }

    @Test
    @Transactional
    void fullUpdateProgressmanagementWithPatch() throws Exception {
        // Initialize the database
        progressmanagementRepository.saveAndFlush(progressmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the progressmanagement using partial update
        Progressmanagement partialUpdatedProgressmanagement = new Progressmanagement();
        partialUpdatedProgressmanagement.setId(progressmanagement.getId());

        partialUpdatedProgressmanagement
            .progressid(UPDATED_PROGRESSID)
            .progressname(UPDATED_PROGRESSNAME)
            .progresstype(UPDATED_PROGRESSTYPE)
            .workfocus(UPDATED_WORKFOCUS)
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .status(UPDATED_STATUS)
            .baselineid(UPDATED_BASELINEID)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restProgressmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProgressmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProgressmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Progressmanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProgressmanagementUpdatableFieldsEquals(
            partialUpdatedProgressmanagement,
            getPersistedProgressmanagement(partialUpdatedProgressmanagement)
        );
    }

    @Test
    @Transactional
    void patchNonExistingProgressmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressmanagement.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgressmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, progressmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(progressmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progressmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProgressmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressmanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(progressmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progressmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProgressmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressmanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressmanagementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(progressmanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Progressmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProgressmanagement() throws Exception {
        // Initialize the database
        progressmanagementRepository.saveAndFlush(progressmanagement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the progressmanagement
        restProgressmanagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, progressmanagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return progressmanagementRepository.count();
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

    protected Progressmanagement getPersistedProgressmanagement(Progressmanagement progressmanagement) {
        return progressmanagementRepository.findById(progressmanagement.getId()).orElseThrow();
    }

    protected void assertPersistedProgressmanagementToMatchAllProperties(Progressmanagement expectedProgressmanagement) {
        assertProgressmanagementAllPropertiesEquals(expectedProgressmanagement, getPersistedProgressmanagement(expectedProgressmanagement));
    }

    protected void assertPersistedProgressmanagementToMatchUpdatableProperties(Progressmanagement expectedProgressmanagement) {
        assertProgressmanagementAllUpdatablePropertiesEquals(
            expectedProgressmanagement,
            getPersistedProgressmanagement(expectedProgressmanagement)
        );
    }
}

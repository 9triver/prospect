package com.cvicse.web.rest;

import static com.cvicse.domain.ProgressplanAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Progressplan;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Progressstatus;
import com.cvicse.domain.enumeration.Progresstype;
import com.cvicse.repository.ProgressplanRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ProgressplanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProgressplanResourceIT {

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

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/progressplans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProgressplanRepository progressplanRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProgressplanMockMvc;

    private Progressplan progressplan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Progressplan createEntity(EntityManager em) {
        Progressplan progressplan = new Progressplan()
            .progressname(DEFAULT_PROGRESSNAME)
            .progresstype(DEFAULT_PROGRESSTYPE)
            .workfocus(DEFAULT_WORKFOCUS)
            .createtime(DEFAULT_CREATETIME)
            .creatorname(DEFAULT_CREATORNAME)
            .responsiblename(DEFAULT_RESPONSIBLENAME)
            .status(DEFAULT_STATUS)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return progressplan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Progressplan createUpdatedEntity(EntityManager em) {
        Progressplan progressplan = new Progressplan()
            .progressname(UPDATED_PROGRESSNAME)
            .progresstype(UPDATED_PROGRESSTYPE)
            .workfocus(UPDATED_WORKFOCUS)
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return progressplan;
    }

    @BeforeEach
    public void initTest() {
        progressplan = createEntity(em);
    }

    @Test
    @Transactional
    void createProgressplan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Progressplan
        var returnedProgressplan = om.readValue(
            restProgressplanMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(progressplan)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Progressplan.class
        );

        // Validate the Progressplan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertProgressplanUpdatableFieldsEquals(returnedProgressplan, getPersistedProgressplan(returnedProgressplan));
    }

    @Test
    @Transactional
    void createProgressplanWithExistingId() throws Exception {
        // Create the Progressplan with an existing ID
        progressplan.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProgressplanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(progressplan)))
            .andExpect(status().isBadRequest());

        // Validate the Progressplan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProgressplans() throws Exception {
        // Initialize the database
        progressplanRepository.saveAndFlush(progressplan);

        // Get all the progressplanList
        restProgressplanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(progressplan.getId())))
            .andExpect(jsonPath("$.[*].progressname").value(hasItem(DEFAULT_PROGRESSNAME)))
            .andExpect(jsonPath("$.[*].progresstype").value(hasItem(DEFAULT_PROGRESSTYPE.toString())))
            .andExpect(jsonPath("$.[*].workfocus").value(hasItem(DEFAULT_WORKFOCUS)))
            .andExpect(jsonPath("$.[*].createtime").value(hasItem(DEFAULT_CREATETIME.toString())))
            .andExpect(jsonPath("$.[*].creatorname").value(hasItem(DEFAULT_CREATORNAME)))
            .andExpect(jsonPath("$.[*].responsiblename").value(hasItem(DEFAULT_RESPONSIBLENAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getProgressplan() throws Exception {
        // Initialize the database
        progressplanRepository.saveAndFlush(progressplan);

        // Get the progressplan
        restProgressplanMockMvc
            .perform(get(ENTITY_API_URL_ID, progressplan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(progressplan.getId()))
            .andExpect(jsonPath("$.progressname").value(DEFAULT_PROGRESSNAME))
            .andExpect(jsonPath("$.progresstype").value(DEFAULT_PROGRESSTYPE.toString()))
            .andExpect(jsonPath("$.workfocus").value(DEFAULT_WORKFOCUS))
            .andExpect(jsonPath("$.createtime").value(DEFAULT_CREATETIME.toString()))
            .andExpect(jsonPath("$.creatorname").value(DEFAULT_CREATORNAME))
            .andExpect(jsonPath("$.responsiblename").value(DEFAULT_RESPONSIBLENAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingProgressplan() throws Exception {
        // Get the progressplan
        restProgressplanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProgressplan() throws Exception {
        // Initialize the database
        progressplanRepository.saveAndFlush(progressplan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the progressplan
        Progressplan updatedProgressplan = progressplanRepository.findById(progressplan.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProgressplan are not directly saved in db
        em.detach(updatedProgressplan);
        updatedProgressplan
            .progressname(UPDATED_PROGRESSNAME)
            .progresstype(UPDATED_PROGRESSTYPE)
            .workfocus(UPDATED_WORKFOCUS)
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restProgressplanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProgressplan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedProgressplan))
            )
            .andExpect(status().isOk());

        // Validate the Progressplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProgressplanToMatchAllProperties(updatedProgressplan);
    }

    @Test
    @Transactional
    void putNonExistingProgressplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressplan.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgressplanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, progressplan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(progressplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progressplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProgressplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressplan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressplanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(progressplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progressplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProgressplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressplan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressplanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(progressplan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Progressplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProgressplanWithPatch() throws Exception {
        // Initialize the database
        progressplanRepository.saveAndFlush(progressplan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the progressplan using partial update
        Progressplan partialUpdatedProgressplan = new Progressplan();
        partialUpdatedProgressplan.setId(progressplan.getId());

        partialUpdatedProgressplan
            .progresstype(UPDATED_PROGRESSTYPE)
            .creatorname(UPDATED_CREATORNAME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restProgressplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProgressplan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProgressplan))
            )
            .andExpect(status().isOk());

        // Validate the Progressplan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProgressplanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProgressplan, progressplan),
            getPersistedProgressplan(progressplan)
        );
    }

    @Test
    @Transactional
    void fullUpdateProgressplanWithPatch() throws Exception {
        // Initialize the database
        progressplanRepository.saveAndFlush(progressplan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the progressplan using partial update
        Progressplan partialUpdatedProgressplan = new Progressplan();
        partialUpdatedProgressplan.setId(progressplan.getId());

        partialUpdatedProgressplan
            .progressname(UPDATED_PROGRESSNAME)
            .progresstype(UPDATED_PROGRESSTYPE)
            .workfocus(UPDATED_WORKFOCUS)
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restProgressplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProgressplan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProgressplan))
            )
            .andExpect(status().isOk());

        // Validate the Progressplan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProgressplanUpdatableFieldsEquals(partialUpdatedProgressplan, getPersistedProgressplan(partialUpdatedProgressplan));
    }

    @Test
    @Transactional
    void patchNonExistingProgressplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressplan.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgressplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, progressplan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(progressplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progressplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProgressplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressplan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(progressplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progressplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProgressplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressplan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressplanMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(progressplan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Progressplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProgressplan() throws Exception {
        // Initialize the database
        progressplanRepository.saveAndFlush(progressplan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the progressplan
        restProgressplanMockMvc
            .perform(delete(ENTITY_API_URL_ID, progressplan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return progressplanRepository.count();
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

    protected Progressplan getPersistedProgressplan(Progressplan progressplan) {
        return progressplanRepository.findById(progressplan.getId()).orElseThrow();
    }

    protected void assertPersistedProgressplanToMatchAllProperties(Progressplan expectedProgressplan) {
        assertProgressplanAllPropertiesEquals(expectedProgressplan, getPersistedProgressplan(expectedProgressplan));
    }

    protected void assertPersistedProgressplanToMatchUpdatableProperties(Progressplan expectedProgressplan) {
        assertProgressplanAllUpdatablePropertiesEquals(expectedProgressplan, getPersistedProgressplan(expectedProgressplan));
    }
}

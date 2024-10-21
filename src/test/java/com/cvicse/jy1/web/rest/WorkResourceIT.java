package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.WorkAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.Work;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.repository.WorkRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.UUID;
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
 * Integration tests for the {@link WorkResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WorkResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.PUBLIC;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.INTERNAL;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_WORKBAGID = "AAAAAAAAAA";
    private static final String UPDATED_WORKBAGID = "BBBBBBBBBB";

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.NOT_AUDITED;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.IN_AUDIT;

    private static final String ENTITY_API_URL = "/api/works";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWorkMockMvc;

    private Work work;

    private Work insertedWork;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Work createEntity(EntityManager em) {
        Work work = new Work()
            .name(DEFAULT_NAME)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .description(DEFAULT_DESCRIPTION)
            .workbagid(DEFAULT_WORKBAGID)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return work;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Work createUpdatedEntity(EntityManager em) {
        Work work = new Work()
            .name(UPDATED_NAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .description(UPDATED_DESCRIPTION)
            .workbagid(UPDATED_WORKBAGID)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return work;
    }

    @BeforeEach
    public void initTest() {
        work = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedWork != null) {
            workRepository.delete(insertedWork);
            insertedWork = null;
        }
    }

    @Test
    @Transactional
    void createWork() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Work
        var returnedWork = om.readValue(
            restWorkMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(work)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Work.class
        );

        // Validate the Work in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertWorkUpdatableFieldsEquals(returnedWork, getPersistedWork(returnedWork));

        insertedWork = returnedWork;
    }

    @Test
    @Transactional
    void createWorkWithExistingId() throws Exception {
        // Create the Work with an existing ID
        work.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWorkMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(work)))
            .andExpect(status().isBadRequest());

        // Validate the Work in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllWorks() throws Exception {
        // Initialize the database
        insertedWork = workRepository.saveAndFlush(work);

        // Get all the workList
        restWorkMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(work.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].workbagid").value(hasItem(DEFAULT_WORKBAGID)))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getWork() throws Exception {
        // Initialize the database
        insertedWork = workRepository.saveAndFlush(work);

        // Get the work
        restWorkMockMvc
            .perform(get(ENTITY_API_URL_ID, work.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(work.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.workbagid").value(DEFAULT_WORKBAGID))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingWork() throws Exception {
        // Get the work
        restWorkMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingWork() throws Exception {
        // Initialize the database
        insertedWork = workRepository.saveAndFlush(work);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the work
        Work updatedWork = workRepository.findById(work.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedWork are not directly saved in db
        em.detach(updatedWork);
        updatedWork
            .name(UPDATED_NAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .description(UPDATED_DESCRIPTION)
            .workbagid(UPDATED_WORKBAGID)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restWorkMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedWork.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedWork))
            )
            .andExpect(status().isOk());

        // Validate the Work in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedWorkToMatchAllProperties(updatedWork);
    }

    @Test
    @Transactional
    void putNonExistingWork() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        work.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkMockMvc
            .perform(put(ENTITY_API_URL_ID, work.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(work)))
            .andExpect(status().isBadRequest());

        // Validate the Work in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWork() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        work.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(work))
            )
            .andExpect(status().isBadRequest());

        // Validate the Work in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWork() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        work.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(work)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Work in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWorkWithPatch() throws Exception {
        // Initialize the database
        insertedWork = workRepository.saveAndFlush(work);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the work using partial update
        Work partialUpdatedWork = new Work();
        partialUpdatedWork.setId(work.getId());

        partialUpdatedWork.secretlevel(UPDATED_SECRETLEVEL).workbagid(UPDATED_WORKBAGID);

        restWorkMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWork.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedWork))
            )
            .andExpect(status().isOk());

        // Validate the Work in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertWorkUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedWork, work), getPersistedWork(work));
    }

    @Test
    @Transactional
    void fullUpdateWorkWithPatch() throws Exception {
        // Initialize the database
        insertedWork = workRepository.saveAndFlush(work);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the work using partial update
        Work partialUpdatedWork = new Work();
        partialUpdatedWork.setId(work.getId());

        partialUpdatedWork
            .name(UPDATED_NAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .description(UPDATED_DESCRIPTION)
            .workbagid(UPDATED_WORKBAGID)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restWorkMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWork.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedWork))
            )
            .andExpect(status().isOk());

        // Validate the Work in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertWorkUpdatableFieldsEquals(partialUpdatedWork, getPersistedWork(partialUpdatedWork));
    }

    @Test
    @Transactional
    void patchNonExistingWork() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        work.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWorkMockMvc
            .perform(patch(ENTITY_API_URL_ID, work.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(work)))
            .andExpect(status().isBadRequest());

        // Validate the Work in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWork() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        work.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(work))
            )
            .andExpect(status().isBadRequest());

        // Validate the Work in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWork() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        work.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWorkMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(work)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Work in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWork() throws Exception {
        // Initialize the database
        insertedWork = workRepository.saveAndFlush(work);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the work
        restWorkMockMvc
            .perform(delete(ENTITY_API_URL_ID, work.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return workRepository.count();
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

    protected Work getPersistedWork(Work work) {
        return workRepository.findById(work.getId()).orElseThrow();
    }

    protected void assertPersistedWorkToMatchAllProperties(Work expectedWork) {
        assertWorkAllPropertiesEquals(expectedWork, getPersistedWork(expectedWork));
    }

    protected void assertPersistedWorkToMatchUpdatableProperties(Work expectedWork) {
        assertWorkAllUpdatablePropertiesEquals(expectedWork, getPersistedWork(expectedWork));
    }
}

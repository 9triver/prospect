package com.cvicse.web.rest;

import static com.cvicse.domain.ProgressmanagementAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Progressmanagement;
import com.cvicse.repository.ProgressmanagementRepository;
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
 * Integration tests for the {@link ProgressmanagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProgressmanagementResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/progressmanagements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

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
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME);
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
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);
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
        progressmanagement.setId("existing_id");

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
            .andExpect(jsonPath("$.[*].id").value(hasItem(progressmanagement.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())));
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
            .andExpect(jsonPath("$.id").value(progressmanagement.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()));
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
        updatedProgressmanagement.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).starttime(UPDATED_STARTTIME).endtime(UPDATED_ENDTIME);

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
        progressmanagement.setId(UUID.randomUUID().toString());

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
        progressmanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
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
        progressmanagement.setId(UUID.randomUUID().toString());

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

        partialUpdatedProgressmanagement.description(UPDATED_DESCRIPTION).starttime(UPDATED_STARTTIME).endtime(UPDATED_ENDTIME);

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
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);

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
        progressmanagement.setId(UUID.randomUUID().toString());

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
        progressmanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
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
        progressmanagement.setId(UUID.randomUUID().toString());

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

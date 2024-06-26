package com.cvicse.web.rest;

import static com.cvicse.domain.ProgressplanreturnsAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Progressplanreturns;
import com.cvicse.repository.ProgressplanreturnsRepository;
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
 * Integration tests for the {@link ProgressplanreturnsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProgressplanreturnsResourceIT {

    private static final LocalDate DEFAULT_PLANSTARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PLANSTARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PLANENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PLANENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_RETURNSTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RETURNSTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/progressplanreturns";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProgressplanreturnsRepository progressplanreturnsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProgressplanreturnsMockMvc;

    private Progressplanreturns progressplanreturns;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Progressplanreturns createEntity(EntityManager em) {
        Progressplanreturns progressplanreturns = new Progressplanreturns()
            .planstarttime(DEFAULT_PLANSTARTTIME)
            .planendtime(DEFAULT_PLANENDTIME)
            .returnstime(DEFAULT_RETURNSTIME);
        return progressplanreturns;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Progressplanreturns createUpdatedEntity(EntityManager em) {
        Progressplanreturns progressplanreturns = new Progressplanreturns()
            .planstarttime(UPDATED_PLANSTARTTIME)
            .planendtime(UPDATED_PLANENDTIME)
            .returnstime(UPDATED_RETURNSTIME);
        return progressplanreturns;
    }

    @BeforeEach
    public void initTest() {
        progressplanreturns = createEntity(em);
    }

    @Test
    @Transactional
    void createProgressplanreturns() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Progressplanreturns
        var returnedProgressplanreturns = om.readValue(
            restProgressplanreturnsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(progressplanreturns)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Progressplanreturns.class
        );

        // Validate the Progressplanreturns in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertProgressplanreturnsUpdatableFieldsEquals(
            returnedProgressplanreturns,
            getPersistedProgressplanreturns(returnedProgressplanreturns)
        );
    }

    @Test
    @Transactional
    void createProgressplanreturnsWithExistingId() throws Exception {
        // Create the Progressplanreturns with an existing ID
        progressplanreturns.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProgressplanreturnsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(progressplanreturns)))
            .andExpect(status().isBadRequest());

        // Validate the Progressplanreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProgressplanreturns() throws Exception {
        // Initialize the database
        progressplanreturnsRepository.saveAndFlush(progressplanreturns);

        // Get all the progressplanreturnsList
        restProgressplanreturnsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(progressplanreturns.getId())))
            .andExpect(jsonPath("$.[*].planstarttime").value(hasItem(DEFAULT_PLANSTARTTIME.toString())))
            .andExpect(jsonPath("$.[*].planendtime").value(hasItem(DEFAULT_PLANENDTIME.toString())))
            .andExpect(jsonPath("$.[*].returnstime").value(hasItem(DEFAULT_RETURNSTIME.toString())));
    }

    @Test
    @Transactional
    void getProgressplanreturns() throws Exception {
        // Initialize the database
        progressplanreturnsRepository.saveAndFlush(progressplanreturns);

        // Get the progressplanreturns
        restProgressplanreturnsMockMvc
            .perform(get(ENTITY_API_URL_ID, progressplanreturns.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(progressplanreturns.getId()))
            .andExpect(jsonPath("$.planstarttime").value(DEFAULT_PLANSTARTTIME.toString()))
            .andExpect(jsonPath("$.planendtime").value(DEFAULT_PLANENDTIME.toString()))
            .andExpect(jsonPath("$.returnstime").value(DEFAULT_RETURNSTIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingProgressplanreturns() throws Exception {
        // Get the progressplanreturns
        restProgressplanreturnsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProgressplanreturns() throws Exception {
        // Initialize the database
        progressplanreturnsRepository.saveAndFlush(progressplanreturns);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the progressplanreturns
        Progressplanreturns updatedProgressplanreturns = progressplanreturnsRepository.findById(progressplanreturns.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProgressplanreturns are not directly saved in db
        em.detach(updatedProgressplanreturns);
        updatedProgressplanreturns.planstarttime(UPDATED_PLANSTARTTIME).planendtime(UPDATED_PLANENDTIME).returnstime(UPDATED_RETURNSTIME);

        restProgressplanreturnsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProgressplanreturns.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedProgressplanreturns))
            )
            .andExpect(status().isOk());

        // Validate the Progressplanreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProgressplanreturnsToMatchAllProperties(updatedProgressplanreturns);
    }

    @Test
    @Transactional
    void putNonExistingProgressplanreturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressplanreturns.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgressplanreturnsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, progressplanreturns.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(progressplanreturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progressplanreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProgressplanreturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressplanreturns.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressplanreturnsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(progressplanreturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progressplanreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProgressplanreturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressplanreturns.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressplanreturnsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(progressplanreturns)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Progressplanreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProgressplanreturnsWithPatch() throws Exception {
        // Initialize the database
        progressplanreturnsRepository.saveAndFlush(progressplanreturns);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the progressplanreturns using partial update
        Progressplanreturns partialUpdatedProgressplanreturns = new Progressplanreturns();
        partialUpdatedProgressplanreturns.setId(progressplanreturns.getId());

        partialUpdatedProgressplanreturns.planstarttime(UPDATED_PLANSTARTTIME).returnstime(UPDATED_RETURNSTIME);

        restProgressplanreturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProgressplanreturns.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProgressplanreturns))
            )
            .andExpect(status().isOk());

        // Validate the Progressplanreturns in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProgressplanreturnsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProgressplanreturns, progressplanreturns),
            getPersistedProgressplanreturns(progressplanreturns)
        );
    }

    @Test
    @Transactional
    void fullUpdateProgressplanreturnsWithPatch() throws Exception {
        // Initialize the database
        progressplanreturnsRepository.saveAndFlush(progressplanreturns);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the progressplanreturns using partial update
        Progressplanreturns partialUpdatedProgressplanreturns = new Progressplanreturns();
        partialUpdatedProgressplanreturns.setId(progressplanreturns.getId());

        partialUpdatedProgressplanreturns
            .planstarttime(UPDATED_PLANSTARTTIME)
            .planendtime(UPDATED_PLANENDTIME)
            .returnstime(UPDATED_RETURNSTIME);

        restProgressplanreturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProgressplanreturns.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProgressplanreturns))
            )
            .andExpect(status().isOk());

        // Validate the Progressplanreturns in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProgressplanreturnsUpdatableFieldsEquals(
            partialUpdatedProgressplanreturns,
            getPersistedProgressplanreturns(partialUpdatedProgressplanreturns)
        );
    }

    @Test
    @Transactional
    void patchNonExistingProgressplanreturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressplanreturns.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgressplanreturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, progressplanreturns.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(progressplanreturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progressplanreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProgressplanreturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressplanreturns.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressplanreturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(progressplanreturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progressplanreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProgressplanreturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressplanreturns.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressplanreturnsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(progressplanreturns)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Progressplanreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProgressplanreturns() throws Exception {
        // Initialize the database
        progressplanreturnsRepository.saveAndFlush(progressplanreturns);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the progressplanreturns
        restProgressplanreturnsMockMvc
            .perform(delete(ENTITY_API_URL_ID, progressplanreturns.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return progressplanreturnsRepository.count();
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

    protected Progressplanreturns getPersistedProgressplanreturns(Progressplanreturns progressplanreturns) {
        return progressplanreturnsRepository.findById(progressplanreturns.getId()).orElseThrow();
    }

    protected void assertPersistedProgressplanreturnsToMatchAllProperties(Progressplanreturns expectedProgressplanreturns) {
        assertProgressplanreturnsAllPropertiesEquals(
            expectedProgressplanreturns,
            getPersistedProgressplanreturns(expectedProgressplanreturns)
        );
    }

    protected void assertPersistedProgressplanreturnsToMatchUpdatableProperties(Progressplanreturns expectedProgressplanreturns) {
        assertProgressplanreturnsAllUpdatablePropertiesEquals(
            expectedProgressplanreturns,
            getPersistedProgressplanreturns(expectedProgressplanreturns)
        );
    }
}

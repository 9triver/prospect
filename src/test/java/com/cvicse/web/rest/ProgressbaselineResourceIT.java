package com.cvicse.web.rest;

import static com.cvicse.domain.ProgressbaselineAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Progressbaseline;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.ProgressbaselineRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link ProgressbaselineResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProgressbaselineResourceIT {

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final String DEFAULT_REQUESTDEPORTMENT = "AAAAAAAAAA";
    private static final String UPDATED_REQUESTDEPORTMENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_CHARGETYPE = 1;
    private static final Integer UPDATED_CHARGETYPE = 2;

    private static final String DEFAULT_CHARGECONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CHARGECONTENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/progressbaselines";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProgressbaselineRepository progressbaselineRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProgressbaselineMockMvc;

    private Progressbaseline progressbaseline;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Progressbaseline createEntity(EntityManager em) {
        Progressbaseline progressbaseline = new Progressbaseline()
            .secretlevel(DEFAULT_SECRETLEVEL)
            .requestdeportment(DEFAULT_REQUESTDEPORTMENT)
            .chargetype(DEFAULT_CHARGETYPE)
            .chargecontent(DEFAULT_CHARGECONTENT)
            .status(DEFAULT_STATUS)
            .version(DEFAULT_VERSION)
            .remark(DEFAULT_REMARK);
        return progressbaseline;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Progressbaseline createUpdatedEntity(EntityManager em) {
        Progressbaseline progressbaseline = new Progressbaseline()
            .secretlevel(UPDATED_SECRETLEVEL)
            .requestdeportment(UPDATED_REQUESTDEPORTMENT)
            .chargetype(UPDATED_CHARGETYPE)
            .chargecontent(UPDATED_CHARGECONTENT)
            .status(UPDATED_STATUS)
            .version(UPDATED_VERSION)
            .remark(UPDATED_REMARK);
        return progressbaseline;
    }

    @BeforeEach
    public void initTest() {
        progressbaseline = createEntity(em);
    }

    @Test
    @Transactional
    void createProgressbaseline() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Progressbaseline
        var returnedProgressbaseline = om.readValue(
            restProgressbaselineMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(progressbaseline)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Progressbaseline.class
        );

        // Validate the Progressbaseline in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertProgressbaselineUpdatableFieldsEquals(returnedProgressbaseline, getPersistedProgressbaseline(returnedProgressbaseline));
    }

    @Test
    @Transactional
    void createProgressbaselineWithExistingId() throws Exception {
        // Create the Progressbaseline with an existing ID
        progressbaseline.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProgressbaselineMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(progressbaseline)))
            .andExpect(status().isBadRequest());

        // Validate the Progressbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProgressbaselines() throws Exception {
        // Initialize the database
        progressbaselineRepository.saveAndFlush(progressbaseline);

        // Get all the progressbaselineList
        restProgressbaselineMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(progressbaseline.getId())))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].requestdeportment").value(hasItem(DEFAULT_REQUESTDEPORTMENT)))
            .andExpect(jsonPath("$.[*].chargetype").value(hasItem(DEFAULT_CHARGETYPE)))
            .andExpect(jsonPath("$.[*].chargecontent").value(hasItem(DEFAULT_CHARGECONTENT)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)));
    }

    @Test
    @Transactional
    void getProgressbaseline() throws Exception {
        // Initialize the database
        progressbaselineRepository.saveAndFlush(progressbaseline);

        // Get the progressbaseline
        restProgressbaselineMockMvc
            .perform(get(ENTITY_API_URL_ID, progressbaseline.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(progressbaseline.getId()))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.requestdeportment").value(DEFAULT_REQUESTDEPORTMENT))
            .andExpect(jsonPath("$.chargetype").value(DEFAULT_CHARGETYPE))
            .andExpect(jsonPath("$.chargecontent").value(DEFAULT_CHARGECONTENT))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK));
    }

    @Test
    @Transactional
    void getNonExistingProgressbaseline() throws Exception {
        // Get the progressbaseline
        restProgressbaselineMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProgressbaseline() throws Exception {
        // Initialize the database
        progressbaselineRepository.saveAndFlush(progressbaseline);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the progressbaseline
        Progressbaseline updatedProgressbaseline = progressbaselineRepository.findById(progressbaseline.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProgressbaseline are not directly saved in db
        em.detach(updatedProgressbaseline);
        updatedProgressbaseline
            .secretlevel(UPDATED_SECRETLEVEL)
            .requestdeportment(UPDATED_REQUESTDEPORTMENT)
            .chargetype(UPDATED_CHARGETYPE)
            .chargecontent(UPDATED_CHARGECONTENT)
            .status(UPDATED_STATUS)
            .version(UPDATED_VERSION)
            .remark(UPDATED_REMARK);

        restProgressbaselineMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProgressbaseline.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedProgressbaseline))
            )
            .andExpect(status().isOk());

        // Validate the Progressbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProgressbaselineToMatchAllProperties(updatedProgressbaseline);
    }

    @Test
    @Transactional
    void putNonExistingProgressbaseline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressbaseline.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgressbaselineMockMvc
            .perform(
                put(ENTITY_API_URL_ID, progressbaseline.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(progressbaseline))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progressbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProgressbaseline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressbaseline.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressbaselineMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(progressbaseline))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progressbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProgressbaseline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressbaseline.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressbaselineMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(progressbaseline)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Progressbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProgressbaselineWithPatch() throws Exception {
        // Initialize the database
        progressbaselineRepository.saveAndFlush(progressbaseline);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the progressbaseline using partial update
        Progressbaseline partialUpdatedProgressbaseline = new Progressbaseline();
        partialUpdatedProgressbaseline.setId(progressbaseline.getId());

        partialUpdatedProgressbaseline
            .requestdeportment(UPDATED_REQUESTDEPORTMENT)
            .chargetype(UPDATED_CHARGETYPE)
            .chargecontent(UPDATED_CHARGECONTENT)
            .status(UPDATED_STATUS)
            .version(UPDATED_VERSION);

        restProgressbaselineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProgressbaseline.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProgressbaseline))
            )
            .andExpect(status().isOk());

        // Validate the Progressbaseline in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProgressbaselineUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProgressbaseline, progressbaseline),
            getPersistedProgressbaseline(progressbaseline)
        );
    }

    @Test
    @Transactional
    void fullUpdateProgressbaselineWithPatch() throws Exception {
        // Initialize the database
        progressbaselineRepository.saveAndFlush(progressbaseline);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the progressbaseline using partial update
        Progressbaseline partialUpdatedProgressbaseline = new Progressbaseline();
        partialUpdatedProgressbaseline.setId(progressbaseline.getId());

        partialUpdatedProgressbaseline
            .secretlevel(UPDATED_SECRETLEVEL)
            .requestdeportment(UPDATED_REQUESTDEPORTMENT)
            .chargetype(UPDATED_CHARGETYPE)
            .chargecontent(UPDATED_CHARGECONTENT)
            .status(UPDATED_STATUS)
            .version(UPDATED_VERSION)
            .remark(UPDATED_REMARK);

        restProgressbaselineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProgressbaseline.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProgressbaseline))
            )
            .andExpect(status().isOk());

        // Validate the Progressbaseline in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProgressbaselineUpdatableFieldsEquals(
            partialUpdatedProgressbaseline,
            getPersistedProgressbaseline(partialUpdatedProgressbaseline)
        );
    }

    @Test
    @Transactional
    void patchNonExistingProgressbaseline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressbaseline.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgressbaselineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, progressbaseline.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(progressbaseline))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progressbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProgressbaseline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressbaseline.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressbaselineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(progressbaseline))
            )
            .andExpect(status().isBadRequest());

        // Validate the Progressbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProgressbaseline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressbaseline.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressbaselineMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(progressbaseline)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Progressbaseline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProgressbaseline() throws Exception {
        // Initialize the database
        progressbaselineRepository.saveAndFlush(progressbaseline);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the progressbaseline
        restProgressbaselineMockMvc
            .perform(delete(ENTITY_API_URL_ID, progressbaseline.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return progressbaselineRepository.count();
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

    protected Progressbaseline getPersistedProgressbaseline(Progressbaseline progressbaseline) {
        return progressbaselineRepository.findById(progressbaseline.getId()).orElseThrow();
    }

    protected void assertPersistedProgressbaselineToMatchAllProperties(Progressbaseline expectedProgressbaseline) {
        assertProgressbaselineAllPropertiesEquals(expectedProgressbaseline, getPersistedProgressbaseline(expectedProgressbaseline));
    }

    protected void assertPersistedProgressbaselineToMatchUpdatableProperties(Progressbaseline expectedProgressbaseline) {
        assertProgressbaselineAllUpdatablePropertiesEquals(
            expectedProgressbaseline,
            getPersistedProgressbaseline(expectedProgressbaseline)
        );
    }
}

package com.cvicse.web.rest;

import static com.cvicse.domain.ProgressmanagementWbsAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.ProgressmanagementWbs;
import com.cvicse.repository.ProgressmanagementWbsRepository;
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
 * Integration tests for the {@link ProgressmanagementWbsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProgressmanagementWbsResourceIT {

    private static final String DEFAULT_WBSSPARE_1 = "AAAAAAAAAA";
    private static final String UPDATED_WBSSPARE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_WBSSPARE_2 = "AAAAAAAAAA";
    private static final String UPDATED_WBSSPARE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_WBSSPARE_3 = "AAAAAAAAAA";
    private static final String UPDATED_WBSSPARE_3 = "BBBBBBBBBB";

    private static final String DEFAULT_WBSSPARE_4 = "AAAAAAAAAA";
    private static final String UPDATED_WBSSPARE_4 = "BBBBBBBBBB";

    private static final String DEFAULT_WBSSPARE_5 = "AAAAAAAAAA";
    private static final String UPDATED_WBSSPARE_5 = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/progressmanagement-wbs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProgressmanagementWbsRepository progressmanagementWbsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProgressmanagementWbsMockMvc;

    private ProgressmanagementWbs progressmanagementWbs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProgressmanagementWbs createEntity(EntityManager em) {
        ProgressmanagementWbs progressmanagementWbs = new ProgressmanagementWbs()
            .wbsspare1(DEFAULT_WBSSPARE_1)
            .wbsspare2(DEFAULT_WBSSPARE_2)
            .wbsspare3(DEFAULT_WBSSPARE_3)
            .wbsspare4(DEFAULT_WBSSPARE_4)
            .wbsspare5(DEFAULT_WBSSPARE_5);
        return progressmanagementWbs;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProgressmanagementWbs createUpdatedEntity(EntityManager em) {
        ProgressmanagementWbs progressmanagementWbs = new ProgressmanagementWbs()
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);
        return progressmanagementWbs;
    }

    @BeforeEach
    public void initTest() {
        progressmanagementWbs = createEntity(em);
    }

    @Test
    @Transactional
    void createProgressmanagementWbs() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ProgressmanagementWbs
        var returnedProgressmanagementWbs = om.readValue(
            restProgressmanagementWbsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(progressmanagementWbs)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ProgressmanagementWbs.class
        );

        // Validate the ProgressmanagementWbs in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertProgressmanagementWbsUpdatableFieldsEquals(
            returnedProgressmanagementWbs,
            getPersistedProgressmanagementWbs(returnedProgressmanagementWbs)
        );
    }

    @Test
    @Transactional
    void createProgressmanagementWbsWithExistingId() throws Exception {
        // Create the ProgressmanagementWbs with an existing ID
        progressmanagementWbs.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProgressmanagementWbsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(progressmanagementWbs)))
            .andExpect(status().isBadRequest());

        // Validate the ProgressmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProgressmanagementWbs() throws Exception {
        // Initialize the database
        progressmanagementWbsRepository.saveAndFlush(progressmanagementWbs);

        // Get all the progressmanagementWbsList
        restProgressmanagementWbsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(progressmanagementWbs.getId())))
            .andExpect(jsonPath("$.[*].wbsspare1").value(hasItem(DEFAULT_WBSSPARE_1)))
            .andExpect(jsonPath("$.[*].wbsspare2").value(hasItem(DEFAULT_WBSSPARE_2)))
            .andExpect(jsonPath("$.[*].wbsspare3").value(hasItem(DEFAULT_WBSSPARE_3)))
            .andExpect(jsonPath("$.[*].wbsspare4").value(hasItem(DEFAULT_WBSSPARE_4)))
            .andExpect(jsonPath("$.[*].wbsspare5").value(hasItem(DEFAULT_WBSSPARE_5)));
    }

    @Test
    @Transactional
    void getProgressmanagementWbs() throws Exception {
        // Initialize the database
        progressmanagementWbsRepository.saveAndFlush(progressmanagementWbs);

        // Get the progressmanagementWbs
        restProgressmanagementWbsMockMvc
            .perform(get(ENTITY_API_URL_ID, progressmanagementWbs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(progressmanagementWbs.getId()))
            .andExpect(jsonPath("$.wbsspare1").value(DEFAULT_WBSSPARE_1))
            .andExpect(jsonPath("$.wbsspare2").value(DEFAULT_WBSSPARE_2))
            .andExpect(jsonPath("$.wbsspare3").value(DEFAULT_WBSSPARE_3))
            .andExpect(jsonPath("$.wbsspare4").value(DEFAULT_WBSSPARE_4))
            .andExpect(jsonPath("$.wbsspare5").value(DEFAULT_WBSSPARE_5));
    }

    @Test
    @Transactional
    void getNonExistingProgressmanagementWbs() throws Exception {
        // Get the progressmanagementWbs
        restProgressmanagementWbsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProgressmanagementWbs() throws Exception {
        // Initialize the database
        progressmanagementWbsRepository.saveAndFlush(progressmanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the progressmanagementWbs
        ProgressmanagementWbs updatedProgressmanagementWbs = progressmanagementWbsRepository
            .findById(progressmanagementWbs.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedProgressmanagementWbs are not directly saved in db
        em.detach(updatedProgressmanagementWbs);
        updatedProgressmanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restProgressmanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProgressmanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedProgressmanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the ProgressmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProgressmanagementWbsToMatchAllProperties(updatedProgressmanagementWbs);
    }

    @Test
    @Transactional
    void putNonExistingProgressmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressmanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgressmanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, progressmanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(progressmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProgressmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProgressmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressmanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(progressmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProgressmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProgressmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressmanagementWbsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(progressmanagementWbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProgressmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProgressmanagementWbsWithPatch() throws Exception {
        // Initialize the database
        progressmanagementWbsRepository.saveAndFlush(progressmanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the progressmanagementWbs using partial update
        ProgressmanagementWbs partialUpdatedProgressmanagementWbs = new ProgressmanagementWbs();
        partialUpdatedProgressmanagementWbs.setId(progressmanagementWbs.getId());

        partialUpdatedProgressmanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restProgressmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProgressmanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProgressmanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the ProgressmanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProgressmanagementWbsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProgressmanagementWbs, progressmanagementWbs),
            getPersistedProgressmanagementWbs(progressmanagementWbs)
        );
    }

    @Test
    @Transactional
    void fullUpdateProgressmanagementWbsWithPatch() throws Exception {
        // Initialize the database
        progressmanagementWbsRepository.saveAndFlush(progressmanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the progressmanagementWbs using partial update
        ProgressmanagementWbs partialUpdatedProgressmanagementWbs = new ProgressmanagementWbs();
        partialUpdatedProgressmanagementWbs.setId(progressmanagementWbs.getId());

        partialUpdatedProgressmanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restProgressmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProgressmanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProgressmanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the ProgressmanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProgressmanagementWbsUpdatableFieldsEquals(
            partialUpdatedProgressmanagementWbs,
            getPersistedProgressmanagementWbs(partialUpdatedProgressmanagementWbs)
        );
    }

    @Test
    @Transactional
    void patchNonExistingProgressmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressmanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProgressmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, progressmanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(progressmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProgressmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProgressmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(progressmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProgressmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProgressmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        progressmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProgressmanagementWbsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(progressmanagementWbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProgressmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProgressmanagementWbs() throws Exception {
        // Initialize the database
        progressmanagementWbsRepository.saveAndFlush(progressmanagementWbs);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the progressmanagementWbs
        restProgressmanagementWbsMockMvc
            .perform(delete(ENTITY_API_URL_ID, progressmanagementWbs.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return progressmanagementWbsRepository.count();
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

    protected ProgressmanagementWbs getPersistedProgressmanagementWbs(ProgressmanagementWbs progressmanagementWbs) {
        return progressmanagementWbsRepository.findById(progressmanagementWbs.getId()).orElseThrow();
    }

    protected void assertPersistedProgressmanagementWbsToMatchAllProperties(ProgressmanagementWbs expectedProgressmanagementWbs) {
        assertProgressmanagementWbsAllPropertiesEquals(
            expectedProgressmanagementWbs,
            getPersistedProgressmanagementWbs(expectedProgressmanagementWbs)
        );
    }

    protected void assertPersistedProgressmanagementWbsToMatchUpdatableProperties(ProgressmanagementWbs expectedProgressmanagementWbs) {
        assertProgressmanagementWbsAllUpdatablePropertiesEquals(
            expectedProgressmanagementWbs,
            getPersistedProgressmanagementWbs(expectedProgressmanagementWbs)
        );
    }
}

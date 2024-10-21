package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.RiskLevelAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.RiskLevel;
import com.cvicse.jy1.repository.RiskLevelRepository;
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
 * Integration tests for the {@link RiskLevelResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RiskLevelResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/risk-levels";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RiskLevelRepository riskLevelRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRiskLevelMockMvc;

    private RiskLevel riskLevel;

    private RiskLevel insertedRiskLevel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskLevel createEntity(EntityManager em) {
        RiskLevel riskLevel = new RiskLevel().name(DEFAULT_NAME);
        return riskLevel;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskLevel createUpdatedEntity(EntityManager em) {
        RiskLevel riskLevel = new RiskLevel().name(UPDATED_NAME);
        return riskLevel;
    }

    @BeforeEach
    public void initTest() {
        riskLevel = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedRiskLevel != null) {
            riskLevelRepository.delete(insertedRiskLevel);
            insertedRiskLevel = null;
        }
    }

    @Test
    @Transactional
    void createRiskLevel() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the RiskLevel
        var returnedRiskLevel = om.readValue(
            restRiskLevelMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskLevel)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            RiskLevel.class
        );

        // Validate the RiskLevel in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRiskLevelUpdatableFieldsEquals(returnedRiskLevel, getPersistedRiskLevel(returnedRiskLevel));

        insertedRiskLevel = returnedRiskLevel;
    }

    @Test
    @Transactional
    void createRiskLevelWithExistingId() throws Exception {
        // Create the RiskLevel with an existing ID
        riskLevel.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskLevelMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskLevel)))
            .andExpect(status().isBadRequest());

        // Validate the RiskLevel in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRiskLevels() throws Exception {
        // Initialize the database
        insertedRiskLevel = riskLevelRepository.saveAndFlush(riskLevel);

        // Get all the riskLevelList
        restRiskLevelMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskLevel.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getRiskLevel() throws Exception {
        // Initialize the database
        insertedRiskLevel = riskLevelRepository.saveAndFlush(riskLevel);

        // Get the riskLevel
        restRiskLevelMockMvc
            .perform(get(ENTITY_API_URL_ID, riskLevel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(riskLevel.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingRiskLevel() throws Exception {
        // Get the riskLevel
        restRiskLevelMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRiskLevel() throws Exception {
        // Initialize the database
        insertedRiskLevel = riskLevelRepository.saveAndFlush(riskLevel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskLevel
        RiskLevel updatedRiskLevel = riskLevelRepository.findById(riskLevel.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedRiskLevel are not directly saved in db
        em.detach(updatedRiskLevel);
        updatedRiskLevel.name(UPDATED_NAME);

        restRiskLevelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRiskLevel.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRiskLevel))
            )
            .andExpect(status().isOk());

        // Validate the RiskLevel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRiskLevelToMatchAllProperties(updatedRiskLevel);
    }

    @Test
    @Transactional
    void putNonExistingRiskLevel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskLevel.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskLevelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, riskLevel.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskLevel))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskLevel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRiskLevel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskLevel.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskLevelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(riskLevel))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskLevel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRiskLevel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskLevel.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskLevelMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskLevel)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RiskLevel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRiskLevelWithPatch() throws Exception {
        // Initialize the database
        insertedRiskLevel = riskLevelRepository.saveAndFlush(riskLevel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskLevel using partial update
        RiskLevel partialUpdatedRiskLevel = new RiskLevel();
        partialUpdatedRiskLevel.setId(riskLevel.getId());

        restRiskLevelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskLevel.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskLevel))
            )
            .andExpect(status().isOk());

        // Validate the RiskLevel in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskLevelUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedRiskLevel, riskLevel),
            getPersistedRiskLevel(riskLevel)
        );
    }

    @Test
    @Transactional
    void fullUpdateRiskLevelWithPatch() throws Exception {
        // Initialize the database
        insertedRiskLevel = riskLevelRepository.saveAndFlush(riskLevel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskLevel using partial update
        RiskLevel partialUpdatedRiskLevel = new RiskLevel();
        partialUpdatedRiskLevel.setId(riskLevel.getId());

        partialUpdatedRiskLevel.name(UPDATED_NAME);

        restRiskLevelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskLevel.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskLevel))
            )
            .andExpect(status().isOk());

        // Validate the RiskLevel in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskLevelUpdatableFieldsEquals(partialUpdatedRiskLevel, getPersistedRiskLevel(partialUpdatedRiskLevel));
    }

    @Test
    @Transactional
    void patchNonExistingRiskLevel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskLevel.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskLevelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, riskLevel.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskLevel))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskLevel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRiskLevel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskLevel.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskLevelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskLevel))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskLevel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRiskLevel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskLevel.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskLevelMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(riskLevel)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RiskLevel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRiskLevel() throws Exception {
        // Initialize the database
        insertedRiskLevel = riskLevelRepository.saveAndFlush(riskLevel);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the riskLevel
        restRiskLevelMockMvc
            .perform(delete(ENTITY_API_URL_ID, riskLevel.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return riskLevelRepository.count();
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

    protected RiskLevel getPersistedRiskLevel(RiskLevel riskLevel) {
        return riskLevelRepository.findById(riskLevel.getId()).orElseThrow();
    }

    protected void assertPersistedRiskLevelToMatchAllProperties(RiskLevel expectedRiskLevel) {
        assertRiskLevelAllPropertiesEquals(expectedRiskLevel, getPersistedRiskLevel(expectedRiskLevel));
    }

    protected void assertPersistedRiskLevelToMatchUpdatableProperties(RiskLevel expectedRiskLevel) {
        assertRiskLevelAllUpdatablePropertiesEquals(expectedRiskLevel, getPersistedRiskLevel(expectedRiskLevel));
    }
}

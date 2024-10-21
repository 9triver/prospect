package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.RiskPossibilityAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.RiskPossibility;
import com.cvicse.jy1.repository.RiskPossibilityRepository;
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
 * Integration tests for the {@link RiskPossibilityResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RiskPossibilityResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/risk-possibilities";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RiskPossibilityRepository riskPossibilityRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRiskPossibilityMockMvc;

    private RiskPossibility riskPossibility;

    private RiskPossibility insertedRiskPossibility;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskPossibility createEntity(EntityManager em) {
        RiskPossibility riskPossibility = new RiskPossibility().name(DEFAULT_NAME);
        return riskPossibility;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskPossibility createUpdatedEntity(EntityManager em) {
        RiskPossibility riskPossibility = new RiskPossibility().name(UPDATED_NAME);
        return riskPossibility;
    }

    @BeforeEach
    public void initTest() {
        riskPossibility = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedRiskPossibility != null) {
            riskPossibilityRepository.delete(insertedRiskPossibility);
            insertedRiskPossibility = null;
        }
    }

    @Test
    @Transactional
    void createRiskPossibility() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the RiskPossibility
        var returnedRiskPossibility = om.readValue(
            restRiskPossibilityMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskPossibility)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            RiskPossibility.class
        );

        // Validate the RiskPossibility in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRiskPossibilityUpdatableFieldsEquals(returnedRiskPossibility, getPersistedRiskPossibility(returnedRiskPossibility));

        insertedRiskPossibility = returnedRiskPossibility;
    }

    @Test
    @Transactional
    void createRiskPossibilityWithExistingId() throws Exception {
        // Create the RiskPossibility with an existing ID
        riskPossibility.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskPossibilityMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskPossibility)))
            .andExpect(status().isBadRequest());

        // Validate the RiskPossibility in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRiskPossibilities() throws Exception {
        // Initialize the database
        insertedRiskPossibility = riskPossibilityRepository.saveAndFlush(riskPossibility);

        // Get all the riskPossibilityList
        restRiskPossibilityMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskPossibility.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getRiskPossibility() throws Exception {
        // Initialize the database
        insertedRiskPossibility = riskPossibilityRepository.saveAndFlush(riskPossibility);

        // Get the riskPossibility
        restRiskPossibilityMockMvc
            .perform(get(ENTITY_API_URL_ID, riskPossibility.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(riskPossibility.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingRiskPossibility() throws Exception {
        // Get the riskPossibility
        restRiskPossibilityMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRiskPossibility() throws Exception {
        // Initialize the database
        insertedRiskPossibility = riskPossibilityRepository.saveAndFlush(riskPossibility);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskPossibility
        RiskPossibility updatedRiskPossibility = riskPossibilityRepository.findById(riskPossibility.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedRiskPossibility are not directly saved in db
        em.detach(updatedRiskPossibility);
        updatedRiskPossibility.name(UPDATED_NAME);

        restRiskPossibilityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRiskPossibility.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRiskPossibility))
            )
            .andExpect(status().isOk());

        // Validate the RiskPossibility in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRiskPossibilityToMatchAllProperties(updatedRiskPossibility);
    }

    @Test
    @Transactional
    void putNonExistingRiskPossibility() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskPossibility.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskPossibilityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, riskPossibility.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(riskPossibility))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskPossibility in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRiskPossibility() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskPossibility.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskPossibilityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(riskPossibility))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskPossibility in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRiskPossibility() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskPossibility.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskPossibilityMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskPossibility)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RiskPossibility in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRiskPossibilityWithPatch() throws Exception {
        // Initialize the database
        insertedRiskPossibility = riskPossibilityRepository.saveAndFlush(riskPossibility);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskPossibility using partial update
        RiskPossibility partialUpdatedRiskPossibility = new RiskPossibility();
        partialUpdatedRiskPossibility.setId(riskPossibility.getId());

        partialUpdatedRiskPossibility.name(UPDATED_NAME);

        restRiskPossibilityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskPossibility.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskPossibility))
            )
            .andExpect(status().isOk());

        // Validate the RiskPossibility in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskPossibilityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedRiskPossibility, riskPossibility),
            getPersistedRiskPossibility(riskPossibility)
        );
    }

    @Test
    @Transactional
    void fullUpdateRiskPossibilityWithPatch() throws Exception {
        // Initialize the database
        insertedRiskPossibility = riskPossibilityRepository.saveAndFlush(riskPossibility);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskPossibility using partial update
        RiskPossibility partialUpdatedRiskPossibility = new RiskPossibility();
        partialUpdatedRiskPossibility.setId(riskPossibility.getId());

        partialUpdatedRiskPossibility.name(UPDATED_NAME);

        restRiskPossibilityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskPossibility.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskPossibility))
            )
            .andExpect(status().isOk());

        // Validate the RiskPossibility in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskPossibilityUpdatableFieldsEquals(
            partialUpdatedRiskPossibility,
            getPersistedRiskPossibility(partialUpdatedRiskPossibility)
        );
    }

    @Test
    @Transactional
    void patchNonExistingRiskPossibility() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskPossibility.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskPossibilityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, riskPossibility.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskPossibility))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskPossibility in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRiskPossibility() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskPossibility.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskPossibilityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskPossibility))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskPossibility in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRiskPossibility() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskPossibility.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskPossibilityMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(riskPossibility)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RiskPossibility in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRiskPossibility() throws Exception {
        // Initialize the database
        insertedRiskPossibility = riskPossibilityRepository.saveAndFlush(riskPossibility);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the riskPossibility
        restRiskPossibilityMockMvc
            .perform(delete(ENTITY_API_URL_ID, riskPossibility.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return riskPossibilityRepository.count();
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

    protected RiskPossibility getPersistedRiskPossibility(RiskPossibility riskPossibility) {
        return riskPossibilityRepository.findById(riskPossibility.getId()).orElseThrow();
    }

    protected void assertPersistedRiskPossibilityToMatchAllProperties(RiskPossibility expectedRiskPossibility) {
        assertRiskPossibilityAllPropertiesEquals(expectedRiskPossibility, getPersistedRiskPossibility(expectedRiskPossibility));
    }

    protected void assertPersistedRiskPossibilityToMatchUpdatableProperties(RiskPossibility expectedRiskPossibility) {
        assertRiskPossibilityAllUpdatablePropertiesEquals(expectedRiskPossibility, getPersistedRiskPossibility(expectedRiskPossibility));
    }
}

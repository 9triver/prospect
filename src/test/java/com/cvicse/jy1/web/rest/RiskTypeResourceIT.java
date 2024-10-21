package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.RiskTypeAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.RiskType;
import com.cvicse.jy1.repository.RiskTypeRepository;
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
 * Integration tests for the {@link RiskTypeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RiskTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/risk-types";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RiskTypeRepository riskTypeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRiskTypeMockMvc;

    private RiskType riskType;

    private RiskType insertedRiskType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskType createEntity(EntityManager em) {
        RiskType riskType = new RiskType().name(DEFAULT_NAME);
        return riskType;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskType createUpdatedEntity(EntityManager em) {
        RiskType riskType = new RiskType().name(UPDATED_NAME);
        return riskType;
    }

    @BeforeEach
    public void initTest() {
        riskType = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedRiskType != null) {
            riskTypeRepository.delete(insertedRiskType);
            insertedRiskType = null;
        }
    }

    @Test
    @Transactional
    void createRiskType() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the RiskType
        var returnedRiskType = om.readValue(
            restRiskTypeMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskType)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            RiskType.class
        );

        // Validate the RiskType in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRiskTypeUpdatableFieldsEquals(returnedRiskType, getPersistedRiskType(returnedRiskType));

        insertedRiskType = returnedRiskType;
    }

    @Test
    @Transactional
    void createRiskTypeWithExistingId() throws Exception {
        // Create the RiskType with an existing ID
        riskType.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskTypeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskType)))
            .andExpect(status().isBadRequest());

        // Validate the RiskType in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRiskTypes() throws Exception {
        // Initialize the database
        insertedRiskType = riskTypeRepository.saveAndFlush(riskType);

        // Get all the riskTypeList
        restRiskTypeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getRiskType() throws Exception {
        // Initialize the database
        insertedRiskType = riskTypeRepository.saveAndFlush(riskType);

        // Get the riskType
        restRiskTypeMockMvc
            .perform(get(ENTITY_API_URL_ID, riskType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(riskType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingRiskType() throws Exception {
        // Get the riskType
        restRiskTypeMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRiskType() throws Exception {
        // Initialize the database
        insertedRiskType = riskTypeRepository.saveAndFlush(riskType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskType
        RiskType updatedRiskType = riskTypeRepository.findById(riskType.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedRiskType are not directly saved in db
        em.detach(updatedRiskType);
        updatedRiskType.name(UPDATED_NAME);

        restRiskTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRiskType.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRiskType))
            )
            .andExpect(status().isOk());

        // Validate the RiskType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRiskTypeToMatchAllProperties(updatedRiskType);
    }

    @Test
    @Transactional
    void putNonExistingRiskType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskType.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, riskType.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskType))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRiskType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskType.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskTypeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(riskType))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRiskType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskType.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskTypeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskType)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RiskType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRiskTypeWithPatch() throws Exception {
        // Initialize the database
        insertedRiskType = riskTypeRepository.saveAndFlush(riskType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskType using partial update
        RiskType partialUpdatedRiskType = new RiskType();
        partialUpdatedRiskType.setId(riskType.getId());

        partialUpdatedRiskType.name(UPDATED_NAME);

        restRiskTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskType.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskType))
            )
            .andExpect(status().isOk());

        // Validate the RiskType in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskTypeUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedRiskType, riskType), getPersistedRiskType(riskType));
    }

    @Test
    @Transactional
    void fullUpdateRiskTypeWithPatch() throws Exception {
        // Initialize the database
        insertedRiskType = riskTypeRepository.saveAndFlush(riskType);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskType using partial update
        RiskType partialUpdatedRiskType = new RiskType();
        partialUpdatedRiskType.setId(riskType.getId());

        partialUpdatedRiskType.name(UPDATED_NAME);

        restRiskTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskType.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskType))
            )
            .andExpect(status().isOk());

        // Validate the RiskType in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskTypeUpdatableFieldsEquals(partialUpdatedRiskType, getPersistedRiskType(partialUpdatedRiskType));
    }

    @Test
    @Transactional
    void patchNonExistingRiskType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskType.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, riskType.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskType))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRiskType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskType.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskTypeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskType))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRiskType() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskType.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskTypeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(riskType)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RiskType in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRiskType() throws Exception {
        // Initialize the database
        insertedRiskType = riskTypeRepository.saveAndFlush(riskType);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the riskType
        restRiskTypeMockMvc
            .perform(delete(ENTITY_API_URL_ID, riskType.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return riskTypeRepository.count();
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

    protected RiskType getPersistedRiskType(RiskType riskType) {
        return riskTypeRepository.findById(riskType.getId()).orElseThrow();
    }

    protected void assertPersistedRiskTypeToMatchAllProperties(RiskType expectedRiskType) {
        assertRiskTypeAllPropertiesEquals(expectedRiskType, getPersistedRiskType(expectedRiskType));
    }

    protected void assertPersistedRiskTypeToMatchUpdatableProperties(RiskType expectedRiskType) {
        assertRiskTypeAllUpdatablePropertiesEquals(expectedRiskType, getPersistedRiskType(expectedRiskType));
    }
}

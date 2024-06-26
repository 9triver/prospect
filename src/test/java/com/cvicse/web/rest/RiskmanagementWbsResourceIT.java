package com.cvicse.web.rest;

import static com.cvicse.domain.RiskmanagementWbsAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.RiskmanagementWbs;
import com.cvicse.repository.RiskmanagementWbsRepository;
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
 * Integration tests for the {@link RiskmanagementWbsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RiskmanagementWbsResourceIT {

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

    private static final String ENTITY_API_URL = "/api/riskmanagement-wbs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RiskmanagementWbsRepository riskmanagementWbsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRiskmanagementWbsMockMvc;

    private RiskmanagementWbs riskmanagementWbs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskmanagementWbs createEntity(EntityManager em) {
        RiskmanagementWbs riskmanagementWbs = new RiskmanagementWbs()
            .wbsspare1(DEFAULT_WBSSPARE_1)
            .wbsspare2(DEFAULT_WBSSPARE_2)
            .wbsspare3(DEFAULT_WBSSPARE_3)
            .wbsspare4(DEFAULT_WBSSPARE_4)
            .wbsspare5(DEFAULT_WBSSPARE_5);
        return riskmanagementWbs;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskmanagementWbs createUpdatedEntity(EntityManager em) {
        RiskmanagementWbs riskmanagementWbs = new RiskmanagementWbs()
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);
        return riskmanagementWbs;
    }

    @BeforeEach
    public void initTest() {
        riskmanagementWbs = createEntity(em);
    }

    @Test
    @Transactional
    void createRiskmanagementWbs() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the RiskmanagementWbs
        var returnedRiskmanagementWbs = om.readValue(
            restRiskmanagementWbsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskmanagementWbs)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            RiskmanagementWbs.class
        );

        // Validate the RiskmanagementWbs in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRiskmanagementWbsUpdatableFieldsEquals(returnedRiskmanagementWbs, getPersistedRiskmanagementWbs(returnedRiskmanagementWbs));
    }

    @Test
    @Transactional
    void createRiskmanagementWbsWithExistingId() throws Exception {
        // Create the RiskmanagementWbs with an existing ID
        riskmanagementWbs.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskmanagementWbsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskmanagementWbs)))
            .andExpect(status().isBadRequest());

        // Validate the RiskmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRiskmanagementWbs() throws Exception {
        // Initialize the database
        riskmanagementWbsRepository.saveAndFlush(riskmanagementWbs);

        // Get all the riskmanagementWbsList
        restRiskmanagementWbsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskmanagementWbs.getId())))
            .andExpect(jsonPath("$.[*].wbsspare1").value(hasItem(DEFAULT_WBSSPARE_1)))
            .andExpect(jsonPath("$.[*].wbsspare2").value(hasItem(DEFAULT_WBSSPARE_2)))
            .andExpect(jsonPath("$.[*].wbsspare3").value(hasItem(DEFAULT_WBSSPARE_3)))
            .andExpect(jsonPath("$.[*].wbsspare4").value(hasItem(DEFAULT_WBSSPARE_4)))
            .andExpect(jsonPath("$.[*].wbsspare5").value(hasItem(DEFAULT_WBSSPARE_5)));
    }

    @Test
    @Transactional
    void getRiskmanagementWbs() throws Exception {
        // Initialize the database
        riskmanagementWbsRepository.saveAndFlush(riskmanagementWbs);

        // Get the riskmanagementWbs
        restRiskmanagementWbsMockMvc
            .perform(get(ENTITY_API_URL_ID, riskmanagementWbs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(riskmanagementWbs.getId()))
            .andExpect(jsonPath("$.wbsspare1").value(DEFAULT_WBSSPARE_1))
            .andExpect(jsonPath("$.wbsspare2").value(DEFAULT_WBSSPARE_2))
            .andExpect(jsonPath("$.wbsspare3").value(DEFAULT_WBSSPARE_3))
            .andExpect(jsonPath("$.wbsspare4").value(DEFAULT_WBSSPARE_4))
            .andExpect(jsonPath("$.wbsspare5").value(DEFAULT_WBSSPARE_5));
    }

    @Test
    @Transactional
    void getNonExistingRiskmanagementWbs() throws Exception {
        // Get the riskmanagementWbs
        restRiskmanagementWbsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRiskmanagementWbs() throws Exception {
        // Initialize the database
        riskmanagementWbsRepository.saveAndFlush(riskmanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskmanagementWbs
        RiskmanagementWbs updatedRiskmanagementWbs = riskmanagementWbsRepository.findById(riskmanagementWbs.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedRiskmanagementWbs are not directly saved in db
        em.detach(updatedRiskmanagementWbs);
        updatedRiskmanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restRiskmanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRiskmanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRiskmanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the RiskmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRiskmanagementWbsToMatchAllProperties(updatedRiskmanagementWbs);
    }

    @Test
    @Transactional
    void putNonExistingRiskmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskmanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskmanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, riskmanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(riskmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRiskmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskmanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(riskmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRiskmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskmanagementWbsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskmanagementWbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RiskmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRiskmanagementWbsWithPatch() throws Exception {
        // Initialize the database
        riskmanagementWbsRepository.saveAndFlush(riskmanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskmanagementWbs using partial update
        RiskmanagementWbs partialUpdatedRiskmanagementWbs = new RiskmanagementWbs();
        partialUpdatedRiskmanagementWbs.setId(riskmanagementWbs.getId());

        partialUpdatedRiskmanagementWbs.wbsspare2(UPDATED_WBSSPARE_2);

        restRiskmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskmanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskmanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the RiskmanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskmanagementWbsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedRiskmanagementWbs, riskmanagementWbs),
            getPersistedRiskmanagementWbs(riskmanagementWbs)
        );
    }

    @Test
    @Transactional
    void fullUpdateRiskmanagementWbsWithPatch() throws Exception {
        // Initialize the database
        riskmanagementWbsRepository.saveAndFlush(riskmanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskmanagementWbs using partial update
        RiskmanagementWbs partialUpdatedRiskmanagementWbs = new RiskmanagementWbs();
        partialUpdatedRiskmanagementWbs.setId(riskmanagementWbs.getId());

        partialUpdatedRiskmanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restRiskmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskmanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskmanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the RiskmanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskmanagementWbsUpdatableFieldsEquals(
            partialUpdatedRiskmanagementWbs,
            getPersistedRiskmanagementWbs(partialUpdatedRiskmanagementWbs)
        );
    }

    @Test
    @Transactional
    void patchNonExistingRiskmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskmanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, riskmanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRiskmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRiskmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskmanagementWbsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(riskmanagementWbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RiskmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRiskmanagementWbs() throws Exception {
        // Initialize the database
        riskmanagementWbsRepository.saveAndFlush(riskmanagementWbs);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the riskmanagementWbs
        restRiskmanagementWbsMockMvc
            .perform(delete(ENTITY_API_URL_ID, riskmanagementWbs.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return riskmanagementWbsRepository.count();
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

    protected RiskmanagementWbs getPersistedRiskmanagementWbs(RiskmanagementWbs riskmanagementWbs) {
        return riskmanagementWbsRepository.findById(riskmanagementWbs.getId()).orElseThrow();
    }

    protected void assertPersistedRiskmanagementWbsToMatchAllProperties(RiskmanagementWbs expectedRiskmanagementWbs) {
        assertRiskmanagementWbsAllPropertiesEquals(expectedRiskmanagementWbs, getPersistedRiskmanagementWbs(expectedRiskmanagementWbs));
    }

    protected void assertPersistedRiskmanagementWbsToMatchUpdatableProperties(RiskmanagementWbs expectedRiskmanagementWbs) {
        assertRiskmanagementWbsAllUpdatablePropertiesEquals(
            expectedRiskmanagementWbs,
            getPersistedRiskmanagementWbs(expectedRiskmanagementWbs)
        );
    }
}

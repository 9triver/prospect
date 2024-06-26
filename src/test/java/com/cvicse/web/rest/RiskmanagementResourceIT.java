package com.cvicse.web.rest;

import static com.cvicse.domain.RiskmanagementAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Riskmanagement;
import com.cvicse.repository.RiskmanagementRepository;
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
 * Integration tests for the {@link RiskmanagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RiskmanagementResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/riskmanagements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RiskmanagementRepository riskmanagementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRiskmanagementMockMvc;

    private Riskmanagement riskmanagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Riskmanagement createEntity(EntityManager em) {
        Riskmanagement riskmanagement = new Riskmanagement()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME);
        return riskmanagement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Riskmanagement createUpdatedEntity(EntityManager em) {
        Riskmanagement riskmanagement = new Riskmanagement()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);
        return riskmanagement;
    }

    @BeforeEach
    public void initTest() {
        riskmanagement = createEntity(em);
    }

    @Test
    @Transactional
    void createRiskmanagement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Riskmanagement
        var returnedRiskmanagement = om.readValue(
            restRiskmanagementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskmanagement)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Riskmanagement.class
        );

        // Validate the Riskmanagement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRiskmanagementUpdatableFieldsEquals(returnedRiskmanagement, getPersistedRiskmanagement(returnedRiskmanagement));
    }

    @Test
    @Transactional
    void createRiskmanagementWithExistingId() throws Exception {
        // Create the Riskmanagement with an existing ID
        riskmanagement.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskmanagementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskmanagement)))
            .andExpect(status().isBadRequest());

        // Validate the Riskmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRiskmanagements() throws Exception {
        // Initialize the database
        riskmanagementRepository.saveAndFlush(riskmanagement);

        // Get all the riskmanagementList
        restRiskmanagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskmanagement.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())));
    }

    @Test
    @Transactional
    void getRiskmanagement() throws Exception {
        // Initialize the database
        riskmanagementRepository.saveAndFlush(riskmanagement);

        // Get the riskmanagement
        restRiskmanagementMockMvc
            .perform(get(ENTITY_API_URL_ID, riskmanagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(riskmanagement.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingRiskmanagement() throws Exception {
        // Get the riskmanagement
        restRiskmanagementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRiskmanagement() throws Exception {
        // Initialize the database
        riskmanagementRepository.saveAndFlush(riskmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskmanagement
        Riskmanagement updatedRiskmanagement = riskmanagementRepository.findById(riskmanagement.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedRiskmanagement are not directly saved in db
        em.detach(updatedRiskmanagement);
        updatedRiskmanagement.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).starttime(UPDATED_STARTTIME).endtime(UPDATED_ENDTIME);

        restRiskmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRiskmanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRiskmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Riskmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRiskmanagementToMatchAllProperties(updatedRiskmanagement);
    }

    @Test
    @Transactional
    void putNonExistingRiskmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskmanagement.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, riskmanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(riskmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Riskmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRiskmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskmanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(riskmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Riskmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRiskmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskmanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskmanagementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskmanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Riskmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRiskmanagementWithPatch() throws Exception {
        // Initialize the database
        riskmanagementRepository.saveAndFlush(riskmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskmanagement using partial update
        Riskmanagement partialUpdatedRiskmanagement = new Riskmanagement();
        partialUpdatedRiskmanagement.setId(riskmanagement.getId());

        partialUpdatedRiskmanagement.name(UPDATED_NAME);

        restRiskmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Riskmanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskmanagementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedRiskmanagement, riskmanagement),
            getPersistedRiskmanagement(riskmanagement)
        );
    }

    @Test
    @Transactional
    void fullUpdateRiskmanagementWithPatch() throws Exception {
        // Initialize the database
        riskmanagementRepository.saveAndFlush(riskmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskmanagement using partial update
        Riskmanagement partialUpdatedRiskmanagement = new Riskmanagement();
        partialUpdatedRiskmanagement.setId(riskmanagement.getId());

        partialUpdatedRiskmanagement
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);

        restRiskmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Riskmanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskmanagementUpdatableFieldsEquals(partialUpdatedRiskmanagement, getPersistedRiskmanagement(partialUpdatedRiskmanagement));
    }

    @Test
    @Transactional
    void patchNonExistingRiskmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskmanagement.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, riskmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Riskmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRiskmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskmanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Riskmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRiskmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskmanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskmanagementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(riskmanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Riskmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRiskmanagement() throws Exception {
        // Initialize the database
        riskmanagementRepository.saveAndFlush(riskmanagement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the riskmanagement
        restRiskmanagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, riskmanagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return riskmanagementRepository.count();
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

    protected Riskmanagement getPersistedRiskmanagement(Riskmanagement riskmanagement) {
        return riskmanagementRepository.findById(riskmanagement.getId()).orElseThrow();
    }

    protected void assertPersistedRiskmanagementToMatchAllProperties(Riskmanagement expectedRiskmanagement) {
        assertRiskmanagementAllPropertiesEquals(expectedRiskmanagement, getPersistedRiskmanagement(expectedRiskmanagement));
    }

    protected void assertPersistedRiskmanagementToMatchUpdatableProperties(Riskmanagement expectedRiskmanagement) {
        assertRiskmanagementAllUpdatablePropertiesEquals(expectedRiskmanagement, getPersistedRiskmanagement(expectedRiskmanagement));
    }
}

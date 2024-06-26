package com.cvicse.web.rest;

import static com.cvicse.domain.TechnicalmanagementWbsAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.TechnicalmanagementWbs;
import com.cvicse.repository.TechnicalmanagementWbsRepository;
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
 * Integration tests for the {@link TechnicalmanagementWbsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TechnicalmanagementWbsResourceIT {

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

    private static final String ENTITY_API_URL = "/api/technicalmanagement-wbs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TechnicalmanagementWbsRepository technicalmanagementWbsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTechnicalmanagementWbsMockMvc;

    private TechnicalmanagementWbs technicalmanagementWbs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TechnicalmanagementWbs createEntity(EntityManager em) {
        TechnicalmanagementWbs technicalmanagementWbs = new TechnicalmanagementWbs()
            .wbsspare1(DEFAULT_WBSSPARE_1)
            .wbsspare2(DEFAULT_WBSSPARE_2)
            .wbsspare3(DEFAULT_WBSSPARE_3)
            .wbsspare4(DEFAULT_WBSSPARE_4)
            .wbsspare5(DEFAULT_WBSSPARE_5);
        return technicalmanagementWbs;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TechnicalmanagementWbs createUpdatedEntity(EntityManager em) {
        TechnicalmanagementWbs technicalmanagementWbs = new TechnicalmanagementWbs()
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);
        return technicalmanagementWbs;
    }

    @BeforeEach
    public void initTest() {
        technicalmanagementWbs = createEntity(em);
    }

    @Test
    @Transactional
    void createTechnicalmanagementWbs() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TechnicalmanagementWbs
        var returnedTechnicalmanagementWbs = om.readValue(
            restTechnicalmanagementWbsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(technicalmanagementWbs)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TechnicalmanagementWbs.class
        );

        // Validate the TechnicalmanagementWbs in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertTechnicalmanagementWbsUpdatableFieldsEquals(
            returnedTechnicalmanagementWbs,
            getPersistedTechnicalmanagementWbs(returnedTechnicalmanagementWbs)
        );
    }

    @Test
    @Transactional
    void createTechnicalmanagementWbsWithExistingId() throws Exception {
        // Create the TechnicalmanagementWbs with an existing ID
        technicalmanagementWbs.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTechnicalmanagementWbsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(technicalmanagementWbs)))
            .andExpect(status().isBadRequest());

        // Validate the TechnicalmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTechnicalmanagementWbs() throws Exception {
        // Initialize the database
        technicalmanagementWbsRepository.saveAndFlush(technicalmanagementWbs);

        // Get all the technicalmanagementWbsList
        restTechnicalmanagementWbsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(technicalmanagementWbs.getId())))
            .andExpect(jsonPath("$.[*].wbsspare1").value(hasItem(DEFAULT_WBSSPARE_1)))
            .andExpect(jsonPath("$.[*].wbsspare2").value(hasItem(DEFAULT_WBSSPARE_2)))
            .andExpect(jsonPath("$.[*].wbsspare3").value(hasItem(DEFAULT_WBSSPARE_3)))
            .andExpect(jsonPath("$.[*].wbsspare4").value(hasItem(DEFAULT_WBSSPARE_4)))
            .andExpect(jsonPath("$.[*].wbsspare5").value(hasItem(DEFAULT_WBSSPARE_5)));
    }

    @Test
    @Transactional
    void getTechnicalmanagementWbs() throws Exception {
        // Initialize the database
        technicalmanagementWbsRepository.saveAndFlush(technicalmanagementWbs);

        // Get the technicalmanagementWbs
        restTechnicalmanagementWbsMockMvc
            .perform(get(ENTITY_API_URL_ID, technicalmanagementWbs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(technicalmanagementWbs.getId()))
            .andExpect(jsonPath("$.wbsspare1").value(DEFAULT_WBSSPARE_1))
            .andExpect(jsonPath("$.wbsspare2").value(DEFAULT_WBSSPARE_2))
            .andExpect(jsonPath("$.wbsspare3").value(DEFAULT_WBSSPARE_3))
            .andExpect(jsonPath("$.wbsspare4").value(DEFAULT_WBSSPARE_4))
            .andExpect(jsonPath("$.wbsspare5").value(DEFAULT_WBSSPARE_5));
    }

    @Test
    @Transactional
    void getNonExistingTechnicalmanagementWbs() throws Exception {
        // Get the technicalmanagementWbs
        restTechnicalmanagementWbsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTechnicalmanagementWbs() throws Exception {
        // Initialize the database
        technicalmanagementWbsRepository.saveAndFlush(technicalmanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the technicalmanagementWbs
        TechnicalmanagementWbs updatedTechnicalmanagementWbs = technicalmanagementWbsRepository
            .findById(technicalmanagementWbs.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedTechnicalmanagementWbs are not directly saved in db
        em.detach(updatedTechnicalmanagementWbs);
        updatedTechnicalmanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restTechnicalmanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTechnicalmanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedTechnicalmanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the TechnicalmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTechnicalmanagementWbsToMatchAllProperties(updatedTechnicalmanagementWbs);
    }

    @Test
    @Transactional
    void putNonExistingTechnicalmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technicalmanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTechnicalmanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, technicalmanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(technicalmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the TechnicalmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTechnicalmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technicalmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTechnicalmanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(technicalmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the TechnicalmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTechnicalmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technicalmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTechnicalmanagementWbsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(technicalmanagementWbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TechnicalmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTechnicalmanagementWbsWithPatch() throws Exception {
        // Initialize the database
        technicalmanagementWbsRepository.saveAndFlush(technicalmanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the technicalmanagementWbs using partial update
        TechnicalmanagementWbs partialUpdatedTechnicalmanagementWbs = new TechnicalmanagementWbs();
        partialUpdatedTechnicalmanagementWbs.setId(technicalmanagementWbs.getId());

        partialUpdatedTechnicalmanagementWbs.wbsspare2(UPDATED_WBSSPARE_2).wbsspare3(UPDATED_WBSSPARE_3).wbsspare5(UPDATED_WBSSPARE_5);

        restTechnicalmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTechnicalmanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTechnicalmanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the TechnicalmanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTechnicalmanagementWbsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTechnicalmanagementWbs, technicalmanagementWbs),
            getPersistedTechnicalmanagementWbs(technicalmanagementWbs)
        );
    }

    @Test
    @Transactional
    void fullUpdateTechnicalmanagementWbsWithPatch() throws Exception {
        // Initialize the database
        technicalmanagementWbsRepository.saveAndFlush(technicalmanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the technicalmanagementWbs using partial update
        TechnicalmanagementWbs partialUpdatedTechnicalmanagementWbs = new TechnicalmanagementWbs();
        partialUpdatedTechnicalmanagementWbs.setId(technicalmanagementWbs.getId());

        partialUpdatedTechnicalmanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restTechnicalmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTechnicalmanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTechnicalmanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the TechnicalmanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTechnicalmanagementWbsUpdatableFieldsEquals(
            partialUpdatedTechnicalmanagementWbs,
            getPersistedTechnicalmanagementWbs(partialUpdatedTechnicalmanagementWbs)
        );
    }

    @Test
    @Transactional
    void patchNonExistingTechnicalmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technicalmanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTechnicalmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, technicalmanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(technicalmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the TechnicalmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTechnicalmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technicalmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTechnicalmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(technicalmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the TechnicalmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTechnicalmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technicalmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTechnicalmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(technicalmanagementWbs))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TechnicalmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTechnicalmanagementWbs() throws Exception {
        // Initialize the database
        technicalmanagementWbsRepository.saveAndFlush(technicalmanagementWbs);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the technicalmanagementWbs
        restTechnicalmanagementWbsMockMvc
            .perform(delete(ENTITY_API_URL_ID, technicalmanagementWbs.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return technicalmanagementWbsRepository.count();
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

    protected TechnicalmanagementWbs getPersistedTechnicalmanagementWbs(TechnicalmanagementWbs technicalmanagementWbs) {
        return technicalmanagementWbsRepository.findById(technicalmanagementWbs.getId()).orElseThrow();
    }

    protected void assertPersistedTechnicalmanagementWbsToMatchAllProperties(TechnicalmanagementWbs expectedTechnicalmanagementWbs) {
        assertTechnicalmanagementWbsAllPropertiesEquals(
            expectedTechnicalmanagementWbs,
            getPersistedTechnicalmanagementWbs(expectedTechnicalmanagementWbs)
        );
    }

    protected void assertPersistedTechnicalmanagementWbsToMatchUpdatableProperties(TechnicalmanagementWbs expectedTechnicalmanagementWbs) {
        assertTechnicalmanagementWbsAllUpdatablePropertiesEquals(
            expectedTechnicalmanagementWbs,
            getPersistedTechnicalmanagementWbs(expectedTechnicalmanagementWbs)
        );
    }
}

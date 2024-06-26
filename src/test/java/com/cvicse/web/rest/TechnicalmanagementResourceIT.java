package com.cvicse.web.rest;

import static com.cvicse.domain.TechnicalmanagementAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Technicalmanagement;
import com.cvicse.repository.TechnicalmanagementRepository;
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
 * Integration tests for the {@link TechnicalmanagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TechnicalmanagementResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/technicalmanagements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TechnicalmanagementRepository technicalmanagementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTechnicalmanagementMockMvc;

    private Technicalmanagement technicalmanagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Technicalmanagement createEntity(EntityManager em) {
        Technicalmanagement technicalmanagement = new Technicalmanagement()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME);
        return technicalmanagement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Technicalmanagement createUpdatedEntity(EntityManager em) {
        Technicalmanagement technicalmanagement = new Technicalmanagement()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);
        return technicalmanagement;
    }

    @BeforeEach
    public void initTest() {
        technicalmanagement = createEntity(em);
    }

    @Test
    @Transactional
    void createTechnicalmanagement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Technicalmanagement
        var returnedTechnicalmanagement = om.readValue(
            restTechnicalmanagementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(technicalmanagement)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Technicalmanagement.class
        );

        // Validate the Technicalmanagement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertTechnicalmanagementUpdatableFieldsEquals(
            returnedTechnicalmanagement,
            getPersistedTechnicalmanagement(returnedTechnicalmanagement)
        );
    }

    @Test
    @Transactional
    void createTechnicalmanagementWithExistingId() throws Exception {
        // Create the Technicalmanagement with an existing ID
        technicalmanagement.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTechnicalmanagementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(technicalmanagement)))
            .andExpect(status().isBadRequest());

        // Validate the Technicalmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTechnicalmanagements() throws Exception {
        // Initialize the database
        technicalmanagementRepository.saveAndFlush(technicalmanagement);

        // Get all the technicalmanagementList
        restTechnicalmanagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(technicalmanagement.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())));
    }

    @Test
    @Transactional
    void getTechnicalmanagement() throws Exception {
        // Initialize the database
        technicalmanagementRepository.saveAndFlush(technicalmanagement);

        // Get the technicalmanagement
        restTechnicalmanagementMockMvc
            .perform(get(ENTITY_API_URL_ID, technicalmanagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(technicalmanagement.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTechnicalmanagement() throws Exception {
        // Get the technicalmanagement
        restTechnicalmanagementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTechnicalmanagement() throws Exception {
        // Initialize the database
        technicalmanagementRepository.saveAndFlush(technicalmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the technicalmanagement
        Technicalmanagement updatedTechnicalmanagement = technicalmanagementRepository.findById(technicalmanagement.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTechnicalmanagement are not directly saved in db
        em.detach(updatedTechnicalmanagement);
        updatedTechnicalmanagement
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);

        restTechnicalmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTechnicalmanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedTechnicalmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Technicalmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTechnicalmanagementToMatchAllProperties(updatedTechnicalmanagement);
    }

    @Test
    @Transactional
    void putNonExistingTechnicalmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technicalmanagement.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTechnicalmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, technicalmanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(technicalmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Technicalmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTechnicalmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technicalmanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTechnicalmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(technicalmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Technicalmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTechnicalmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technicalmanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTechnicalmanagementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(technicalmanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Technicalmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTechnicalmanagementWithPatch() throws Exception {
        // Initialize the database
        technicalmanagementRepository.saveAndFlush(technicalmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the technicalmanagement using partial update
        Technicalmanagement partialUpdatedTechnicalmanagement = new Technicalmanagement();
        partialUpdatedTechnicalmanagement.setId(technicalmanagement.getId());

        partialUpdatedTechnicalmanagement.description(UPDATED_DESCRIPTION).starttime(UPDATED_STARTTIME).endtime(UPDATED_ENDTIME);

        restTechnicalmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTechnicalmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTechnicalmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Technicalmanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTechnicalmanagementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTechnicalmanagement, technicalmanagement),
            getPersistedTechnicalmanagement(technicalmanagement)
        );
    }

    @Test
    @Transactional
    void fullUpdateTechnicalmanagementWithPatch() throws Exception {
        // Initialize the database
        technicalmanagementRepository.saveAndFlush(technicalmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the technicalmanagement using partial update
        Technicalmanagement partialUpdatedTechnicalmanagement = new Technicalmanagement();
        partialUpdatedTechnicalmanagement.setId(technicalmanagement.getId());

        partialUpdatedTechnicalmanagement
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);

        restTechnicalmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTechnicalmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTechnicalmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Technicalmanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTechnicalmanagementUpdatableFieldsEquals(
            partialUpdatedTechnicalmanagement,
            getPersistedTechnicalmanagement(partialUpdatedTechnicalmanagement)
        );
    }

    @Test
    @Transactional
    void patchNonExistingTechnicalmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technicalmanagement.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTechnicalmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, technicalmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(technicalmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Technicalmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTechnicalmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technicalmanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTechnicalmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(technicalmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Technicalmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTechnicalmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technicalmanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTechnicalmanagementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(technicalmanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Technicalmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTechnicalmanagement() throws Exception {
        // Initialize the database
        technicalmanagementRepository.saveAndFlush(technicalmanagement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the technicalmanagement
        restTechnicalmanagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, technicalmanagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return technicalmanagementRepository.count();
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

    protected Technicalmanagement getPersistedTechnicalmanagement(Technicalmanagement technicalmanagement) {
        return technicalmanagementRepository.findById(technicalmanagement.getId()).orElseThrow();
    }

    protected void assertPersistedTechnicalmanagementToMatchAllProperties(Technicalmanagement expectedTechnicalmanagement) {
        assertTechnicalmanagementAllPropertiesEquals(
            expectedTechnicalmanagement,
            getPersistedTechnicalmanagement(expectedTechnicalmanagement)
        );
    }

    protected void assertPersistedTechnicalmanagementToMatchUpdatableProperties(Technicalmanagement expectedTechnicalmanagement) {
        assertTechnicalmanagementAllUpdatablePropertiesEquals(
            expectedTechnicalmanagement,
            getPersistedTechnicalmanagement(expectedTechnicalmanagement)
        );
    }
}

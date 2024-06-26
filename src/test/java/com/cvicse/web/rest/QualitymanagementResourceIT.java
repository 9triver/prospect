package com.cvicse.web.rest;

import static com.cvicse.domain.QualitymanagementAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Qualitymanagement;
import com.cvicse.repository.QualitymanagementRepository;
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
 * Integration tests for the {@link QualitymanagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QualitymanagementResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/qualitymanagements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QualitymanagementRepository qualitymanagementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQualitymanagementMockMvc;

    private Qualitymanagement qualitymanagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Qualitymanagement createEntity(EntityManager em) {
        Qualitymanagement qualitymanagement = new Qualitymanagement()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME);
        return qualitymanagement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Qualitymanagement createUpdatedEntity(EntityManager em) {
        Qualitymanagement qualitymanagement = new Qualitymanagement()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);
        return qualitymanagement;
    }

    @BeforeEach
    public void initTest() {
        qualitymanagement = createEntity(em);
    }

    @Test
    @Transactional
    void createQualitymanagement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Qualitymanagement
        var returnedQualitymanagement = om.readValue(
            restQualitymanagementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualitymanagement)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Qualitymanagement.class
        );

        // Validate the Qualitymanagement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertQualitymanagementUpdatableFieldsEquals(returnedQualitymanagement, getPersistedQualitymanagement(returnedQualitymanagement));
    }

    @Test
    @Transactional
    void createQualitymanagementWithExistingId() throws Exception {
        // Create the Qualitymanagement with an existing ID
        qualitymanagement.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQualitymanagementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualitymanagement)))
            .andExpect(status().isBadRequest());

        // Validate the Qualitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQualitymanagements() throws Exception {
        // Initialize the database
        qualitymanagementRepository.saveAndFlush(qualitymanagement);

        // Get all the qualitymanagementList
        restQualitymanagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qualitymanagement.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())));
    }

    @Test
    @Transactional
    void getQualitymanagement() throws Exception {
        // Initialize the database
        qualitymanagementRepository.saveAndFlush(qualitymanagement);

        // Get the qualitymanagement
        restQualitymanagementMockMvc
            .perform(get(ENTITY_API_URL_ID, qualitymanagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(qualitymanagement.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingQualitymanagement() throws Exception {
        // Get the qualitymanagement
        restQualitymanagementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQualitymanagement() throws Exception {
        // Initialize the database
        qualitymanagementRepository.saveAndFlush(qualitymanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualitymanagement
        Qualitymanagement updatedQualitymanagement = qualitymanagementRepository.findById(qualitymanagement.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedQualitymanagement are not directly saved in db
        em.detach(updatedQualitymanagement);
        updatedQualitymanagement.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).starttime(UPDATED_STARTTIME).endtime(UPDATED_ENDTIME);

        restQualitymanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedQualitymanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedQualitymanagement))
            )
            .andExpect(status().isOk());

        // Validate the Qualitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQualitymanagementToMatchAllProperties(updatedQualitymanagement);
    }

    @Test
    @Transactional
    void putNonExistingQualitymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitymanagement.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualitymanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, qualitymanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualitymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQualitymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitymanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualitymanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualitymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQualitymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitymanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualitymanagementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualitymanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Qualitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQualitymanagementWithPatch() throws Exception {
        // Initialize the database
        qualitymanagementRepository.saveAndFlush(qualitymanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualitymanagement using partial update
        Qualitymanagement partialUpdatedQualitymanagement = new Qualitymanagement();
        partialUpdatedQualitymanagement.setId(qualitymanagement.getId());

        partialUpdatedQualitymanagement.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).starttime(UPDATED_STARTTIME);

        restQualitymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualitymanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualitymanagement))
            )
            .andExpect(status().isOk());

        // Validate the Qualitymanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualitymanagementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedQualitymanagement, qualitymanagement),
            getPersistedQualitymanagement(qualitymanagement)
        );
    }

    @Test
    @Transactional
    void fullUpdateQualitymanagementWithPatch() throws Exception {
        // Initialize the database
        qualitymanagementRepository.saveAndFlush(qualitymanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualitymanagement using partial update
        Qualitymanagement partialUpdatedQualitymanagement = new Qualitymanagement();
        partialUpdatedQualitymanagement.setId(qualitymanagement.getId());

        partialUpdatedQualitymanagement
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);

        restQualitymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualitymanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualitymanagement))
            )
            .andExpect(status().isOk());

        // Validate the Qualitymanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualitymanagementUpdatableFieldsEquals(
            partialUpdatedQualitymanagement,
            getPersistedQualitymanagement(partialUpdatedQualitymanagement)
        );
    }

    @Test
    @Transactional
    void patchNonExistingQualitymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitymanagement.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualitymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, qualitymanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualitymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQualitymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitymanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualitymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualitymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQualitymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitymanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualitymanagementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(qualitymanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Qualitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQualitymanagement() throws Exception {
        // Initialize the database
        qualitymanagementRepository.saveAndFlush(qualitymanagement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the qualitymanagement
        restQualitymanagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, qualitymanagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return qualitymanagementRepository.count();
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

    protected Qualitymanagement getPersistedQualitymanagement(Qualitymanagement qualitymanagement) {
        return qualitymanagementRepository.findById(qualitymanagement.getId()).orElseThrow();
    }

    protected void assertPersistedQualitymanagementToMatchAllProperties(Qualitymanagement expectedQualitymanagement) {
        assertQualitymanagementAllPropertiesEquals(expectedQualitymanagement, getPersistedQualitymanagement(expectedQualitymanagement));
    }

    protected void assertPersistedQualitymanagementToMatchUpdatableProperties(Qualitymanagement expectedQualitymanagement) {
        assertQualitymanagementAllUpdatablePropertiesEquals(
            expectedQualitymanagement,
            getPersistedQualitymanagement(expectedQualitymanagement)
        );
    }
}

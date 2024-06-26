package com.cvicse.web.rest;

import static com.cvicse.domain.OutsourcingmanagementAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Outsourcingmanagement;
import com.cvicse.repository.OutsourcingmanagementRepository;
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
 * Integration tests for the {@link OutsourcingmanagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OutsourcingmanagementResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/outsourcingmanagements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OutsourcingmanagementRepository outsourcingmanagementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOutsourcingmanagementMockMvc;

    private Outsourcingmanagement outsourcingmanagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Outsourcingmanagement createEntity(EntityManager em) {
        Outsourcingmanagement outsourcingmanagement = new Outsourcingmanagement()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME);
        return outsourcingmanagement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Outsourcingmanagement createUpdatedEntity(EntityManager em) {
        Outsourcingmanagement outsourcingmanagement = new Outsourcingmanagement()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);
        return outsourcingmanagement;
    }

    @BeforeEach
    public void initTest() {
        outsourcingmanagement = createEntity(em);
    }

    @Test
    @Transactional
    void createOutsourcingmanagement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Outsourcingmanagement
        var returnedOutsourcingmanagement = om.readValue(
            restOutsourcingmanagementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingmanagement)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Outsourcingmanagement.class
        );

        // Validate the Outsourcingmanagement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOutsourcingmanagementUpdatableFieldsEquals(
            returnedOutsourcingmanagement,
            getPersistedOutsourcingmanagement(returnedOutsourcingmanagement)
        );
    }

    @Test
    @Transactional
    void createOutsourcingmanagementWithExistingId() throws Exception {
        // Create the Outsourcingmanagement with an existing ID
        outsourcingmanagement.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOutsourcingmanagementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingmanagement)))
            .andExpect(status().isBadRequest());

        // Validate the Outsourcingmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOutsourcingmanagements() throws Exception {
        // Initialize the database
        outsourcingmanagementRepository.saveAndFlush(outsourcingmanagement);

        // Get all the outsourcingmanagementList
        restOutsourcingmanagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(outsourcingmanagement.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())));
    }

    @Test
    @Transactional
    void getOutsourcingmanagement() throws Exception {
        // Initialize the database
        outsourcingmanagementRepository.saveAndFlush(outsourcingmanagement);

        // Get the outsourcingmanagement
        restOutsourcingmanagementMockMvc
            .perform(get(ENTITY_API_URL_ID, outsourcingmanagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(outsourcingmanagement.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingOutsourcingmanagement() throws Exception {
        // Get the outsourcingmanagement
        restOutsourcingmanagementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOutsourcingmanagement() throws Exception {
        // Initialize the database
        outsourcingmanagementRepository.saveAndFlush(outsourcingmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingmanagement
        Outsourcingmanagement updatedOutsourcingmanagement = outsourcingmanagementRepository
            .findById(outsourcingmanagement.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOutsourcingmanagement are not directly saved in db
        em.detach(updatedOutsourcingmanagement);
        updatedOutsourcingmanagement
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);

        restOutsourcingmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOutsourcingmanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOutsourcingmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Outsourcingmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOutsourcingmanagementToMatchAllProperties(updatedOutsourcingmanagement);
    }

    @Test
    @Transactional
    void putNonExistingOutsourcingmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmanagement.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOutsourcingmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, outsourcingmanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(outsourcingmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Outsourcingmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOutsourcingmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(outsourcingmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Outsourcingmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOutsourcingmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingmanagementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingmanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Outsourcingmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOutsourcingmanagementWithPatch() throws Exception {
        // Initialize the database
        outsourcingmanagementRepository.saveAndFlush(outsourcingmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingmanagement using partial update
        Outsourcingmanagement partialUpdatedOutsourcingmanagement = new Outsourcingmanagement();
        partialUpdatedOutsourcingmanagement.setId(outsourcingmanagement.getId());

        partialUpdatedOutsourcingmanagement.name(UPDATED_NAME).starttime(UPDATED_STARTTIME).endtime(UPDATED_ENDTIME);

        restOutsourcingmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOutsourcingmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOutsourcingmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Outsourcingmanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOutsourcingmanagementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOutsourcingmanagement, outsourcingmanagement),
            getPersistedOutsourcingmanagement(outsourcingmanagement)
        );
    }

    @Test
    @Transactional
    void fullUpdateOutsourcingmanagementWithPatch() throws Exception {
        // Initialize the database
        outsourcingmanagementRepository.saveAndFlush(outsourcingmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingmanagement using partial update
        Outsourcingmanagement partialUpdatedOutsourcingmanagement = new Outsourcingmanagement();
        partialUpdatedOutsourcingmanagement.setId(outsourcingmanagement.getId());

        partialUpdatedOutsourcingmanagement
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);

        restOutsourcingmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOutsourcingmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOutsourcingmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Outsourcingmanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOutsourcingmanagementUpdatableFieldsEquals(
            partialUpdatedOutsourcingmanagement,
            getPersistedOutsourcingmanagement(partialUpdatedOutsourcingmanagement)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOutsourcingmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmanagement.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOutsourcingmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, outsourcingmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(outsourcingmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Outsourcingmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOutsourcingmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(outsourcingmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Outsourcingmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOutsourcingmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingmanagementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(outsourcingmanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Outsourcingmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOutsourcingmanagement() throws Exception {
        // Initialize the database
        outsourcingmanagementRepository.saveAndFlush(outsourcingmanagement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the outsourcingmanagement
        restOutsourcingmanagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, outsourcingmanagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return outsourcingmanagementRepository.count();
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

    protected Outsourcingmanagement getPersistedOutsourcingmanagement(Outsourcingmanagement outsourcingmanagement) {
        return outsourcingmanagementRepository.findById(outsourcingmanagement.getId()).orElseThrow();
    }

    protected void assertPersistedOutsourcingmanagementToMatchAllProperties(Outsourcingmanagement expectedOutsourcingmanagement) {
        assertOutsourcingmanagementAllPropertiesEquals(
            expectedOutsourcingmanagement,
            getPersistedOutsourcingmanagement(expectedOutsourcingmanagement)
        );
    }

    protected void assertPersistedOutsourcingmanagementToMatchUpdatableProperties(Outsourcingmanagement expectedOutsourcingmanagement) {
        assertOutsourcingmanagementAllUpdatablePropertiesEquals(
            expectedOutsourcingmanagement,
            getPersistedOutsourcingmanagement(expectedOutsourcingmanagement)
        );
    }
}

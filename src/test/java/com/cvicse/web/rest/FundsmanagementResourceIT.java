package com.cvicse.web.rest;

import static com.cvicse.domain.FundsmanagementAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Fundsmanagement;
import com.cvicse.repository.FundsmanagementRepository;
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
 * Integration tests for the {@link FundsmanagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FundsmanagementResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/fundsmanagements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FundsmanagementRepository fundsmanagementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFundsmanagementMockMvc;

    private Fundsmanagement fundsmanagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fundsmanagement createEntity(EntityManager em) {
        Fundsmanagement fundsmanagement = new Fundsmanagement()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME);
        return fundsmanagement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fundsmanagement createUpdatedEntity(EntityManager em) {
        Fundsmanagement fundsmanagement = new Fundsmanagement()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);
        return fundsmanagement;
    }

    @BeforeEach
    public void initTest() {
        fundsmanagement = createEntity(em);
    }

    @Test
    @Transactional
    void createFundsmanagement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Fundsmanagement
        var returnedFundsmanagement = om.readValue(
            restFundsmanagementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fundsmanagement)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Fundsmanagement.class
        );

        // Validate the Fundsmanagement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertFundsmanagementUpdatableFieldsEquals(returnedFundsmanagement, getPersistedFundsmanagement(returnedFundsmanagement));
    }

    @Test
    @Transactional
    void createFundsmanagementWithExistingId() throws Exception {
        // Create the Fundsmanagement with an existing ID
        fundsmanagement.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFundsmanagementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fundsmanagement)))
            .andExpect(status().isBadRequest());

        // Validate the Fundsmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFundsmanagements() throws Exception {
        // Initialize the database
        fundsmanagementRepository.saveAndFlush(fundsmanagement);

        // Get all the fundsmanagementList
        restFundsmanagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fundsmanagement.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())));
    }

    @Test
    @Transactional
    void getFundsmanagement() throws Exception {
        // Initialize the database
        fundsmanagementRepository.saveAndFlush(fundsmanagement);

        // Get the fundsmanagement
        restFundsmanagementMockMvc
            .perform(get(ENTITY_API_URL_ID, fundsmanagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fundsmanagement.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingFundsmanagement() throws Exception {
        // Get the fundsmanagement
        restFundsmanagementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFundsmanagement() throws Exception {
        // Initialize the database
        fundsmanagementRepository.saveAndFlush(fundsmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fundsmanagement
        Fundsmanagement updatedFundsmanagement = fundsmanagementRepository.findById(fundsmanagement.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFundsmanagement are not directly saved in db
        em.detach(updatedFundsmanagement);
        updatedFundsmanagement.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).starttime(UPDATED_STARTTIME).endtime(UPDATED_ENDTIME);

        restFundsmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFundsmanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedFundsmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Fundsmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFundsmanagementToMatchAllProperties(updatedFundsmanagement);
    }

    @Test
    @Transactional
    void putNonExistingFundsmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsmanagement.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFundsmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fundsmanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fundsmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fundsmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFundsmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsmanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fundsmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fundsmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFundsmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsmanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsmanagementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fundsmanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Fundsmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFundsmanagementWithPatch() throws Exception {
        // Initialize the database
        fundsmanagementRepository.saveAndFlush(fundsmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fundsmanagement using partial update
        Fundsmanagement partialUpdatedFundsmanagement = new Fundsmanagement();
        partialUpdatedFundsmanagement.setId(fundsmanagement.getId());

        partialUpdatedFundsmanagement.description(UPDATED_DESCRIPTION);

        restFundsmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFundsmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFundsmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Fundsmanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFundsmanagementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFundsmanagement, fundsmanagement),
            getPersistedFundsmanagement(fundsmanagement)
        );
    }

    @Test
    @Transactional
    void fullUpdateFundsmanagementWithPatch() throws Exception {
        // Initialize the database
        fundsmanagementRepository.saveAndFlush(fundsmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fundsmanagement using partial update
        Fundsmanagement partialUpdatedFundsmanagement = new Fundsmanagement();
        partialUpdatedFundsmanagement.setId(fundsmanagement.getId());

        partialUpdatedFundsmanagement
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);

        restFundsmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFundsmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFundsmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Fundsmanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFundsmanagementUpdatableFieldsEquals(
            partialUpdatedFundsmanagement,
            getPersistedFundsmanagement(partialUpdatedFundsmanagement)
        );
    }

    @Test
    @Transactional
    void patchNonExistingFundsmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsmanagement.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFundsmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fundsmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fundsmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fundsmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFundsmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsmanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fundsmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fundsmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFundsmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsmanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsmanagementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(fundsmanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Fundsmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFundsmanagement() throws Exception {
        // Initialize the database
        fundsmanagementRepository.saveAndFlush(fundsmanagement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the fundsmanagement
        restFundsmanagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, fundsmanagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return fundsmanagementRepository.count();
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

    protected Fundsmanagement getPersistedFundsmanagement(Fundsmanagement fundsmanagement) {
        return fundsmanagementRepository.findById(fundsmanagement.getId()).orElseThrow();
    }

    protected void assertPersistedFundsmanagementToMatchAllProperties(Fundsmanagement expectedFundsmanagement) {
        assertFundsmanagementAllPropertiesEquals(expectedFundsmanagement, getPersistedFundsmanagement(expectedFundsmanagement));
    }

    protected void assertPersistedFundsmanagementToMatchUpdatableProperties(Fundsmanagement expectedFundsmanagement) {
        assertFundsmanagementAllUpdatablePropertiesEquals(expectedFundsmanagement, getPersistedFundsmanagement(expectedFundsmanagement));
    }
}

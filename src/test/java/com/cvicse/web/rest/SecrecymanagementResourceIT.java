package com.cvicse.web.rest;

import static com.cvicse.domain.SecrecymanagementAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Secrecymanagement;
import com.cvicse.repository.SecrecymanagementRepository;
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
 * Integration tests for the {@link SecrecymanagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SecrecymanagementResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/secrecymanagements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SecrecymanagementRepository secrecymanagementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSecrecymanagementMockMvc;

    private Secrecymanagement secrecymanagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Secrecymanagement createEntity(EntityManager em) {
        Secrecymanagement secrecymanagement = new Secrecymanagement()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME);
        return secrecymanagement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Secrecymanagement createUpdatedEntity(EntityManager em) {
        Secrecymanagement secrecymanagement = new Secrecymanagement()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);
        return secrecymanagement;
    }

    @BeforeEach
    public void initTest() {
        secrecymanagement = createEntity(em);
    }

    @Test
    @Transactional
    void createSecrecymanagement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Secrecymanagement
        var returnedSecrecymanagement = om.readValue(
            restSecrecymanagementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(secrecymanagement)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Secrecymanagement.class
        );

        // Validate the Secrecymanagement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertSecrecymanagementUpdatableFieldsEquals(returnedSecrecymanagement, getPersistedSecrecymanagement(returnedSecrecymanagement));
    }

    @Test
    @Transactional
    void createSecrecymanagementWithExistingId() throws Exception {
        // Create the Secrecymanagement with an existing ID
        secrecymanagement.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSecrecymanagementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(secrecymanagement)))
            .andExpect(status().isBadRequest());

        // Validate the Secrecymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSecrecymanagements() throws Exception {
        // Initialize the database
        secrecymanagementRepository.saveAndFlush(secrecymanagement);

        // Get all the secrecymanagementList
        restSecrecymanagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(secrecymanagement.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())));
    }

    @Test
    @Transactional
    void getSecrecymanagement() throws Exception {
        // Initialize the database
        secrecymanagementRepository.saveAndFlush(secrecymanagement);

        // Get the secrecymanagement
        restSecrecymanagementMockMvc
            .perform(get(ENTITY_API_URL_ID, secrecymanagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(secrecymanagement.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingSecrecymanagement() throws Exception {
        // Get the secrecymanagement
        restSecrecymanagementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSecrecymanagement() throws Exception {
        // Initialize the database
        secrecymanagementRepository.saveAndFlush(secrecymanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the secrecymanagement
        Secrecymanagement updatedSecrecymanagement = secrecymanagementRepository.findById(secrecymanagement.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedSecrecymanagement are not directly saved in db
        em.detach(updatedSecrecymanagement);
        updatedSecrecymanagement.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).starttime(UPDATED_STARTTIME).endtime(UPDATED_ENDTIME);

        restSecrecymanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSecrecymanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedSecrecymanagement))
            )
            .andExpect(status().isOk());

        // Validate the Secrecymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSecrecymanagementToMatchAllProperties(updatedSecrecymanagement);
    }

    @Test
    @Transactional
    void putNonExistingSecrecymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecymanagement.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecrecymanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, secrecymanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(secrecymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Secrecymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSecrecymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecymanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecrecymanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(secrecymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Secrecymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSecrecymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecymanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecrecymanagementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(secrecymanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Secrecymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSecrecymanagementWithPatch() throws Exception {
        // Initialize the database
        secrecymanagementRepository.saveAndFlush(secrecymanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the secrecymanagement using partial update
        Secrecymanagement partialUpdatedSecrecymanagement = new Secrecymanagement();
        partialUpdatedSecrecymanagement.setId(secrecymanagement.getId());

        partialUpdatedSecrecymanagement.description(UPDATED_DESCRIPTION);

        restSecrecymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSecrecymanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSecrecymanagement))
            )
            .andExpect(status().isOk());

        // Validate the Secrecymanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSecrecymanagementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSecrecymanagement, secrecymanagement),
            getPersistedSecrecymanagement(secrecymanagement)
        );
    }

    @Test
    @Transactional
    void fullUpdateSecrecymanagementWithPatch() throws Exception {
        // Initialize the database
        secrecymanagementRepository.saveAndFlush(secrecymanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the secrecymanagement using partial update
        Secrecymanagement partialUpdatedSecrecymanagement = new Secrecymanagement();
        partialUpdatedSecrecymanagement.setId(secrecymanagement.getId());

        partialUpdatedSecrecymanagement
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);

        restSecrecymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSecrecymanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSecrecymanagement))
            )
            .andExpect(status().isOk());

        // Validate the Secrecymanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSecrecymanagementUpdatableFieldsEquals(
            partialUpdatedSecrecymanagement,
            getPersistedSecrecymanagement(partialUpdatedSecrecymanagement)
        );
    }

    @Test
    @Transactional
    void patchNonExistingSecrecymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecymanagement.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecrecymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, secrecymanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(secrecymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Secrecymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSecrecymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecymanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecrecymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(secrecymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Secrecymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSecrecymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecymanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecrecymanagementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(secrecymanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Secrecymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSecrecymanagement() throws Exception {
        // Initialize the database
        secrecymanagementRepository.saveAndFlush(secrecymanagement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the secrecymanagement
        restSecrecymanagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, secrecymanagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return secrecymanagementRepository.count();
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

    protected Secrecymanagement getPersistedSecrecymanagement(Secrecymanagement secrecymanagement) {
        return secrecymanagementRepository.findById(secrecymanagement.getId()).orElseThrow();
    }

    protected void assertPersistedSecrecymanagementToMatchAllProperties(Secrecymanagement expectedSecrecymanagement) {
        assertSecrecymanagementAllPropertiesEquals(expectedSecrecymanagement, getPersistedSecrecymanagement(expectedSecrecymanagement));
    }

    protected void assertPersistedSecrecymanagementToMatchUpdatableProperties(Secrecymanagement expectedSecrecymanagement) {
        assertSecrecymanagementAllUpdatablePropertiesEquals(
            expectedSecrecymanagement,
            getPersistedSecrecymanagement(expectedSecrecymanagement)
        );
    }
}

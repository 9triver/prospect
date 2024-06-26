package com.cvicse.web.rest;

import static com.cvicse.domain.SecuritymanagementAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Securitymanagement;
import com.cvicse.repository.SecuritymanagementRepository;
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
 * Integration tests for the {@link SecuritymanagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SecuritymanagementResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/securitymanagements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SecuritymanagementRepository securitymanagementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSecuritymanagementMockMvc;

    private Securitymanagement securitymanagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Securitymanagement createEntity(EntityManager em) {
        Securitymanagement securitymanagement = new Securitymanagement()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME);
        return securitymanagement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Securitymanagement createUpdatedEntity(EntityManager em) {
        Securitymanagement securitymanagement = new Securitymanagement()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);
        return securitymanagement;
    }

    @BeforeEach
    public void initTest() {
        securitymanagement = createEntity(em);
    }

    @Test
    @Transactional
    void createSecuritymanagement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Securitymanagement
        var returnedSecuritymanagement = om.readValue(
            restSecuritymanagementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(securitymanagement)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Securitymanagement.class
        );

        // Validate the Securitymanagement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertSecuritymanagementUpdatableFieldsEquals(
            returnedSecuritymanagement,
            getPersistedSecuritymanagement(returnedSecuritymanagement)
        );
    }

    @Test
    @Transactional
    void createSecuritymanagementWithExistingId() throws Exception {
        // Create the Securitymanagement with an existing ID
        securitymanagement.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSecuritymanagementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(securitymanagement)))
            .andExpect(status().isBadRequest());

        // Validate the Securitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSecuritymanagements() throws Exception {
        // Initialize the database
        securitymanagementRepository.saveAndFlush(securitymanagement);

        // Get all the securitymanagementList
        restSecuritymanagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(securitymanagement.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())));
    }

    @Test
    @Transactional
    void getSecuritymanagement() throws Exception {
        // Initialize the database
        securitymanagementRepository.saveAndFlush(securitymanagement);

        // Get the securitymanagement
        restSecuritymanagementMockMvc
            .perform(get(ENTITY_API_URL_ID, securitymanagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(securitymanagement.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingSecuritymanagement() throws Exception {
        // Get the securitymanagement
        restSecuritymanagementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSecuritymanagement() throws Exception {
        // Initialize the database
        securitymanagementRepository.saveAndFlush(securitymanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the securitymanagement
        Securitymanagement updatedSecuritymanagement = securitymanagementRepository.findById(securitymanagement.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedSecuritymanagement are not directly saved in db
        em.detach(updatedSecuritymanagement);
        updatedSecuritymanagement.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).starttime(UPDATED_STARTTIME).endtime(UPDATED_ENDTIME);

        restSecuritymanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSecuritymanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedSecuritymanagement))
            )
            .andExpect(status().isOk());

        // Validate the Securitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSecuritymanagementToMatchAllProperties(updatedSecuritymanagement);
    }

    @Test
    @Transactional
    void putNonExistingSecuritymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        securitymanagement.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecuritymanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, securitymanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(securitymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Securitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSecuritymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        securitymanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecuritymanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(securitymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Securitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSecuritymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        securitymanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecuritymanagementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(securitymanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Securitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSecuritymanagementWithPatch() throws Exception {
        // Initialize the database
        securitymanagementRepository.saveAndFlush(securitymanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the securitymanagement using partial update
        Securitymanagement partialUpdatedSecuritymanagement = new Securitymanagement();
        partialUpdatedSecuritymanagement.setId(securitymanagement.getId());

        partialUpdatedSecuritymanagement.starttime(UPDATED_STARTTIME);

        restSecuritymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSecuritymanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSecuritymanagement))
            )
            .andExpect(status().isOk());

        // Validate the Securitymanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSecuritymanagementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSecuritymanagement, securitymanagement),
            getPersistedSecuritymanagement(securitymanagement)
        );
    }

    @Test
    @Transactional
    void fullUpdateSecuritymanagementWithPatch() throws Exception {
        // Initialize the database
        securitymanagementRepository.saveAndFlush(securitymanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the securitymanagement using partial update
        Securitymanagement partialUpdatedSecuritymanagement = new Securitymanagement();
        partialUpdatedSecuritymanagement.setId(securitymanagement.getId());

        partialUpdatedSecuritymanagement
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);

        restSecuritymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSecuritymanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSecuritymanagement))
            )
            .andExpect(status().isOk());

        // Validate the Securitymanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSecuritymanagementUpdatableFieldsEquals(
            partialUpdatedSecuritymanagement,
            getPersistedSecuritymanagement(partialUpdatedSecuritymanagement)
        );
    }

    @Test
    @Transactional
    void patchNonExistingSecuritymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        securitymanagement.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecuritymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, securitymanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(securitymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Securitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSecuritymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        securitymanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecuritymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(securitymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Securitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSecuritymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        securitymanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecuritymanagementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(securitymanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Securitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSecuritymanagement() throws Exception {
        // Initialize the database
        securitymanagementRepository.saveAndFlush(securitymanagement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the securitymanagement
        restSecuritymanagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, securitymanagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return securitymanagementRepository.count();
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

    protected Securitymanagement getPersistedSecuritymanagement(Securitymanagement securitymanagement) {
        return securitymanagementRepository.findById(securitymanagement.getId()).orElseThrow();
    }

    protected void assertPersistedSecuritymanagementToMatchAllProperties(Securitymanagement expectedSecuritymanagement) {
        assertSecuritymanagementAllPropertiesEquals(expectedSecuritymanagement, getPersistedSecuritymanagement(expectedSecuritymanagement));
    }

    protected void assertPersistedSecuritymanagementToMatchUpdatableProperties(Securitymanagement expectedSecuritymanagement) {
        assertSecuritymanagementAllUpdatablePropertiesEquals(
            expectedSecuritymanagement,
            getPersistedSecuritymanagement(expectedSecuritymanagement)
        );
    }
}

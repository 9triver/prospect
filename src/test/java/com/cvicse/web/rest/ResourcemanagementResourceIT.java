package com.cvicse.web.rest;

import static com.cvicse.domain.ResourcemanagementAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Resourcemanagement;
import com.cvicse.repository.ResourcemanagementRepository;
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
 * Integration tests for the {@link ResourcemanagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResourcemanagementResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/resourcemanagements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ResourcemanagementRepository resourcemanagementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResourcemanagementMockMvc;

    private Resourcemanagement resourcemanagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Resourcemanagement createEntity(EntityManager em) {
        Resourcemanagement resourcemanagement = new Resourcemanagement()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME);
        return resourcemanagement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Resourcemanagement createUpdatedEntity(EntityManager em) {
        Resourcemanagement resourcemanagement = new Resourcemanagement()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);
        return resourcemanagement;
    }

    @BeforeEach
    public void initTest() {
        resourcemanagement = createEntity(em);
    }

    @Test
    @Transactional
    void createResourcemanagement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Resourcemanagement
        var returnedResourcemanagement = om.readValue(
            restResourcemanagementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resourcemanagement)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Resourcemanagement.class
        );

        // Validate the Resourcemanagement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertResourcemanagementUpdatableFieldsEquals(
            returnedResourcemanagement,
            getPersistedResourcemanagement(returnedResourcemanagement)
        );
    }

    @Test
    @Transactional
    void createResourcemanagementWithExistingId() throws Exception {
        // Create the Resourcemanagement with an existing ID
        resourcemanagement.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restResourcemanagementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resourcemanagement)))
            .andExpect(status().isBadRequest());

        // Validate the Resourcemanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllResourcemanagements() throws Exception {
        // Initialize the database
        resourcemanagementRepository.saveAndFlush(resourcemanagement);

        // Get all the resourcemanagementList
        restResourcemanagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resourcemanagement.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())));
    }

    @Test
    @Transactional
    void getResourcemanagement() throws Exception {
        // Initialize the database
        resourcemanagementRepository.saveAndFlush(resourcemanagement);

        // Get the resourcemanagement
        restResourcemanagementMockMvc
            .perform(get(ENTITY_API_URL_ID, resourcemanagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resourcemanagement.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingResourcemanagement() throws Exception {
        // Get the resourcemanagement
        restResourcemanagementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingResourcemanagement() throws Exception {
        // Initialize the database
        resourcemanagementRepository.saveAndFlush(resourcemanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resourcemanagement
        Resourcemanagement updatedResourcemanagement = resourcemanagementRepository.findById(resourcemanagement.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedResourcemanagement are not directly saved in db
        em.detach(updatedResourcemanagement);
        updatedResourcemanagement.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).starttime(UPDATED_STARTTIME).endtime(UPDATED_ENDTIME);

        restResourcemanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedResourcemanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedResourcemanagement))
            )
            .andExpect(status().isOk());

        // Validate the Resourcemanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedResourcemanagementToMatchAllProperties(updatedResourcemanagement);
    }

    @Test
    @Transactional
    void putNonExistingResourcemanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resourcemanagement.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourcemanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resourcemanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(resourcemanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Resourcemanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchResourcemanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resourcemanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourcemanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(resourcemanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Resourcemanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamResourcemanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resourcemanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourcemanagementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resourcemanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Resourcemanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateResourcemanagementWithPatch() throws Exception {
        // Initialize the database
        resourcemanagementRepository.saveAndFlush(resourcemanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resourcemanagement using partial update
        Resourcemanagement partialUpdatedResourcemanagement = new Resourcemanagement();
        partialUpdatedResourcemanagement.setId(resourcemanagement.getId());

        partialUpdatedResourcemanagement.name(UPDATED_NAME).description(UPDATED_DESCRIPTION).endtime(UPDATED_ENDTIME);

        restResourcemanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResourcemanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedResourcemanagement))
            )
            .andExpect(status().isOk());

        // Validate the Resourcemanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertResourcemanagementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedResourcemanagement, resourcemanagement),
            getPersistedResourcemanagement(resourcemanagement)
        );
    }

    @Test
    @Transactional
    void fullUpdateResourcemanagementWithPatch() throws Exception {
        // Initialize the database
        resourcemanagementRepository.saveAndFlush(resourcemanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resourcemanagement using partial update
        Resourcemanagement partialUpdatedResourcemanagement = new Resourcemanagement();
        partialUpdatedResourcemanagement.setId(resourcemanagement.getId());

        partialUpdatedResourcemanagement
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME);

        restResourcemanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResourcemanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedResourcemanagement))
            )
            .andExpect(status().isOk());

        // Validate the Resourcemanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertResourcemanagementUpdatableFieldsEquals(
            partialUpdatedResourcemanagement,
            getPersistedResourcemanagement(partialUpdatedResourcemanagement)
        );
    }

    @Test
    @Transactional
    void patchNonExistingResourcemanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resourcemanagement.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourcemanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, resourcemanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(resourcemanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Resourcemanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchResourcemanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resourcemanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourcemanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(resourcemanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Resourcemanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamResourcemanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resourcemanagement.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourcemanagementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(resourcemanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Resourcemanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteResourcemanagement() throws Exception {
        // Initialize the database
        resourcemanagementRepository.saveAndFlush(resourcemanagement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the resourcemanagement
        restResourcemanagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, resourcemanagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return resourcemanagementRepository.count();
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

    protected Resourcemanagement getPersistedResourcemanagement(Resourcemanagement resourcemanagement) {
        return resourcemanagementRepository.findById(resourcemanagement.getId()).orElseThrow();
    }

    protected void assertPersistedResourcemanagementToMatchAllProperties(Resourcemanagement expectedResourcemanagement) {
        assertResourcemanagementAllPropertiesEquals(expectedResourcemanagement, getPersistedResourcemanagement(expectedResourcemanagement));
    }

    protected void assertPersistedResourcemanagementToMatchUpdatableProperties(Resourcemanagement expectedResourcemanagement) {
        assertResourcemanagementAllUpdatablePropertiesEquals(
            expectedResourcemanagement,
            getPersistedResourcemanagement(expectedResourcemanagement)
        );
    }
}

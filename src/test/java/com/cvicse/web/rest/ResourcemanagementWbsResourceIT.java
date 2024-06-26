package com.cvicse.web.rest;

import static com.cvicse.domain.ResourcemanagementWbsAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.ResourcemanagementWbs;
import com.cvicse.repository.ResourcemanagementWbsRepository;
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
 * Integration tests for the {@link ResourcemanagementWbsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResourcemanagementWbsResourceIT {

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

    private static final String ENTITY_API_URL = "/api/resourcemanagement-wbs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ResourcemanagementWbsRepository resourcemanagementWbsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResourcemanagementWbsMockMvc;

    private ResourcemanagementWbs resourcemanagementWbs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourcemanagementWbs createEntity(EntityManager em) {
        ResourcemanagementWbs resourcemanagementWbs = new ResourcemanagementWbs()
            .wbsspare1(DEFAULT_WBSSPARE_1)
            .wbsspare2(DEFAULT_WBSSPARE_2)
            .wbsspare3(DEFAULT_WBSSPARE_3)
            .wbsspare4(DEFAULT_WBSSPARE_4)
            .wbsspare5(DEFAULT_WBSSPARE_5);
        return resourcemanagementWbs;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourcemanagementWbs createUpdatedEntity(EntityManager em) {
        ResourcemanagementWbs resourcemanagementWbs = new ResourcemanagementWbs()
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);
        return resourcemanagementWbs;
    }

    @BeforeEach
    public void initTest() {
        resourcemanagementWbs = createEntity(em);
    }

    @Test
    @Transactional
    void createResourcemanagementWbs() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ResourcemanagementWbs
        var returnedResourcemanagementWbs = om.readValue(
            restResourcemanagementWbsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resourcemanagementWbs)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ResourcemanagementWbs.class
        );

        // Validate the ResourcemanagementWbs in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertResourcemanagementWbsUpdatableFieldsEquals(
            returnedResourcemanagementWbs,
            getPersistedResourcemanagementWbs(returnedResourcemanagementWbs)
        );
    }

    @Test
    @Transactional
    void createResourcemanagementWbsWithExistingId() throws Exception {
        // Create the ResourcemanagementWbs with an existing ID
        resourcemanagementWbs.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restResourcemanagementWbsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resourcemanagementWbs)))
            .andExpect(status().isBadRequest());

        // Validate the ResourcemanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllResourcemanagementWbs() throws Exception {
        // Initialize the database
        resourcemanagementWbsRepository.saveAndFlush(resourcemanagementWbs);

        // Get all the resourcemanagementWbsList
        restResourcemanagementWbsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resourcemanagementWbs.getId())))
            .andExpect(jsonPath("$.[*].wbsspare1").value(hasItem(DEFAULT_WBSSPARE_1)))
            .andExpect(jsonPath("$.[*].wbsspare2").value(hasItem(DEFAULT_WBSSPARE_2)))
            .andExpect(jsonPath("$.[*].wbsspare3").value(hasItem(DEFAULT_WBSSPARE_3)))
            .andExpect(jsonPath("$.[*].wbsspare4").value(hasItem(DEFAULT_WBSSPARE_4)))
            .andExpect(jsonPath("$.[*].wbsspare5").value(hasItem(DEFAULT_WBSSPARE_5)));
    }

    @Test
    @Transactional
    void getResourcemanagementWbs() throws Exception {
        // Initialize the database
        resourcemanagementWbsRepository.saveAndFlush(resourcemanagementWbs);

        // Get the resourcemanagementWbs
        restResourcemanagementWbsMockMvc
            .perform(get(ENTITY_API_URL_ID, resourcemanagementWbs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resourcemanagementWbs.getId()))
            .andExpect(jsonPath("$.wbsspare1").value(DEFAULT_WBSSPARE_1))
            .andExpect(jsonPath("$.wbsspare2").value(DEFAULT_WBSSPARE_2))
            .andExpect(jsonPath("$.wbsspare3").value(DEFAULT_WBSSPARE_3))
            .andExpect(jsonPath("$.wbsspare4").value(DEFAULT_WBSSPARE_4))
            .andExpect(jsonPath("$.wbsspare5").value(DEFAULT_WBSSPARE_5));
    }

    @Test
    @Transactional
    void getNonExistingResourcemanagementWbs() throws Exception {
        // Get the resourcemanagementWbs
        restResourcemanagementWbsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingResourcemanagementWbs() throws Exception {
        // Initialize the database
        resourcemanagementWbsRepository.saveAndFlush(resourcemanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resourcemanagementWbs
        ResourcemanagementWbs updatedResourcemanagementWbs = resourcemanagementWbsRepository
            .findById(resourcemanagementWbs.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedResourcemanagementWbs are not directly saved in db
        em.detach(updatedResourcemanagementWbs);
        updatedResourcemanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restResourcemanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedResourcemanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedResourcemanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the ResourcemanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedResourcemanagementWbsToMatchAllProperties(updatedResourcemanagementWbs);
    }

    @Test
    @Transactional
    void putNonExistingResourcemanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resourcemanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourcemanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resourcemanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(resourcemanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourcemanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchResourcemanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resourcemanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourcemanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(resourcemanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourcemanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamResourcemanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resourcemanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourcemanagementWbsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resourcemanagementWbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResourcemanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateResourcemanagementWbsWithPatch() throws Exception {
        // Initialize the database
        resourcemanagementWbsRepository.saveAndFlush(resourcemanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resourcemanagementWbs using partial update
        ResourcemanagementWbs partialUpdatedResourcemanagementWbs = new ResourcemanagementWbs();
        partialUpdatedResourcemanagementWbs.setId(resourcemanagementWbs.getId());

        partialUpdatedResourcemanagementWbs.wbsspare1(UPDATED_WBSSPARE_1).wbsspare2(UPDATED_WBSSPARE_2).wbsspare4(UPDATED_WBSSPARE_4);

        restResourcemanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResourcemanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedResourcemanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the ResourcemanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertResourcemanagementWbsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedResourcemanagementWbs, resourcemanagementWbs),
            getPersistedResourcemanagementWbs(resourcemanagementWbs)
        );
    }

    @Test
    @Transactional
    void fullUpdateResourcemanagementWbsWithPatch() throws Exception {
        // Initialize the database
        resourcemanagementWbsRepository.saveAndFlush(resourcemanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resourcemanagementWbs using partial update
        ResourcemanagementWbs partialUpdatedResourcemanagementWbs = new ResourcemanagementWbs();
        partialUpdatedResourcemanagementWbs.setId(resourcemanagementWbs.getId());

        partialUpdatedResourcemanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restResourcemanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResourcemanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedResourcemanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the ResourcemanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertResourcemanagementWbsUpdatableFieldsEquals(
            partialUpdatedResourcemanagementWbs,
            getPersistedResourcemanagementWbs(partialUpdatedResourcemanagementWbs)
        );
    }

    @Test
    @Transactional
    void patchNonExistingResourcemanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resourcemanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourcemanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, resourcemanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(resourcemanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourcemanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchResourcemanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resourcemanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourcemanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(resourcemanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the ResourcemanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamResourcemanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resourcemanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourcemanagementWbsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(resourcemanagementWbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ResourcemanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteResourcemanagementWbs() throws Exception {
        // Initialize the database
        resourcemanagementWbsRepository.saveAndFlush(resourcemanagementWbs);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the resourcemanagementWbs
        restResourcemanagementWbsMockMvc
            .perform(delete(ENTITY_API_URL_ID, resourcemanagementWbs.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return resourcemanagementWbsRepository.count();
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

    protected ResourcemanagementWbs getPersistedResourcemanagementWbs(ResourcemanagementWbs resourcemanagementWbs) {
        return resourcemanagementWbsRepository.findById(resourcemanagementWbs.getId()).orElseThrow();
    }

    protected void assertPersistedResourcemanagementWbsToMatchAllProperties(ResourcemanagementWbs expectedResourcemanagementWbs) {
        assertResourcemanagementWbsAllPropertiesEquals(
            expectedResourcemanagementWbs,
            getPersistedResourcemanagementWbs(expectedResourcemanagementWbs)
        );
    }

    protected void assertPersistedResourcemanagementWbsToMatchUpdatableProperties(ResourcemanagementWbs expectedResourcemanagementWbs) {
        assertResourcemanagementWbsAllUpdatablePropertiesEquals(
            expectedResourcemanagementWbs,
            getPersistedResourcemanagementWbs(expectedResourcemanagementWbs)
        );
    }
}

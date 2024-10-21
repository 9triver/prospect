package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.FrontlineAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.Frontline;
import com.cvicse.jy1.repository.FrontlineRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link FrontlineResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FrontlineResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/frontlines";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FrontlineRepository frontlineRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFrontlineMockMvc;

    private Frontline frontline;

    private Frontline insertedFrontline;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Frontline createEntity(EntityManager em) {
        Frontline frontline = new Frontline().name(DEFAULT_NAME).description(DEFAULT_DESCRIPTION);
        return frontline;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Frontline createUpdatedEntity(EntityManager em) {
        Frontline frontline = new Frontline().name(UPDATED_NAME).description(UPDATED_DESCRIPTION);
        return frontline;
    }

    @BeforeEach
    public void initTest() {
        frontline = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedFrontline != null) {
            frontlineRepository.delete(insertedFrontline);
            insertedFrontline = null;
        }
    }

    @Test
    @Transactional
    void createFrontline() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Frontline
        var returnedFrontline = om.readValue(
            restFrontlineMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(frontline)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Frontline.class
        );

        // Validate the Frontline in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertFrontlineUpdatableFieldsEquals(returnedFrontline, getPersistedFrontline(returnedFrontline));

        insertedFrontline = returnedFrontline;
    }

    @Test
    @Transactional
    void createFrontlineWithExistingId() throws Exception {
        // Create the Frontline with an existing ID
        frontline.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFrontlineMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(frontline)))
            .andExpect(status().isBadRequest());

        // Validate the Frontline in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFrontlines() throws Exception {
        // Initialize the database
        insertedFrontline = frontlineRepository.saveAndFlush(frontline);

        // Get all the frontlineList
        restFrontlineMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(frontline.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    void getFrontline() throws Exception {
        // Initialize the database
        insertedFrontline = frontlineRepository.saveAndFlush(frontline);

        // Get the frontline
        restFrontlineMockMvc
            .perform(get(ENTITY_API_URL_ID, frontline.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(frontline.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    void getNonExistingFrontline() throws Exception {
        // Get the frontline
        restFrontlineMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFrontline() throws Exception {
        // Initialize the database
        insertedFrontline = frontlineRepository.saveAndFlush(frontline);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the frontline
        Frontline updatedFrontline = frontlineRepository.findById(frontline.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFrontline are not directly saved in db
        em.detach(updatedFrontline);
        updatedFrontline.name(UPDATED_NAME).description(UPDATED_DESCRIPTION);

        restFrontlineMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFrontline.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedFrontline))
            )
            .andExpect(status().isOk());

        // Validate the Frontline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFrontlineToMatchAllProperties(updatedFrontline);
    }

    @Test
    @Transactional
    void putNonExistingFrontline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        frontline.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFrontlineMockMvc
            .perform(
                put(ENTITY_API_URL_ID, frontline.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(frontline))
            )
            .andExpect(status().isBadRequest());

        // Validate the Frontline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFrontline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        frontline.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFrontlineMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(frontline))
            )
            .andExpect(status().isBadRequest());

        // Validate the Frontline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFrontline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        frontline.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFrontlineMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(frontline)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Frontline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFrontlineWithPatch() throws Exception {
        // Initialize the database
        insertedFrontline = frontlineRepository.saveAndFlush(frontline);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the frontline using partial update
        Frontline partialUpdatedFrontline = new Frontline();
        partialUpdatedFrontline.setId(frontline.getId());

        restFrontlineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFrontline.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFrontline))
            )
            .andExpect(status().isOk());

        // Validate the Frontline in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFrontlineUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFrontline, frontline),
            getPersistedFrontline(frontline)
        );
    }

    @Test
    @Transactional
    void fullUpdateFrontlineWithPatch() throws Exception {
        // Initialize the database
        insertedFrontline = frontlineRepository.saveAndFlush(frontline);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the frontline using partial update
        Frontline partialUpdatedFrontline = new Frontline();
        partialUpdatedFrontline.setId(frontline.getId());

        partialUpdatedFrontline.name(UPDATED_NAME).description(UPDATED_DESCRIPTION);

        restFrontlineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFrontline.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFrontline))
            )
            .andExpect(status().isOk());

        // Validate the Frontline in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFrontlineUpdatableFieldsEquals(partialUpdatedFrontline, getPersistedFrontline(partialUpdatedFrontline));
    }

    @Test
    @Transactional
    void patchNonExistingFrontline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        frontline.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFrontlineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, frontline.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(frontline))
            )
            .andExpect(status().isBadRequest());

        // Validate the Frontline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFrontline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        frontline.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFrontlineMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(frontline))
            )
            .andExpect(status().isBadRequest());

        // Validate the Frontline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFrontline() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        frontline.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFrontlineMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(frontline)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Frontline in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFrontline() throws Exception {
        // Initialize the database
        insertedFrontline = frontlineRepository.saveAndFlush(frontline);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the frontline
        restFrontlineMockMvc
            .perform(delete(ENTITY_API_URL_ID, frontline.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return frontlineRepository.count();
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

    protected Frontline getPersistedFrontline(Frontline frontline) {
        return frontlineRepository.findById(frontline.getId()).orElseThrow();
    }

    protected void assertPersistedFrontlineToMatchAllProperties(Frontline expectedFrontline) {
        assertFrontlineAllPropertiesEquals(expectedFrontline, getPersistedFrontline(expectedFrontline));
    }

    protected void assertPersistedFrontlineToMatchUpdatableProperties(Frontline expectedFrontline) {
        assertFrontlineAllUpdatablePropertiesEquals(expectedFrontline, getPersistedFrontline(expectedFrontline));
    }
}

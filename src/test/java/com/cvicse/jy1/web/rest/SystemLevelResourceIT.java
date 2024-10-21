package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.SystemLevelAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.SystemLevel;
import com.cvicse.jy1.repository.SystemLevelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
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
 * Integration tests for the {@link SystemLevelResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SystemLevelResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/system-levels";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SystemLevelRepository systemLevelRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSystemLevelMockMvc;

    private SystemLevel systemLevel;

    private SystemLevel insertedSystemLevel;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SystemLevel createEntity(EntityManager em) {
        SystemLevel systemLevel = new SystemLevel().name(DEFAULT_NAME);
        return systemLevel;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SystemLevel createUpdatedEntity(EntityManager em) {
        SystemLevel systemLevel = new SystemLevel().name(UPDATED_NAME);
        return systemLevel;
    }

    @BeforeEach
    public void initTest() {
        systemLevel = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedSystemLevel != null) {
            systemLevelRepository.delete(insertedSystemLevel);
            insertedSystemLevel = null;
        }
    }

    @Test
    @Transactional
    void createSystemLevel() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the SystemLevel
        var returnedSystemLevel = om.readValue(
            restSystemLevelMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(systemLevel)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            SystemLevel.class
        );

        // Validate the SystemLevel in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertSystemLevelUpdatableFieldsEquals(returnedSystemLevel, getPersistedSystemLevel(returnedSystemLevel));

        insertedSystemLevel = returnedSystemLevel;
    }

    @Test
    @Transactional
    void createSystemLevelWithExistingId() throws Exception {
        // Create the SystemLevel with an existing ID
        systemLevel.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSystemLevelMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(systemLevel)))
            .andExpect(status().isBadRequest());

        // Validate the SystemLevel in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSystemLevels() throws Exception {
        // Initialize the database
        insertedSystemLevel = systemLevelRepository.saveAndFlush(systemLevel);

        // Get all the systemLevelList
        restSystemLevelMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(systemLevel.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getSystemLevel() throws Exception {
        // Initialize the database
        insertedSystemLevel = systemLevelRepository.saveAndFlush(systemLevel);

        // Get the systemLevel
        restSystemLevelMockMvc
            .perform(get(ENTITY_API_URL_ID, systemLevel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(systemLevel.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getNonExistingSystemLevel() throws Exception {
        // Get the systemLevel
        restSystemLevelMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSystemLevel() throws Exception {
        // Initialize the database
        insertedSystemLevel = systemLevelRepository.saveAndFlush(systemLevel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the systemLevel
        SystemLevel updatedSystemLevel = systemLevelRepository.findById(systemLevel.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedSystemLevel are not directly saved in db
        em.detach(updatedSystemLevel);
        updatedSystemLevel.name(UPDATED_NAME);

        restSystemLevelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSystemLevel.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedSystemLevel))
            )
            .andExpect(status().isOk());

        // Validate the SystemLevel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSystemLevelToMatchAllProperties(updatedSystemLevel);
    }

    @Test
    @Transactional
    void putNonExistingSystemLevel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        systemLevel.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSystemLevelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, systemLevel.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(systemLevel))
            )
            .andExpect(status().isBadRequest());

        // Validate the SystemLevel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSystemLevel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        systemLevel.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSystemLevelMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(systemLevel))
            )
            .andExpect(status().isBadRequest());

        // Validate the SystemLevel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSystemLevel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        systemLevel.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSystemLevelMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(systemLevel)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SystemLevel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSystemLevelWithPatch() throws Exception {
        // Initialize the database
        insertedSystemLevel = systemLevelRepository.saveAndFlush(systemLevel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the systemLevel using partial update
        SystemLevel partialUpdatedSystemLevel = new SystemLevel();
        partialUpdatedSystemLevel.setId(systemLevel.getId());

        partialUpdatedSystemLevel.name(UPDATED_NAME);

        restSystemLevelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSystemLevel.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSystemLevel))
            )
            .andExpect(status().isOk());

        // Validate the SystemLevel in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSystemLevelUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSystemLevel, systemLevel),
            getPersistedSystemLevel(systemLevel)
        );
    }

    @Test
    @Transactional
    void fullUpdateSystemLevelWithPatch() throws Exception {
        // Initialize the database
        insertedSystemLevel = systemLevelRepository.saveAndFlush(systemLevel);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the systemLevel using partial update
        SystemLevel partialUpdatedSystemLevel = new SystemLevel();
        partialUpdatedSystemLevel.setId(systemLevel.getId());

        partialUpdatedSystemLevel.name(UPDATED_NAME);

        restSystemLevelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSystemLevel.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSystemLevel))
            )
            .andExpect(status().isOk());

        // Validate the SystemLevel in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSystemLevelUpdatableFieldsEquals(partialUpdatedSystemLevel, getPersistedSystemLevel(partialUpdatedSystemLevel));
    }

    @Test
    @Transactional
    void patchNonExistingSystemLevel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        systemLevel.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSystemLevelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, systemLevel.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(systemLevel))
            )
            .andExpect(status().isBadRequest());

        // Validate the SystemLevel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSystemLevel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        systemLevel.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSystemLevelMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(systemLevel))
            )
            .andExpect(status().isBadRequest());

        // Validate the SystemLevel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSystemLevel() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        systemLevel.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSystemLevelMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(systemLevel)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SystemLevel in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSystemLevel() throws Exception {
        // Initialize the database
        insertedSystemLevel = systemLevelRepository.saveAndFlush(systemLevel);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the systemLevel
        restSystemLevelMockMvc
            .perform(delete(ENTITY_API_URL_ID, systemLevel.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return systemLevelRepository.count();
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

    protected SystemLevel getPersistedSystemLevel(SystemLevel systemLevel) {
        return systemLevelRepository.findById(systemLevel.getId()).orElseThrow();
    }

    protected void assertPersistedSystemLevelToMatchAllProperties(SystemLevel expectedSystemLevel) {
        assertSystemLevelAllPropertiesEquals(expectedSystemLevel, getPersistedSystemLevel(expectedSystemLevel));
    }

    protected void assertPersistedSystemLevelToMatchUpdatableProperties(SystemLevel expectedSystemLevel) {
        assertSystemLevelAllUpdatablePropertiesEquals(expectedSystemLevel, getPersistedSystemLevel(expectedSystemLevel));
    }
}

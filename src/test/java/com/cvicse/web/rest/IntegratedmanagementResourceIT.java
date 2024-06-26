package com.cvicse.web.rest;

import static com.cvicse.domain.IntegratedmanagementAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Integratedmanagement;
import com.cvicse.repository.IntegratedmanagementRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link IntegratedmanagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class IntegratedmanagementResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/integratedmanagements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private IntegratedmanagementRepository integratedmanagementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restIntegratedmanagementMockMvc;

    private Integratedmanagement integratedmanagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Integratedmanagement createEntity(EntityManager em) {
        Integratedmanagement integratedmanagement = new Integratedmanagement().name(DEFAULT_NAME).description(DEFAULT_DESCRIPTION);
        return integratedmanagement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Integratedmanagement createUpdatedEntity(EntityManager em) {
        Integratedmanagement integratedmanagement = new Integratedmanagement().name(UPDATED_NAME).description(UPDATED_DESCRIPTION);
        return integratedmanagement;
    }

    @BeforeEach
    public void initTest() {
        integratedmanagement = createEntity(em);
    }

    @Test
    @Transactional
    void createIntegratedmanagement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Integratedmanagement
        var returnedIntegratedmanagement = om.readValue(
            restIntegratedmanagementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(integratedmanagement)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Integratedmanagement.class
        );

        // Validate the Integratedmanagement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertIntegratedmanagementUpdatableFieldsEquals(
            returnedIntegratedmanagement,
            getPersistedIntegratedmanagement(returnedIntegratedmanagement)
        );
    }

    @Test
    @Transactional
    void createIntegratedmanagementWithExistingId() throws Exception {
        // Create the Integratedmanagement with an existing ID
        integratedmanagement.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restIntegratedmanagementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(integratedmanagement)))
            .andExpect(status().isBadRequest());

        // Validate the Integratedmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllIntegratedmanagements() throws Exception {
        // Initialize the database
        integratedmanagementRepository.saveAndFlush(integratedmanagement);

        // Get all the integratedmanagementList
        restIntegratedmanagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(integratedmanagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    void getIntegratedmanagement() throws Exception {
        // Initialize the database
        integratedmanagementRepository.saveAndFlush(integratedmanagement);

        // Get the integratedmanagement
        restIntegratedmanagementMockMvc
            .perform(get(ENTITY_API_URL_ID, integratedmanagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(integratedmanagement.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    void getNonExistingIntegratedmanagement() throws Exception {
        // Get the integratedmanagement
        restIntegratedmanagementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingIntegratedmanagement() throws Exception {
        // Initialize the database
        integratedmanagementRepository.saveAndFlush(integratedmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the integratedmanagement
        Integratedmanagement updatedIntegratedmanagement = integratedmanagementRepository
            .findById(integratedmanagement.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedIntegratedmanagement are not directly saved in db
        em.detach(updatedIntegratedmanagement);
        updatedIntegratedmanagement.name(UPDATED_NAME).description(UPDATED_DESCRIPTION);

        restIntegratedmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedIntegratedmanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedIntegratedmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Integratedmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedIntegratedmanagementToMatchAllProperties(updatedIntegratedmanagement);
    }

    @Test
    @Transactional
    void putNonExistingIntegratedmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        integratedmanagement.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIntegratedmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, integratedmanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(integratedmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Integratedmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchIntegratedmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        integratedmanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIntegratedmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(integratedmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Integratedmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamIntegratedmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        integratedmanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIntegratedmanagementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(integratedmanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Integratedmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateIntegratedmanagementWithPatch() throws Exception {
        // Initialize the database
        integratedmanagementRepository.saveAndFlush(integratedmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the integratedmanagement using partial update
        Integratedmanagement partialUpdatedIntegratedmanagement = new Integratedmanagement();
        partialUpdatedIntegratedmanagement.setId(integratedmanagement.getId());

        restIntegratedmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedIntegratedmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedIntegratedmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Integratedmanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertIntegratedmanagementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedIntegratedmanagement, integratedmanagement),
            getPersistedIntegratedmanagement(integratedmanagement)
        );
    }

    @Test
    @Transactional
    void fullUpdateIntegratedmanagementWithPatch() throws Exception {
        // Initialize the database
        integratedmanagementRepository.saveAndFlush(integratedmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the integratedmanagement using partial update
        Integratedmanagement partialUpdatedIntegratedmanagement = new Integratedmanagement();
        partialUpdatedIntegratedmanagement.setId(integratedmanagement.getId());

        partialUpdatedIntegratedmanagement.name(UPDATED_NAME).description(UPDATED_DESCRIPTION);

        restIntegratedmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedIntegratedmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedIntegratedmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Integratedmanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertIntegratedmanagementUpdatableFieldsEquals(
            partialUpdatedIntegratedmanagement,
            getPersistedIntegratedmanagement(partialUpdatedIntegratedmanagement)
        );
    }

    @Test
    @Transactional
    void patchNonExistingIntegratedmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        integratedmanagement.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIntegratedmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, integratedmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(integratedmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Integratedmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchIntegratedmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        integratedmanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIntegratedmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(integratedmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Integratedmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamIntegratedmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        integratedmanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIntegratedmanagementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(integratedmanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Integratedmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteIntegratedmanagement() throws Exception {
        // Initialize the database
        integratedmanagementRepository.saveAndFlush(integratedmanagement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the integratedmanagement
        restIntegratedmanagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, integratedmanagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return integratedmanagementRepository.count();
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

    protected Integratedmanagement getPersistedIntegratedmanagement(Integratedmanagement integratedmanagement) {
        return integratedmanagementRepository.findById(integratedmanagement.getId()).orElseThrow();
    }

    protected void assertPersistedIntegratedmanagementToMatchAllProperties(Integratedmanagement expectedIntegratedmanagement) {
        assertIntegratedmanagementAllPropertiesEquals(
            expectedIntegratedmanagement,
            getPersistedIntegratedmanagement(expectedIntegratedmanagement)
        );
    }

    protected void assertPersistedIntegratedmanagementToMatchUpdatableProperties(Integratedmanagement expectedIntegratedmanagement) {
        assertIntegratedmanagementAllUpdatablePropertiesEquals(
            expectedIntegratedmanagement,
            getPersistedIntegratedmanagement(expectedIntegratedmanagement)
        );
    }
}

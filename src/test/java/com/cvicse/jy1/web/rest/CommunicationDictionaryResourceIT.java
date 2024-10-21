package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.CommunicationDictionaryAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.CommunicationDictionary;
import com.cvicse.jy1.repository.CommunicationDictionaryRepository;
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
 * Integration tests for the {@link CommunicationDictionaryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CommunicationDictionaryResourceIT {

    private static final String DEFAULT_PARTIESNAME = "AAAAAAAAAA";
    private static final String UPDATED_PARTIESNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PARTIESTYPE = "AAAAAAAAAA";
    private static final String UPDATED_PARTIESTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_PARTIESDUTY = "AAAAAAAAAA";
    private static final String UPDATED_PARTIESDUTY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/communication-dictionaries";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CommunicationDictionaryRepository communicationDictionaryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommunicationDictionaryMockMvc;

    private CommunicationDictionary communicationDictionary;

    private CommunicationDictionary insertedCommunicationDictionary;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommunicationDictionary createEntity(EntityManager em) {
        CommunicationDictionary communicationDictionary = new CommunicationDictionary()
            .partiesname(DEFAULT_PARTIESNAME)
            .partiestype(DEFAULT_PARTIESTYPE)
            .partiesduty(DEFAULT_PARTIESDUTY);
        return communicationDictionary;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommunicationDictionary createUpdatedEntity(EntityManager em) {
        CommunicationDictionary communicationDictionary = new CommunicationDictionary()
            .partiesname(UPDATED_PARTIESNAME)
            .partiestype(UPDATED_PARTIESTYPE)
            .partiesduty(UPDATED_PARTIESDUTY);
        return communicationDictionary;
    }

    @BeforeEach
    public void initTest() {
        communicationDictionary = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedCommunicationDictionary != null) {
            communicationDictionaryRepository.delete(insertedCommunicationDictionary);
            insertedCommunicationDictionary = null;
        }
    }

    @Test
    @Transactional
    void createCommunicationDictionary() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CommunicationDictionary
        var returnedCommunicationDictionary = om.readValue(
            restCommunicationDictionaryMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(communicationDictionary))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CommunicationDictionary.class
        );

        // Validate the CommunicationDictionary in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCommunicationDictionaryUpdatableFieldsEquals(
            returnedCommunicationDictionary,
            getPersistedCommunicationDictionary(returnedCommunicationDictionary)
        );

        insertedCommunicationDictionary = returnedCommunicationDictionary;
    }

    @Test
    @Transactional
    void createCommunicationDictionaryWithExistingId() throws Exception {
        // Create the CommunicationDictionary with an existing ID
        communicationDictionary.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommunicationDictionaryMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(communicationDictionary)))
            .andExpect(status().isBadRequest());

        // Validate the CommunicationDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCommunicationDictionaries() throws Exception {
        // Initialize the database
        insertedCommunicationDictionary = communicationDictionaryRepository.saveAndFlush(communicationDictionary);

        // Get all the communicationDictionaryList
        restCommunicationDictionaryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(communicationDictionary.getId().intValue())))
            .andExpect(jsonPath("$.[*].partiesname").value(hasItem(DEFAULT_PARTIESNAME)))
            .andExpect(jsonPath("$.[*].partiestype").value(hasItem(DEFAULT_PARTIESTYPE)))
            .andExpect(jsonPath("$.[*].partiesduty").value(hasItem(DEFAULT_PARTIESDUTY)));
    }

    @Test
    @Transactional
    void getCommunicationDictionary() throws Exception {
        // Initialize the database
        insertedCommunicationDictionary = communicationDictionaryRepository.saveAndFlush(communicationDictionary);

        // Get the communicationDictionary
        restCommunicationDictionaryMockMvc
            .perform(get(ENTITY_API_URL_ID, communicationDictionary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(communicationDictionary.getId().intValue()))
            .andExpect(jsonPath("$.partiesname").value(DEFAULT_PARTIESNAME))
            .andExpect(jsonPath("$.partiestype").value(DEFAULT_PARTIESTYPE))
            .andExpect(jsonPath("$.partiesduty").value(DEFAULT_PARTIESDUTY));
    }

    @Test
    @Transactional
    void getNonExistingCommunicationDictionary() throws Exception {
        // Get the communicationDictionary
        restCommunicationDictionaryMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCommunicationDictionary() throws Exception {
        // Initialize the database
        insertedCommunicationDictionary = communicationDictionaryRepository.saveAndFlush(communicationDictionary);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the communicationDictionary
        CommunicationDictionary updatedCommunicationDictionary = communicationDictionaryRepository
            .findById(communicationDictionary.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedCommunicationDictionary are not directly saved in db
        em.detach(updatedCommunicationDictionary);
        updatedCommunicationDictionary.partiesname(UPDATED_PARTIESNAME).partiestype(UPDATED_PARTIESTYPE).partiesduty(UPDATED_PARTIESDUTY);

        restCommunicationDictionaryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCommunicationDictionary.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCommunicationDictionary))
            )
            .andExpect(status().isOk());

        // Validate the CommunicationDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCommunicationDictionaryToMatchAllProperties(updatedCommunicationDictionary);
    }

    @Test
    @Transactional
    void putNonExistingCommunicationDictionary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationDictionary.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommunicationDictionaryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, communicationDictionary.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(communicationDictionary))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommunicationDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCommunicationDictionary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationDictionary.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationDictionaryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(communicationDictionary))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommunicationDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCommunicationDictionary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationDictionary.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationDictionaryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(communicationDictionary)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CommunicationDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCommunicationDictionaryWithPatch() throws Exception {
        // Initialize the database
        insertedCommunicationDictionary = communicationDictionaryRepository.saveAndFlush(communicationDictionary);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the communicationDictionary using partial update
        CommunicationDictionary partialUpdatedCommunicationDictionary = new CommunicationDictionary();
        partialUpdatedCommunicationDictionary.setId(communicationDictionary.getId());

        partialUpdatedCommunicationDictionary.partiestype(UPDATED_PARTIESTYPE).partiesduty(UPDATED_PARTIESDUTY);

        restCommunicationDictionaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCommunicationDictionary.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCommunicationDictionary))
            )
            .andExpect(status().isOk());

        // Validate the CommunicationDictionary in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCommunicationDictionaryUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCommunicationDictionary, communicationDictionary),
            getPersistedCommunicationDictionary(communicationDictionary)
        );
    }

    @Test
    @Transactional
    void fullUpdateCommunicationDictionaryWithPatch() throws Exception {
        // Initialize the database
        insertedCommunicationDictionary = communicationDictionaryRepository.saveAndFlush(communicationDictionary);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the communicationDictionary using partial update
        CommunicationDictionary partialUpdatedCommunicationDictionary = new CommunicationDictionary();
        partialUpdatedCommunicationDictionary.setId(communicationDictionary.getId());

        partialUpdatedCommunicationDictionary
            .partiesname(UPDATED_PARTIESNAME)
            .partiestype(UPDATED_PARTIESTYPE)
            .partiesduty(UPDATED_PARTIESDUTY);

        restCommunicationDictionaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCommunicationDictionary.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCommunicationDictionary))
            )
            .andExpect(status().isOk());

        // Validate the CommunicationDictionary in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCommunicationDictionaryUpdatableFieldsEquals(
            partialUpdatedCommunicationDictionary,
            getPersistedCommunicationDictionary(partialUpdatedCommunicationDictionary)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCommunicationDictionary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationDictionary.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommunicationDictionaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, communicationDictionary.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(communicationDictionary))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommunicationDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCommunicationDictionary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationDictionary.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationDictionaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(communicationDictionary))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommunicationDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCommunicationDictionary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationDictionary.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationDictionaryMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(communicationDictionary))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CommunicationDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCommunicationDictionary() throws Exception {
        // Initialize the database
        insertedCommunicationDictionary = communicationDictionaryRepository.saveAndFlush(communicationDictionary);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the communicationDictionary
        restCommunicationDictionaryMockMvc
            .perform(delete(ENTITY_API_URL_ID, communicationDictionary.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return communicationDictionaryRepository.count();
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

    protected CommunicationDictionary getPersistedCommunicationDictionary(CommunicationDictionary communicationDictionary) {
        return communicationDictionaryRepository.findById(communicationDictionary.getId()).orElseThrow();
    }

    protected void assertPersistedCommunicationDictionaryToMatchAllProperties(CommunicationDictionary expectedCommunicationDictionary) {
        assertCommunicationDictionaryAllPropertiesEquals(
            expectedCommunicationDictionary,
            getPersistedCommunicationDictionary(expectedCommunicationDictionary)
        );
    }

    protected void assertPersistedCommunicationDictionaryToMatchUpdatableProperties(
        CommunicationDictionary expectedCommunicationDictionary
    ) {
        assertCommunicationDictionaryAllUpdatablePropertiesEquals(
            expectedCommunicationDictionary,
            getPersistedCommunicationDictionary(expectedCommunicationDictionary)
        );
    }
}

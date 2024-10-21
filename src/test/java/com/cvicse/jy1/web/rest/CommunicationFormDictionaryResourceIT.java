package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.CommunicationFormDictionaryAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.CommunicationFormDictionary;
import com.cvicse.jy1.repository.CommunicationFormDictionaryRepository;
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
 * Integration tests for the {@link CommunicationFormDictionaryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CommunicationFormDictionaryResourceIT {

    private static final String DEFAULT_COMMUNICATIONFORMNAME = "AAAAAAAAAA";
    private static final String UPDATED_COMMUNICATIONFORMNAME = "BBBBBBBBBB";

    private static final String DEFAULT_COMMUNICATIONFORMTYPE = "AAAAAAAAAA";
    private static final String UPDATED_COMMUNICATIONFORMTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/communication-form-dictionaries";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CommunicationFormDictionaryRepository communicationFormDictionaryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommunicationFormDictionaryMockMvc;

    private CommunicationFormDictionary communicationFormDictionary;

    private CommunicationFormDictionary insertedCommunicationFormDictionary;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommunicationFormDictionary createEntity(EntityManager em) {
        CommunicationFormDictionary communicationFormDictionary = new CommunicationFormDictionary()
            .communicationformname(DEFAULT_COMMUNICATIONFORMNAME)
            .communicationformtype(DEFAULT_COMMUNICATIONFORMTYPE)
            .status(DEFAULT_STATUS);
        return communicationFormDictionary;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommunicationFormDictionary createUpdatedEntity(EntityManager em) {
        CommunicationFormDictionary communicationFormDictionary = new CommunicationFormDictionary()
            .communicationformname(UPDATED_COMMUNICATIONFORMNAME)
            .communicationformtype(UPDATED_COMMUNICATIONFORMTYPE)
            .status(UPDATED_STATUS);
        return communicationFormDictionary;
    }

    @BeforeEach
    public void initTest() {
        communicationFormDictionary = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedCommunicationFormDictionary != null) {
            communicationFormDictionaryRepository.delete(insertedCommunicationFormDictionary);
            insertedCommunicationFormDictionary = null;
        }
    }

    @Test
    @Transactional
    void createCommunicationFormDictionary() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CommunicationFormDictionary
        var returnedCommunicationFormDictionary = om.readValue(
            restCommunicationFormDictionaryMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(communicationFormDictionary))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CommunicationFormDictionary.class
        );

        // Validate the CommunicationFormDictionary in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCommunicationFormDictionaryUpdatableFieldsEquals(
            returnedCommunicationFormDictionary,
            getPersistedCommunicationFormDictionary(returnedCommunicationFormDictionary)
        );

        insertedCommunicationFormDictionary = returnedCommunicationFormDictionary;
    }

    @Test
    @Transactional
    void createCommunicationFormDictionaryWithExistingId() throws Exception {
        // Create the CommunicationFormDictionary with an existing ID
        communicationFormDictionary.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommunicationFormDictionaryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(communicationFormDictionary))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommunicationFormDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCommunicationFormDictionaries() throws Exception {
        // Initialize the database
        insertedCommunicationFormDictionary = communicationFormDictionaryRepository.saveAndFlush(communicationFormDictionary);

        // Get all the communicationFormDictionaryList
        restCommunicationFormDictionaryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(communicationFormDictionary.getId().intValue())))
            .andExpect(jsonPath("$.[*].communicationformname").value(hasItem(DEFAULT_COMMUNICATIONFORMNAME)))
            .andExpect(jsonPath("$.[*].communicationformtype").value(hasItem(DEFAULT_COMMUNICATIONFORMTYPE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    void getCommunicationFormDictionary() throws Exception {
        // Initialize the database
        insertedCommunicationFormDictionary = communicationFormDictionaryRepository.saveAndFlush(communicationFormDictionary);

        // Get the communicationFormDictionary
        restCommunicationFormDictionaryMockMvc
            .perform(get(ENTITY_API_URL_ID, communicationFormDictionary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(communicationFormDictionary.getId().intValue()))
            .andExpect(jsonPath("$.communicationformname").value(DEFAULT_COMMUNICATIONFORMNAME))
            .andExpect(jsonPath("$.communicationformtype").value(DEFAULT_COMMUNICATIONFORMTYPE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingCommunicationFormDictionary() throws Exception {
        // Get the communicationFormDictionary
        restCommunicationFormDictionaryMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCommunicationFormDictionary() throws Exception {
        // Initialize the database
        insertedCommunicationFormDictionary = communicationFormDictionaryRepository.saveAndFlush(communicationFormDictionary);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the communicationFormDictionary
        CommunicationFormDictionary updatedCommunicationFormDictionary = communicationFormDictionaryRepository
            .findById(communicationFormDictionary.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedCommunicationFormDictionary are not directly saved in db
        em.detach(updatedCommunicationFormDictionary);
        updatedCommunicationFormDictionary
            .communicationformname(UPDATED_COMMUNICATIONFORMNAME)
            .communicationformtype(UPDATED_COMMUNICATIONFORMTYPE)
            .status(UPDATED_STATUS);

        restCommunicationFormDictionaryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCommunicationFormDictionary.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCommunicationFormDictionary))
            )
            .andExpect(status().isOk());

        // Validate the CommunicationFormDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCommunicationFormDictionaryToMatchAllProperties(updatedCommunicationFormDictionary);
    }

    @Test
    @Transactional
    void putNonExistingCommunicationFormDictionary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationFormDictionary.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommunicationFormDictionaryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, communicationFormDictionary.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(communicationFormDictionary))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommunicationFormDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCommunicationFormDictionary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationFormDictionary.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationFormDictionaryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(communicationFormDictionary))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommunicationFormDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCommunicationFormDictionary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationFormDictionary.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationFormDictionaryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(communicationFormDictionary)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CommunicationFormDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCommunicationFormDictionaryWithPatch() throws Exception {
        // Initialize the database
        insertedCommunicationFormDictionary = communicationFormDictionaryRepository.saveAndFlush(communicationFormDictionary);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the communicationFormDictionary using partial update
        CommunicationFormDictionary partialUpdatedCommunicationFormDictionary = new CommunicationFormDictionary();
        partialUpdatedCommunicationFormDictionary.setId(communicationFormDictionary.getId());

        restCommunicationFormDictionaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCommunicationFormDictionary.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCommunicationFormDictionary))
            )
            .andExpect(status().isOk());

        // Validate the CommunicationFormDictionary in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCommunicationFormDictionaryUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCommunicationFormDictionary, communicationFormDictionary),
            getPersistedCommunicationFormDictionary(communicationFormDictionary)
        );
    }

    @Test
    @Transactional
    void fullUpdateCommunicationFormDictionaryWithPatch() throws Exception {
        // Initialize the database
        insertedCommunicationFormDictionary = communicationFormDictionaryRepository.saveAndFlush(communicationFormDictionary);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the communicationFormDictionary using partial update
        CommunicationFormDictionary partialUpdatedCommunicationFormDictionary = new CommunicationFormDictionary();
        partialUpdatedCommunicationFormDictionary.setId(communicationFormDictionary.getId());

        partialUpdatedCommunicationFormDictionary
            .communicationformname(UPDATED_COMMUNICATIONFORMNAME)
            .communicationformtype(UPDATED_COMMUNICATIONFORMTYPE)
            .status(UPDATED_STATUS);

        restCommunicationFormDictionaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCommunicationFormDictionary.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCommunicationFormDictionary))
            )
            .andExpect(status().isOk());

        // Validate the CommunicationFormDictionary in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCommunicationFormDictionaryUpdatableFieldsEquals(
            partialUpdatedCommunicationFormDictionary,
            getPersistedCommunicationFormDictionary(partialUpdatedCommunicationFormDictionary)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCommunicationFormDictionary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationFormDictionary.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommunicationFormDictionaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, communicationFormDictionary.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(communicationFormDictionary))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommunicationFormDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCommunicationFormDictionary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationFormDictionary.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationFormDictionaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(communicationFormDictionary))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommunicationFormDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCommunicationFormDictionary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationFormDictionary.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationFormDictionaryMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(communicationFormDictionary))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CommunicationFormDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCommunicationFormDictionary() throws Exception {
        // Initialize the database
        insertedCommunicationFormDictionary = communicationFormDictionaryRepository.saveAndFlush(communicationFormDictionary);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the communicationFormDictionary
        restCommunicationFormDictionaryMockMvc
            .perform(delete(ENTITY_API_URL_ID, communicationFormDictionary.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return communicationFormDictionaryRepository.count();
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

    protected CommunicationFormDictionary getPersistedCommunicationFormDictionary(CommunicationFormDictionary communicationFormDictionary) {
        return communicationFormDictionaryRepository.findById(communicationFormDictionary.getId()).orElseThrow();
    }

    protected void assertPersistedCommunicationFormDictionaryToMatchAllProperties(
        CommunicationFormDictionary expectedCommunicationFormDictionary
    ) {
        assertCommunicationFormDictionaryAllPropertiesEquals(
            expectedCommunicationFormDictionary,
            getPersistedCommunicationFormDictionary(expectedCommunicationFormDictionary)
        );
    }

    protected void assertPersistedCommunicationFormDictionaryToMatchUpdatableProperties(
        CommunicationFormDictionary expectedCommunicationFormDictionary
    ) {
        assertCommunicationFormDictionaryAllUpdatablePropertiesEquals(
            expectedCommunicationFormDictionary,
            getPersistedCommunicationFormDictionary(expectedCommunicationFormDictionary)
        );
    }
}

package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.QualityObjectivesDictionaryAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.QualityObjectivesDictionary;
import com.cvicse.jy1.repository.QualityObjectivesDictionaryRepository;
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
 * Integration tests for the {@link QualityObjectivesDictionaryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QualityObjectivesDictionaryResourceIT {

    private static final String DEFAULT_OBJECTIVESLEVEL = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTIVESLEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_OBJECTIVESTYPE = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTIVESTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_OBJECTIVESNAME = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTIVESNAME = "BBBBBBBBBB";

    private static final String DEFAULT_OBJECTIVESCONTENT = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTIVESCONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_CALCULATIONMETHOD = "AAAAAAAAAA";
    private static final String UPDATED_CALCULATIONMETHOD = "BBBBBBBBBB";

    private static final String DEFAULT_FREQUENCY = "AAAAAAAAAA";
    private static final String UPDATED_FREQUENCY = "BBBBBBBBBB";

    private static final String DEFAULT_EVALUATIONCRITERIA = "AAAAAAAAAA";
    private static final String UPDATED_EVALUATIONCRITERIA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/quality-objectives-dictionaries";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QualityObjectivesDictionaryRepository qualityObjectivesDictionaryRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQualityObjectivesDictionaryMockMvc;

    private QualityObjectivesDictionary qualityObjectivesDictionary;

    private QualityObjectivesDictionary insertedQualityObjectivesDictionary;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QualityObjectivesDictionary createEntity(EntityManager em) {
        QualityObjectivesDictionary qualityObjectivesDictionary = new QualityObjectivesDictionary()
            .objectiveslevel(DEFAULT_OBJECTIVESLEVEL)
            .objectivestype(DEFAULT_OBJECTIVESTYPE)
            .objectivesname(DEFAULT_OBJECTIVESNAME)
            .objectivescontent(DEFAULT_OBJECTIVESCONTENT)
            .calculationmethod(DEFAULT_CALCULATIONMETHOD)
            .frequency(DEFAULT_FREQUENCY)
            .evaluationcriteria(DEFAULT_EVALUATIONCRITERIA);
        return qualityObjectivesDictionary;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QualityObjectivesDictionary createUpdatedEntity(EntityManager em) {
        QualityObjectivesDictionary qualityObjectivesDictionary = new QualityObjectivesDictionary()
            .objectiveslevel(UPDATED_OBJECTIVESLEVEL)
            .objectivestype(UPDATED_OBJECTIVESTYPE)
            .objectivesname(UPDATED_OBJECTIVESNAME)
            .objectivescontent(UPDATED_OBJECTIVESCONTENT)
            .calculationmethod(UPDATED_CALCULATIONMETHOD)
            .frequency(UPDATED_FREQUENCY)
            .evaluationcriteria(UPDATED_EVALUATIONCRITERIA);
        return qualityObjectivesDictionary;
    }

    @BeforeEach
    public void initTest() {
        qualityObjectivesDictionary = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedQualityObjectivesDictionary != null) {
            qualityObjectivesDictionaryRepository.delete(insertedQualityObjectivesDictionary);
            insertedQualityObjectivesDictionary = null;
        }
    }

    @Test
    @Transactional
    void createQualityObjectivesDictionary() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the QualityObjectivesDictionary
        var returnedQualityObjectivesDictionary = om.readValue(
            restQualityObjectivesDictionaryMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityObjectivesDictionary))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            QualityObjectivesDictionary.class
        );

        // Validate the QualityObjectivesDictionary in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertQualityObjectivesDictionaryUpdatableFieldsEquals(
            returnedQualityObjectivesDictionary,
            getPersistedQualityObjectivesDictionary(returnedQualityObjectivesDictionary)
        );

        insertedQualityObjectivesDictionary = returnedQualityObjectivesDictionary;
    }

    @Test
    @Transactional
    void createQualityObjectivesDictionaryWithExistingId() throws Exception {
        // Create the QualityObjectivesDictionary with an existing ID
        qualityObjectivesDictionary.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQualityObjectivesDictionaryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityObjectivesDictionary))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityObjectivesDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQualityObjectivesDictionaries() throws Exception {
        // Initialize the database
        insertedQualityObjectivesDictionary = qualityObjectivesDictionaryRepository.saveAndFlush(qualityObjectivesDictionary);

        // Get all the qualityObjectivesDictionaryList
        restQualityObjectivesDictionaryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qualityObjectivesDictionary.getId().intValue())))
            .andExpect(jsonPath("$.[*].objectiveslevel").value(hasItem(DEFAULT_OBJECTIVESLEVEL)))
            .andExpect(jsonPath("$.[*].objectivestype").value(hasItem(DEFAULT_OBJECTIVESTYPE)))
            .andExpect(jsonPath("$.[*].objectivesname").value(hasItem(DEFAULT_OBJECTIVESNAME)))
            .andExpect(jsonPath("$.[*].objectivescontent").value(hasItem(DEFAULT_OBJECTIVESCONTENT)))
            .andExpect(jsonPath("$.[*].calculationmethod").value(hasItem(DEFAULT_CALCULATIONMETHOD)))
            .andExpect(jsonPath("$.[*].frequency").value(hasItem(DEFAULT_FREQUENCY)))
            .andExpect(jsonPath("$.[*].evaluationcriteria").value(hasItem(DEFAULT_EVALUATIONCRITERIA)));
    }

    @Test
    @Transactional
    void getQualityObjectivesDictionary() throws Exception {
        // Initialize the database
        insertedQualityObjectivesDictionary = qualityObjectivesDictionaryRepository.saveAndFlush(qualityObjectivesDictionary);

        // Get the qualityObjectivesDictionary
        restQualityObjectivesDictionaryMockMvc
            .perform(get(ENTITY_API_URL_ID, qualityObjectivesDictionary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(qualityObjectivesDictionary.getId().intValue()))
            .andExpect(jsonPath("$.objectiveslevel").value(DEFAULT_OBJECTIVESLEVEL))
            .andExpect(jsonPath("$.objectivestype").value(DEFAULT_OBJECTIVESTYPE))
            .andExpect(jsonPath("$.objectivesname").value(DEFAULT_OBJECTIVESNAME))
            .andExpect(jsonPath("$.objectivescontent").value(DEFAULT_OBJECTIVESCONTENT))
            .andExpect(jsonPath("$.calculationmethod").value(DEFAULT_CALCULATIONMETHOD))
            .andExpect(jsonPath("$.frequency").value(DEFAULT_FREQUENCY))
            .andExpect(jsonPath("$.evaluationcriteria").value(DEFAULT_EVALUATIONCRITERIA));
    }

    @Test
    @Transactional
    void getNonExistingQualityObjectivesDictionary() throws Exception {
        // Get the qualityObjectivesDictionary
        restQualityObjectivesDictionaryMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQualityObjectivesDictionary() throws Exception {
        // Initialize the database
        insertedQualityObjectivesDictionary = qualityObjectivesDictionaryRepository.saveAndFlush(qualityObjectivesDictionary);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityObjectivesDictionary
        QualityObjectivesDictionary updatedQualityObjectivesDictionary = qualityObjectivesDictionaryRepository
            .findById(qualityObjectivesDictionary.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedQualityObjectivesDictionary are not directly saved in db
        em.detach(updatedQualityObjectivesDictionary);
        updatedQualityObjectivesDictionary
            .objectiveslevel(UPDATED_OBJECTIVESLEVEL)
            .objectivestype(UPDATED_OBJECTIVESTYPE)
            .objectivesname(UPDATED_OBJECTIVESNAME)
            .objectivescontent(UPDATED_OBJECTIVESCONTENT)
            .calculationmethod(UPDATED_CALCULATIONMETHOD)
            .frequency(UPDATED_FREQUENCY)
            .evaluationcriteria(UPDATED_EVALUATIONCRITERIA);

        restQualityObjectivesDictionaryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedQualityObjectivesDictionary.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedQualityObjectivesDictionary))
            )
            .andExpect(status().isOk());

        // Validate the QualityObjectivesDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQualityObjectivesDictionaryToMatchAllProperties(updatedQualityObjectivesDictionary);
    }

    @Test
    @Transactional
    void putNonExistingQualityObjectivesDictionary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityObjectivesDictionary.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualityObjectivesDictionaryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, qualityObjectivesDictionary.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualityObjectivesDictionary))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityObjectivesDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQualityObjectivesDictionary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityObjectivesDictionary.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityObjectivesDictionaryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualityObjectivesDictionary))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityObjectivesDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQualityObjectivesDictionary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityObjectivesDictionary.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityObjectivesDictionaryMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityObjectivesDictionary)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QualityObjectivesDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQualityObjectivesDictionaryWithPatch() throws Exception {
        // Initialize the database
        insertedQualityObjectivesDictionary = qualityObjectivesDictionaryRepository.saveAndFlush(qualityObjectivesDictionary);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityObjectivesDictionary using partial update
        QualityObjectivesDictionary partialUpdatedQualityObjectivesDictionary = new QualityObjectivesDictionary();
        partialUpdatedQualityObjectivesDictionary.setId(qualityObjectivesDictionary.getId());

        partialUpdatedQualityObjectivesDictionary
            .objectiveslevel(UPDATED_OBJECTIVESLEVEL)
            .objectivesname(UPDATED_OBJECTIVESNAME)
            .objectivescontent(UPDATED_OBJECTIVESCONTENT);

        restQualityObjectivesDictionaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualityObjectivesDictionary.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualityObjectivesDictionary))
            )
            .andExpect(status().isOk());

        // Validate the QualityObjectivesDictionary in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualityObjectivesDictionaryUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedQualityObjectivesDictionary, qualityObjectivesDictionary),
            getPersistedQualityObjectivesDictionary(qualityObjectivesDictionary)
        );
    }

    @Test
    @Transactional
    void fullUpdateQualityObjectivesDictionaryWithPatch() throws Exception {
        // Initialize the database
        insertedQualityObjectivesDictionary = qualityObjectivesDictionaryRepository.saveAndFlush(qualityObjectivesDictionary);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityObjectivesDictionary using partial update
        QualityObjectivesDictionary partialUpdatedQualityObjectivesDictionary = new QualityObjectivesDictionary();
        partialUpdatedQualityObjectivesDictionary.setId(qualityObjectivesDictionary.getId());

        partialUpdatedQualityObjectivesDictionary
            .objectiveslevel(UPDATED_OBJECTIVESLEVEL)
            .objectivestype(UPDATED_OBJECTIVESTYPE)
            .objectivesname(UPDATED_OBJECTIVESNAME)
            .objectivescontent(UPDATED_OBJECTIVESCONTENT)
            .calculationmethod(UPDATED_CALCULATIONMETHOD)
            .frequency(UPDATED_FREQUENCY)
            .evaluationcriteria(UPDATED_EVALUATIONCRITERIA);

        restQualityObjectivesDictionaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualityObjectivesDictionary.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualityObjectivesDictionary))
            )
            .andExpect(status().isOk());

        // Validate the QualityObjectivesDictionary in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualityObjectivesDictionaryUpdatableFieldsEquals(
            partialUpdatedQualityObjectivesDictionary,
            getPersistedQualityObjectivesDictionary(partialUpdatedQualityObjectivesDictionary)
        );
    }

    @Test
    @Transactional
    void patchNonExistingQualityObjectivesDictionary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityObjectivesDictionary.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualityObjectivesDictionaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, qualityObjectivesDictionary.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualityObjectivesDictionary))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityObjectivesDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQualityObjectivesDictionary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityObjectivesDictionary.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityObjectivesDictionaryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualityObjectivesDictionary))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityObjectivesDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQualityObjectivesDictionary() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityObjectivesDictionary.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityObjectivesDictionaryMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(qualityObjectivesDictionary))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the QualityObjectivesDictionary in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQualityObjectivesDictionary() throws Exception {
        // Initialize the database
        insertedQualityObjectivesDictionary = qualityObjectivesDictionaryRepository.saveAndFlush(qualityObjectivesDictionary);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the qualityObjectivesDictionary
        restQualityObjectivesDictionaryMockMvc
            .perform(delete(ENTITY_API_URL_ID, qualityObjectivesDictionary.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return qualityObjectivesDictionaryRepository.count();
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

    protected QualityObjectivesDictionary getPersistedQualityObjectivesDictionary(QualityObjectivesDictionary qualityObjectivesDictionary) {
        return qualityObjectivesDictionaryRepository.findById(qualityObjectivesDictionary.getId()).orElseThrow();
    }

    protected void assertPersistedQualityObjectivesDictionaryToMatchAllProperties(
        QualityObjectivesDictionary expectedQualityObjectivesDictionary
    ) {
        assertQualityObjectivesDictionaryAllPropertiesEquals(
            expectedQualityObjectivesDictionary,
            getPersistedQualityObjectivesDictionary(expectedQualityObjectivesDictionary)
        );
    }

    protected void assertPersistedQualityObjectivesDictionaryToMatchUpdatableProperties(
        QualityObjectivesDictionary expectedQualityObjectivesDictionary
    ) {
        assertQualityObjectivesDictionaryAllUpdatablePropertiesEquals(
            expectedQualityObjectivesDictionary,
            getPersistedQualityObjectivesDictionary(expectedQualityObjectivesDictionary)
        );
    }
}

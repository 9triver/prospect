package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.QualityObjectivesAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.QualityObjectives;
import com.cvicse.jy1.repository.QualityObjectivesRepository;
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
 * Integration tests for the {@link QualityObjectivesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QualityObjectivesResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OBJECTIVESLEVEL = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTIVESLEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_OBJECTIVES = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTIVES = "BBBBBBBBBB";

    private static final String DEFAULT_OBJECTIVESVALUE = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTIVESVALUE = "BBBBBBBBBB";

    private static final String DEFAULT_CALCULATIONMETHOD = "AAAAAAAAAA";
    private static final String UPDATED_CALCULATIONMETHOD = "BBBBBBBBBB";

    private static final String DEFAULT_FREQUENCY = "AAAAAAAAAA";
    private static final String UPDATED_FREQUENCY = "BBBBBBBBBB";

    private static final String DEFAULT_TAKEACTION = "AAAAAAAAAA";
    private static final String UPDATED_TAKEACTION = "BBBBBBBBBB";

    private static final String DEFAULT_NEEDRESOURCE = "AAAAAAAAAA";
    private static final String UPDATED_NEEDRESOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/quality-objectives";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QualityObjectivesRepository qualityObjectivesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQualityObjectivesMockMvc;

    private QualityObjectives qualityObjectives;

    private QualityObjectives insertedQualityObjectives;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QualityObjectives createEntity(EntityManager em) {
        QualityObjectives qualityObjectives = new QualityObjectives()
            .name(DEFAULT_NAME)
            .objectiveslevel(DEFAULT_OBJECTIVESLEVEL)
            .objectives(DEFAULT_OBJECTIVES)
            .objectivesvalue(DEFAULT_OBJECTIVESVALUE)
            .calculationmethod(DEFAULT_CALCULATIONMETHOD)
            .frequency(DEFAULT_FREQUENCY)
            .takeaction(DEFAULT_TAKEACTION)
            .needresource(DEFAULT_NEEDRESOURCE)
            .status(DEFAULT_STATUS);
        return qualityObjectives;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QualityObjectives createUpdatedEntity(EntityManager em) {
        QualityObjectives qualityObjectives = new QualityObjectives()
            .name(UPDATED_NAME)
            .objectiveslevel(UPDATED_OBJECTIVESLEVEL)
            .objectives(UPDATED_OBJECTIVES)
            .objectivesvalue(UPDATED_OBJECTIVESVALUE)
            .calculationmethod(UPDATED_CALCULATIONMETHOD)
            .frequency(UPDATED_FREQUENCY)
            .takeaction(UPDATED_TAKEACTION)
            .needresource(UPDATED_NEEDRESOURCE)
            .status(UPDATED_STATUS);
        return qualityObjectives;
    }

    @BeforeEach
    public void initTest() {
        qualityObjectives = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedQualityObjectives != null) {
            qualityObjectivesRepository.delete(insertedQualityObjectives);
            insertedQualityObjectives = null;
        }
    }

    @Test
    @Transactional
    void createQualityObjectives() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the QualityObjectives
        var returnedQualityObjectives = om.readValue(
            restQualityObjectivesMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityObjectives)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            QualityObjectives.class
        );

        // Validate the QualityObjectives in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertQualityObjectivesUpdatableFieldsEquals(returnedQualityObjectives, getPersistedQualityObjectives(returnedQualityObjectives));

        insertedQualityObjectives = returnedQualityObjectives;
    }

    @Test
    @Transactional
    void createQualityObjectivesWithExistingId() throws Exception {
        // Create the QualityObjectives with an existing ID
        qualityObjectives.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQualityObjectivesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityObjectives)))
            .andExpect(status().isBadRequest());

        // Validate the QualityObjectives in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQualityObjectives() throws Exception {
        // Initialize the database
        insertedQualityObjectives = qualityObjectivesRepository.saveAndFlush(qualityObjectives);

        // Get all the qualityObjectivesList
        restQualityObjectivesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qualityObjectives.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].objectiveslevel").value(hasItem(DEFAULT_OBJECTIVESLEVEL)))
            .andExpect(jsonPath("$.[*].objectives").value(hasItem(DEFAULT_OBJECTIVES)))
            .andExpect(jsonPath("$.[*].objectivesvalue").value(hasItem(DEFAULT_OBJECTIVESVALUE)))
            .andExpect(jsonPath("$.[*].calculationmethod").value(hasItem(DEFAULT_CALCULATIONMETHOD)))
            .andExpect(jsonPath("$.[*].frequency").value(hasItem(DEFAULT_FREQUENCY)))
            .andExpect(jsonPath("$.[*].takeaction").value(hasItem(DEFAULT_TAKEACTION)))
            .andExpect(jsonPath("$.[*].needresource").value(hasItem(DEFAULT_NEEDRESOURCE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    void getQualityObjectives() throws Exception {
        // Initialize the database
        insertedQualityObjectives = qualityObjectivesRepository.saveAndFlush(qualityObjectives);

        // Get the qualityObjectives
        restQualityObjectivesMockMvc
            .perform(get(ENTITY_API_URL_ID, qualityObjectives.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(qualityObjectives.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.objectiveslevel").value(DEFAULT_OBJECTIVESLEVEL))
            .andExpect(jsonPath("$.objectives").value(DEFAULT_OBJECTIVES))
            .andExpect(jsonPath("$.objectivesvalue").value(DEFAULT_OBJECTIVESVALUE))
            .andExpect(jsonPath("$.calculationmethod").value(DEFAULT_CALCULATIONMETHOD))
            .andExpect(jsonPath("$.frequency").value(DEFAULT_FREQUENCY))
            .andExpect(jsonPath("$.takeaction").value(DEFAULT_TAKEACTION))
            .andExpect(jsonPath("$.needresource").value(DEFAULT_NEEDRESOURCE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingQualityObjectives() throws Exception {
        // Get the qualityObjectives
        restQualityObjectivesMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQualityObjectives() throws Exception {
        // Initialize the database
        insertedQualityObjectives = qualityObjectivesRepository.saveAndFlush(qualityObjectives);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityObjectives
        QualityObjectives updatedQualityObjectives = qualityObjectivesRepository.findById(qualityObjectives.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedQualityObjectives are not directly saved in db
        em.detach(updatedQualityObjectives);
        updatedQualityObjectives
            .name(UPDATED_NAME)
            .objectiveslevel(UPDATED_OBJECTIVESLEVEL)
            .objectives(UPDATED_OBJECTIVES)
            .objectivesvalue(UPDATED_OBJECTIVESVALUE)
            .calculationmethod(UPDATED_CALCULATIONMETHOD)
            .frequency(UPDATED_FREQUENCY)
            .takeaction(UPDATED_TAKEACTION)
            .needresource(UPDATED_NEEDRESOURCE)
            .status(UPDATED_STATUS);

        restQualityObjectivesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedQualityObjectives.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedQualityObjectives))
            )
            .andExpect(status().isOk());

        // Validate the QualityObjectives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQualityObjectivesToMatchAllProperties(updatedQualityObjectives);
    }

    @Test
    @Transactional
    void putNonExistingQualityObjectives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityObjectives.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualityObjectivesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, qualityObjectives.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualityObjectives))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityObjectives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQualityObjectives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityObjectives.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityObjectivesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualityObjectives))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityObjectives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQualityObjectives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityObjectives.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityObjectivesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityObjectives)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QualityObjectives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQualityObjectivesWithPatch() throws Exception {
        // Initialize the database
        insertedQualityObjectives = qualityObjectivesRepository.saveAndFlush(qualityObjectives);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityObjectives using partial update
        QualityObjectives partialUpdatedQualityObjectives = new QualityObjectives();
        partialUpdatedQualityObjectives.setId(qualityObjectives.getId());

        partialUpdatedQualityObjectives
            .objectiveslevel(UPDATED_OBJECTIVESLEVEL)
            .objectives(UPDATED_OBJECTIVES)
            .calculationmethod(UPDATED_CALCULATIONMETHOD)
            .frequency(UPDATED_FREQUENCY)
            .status(UPDATED_STATUS);

        restQualityObjectivesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualityObjectives.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualityObjectives))
            )
            .andExpect(status().isOk());

        // Validate the QualityObjectives in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualityObjectivesUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedQualityObjectives, qualityObjectives),
            getPersistedQualityObjectives(qualityObjectives)
        );
    }

    @Test
    @Transactional
    void fullUpdateQualityObjectivesWithPatch() throws Exception {
        // Initialize the database
        insertedQualityObjectives = qualityObjectivesRepository.saveAndFlush(qualityObjectives);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityObjectives using partial update
        QualityObjectives partialUpdatedQualityObjectives = new QualityObjectives();
        partialUpdatedQualityObjectives.setId(qualityObjectives.getId());

        partialUpdatedQualityObjectives
            .name(UPDATED_NAME)
            .objectiveslevel(UPDATED_OBJECTIVESLEVEL)
            .objectives(UPDATED_OBJECTIVES)
            .objectivesvalue(UPDATED_OBJECTIVESVALUE)
            .calculationmethod(UPDATED_CALCULATIONMETHOD)
            .frequency(UPDATED_FREQUENCY)
            .takeaction(UPDATED_TAKEACTION)
            .needresource(UPDATED_NEEDRESOURCE)
            .status(UPDATED_STATUS);

        restQualityObjectivesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualityObjectives.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualityObjectives))
            )
            .andExpect(status().isOk());

        // Validate the QualityObjectives in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualityObjectivesUpdatableFieldsEquals(
            partialUpdatedQualityObjectives,
            getPersistedQualityObjectives(partialUpdatedQualityObjectives)
        );
    }

    @Test
    @Transactional
    void patchNonExistingQualityObjectives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityObjectives.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualityObjectivesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, qualityObjectives.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualityObjectives))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityObjectives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQualityObjectives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityObjectives.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityObjectivesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualityObjectives))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityObjectives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQualityObjectives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityObjectives.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityObjectivesMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(qualityObjectives)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QualityObjectives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQualityObjectives() throws Exception {
        // Initialize the database
        insertedQualityObjectives = qualityObjectivesRepository.saveAndFlush(qualityObjectives);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the qualityObjectives
        restQualityObjectivesMockMvc
            .perform(delete(ENTITY_API_URL_ID, qualityObjectives.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return qualityObjectivesRepository.count();
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

    protected QualityObjectives getPersistedQualityObjectives(QualityObjectives qualityObjectives) {
        return qualityObjectivesRepository.findById(qualityObjectives.getId()).orElseThrow();
    }

    protected void assertPersistedQualityObjectivesToMatchAllProperties(QualityObjectives expectedQualityObjectives) {
        assertQualityObjectivesAllPropertiesEquals(expectedQualityObjectives, getPersistedQualityObjectives(expectedQualityObjectives));
    }

    protected void assertPersistedQualityObjectivesToMatchUpdatableProperties(QualityObjectives expectedQualityObjectives) {
        assertQualityObjectivesAllUpdatablePropertiesEquals(
            expectedQualityObjectives,
            getPersistedQualityObjectives(expectedQualityObjectives)
        );
    }
}

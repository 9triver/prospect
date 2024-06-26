package com.cvicse.web.rest;

import static com.cvicse.domain.QualitymanagementWbsAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.QualitymanagementWbs;
import com.cvicse.repository.QualitymanagementWbsRepository;
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
 * Integration tests for the {@link QualitymanagementWbsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QualitymanagementWbsResourceIT {

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

    private static final String ENTITY_API_URL = "/api/qualitymanagement-wbs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QualitymanagementWbsRepository qualitymanagementWbsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQualitymanagementWbsMockMvc;

    private QualitymanagementWbs qualitymanagementWbs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QualitymanagementWbs createEntity(EntityManager em) {
        QualitymanagementWbs qualitymanagementWbs = new QualitymanagementWbs()
            .wbsspare1(DEFAULT_WBSSPARE_1)
            .wbsspare2(DEFAULT_WBSSPARE_2)
            .wbsspare3(DEFAULT_WBSSPARE_3)
            .wbsspare4(DEFAULT_WBSSPARE_4)
            .wbsspare5(DEFAULT_WBSSPARE_5);
        return qualitymanagementWbs;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QualitymanagementWbs createUpdatedEntity(EntityManager em) {
        QualitymanagementWbs qualitymanagementWbs = new QualitymanagementWbs()
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);
        return qualitymanagementWbs;
    }

    @BeforeEach
    public void initTest() {
        qualitymanagementWbs = createEntity(em);
    }

    @Test
    @Transactional
    void createQualitymanagementWbs() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the QualitymanagementWbs
        var returnedQualitymanagementWbs = om.readValue(
            restQualitymanagementWbsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualitymanagementWbs)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            QualitymanagementWbs.class
        );

        // Validate the QualitymanagementWbs in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertQualitymanagementWbsUpdatableFieldsEquals(
            returnedQualitymanagementWbs,
            getPersistedQualitymanagementWbs(returnedQualitymanagementWbs)
        );
    }

    @Test
    @Transactional
    void createQualitymanagementWbsWithExistingId() throws Exception {
        // Create the QualitymanagementWbs with an existing ID
        qualitymanagementWbs.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQualitymanagementWbsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualitymanagementWbs)))
            .andExpect(status().isBadRequest());

        // Validate the QualitymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQualitymanagementWbs() throws Exception {
        // Initialize the database
        qualitymanagementWbsRepository.saveAndFlush(qualitymanagementWbs);

        // Get all the qualitymanagementWbsList
        restQualitymanagementWbsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qualitymanagementWbs.getId())))
            .andExpect(jsonPath("$.[*].wbsspare1").value(hasItem(DEFAULT_WBSSPARE_1)))
            .andExpect(jsonPath("$.[*].wbsspare2").value(hasItem(DEFAULT_WBSSPARE_2)))
            .andExpect(jsonPath("$.[*].wbsspare3").value(hasItem(DEFAULT_WBSSPARE_3)))
            .andExpect(jsonPath("$.[*].wbsspare4").value(hasItem(DEFAULT_WBSSPARE_4)))
            .andExpect(jsonPath("$.[*].wbsspare5").value(hasItem(DEFAULT_WBSSPARE_5)));
    }

    @Test
    @Transactional
    void getQualitymanagementWbs() throws Exception {
        // Initialize the database
        qualitymanagementWbsRepository.saveAndFlush(qualitymanagementWbs);

        // Get the qualitymanagementWbs
        restQualitymanagementWbsMockMvc
            .perform(get(ENTITY_API_URL_ID, qualitymanagementWbs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(qualitymanagementWbs.getId()))
            .andExpect(jsonPath("$.wbsspare1").value(DEFAULT_WBSSPARE_1))
            .andExpect(jsonPath("$.wbsspare2").value(DEFAULT_WBSSPARE_2))
            .andExpect(jsonPath("$.wbsspare3").value(DEFAULT_WBSSPARE_3))
            .andExpect(jsonPath("$.wbsspare4").value(DEFAULT_WBSSPARE_4))
            .andExpect(jsonPath("$.wbsspare5").value(DEFAULT_WBSSPARE_5));
    }

    @Test
    @Transactional
    void getNonExistingQualitymanagementWbs() throws Exception {
        // Get the qualitymanagementWbs
        restQualitymanagementWbsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQualitymanagementWbs() throws Exception {
        // Initialize the database
        qualitymanagementWbsRepository.saveAndFlush(qualitymanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualitymanagementWbs
        QualitymanagementWbs updatedQualitymanagementWbs = qualitymanagementWbsRepository
            .findById(qualitymanagementWbs.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedQualitymanagementWbs are not directly saved in db
        em.detach(updatedQualitymanagementWbs);
        updatedQualitymanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restQualitymanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedQualitymanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedQualitymanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the QualitymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQualitymanagementWbsToMatchAllProperties(updatedQualitymanagementWbs);
    }

    @Test
    @Transactional
    void putNonExistingQualitymanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitymanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualitymanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, qualitymanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualitymanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualitymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQualitymanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitymanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualitymanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualitymanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualitymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQualitymanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitymanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualitymanagementWbsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualitymanagementWbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QualitymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQualitymanagementWbsWithPatch() throws Exception {
        // Initialize the database
        qualitymanagementWbsRepository.saveAndFlush(qualitymanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualitymanagementWbs using partial update
        QualitymanagementWbs partialUpdatedQualitymanagementWbs = new QualitymanagementWbs();
        partialUpdatedQualitymanagementWbs.setId(qualitymanagementWbs.getId());

        partialUpdatedQualitymanagementWbs.wbsspare2(UPDATED_WBSSPARE_2).wbsspare3(UPDATED_WBSSPARE_3).wbsspare4(UPDATED_WBSSPARE_4);

        restQualitymanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualitymanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualitymanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the QualitymanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualitymanagementWbsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedQualitymanagementWbs, qualitymanagementWbs),
            getPersistedQualitymanagementWbs(qualitymanagementWbs)
        );
    }

    @Test
    @Transactional
    void fullUpdateQualitymanagementWbsWithPatch() throws Exception {
        // Initialize the database
        qualitymanagementWbsRepository.saveAndFlush(qualitymanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualitymanagementWbs using partial update
        QualitymanagementWbs partialUpdatedQualitymanagementWbs = new QualitymanagementWbs();
        partialUpdatedQualitymanagementWbs.setId(qualitymanagementWbs.getId());

        partialUpdatedQualitymanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restQualitymanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualitymanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualitymanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the QualitymanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualitymanagementWbsUpdatableFieldsEquals(
            partialUpdatedQualitymanagementWbs,
            getPersistedQualitymanagementWbs(partialUpdatedQualitymanagementWbs)
        );
    }

    @Test
    @Transactional
    void patchNonExistingQualitymanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitymanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualitymanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, qualitymanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualitymanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualitymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQualitymanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitymanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualitymanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualitymanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualitymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQualitymanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitymanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualitymanagementWbsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(qualitymanagementWbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QualitymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQualitymanagementWbs() throws Exception {
        // Initialize the database
        qualitymanagementWbsRepository.saveAndFlush(qualitymanagementWbs);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the qualitymanagementWbs
        restQualitymanagementWbsMockMvc
            .perform(delete(ENTITY_API_URL_ID, qualitymanagementWbs.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return qualitymanagementWbsRepository.count();
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

    protected QualitymanagementWbs getPersistedQualitymanagementWbs(QualitymanagementWbs qualitymanagementWbs) {
        return qualitymanagementWbsRepository.findById(qualitymanagementWbs.getId()).orElseThrow();
    }

    protected void assertPersistedQualitymanagementWbsToMatchAllProperties(QualitymanagementWbs expectedQualitymanagementWbs) {
        assertQualitymanagementWbsAllPropertiesEquals(
            expectedQualitymanagementWbs,
            getPersistedQualitymanagementWbs(expectedQualitymanagementWbs)
        );
    }

    protected void assertPersistedQualitymanagementWbsToMatchUpdatableProperties(QualitymanagementWbs expectedQualitymanagementWbs) {
        assertQualitymanagementWbsAllUpdatablePropertiesEquals(
            expectedQualitymanagementWbs,
            getPersistedQualitymanagementWbs(expectedQualitymanagementWbs)
        );
    }
}

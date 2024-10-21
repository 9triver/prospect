package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.QualityPlanAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.QualityPlan;
import com.cvicse.jy1.domain.enumeration.QualityType;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.repository.QualityPlanRepository;
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
 * Integration tests for the {@link QualityPlanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QualityPlanResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final QualityType DEFAULT_QUALITYTYPE = QualityType.CYCLE;
    private static final QualityType UPDATED_QUALITYTYPE = QualityType.YEAR;

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.PUBLIC;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.INTERNAL;

    private static final String DEFAULT_WBSID = "AAAAAAAAAA";
    private static final String UPDATED_WBSID = "BBBBBBBBBB";

    private static final String DEFAULT_WORKBAGID = "AAAAAAAAAA";
    private static final String UPDATED_WORKBAGID = "BBBBBBBBBB";

    private static final String DEFAULT_FILEVERSION = "AAAAAAAAAA";
    private static final String UPDATED_FILEVERSION = "BBBBBBBBBB";

    private static final String DEFAULT_AUTHOR = "AAAAAAAAAA";
    private static final String UPDATED_AUTHOR = "BBBBBBBBBB";

    private static final String DEFAULT_ATTACHMENT = "AAAAAAAAAA";
    private static final String UPDATED_ATTACHMENT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/quality-plans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QualityPlanRepository qualityPlanRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQualityPlanMockMvc;

    private QualityPlan qualityPlan;

    private QualityPlan insertedQualityPlan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QualityPlan createEntity(EntityManager em) {
        QualityPlan qualityPlan = new QualityPlan()
            .name(DEFAULT_NAME)
            .qualitytype(DEFAULT_QUALITYTYPE)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .wbsid(DEFAULT_WBSID)
            .workbagid(DEFAULT_WORKBAGID)
            .fileversion(DEFAULT_FILEVERSION)
            .author(DEFAULT_AUTHOR)
            .attachment(DEFAULT_ATTACHMENT);
        return qualityPlan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QualityPlan createUpdatedEntity(EntityManager em) {
        QualityPlan qualityPlan = new QualityPlan()
            .name(UPDATED_NAME)
            .qualitytype(UPDATED_QUALITYTYPE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .wbsid(UPDATED_WBSID)
            .workbagid(UPDATED_WORKBAGID)
            .fileversion(UPDATED_FILEVERSION)
            .author(UPDATED_AUTHOR)
            .attachment(UPDATED_ATTACHMENT);
        return qualityPlan;
    }

    @BeforeEach
    public void initTest() {
        qualityPlan = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedQualityPlan != null) {
            qualityPlanRepository.delete(insertedQualityPlan);
            insertedQualityPlan = null;
        }
    }

    @Test
    @Transactional
    void createQualityPlan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the QualityPlan
        var returnedQualityPlan = om.readValue(
            restQualityPlanMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityPlan)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            QualityPlan.class
        );

        // Validate the QualityPlan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertQualityPlanUpdatableFieldsEquals(returnedQualityPlan, getPersistedQualityPlan(returnedQualityPlan));

        insertedQualityPlan = returnedQualityPlan;
    }

    @Test
    @Transactional
    void createQualityPlanWithExistingId() throws Exception {
        // Create the QualityPlan with an existing ID
        qualityPlan.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQualityPlanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityPlan)))
            .andExpect(status().isBadRequest());

        // Validate the QualityPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQualityPlans() throws Exception {
        // Initialize the database
        insertedQualityPlan = qualityPlanRepository.saveAndFlush(qualityPlan);

        // Get all the qualityPlanList
        restQualityPlanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qualityPlan.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].qualitytype").value(hasItem(DEFAULT_QUALITYTYPE.toString())))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].wbsid").value(hasItem(DEFAULT_WBSID)))
            .andExpect(jsonPath("$.[*].workbagid").value(hasItem(DEFAULT_WORKBAGID)))
            .andExpect(jsonPath("$.[*].fileversion").value(hasItem(DEFAULT_FILEVERSION)))
            .andExpect(jsonPath("$.[*].author").value(hasItem(DEFAULT_AUTHOR)))
            .andExpect(jsonPath("$.[*].attachment").value(hasItem(DEFAULT_ATTACHMENT)));
    }

    @Test
    @Transactional
    void getQualityPlan() throws Exception {
        // Initialize the database
        insertedQualityPlan = qualityPlanRepository.saveAndFlush(qualityPlan);

        // Get the qualityPlan
        restQualityPlanMockMvc
            .perform(get(ENTITY_API_URL_ID, qualityPlan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(qualityPlan.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.qualitytype").value(DEFAULT_QUALITYTYPE.toString()))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.wbsid").value(DEFAULT_WBSID))
            .andExpect(jsonPath("$.workbagid").value(DEFAULT_WORKBAGID))
            .andExpect(jsonPath("$.fileversion").value(DEFAULT_FILEVERSION))
            .andExpect(jsonPath("$.author").value(DEFAULT_AUTHOR))
            .andExpect(jsonPath("$.attachment").value(DEFAULT_ATTACHMENT));
    }

    @Test
    @Transactional
    void getNonExistingQualityPlan() throws Exception {
        // Get the qualityPlan
        restQualityPlanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQualityPlan() throws Exception {
        // Initialize the database
        insertedQualityPlan = qualityPlanRepository.saveAndFlush(qualityPlan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityPlan
        QualityPlan updatedQualityPlan = qualityPlanRepository.findById(qualityPlan.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedQualityPlan are not directly saved in db
        em.detach(updatedQualityPlan);
        updatedQualityPlan
            .name(UPDATED_NAME)
            .qualitytype(UPDATED_QUALITYTYPE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .wbsid(UPDATED_WBSID)
            .workbagid(UPDATED_WORKBAGID)
            .fileversion(UPDATED_FILEVERSION)
            .author(UPDATED_AUTHOR)
            .attachment(UPDATED_ATTACHMENT);

        restQualityPlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedQualityPlan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedQualityPlan))
            )
            .andExpect(status().isOk());

        // Validate the QualityPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQualityPlanToMatchAllProperties(updatedQualityPlan);
    }

    @Test
    @Transactional
    void putNonExistingQualityPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityPlan.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualityPlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, qualityPlan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualityPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQualityPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityPlan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityPlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualityPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQualityPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityPlan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityPlanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityPlan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QualityPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQualityPlanWithPatch() throws Exception {
        // Initialize the database
        insertedQualityPlan = qualityPlanRepository.saveAndFlush(qualityPlan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityPlan using partial update
        QualityPlan partialUpdatedQualityPlan = new QualityPlan();
        partialUpdatedQualityPlan.setId(qualityPlan.getId());

        partialUpdatedQualityPlan
            .name(UPDATED_NAME)
            .qualitytype(UPDATED_QUALITYTYPE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .wbsid(UPDATED_WBSID)
            .workbagid(UPDATED_WORKBAGID)
            .author(UPDATED_AUTHOR)
            .attachment(UPDATED_ATTACHMENT);

        restQualityPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualityPlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualityPlan))
            )
            .andExpect(status().isOk());

        // Validate the QualityPlan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualityPlanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedQualityPlan, qualityPlan),
            getPersistedQualityPlan(qualityPlan)
        );
    }

    @Test
    @Transactional
    void fullUpdateQualityPlanWithPatch() throws Exception {
        // Initialize the database
        insertedQualityPlan = qualityPlanRepository.saveAndFlush(qualityPlan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityPlan using partial update
        QualityPlan partialUpdatedQualityPlan = new QualityPlan();
        partialUpdatedQualityPlan.setId(qualityPlan.getId());

        partialUpdatedQualityPlan
            .name(UPDATED_NAME)
            .qualitytype(UPDATED_QUALITYTYPE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .wbsid(UPDATED_WBSID)
            .workbagid(UPDATED_WORKBAGID)
            .fileversion(UPDATED_FILEVERSION)
            .author(UPDATED_AUTHOR)
            .attachment(UPDATED_ATTACHMENT);

        restQualityPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualityPlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualityPlan))
            )
            .andExpect(status().isOk());

        // Validate the QualityPlan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualityPlanUpdatableFieldsEquals(partialUpdatedQualityPlan, getPersistedQualityPlan(partialUpdatedQualityPlan));
    }

    @Test
    @Transactional
    void patchNonExistingQualityPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityPlan.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualityPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, qualityPlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualityPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQualityPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityPlan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualityPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQualityPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityPlan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityPlanMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(qualityPlan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QualityPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQualityPlan() throws Exception {
        // Initialize the database
        insertedQualityPlan = qualityPlanRepository.saveAndFlush(qualityPlan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the qualityPlan
        restQualityPlanMockMvc
            .perform(delete(ENTITY_API_URL_ID, qualityPlan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return qualityPlanRepository.count();
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

    protected QualityPlan getPersistedQualityPlan(QualityPlan qualityPlan) {
        return qualityPlanRepository.findById(qualityPlan.getId()).orElseThrow();
    }

    protected void assertPersistedQualityPlanToMatchAllProperties(QualityPlan expectedQualityPlan) {
        assertQualityPlanAllPropertiesEquals(expectedQualityPlan, getPersistedQualityPlan(expectedQualityPlan));
    }

    protected void assertPersistedQualityPlanToMatchUpdatableProperties(QualityPlan expectedQualityPlan) {
        assertQualityPlanAllUpdatablePropertiesEquals(expectedQualityPlan, getPersistedQualityPlan(expectedQualityPlan));
    }
}

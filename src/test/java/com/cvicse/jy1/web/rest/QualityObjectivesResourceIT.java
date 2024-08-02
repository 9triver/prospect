package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.QualityObjectivesAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.QualityObjectives;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.QualityType;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.repository.QualityObjectivesRepository;
import com.cvicse.jy1.service.QualityObjectivesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link QualityObjectivesResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class QualityObjectivesResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OBJECTIVES = "AAAAAAAAAA";
    private static final String UPDATED_OBJECTIVES = "BBBBBBBBBB";

    private static final QualityType DEFAULT_QUALITYTYPE = QualityType.Cycle;
    private static final QualityType UPDATED_QUALITYTYPE = QualityType.Year;

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final Integer DEFAULT_TARGET = 1;
    private static final Integer UPDATED_TARGET = 2;

    private static final String DEFAULT_STATISTICALMETHOD = "AAAAAAAAAA";
    private static final String UPDATED_STATISTICALMETHOD = "BBBBBBBBBB";

    private static final String DEFAULT_STATISTICALFREQUENCY = "AAAAAAAAAA";
    private static final String UPDATED_STATISTICALFREQUENCY = "BBBBBBBBBB";

    private static final Integer DEFAULT_ISTARGET = 1;
    private static final Integer UPDATED_ISTARGET = 2;

    private static final Integer DEFAULT_PROGRESS = 1;
    private static final Integer UPDATED_PROGRESS = 2;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PROBLEMS = "AAAAAAAAAA";
    private static final String UPDATED_PROBLEMS = "BBBBBBBBBB";

    private static final String DEFAULT_IMPROVEMENTMEASURES = "AAAAAAAAAA";
    private static final String UPDATED_IMPROVEMENTMEASURES = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RETURNTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RETURNTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CREATETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATETIME = LocalDate.now(ZoneId.systemDefault());

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/quality-objectives";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QualityObjectivesRepository qualityObjectivesRepository;

    @Mock
    private QualityObjectivesRepository qualityObjectivesRepositoryMock;

    @Mock
    private QualityObjectivesService qualityObjectivesServiceMock;

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
            .objectives(DEFAULT_OBJECTIVES)
            .qualitytype(DEFAULT_QUALITYTYPE)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .target(DEFAULT_TARGET)
            .statisticalmethod(DEFAULT_STATISTICALMETHOD)
            .statisticalfrequency(DEFAULT_STATISTICALFREQUENCY)
            .istarget(DEFAULT_ISTARGET)
            .progress(DEFAULT_PROGRESS)
            .description(DEFAULT_DESCRIPTION)
            .problems(DEFAULT_PROBLEMS)
            .improvementmeasures(DEFAULT_IMPROVEMENTMEASURES)
            .returntime(DEFAULT_RETURNTIME)
            .createtime(DEFAULT_CREATETIME)
            .auditStatus(DEFAULT_AUDIT_STATUS);
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
            .objectives(UPDATED_OBJECTIVES)
            .qualitytype(UPDATED_QUALITYTYPE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .target(UPDATED_TARGET)
            .statisticalmethod(UPDATED_STATISTICALMETHOD)
            .statisticalfrequency(UPDATED_STATISTICALFREQUENCY)
            .istarget(UPDATED_ISTARGET)
            .progress(UPDATED_PROGRESS)
            .description(UPDATED_DESCRIPTION)
            .problems(UPDATED_PROBLEMS)
            .improvementmeasures(UPDATED_IMPROVEMENTMEASURES)
            .returntime(UPDATED_RETURNTIME)
            .createtime(UPDATED_CREATETIME)
            .auditStatus(UPDATED_AUDIT_STATUS);
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
        qualityObjectives.setId("existing_id");

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
            .andExpect(jsonPath("$.[*].id").value(hasItem(qualityObjectives.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].objectives").value(hasItem(DEFAULT_OBJECTIVES)))
            .andExpect(jsonPath("$.[*].qualitytype").value(hasItem(DEFAULT_QUALITYTYPE.toString())))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].target").value(hasItem(DEFAULT_TARGET)))
            .andExpect(jsonPath("$.[*].statisticalmethod").value(hasItem(DEFAULT_STATISTICALMETHOD)))
            .andExpect(jsonPath("$.[*].statisticalfrequency").value(hasItem(DEFAULT_STATISTICALFREQUENCY)))
            .andExpect(jsonPath("$.[*].istarget").value(hasItem(DEFAULT_ISTARGET)))
            .andExpect(jsonPath("$.[*].progress").value(hasItem(DEFAULT_PROGRESS)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].problems").value(hasItem(DEFAULT_PROBLEMS)))
            .andExpect(jsonPath("$.[*].improvementmeasures").value(hasItem(DEFAULT_IMPROVEMENTMEASURES)))
            .andExpect(jsonPath("$.[*].returntime").value(hasItem(DEFAULT_RETURNTIME.toString())))
            .andExpect(jsonPath("$.[*].createtime").value(hasItem(DEFAULT_CREATETIME.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllQualityObjectivesWithEagerRelationshipsIsEnabled() throws Exception {
        when(qualityObjectivesServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restQualityObjectivesMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(qualityObjectivesServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllQualityObjectivesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(qualityObjectivesServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restQualityObjectivesMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(qualityObjectivesRepositoryMock, times(1)).findAll(any(Pageable.class));
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
            .andExpect(jsonPath("$.id").value(qualityObjectives.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.objectives").value(DEFAULT_OBJECTIVES))
            .andExpect(jsonPath("$.qualitytype").value(DEFAULT_QUALITYTYPE.toString()))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.target").value(DEFAULT_TARGET))
            .andExpect(jsonPath("$.statisticalmethod").value(DEFAULT_STATISTICALMETHOD))
            .andExpect(jsonPath("$.statisticalfrequency").value(DEFAULT_STATISTICALFREQUENCY))
            .andExpect(jsonPath("$.istarget").value(DEFAULT_ISTARGET))
            .andExpect(jsonPath("$.progress").value(DEFAULT_PROGRESS))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.problems").value(DEFAULT_PROBLEMS))
            .andExpect(jsonPath("$.improvementmeasures").value(DEFAULT_IMPROVEMENTMEASURES))
            .andExpect(jsonPath("$.returntime").value(DEFAULT_RETURNTIME.toString()))
            .andExpect(jsonPath("$.createtime").value(DEFAULT_CREATETIME.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingQualityObjectives() throws Exception {
        // Get the qualityObjectives
        restQualityObjectivesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
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
            .objectives(UPDATED_OBJECTIVES)
            .qualitytype(UPDATED_QUALITYTYPE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .target(UPDATED_TARGET)
            .statisticalmethod(UPDATED_STATISTICALMETHOD)
            .statisticalfrequency(UPDATED_STATISTICALFREQUENCY)
            .istarget(UPDATED_ISTARGET)
            .progress(UPDATED_PROGRESS)
            .description(UPDATED_DESCRIPTION)
            .problems(UPDATED_PROBLEMS)
            .improvementmeasures(UPDATED_IMPROVEMENTMEASURES)
            .returntime(UPDATED_RETURNTIME)
            .createtime(UPDATED_CREATETIME)
            .auditStatus(UPDATED_AUDIT_STATUS);

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
        qualityObjectives.setId(UUID.randomUUID().toString());

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
        qualityObjectives.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityObjectivesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
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
        qualityObjectives.setId(UUID.randomUUID().toString());

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
            .name(UPDATED_NAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .statisticalmethod(UPDATED_STATISTICALMETHOD)
            .progress(UPDATED_PROGRESS)
            .description(UPDATED_DESCRIPTION)
            .problems(UPDATED_PROBLEMS)
            .improvementmeasures(UPDATED_IMPROVEMENTMEASURES)
            .returntime(UPDATED_RETURNTIME);

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
            .objectives(UPDATED_OBJECTIVES)
            .qualitytype(UPDATED_QUALITYTYPE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .target(UPDATED_TARGET)
            .statisticalmethod(UPDATED_STATISTICALMETHOD)
            .statisticalfrequency(UPDATED_STATISTICALFREQUENCY)
            .istarget(UPDATED_ISTARGET)
            .progress(UPDATED_PROGRESS)
            .description(UPDATED_DESCRIPTION)
            .problems(UPDATED_PROBLEMS)
            .improvementmeasures(UPDATED_IMPROVEMENTMEASURES)
            .returntime(UPDATED_RETURNTIME)
            .createtime(UPDATED_CREATETIME)
            .auditStatus(UPDATED_AUDIT_STATUS);

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
        qualityObjectives.setId(UUID.randomUUID().toString());

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
        qualityObjectives.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityObjectivesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
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
        qualityObjectives.setId(UUID.randomUUID().toString());

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

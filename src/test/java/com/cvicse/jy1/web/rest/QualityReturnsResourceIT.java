package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.QualityReturnsAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.QualityReturns;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.QualityType;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.repository.QualityReturnsRepository;
import com.cvicse.jy1.service.QualityReturnsService;
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
 * Integration tests for the {@link QualityReturnsResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class QualityReturnsResourceIT {

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

    private static final String ENTITY_API_URL = "/api/quality-returns";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QualityReturnsRepository qualityReturnsRepository;

    @Mock
    private QualityReturnsRepository qualityReturnsRepositoryMock;

    @Mock
    private QualityReturnsService qualityReturnsServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQualityReturnsMockMvc;

    private QualityReturns qualityReturns;

    private QualityReturns insertedQualityReturns;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QualityReturns createEntity(EntityManager em) {
        QualityReturns qualityReturns = new QualityReturns()
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
        return qualityReturns;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static QualityReturns createUpdatedEntity(EntityManager em) {
        QualityReturns qualityReturns = new QualityReturns()
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
        return qualityReturns;
    }

    @BeforeEach
    public void initTest() {
        qualityReturns = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedQualityReturns != null) {
            qualityReturnsRepository.delete(insertedQualityReturns);
            insertedQualityReturns = null;
        }
    }

    @Test
    @Transactional
    void createQualityReturns() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the QualityReturns
        var returnedQualityReturns = om.readValue(
            restQualityReturnsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityReturns)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            QualityReturns.class
        );

        // Validate the QualityReturns in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertQualityReturnsUpdatableFieldsEquals(returnedQualityReturns, getPersistedQualityReturns(returnedQualityReturns));

        insertedQualityReturns = returnedQualityReturns;
    }

    @Test
    @Transactional
    void createQualityReturnsWithExistingId() throws Exception {
        // Create the QualityReturns with an existing ID
        qualityReturns.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQualityReturnsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityReturns)))
            .andExpect(status().isBadRequest());

        // Validate the QualityReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQualityReturns() throws Exception {
        // Initialize the database
        insertedQualityReturns = qualityReturnsRepository.saveAndFlush(qualityReturns);

        // Get all the qualityReturnsList
        restQualityReturnsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qualityReturns.getId())))
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
    void getAllQualityReturnsWithEagerRelationshipsIsEnabled() throws Exception {
        when(qualityReturnsServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restQualityReturnsMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(qualityReturnsServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllQualityReturnsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(qualityReturnsServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restQualityReturnsMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(qualityReturnsRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getQualityReturns() throws Exception {
        // Initialize the database
        insertedQualityReturns = qualityReturnsRepository.saveAndFlush(qualityReturns);

        // Get the qualityReturns
        restQualityReturnsMockMvc
            .perform(get(ENTITY_API_URL_ID, qualityReturns.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(qualityReturns.getId()))
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
    void getNonExistingQualityReturns() throws Exception {
        // Get the qualityReturns
        restQualityReturnsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQualityReturns() throws Exception {
        // Initialize the database
        insertedQualityReturns = qualityReturnsRepository.saveAndFlush(qualityReturns);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityReturns
        QualityReturns updatedQualityReturns = qualityReturnsRepository.findById(qualityReturns.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedQualityReturns are not directly saved in db
        em.detach(updatedQualityReturns);
        updatedQualityReturns
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

        restQualityReturnsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedQualityReturns.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedQualityReturns))
            )
            .andExpect(status().isOk());

        // Validate the QualityReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQualityReturnsToMatchAllProperties(updatedQualityReturns);
    }

    @Test
    @Transactional
    void putNonExistingQualityReturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityReturns.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualityReturnsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, qualityReturns.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualityReturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQualityReturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityReturns.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityReturnsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualityReturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQualityReturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityReturns.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityReturnsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityReturns)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QualityReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQualityReturnsWithPatch() throws Exception {
        // Initialize the database
        insertedQualityReturns = qualityReturnsRepository.saveAndFlush(qualityReturns);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityReturns using partial update
        QualityReturns partialUpdatedQualityReturns = new QualityReturns();
        partialUpdatedQualityReturns.setId(qualityReturns.getId());

        partialUpdatedQualityReturns.improvementmeasures(UPDATED_IMPROVEMENTMEASURES).auditStatus(UPDATED_AUDIT_STATUS);

        restQualityReturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualityReturns.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualityReturns))
            )
            .andExpect(status().isOk());

        // Validate the QualityReturns in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualityReturnsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedQualityReturns, qualityReturns),
            getPersistedQualityReturns(qualityReturns)
        );
    }

    @Test
    @Transactional
    void fullUpdateQualityReturnsWithPatch() throws Exception {
        // Initialize the database
        insertedQualityReturns = qualityReturnsRepository.saveAndFlush(qualityReturns);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityReturns using partial update
        QualityReturns partialUpdatedQualityReturns = new QualityReturns();
        partialUpdatedQualityReturns.setId(qualityReturns.getId());

        partialUpdatedQualityReturns
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

        restQualityReturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualityReturns.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualityReturns))
            )
            .andExpect(status().isOk());

        // Validate the QualityReturns in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualityReturnsUpdatableFieldsEquals(partialUpdatedQualityReturns, getPersistedQualityReturns(partialUpdatedQualityReturns));
    }

    @Test
    @Transactional
    void patchNonExistingQualityReturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityReturns.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualityReturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, qualityReturns.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualityReturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQualityReturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityReturns.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityReturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualityReturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the QualityReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQualityReturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityReturns.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityReturnsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(qualityReturns)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the QualityReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQualityReturns() throws Exception {
        // Initialize the database
        insertedQualityReturns = qualityReturnsRepository.saveAndFlush(qualityReturns);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the qualityReturns
        restQualityReturnsMockMvc
            .perform(delete(ENTITY_API_URL_ID, qualityReturns.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return qualityReturnsRepository.count();
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

    protected QualityReturns getPersistedQualityReturns(QualityReturns qualityReturns) {
        return qualityReturnsRepository.findById(qualityReturns.getId()).orElseThrow();
    }

    protected void assertPersistedQualityReturnsToMatchAllProperties(QualityReturns expectedQualityReturns) {
        assertQualityReturnsAllPropertiesEquals(expectedQualityReturns, getPersistedQualityReturns(expectedQualityReturns));
    }

    protected void assertPersistedQualityReturnsToMatchUpdatableProperties(QualityReturns expectedQualityReturns) {
        assertQualityReturnsAllUpdatablePropertiesEquals(expectedQualityReturns, getPersistedQualityReturns(expectedQualityReturns));
    }
}

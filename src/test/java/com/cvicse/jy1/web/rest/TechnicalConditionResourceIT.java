package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.TechnicalConditionAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.TechnicalCondition;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.repository.TechnicalConditionRepository;
import com.cvicse.jy1.service.TechnicalConditionService;
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
 * Integration tests for the {@link TechnicalConditionResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class TechnicalConditionResourceIT {

    private static final String DEFAULT_CAPTION = "AAAAAAAAAA";
    private static final String UPDATED_CAPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECTNAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTNAME = "BBBBBBBBBB";

    private static final Long DEFAULT_DECUMENTID = 1L;
    private static final Long UPDATED_DECUMENTID = 2L;

    private static final String DEFAULT_CLAIMANT = "AAAAAAAAAA";
    private static final String UPDATED_CLAIMANT = "BBBBBBBBBB";

    private static final String DEFAULT_APPLICANT = "AAAAAAAAAA";
    private static final String UPDATED_APPLICANT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_APPLICANTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_APPLICANTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_VALIDRANGE = "AAAAAAAAAA";
    private static final String UPDATED_VALIDRANGE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATETIME = LocalDate.now(ZoneId.systemDefault());

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/technical-conditions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TechnicalConditionRepository technicalConditionRepository;

    @Mock
    private TechnicalConditionRepository technicalConditionRepositoryMock;

    @Mock
    private TechnicalConditionService technicalConditionServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTechnicalConditionMockMvc;

    private TechnicalCondition technicalCondition;

    private TechnicalCondition insertedTechnicalCondition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TechnicalCondition createEntity(EntityManager em) {
        TechnicalCondition technicalCondition = new TechnicalCondition()
            .caption(DEFAULT_CAPTION)
            .projectname(DEFAULT_PROJECTNAME)
            .decumentid(DEFAULT_DECUMENTID)
            .claimant(DEFAULT_CLAIMANT)
            .applicant(DEFAULT_APPLICANT)
            .applicanttime(DEFAULT_APPLICANTTIME)
            .validrange(DEFAULT_VALIDRANGE)
            .createtime(DEFAULT_CREATETIME)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return technicalCondition;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TechnicalCondition createUpdatedEntity(EntityManager em) {
        TechnicalCondition technicalCondition = new TechnicalCondition()
            .caption(UPDATED_CAPTION)
            .projectname(UPDATED_PROJECTNAME)
            .decumentid(UPDATED_DECUMENTID)
            .claimant(UPDATED_CLAIMANT)
            .applicant(UPDATED_APPLICANT)
            .applicanttime(UPDATED_APPLICANTTIME)
            .validrange(UPDATED_VALIDRANGE)
            .createtime(UPDATED_CREATETIME)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return technicalCondition;
    }

    @BeforeEach
    public void initTest() {
        technicalCondition = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedTechnicalCondition != null) {
            technicalConditionRepository.delete(insertedTechnicalCondition);
            insertedTechnicalCondition = null;
        }
    }

    @Test
    @Transactional
    void createTechnicalCondition() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TechnicalCondition
        var returnedTechnicalCondition = om.readValue(
            restTechnicalConditionMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(technicalCondition)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TechnicalCondition.class
        );

        // Validate the TechnicalCondition in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertTechnicalConditionUpdatableFieldsEquals(
            returnedTechnicalCondition,
            getPersistedTechnicalCondition(returnedTechnicalCondition)
        );

        insertedTechnicalCondition = returnedTechnicalCondition;
    }

    @Test
    @Transactional
    void createTechnicalConditionWithExistingId() throws Exception {
        // Create the TechnicalCondition with an existing ID
        technicalCondition.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTechnicalConditionMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(technicalCondition)))
            .andExpect(status().isBadRequest());

        // Validate the TechnicalCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTechnicalConditions() throws Exception {
        // Initialize the database
        insertedTechnicalCondition = technicalConditionRepository.saveAndFlush(technicalCondition);

        // Get all the technicalConditionList
        restTechnicalConditionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(technicalCondition.getId())))
            .andExpect(jsonPath("$.[*].caption").value(hasItem(DEFAULT_CAPTION)))
            .andExpect(jsonPath("$.[*].projectname").value(hasItem(DEFAULT_PROJECTNAME)))
            .andExpect(jsonPath("$.[*].decumentid").value(hasItem(DEFAULT_DECUMENTID.intValue())))
            .andExpect(jsonPath("$.[*].claimant").value(hasItem(DEFAULT_CLAIMANT)))
            .andExpect(jsonPath("$.[*].applicant").value(hasItem(DEFAULT_APPLICANT)))
            .andExpect(jsonPath("$.[*].applicanttime").value(hasItem(DEFAULT_APPLICANTTIME.toString())))
            .andExpect(jsonPath("$.[*].validrange").value(hasItem(DEFAULT_VALIDRANGE)))
            .andExpect(jsonPath("$.[*].createtime").value(hasItem(DEFAULT_CREATETIME.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllTechnicalConditionsWithEagerRelationshipsIsEnabled() throws Exception {
        when(technicalConditionServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTechnicalConditionMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(technicalConditionServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllTechnicalConditionsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(technicalConditionServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restTechnicalConditionMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(technicalConditionRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getTechnicalCondition() throws Exception {
        // Initialize the database
        insertedTechnicalCondition = technicalConditionRepository.saveAndFlush(technicalCondition);

        // Get the technicalCondition
        restTechnicalConditionMockMvc
            .perform(get(ENTITY_API_URL_ID, technicalCondition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(technicalCondition.getId()))
            .andExpect(jsonPath("$.caption").value(DEFAULT_CAPTION))
            .andExpect(jsonPath("$.projectname").value(DEFAULT_PROJECTNAME))
            .andExpect(jsonPath("$.decumentid").value(DEFAULT_DECUMENTID.intValue()))
            .andExpect(jsonPath("$.claimant").value(DEFAULT_CLAIMANT))
            .andExpect(jsonPath("$.applicant").value(DEFAULT_APPLICANT))
            .andExpect(jsonPath("$.applicanttime").value(DEFAULT_APPLICANTTIME.toString()))
            .andExpect(jsonPath("$.validrange").value(DEFAULT_VALIDRANGE))
            .andExpect(jsonPath("$.createtime").value(DEFAULT_CREATETIME.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTechnicalCondition() throws Exception {
        // Get the technicalCondition
        restTechnicalConditionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTechnicalCondition() throws Exception {
        // Initialize the database
        insertedTechnicalCondition = technicalConditionRepository.saveAndFlush(technicalCondition);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the technicalCondition
        TechnicalCondition updatedTechnicalCondition = technicalConditionRepository.findById(technicalCondition.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTechnicalCondition are not directly saved in db
        em.detach(updatedTechnicalCondition);
        updatedTechnicalCondition
            .caption(UPDATED_CAPTION)
            .projectname(UPDATED_PROJECTNAME)
            .decumentid(UPDATED_DECUMENTID)
            .claimant(UPDATED_CLAIMANT)
            .applicant(UPDATED_APPLICANT)
            .applicanttime(UPDATED_APPLICANTTIME)
            .validrange(UPDATED_VALIDRANGE)
            .createtime(UPDATED_CREATETIME)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restTechnicalConditionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTechnicalCondition.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedTechnicalCondition))
            )
            .andExpect(status().isOk());

        // Validate the TechnicalCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTechnicalConditionToMatchAllProperties(updatedTechnicalCondition);
    }

    @Test
    @Transactional
    void putNonExistingTechnicalCondition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technicalCondition.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTechnicalConditionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, technicalCondition.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(technicalCondition))
            )
            .andExpect(status().isBadRequest());

        // Validate the TechnicalCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTechnicalCondition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technicalCondition.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTechnicalConditionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(technicalCondition))
            )
            .andExpect(status().isBadRequest());

        // Validate the TechnicalCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTechnicalCondition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technicalCondition.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTechnicalConditionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(technicalCondition)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TechnicalCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTechnicalConditionWithPatch() throws Exception {
        // Initialize the database
        insertedTechnicalCondition = technicalConditionRepository.saveAndFlush(technicalCondition);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the technicalCondition using partial update
        TechnicalCondition partialUpdatedTechnicalCondition = new TechnicalCondition();
        partialUpdatedTechnicalCondition.setId(technicalCondition.getId());

        partialUpdatedTechnicalCondition.projectname(UPDATED_PROJECTNAME).validrange(UPDATED_VALIDRANGE);

        restTechnicalConditionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTechnicalCondition.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTechnicalCondition))
            )
            .andExpect(status().isOk());

        // Validate the TechnicalCondition in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTechnicalConditionUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTechnicalCondition, technicalCondition),
            getPersistedTechnicalCondition(technicalCondition)
        );
    }

    @Test
    @Transactional
    void fullUpdateTechnicalConditionWithPatch() throws Exception {
        // Initialize the database
        insertedTechnicalCondition = technicalConditionRepository.saveAndFlush(technicalCondition);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the technicalCondition using partial update
        TechnicalCondition partialUpdatedTechnicalCondition = new TechnicalCondition();
        partialUpdatedTechnicalCondition.setId(technicalCondition.getId());

        partialUpdatedTechnicalCondition
            .caption(UPDATED_CAPTION)
            .projectname(UPDATED_PROJECTNAME)
            .decumentid(UPDATED_DECUMENTID)
            .claimant(UPDATED_CLAIMANT)
            .applicant(UPDATED_APPLICANT)
            .applicanttime(UPDATED_APPLICANTTIME)
            .validrange(UPDATED_VALIDRANGE)
            .createtime(UPDATED_CREATETIME)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restTechnicalConditionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTechnicalCondition.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTechnicalCondition))
            )
            .andExpect(status().isOk());

        // Validate the TechnicalCondition in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTechnicalConditionUpdatableFieldsEquals(
            partialUpdatedTechnicalCondition,
            getPersistedTechnicalCondition(partialUpdatedTechnicalCondition)
        );
    }

    @Test
    @Transactional
    void patchNonExistingTechnicalCondition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technicalCondition.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTechnicalConditionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, technicalCondition.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(technicalCondition))
            )
            .andExpect(status().isBadRequest());

        // Validate the TechnicalCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTechnicalCondition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technicalCondition.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTechnicalConditionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(technicalCondition))
            )
            .andExpect(status().isBadRequest());

        // Validate the TechnicalCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTechnicalCondition() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        technicalCondition.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTechnicalConditionMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(technicalCondition)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TechnicalCondition in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTechnicalCondition() throws Exception {
        // Initialize the database
        insertedTechnicalCondition = technicalConditionRepository.saveAndFlush(technicalCondition);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the technicalCondition
        restTechnicalConditionMockMvc
            .perform(delete(ENTITY_API_URL_ID, technicalCondition.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return technicalConditionRepository.count();
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

    protected TechnicalCondition getPersistedTechnicalCondition(TechnicalCondition technicalCondition) {
        return technicalConditionRepository.findById(technicalCondition.getId()).orElseThrow();
    }

    protected void assertPersistedTechnicalConditionToMatchAllProperties(TechnicalCondition expectedTechnicalCondition) {
        assertTechnicalConditionAllPropertiesEquals(expectedTechnicalCondition, getPersistedTechnicalCondition(expectedTechnicalCondition));
    }

    protected void assertPersistedTechnicalConditionToMatchUpdatableProperties(TechnicalCondition expectedTechnicalCondition) {
        assertTechnicalConditionAllUpdatablePropertiesEquals(
            expectedTechnicalCondition,
            getPersistedTechnicalCondition(expectedTechnicalCondition)
        );
    }
}

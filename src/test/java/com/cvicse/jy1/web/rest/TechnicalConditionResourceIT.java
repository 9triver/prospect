package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.TechnicalConditionAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.TechnicalCondition;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.repository.TechnicalConditionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link TechnicalConditionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TechnicalConditionResourceIT {

    private static final String DEFAULT_WORKBAGID = "AAAAAAAAAA";
    private static final String UPDATED_WORKBAGID = "BBBBBBBBBB";

    private static final String DEFAULT_BELONGWBSID = "AAAAAAAAAA";
    private static final String UPDATED_BELONGWBSID = "BBBBBBBBBB";

    private static final String DEFAULT_OUTSOURCINGCONTRACTID = "AAAAAAAAAA";
    private static final String UPDATED_OUTSOURCINGCONTRACTID = "BBBBBBBBBB";

    private static final String DEFAULT_TECHNICALID = "AAAAAAAAAA";
    private static final String UPDATED_TECHNICALID = "BBBBBBBBBB";

    private static final String DEFAULT_TECHNICALNAME = "AAAAAAAAAA";
    private static final String UPDATED_TECHNICALNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CHANGEDFILENAME = "AAAAAAAAAA";
    private static final String UPDATED_CHANGEDFILENAME = "BBBBBBBBBB";

    private static final String DEFAULT_APPLICANT = "AAAAAAAAAA";
    private static final String UPDATED_APPLICANT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_APPLICATIONDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_APPLICATIONDATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CHANGEDREASON = "AAAAAAAAAA";
    private static final String UPDATED_CHANGEDREASON = "BBBBBBBBBB";

    private static final String DEFAULT_CHANGEDBEFORE = "AAAAAAAAAA";
    private static final String UPDATED_CHANGEDBEFORE = "BBBBBBBBBB";

    private static final String DEFAULT_CHANGEDAFTER = "AAAAAAAAAA";
    private static final String UPDATED_CHANGEDAFTER = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRIBUTIONRANGE = "AAAAAAAAAA";
    private static final String UPDATED_DISTRIBUTIONRANGE = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.NOT_AUDITED;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.IN_AUDIT;

    private static final String ENTITY_API_URL = "/api/technical-conditions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TechnicalConditionRepository technicalConditionRepository;

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
            .workbagid(DEFAULT_WORKBAGID)
            .belongwbsid(DEFAULT_BELONGWBSID)
            .outsourcingcontractid(DEFAULT_OUTSOURCINGCONTRACTID)
            .technicalid(DEFAULT_TECHNICALID)
            .technicalname(DEFAULT_TECHNICALNAME)
            .changedfilename(DEFAULT_CHANGEDFILENAME)
            .applicant(DEFAULT_APPLICANT)
            .applicationdate(DEFAULT_APPLICATIONDATE)
            .changedreason(DEFAULT_CHANGEDREASON)
            .changedbefore(DEFAULT_CHANGEDBEFORE)
            .changedafter(DEFAULT_CHANGEDAFTER)
            .distributionrange(DEFAULT_DISTRIBUTIONRANGE)
            .remarks(DEFAULT_REMARKS)
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
            .workbagid(UPDATED_WORKBAGID)
            .belongwbsid(UPDATED_BELONGWBSID)
            .outsourcingcontractid(UPDATED_OUTSOURCINGCONTRACTID)
            .technicalid(UPDATED_TECHNICALID)
            .technicalname(UPDATED_TECHNICALNAME)
            .changedfilename(UPDATED_CHANGEDFILENAME)
            .applicant(UPDATED_APPLICANT)
            .applicationdate(UPDATED_APPLICATIONDATE)
            .changedreason(UPDATED_CHANGEDREASON)
            .changedbefore(UPDATED_CHANGEDBEFORE)
            .changedafter(UPDATED_CHANGEDAFTER)
            .distributionrange(UPDATED_DISTRIBUTIONRANGE)
            .remarks(UPDATED_REMARKS)
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
        technicalCondition.setId(1);

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
            .andExpect(jsonPath("$.[*].id").value(hasItem(technicalCondition.getId().intValue())))
            .andExpect(jsonPath("$.[*].workbagid").value(hasItem(DEFAULT_WORKBAGID)))
            .andExpect(jsonPath("$.[*].belongwbsid").value(hasItem(DEFAULT_BELONGWBSID)))
            .andExpect(jsonPath("$.[*].outsourcingcontractid").value(hasItem(DEFAULT_OUTSOURCINGCONTRACTID)))
            .andExpect(jsonPath("$.[*].technicalid").value(hasItem(DEFAULT_TECHNICALID)))
            .andExpect(jsonPath("$.[*].technicalname").value(hasItem(DEFAULT_TECHNICALNAME)))
            .andExpect(jsonPath("$.[*].changedfilename").value(hasItem(DEFAULT_CHANGEDFILENAME)))
            .andExpect(jsonPath("$.[*].applicant").value(hasItem(DEFAULT_APPLICANT)))
            .andExpect(jsonPath("$.[*].applicationdate").value(hasItem(DEFAULT_APPLICATIONDATE.toString())))
            .andExpect(jsonPath("$.[*].changedreason").value(hasItem(DEFAULT_CHANGEDREASON)))
            .andExpect(jsonPath("$.[*].changedbefore").value(hasItem(DEFAULT_CHANGEDBEFORE)))
            .andExpect(jsonPath("$.[*].changedafter").value(hasItem(DEFAULT_CHANGEDAFTER)))
            .andExpect(jsonPath("$.[*].distributionrange").value(hasItem(DEFAULT_DISTRIBUTIONRANGE)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
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
            .andExpect(jsonPath("$.id").value(technicalCondition.getId().intValue()))
            .andExpect(jsonPath("$.workbagid").value(DEFAULT_WORKBAGID))
            .andExpect(jsonPath("$.belongwbsid").value(DEFAULT_BELONGWBSID))
            .andExpect(jsonPath("$.outsourcingcontractid").value(DEFAULT_OUTSOURCINGCONTRACTID))
            .andExpect(jsonPath("$.technicalid").value(DEFAULT_TECHNICALID))
            .andExpect(jsonPath("$.technicalname").value(DEFAULT_TECHNICALNAME))
            .andExpect(jsonPath("$.changedfilename").value(DEFAULT_CHANGEDFILENAME))
            .andExpect(jsonPath("$.applicant").value(DEFAULT_APPLICANT))
            .andExpect(jsonPath("$.applicationdate").value(DEFAULT_APPLICATIONDATE.toString()))
            .andExpect(jsonPath("$.changedreason").value(DEFAULT_CHANGEDREASON))
            .andExpect(jsonPath("$.changedbefore").value(DEFAULT_CHANGEDBEFORE))
            .andExpect(jsonPath("$.changedafter").value(DEFAULT_CHANGEDAFTER))
            .andExpect(jsonPath("$.distributionrange").value(DEFAULT_DISTRIBUTIONRANGE))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingTechnicalCondition() throws Exception {
        // Get the technicalCondition
        restTechnicalConditionMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
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
            .workbagid(UPDATED_WORKBAGID)
            .belongwbsid(UPDATED_BELONGWBSID)
            .outsourcingcontractid(UPDATED_OUTSOURCINGCONTRACTID)
            .technicalid(UPDATED_TECHNICALID)
            .technicalname(UPDATED_TECHNICALNAME)
            .changedfilename(UPDATED_CHANGEDFILENAME)
            .applicant(UPDATED_APPLICANT)
            .applicationdate(UPDATED_APPLICATIONDATE)
            .changedreason(UPDATED_CHANGEDREASON)
            .changedbefore(UPDATED_CHANGEDBEFORE)
            .changedafter(UPDATED_CHANGEDAFTER)
            .distributionrange(UPDATED_DISTRIBUTIONRANGE)
            .remarks(UPDATED_REMARKS)
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
        technicalCondition.setId(intCount.incrementAndGet());

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
        technicalCondition.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTechnicalConditionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
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
        technicalCondition.setId(intCount.incrementAndGet());

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

        partialUpdatedTechnicalCondition
            .outsourcingcontractid(UPDATED_OUTSOURCINGCONTRACTID)
            .changedfilename(UPDATED_CHANGEDFILENAME)
            .changedbefore(UPDATED_CHANGEDBEFORE)
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
            .workbagid(UPDATED_WORKBAGID)
            .belongwbsid(UPDATED_BELONGWBSID)
            .outsourcingcontractid(UPDATED_OUTSOURCINGCONTRACTID)
            .technicalid(UPDATED_TECHNICALID)
            .technicalname(UPDATED_TECHNICALNAME)
            .changedfilename(UPDATED_CHANGEDFILENAME)
            .applicant(UPDATED_APPLICANT)
            .applicationdate(UPDATED_APPLICATIONDATE)
            .changedreason(UPDATED_CHANGEDREASON)
            .changedbefore(UPDATED_CHANGEDBEFORE)
            .changedafter(UPDATED_CHANGEDAFTER)
            .distributionrange(UPDATED_DISTRIBUTIONRANGE)
            .remarks(UPDATED_REMARKS)
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
        technicalCondition.setId(intCount.incrementAndGet());

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
        technicalCondition.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTechnicalConditionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
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
        technicalCondition.setId(intCount.incrementAndGet());

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

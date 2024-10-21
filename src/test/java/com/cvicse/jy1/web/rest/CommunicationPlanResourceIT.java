package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.CommunicationPlanAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.CommunicationPlan;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.repository.CommunicationPlanRepository;
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
 * Integration tests for the {@link CommunicationPlanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CommunicationPlanResourceIT {

    private static final String DEFAULT_WBSID = "AAAAAAAAAA";
    private static final String UPDATED_WBSID = "BBBBBBBBBB";

    private static final String DEFAULT_COMMUNICATIONTOPIC = "AAAAAAAAAA";
    private static final String UPDATED_COMMUNICATIONTOPIC = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_COMMUNICATIONTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_COMMUNICATIONTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_WORKTARGET = "AAAAAAAAAA";
    private static final String UPDATED_WORKTARGET = "BBBBBBBBBB";

    private static final String DEFAULT_WORKCONTENT = "AAAAAAAAAA";
    private static final String UPDATED_WORKCONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.NOT_AUDITED;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.IN_AUDIT;

    private static final String ENTITY_API_URL = "/api/communication-plans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CommunicationPlanRepository communicationPlanRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCommunicationPlanMockMvc;

    private CommunicationPlan communicationPlan;

    private CommunicationPlan insertedCommunicationPlan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommunicationPlan createEntity(EntityManager em) {
        CommunicationPlan communicationPlan = new CommunicationPlan()
            .wbsid(DEFAULT_WBSID)
            .communicationtopic(DEFAULT_COMMUNICATIONTOPIC)
            .communicationtime(DEFAULT_COMMUNICATIONTIME)
            .worktarget(DEFAULT_WORKTARGET)
            .workcontent(DEFAULT_WORKCONTENT)
            .remarks(DEFAULT_REMARKS)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return communicationPlan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CommunicationPlan createUpdatedEntity(EntityManager em) {
        CommunicationPlan communicationPlan = new CommunicationPlan()
            .wbsid(UPDATED_WBSID)
            .communicationtopic(UPDATED_COMMUNICATIONTOPIC)
            .communicationtime(UPDATED_COMMUNICATIONTIME)
            .worktarget(UPDATED_WORKTARGET)
            .workcontent(UPDATED_WORKCONTENT)
            .remarks(UPDATED_REMARKS)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return communicationPlan;
    }

    @BeforeEach
    public void initTest() {
        communicationPlan = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedCommunicationPlan != null) {
            communicationPlanRepository.delete(insertedCommunicationPlan);
            insertedCommunicationPlan = null;
        }
    }

    @Test
    @Transactional
    void createCommunicationPlan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CommunicationPlan
        var returnedCommunicationPlan = om.readValue(
            restCommunicationPlanMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(communicationPlan)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CommunicationPlan.class
        );

        // Validate the CommunicationPlan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCommunicationPlanUpdatableFieldsEquals(returnedCommunicationPlan, getPersistedCommunicationPlan(returnedCommunicationPlan));

        insertedCommunicationPlan = returnedCommunicationPlan;
    }

    @Test
    @Transactional
    void createCommunicationPlanWithExistingId() throws Exception {
        // Create the CommunicationPlan with an existing ID
        communicationPlan.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCommunicationPlanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(communicationPlan)))
            .andExpect(status().isBadRequest());

        // Validate the CommunicationPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCommunicationPlans() throws Exception {
        // Initialize the database
        insertedCommunicationPlan = communicationPlanRepository.saveAndFlush(communicationPlan);

        // Get all the communicationPlanList
        restCommunicationPlanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(communicationPlan.getId().intValue())))
            .andExpect(jsonPath("$.[*].wbsid").value(hasItem(DEFAULT_WBSID)))
            .andExpect(jsonPath("$.[*].communicationtopic").value(hasItem(DEFAULT_COMMUNICATIONTOPIC)))
            .andExpect(jsonPath("$.[*].communicationtime").value(hasItem(DEFAULT_COMMUNICATIONTIME.toString())))
            .andExpect(jsonPath("$.[*].worktarget").value(hasItem(DEFAULT_WORKTARGET)))
            .andExpect(jsonPath("$.[*].workcontent").value(hasItem(DEFAULT_WORKCONTENT)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getCommunicationPlan() throws Exception {
        // Initialize the database
        insertedCommunicationPlan = communicationPlanRepository.saveAndFlush(communicationPlan);

        // Get the communicationPlan
        restCommunicationPlanMockMvc
            .perform(get(ENTITY_API_URL_ID, communicationPlan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(communicationPlan.getId().intValue()))
            .andExpect(jsonPath("$.wbsid").value(DEFAULT_WBSID))
            .andExpect(jsonPath("$.communicationtopic").value(DEFAULT_COMMUNICATIONTOPIC))
            .andExpect(jsonPath("$.communicationtime").value(DEFAULT_COMMUNICATIONTIME.toString()))
            .andExpect(jsonPath("$.worktarget").value(DEFAULT_WORKTARGET))
            .andExpect(jsonPath("$.workcontent").value(DEFAULT_WORKCONTENT))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingCommunicationPlan() throws Exception {
        // Get the communicationPlan
        restCommunicationPlanMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCommunicationPlan() throws Exception {
        // Initialize the database
        insertedCommunicationPlan = communicationPlanRepository.saveAndFlush(communicationPlan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the communicationPlan
        CommunicationPlan updatedCommunicationPlan = communicationPlanRepository.findById(communicationPlan.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCommunicationPlan are not directly saved in db
        em.detach(updatedCommunicationPlan);
        updatedCommunicationPlan
            .wbsid(UPDATED_WBSID)
            .communicationtopic(UPDATED_COMMUNICATIONTOPIC)
            .communicationtime(UPDATED_COMMUNICATIONTIME)
            .worktarget(UPDATED_WORKTARGET)
            .workcontent(UPDATED_WORKCONTENT)
            .remarks(UPDATED_REMARKS)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restCommunicationPlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCommunicationPlan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCommunicationPlan))
            )
            .andExpect(status().isOk());

        // Validate the CommunicationPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCommunicationPlanToMatchAllProperties(updatedCommunicationPlan);
    }

    @Test
    @Transactional
    void putNonExistingCommunicationPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationPlan.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommunicationPlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, communicationPlan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(communicationPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommunicationPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCommunicationPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationPlan.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationPlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(communicationPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommunicationPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCommunicationPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationPlan.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationPlanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(communicationPlan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CommunicationPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCommunicationPlanWithPatch() throws Exception {
        // Initialize the database
        insertedCommunicationPlan = communicationPlanRepository.saveAndFlush(communicationPlan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the communicationPlan using partial update
        CommunicationPlan partialUpdatedCommunicationPlan = new CommunicationPlan();
        partialUpdatedCommunicationPlan.setId(communicationPlan.getId());

        partialUpdatedCommunicationPlan.communicationtime(UPDATED_COMMUNICATIONTIME);

        restCommunicationPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCommunicationPlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCommunicationPlan))
            )
            .andExpect(status().isOk());

        // Validate the CommunicationPlan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCommunicationPlanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCommunicationPlan, communicationPlan),
            getPersistedCommunicationPlan(communicationPlan)
        );
    }

    @Test
    @Transactional
    void fullUpdateCommunicationPlanWithPatch() throws Exception {
        // Initialize the database
        insertedCommunicationPlan = communicationPlanRepository.saveAndFlush(communicationPlan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the communicationPlan using partial update
        CommunicationPlan partialUpdatedCommunicationPlan = new CommunicationPlan();
        partialUpdatedCommunicationPlan.setId(communicationPlan.getId());

        partialUpdatedCommunicationPlan
            .wbsid(UPDATED_WBSID)
            .communicationtopic(UPDATED_COMMUNICATIONTOPIC)
            .communicationtime(UPDATED_COMMUNICATIONTIME)
            .worktarget(UPDATED_WORKTARGET)
            .workcontent(UPDATED_WORKCONTENT)
            .remarks(UPDATED_REMARKS)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restCommunicationPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCommunicationPlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCommunicationPlan))
            )
            .andExpect(status().isOk());

        // Validate the CommunicationPlan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCommunicationPlanUpdatableFieldsEquals(
            partialUpdatedCommunicationPlan,
            getPersistedCommunicationPlan(partialUpdatedCommunicationPlan)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCommunicationPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationPlan.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCommunicationPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, communicationPlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(communicationPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommunicationPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCommunicationPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationPlan.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(communicationPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the CommunicationPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCommunicationPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        communicationPlan.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCommunicationPlanMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(communicationPlan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CommunicationPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCommunicationPlan() throws Exception {
        // Initialize the database
        insertedCommunicationPlan = communicationPlanRepository.saveAndFlush(communicationPlan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the communicationPlan
        restCommunicationPlanMockMvc
            .perform(delete(ENTITY_API_URL_ID, communicationPlan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return communicationPlanRepository.count();
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

    protected CommunicationPlan getPersistedCommunicationPlan(CommunicationPlan communicationPlan) {
        return communicationPlanRepository.findById(communicationPlan.getId()).orElseThrow();
    }

    protected void assertPersistedCommunicationPlanToMatchAllProperties(CommunicationPlan expectedCommunicationPlan) {
        assertCommunicationPlanAllPropertiesEquals(expectedCommunicationPlan, getPersistedCommunicationPlan(expectedCommunicationPlan));
    }

    protected void assertPersistedCommunicationPlanToMatchUpdatableProperties(CommunicationPlan expectedCommunicationPlan) {
        assertCommunicationPlanAllUpdatablePropertiesEquals(
            expectedCommunicationPlan,
            getPersistedCommunicationPlan(expectedCommunicationPlan)
        );
    }
}

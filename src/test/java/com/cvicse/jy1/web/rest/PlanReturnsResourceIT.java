package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.PlanReturnsAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.PlanReturns;
import com.cvicse.jy1.domain.enumeration.PlanLevel;
import com.cvicse.jy1.domain.enumeration.Planstatus;
import com.cvicse.jy1.domain.enumeration.ReturnsStatus;
import com.cvicse.jy1.repository.PlanReturnsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link PlanReturnsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PlanReturnsResourceIT {

    private static final String DEFAULT_PLANRETURNSNAME = "AAAAAAAAAA";
    private static final String UPDATED_PLANRETURNSNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_PLANTYPE = 1;
    private static final Integer UPDATED_PLANTYPE = 2;

    private static final PlanLevel DEFAULT_PLANLEVEL = PlanLevel.CYCLE;
    private static final PlanLevel UPDATED_PLANLEVEL = PlanLevel.STAGE;

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ACTUALSTARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACTUALSTARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ACTUALENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACTUALENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_DELIVERABLES = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERABLES = "BBBBBBBBBB";

    private static final Integer DEFAULT_PROGRESS = 1;
    private static final Integer UPDATED_PROGRESS = 2;

    private static final Planstatus DEFAULT_STATUS = Planstatus.IN_DEADLINE;
    private static final Planstatus UPDATED_STATUS = Planstatus.OVERDUE;

    private static final String DEFAULT_IMPACTANALYSIS = "AAAAAAAAAA";
    private static final String UPDATED_IMPACTANALYSIS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RETURNSTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RETURNSTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REJECTIONREASON = "AAAAAAAAAA";
    private static final String UPDATED_REJECTIONREASON = "BBBBBBBBBB";

    private static final ReturnsStatus DEFAULT_RETURNSSTATUS = ReturnsStatus.Not_Comlated;
    private static final ReturnsStatus UPDATED_RETURNSSTATUS = ReturnsStatus.Completed;

    private static final String ENTITY_API_URL = "/api/plan-returns";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PlanReturnsRepository planReturnsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlanReturnsMockMvc;

    private PlanReturns planReturns;

    private PlanReturns insertedPlanReturns;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlanReturns createEntity(EntityManager em) {
        PlanReturns planReturns = new PlanReturns()
            .planreturnsname(DEFAULT_PLANRETURNSNAME)
            .plantype(DEFAULT_PLANTYPE)
            .planlevel(DEFAULT_PLANLEVEL)
            .description(DEFAULT_DESCRIPTION)
            .actualstarttime(DEFAULT_ACTUALSTARTTIME)
            .actualendtime(DEFAULT_ACTUALENDTIME)
            .deliverables(DEFAULT_DELIVERABLES)
            .progress(DEFAULT_PROGRESS)
            .status(DEFAULT_STATUS)
            .impactanalysis(DEFAULT_IMPACTANALYSIS)
            .returnstime(DEFAULT_RETURNSTIME)
            .rejectionreason(DEFAULT_REJECTIONREASON)
            .returnsstatus(DEFAULT_RETURNSSTATUS);
        return planReturns;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PlanReturns createUpdatedEntity(EntityManager em) {
        PlanReturns planReturns = new PlanReturns()
            .planreturnsname(UPDATED_PLANRETURNSNAME)
            .plantype(UPDATED_PLANTYPE)
            .planlevel(UPDATED_PLANLEVEL)
            .description(UPDATED_DESCRIPTION)
            .actualstarttime(UPDATED_ACTUALSTARTTIME)
            .actualendtime(UPDATED_ACTUALENDTIME)
            .deliverables(UPDATED_DELIVERABLES)
            .progress(UPDATED_PROGRESS)
            .status(UPDATED_STATUS)
            .impactanalysis(UPDATED_IMPACTANALYSIS)
            .returnstime(UPDATED_RETURNSTIME)
            .rejectionreason(UPDATED_REJECTIONREASON)
            .returnsstatus(UPDATED_RETURNSSTATUS);
        return planReturns;
    }

    @BeforeEach
    public void initTest() {
        planReturns = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedPlanReturns != null) {
            planReturnsRepository.delete(insertedPlanReturns);
            insertedPlanReturns = null;
        }
    }

    @Test
    @Transactional
    void createPlanReturns() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the PlanReturns
        var returnedPlanReturns = om.readValue(
            restPlanReturnsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(planReturns)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            PlanReturns.class
        );

        // Validate the PlanReturns in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertPlanReturnsUpdatableFieldsEquals(returnedPlanReturns, getPersistedPlanReturns(returnedPlanReturns));

        insertedPlanReturns = returnedPlanReturns;
    }

    @Test
    @Transactional
    void createPlanReturnsWithExistingId() throws Exception {
        // Create the PlanReturns with an existing ID
        planReturns.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanReturnsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(planReturns)))
            .andExpect(status().isBadRequest());

        // Validate the PlanReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPlanReturns() throws Exception {
        // Initialize the database
        insertedPlanReturns = planReturnsRepository.saveAndFlush(planReturns);

        // Get all the planReturnsList
        restPlanReturnsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planReturns.getId())))
            .andExpect(jsonPath("$.[*].planreturnsname").value(hasItem(DEFAULT_PLANRETURNSNAME)))
            .andExpect(jsonPath("$.[*].plantype").value(hasItem(DEFAULT_PLANTYPE)))
            .andExpect(jsonPath("$.[*].planlevel").value(hasItem(DEFAULT_PLANLEVEL.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].actualstarttime").value(hasItem(DEFAULT_ACTUALSTARTTIME.toString())))
            .andExpect(jsonPath("$.[*].actualendtime").value(hasItem(DEFAULT_ACTUALENDTIME.toString())))
            .andExpect(jsonPath("$.[*].deliverables").value(hasItem(DEFAULT_DELIVERABLES)))
            .andExpect(jsonPath("$.[*].progress").value(hasItem(DEFAULT_PROGRESS)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].impactanalysis").value(hasItem(DEFAULT_IMPACTANALYSIS)))
            .andExpect(jsonPath("$.[*].returnstime").value(hasItem(DEFAULT_RETURNSTIME.toString())))
            .andExpect(jsonPath("$.[*].rejectionreason").value(hasItem(DEFAULT_REJECTIONREASON)))
            .andExpect(jsonPath("$.[*].returnsstatus").value(hasItem(DEFAULT_RETURNSSTATUS.toString())));
    }

    @Test
    @Transactional
    void getPlanReturns() throws Exception {
        // Initialize the database
        insertedPlanReturns = planReturnsRepository.saveAndFlush(planReturns);

        // Get the planReturns
        restPlanReturnsMockMvc
            .perform(get(ENTITY_API_URL_ID, planReturns.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(planReturns.getId()))
            .andExpect(jsonPath("$.planreturnsname").value(DEFAULT_PLANRETURNSNAME))
            .andExpect(jsonPath("$.plantype").value(DEFAULT_PLANTYPE))
            .andExpect(jsonPath("$.planlevel").value(DEFAULT_PLANLEVEL.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.actualstarttime").value(DEFAULT_ACTUALSTARTTIME.toString()))
            .andExpect(jsonPath("$.actualendtime").value(DEFAULT_ACTUALENDTIME.toString()))
            .andExpect(jsonPath("$.deliverables").value(DEFAULT_DELIVERABLES))
            .andExpect(jsonPath("$.progress").value(DEFAULT_PROGRESS))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.impactanalysis").value(DEFAULT_IMPACTANALYSIS))
            .andExpect(jsonPath("$.returnstime").value(DEFAULT_RETURNSTIME.toString()))
            .andExpect(jsonPath("$.rejectionreason").value(DEFAULT_REJECTIONREASON))
            .andExpect(jsonPath("$.returnsstatus").value(DEFAULT_RETURNSSTATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPlanReturns() throws Exception {
        // Get the planReturns
        restPlanReturnsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPlanReturns() throws Exception {
        // Initialize the database
        insertedPlanReturns = planReturnsRepository.saveAndFlush(planReturns);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the planReturns
        PlanReturns updatedPlanReturns = planReturnsRepository.findById(planReturns.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPlanReturns are not directly saved in db
        em.detach(updatedPlanReturns);
        updatedPlanReturns
            .planreturnsname(UPDATED_PLANRETURNSNAME)
            .plantype(UPDATED_PLANTYPE)
            .planlevel(UPDATED_PLANLEVEL)
            .description(UPDATED_DESCRIPTION)
            .actualstarttime(UPDATED_ACTUALSTARTTIME)
            .actualendtime(UPDATED_ACTUALENDTIME)
            .deliverables(UPDATED_DELIVERABLES)
            .progress(UPDATED_PROGRESS)
            .status(UPDATED_STATUS)
            .impactanalysis(UPDATED_IMPACTANALYSIS)
            .returnstime(UPDATED_RETURNSTIME)
            .rejectionreason(UPDATED_REJECTIONREASON)
            .returnsstatus(UPDATED_RETURNSSTATUS);

        restPlanReturnsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPlanReturns.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedPlanReturns))
            )
            .andExpect(status().isOk());

        // Validate the PlanReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPlanReturnsToMatchAllProperties(updatedPlanReturns);
    }

    @Test
    @Transactional
    void putNonExistingPlanReturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planReturns.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanReturnsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, planReturns.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(planReturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlanReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPlanReturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planReturns.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanReturnsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(planReturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlanReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPlanReturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planReturns.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanReturnsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(planReturns)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PlanReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePlanReturnsWithPatch() throws Exception {
        // Initialize the database
        insertedPlanReturns = planReturnsRepository.saveAndFlush(planReturns);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the planReturns using partial update
        PlanReturns partialUpdatedPlanReturns = new PlanReturns();
        partialUpdatedPlanReturns.setId(planReturns.getId());

        partialUpdatedPlanReturns
            .plantype(UPDATED_PLANTYPE)
            .planlevel(UPDATED_PLANLEVEL)
            .actualendtime(UPDATED_ACTUALENDTIME)
            .deliverables(UPDATED_DELIVERABLES)
            .impactanalysis(UPDATED_IMPACTANALYSIS)
            .rejectionreason(UPDATED_REJECTIONREASON);

        restPlanReturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPlanReturns.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPlanReturns))
            )
            .andExpect(status().isOk());

        // Validate the PlanReturns in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPlanReturnsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPlanReturns, planReturns),
            getPersistedPlanReturns(planReturns)
        );
    }

    @Test
    @Transactional
    void fullUpdatePlanReturnsWithPatch() throws Exception {
        // Initialize the database
        insertedPlanReturns = planReturnsRepository.saveAndFlush(planReturns);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the planReturns using partial update
        PlanReturns partialUpdatedPlanReturns = new PlanReturns();
        partialUpdatedPlanReturns.setId(planReturns.getId());

        partialUpdatedPlanReturns
            .planreturnsname(UPDATED_PLANRETURNSNAME)
            .plantype(UPDATED_PLANTYPE)
            .planlevel(UPDATED_PLANLEVEL)
            .description(UPDATED_DESCRIPTION)
            .actualstarttime(UPDATED_ACTUALSTARTTIME)
            .actualendtime(UPDATED_ACTUALENDTIME)
            .deliverables(UPDATED_DELIVERABLES)
            .progress(UPDATED_PROGRESS)
            .status(UPDATED_STATUS)
            .impactanalysis(UPDATED_IMPACTANALYSIS)
            .returnstime(UPDATED_RETURNSTIME)
            .rejectionreason(UPDATED_REJECTIONREASON)
            .returnsstatus(UPDATED_RETURNSSTATUS);

        restPlanReturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPlanReturns.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPlanReturns))
            )
            .andExpect(status().isOk());

        // Validate the PlanReturns in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPlanReturnsUpdatableFieldsEquals(partialUpdatedPlanReturns, getPersistedPlanReturns(partialUpdatedPlanReturns));
    }

    @Test
    @Transactional
    void patchNonExistingPlanReturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planReturns.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanReturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, planReturns.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(planReturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlanReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPlanReturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planReturns.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanReturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(planReturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the PlanReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPlanReturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planReturns.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanReturnsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(planReturns)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PlanReturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePlanReturns() throws Exception {
        // Initialize the database
        insertedPlanReturns = planReturnsRepository.saveAndFlush(planReturns);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the planReturns
        restPlanReturnsMockMvc
            .perform(delete(ENTITY_API_URL_ID, planReturns.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return planReturnsRepository.count();
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

    protected PlanReturns getPersistedPlanReturns(PlanReturns planReturns) {
        return planReturnsRepository.findById(planReturns.getId()).orElseThrow();
    }

    protected void assertPersistedPlanReturnsToMatchAllProperties(PlanReturns expectedPlanReturns) {
        assertPlanReturnsAllPropertiesEquals(expectedPlanReturns, getPersistedPlanReturns(expectedPlanReturns));
    }

    protected void assertPersistedPlanReturnsToMatchUpdatableProperties(PlanReturns expectedPlanReturns) {
        assertPlanReturnsAllUpdatablePropertiesEquals(expectedPlanReturns, getPersistedPlanReturns(expectedPlanReturns));
    }
}

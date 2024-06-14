package com.cvicse.web.rest;

import static com.cvicse.domain.PlanreturnsAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Planreturns;
import com.cvicse.domain.enumeration.ReturnsStatus;
import com.cvicse.repository.PlanreturnsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link PlanreturnsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PlanreturnsResourceIT {

    private static final Long DEFAULT_PLANRETURNSID = 1L;
    private static final Long UPDATED_PLANRETURNSID = 2L;

    private static final String DEFAULT_PLANRETURNSNAME = "AAAAAAAAAA";
    private static final String UPDATED_PLANRETURNSNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_PLANTYPE = 1;
    private static final Integer UPDATED_PLANTYPE = 2;

    private static final LocalDate DEFAULT_RETURNSTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RETURNSTIME = LocalDate.now(ZoneId.systemDefault());

    private static final ReturnsStatus DEFAULT_RETURNSSTATUS = ReturnsStatus.Not_Comlated;
    private static final ReturnsStatus UPDATED_RETURNSSTATUS = ReturnsStatus.Completed;

    private static final String ENTITY_API_URL = "/api/planreturns";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PlanreturnsRepository planreturnsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlanreturnsMockMvc;

    private Planreturns planreturns;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Planreturns createEntity(EntityManager em) {
        Planreturns planreturns = new Planreturns()
            .planreturnsid(DEFAULT_PLANRETURNSID)
            .planreturnsname(DEFAULT_PLANRETURNSNAME)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME)
            .plantype(DEFAULT_PLANTYPE)
            .returnstime(DEFAULT_RETURNSTIME)
            .returnsstatus(DEFAULT_RETURNSSTATUS);
        return planreturns;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Planreturns createUpdatedEntity(EntityManager em) {
        Planreturns planreturns = new Planreturns()
            .planreturnsid(UPDATED_PLANRETURNSID)
            .planreturnsname(UPDATED_PLANRETURNSNAME)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .plantype(UPDATED_PLANTYPE)
            .returnstime(UPDATED_RETURNSTIME)
            .returnsstatus(UPDATED_RETURNSSTATUS);
        return planreturns;
    }

    @BeforeEach
    public void initTest() {
        planreturns = createEntity(em);
    }

    @Test
    @Transactional
    void createPlanreturns() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Planreturns
        var returnedPlanreturns = om.readValue(
            restPlanreturnsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(planreturns)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Planreturns.class
        );

        // Validate the Planreturns in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertPlanreturnsUpdatableFieldsEquals(returnedPlanreturns, getPersistedPlanreturns(returnedPlanreturns));
    }

    @Test
    @Transactional
    void createPlanreturnsWithExistingId() throws Exception {
        // Create the Planreturns with an existing ID
        planreturns.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanreturnsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(planreturns)))
            .andExpect(status().isBadRequest());

        // Validate the Planreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPlanreturns() throws Exception {
        // Initialize the database
        planreturnsRepository.saveAndFlush(planreturns);

        // Get all the planreturnsList
        restPlanreturnsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planreturns.getId().intValue())))
            .andExpect(jsonPath("$.[*].planreturnsid").value(hasItem(DEFAULT_PLANRETURNSID.intValue())))
            .andExpect(jsonPath("$.[*].planreturnsname").value(hasItem(DEFAULT_PLANRETURNSNAME)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())))
            .andExpect(jsonPath("$.[*].plantype").value(hasItem(DEFAULT_PLANTYPE)))
            .andExpect(jsonPath("$.[*].returnstime").value(hasItem(DEFAULT_RETURNSTIME.toString())))
            .andExpect(jsonPath("$.[*].returnsstatus").value(hasItem(DEFAULT_RETURNSSTATUS.toString())));
    }

    @Test
    @Transactional
    void getPlanreturns() throws Exception {
        // Initialize the database
        planreturnsRepository.saveAndFlush(planreturns);

        // Get the planreturns
        restPlanreturnsMockMvc
            .perform(get(ENTITY_API_URL_ID, planreturns.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(planreturns.getId().intValue()))
            .andExpect(jsonPath("$.planreturnsid").value(DEFAULT_PLANRETURNSID.intValue()))
            .andExpect(jsonPath("$.planreturnsname").value(DEFAULT_PLANRETURNSNAME))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()))
            .andExpect(jsonPath("$.plantype").value(DEFAULT_PLANTYPE))
            .andExpect(jsonPath("$.returnstime").value(DEFAULT_RETURNSTIME.toString()))
            .andExpect(jsonPath("$.returnsstatus").value(DEFAULT_RETURNSSTATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPlanreturns() throws Exception {
        // Get the planreturns
        restPlanreturnsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPlanreturns() throws Exception {
        // Initialize the database
        planreturnsRepository.saveAndFlush(planreturns);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the planreturns
        Planreturns updatedPlanreturns = planreturnsRepository.findById(planreturns.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPlanreturns are not directly saved in db
        em.detach(updatedPlanreturns);
        updatedPlanreturns
            .planreturnsid(UPDATED_PLANRETURNSID)
            .planreturnsname(UPDATED_PLANRETURNSNAME)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .plantype(UPDATED_PLANTYPE)
            .returnstime(UPDATED_RETURNSTIME)
            .returnsstatus(UPDATED_RETURNSSTATUS);

        restPlanreturnsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPlanreturns.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedPlanreturns))
            )
            .andExpect(status().isOk());

        // Validate the Planreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPlanreturnsToMatchAllProperties(updatedPlanreturns);
    }

    @Test
    @Transactional
    void putNonExistingPlanreturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planreturns.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanreturnsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, planreturns.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(planreturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the Planreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPlanreturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planreturns.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanreturnsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(planreturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the Planreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPlanreturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planreturns.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanreturnsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(planreturns)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Planreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePlanreturnsWithPatch() throws Exception {
        // Initialize the database
        planreturnsRepository.saveAndFlush(planreturns);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the planreturns using partial update
        Planreturns partialUpdatedPlanreturns = new Planreturns();
        partialUpdatedPlanreturns.setId(planreturns.getId());

        partialUpdatedPlanreturns.starttime(UPDATED_STARTTIME).returnstime(UPDATED_RETURNSTIME).returnsstatus(UPDATED_RETURNSSTATUS);

        restPlanreturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPlanreturns.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPlanreturns))
            )
            .andExpect(status().isOk());

        // Validate the Planreturns in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPlanreturnsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPlanreturns, planreturns),
            getPersistedPlanreturns(planreturns)
        );
    }

    @Test
    @Transactional
    void fullUpdatePlanreturnsWithPatch() throws Exception {
        // Initialize the database
        planreturnsRepository.saveAndFlush(planreturns);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the planreturns using partial update
        Planreturns partialUpdatedPlanreturns = new Planreturns();
        partialUpdatedPlanreturns.setId(planreturns.getId());

        partialUpdatedPlanreturns
            .planreturnsid(UPDATED_PLANRETURNSID)
            .planreturnsname(UPDATED_PLANRETURNSNAME)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .plantype(UPDATED_PLANTYPE)
            .returnstime(UPDATED_RETURNSTIME)
            .returnsstatus(UPDATED_RETURNSSTATUS);

        restPlanreturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPlanreturns.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPlanreturns))
            )
            .andExpect(status().isOk());

        // Validate the Planreturns in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPlanreturnsUpdatableFieldsEquals(partialUpdatedPlanreturns, getPersistedPlanreturns(partialUpdatedPlanreturns));
    }

    @Test
    @Transactional
    void patchNonExistingPlanreturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planreturns.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanreturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, planreturns.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(planreturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the Planreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPlanreturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planreturns.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanreturnsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(planreturns))
            )
            .andExpect(status().isBadRequest());

        // Validate the Planreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPlanreturns() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planreturns.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanreturnsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(planreturns)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Planreturns in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePlanreturns() throws Exception {
        // Initialize the database
        planreturnsRepository.saveAndFlush(planreturns);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the planreturns
        restPlanreturnsMockMvc
            .perform(delete(ENTITY_API_URL_ID, planreturns.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return planreturnsRepository.count();
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

    protected Planreturns getPersistedPlanreturns(Planreturns planreturns) {
        return planreturnsRepository.findById(planreturns.getId()).orElseThrow();
    }

    protected void assertPersistedPlanreturnsToMatchAllProperties(Planreturns expectedPlanreturns) {
        assertPlanreturnsAllPropertiesEquals(expectedPlanreturns, getPersistedPlanreturns(expectedPlanreturns));
    }

    protected void assertPersistedPlanreturnsToMatchUpdatableProperties(Planreturns expectedPlanreturns) {
        assertPlanreturnsAllUpdatablePropertiesEquals(expectedPlanreturns, getPersistedPlanreturns(expectedPlanreturns));
    }
}

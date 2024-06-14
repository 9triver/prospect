package com.cvicse.web.rest;

import static com.cvicse.domain.PlanexecuteAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Planexecute;
import com.cvicse.repository.PlanexecuteRepository;
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
 * Integration tests for the {@link PlanexecuteResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PlanexecuteResourceIT {

    private static final String DEFAULT_PLANNAME = "AAAAAAAAAA";
    private static final String UPDATED_PLANNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PLANSTARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PLANSTARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_PLANENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PLANENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/planexecutes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PlanexecuteRepository planexecuteRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlanexecuteMockMvc;

    private Planexecute planexecute;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Planexecute createEntity(EntityManager em) {
        Planexecute planexecute = new Planexecute()
            .planname(DEFAULT_PLANNAME)
            .planstarttime(DEFAULT_PLANSTARTTIME)
            .planendtime(DEFAULT_PLANENDTIME);
        return planexecute;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Planexecute createUpdatedEntity(EntityManager em) {
        Planexecute planexecute = new Planexecute()
            .planname(UPDATED_PLANNAME)
            .planstarttime(UPDATED_PLANSTARTTIME)
            .planendtime(UPDATED_PLANENDTIME);
        return planexecute;
    }

    @BeforeEach
    public void initTest() {
        planexecute = createEntity(em);
    }

    @Test
    @Transactional
    void createPlanexecute() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Planexecute
        var returnedPlanexecute = om.readValue(
            restPlanexecuteMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(planexecute)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Planexecute.class
        );

        // Validate the Planexecute in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertPlanexecuteUpdatableFieldsEquals(returnedPlanexecute, getPersistedPlanexecute(returnedPlanexecute));
    }

    @Test
    @Transactional
    void createPlanexecuteWithExistingId() throws Exception {
        // Create the Planexecute with an existing ID
        planexecute.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanexecuteMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(planexecute)))
            .andExpect(status().isBadRequest());

        // Validate the Planexecute in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPlanexecutes() throws Exception {
        // Initialize the database
        planexecuteRepository.saveAndFlush(planexecute);

        // Get all the planexecuteList
        restPlanexecuteMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planexecute.getId().intValue())))
            .andExpect(jsonPath("$.[*].planname").value(hasItem(DEFAULT_PLANNAME)))
            .andExpect(jsonPath("$.[*].planstarttime").value(hasItem(DEFAULT_PLANSTARTTIME.toString())))
            .andExpect(jsonPath("$.[*].planendtime").value(hasItem(DEFAULT_PLANENDTIME.toString())));
    }

    @Test
    @Transactional
    void getPlanexecute() throws Exception {
        // Initialize the database
        planexecuteRepository.saveAndFlush(planexecute);

        // Get the planexecute
        restPlanexecuteMockMvc
            .perform(get(ENTITY_API_URL_ID, planexecute.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(planexecute.getId().intValue()))
            .andExpect(jsonPath("$.planname").value(DEFAULT_PLANNAME))
            .andExpect(jsonPath("$.planstarttime").value(DEFAULT_PLANSTARTTIME.toString()))
            .andExpect(jsonPath("$.planendtime").value(DEFAULT_PLANENDTIME.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPlanexecute() throws Exception {
        // Get the planexecute
        restPlanexecuteMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPlanexecute() throws Exception {
        // Initialize the database
        planexecuteRepository.saveAndFlush(planexecute);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the planexecute
        Planexecute updatedPlanexecute = planexecuteRepository.findById(planexecute.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPlanexecute are not directly saved in db
        em.detach(updatedPlanexecute);
        updatedPlanexecute.planname(UPDATED_PLANNAME).planstarttime(UPDATED_PLANSTARTTIME).planendtime(UPDATED_PLANENDTIME);

        restPlanexecuteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPlanexecute.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedPlanexecute))
            )
            .andExpect(status().isOk());

        // Validate the Planexecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPlanexecuteToMatchAllProperties(updatedPlanexecute);
    }

    @Test
    @Transactional
    void putNonExistingPlanexecute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planexecute.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanexecuteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, planexecute.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(planexecute))
            )
            .andExpect(status().isBadRequest());

        // Validate the Planexecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPlanexecute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planexecute.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanexecuteMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(planexecute))
            )
            .andExpect(status().isBadRequest());

        // Validate the Planexecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPlanexecute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planexecute.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanexecuteMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(planexecute)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Planexecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePlanexecuteWithPatch() throws Exception {
        // Initialize the database
        planexecuteRepository.saveAndFlush(planexecute);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the planexecute using partial update
        Planexecute partialUpdatedPlanexecute = new Planexecute();
        partialUpdatedPlanexecute.setId(planexecute.getId());

        partialUpdatedPlanexecute.planname(UPDATED_PLANNAME).planendtime(UPDATED_PLANENDTIME);

        restPlanexecuteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPlanexecute.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPlanexecute))
            )
            .andExpect(status().isOk());

        // Validate the Planexecute in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPlanexecuteUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPlanexecute, planexecute),
            getPersistedPlanexecute(planexecute)
        );
    }

    @Test
    @Transactional
    void fullUpdatePlanexecuteWithPatch() throws Exception {
        // Initialize the database
        planexecuteRepository.saveAndFlush(planexecute);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the planexecute using partial update
        Planexecute partialUpdatedPlanexecute = new Planexecute();
        partialUpdatedPlanexecute.setId(planexecute.getId());

        partialUpdatedPlanexecute.planname(UPDATED_PLANNAME).planstarttime(UPDATED_PLANSTARTTIME).planendtime(UPDATED_PLANENDTIME);

        restPlanexecuteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPlanexecute.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPlanexecute))
            )
            .andExpect(status().isOk());

        // Validate the Planexecute in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPlanexecuteUpdatableFieldsEquals(partialUpdatedPlanexecute, getPersistedPlanexecute(partialUpdatedPlanexecute));
    }

    @Test
    @Transactional
    void patchNonExistingPlanexecute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planexecute.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanexecuteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, planexecute.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(planexecute))
            )
            .andExpect(status().isBadRequest());

        // Validate the Planexecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPlanexecute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planexecute.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanexecuteMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(planexecute))
            )
            .andExpect(status().isBadRequest());

        // Validate the Planexecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPlanexecute() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planexecute.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanexecuteMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(planexecute)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Planexecute in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePlanexecute() throws Exception {
        // Initialize the database
        planexecuteRepository.saveAndFlush(planexecute);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the planexecute
        restPlanexecuteMockMvc
            .perform(delete(ENTITY_API_URL_ID, planexecute.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return planexecuteRepository.count();
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

    protected Planexecute getPersistedPlanexecute(Planexecute planexecute) {
        return planexecuteRepository.findById(planexecute.getId()).orElseThrow();
    }

    protected void assertPersistedPlanexecuteToMatchAllProperties(Planexecute expectedPlanexecute) {
        assertPlanexecuteAllPropertiesEquals(expectedPlanexecute, getPersistedPlanexecute(expectedPlanexecute));
    }

    protected void assertPersistedPlanexecuteToMatchUpdatableProperties(Planexecute expectedPlanexecute) {
        assertPlanexecuteAllUpdatablePropertiesEquals(expectedPlanexecute, getPersistedPlanexecute(expectedPlanexecute));
    }
}

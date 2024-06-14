package com.cvicse.web.rest;

import static com.cvicse.domain.PlanstrategyAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Planstrategy;
import com.cvicse.repository.PlanstrategyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link PlanstrategyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PlanstrategyResourceIT {

    private static final String DEFAULT_STRATEGYID = "AAAAAAAAAA";
    private static final String UPDATED_STRATEGYID = "BBBBBBBBBB";

    private static final String DEFAULT_STRATEGYNAME = "AAAAAAAAAA";
    private static final String UPDATED_STRATEGYNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/planstrategies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PlanstrategyRepository planstrategyRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlanstrategyMockMvc;

    private Planstrategy planstrategy;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Planstrategy createEntity(EntityManager em) {
        Planstrategy planstrategy = new Planstrategy()
            .strategyid(DEFAULT_STRATEGYID)
            .strategyname(DEFAULT_STRATEGYNAME)
            .number(DEFAULT_NUMBER)
            .type(DEFAULT_TYPE);
        return planstrategy;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Planstrategy createUpdatedEntity(EntityManager em) {
        Planstrategy planstrategy = new Planstrategy()
            .strategyid(UPDATED_STRATEGYID)
            .strategyname(UPDATED_STRATEGYNAME)
            .number(UPDATED_NUMBER)
            .type(UPDATED_TYPE);
        return planstrategy;
    }

    @BeforeEach
    public void initTest() {
        planstrategy = createEntity(em);
    }

    @Test
    @Transactional
    void createPlanstrategy() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Planstrategy
        var returnedPlanstrategy = om.readValue(
            restPlanstrategyMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(planstrategy)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Planstrategy.class
        );

        // Validate the Planstrategy in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertPlanstrategyUpdatableFieldsEquals(returnedPlanstrategy, getPersistedPlanstrategy(returnedPlanstrategy));
    }

    @Test
    @Transactional
    void createPlanstrategyWithExistingId() throws Exception {
        // Create the Planstrategy with an existing ID
        planstrategy.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanstrategyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(planstrategy)))
            .andExpect(status().isBadRequest());

        // Validate the Planstrategy in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPlanstrategies() throws Exception {
        // Initialize the database
        planstrategyRepository.saveAndFlush(planstrategy);

        // Get all the planstrategyList
        restPlanstrategyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planstrategy.getId().intValue())))
            .andExpect(jsonPath("$.[*].strategyid").value(hasItem(DEFAULT_STRATEGYID)))
            .andExpect(jsonPath("$.[*].strategyname").value(hasItem(DEFAULT_STRATEGYNAME)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)));
    }

    @Test
    @Transactional
    void getPlanstrategy() throws Exception {
        // Initialize the database
        planstrategyRepository.saveAndFlush(planstrategy);

        // Get the planstrategy
        restPlanstrategyMockMvc
            .perform(get(ENTITY_API_URL_ID, planstrategy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(planstrategy.getId().intValue()))
            .andExpect(jsonPath("$.strategyid").value(DEFAULT_STRATEGYID))
            .andExpect(jsonPath("$.strategyname").value(DEFAULT_STRATEGYNAME))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE));
    }

    @Test
    @Transactional
    void getNonExistingPlanstrategy() throws Exception {
        // Get the planstrategy
        restPlanstrategyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPlanstrategy() throws Exception {
        // Initialize the database
        planstrategyRepository.saveAndFlush(planstrategy);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the planstrategy
        Planstrategy updatedPlanstrategy = planstrategyRepository.findById(planstrategy.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPlanstrategy are not directly saved in db
        em.detach(updatedPlanstrategy);
        updatedPlanstrategy.strategyid(UPDATED_STRATEGYID).strategyname(UPDATED_STRATEGYNAME).number(UPDATED_NUMBER).type(UPDATED_TYPE);

        restPlanstrategyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPlanstrategy.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedPlanstrategy))
            )
            .andExpect(status().isOk());

        // Validate the Planstrategy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPlanstrategyToMatchAllProperties(updatedPlanstrategy);
    }

    @Test
    @Transactional
    void putNonExistingPlanstrategy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planstrategy.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanstrategyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, planstrategy.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(planstrategy))
            )
            .andExpect(status().isBadRequest());

        // Validate the Planstrategy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPlanstrategy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planstrategy.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanstrategyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(planstrategy))
            )
            .andExpect(status().isBadRequest());

        // Validate the Planstrategy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPlanstrategy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planstrategy.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanstrategyMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(planstrategy)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Planstrategy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePlanstrategyWithPatch() throws Exception {
        // Initialize the database
        planstrategyRepository.saveAndFlush(planstrategy);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the planstrategy using partial update
        Planstrategy partialUpdatedPlanstrategy = new Planstrategy();
        partialUpdatedPlanstrategy.setId(planstrategy.getId());

        partialUpdatedPlanstrategy.strategyid(UPDATED_STRATEGYID);

        restPlanstrategyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPlanstrategy.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPlanstrategy))
            )
            .andExpect(status().isOk());

        // Validate the Planstrategy in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPlanstrategyUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPlanstrategy, planstrategy),
            getPersistedPlanstrategy(planstrategy)
        );
    }

    @Test
    @Transactional
    void fullUpdatePlanstrategyWithPatch() throws Exception {
        // Initialize the database
        planstrategyRepository.saveAndFlush(planstrategy);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the planstrategy using partial update
        Planstrategy partialUpdatedPlanstrategy = new Planstrategy();
        partialUpdatedPlanstrategy.setId(planstrategy.getId());

        partialUpdatedPlanstrategy
            .strategyid(UPDATED_STRATEGYID)
            .strategyname(UPDATED_STRATEGYNAME)
            .number(UPDATED_NUMBER)
            .type(UPDATED_TYPE);

        restPlanstrategyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPlanstrategy.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPlanstrategy))
            )
            .andExpect(status().isOk());

        // Validate the Planstrategy in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPlanstrategyUpdatableFieldsEquals(partialUpdatedPlanstrategy, getPersistedPlanstrategy(partialUpdatedPlanstrategy));
    }

    @Test
    @Transactional
    void patchNonExistingPlanstrategy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planstrategy.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanstrategyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, planstrategy.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(planstrategy))
            )
            .andExpect(status().isBadRequest());

        // Validate the Planstrategy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPlanstrategy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planstrategy.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanstrategyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(planstrategy))
            )
            .andExpect(status().isBadRequest());

        // Validate the Planstrategy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPlanstrategy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planstrategy.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanstrategyMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(planstrategy)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Planstrategy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePlanstrategy() throws Exception {
        // Initialize the database
        planstrategyRepository.saveAndFlush(planstrategy);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the planstrategy
        restPlanstrategyMockMvc
            .perform(delete(ENTITY_API_URL_ID, planstrategy.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return planstrategyRepository.count();
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

    protected Planstrategy getPersistedPlanstrategy(Planstrategy planstrategy) {
        return planstrategyRepository.findById(planstrategy.getId()).orElseThrow();
    }

    protected void assertPersistedPlanstrategyToMatchAllProperties(Planstrategy expectedPlanstrategy) {
        assertPlanstrategyAllPropertiesEquals(expectedPlanstrategy, getPersistedPlanstrategy(expectedPlanstrategy));
    }

    protected void assertPersistedPlanstrategyToMatchUpdatableProperties(Planstrategy expectedPlanstrategy) {
        assertPlanstrategyAllUpdatablePropertiesEquals(expectedPlanstrategy, getPersistedPlanstrategy(expectedPlanstrategy));
    }
}

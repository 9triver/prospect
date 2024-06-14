package com.cvicse.web.rest;

import static com.cvicse.domain.PlanmonitorAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Planmonitor;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.PlanmonitorRepository;
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
 * Integration tests for the {@link PlanmonitorResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PlanmonitorResourceIT {

    private static final LocalDate DEFAULT_MONTH = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MONTH = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_YEAR = 1L;
    private static final Long UPDATED_YEAR = 2L;

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final String ENTITY_API_URL = "/api/planmonitors";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PlanmonitorRepository planmonitorRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPlanmonitorMockMvc;

    private Planmonitor planmonitor;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Planmonitor createEntity(EntityManager em) {
        Planmonitor planmonitor = new Planmonitor()
            .month(DEFAULT_MONTH)
            .type(DEFAULT_TYPE)
            .year(DEFAULT_YEAR)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .status(DEFAULT_STATUS);
        return planmonitor;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Planmonitor createUpdatedEntity(EntityManager em) {
        Planmonitor planmonitor = new Planmonitor()
            .month(UPDATED_MONTH)
            .type(UPDATED_TYPE)
            .year(UPDATED_YEAR)
            .secretlevel(UPDATED_SECRETLEVEL)
            .status(UPDATED_STATUS);
        return planmonitor;
    }

    @BeforeEach
    public void initTest() {
        planmonitor = createEntity(em);
    }

    @Test
    @Transactional
    void createPlanmonitor() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Planmonitor
        var returnedPlanmonitor = om.readValue(
            restPlanmonitorMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(planmonitor)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Planmonitor.class
        );

        // Validate the Planmonitor in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertPlanmonitorUpdatableFieldsEquals(returnedPlanmonitor, getPersistedPlanmonitor(returnedPlanmonitor));
    }

    @Test
    @Transactional
    void createPlanmonitorWithExistingId() throws Exception {
        // Create the Planmonitor with an existing ID
        planmonitor.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPlanmonitorMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(planmonitor)))
            .andExpect(status().isBadRequest());

        // Validate the Planmonitor in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPlanmonitors() throws Exception {
        // Initialize the database
        planmonitorRepository.saveAndFlush(planmonitor);

        // Get all the planmonitorList
        restPlanmonitorMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(planmonitor.getId().intValue())))
            .andExpect(jsonPath("$.[*].month").value(hasItem(DEFAULT_MONTH.toString())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR.intValue())))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    void getPlanmonitor() throws Exception {
        // Initialize the database
        planmonitorRepository.saveAndFlush(planmonitor);

        // Get the planmonitor
        restPlanmonitorMockMvc
            .perform(get(ENTITY_API_URL_ID, planmonitor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(planmonitor.getId().intValue()))
            .andExpect(jsonPath("$.month").value(DEFAULT_MONTH.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR.intValue()))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingPlanmonitor() throws Exception {
        // Get the planmonitor
        restPlanmonitorMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPlanmonitor() throws Exception {
        // Initialize the database
        planmonitorRepository.saveAndFlush(planmonitor);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the planmonitor
        Planmonitor updatedPlanmonitor = planmonitorRepository.findById(planmonitor.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPlanmonitor are not directly saved in db
        em.detach(updatedPlanmonitor);
        updatedPlanmonitor
            .month(UPDATED_MONTH)
            .type(UPDATED_TYPE)
            .year(UPDATED_YEAR)
            .secretlevel(UPDATED_SECRETLEVEL)
            .status(UPDATED_STATUS);

        restPlanmonitorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPlanmonitor.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedPlanmonitor))
            )
            .andExpect(status().isOk());

        // Validate the Planmonitor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPlanmonitorToMatchAllProperties(updatedPlanmonitor);
    }

    @Test
    @Transactional
    void putNonExistingPlanmonitor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planmonitor.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanmonitorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, planmonitor.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(planmonitor))
            )
            .andExpect(status().isBadRequest());

        // Validate the Planmonitor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPlanmonitor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planmonitor.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanmonitorMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(planmonitor))
            )
            .andExpect(status().isBadRequest());

        // Validate the Planmonitor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPlanmonitor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planmonitor.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanmonitorMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(planmonitor)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Planmonitor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePlanmonitorWithPatch() throws Exception {
        // Initialize the database
        planmonitorRepository.saveAndFlush(planmonitor);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the planmonitor using partial update
        Planmonitor partialUpdatedPlanmonitor = new Planmonitor();
        partialUpdatedPlanmonitor.setId(planmonitor.getId());

        partialUpdatedPlanmonitor.type(UPDATED_TYPE).secretlevel(UPDATED_SECRETLEVEL);

        restPlanmonitorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPlanmonitor.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPlanmonitor))
            )
            .andExpect(status().isOk());

        // Validate the Planmonitor in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPlanmonitorUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPlanmonitor, planmonitor),
            getPersistedPlanmonitor(planmonitor)
        );
    }

    @Test
    @Transactional
    void fullUpdatePlanmonitorWithPatch() throws Exception {
        // Initialize the database
        planmonitorRepository.saveAndFlush(planmonitor);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the planmonitor using partial update
        Planmonitor partialUpdatedPlanmonitor = new Planmonitor();
        partialUpdatedPlanmonitor.setId(planmonitor.getId());

        partialUpdatedPlanmonitor
            .month(UPDATED_MONTH)
            .type(UPDATED_TYPE)
            .year(UPDATED_YEAR)
            .secretlevel(UPDATED_SECRETLEVEL)
            .status(UPDATED_STATUS);

        restPlanmonitorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPlanmonitor.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPlanmonitor))
            )
            .andExpect(status().isOk());

        // Validate the Planmonitor in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPlanmonitorUpdatableFieldsEquals(partialUpdatedPlanmonitor, getPersistedPlanmonitor(partialUpdatedPlanmonitor));
    }

    @Test
    @Transactional
    void patchNonExistingPlanmonitor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planmonitor.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPlanmonitorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, planmonitor.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(planmonitor))
            )
            .andExpect(status().isBadRequest());

        // Validate the Planmonitor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPlanmonitor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planmonitor.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanmonitorMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(planmonitor))
            )
            .andExpect(status().isBadRequest());

        // Validate the Planmonitor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPlanmonitor() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        planmonitor.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPlanmonitorMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(planmonitor)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Planmonitor in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePlanmonitor() throws Exception {
        // Initialize the database
        planmonitorRepository.saveAndFlush(planmonitor);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the planmonitor
        restPlanmonitorMockMvc
            .perform(delete(ENTITY_API_URL_ID, planmonitor.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return planmonitorRepository.count();
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

    protected Planmonitor getPersistedPlanmonitor(Planmonitor planmonitor) {
        return planmonitorRepository.findById(planmonitor.getId()).orElseThrow();
    }

    protected void assertPersistedPlanmonitorToMatchAllProperties(Planmonitor expectedPlanmonitor) {
        assertPlanmonitorAllPropertiesEquals(expectedPlanmonitor, getPersistedPlanmonitor(expectedPlanmonitor));
    }

    protected void assertPersistedPlanmonitorToMatchUpdatableProperties(Planmonitor expectedPlanmonitor) {
        assertPlanmonitorAllUpdatablePropertiesEquals(expectedPlanmonitor, getPersistedPlanmonitor(expectedPlanmonitor));
    }
}

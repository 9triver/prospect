package com.cvicse.web.rest;

import static com.cvicse.domain.ManagementCapacityEvaluationAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.ManagementCapacityEvaluation;
import com.cvicse.repository.ManagementCapacityEvaluationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
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
 * Integration tests for the {@link ManagementCapacityEvaluationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ManagementCapacityEvaluationResourceIT {

    private static final Long DEFAULT_YEAR = 1L;
    private static final Long UPDATED_YEAR = 2L;

    private static final String DEFAULT_DEPROTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPROTMENT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATETIME = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_STATUS = 1;
    private static final Integer UPDATED_STATUS = 2;

    private static final BigDecimal DEFAULT_TOTALMARK = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTALMARK = new BigDecimal(2);

    private static final BigDecimal DEFAULT_MARK = new BigDecimal(1);
    private static final BigDecimal UPDATED_MARK = new BigDecimal(2);

    private static final String DEFAULT_RATINGPERSONNAME = "AAAAAAAAAA";
    private static final String UPDATED_RATINGPERSONNAME = "BBBBBBBBBB";

    private static final String DEFAULT_RATINGDEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_RATINGDEPARTMENT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RATINGTIMG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RATINGTIMG = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/management-capacity-evaluations";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ManagementCapacityEvaluationRepository managementCapacityEvaluationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restManagementCapacityEvaluationMockMvc;

    private ManagementCapacityEvaluation managementCapacityEvaluation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ManagementCapacityEvaluation createEntity(EntityManager em) {
        ManagementCapacityEvaluation managementCapacityEvaluation = new ManagementCapacityEvaluation()
            .year(DEFAULT_YEAR)
            .deprotment(DEFAULT_DEPROTMENT)
            .createtime(DEFAULT_CREATETIME)
            .status(DEFAULT_STATUS)
            .totalmark(DEFAULT_TOTALMARK)
            .mark(DEFAULT_MARK)
            .ratingpersonname(DEFAULT_RATINGPERSONNAME)
            .ratingdepartment(DEFAULT_RATINGDEPARTMENT)
            .ratingtimg(DEFAULT_RATINGTIMG);
        return managementCapacityEvaluation;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ManagementCapacityEvaluation createUpdatedEntity(EntityManager em) {
        ManagementCapacityEvaluation managementCapacityEvaluation = new ManagementCapacityEvaluation()
            .year(UPDATED_YEAR)
            .deprotment(UPDATED_DEPROTMENT)
            .createtime(UPDATED_CREATETIME)
            .status(UPDATED_STATUS)
            .totalmark(UPDATED_TOTALMARK)
            .mark(UPDATED_MARK)
            .ratingpersonname(UPDATED_RATINGPERSONNAME)
            .ratingdepartment(UPDATED_RATINGDEPARTMENT)
            .ratingtimg(UPDATED_RATINGTIMG);
        return managementCapacityEvaluation;
    }

    @BeforeEach
    public void initTest() {
        managementCapacityEvaluation = createEntity(em);
    }

    @Test
    @Transactional
    void createManagementCapacityEvaluation() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ManagementCapacityEvaluation
        var returnedManagementCapacityEvaluation = om.readValue(
            restManagementCapacityEvaluationMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(managementCapacityEvaluation))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ManagementCapacityEvaluation.class
        );

        // Validate the ManagementCapacityEvaluation in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertManagementCapacityEvaluationUpdatableFieldsEquals(
            returnedManagementCapacityEvaluation,
            getPersistedManagementCapacityEvaluation(returnedManagementCapacityEvaluation)
        );
    }

    @Test
    @Transactional
    void createManagementCapacityEvaluationWithExistingId() throws Exception {
        // Create the ManagementCapacityEvaluation with an existing ID
        managementCapacityEvaluation.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restManagementCapacityEvaluationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(managementCapacityEvaluation))
            )
            .andExpect(status().isBadRequest());

        // Validate the ManagementCapacityEvaluation in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllManagementCapacityEvaluations() throws Exception {
        // Initialize the database
        managementCapacityEvaluationRepository.saveAndFlush(managementCapacityEvaluation);

        // Get all the managementCapacityEvaluationList
        restManagementCapacityEvaluationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(managementCapacityEvaluation.getId().intValue())))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR.intValue())))
            .andExpect(jsonPath("$.[*].deprotment").value(hasItem(DEFAULT_DEPROTMENT)))
            .andExpect(jsonPath("$.[*].createtime").value(hasItem(DEFAULT_CREATETIME.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].totalmark").value(hasItem(sameNumber(DEFAULT_TOTALMARK))))
            .andExpect(jsonPath("$.[*].mark").value(hasItem(sameNumber(DEFAULT_MARK))))
            .andExpect(jsonPath("$.[*].ratingpersonname").value(hasItem(DEFAULT_RATINGPERSONNAME)))
            .andExpect(jsonPath("$.[*].ratingdepartment").value(hasItem(DEFAULT_RATINGDEPARTMENT)))
            .andExpect(jsonPath("$.[*].ratingtimg").value(hasItem(DEFAULT_RATINGTIMG.toString())));
    }

    @Test
    @Transactional
    void getManagementCapacityEvaluation() throws Exception {
        // Initialize the database
        managementCapacityEvaluationRepository.saveAndFlush(managementCapacityEvaluation);

        // Get the managementCapacityEvaluation
        restManagementCapacityEvaluationMockMvc
            .perform(get(ENTITY_API_URL_ID, managementCapacityEvaluation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(managementCapacityEvaluation.getId().intValue()))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR.intValue()))
            .andExpect(jsonPath("$.deprotment").value(DEFAULT_DEPROTMENT))
            .andExpect(jsonPath("$.createtime").value(DEFAULT_CREATETIME.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.totalmark").value(sameNumber(DEFAULT_TOTALMARK)))
            .andExpect(jsonPath("$.mark").value(sameNumber(DEFAULT_MARK)))
            .andExpect(jsonPath("$.ratingpersonname").value(DEFAULT_RATINGPERSONNAME))
            .andExpect(jsonPath("$.ratingdepartment").value(DEFAULT_RATINGDEPARTMENT))
            .andExpect(jsonPath("$.ratingtimg").value(DEFAULT_RATINGTIMG.toString()));
    }

    @Test
    @Transactional
    void getNonExistingManagementCapacityEvaluation() throws Exception {
        // Get the managementCapacityEvaluation
        restManagementCapacityEvaluationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingManagementCapacityEvaluation() throws Exception {
        // Initialize the database
        managementCapacityEvaluationRepository.saveAndFlush(managementCapacityEvaluation);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the managementCapacityEvaluation
        ManagementCapacityEvaluation updatedManagementCapacityEvaluation = managementCapacityEvaluationRepository
            .findById(managementCapacityEvaluation.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedManagementCapacityEvaluation are not directly saved in db
        em.detach(updatedManagementCapacityEvaluation);
        updatedManagementCapacityEvaluation
            .year(UPDATED_YEAR)
            .deprotment(UPDATED_DEPROTMENT)
            .createtime(UPDATED_CREATETIME)
            .status(UPDATED_STATUS)
            .totalmark(UPDATED_TOTALMARK)
            .mark(UPDATED_MARK)
            .ratingpersonname(UPDATED_RATINGPERSONNAME)
            .ratingdepartment(UPDATED_RATINGDEPARTMENT)
            .ratingtimg(UPDATED_RATINGTIMG);

        restManagementCapacityEvaluationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedManagementCapacityEvaluation.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedManagementCapacityEvaluation))
            )
            .andExpect(status().isOk());

        // Validate the ManagementCapacityEvaluation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedManagementCapacityEvaluationToMatchAllProperties(updatedManagementCapacityEvaluation);
    }

    @Test
    @Transactional
    void putNonExistingManagementCapacityEvaluation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        managementCapacityEvaluation.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restManagementCapacityEvaluationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, managementCapacityEvaluation.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(managementCapacityEvaluation))
            )
            .andExpect(status().isBadRequest());

        // Validate the ManagementCapacityEvaluation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchManagementCapacityEvaluation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        managementCapacityEvaluation.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restManagementCapacityEvaluationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(managementCapacityEvaluation))
            )
            .andExpect(status().isBadRequest());

        // Validate the ManagementCapacityEvaluation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamManagementCapacityEvaluation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        managementCapacityEvaluation.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restManagementCapacityEvaluationMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(managementCapacityEvaluation))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ManagementCapacityEvaluation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateManagementCapacityEvaluationWithPatch() throws Exception {
        // Initialize the database
        managementCapacityEvaluationRepository.saveAndFlush(managementCapacityEvaluation);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the managementCapacityEvaluation using partial update
        ManagementCapacityEvaluation partialUpdatedManagementCapacityEvaluation = new ManagementCapacityEvaluation();
        partialUpdatedManagementCapacityEvaluation.setId(managementCapacityEvaluation.getId());

        partialUpdatedManagementCapacityEvaluation
            .deprotment(UPDATED_DEPROTMENT)
            .createtime(UPDATED_CREATETIME)
            .status(UPDATED_STATUS)
            .ratingpersonname(UPDATED_RATINGPERSONNAME)
            .ratingdepartment(UPDATED_RATINGDEPARTMENT);

        restManagementCapacityEvaluationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedManagementCapacityEvaluation.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedManagementCapacityEvaluation))
            )
            .andExpect(status().isOk());

        // Validate the ManagementCapacityEvaluation in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertManagementCapacityEvaluationUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedManagementCapacityEvaluation, managementCapacityEvaluation),
            getPersistedManagementCapacityEvaluation(managementCapacityEvaluation)
        );
    }

    @Test
    @Transactional
    void fullUpdateManagementCapacityEvaluationWithPatch() throws Exception {
        // Initialize the database
        managementCapacityEvaluationRepository.saveAndFlush(managementCapacityEvaluation);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the managementCapacityEvaluation using partial update
        ManagementCapacityEvaluation partialUpdatedManagementCapacityEvaluation = new ManagementCapacityEvaluation();
        partialUpdatedManagementCapacityEvaluation.setId(managementCapacityEvaluation.getId());

        partialUpdatedManagementCapacityEvaluation
            .year(UPDATED_YEAR)
            .deprotment(UPDATED_DEPROTMENT)
            .createtime(UPDATED_CREATETIME)
            .status(UPDATED_STATUS)
            .totalmark(UPDATED_TOTALMARK)
            .mark(UPDATED_MARK)
            .ratingpersonname(UPDATED_RATINGPERSONNAME)
            .ratingdepartment(UPDATED_RATINGDEPARTMENT)
            .ratingtimg(UPDATED_RATINGTIMG);

        restManagementCapacityEvaluationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedManagementCapacityEvaluation.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedManagementCapacityEvaluation))
            )
            .andExpect(status().isOk());

        // Validate the ManagementCapacityEvaluation in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertManagementCapacityEvaluationUpdatableFieldsEquals(
            partialUpdatedManagementCapacityEvaluation,
            getPersistedManagementCapacityEvaluation(partialUpdatedManagementCapacityEvaluation)
        );
    }

    @Test
    @Transactional
    void patchNonExistingManagementCapacityEvaluation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        managementCapacityEvaluation.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restManagementCapacityEvaluationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, managementCapacityEvaluation.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(managementCapacityEvaluation))
            )
            .andExpect(status().isBadRequest());

        // Validate the ManagementCapacityEvaluation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchManagementCapacityEvaluation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        managementCapacityEvaluation.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restManagementCapacityEvaluationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(managementCapacityEvaluation))
            )
            .andExpect(status().isBadRequest());

        // Validate the ManagementCapacityEvaluation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamManagementCapacityEvaluation() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        managementCapacityEvaluation.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restManagementCapacityEvaluationMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(managementCapacityEvaluation))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ManagementCapacityEvaluation in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteManagementCapacityEvaluation() throws Exception {
        // Initialize the database
        managementCapacityEvaluationRepository.saveAndFlush(managementCapacityEvaluation);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the managementCapacityEvaluation
        restManagementCapacityEvaluationMockMvc
            .perform(delete(ENTITY_API_URL_ID, managementCapacityEvaluation.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return managementCapacityEvaluationRepository.count();
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

    protected ManagementCapacityEvaluation getPersistedManagementCapacityEvaluation(
        ManagementCapacityEvaluation managementCapacityEvaluation
    ) {
        return managementCapacityEvaluationRepository.findById(managementCapacityEvaluation.getId()).orElseThrow();
    }

    protected void assertPersistedManagementCapacityEvaluationToMatchAllProperties(
        ManagementCapacityEvaluation expectedManagementCapacityEvaluation
    ) {
        assertManagementCapacityEvaluationAllPropertiesEquals(
            expectedManagementCapacityEvaluation,
            getPersistedManagementCapacityEvaluation(expectedManagementCapacityEvaluation)
        );
    }

    protected void assertPersistedManagementCapacityEvaluationToMatchUpdatableProperties(
        ManagementCapacityEvaluation expectedManagementCapacityEvaluation
    ) {
        assertManagementCapacityEvaluationAllUpdatablePropertiesEquals(
            expectedManagementCapacityEvaluation,
            getPersistedManagementCapacityEvaluation(expectedManagementCapacityEvaluation)
        );
    }
}

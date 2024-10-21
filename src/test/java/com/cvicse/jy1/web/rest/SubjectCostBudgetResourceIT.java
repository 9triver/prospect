package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.SubjectCostBudgetAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.SubjectCostBudget;
import com.cvicse.jy1.repository.SubjectCostBudgetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
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
 * Integration tests for the {@link SubjectCostBudgetResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SubjectCostBudgetResourceIT {

    private static final String DEFAULT_CONTRACTID = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTID = "BBBBBBBBBB";

    private static final Integer DEFAULT_SUBJECTID = 1;
    private static final Integer UPDATED_SUBJECTID = 2;

    private static final String DEFAULT_SUBJECTNAME = "AAAAAAAAAA";
    private static final String UPDATED_SUBJECTNAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BUDGETAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_BUDGETAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ESTIMATEDAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ESTIMATEDAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_IMPLEMENTEDAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_IMPLEMENTEDAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_DIFFERENCE = new BigDecimal(1);
    private static final BigDecimal UPDATED_DIFFERENCE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PERCENTAGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PERCENTAGE = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/subject-cost-budgets";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SubjectCostBudgetRepository subjectCostBudgetRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSubjectCostBudgetMockMvc;

    private SubjectCostBudget subjectCostBudget;

    private SubjectCostBudget insertedSubjectCostBudget;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubjectCostBudget createEntity(EntityManager em) {
        SubjectCostBudget subjectCostBudget = new SubjectCostBudget()
            .contractid(DEFAULT_CONTRACTID)
            .subjectid(DEFAULT_SUBJECTID)
            .subjectname(DEFAULT_SUBJECTNAME)
            .budgetamount(DEFAULT_BUDGETAMOUNT)
            .estimatedamount(DEFAULT_ESTIMATEDAMOUNT)
            .implementedamount(DEFAULT_IMPLEMENTEDAMOUNT)
            .difference(DEFAULT_DIFFERENCE)
            .percentage(DEFAULT_PERCENTAGE);
        return subjectCostBudget;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SubjectCostBudget createUpdatedEntity(EntityManager em) {
        SubjectCostBudget subjectCostBudget = new SubjectCostBudget()
            .contractid(UPDATED_CONTRACTID)
            .subjectid(UPDATED_SUBJECTID)
            .subjectname(UPDATED_SUBJECTNAME)
            .budgetamount(UPDATED_BUDGETAMOUNT)
            .estimatedamount(UPDATED_ESTIMATEDAMOUNT)
            .implementedamount(UPDATED_IMPLEMENTEDAMOUNT)
            .difference(UPDATED_DIFFERENCE)
            .percentage(UPDATED_PERCENTAGE);
        return subjectCostBudget;
    }

    @BeforeEach
    public void initTest() {
        subjectCostBudget = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedSubjectCostBudget != null) {
            subjectCostBudgetRepository.delete(insertedSubjectCostBudget);
            insertedSubjectCostBudget = null;
        }
    }

    @Test
    @Transactional
    void createSubjectCostBudget() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the SubjectCostBudget
        var returnedSubjectCostBudget = om.readValue(
            restSubjectCostBudgetMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(subjectCostBudget)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            SubjectCostBudget.class
        );

        // Validate the SubjectCostBudget in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertSubjectCostBudgetUpdatableFieldsEquals(returnedSubjectCostBudget, getPersistedSubjectCostBudget(returnedSubjectCostBudget));

        insertedSubjectCostBudget = returnedSubjectCostBudget;
    }

    @Test
    @Transactional
    void createSubjectCostBudgetWithExistingId() throws Exception {
        // Create the SubjectCostBudget with an existing ID
        subjectCostBudget.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubjectCostBudgetMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(subjectCostBudget)))
            .andExpect(status().isBadRequest());

        // Validate the SubjectCostBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkContractidIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        subjectCostBudget.setContractid(null);

        // Create the SubjectCostBudget, which fails.

        restSubjectCostBudgetMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(subjectCostBudget)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkSubjectidIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        subjectCostBudget.setSubjectid(null);

        // Create the SubjectCostBudget, which fails.

        restSubjectCostBudgetMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(subjectCostBudget)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllSubjectCostBudgets() throws Exception {
        // Initialize the database
        insertedSubjectCostBudget = subjectCostBudgetRepository.saveAndFlush(subjectCostBudget);

        // Get all the subjectCostBudgetList
        restSubjectCostBudgetMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subjectCostBudget.getId().intValue())))
            .andExpect(jsonPath("$.[*].contractid").value(hasItem(DEFAULT_CONTRACTID)))
            .andExpect(jsonPath("$.[*].subjectid").value(hasItem(DEFAULT_SUBJECTID)))
            .andExpect(jsonPath("$.[*].subjectname").value(hasItem(DEFAULT_SUBJECTNAME)))
            .andExpect(jsonPath("$.[*].budgetamount").value(hasItem(sameNumber(DEFAULT_BUDGETAMOUNT))))
            .andExpect(jsonPath("$.[*].estimatedamount").value(hasItem(sameNumber(DEFAULT_ESTIMATEDAMOUNT))))
            .andExpect(jsonPath("$.[*].implementedamount").value(hasItem(sameNumber(DEFAULT_IMPLEMENTEDAMOUNT))))
            .andExpect(jsonPath("$.[*].difference").value(hasItem(sameNumber(DEFAULT_DIFFERENCE))))
            .andExpect(jsonPath("$.[*].percentage").value(hasItem(sameNumber(DEFAULT_PERCENTAGE))));
    }

    @Test
    @Transactional
    void getSubjectCostBudget() throws Exception {
        // Initialize the database
        insertedSubjectCostBudget = subjectCostBudgetRepository.saveAndFlush(subjectCostBudget);

        // Get the subjectCostBudget
        restSubjectCostBudgetMockMvc
            .perform(get(ENTITY_API_URL_ID, subjectCostBudget.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(subjectCostBudget.getId().intValue()))
            .andExpect(jsonPath("$.contractid").value(DEFAULT_CONTRACTID))
            .andExpect(jsonPath("$.subjectid").value(DEFAULT_SUBJECTID))
            .andExpect(jsonPath("$.subjectname").value(DEFAULT_SUBJECTNAME))
            .andExpect(jsonPath("$.budgetamount").value(sameNumber(DEFAULT_BUDGETAMOUNT)))
            .andExpect(jsonPath("$.estimatedamount").value(sameNumber(DEFAULT_ESTIMATEDAMOUNT)))
            .andExpect(jsonPath("$.implementedamount").value(sameNumber(DEFAULT_IMPLEMENTEDAMOUNT)))
            .andExpect(jsonPath("$.difference").value(sameNumber(DEFAULT_DIFFERENCE)))
            .andExpect(jsonPath("$.percentage").value(sameNumber(DEFAULT_PERCENTAGE)));
    }

    @Test
    @Transactional
    void getNonExistingSubjectCostBudget() throws Exception {
        // Get the subjectCostBudget
        restSubjectCostBudgetMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSubjectCostBudget() throws Exception {
        // Initialize the database
        insertedSubjectCostBudget = subjectCostBudgetRepository.saveAndFlush(subjectCostBudget);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the subjectCostBudget
        SubjectCostBudget updatedSubjectCostBudget = subjectCostBudgetRepository.findById(subjectCostBudget.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedSubjectCostBudget are not directly saved in db
        em.detach(updatedSubjectCostBudget);
        updatedSubjectCostBudget
            .contractid(UPDATED_CONTRACTID)
            .subjectid(UPDATED_SUBJECTID)
            .subjectname(UPDATED_SUBJECTNAME)
            .budgetamount(UPDATED_BUDGETAMOUNT)
            .estimatedamount(UPDATED_ESTIMATEDAMOUNT)
            .implementedamount(UPDATED_IMPLEMENTEDAMOUNT)
            .difference(UPDATED_DIFFERENCE)
            .percentage(UPDATED_PERCENTAGE);

        restSubjectCostBudgetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSubjectCostBudget.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedSubjectCostBudget))
            )
            .andExpect(status().isOk());

        // Validate the SubjectCostBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSubjectCostBudgetToMatchAllProperties(updatedSubjectCostBudget);
    }

    @Test
    @Transactional
    void putNonExistingSubjectCostBudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        subjectCostBudget.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubjectCostBudgetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, subjectCostBudget.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(subjectCostBudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the SubjectCostBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSubjectCostBudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        subjectCostBudget.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubjectCostBudgetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(subjectCostBudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the SubjectCostBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSubjectCostBudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        subjectCostBudget.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubjectCostBudgetMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(subjectCostBudget)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SubjectCostBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSubjectCostBudgetWithPatch() throws Exception {
        // Initialize the database
        insertedSubjectCostBudget = subjectCostBudgetRepository.saveAndFlush(subjectCostBudget);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the subjectCostBudget using partial update
        SubjectCostBudget partialUpdatedSubjectCostBudget = new SubjectCostBudget();
        partialUpdatedSubjectCostBudget.setId(subjectCostBudget.getId());

        partialUpdatedSubjectCostBudget
            .contractid(UPDATED_CONTRACTID)
            .subjectname(UPDATED_SUBJECTNAME)
            .estimatedamount(UPDATED_ESTIMATEDAMOUNT)
            .implementedamount(UPDATED_IMPLEMENTEDAMOUNT)
            .percentage(UPDATED_PERCENTAGE);

        restSubjectCostBudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSubjectCostBudget.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSubjectCostBudget))
            )
            .andExpect(status().isOk());

        // Validate the SubjectCostBudget in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSubjectCostBudgetUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSubjectCostBudget, subjectCostBudget),
            getPersistedSubjectCostBudget(subjectCostBudget)
        );
    }

    @Test
    @Transactional
    void fullUpdateSubjectCostBudgetWithPatch() throws Exception {
        // Initialize the database
        insertedSubjectCostBudget = subjectCostBudgetRepository.saveAndFlush(subjectCostBudget);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the subjectCostBudget using partial update
        SubjectCostBudget partialUpdatedSubjectCostBudget = new SubjectCostBudget();
        partialUpdatedSubjectCostBudget.setId(subjectCostBudget.getId());

        partialUpdatedSubjectCostBudget
            .contractid(UPDATED_CONTRACTID)
            .subjectid(UPDATED_SUBJECTID)
            .subjectname(UPDATED_SUBJECTNAME)
            .budgetamount(UPDATED_BUDGETAMOUNT)
            .estimatedamount(UPDATED_ESTIMATEDAMOUNT)
            .implementedamount(UPDATED_IMPLEMENTEDAMOUNT)
            .difference(UPDATED_DIFFERENCE)
            .percentage(UPDATED_PERCENTAGE);

        restSubjectCostBudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSubjectCostBudget.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSubjectCostBudget))
            )
            .andExpect(status().isOk());

        // Validate the SubjectCostBudget in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSubjectCostBudgetUpdatableFieldsEquals(
            partialUpdatedSubjectCostBudget,
            getPersistedSubjectCostBudget(partialUpdatedSubjectCostBudget)
        );
    }

    @Test
    @Transactional
    void patchNonExistingSubjectCostBudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        subjectCostBudget.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubjectCostBudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, subjectCostBudget.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(subjectCostBudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the SubjectCostBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSubjectCostBudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        subjectCostBudget.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubjectCostBudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(subjectCostBudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the SubjectCostBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSubjectCostBudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        subjectCostBudget.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSubjectCostBudgetMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(subjectCostBudget)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SubjectCostBudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSubjectCostBudget() throws Exception {
        // Initialize the database
        insertedSubjectCostBudget = subjectCostBudgetRepository.saveAndFlush(subjectCostBudget);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the subjectCostBudget
        restSubjectCostBudgetMockMvc
            .perform(delete(ENTITY_API_URL_ID, subjectCostBudget.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return subjectCostBudgetRepository.count();
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

    protected SubjectCostBudget getPersistedSubjectCostBudget(SubjectCostBudget subjectCostBudget) {
        return subjectCostBudgetRepository.findById(subjectCostBudget.getId()).orElseThrow();
    }

    protected void assertPersistedSubjectCostBudgetToMatchAllProperties(SubjectCostBudget expectedSubjectCostBudget) {
        assertSubjectCostBudgetAllPropertiesEquals(expectedSubjectCostBudget, getPersistedSubjectCostBudget(expectedSubjectCostBudget));
    }

    protected void assertPersistedSubjectCostBudgetToMatchUpdatableProperties(SubjectCostBudget expectedSubjectCostBudget) {
        assertSubjectCostBudgetAllUpdatablePropertiesEquals(
            expectedSubjectCostBudget,
            getPersistedSubjectCostBudget(expectedSubjectCostBudget)
        );
    }
}

package com.cvicse.web.rest;

import static com.cvicse.domain.TotalbudgetAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Totalbudget;
import com.cvicse.repository.TotalbudgetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
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
 * Integration tests for the {@link TotalbudgetResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TotalbudgetResourceIT {

    private static final Long DEFAULT_TOTALBUDGETID = 1L;
    private static final Long UPDATED_TOTALBUDGETID = 2L;

    private static final String DEFAULT_VALUATIONSUBJECTS = "AAAAAAAAAA";
    private static final String UPDATED_VALUATIONSUBJECTS = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BUDGET = new BigDecimal(1);
    private static final BigDecimal UPDATED_BUDGET = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PERCENTAGE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PERCENTAGE = new BigDecimal(2);

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/totalbudgets";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TotalbudgetRepository totalbudgetRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTotalbudgetMockMvc;

    private Totalbudget totalbudget;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Totalbudget createEntity(EntityManager em) {
        Totalbudget totalbudget = new Totalbudget()
            .totalbudgetid(DEFAULT_TOTALBUDGETID)
            .valuationsubjects(DEFAULT_VALUATIONSUBJECTS)
            .budget(DEFAULT_BUDGET)
            .percentage(DEFAULT_PERCENTAGE)
            .remarks(DEFAULT_REMARKS);
        return totalbudget;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Totalbudget createUpdatedEntity(EntityManager em) {
        Totalbudget totalbudget = new Totalbudget()
            .totalbudgetid(UPDATED_TOTALBUDGETID)
            .valuationsubjects(UPDATED_VALUATIONSUBJECTS)
            .budget(UPDATED_BUDGET)
            .percentage(UPDATED_PERCENTAGE)
            .remarks(UPDATED_REMARKS);
        return totalbudget;
    }

    @BeforeEach
    public void initTest() {
        totalbudget = createEntity(em);
    }

    @Test
    @Transactional
    void createTotalbudget() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Totalbudget
        var returnedTotalbudget = om.readValue(
            restTotalbudgetMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(totalbudget)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Totalbudget.class
        );

        // Validate the Totalbudget in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertTotalbudgetUpdatableFieldsEquals(returnedTotalbudget, getPersistedTotalbudget(returnedTotalbudget));
    }

    @Test
    @Transactional
    void createTotalbudgetWithExistingId() throws Exception {
        // Create the Totalbudget with an existing ID
        totalbudget.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTotalbudgetMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(totalbudget)))
            .andExpect(status().isBadRequest());

        // Validate the Totalbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTotalbudgets() throws Exception {
        // Initialize the database
        totalbudgetRepository.saveAndFlush(totalbudget);

        // Get all the totalbudgetList
        restTotalbudgetMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(totalbudget.getId().intValue())))
            .andExpect(jsonPath("$.[*].totalbudgetid").value(hasItem(DEFAULT_TOTALBUDGETID.intValue())))
            .andExpect(jsonPath("$.[*].valuationsubjects").value(hasItem(DEFAULT_VALUATIONSUBJECTS)))
            .andExpect(jsonPath("$.[*].budget").value(hasItem(sameNumber(DEFAULT_BUDGET))))
            .andExpect(jsonPath("$.[*].percentage").value(hasItem(sameNumber(DEFAULT_PERCENTAGE))))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)));
    }

    @Test
    @Transactional
    void getTotalbudget() throws Exception {
        // Initialize the database
        totalbudgetRepository.saveAndFlush(totalbudget);

        // Get the totalbudget
        restTotalbudgetMockMvc
            .perform(get(ENTITY_API_URL_ID, totalbudget.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(totalbudget.getId().intValue()))
            .andExpect(jsonPath("$.totalbudgetid").value(DEFAULT_TOTALBUDGETID.intValue()))
            .andExpect(jsonPath("$.valuationsubjects").value(DEFAULT_VALUATIONSUBJECTS))
            .andExpect(jsonPath("$.budget").value(sameNumber(DEFAULT_BUDGET)))
            .andExpect(jsonPath("$.percentage").value(sameNumber(DEFAULT_PERCENTAGE)))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS));
    }

    @Test
    @Transactional
    void getNonExistingTotalbudget() throws Exception {
        // Get the totalbudget
        restTotalbudgetMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTotalbudget() throws Exception {
        // Initialize the database
        totalbudgetRepository.saveAndFlush(totalbudget);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the totalbudget
        Totalbudget updatedTotalbudget = totalbudgetRepository.findById(totalbudget.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTotalbudget are not directly saved in db
        em.detach(updatedTotalbudget);
        updatedTotalbudget
            .totalbudgetid(UPDATED_TOTALBUDGETID)
            .valuationsubjects(UPDATED_VALUATIONSUBJECTS)
            .budget(UPDATED_BUDGET)
            .percentage(UPDATED_PERCENTAGE)
            .remarks(UPDATED_REMARKS);

        restTotalbudgetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTotalbudget.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedTotalbudget))
            )
            .andExpect(status().isOk());

        // Validate the Totalbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTotalbudgetToMatchAllProperties(updatedTotalbudget);
    }

    @Test
    @Transactional
    void putNonExistingTotalbudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        totalbudget.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTotalbudgetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, totalbudget.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(totalbudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the Totalbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTotalbudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        totalbudget.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTotalbudgetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(totalbudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the Totalbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTotalbudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        totalbudget.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTotalbudgetMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(totalbudget)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Totalbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTotalbudgetWithPatch() throws Exception {
        // Initialize the database
        totalbudgetRepository.saveAndFlush(totalbudget);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the totalbudget using partial update
        Totalbudget partialUpdatedTotalbudget = new Totalbudget();
        partialUpdatedTotalbudget.setId(totalbudget.getId());

        partialUpdatedTotalbudget.budget(UPDATED_BUDGET).percentage(UPDATED_PERCENTAGE);

        restTotalbudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTotalbudget.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTotalbudget))
            )
            .andExpect(status().isOk());

        // Validate the Totalbudget in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTotalbudgetUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTotalbudget, totalbudget),
            getPersistedTotalbudget(totalbudget)
        );
    }

    @Test
    @Transactional
    void fullUpdateTotalbudgetWithPatch() throws Exception {
        // Initialize the database
        totalbudgetRepository.saveAndFlush(totalbudget);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the totalbudget using partial update
        Totalbudget partialUpdatedTotalbudget = new Totalbudget();
        partialUpdatedTotalbudget.setId(totalbudget.getId());

        partialUpdatedTotalbudget
            .totalbudgetid(UPDATED_TOTALBUDGETID)
            .valuationsubjects(UPDATED_VALUATIONSUBJECTS)
            .budget(UPDATED_BUDGET)
            .percentage(UPDATED_PERCENTAGE)
            .remarks(UPDATED_REMARKS);

        restTotalbudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTotalbudget.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTotalbudget))
            )
            .andExpect(status().isOk());

        // Validate the Totalbudget in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTotalbudgetUpdatableFieldsEquals(partialUpdatedTotalbudget, getPersistedTotalbudget(partialUpdatedTotalbudget));
    }

    @Test
    @Transactional
    void patchNonExistingTotalbudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        totalbudget.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTotalbudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, totalbudget.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(totalbudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the Totalbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTotalbudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        totalbudget.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTotalbudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(totalbudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the Totalbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTotalbudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        totalbudget.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTotalbudgetMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(totalbudget)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Totalbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTotalbudget() throws Exception {
        // Initialize the database
        totalbudgetRepository.saveAndFlush(totalbudget);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the totalbudget
        restTotalbudgetMockMvc
            .perform(delete(ENTITY_API_URL_ID, totalbudget.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return totalbudgetRepository.count();
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

    protected Totalbudget getPersistedTotalbudget(Totalbudget totalbudget) {
        return totalbudgetRepository.findById(totalbudget.getId()).orElseThrow();
    }

    protected void assertPersistedTotalbudgetToMatchAllProperties(Totalbudget expectedTotalbudget) {
        assertTotalbudgetAllPropertiesEquals(expectedTotalbudget, getPersistedTotalbudget(expectedTotalbudget));
    }

    protected void assertPersistedTotalbudgetToMatchUpdatableProperties(Totalbudget expectedTotalbudget) {
        assertTotalbudgetAllUpdatablePropertiesEquals(expectedTotalbudget, getPersistedTotalbudget(expectedTotalbudget));
    }
}

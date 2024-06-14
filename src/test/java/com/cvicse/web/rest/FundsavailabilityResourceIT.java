package com.cvicse.web.rest;

import static com.cvicse.domain.FundsavailabilityAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Fundsavailability;
import com.cvicse.repository.FundsavailabilityRepository;
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
 * Integration tests for the {@link FundsavailabilityResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FundsavailabilityResourceIT {

    private static final Long DEFAULT_FUNDSAVAILABILITYID = 1L;
    private static final Long UPDATED_FUNDSAVAILABILITYID = 2L;

    private static final Long DEFAULT_FUNDSID = 1L;
    private static final Long UPDATED_FUNDSID = 2L;

    private static final Long DEFAULT_YEAR = 1L;
    private static final Long UPDATED_YEAR = 2L;

    private static final BigDecimal DEFAULT_BUDGIT = new BigDecimal(1);
    private static final BigDecimal UPDATED_BUDGIT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_FUNDING = new BigDecimal(1);
    private static final BigDecimal UPDATED_FUNDING = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/fundsavailabilities";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FundsavailabilityRepository fundsavailabilityRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFundsavailabilityMockMvc;

    private Fundsavailability fundsavailability;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fundsavailability createEntity(EntityManager em) {
        Fundsavailability fundsavailability = new Fundsavailability()
            .fundsavailabilityid(DEFAULT_FUNDSAVAILABILITYID)
            .fundsid(DEFAULT_FUNDSID)
            .year(DEFAULT_YEAR)
            .budgit(DEFAULT_BUDGIT)
            .funding(DEFAULT_FUNDING);
        return fundsavailability;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fundsavailability createUpdatedEntity(EntityManager em) {
        Fundsavailability fundsavailability = new Fundsavailability()
            .fundsavailabilityid(UPDATED_FUNDSAVAILABILITYID)
            .fundsid(UPDATED_FUNDSID)
            .year(UPDATED_YEAR)
            .budgit(UPDATED_BUDGIT)
            .funding(UPDATED_FUNDING);
        return fundsavailability;
    }

    @BeforeEach
    public void initTest() {
        fundsavailability = createEntity(em);
    }

    @Test
    @Transactional
    void createFundsavailability() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Fundsavailability
        var returnedFundsavailability = om.readValue(
            restFundsavailabilityMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fundsavailability)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Fundsavailability.class
        );

        // Validate the Fundsavailability in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertFundsavailabilityUpdatableFieldsEquals(returnedFundsavailability, getPersistedFundsavailability(returnedFundsavailability));
    }

    @Test
    @Transactional
    void createFundsavailabilityWithExistingId() throws Exception {
        // Create the Fundsavailability with an existing ID
        fundsavailability.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFundsavailabilityMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fundsavailability)))
            .andExpect(status().isBadRequest());

        // Validate the Fundsavailability in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFundsavailabilities() throws Exception {
        // Initialize the database
        fundsavailabilityRepository.saveAndFlush(fundsavailability);

        // Get all the fundsavailabilityList
        restFundsavailabilityMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fundsavailability.getId().intValue())))
            .andExpect(jsonPath("$.[*].fundsavailabilityid").value(hasItem(DEFAULT_FUNDSAVAILABILITYID.intValue())))
            .andExpect(jsonPath("$.[*].fundsid").value(hasItem(DEFAULT_FUNDSID.intValue())))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR.intValue())))
            .andExpect(jsonPath("$.[*].budgit").value(hasItem(sameNumber(DEFAULT_BUDGIT))))
            .andExpect(jsonPath("$.[*].funding").value(hasItem(sameNumber(DEFAULT_FUNDING))));
    }

    @Test
    @Transactional
    void getFundsavailability() throws Exception {
        // Initialize the database
        fundsavailabilityRepository.saveAndFlush(fundsavailability);

        // Get the fundsavailability
        restFundsavailabilityMockMvc
            .perform(get(ENTITY_API_URL_ID, fundsavailability.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fundsavailability.getId().intValue()))
            .andExpect(jsonPath("$.fundsavailabilityid").value(DEFAULT_FUNDSAVAILABILITYID.intValue()))
            .andExpect(jsonPath("$.fundsid").value(DEFAULT_FUNDSID.intValue()))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR.intValue()))
            .andExpect(jsonPath("$.budgit").value(sameNumber(DEFAULT_BUDGIT)))
            .andExpect(jsonPath("$.funding").value(sameNumber(DEFAULT_FUNDING)));
    }

    @Test
    @Transactional
    void getNonExistingFundsavailability() throws Exception {
        // Get the fundsavailability
        restFundsavailabilityMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFundsavailability() throws Exception {
        // Initialize the database
        fundsavailabilityRepository.saveAndFlush(fundsavailability);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fundsavailability
        Fundsavailability updatedFundsavailability = fundsavailabilityRepository.findById(fundsavailability.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFundsavailability are not directly saved in db
        em.detach(updatedFundsavailability);
        updatedFundsavailability
            .fundsavailabilityid(UPDATED_FUNDSAVAILABILITYID)
            .fundsid(UPDATED_FUNDSID)
            .year(UPDATED_YEAR)
            .budgit(UPDATED_BUDGIT)
            .funding(UPDATED_FUNDING);

        restFundsavailabilityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFundsavailability.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedFundsavailability))
            )
            .andExpect(status().isOk());

        // Validate the Fundsavailability in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFundsavailabilityToMatchAllProperties(updatedFundsavailability);
    }

    @Test
    @Transactional
    void putNonExistingFundsavailability() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsavailability.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFundsavailabilityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fundsavailability.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fundsavailability))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fundsavailability in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFundsavailability() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsavailability.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsavailabilityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fundsavailability))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fundsavailability in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFundsavailability() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsavailability.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsavailabilityMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fundsavailability)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Fundsavailability in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFundsavailabilityWithPatch() throws Exception {
        // Initialize the database
        fundsavailabilityRepository.saveAndFlush(fundsavailability);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fundsavailability using partial update
        Fundsavailability partialUpdatedFundsavailability = new Fundsavailability();
        partialUpdatedFundsavailability.setId(fundsavailability.getId());

        restFundsavailabilityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFundsavailability.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFundsavailability))
            )
            .andExpect(status().isOk());

        // Validate the Fundsavailability in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFundsavailabilityUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFundsavailability, fundsavailability),
            getPersistedFundsavailability(fundsavailability)
        );
    }

    @Test
    @Transactional
    void fullUpdateFundsavailabilityWithPatch() throws Exception {
        // Initialize the database
        fundsavailabilityRepository.saveAndFlush(fundsavailability);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fundsavailability using partial update
        Fundsavailability partialUpdatedFundsavailability = new Fundsavailability();
        partialUpdatedFundsavailability.setId(fundsavailability.getId());

        partialUpdatedFundsavailability
            .fundsavailabilityid(UPDATED_FUNDSAVAILABILITYID)
            .fundsid(UPDATED_FUNDSID)
            .year(UPDATED_YEAR)
            .budgit(UPDATED_BUDGIT)
            .funding(UPDATED_FUNDING);

        restFundsavailabilityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFundsavailability.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFundsavailability))
            )
            .andExpect(status().isOk());

        // Validate the Fundsavailability in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFundsavailabilityUpdatableFieldsEquals(
            partialUpdatedFundsavailability,
            getPersistedFundsavailability(partialUpdatedFundsavailability)
        );
    }

    @Test
    @Transactional
    void patchNonExistingFundsavailability() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsavailability.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFundsavailabilityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fundsavailability.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fundsavailability))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fundsavailability in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFundsavailability() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsavailability.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsavailabilityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fundsavailability))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fundsavailability in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFundsavailability() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsavailability.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsavailabilityMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(fundsavailability)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Fundsavailability in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFundsavailability() throws Exception {
        // Initialize the database
        fundsavailabilityRepository.saveAndFlush(fundsavailability);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the fundsavailability
        restFundsavailabilityMockMvc
            .perform(delete(ENTITY_API_URL_ID, fundsavailability.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return fundsavailabilityRepository.count();
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

    protected Fundsavailability getPersistedFundsavailability(Fundsavailability fundsavailability) {
        return fundsavailabilityRepository.findById(fundsavailability.getId()).orElseThrow();
    }

    protected void assertPersistedFundsavailabilityToMatchAllProperties(Fundsavailability expectedFundsavailability) {
        assertFundsavailabilityAllPropertiesEquals(expectedFundsavailability, getPersistedFundsavailability(expectedFundsavailability));
    }

    protected void assertPersistedFundsavailabilityToMatchUpdatableProperties(Fundsavailability expectedFundsavailability) {
        assertFundsavailabilityAllUpdatablePropertiesEquals(
            expectedFundsavailability,
            getPersistedFundsavailability(expectedFundsavailability)
        );
    }
}

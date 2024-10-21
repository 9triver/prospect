package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.RiskReturnAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.RiskReturn;
import com.cvicse.jy1.repository.RiskReturnRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link RiskReturnResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RiskReturnResourceIT {

    private static final Integer DEFAULT_BELONGRISKID = 1;
    private static final Integer UPDATED_BELONGRISKID = 2;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CLOSESTATUS = "AAAAAAAAAA";
    private static final String UPDATED_CLOSESTATUS = "BBBBBBBBBB";

    private static final String DEFAULT_EVIDENCEFILE = "AAAAAAAAAA";
    private static final String UPDATED_EVIDENCEFILE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/risk-returns";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RiskReturnRepository riskReturnRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRiskReturnMockMvc;

    private RiskReturn riskReturn;

    private RiskReturn insertedRiskReturn;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskReturn createEntity(EntityManager em) {
        RiskReturn riskReturn = new RiskReturn()
            .belongriskid(DEFAULT_BELONGRISKID)
            .status(DEFAULT_STATUS)
            .closestatus(DEFAULT_CLOSESTATUS)
            .evidencefile(DEFAULT_EVIDENCEFILE);
        return riskReturn;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskReturn createUpdatedEntity(EntityManager em) {
        RiskReturn riskReturn = new RiskReturn()
            .belongriskid(UPDATED_BELONGRISKID)
            .status(UPDATED_STATUS)
            .closestatus(UPDATED_CLOSESTATUS)
            .evidencefile(UPDATED_EVIDENCEFILE);
        return riskReturn;
    }

    @BeforeEach
    public void initTest() {
        riskReturn = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedRiskReturn != null) {
            riskReturnRepository.delete(insertedRiskReturn);
            insertedRiskReturn = null;
        }
    }

    @Test
    @Transactional
    void createRiskReturn() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the RiskReturn
        var returnedRiskReturn = om.readValue(
            restRiskReturnMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskReturn)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            RiskReturn.class
        );

        // Validate the RiskReturn in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRiskReturnUpdatableFieldsEquals(returnedRiskReturn, getPersistedRiskReturn(returnedRiskReturn));

        insertedRiskReturn = returnedRiskReturn;
    }

    @Test
    @Transactional
    void createRiskReturnWithExistingId() throws Exception {
        // Create the RiskReturn with an existing ID
        riskReturn.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskReturnMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskReturn)))
            .andExpect(status().isBadRequest());

        // Validate the RiskReturn in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRiskReturns() throws Exception {
        // Initialize the database
        insertedRiskReturn = riskReturnRepository.saveAndFlush(riskReturn);

        // Get all the riskReturnList
        restRiskReturnMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskReturn.getId().intValue())))
            .andExpect(jsonPath("$.[*].belongriskid").value(hasItem(DEFAULT_BELONGRISKID)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].closestatus").value(hasItem(DEFAULT_CLOSESTATUS)))
            .andExpect(jsonPath("$.[*].evidencefile").value(hasItem(DEFAULT_EVIDENCEFILE)));
    }

    @Test
    @Transactional
    void getRiskReturn() throws Exception {
        // Initialize the database
        insertedRiskReturn = riskReturnRepository.saveAndFlush(riskReturn);

        // Get the riskReturn
        restRiskReturnMockMvc
            .perform(get(ENTITY_API_URL_ID, riskReturn.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(riskReturn.getId().intValue()))
            .andExpect(jsonPath("$.belongriskid").value(DEFAULT_BELONGRISKID))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.closestatus").value(DEFAULT_CLOSESTATUS))
            .andExpect(jsonPath("$.evidencefile").value(DEFAULT_EVIDENCEFILE));
    }

    @Test
    @Transactional
    void getNonExistingRiskReturn() throws Exception {
        // Get the riskReturn
        restRiskReturnMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRiskReturn() throws Exception {
        // Initialize the database
        insertedRiskReturn = riskReturnRepository.saveAndFlush(riskReturn);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskReturn
        RiskReturn updatedRiskReturn = riskReturnRepository.findById(riskReturn.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedRiskReturn are not directly saved in db
        em.detach(updatedRiskReturn);
        updatedRiskReturn
            .belongriskid(UPDATED_BELONGRISKID)
            .status(UPDATED_STATUS)
            .closestatus(UPDATED_CLOSESTATUS)
            .evidencefile(UPDATED_EVIDENCEFILE);

        restRiskReturnMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRiskReturn.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRiskReturn))
            )
            .andExpect(status().isOk());

        // Validate the RiskReturn in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRiskReturnToMatchAllProperties(updatedRiskReturn);
    }

    @Test
    @Transactional
    void putNonExistingRiskReturn() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskReturn.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskReturnMockMvc
            .perform(
                put(ENTITY_API_URL_ID, riskReturn.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskReturn))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskReturn in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRiskReturn() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskReturn.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskReturnMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(riskReturn))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskReturn in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRiskReturn() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskReturn.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskReturnMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskReturn)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RiskReturn in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRiskReturnWithPatch() throws Exception {
        // Initialize the database
        insertedRiskReturn = riskReturnRepository.saveAndFlush(riskReturn);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskReturn using partial update
        RiskReturn partialUpdatedRiskReturn = new RiskReturn();
        partialUpdatedRiskReturn.setId(riskReturn.getId());

        restRiskReturnMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskReturn.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskReturn))
            )
            .andExpect(status().isOk());

        // Validate the RiskReturn in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskReturnUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedRiskReturn, riskReturn),
            getPersistedRiskReturn(riskReturn)
        );
    }

    @Test
    @Transactional
    void fullUpdateRiskReturnWithPatch() throws Exception {
        // Initialize the database
        insertedRiskReturn = riskReturnRepository.saveAndFlush(riskReturn);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskReturn using partial update
        RiskReturn partialUpdatedRiskReturn = new RiskReturn();
        partialUpdatedRiskReturn.setId(riskReturn.getId());

        partialUpdatedRiskReturn
            .belongriskid(UPDATED_BELONGRISKID)
            .status(UPDATED_STATUS)
            .closestatus(UPDATED_CLOSESTATUS)
            .evidencefile(UPDATED_EVIDENCEFILE);

        restRiskReturnMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskReturn.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskReturn))
            )
            .andExpect(status().isOk());

        // Validate the RiskReturn in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskReturnUpdatableFieldsEquals(partialUpdatedRiskReturn, getPersistedRiskReturn(partialUpdatedRiskReturn));
    }

    @Test
    @Transactional
    void patchNonExistingRiskReturn() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskReturn.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskReturnMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, riskReturn.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskReturn))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskReturn in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRiskReturn() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskReturn.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskReturnMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskReturn))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskReturn in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRiskReturn() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskReturn.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskReturnMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(riskReturn)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RiskReturn in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRiskReturn() throws Exception {
        // Initialize the database
        insertedRiskReturn = riskReturnRepository.saveAndFlush(riskReturn);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the riskReturn
        restRiskReturnMockMvc
            .perform(delete(ENTITY_API_URL_ID, riskReturn.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return riskReturnRepository.count();
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

    protected RiskReturn getPersistedRiskReturn(RiskReturn riskReturn) {
        return riskReturnRepository.findById(riskReturn.getId()).orElseThrow();
    }

    protected void assertPersistedRiskReturnToMatchAllProperties(RiskReturn expectedRiskReturn) {
        assertRiskReturnAllPropertiesEquals(expectedRiskReturn, getPersistedRiskReturn(expectedRiskReturn));
    }

    protected void assertPersistedRiskReturnToMatchUpdatableProperties(RiskReturn expectedRiskReturn) {
        assertRiskReturnAllUpdatablePropertiesEquals(expectedRiskReturn, getPersistedRiskReturn(expectedRiskReturn));
    }
}

package com.cvicse.web.rest;

import static com.cvicse.domain.RiskmanagementAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Riskmanagement;
import com.cvicse.domain.enumeration.Risklevel;
import com.cvicse.repository.RiskmanagementRepository;
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
 * Integration tests for the {@link RiskmanagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RiskmanagementResourceIT {

    private static final Long DEFAULT_RISKID = 1L;
    private static final Long UPDATED_RISKID = 2L;

    private static final String DEFAULT_PROJECTNAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTNAME = "BBBBBBBBBB";

    private static final Long DEFAULT_YEAR = 1L;
    private static final Long UPDATED_YEAR = 2L;

    private static final String DEFAULT_NODENAME = "AAAAAAAAAA";
    private static final String UPDATED_NODENAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_RISKTYPE = 1;
    private static final Integer UPDATED_RISKTYPE = 2;

    private static final Long DEFAULT_DECUMENTID = 1L;
    private static final Long UPDATED_DECUMENTID = 2L;

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    private static final LocalDate DEFAULT_USETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_USETIME = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_SYSTEMLEVEL = 1;
    private static final Integer UPDATED_SYSTEMLEVEL = 2;

    private static final Risklevel DEFAULT_RISKLEVEL = Risklevel.One;
    private static final Risklevel UPDATED_RISKLEVEL = Risklevel.Two;

    private static final String DEFAULT_LIMITATIONTIME = "AAAAAAAAAA";
    private static final String UPDATED_LIMITATIONTIME = "BBBBBBBBBB";

    private static final Integer DEFAULT_CLOSETYPE = 1;
    private static final Integer UPDATED_CLOSETYPE = 2;

    private static final String ENTITY_API_URL = "/api/riskmanagements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RiskmanagementRepository riskmanagementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRiskmanagementMockMvc;

    private Riskmanagement riskmanagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Riskmanagement createEntity(EntityManager em) {
        Riskmanagement riskmanagement = new Riskmanagement()
            .riskid(DEFAULT_RISKID)
            .projectname(DEFAULT_PROJECTNAME)
            .year(DEFAULT_YEAR)
            .nodename(DEFAULT_NODENAME)
            .risktype(DEFAULT_RISKTYPE)
            .decumentid(DEFAULT_DECUMENTID)
            .version(DEFAULT_VERSION)
            .usetime(DEFAULT_USETIME)
            .systemlevel(DEFAULT_SYSTEMLEVEL)
            .risklevel(DEFAULT_RISKLEVEL)
            .limitationtime(DEFAULT_LIMITATIONTIME)
            .closetype(DEFAULT_CLOSETYPE);
        return riskmanagement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Riskmanagement createUpdatedEntity(EntityManager em) {
        Riskmanagement riskmanagement = new Riskmanagement()
            .riskid(UPDATED_RISKID)
            .projectname(UPDATED_PROJECTNAME)
            .year(UPDATED_YEAR)
            .nodename(UPDATED_NODENAME)
            .risktype(UPDATED_RISKTYPE)
            .decumentid(UPDATED_DECUMENTID)
            .version(UPDATED_VERSION)
            .usetime(UPDATED_USETIME)
            .systemlevel(UPDATED_SYSTEMLEVEL)
            .risklevel(UPDATED_RISKLEVEL)
            .limitationtime(UPDATED_LIMITATIONTIME)
            .closetype(UPDATED_CLOSETYPE);
        return riskmanagement;
    }

    @BeforeEach
    public void initTest() {
        riskmanagement = createEntity(em);
    }

    @Test
    @Transactional
    void createRiskmanagement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Riskmanagement
        var returnedRiskmanagement = om.readValue(
            restRiskmanagementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskmanagement)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Riskmanagement.class
        );

        // Validate the Riskmanagement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRiskmanagementUpdatableFieldsEquals(returnedRiskmanagement, getPersistedRiskmanagement(returnedRiskmanagement));
    }

    @Test
    @Transactional
    void createRiskmanagementWithExistingId() throws Exception {
        // Create the Riskmanagement with an existing ID
        riskmanagement.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskmanagementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskmanagement)))
            .andExpect(status().isBadRequest());

        // Validate the Riskmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRiskmanagements() throws Exception {
        // Initialize the database
        riskmanagementRepository.saveAndFlush(riskmanagement);

        // Get all the riskmanagementList
        restRiskmanagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskmanagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].riskid").value(hasItem(DEFAULT_RISKID.intValue())))
            .andExpect(jsonPath("$.[*].projectname").value(hasItem(DEFAULT_PROJECTNAME)))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR.intValue())))
            .andExpect(jsonPath("$.[*].nodename").value(hasItem(DEFAULT_NODENAME)))
            .andExpect(jsonPath("$.[*].risktype").value(hasItem(DEFAULT_RISKTYPE)))
            .andExpect(jsonPath("$.[*].decumentid").value(hasItem(DEFAULT_DECUMENTID.intValue())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].usetime").value(hasItem(DEFAULT_USETIME.toString())))
            .andExpect(jsonPath("$.[*].systemlevel").value(hasItem(DEFAULT_SYSTEMLEVEL)))
            .andExpect(jsonPath("$.[*].risklevel").value(hasItem(DEFAULT_RISKLEVEL.toString())))
            .andExpect(jsonPath("$.[*].limitationtime").value(hasItem(DEFAULT_LIMITATIONTIME)))
            .andExpect(jsonPath("$.[*].closetype").value(hasItem(DEFAULT_CLOSETYPE)));
    }

    @Test
    @Transactional
    void getRiskmanagement() throws Exception {
        // Initialize the database
        riskmanagementRepository.saveAndFlush(riskmanagement);

        // Get the riskmanagement
        restRiskmanagementMockMvc
            .perform(get(ENTITY_API_URL_ID, riskmanagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(riskmanagement.getId().intValue()))
            .andExpect(jsonPath("$.riskid").value(DEFAULT_RISKID.intValue()))
            .andExpect(jsonPath("$.projectname").value(DEFAULT_PROJECTNAME))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR.intValue()))
            .andExpect(jsonPath("$.nodename").value(DEFAULT_NODENAME))
            .andExpect(jsonPath("$.risktype").value(DEFAULT_RISKTYPE))
            .andExpect(jsonPath("$.decumentid").value(DEFAULT_DECUMENTID.intValue()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.usetime").value(DEFAULT_USETIME.toString()))
            .andExpect(jsonPath("$.systemlevel").value(DEFAULT_SYSTEMLEVEL))
            .andExpect(jsonPath("$.risklevel").value(DEFAULT_RISKLEVEL.toString()))
            .andExpect(jsonPath("$.limitationtime").value(DEFAULT_LIMITATIONTIME))
            .andExpect(jsonPath("$.closetype").value(DEFAULT_CLOSETYPE));
    }

    @Test
    @Transactional
    void getNonExistingRiskmanagement() throws Exception {
        // Get the riskmanagement
        restRiskmanagementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRiskmanagement() throws Exception {
        // Initialize the database
        riskmanagementRepository.saveAndFlush(riskmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskmanagement
        Riskmanagement updatedRiskmanagement = riskmanagementRepository.findById(riskmanagement.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedRiskmanagement are not directly saved in db
        em.detach(updatedRiskmanagement);
        updatedRiskmanagement
            .riskid(UPDATED_RISKID)
            .projectname(UPDATED_PROJECTNAME)
            .year(UPDATED_YEAR)
            .nodename(UPDATED_NODENAME)
            .risktype(UPDATED_RISKTYPE)
            .decumentid(UPDATED_DECUMENTID)
            .version(UPDATED_VERSION)
            .usetime(UPDATED_USETIME)
            .systemlevel(UPDATED_SYSTEMLEVEL)
            .risklevel(UPDATED_RISKLEVEL)
            .limitationtime(UPDATED_LIMITATIONTIME)
            .closetype(UPDATED_CLOSETYPE);

        restRiskmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRiskmanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRiskmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Riskmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRiskmanagementToMatchAllProperties(updatedRiskmanagement);
    }

    @Test
    @Transactional
    void putNonExistingRiskmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskmanagement.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, riskmanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(riskmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Riskmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRiskmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskmanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(riskmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Riskmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRiskmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskmanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskmanagementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskmanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Riskmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRiskmanagementWithPatch() throws Exception {
        // Initialize the database
        riskmanagementRepository.saveAndFlush(riskmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskmanagement using partial update
        Riskmanagement partialUpdatedRiskmanagement = new Riskmanagement();
        partialUpdatedRiskmanagement.setId(riskmanagement.getId());

        partialUpdatedRiskmanagement
            .riskid(UPDATED_RISKID)
            .risktype(UPDATED_RISKTYPE)
            .decumentid(UPDATED_DECUMENTID)
            .version(UPDATED_VERSION)
            .usetime(UPDATED_USETIME)
            .limitationtime(UPDATED_LIMITATIONTIME)
            .closetype(UPDATED_CLOSETYPE);

        restRiskmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Riskmanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskmanagementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedRiskmanagement, riskmanagement),
            getPersistedRiskmanagement(riskmanagement)
        );
    }

    @Test
    @Transactional
    void fullUpdateRiskmanagementWithPatch() throws Exception {
        // Initialize the database
        riskmanagementRepository.saveAndFlush(riskmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskmanagement using partial update
        Riskmanagement partialUpdatedRiskmanagement = new Riskmanagement();
        partialUpdatedRiskmanagement.setId(riskmanagement.getId());

        partialUpdatedRiskmanagement
            .riskid(UPDATED_RISKID)
            .projectname(UPDATED_PROJECTNAME)
            .year(UPDATED_YEAR)
            .nodename(UPDATED_NODENAME)
            .risktype(UPDATED_RISKTYPE)
            .decumentid(UPDATED_DECUMENTID)
            .version(UPDATED_VERSION)
            .usetime(UPDATED_USETIME)
            .systemlevel(UPDATED_SYSTEMLEVEL)
            .risklevel(UPDATED_RISKLEVEL)
            .limitationtime(UPDATED_LIMITATIONTIME)
            .closetype(UPDATED_CLOSETYPE);

        restRiskmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Riskmanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskmanagementUpdatableFieldsEquals(partialUpdatedRiskmanagement, getPersistedRiskmanagement(partialUpdatedRiskmanagement));
    }

    @Test
    @Transactional
    void patchNonExistingRiskmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskmanagement.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, riskmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Riskmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRiskmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskmanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Riskmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRiskmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskmanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskmanagementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(riskmanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Riskmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRiskmanagement() throws Exception {
        // Initialize the database
        riskmanagementRepository.saveAndFlush(riskmanagement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the riskmanagement
        restRiskmanagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, riskmanagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return riskmanagementRepository.count();
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

    protected Riskmanagement getPersistedRiskmanagement(Riskmanagement riskmanagement) {
        return riskmanagementRepository.findById(riskmanagement.getId()).orElseThrow();
    }

    protected void assertPersistedRiskmanagementToMatchAllProperties(Riskmanagement expectedRiskmanagement) {
        assertRiskmanagementAllPropertiesEquals(expectedRiskmanagement, getPersistedRiskmanagement(expectedRiskmanagement));
    }

    protected void assertPersistedRiskmanagementToMatchUpdatableProperties(Riskmanagement expectedRiskmanagement) {
        assertRiskmanagementAllUpdatablePropertiesEquals(expectedRiskmanagement, getPersistedRiskmanagement(expectedRiskmanagement));
    }
}

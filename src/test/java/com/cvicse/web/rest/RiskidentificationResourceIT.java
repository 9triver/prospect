package com.cvicse.web.rest;

import static com.cvicse.domain.RiskidentificationAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Riskidentification;
import com.cvicse.domain.enumeration.Risklevel;
import com.cvicse.repository.RiskidentificationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RiskidentificationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RiskidentificationResourceIT {

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

    private static final String ENTITY_API_URL = "/api/riskidentifications";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RiskidentificationRepository riskidentificationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRiskidentificationMockMvc;

    private Riskidentification riskidentification;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Riskidentification createEntity(EntityManager em) {
        Riskidentification riskidentification = new Riskidentification()
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
        return riskidentification;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Riskidentification createUpdatedEntity(EntityManager em) {
        Riskidentification riskidentification = new Riskidentification()
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
        return riskidentification;
    }

    @BeforeEach
    public void initTest() {
        riskidentification = createEntity(em);
    }

    @Test
    @Transactional
    void createRiskidentification() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Riskidentification
        var returnedRiskidentification = om.readValue(
            restRiskidentificationMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskidentification)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Riskidentification.class
        );

        // Validate the Riskidentification in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRiskidentificationUpdatableFieldsEquals(
            returnedRiskidentification,
            getPersistedRiskidentification(returnedRiskidentification)
        );
    }

    @Test
    @Transactional
    void createRiskidentificationWithExistingId() throws Exception {
        // Create the Riskidentification with an existing ID
        riskidentification.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskidentificationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskidentification)))
            .andExpect(status().isBadRequest());

        // Validate the Riskidentification in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRiskidentifications() throws Exception {
        // Initialize the database
        riskidentificationRepository.saveAndFlush(riskidentification);

        // Get all the riskidentificationList
        restRiskidentificationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskidentification.getId())))
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
    void getRiskidentification() throws Exception {
        // Initialize the database
        riskidentificationRepository.saveAndFlush(riskidentification);

        // Get the riskidentification
        restRiskidentificationMockMvc
            .perform(get(ENTITY_API_URL_ID, riskidentification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(riskidentification.getId()))
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
    void getNonExistingRiskidentification() throws Exception {
        // Get the riskidentification
        restRiskidentificationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRiskidentification() throws Exception {
        // Initialize the database
        riskidentificationRepository.saveAndFlush(riskidentification);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskidentification
        Riskidentification updatedRiskidentification = riskidentificationRepository.findById(riskidentification.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedRiskidentification are not directly saved in db
        em.detach(updatedRiskidentification);
        updatedRiskidentification
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

        restRiskidentificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRiskidentification.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRiskidentification))
            )
            .andExpect(status().isOk());

        // Validate the Riskidentification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRiskidentificationToMatchAllProperties(updatedRiskidentification);
    }

    @Test
    @Transactional
    void putNonExistingRiskidentification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskidentification.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskidentificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, riskidentification.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(riskidentification))
            )
            .andExpect(status().isBadRequest());

        // Validate the Riskidentification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRiskidentification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskidentification.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskidentificationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(riskidentification))
            )
            .andExpect(status().isBadRequest());

        // Validate the Riskidentification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRiskidentification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskidentification.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskidentificationMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskidentification)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Riskidentification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRiskidentificationWithPatch() throws Exception {
        // Initialize the database
        riskidentificationRepository.saveAndFlush(riskidentification);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskidentification using partial update
        Riskidentification partialUpdatedRiskidentification = new Riskidentification();
        partialUpdatedRiskidentification.setId(riskidentification.getId());

        partialUpdatedRiskidentification
            .projectname(UPDATED_PROJECTNAME)
            .year(UPDATED_YEAR)
            .nodename(UPDATED_NODENAME)
            .risktype(UPDATED_RISKTYPE)
            .decumentid(UPDATED_DECUMENTID)
            .usetime(UPDATED_USETIME)
            .risklevel(UPDATED_RISKLEVEL)
            .closetype(UPDATED_CLOSETYPE);

        restRiskidentificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskidentification.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskidentification))
            )
            .andExpect(status().isOk());

        // Validate the Riskidentification in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskidentificationUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedRiskidentification, riskidentification),
            getPersistedRiskidentification(riskidentification)
        );
    }

    @Test
    @Transactional
    void fullUpdateRiskidentificationWithPatch() throws Exception {
        // Initialize the database
        riskidentificationRepository.saveAndFlush(riskidentification);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskidentification using partial update
        Riskidentification partialUpdatedRiskidentification = new Riskidentification();
        partialUpdatedRiskidentification.setId(riskidentification.getId());

        partialUpdatedRiskidentification
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

        restRiskidentificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskidentification.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskidentification))
            )
            .andExpect(status().isOk());

        // Validate the Riskidentification in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskidentificationUpdatableFieldsEquals(
            partialUpdatedRiskidentification,
            getPersistedRiskidentification(partialUpdatedRiskidentification)
        );
    }

    @Test
    @Transactional
    void patchNonExistingRiskidentification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskidentification.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskidentificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, riskidentification.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskidentification))
            )
            .andExpect(status().isBadRequest());

        // Validate the Riskidentification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRiskidentification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskidentification.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskidentificationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskidentification))
            )
            .andExpect(status().isBadRequest());

        // Validate the Riskidentification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRiskidentification() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskidentification.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskidentificationMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(riskidentification)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Riskidentification in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRiskidentification() throws Exception {
        // Initialize the database
        riskidentificationRepository.saveAndFlush(riskidentification);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the riskidentification
        restRiskidentificationMockMvc
            .perform(delete(ENTITY_API_URL_ID, riskidentification.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return riskidentificationRepository.count();
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

    protected Riskidentification getPersistedRiskidentification(Riskidentification riskidentification) {
        return riskidentificationRepository.findById(riskidentification.getId()).orElseThrow();
    }

    protected void assertPersistedRiskidentificationToMatchAllProperties(Riskidentification expectedRiskidentification) {
        assertRiskidentificationAllPropertiesEquals(expectedRiskidentification, getPersistedRiskidentification(expectedRiskidentification));
    }

    protected void assertPersistedRiskidentificationToMatchUpdatableProperties(Riskidentification expectedRiskidentification) {
        assertRiskidentificationAllUpdatablePropertiesEquals(
            expectedRiskidentification,
            getPersistedRiskidentification(expectedRiskidentification)
        );
    }
}

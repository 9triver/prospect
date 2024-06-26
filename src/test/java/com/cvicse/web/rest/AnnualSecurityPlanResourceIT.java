package com.cvicse.web.rest;

import static com.cvicse.domain.AnnualSecurityPlanAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.AnnualSecurityPlan;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.repository.AnnualSecurityPlanRepository;
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
 * Integration tests for the {@link AnnualSecurityPlanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AnnualSecurityPlanResourceIT {

    private static final String DEFAULT_SECURITYPLANNAME = "AAAAAAAAAA";
    private static final String UPDATED_SECURITYPLANNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RELEASETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RELEASETIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_CREATETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATETIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATORNAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATORNAME = "BBBBBBBBBB";

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    private static final String ENTITY_API_URL = "/api/annual-security-plans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AnnualSecurityPlanRepository annualSecurityPlanRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnnualSecurityPlanMockMvc;

    private AnnualSecurityPlan annualSecurityPlan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnnualSecurityPlan createEntity(EntityManager em) {
        AnnualSecurityPlan annualSecurityPlan = new AnnualSecurityPlan()
            .securityplanname(DEFAULT_SECURITYPLANNAME)
            .releasetime(DEFAULT_RELEASETIME)
            .createtime(DEFAULT_CREATETIME)
            .creatorname(DEFAULT_CREATORNAME)
            .auditStatus(DEFAULT_AUDIT_STATUS)
            .version(DEFAULT_VERSION);
        return annualSecurityPlan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnnualSecurityPlan createUpdatedEntity(EntityManager em) {
        AnnualSecurityPlan annualSecurityPlan = new AnnualSecurityPlan()
            .securityplanname(UPDATED_SECURITYPLANNAME)
            .releasetime(UPDATED_RELEASETIME)
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .version(UPDATED_VERSION);
        return annualSecurityPlan;
    }

    @BeforeEach
    public void initTest() {
        annualSecurityPlan = createEntity(em);
    }

    @Test
    @Transactional
    void createAnnualSecurityPlan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the AnnualSecurityPlan
        var returnedAnnualSecurityPlan = om.readValue(
            restAnnualSecurityPlanMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(annualSecurityPlan)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            AnnualSecurityPlan.class
        );

        // Validate the AnnualSecurityPlan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAnnualSecurityPlanUpdatableFieldsEquals(
            returnedAnnualSecurityPlan,
            getPersistedAnnualSecurityPlan(returnedAnnualSecurityPlan)
        );
    }

    @Test
    @Transactional
    void createAnnualSecurityPlanWithExistingId() throws Exception {
        // Create the AnnualSecurityPlan with an existing ID
        annualSecurityPlan.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnnualSecurityPlanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(annualSecurityPlan)))
            .andExpect(status().isBadRequest());

        // Validate the AnnualSecurityPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAnnualSecurityPlans() throws Exception {
        // Initialize the database
        annualSecurityPlanRepository.saveAndFlush(annualSecurityPlan);

        // Get all the annualSecurityPlanList
        restAnnualSecurityPlanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(annualSecurityPlan.getId())))
            .andExpect(jsonPath("$.[*].securityplanname").value(hasItem(DEFAULT_SECURITYPLANNAME)))
            .andExpect(jsonPath("$.[*].releasetime").value(hasItem(DEFAULT_RELEASETIME.toString())))
            .andExpect(jsonPath("$.[*].createtime").value(hasItem(DEFAULT_CREATETIME.toString())))
            .andExpect(jsonPath("$.[*].creatorname").value(hasItem(DEFAULT_CREATORNAME)))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)));
    }

    @Test
    @Transactional
    void getAnnualSecurityPlan() throws Exception {
        // Initialize the database
        annualSecurityPlanRepository.saveAndFlush(annualSecurityPlan);

        // Get the annualSecurityPlan
        restAnnualSecurityPlanMockMvc
            .perform(get(ENTITY_API_URL_ID, annualSecurityPlan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(annualSecurityPlan.getId()))
            .andExpect(jsonPath("$.securityplanname").value(DEFAULT_SECURITYPLANNAME))
            .andExpect(jsonPath("$.releasetime").value(DEFAULT_RELEASETIME.toString()))
            .andExpect(jsonPath("$.createtime").value(DEFAULT_CREATETIME.toString()))
            .andExpect(jsonPath("$.creatorname").value(DEFAULT_CREATORNAME))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION));
    }

    @Test
    @Transactional
    void getNonExistingAnnualSecurityPlan() throws Exception {
        // Get the annualSecurityPlan
        restAnnualSecurityPlanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAnnualSecurityPlan() throws Exception {
        // Initialize the database
        annualSecurityPlanRepository.saveAndFlush(annualSecurityPlan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the annualSecurityPlan
        AnnualSecurityPlan updatedAnnualSecurityPlan = annualSecurityPlanRepository.findById(annualSecurityPlan.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAnnualSecurityPlan are not directly saved in db
        em.detach(updatedAnnualSecurityPlan);
        updatedAnnualSecurityPlan
            .securityplanname(UPDATED_SECURITYPLANNAME)
            .releasetime(UPDATED_RELEASETIME)
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .version(UPDATED_VERSION);

        restAnnualSecurityPlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAnnualSecurityPlan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAnnualSecurityPlan))
            )
            .andExpect(status().isOk());

        // Validate the AnnualSecurityPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAnnualSecurityPlanToMatchAllProperties(updatedAnnualSecurityPlan);
    }

    @Test
    @Transactional
    void putNonExistingAnnualSecurityPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        annualSecurityPlan.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnnualSecurityPlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, annualSecurityPlan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(annualSecurityPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnnualSecurityPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAnnualSecurityPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        annualSecurityPlan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnnualSecurityPlanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(annualSecurityPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnnualSecurityPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAnnualSecurityPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        annualSecurityPlan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnnualSecurityPlanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(annualSecurityPlan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AnnualSecurityPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAnnualSecurityPlanWithPatch() throws Exception {
        // Initialize the database
        annualSecurityPlanRepository.saveAndFlush(annualSecurityPlan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the annualSecurityPlan using partial update
        AnnualSecurityPlan partialUpdatedAnnualSecurityPlan = new AnnualSecurityPlan();
        partialUpdatedAnnualSecurityPlan.setId(annualSecurityPlan.getId());

        partialUpdatedAnnualSecurityPlan
            .securityplanname(UPDATED_SECURITYPLANNAME)
            .createtime(UPDATED_CREATETIME)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restAnnualSecurityPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnnualSecurityPlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAnnualSecurityPlan))
            )
            .andExpect(status().isOk());

        // Validate the AnnualSecurityPlan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAnnualSecurityPlanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAnnualSecurityPlan, annualSecurityPlan),
            getPersistedAnnualSecurityPlan(annualSecurityPlan)
        );
    }

    @Test
    @Transactional
    void fullUpdateAnnualSecurityPlanWithPatch() throws Exception {
        // Initialize the database
        annualSecurityPlanRepository.saveAndFlush(annualSecurityPlan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the annualSecurityPlan using partial update
        AnnualSecurityPlan partialUpdatedAnnualSecurityPlan = new AnnualSecurityPlan();
        partialUpdatedAnnualSecurityPlan.setId(annualSecurityPlan.getId());

        partialUpdatedAnnualSecurityPlan
            .securityplanname(UPDATED_SECURITYPLANNAME)
            .releasetime(UPDATED_RELEASETIME)
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .version(UPDATED_VERSION);

        restAnnualSecurityPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAnnualSecurityPlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAnnualSecurityPlan))
            )
            .andExpect(status().isOk());

        // Validate the AnnualSecurityPlan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAnnualSecurityPlanUpdatableFieldsEquals(
            partialUpdatedAnnualSecurityPlan,
            getPersistedAnnualSecurityPlan(partialUpdatedAnnualSecurityPlan)
        );
    }

    @Test
    @Transactional
    void patchNonExistingAnnualSecurityPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        annualSecurityPlan.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnnualSecurityPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, annualSecurityPlan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(annualSecurityPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnnualSecurityPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAnnualSecurityPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        annualSecurityPlan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnnualSecurityPlanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(annualSecurityPlan))
            )
            .andExpect(status().isBadRequest());

        // Validate the AnnualSecurityPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAnnualSecurityPlan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        annualSecurityPlan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAnnualSecurityPlanMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(annualSecurityPlan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the AnnualSecurityPlan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAnnualSecurityPlan() throws Exception {
        // Initialize the database
        annualSecurityPlanRepository.saveAndFlush(annualSecurityPlan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the annualSecurityPlan
        restAnnualSecurityPlanMockMvc
            .perform(delete(ENTITY_API_URL_ID, annualSecurityPlan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return annualSecurityPlanRepository.count();
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

    protected AnnualSecurityPlan getPersistedAnnualSecurityPlan(AnnualSecurityPlan annualSecurityPlan) {
        return annualSecurityPlanRepository.findById(annualSecurityPlan.getId()).orElseThrow();
    }

    protected void assertPersistedAnnualSecurityPlanToMatchAllProperties(AnnualSecurityPlan expectedAnnualSecurityPlan) {
        assertAnnualSecurityPlanAllPropertiesEquals(expectedAnnualSecurityPlan, getPersistedAnnualSecurityPlan(expectedAnnualSecurityPlan));
    }

    protected void assertPersistedAnnualSecurityPlanToMatchUpdatableProperties(AnnualSecurityPlan expectedAnnualSecurityPlan) {
        assertAnnualSecurityPlanAllUpdatablePropertiesEquals(
            expectedAnnualSecurityPlan,
            getPersistedAnnualSecurityPlan(expectedAnnualSecurityPlan)
        );
    }
}

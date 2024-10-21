package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.RiskReportAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.RiskReport;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.repository.RiskReportRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link RiskReportResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RiskReportResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_YEAR = 1;
    private static final Integer UPDATED_YEAR = 2;

    private static final String DEFAULT_RISKREPORTNAME = "AAAAAAAAAA";
    private static final String UPDATED_RISKREPORTNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_REPORTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REPORTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.NOT_AUDITED;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.IN_AUDIT;

    private static final String ENTITY_API_URL = "/api/risk-reports";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RiskReportRepository riskReportRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRiskReportMockMvc;

    private RiskReport riskReport;

    private RiskReport insertedRiskReport;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskReport createEntity(EntityManager em) {
        RiskReport riskReport = new RiskReport()
            .type(DEFAULT_TYPE)
            .year(DEFAULT_YEAR)
            .riskreportname(DEFAULT_RISKREPORTNAME)
            .reporttime(DEFAULT_REPORTTIME)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return riskReport;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RiskReport createUpdatedEntity(EntityManager em) {
        RiskReport riskReport = new RiskReport()
            .type(UPDATED_TYPE)
            .year(UPDATED_YEAR)
            .riskreportname(UPDATED_RISKREPORTNAME)
            .reporttime(UPDATED_REPORTTIME)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return riskReport;
    }

    @BeforeEach
    public void initTest() {
        riskReport = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedRiskReport != null) {
            riskReportRepository.delete(insertedRiskReport);
            insertedRiskReport = null;
        }
    }

    @Test
    @Transactional
    void createRiskReport() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the RiskReport
        var returnedRiskReport = om.readValue(
            restRiskReportMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskReport)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            RiskReport.class
        );

        // Validate the RiskReport in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRiskReportUpdatableFieldsEquals(returnedRiskReport, getPersistedRiskReport(returnedRiskReport));

        insertedRiskReport = returnedRiskReport;
    }

    @Test
    @Transactional
    void createRiskReportWithExistingId() throws Exception {
        // Create the RiskReport with an existing ID
        riskReport.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskReportMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskReport)))
            .andExpect(status().isBadRequest());

        // Validate the RiskReport in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRiskReports() throws Exception {
        // Initialize the database
        insertedRiskReport = riskReportRepository.saveAndFlush(riskReport);

        // Get all the riskReportList
        restRiskReportMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskReport.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)))
            .andExpect(jsonPath("$.[*].riskreportname").value(hasItem(DEFAULT_RISKREPORTNAME)))
            .andExpect(jsonPath("$.[*].reporttime").value(hasItem(DEFAULT_REPORTTIME.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getRiskReport() throws Exception {
        // Initialize the database
        insertedRiskReport = riskReportRepository.saveAndFlush(riskReport);

        // Get the riskReport
        restRiskReportMockMvc
            .perform(get(ENTITY_API_URL_ID, riskReport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(riskReport.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR))
            .andExpect(jsonPath("$.riskreportname").value(DEFAULT_RISKREPORTNAME))
            .andExpect(jsonPath("$.reporttime").value(DEFAULT_REPORTTIME.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingRiskReport() throws Exception {
        // Get the riskReport
        restRiskReportMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRiskReport() throws Exception {
        // Initialize the database
        insertedRiskReport = riskReportRepository.saveAndFlush(riskReport);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskReport
        RiskReport updatedRiskReport = riskReportRepository.findById(riskReport.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedRiskReport are not directly saved in db
        em.detach(updatedRiskReport);
        updatedRiskReport
            .type(UPDATED_TYPE)
            .year(UPDATED_YEAR)
            .riskreportname(UPDATED_RISKREPORTNAME)
            .reporttime(UPDATED_REPORTTIME)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restRiskReportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRiskReport.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRiskReport))
            )
            .andExpect(status().isOk());

        // Validate the RiskReport in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRiskReportToMatchAllProperties(updatedRiskReport);
    }

    @Test
    @Transactional
    void putNonExistingRiskReport() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskReport.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskReportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, riskReport.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskReport in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRiskReport() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskReport.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskReportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(riskReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskReport in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRiskReport() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskReport.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskReportMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskReport)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RiskReport in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRiskReportWithPatch() throws Exception {
        // Initialize the database
        insertedRiskReport = riskReportRepository.saveAndFlush(riskReport);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskReport using partial update
        RiskReport partialUpdatedRiskReport = new RiskReport();
        partialUpdatedRiskReport.setId(riskReport.getId());

        partialUpdatedRiskReport.type(UPDATED_TYPE).riskreportname(UPDATED_RISKREPORTNAME).auditStatus(UPDATED_AUDIT_STATUS);

        restRiskReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskReport.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskReport))
            )
            .andExpect(status().isOk());

        // Validate the RiskReport in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskReportUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedRiskReport, riskReport),
            getPersistedRiskReport(riskReport)
        );
    }

    @Test
    @Transactional
    void fullUpdateRiskReportWithPatch() throws Exception {
        // Initialize the database
        insertedRiskReport = riskReportRepository.saveAndFlush(riskReport);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskReport using partial update
        RiskReport partialUpdatedRiskReport = new RiskReport();
        partialUpdatedRiskReport.setId(riskReport.getId());

        partialUpdatedRiskReport
            .type(UPDATED_TYPE)
            .year(UPDATED_YEAR)
            .riskreportname(UPDATED_RISKREPORTNAME)
            .reporttime(UPDATED_REPORTTIME)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restRiskReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskReport.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskReport))
            )
            .andExpect(status().isOk());

        // Validate the RiskReport in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskReportUpdatableFieldsEquals(partialUpdatedRiskReport, getPersistedRiskReport(partialUpdatedRiskReport));
    }

    @Test
    @Transactional
    void patchNonExistingRiskReport() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskReport.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, riskReport.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskReport in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRiskReport() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskReport.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskReportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskReport))
            )
            .andExpect(status().isBadRequest());

        // Validate the RiskReport in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRiskReport() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskReport.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskReportMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(riskReport)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RiskReport in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRiskReport() throws Exception {
        // Initialize the database
        insertedRiskReport = riskReportRepository.saveAndFlush(riskReport);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the riskReport
        restRiskReportMockMvc
            .perform(delete(ENTITY_API_URL_ID, riskReport.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return riskReportRepository.count();
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

    protected RiskReport getPersistedRiskReport(RiskReport riskReport) {
        return riskReportRepository.findById(riskReport.getId()).orElseThrow();
    }

    protected void assertPersistedRiskReportToMatchAllProperties(RiskReport expectedRiskReport) {
        assertRiskReportAllPropertiesEquals(expectedRiskReport, getPersistedRiskReport(expectedRiskReport));
    }

    protected void assertPersistedRiskReportToMatchUpdatableProperties(RiskReport expectedRiskReport) {
        assertRiskReportAllUpdatablePropertiesEquals(expectedRiskReport, getPersistedRiskReport(expectedRiskReport));
    }
}

package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.DeviationPermitApplicationAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.DeviationPermitApplication;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.repository.DeviationPermitApplicationRepository;
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
 * Integration tests for the {@link DeviationPermitApplicationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DeviationPermitApplicationResourceIT {

    private static final String DEFAULT_WBSID = "AAAAAAAAAA";
    private static final String UPDATED_WBSID = "BBBBBBBBBB";

    private static final String DEFAULT_TECHNICALFILEID = "AAAAAAAAAA";
    private static final String UPDATED_TECHNICALFILEID = "BBBBBBBBBB";

    private static final String DEFAULT_APPLICATIONUNIT = "AAAAAAAAAA";
    private static final String UPDATED_APPLICATIONUNIT = "BBBBBBBBBB";

    private static final String DEFAULT_APPLICANT = "AAAAAAAAAA";
    private static final String UPDATED_APPLICANT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_APPLICATIONDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_APPLICATIONDATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PERMITCONTENT = "AAAAAAAAAA";
    private static final String UPDATED_PERMITCONTENT = "BBBBBBBBBB";

    private static final String DEFAULT_PERMITREASON = "AAAAAAAAAA";
    private static final String UPDATED_PERMITREASON = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECTINFLUENCE = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTINFLUENCE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTINFLUENCE = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTINFLUENCE = "BBBBBBBBBB";

    private static final String DEFAULT_PERMITRANGE = "AAAAAAAAAA";
    private static final String UPDATED_PERMITRANGE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_IMPLEMENTATIONDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_IMPLEMENTATIONDATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.NOT_AUDITED;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.IN_AUDIT;

    private static final String ENTITY_API_URL = "/api/deviation-permit-applications";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DeviationPermitApplicationRepository deviationPermitApplicationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDeviationPermitApplicationMockMvc;

    private DeviationPermitApplication deviationPermitApplication;

    private DeviationPermitApplication insertedDeviationPermitApplication;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeviationPermitApplication createEntity(EntityManager em) {
        DeviationPermitApplication deviationPermitApplication = new DeviationPermitApplication()
            .wbsid(DEFAULT_WBSID)
            .technicalfileid(DEFAULT_TECHNICALFILEID)
            .applicationunit(DEFAULT_APPLICATIONUNIT)
            .applicant(DEFAULT_APPLICANT)
            .applicationdate(DEFAULT_APPLICATIONDATE)
            .permitcontent(DEFAULT_PERMITCONTENT)
            .permitreason(DEFAULT_PERMITREASON)
            .projectinfluence(DEFAULT_PROJECTINFLUENCE)
            .contractinfluence(DEFAULT_CONTRACTINFLUENCE)
            .permitrange(DEFAULT_PERMITRANGE)
            .implementationdate(DEFAULT_IMPLEMENTATIONDATE)
            .remarks(DEFAULT_REMARKS)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return deviationPermitApplication;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DeviationPermitApplication createUpdatedEntity(EntityManager em) {
        DeviationPermitApplication deviationPermitApplication = new DeviationPermitApplication()
            .wbsid(UPDATED_WBSID)
            .technicalfileid(UPDATED_TECHNICALFILEID)
            .applicationunit(UPDATED_APPLICATIONUNIT)
            .applicant(UPDATED_APPLICANT)
            .applicationdate(UPDATED_APPLICATIONDATE)
            .permitcontent(UPDATED_PERMITCONTENT)
            .permitreason(UPDATED_PERMITREASON)
            .projectinfluence(UPDATED_PROJECTINFLUENCE)
            .contractinfluence(UPDATED_CONTRACTINFLUENCE)
            .permitrange(UPDATED_PERMITRANGE)
            .implementationdate(UPDATED_IMPLEMENTATIONDATE)
            .remarks(UPDATED_REMARKS)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return deviationPermitApplication;
    }

    @BeforeEach
    public void initTest() {
        deviationPermitApplication = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDeviationPermitApplication != null) {
            deviationPermitApplicationRepository.delete(insertedDeviationPermitApplication);
            insertedDeviationPermitApplication = null;
        }
    }

    @Test
    @Transactional
    void createDeviationPermitApplication() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the DeviationPermitApplication
        var returnedDeviationPermitApplication = om.readValue(
            restDeviationPermitApplicationMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(deviationPermitApplication))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            DeviationPermitApplication.class
        );

        // Validate the DeviationPermitApplication in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertDeviationPermitApplicationUpdatableFieldsEquals(
            returnedDeviationPermitApplication,
            getPersistedDeviationPermitApplication(returnedDeviationPermitApplication)
        );

        insertedDeviationPermitApplication = returnedDeviationPermitApplication;
    }

    @Test
    @Transactional
    void createDeviationPermitApplicationWithExistingId() throws Exception {
        // Create the DeviationPermitApplication with an existing ID
        deviationPermitApplication.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeviationPermitApplicationMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(deviationPermitApplication)))
            .andExpect(status().isBadRequest());

        // Validate the DeviationPermitApplication in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDeviationPermitApplications() throws Exception {
        // Initialize the database
        insertedDeviationPermitApplication = deviationPermitApplicationRepository.saveAndFlush(deviationPermitApplication);

        // Get all the deviationPermitApplicationList
        restDeviationPermitApplicationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deviationPermitApplication.getId().intValue())))
            .andExpect(jsonPath("$.[*].wbsid").value(hasItem(DEFAULT_WBSID)))
            .andExpect(jsonPath("$.[*].technicalfileid").value(hasItem(DEFAULT_TECHNICALFILEID)))
            .andExpect(jsonPath("$.[*].applicationunit").value(hasItem(DEFAULT_APPLICATIONUNIT)))
            .andExpect(jsonPath("$.[*].applicant").value(hasItem(DEFAULT_APPLICANT)))
            .andExpect(jsonPath("$.[*].applicationdate").value(hasItem(DEFAULT_APPLICATIONDATE.toString())))
            .andExpect(jsonPath("$.[*].permitcontent").value(hasItem(DEFAULT_PERMITCONTENT)))
            .andExpect(jsonPath("$.[*].permitreason").value(hasItem(DEFAULT_PERMITREASON)))
            .andExpect(jsonPath("$.[*].projectinfluence").value(hasItem(DEFAULT_PROJECTINFLUENCE)))
            .andExpect(jsonPath("$.[*].contractinfluence").value(hasItem(DEFAULT_CONTRACTINFLUENCE)))
            .andExpect(jsonPath("$.[*].permitrange").value(hasItem(DEFAULT_PERMITRANGE)))
            .andExpect(jsonPath("$.[*].implementationdate").value(hasItem(DEFAULT_IMPLEMENTATIONDATE.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getDeviationPermitApplication() throws Exception {
        // Initialize the database
        insertedDeviationPermitApplication = deviationPermitApplicationRepository.saveAndFlush(deviationPermitApplication);

        // Get the deviationPermitApplication
        restDeviationPermitApplicationMockMvc
            .perform(get(ENTITY_API_URL_ID, deviationPermitApplication.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(deviationPermitApplication.getId().intValue()))
            .andExpect(jsonPath("$.wbsid").value(DEFAULT_WBSID))
            .andExpect(jsonPath("$.technicalfileid").value(DEFAULT_TECHNICALFILEID))
            .andExpect(jsonPath("$.applicationunit").value(DEFAULT_APPLICATIONUNIT))
            .andExpect(jsonPath("$.applicant").value(DEFAULT_APPLICANT))
            .andExpect(jsonPath("$.applicationdate").value(DEFAULT_APPLICATIONDATE.toString()))
            .andExpect(jsonPath("$.permitcontent").value(DEFAULT_PERMITCONTENT))
            .andExpect(jsonPath("$.permitreason").value(DEFAULT_PERMITREASON))
            .andExpect(jsonPath("$.projectinfluence").value(DEFAULT_PROJECTINFLUENCE))
            .andExpect(jsonPath("$.contractinfluence").value(DEFAULT_CONTRACTINFLUENCE))
            .andExpect(jsonPath("$.permitrange").value(DEFAULT_PERMITRANGE))
            .andExpect(jsonPath("$.implementationdate").value(DEFAULT_IMPLEMENTATIONDATE.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingDeviationPermitApplication() throws Exception {
        // Get the deviationPermitApplication
        restDeviationPermitApplicationMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDeviationPermitApplication() throws Exception {
        // Initialize the database
        insertedDeviationPermitApplication = deviationPermitApplicationRepository.saveAndFlush(deviationPermitApplication);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the deviationPermitApplication
        DeviationPermitApplication updatedDeviationPermitApplication = deviationPermitApplicationRepository
            .findById(deviationPermitApplication.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedDeviationPermitApplication are not directly saved in db
        em.detach(updatedDeviationPermitApplication);
        updatedDeviationPermitApplication
            .wbsid(UPDATED_WBSID)
            .technicalfileid(UPDATED_TECHNICALFILEID)
            .applicationunit(UPDATED_APPLICATIONUNIT)
            .applicant(UPDATED_APPLICANT)
            .applicationdate(UPDATED_APPLICATIONDATE)
            .permitcontent(UPDATED_PERMITCONTENT)
            .permitreason(UPDATED_PERMITREASON)
            .projectinfluence(UPDATED_PROJECTINFLUENCE)
            .contractinfluence(UPDATED_CONTRACTINFLUENCE)
            .permitrange(UPDATED_PERMITRANGE)
            .implementationdate(UPDATED_IMPLEMENTATIONDATE)
            .remarks(UPDATED_REMARKS)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restDeviationPermitApplicationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDeviationPermitApplication.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedDeviationPermitApplication))
            )
            .andExpect(status().isOk());

        // Validate the DeviationPermitApplication in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDeviationPermitApplicationToMatchAllProperties(updatedDeviationPermitApplication);
    }

    @Test
    @Transactional
    void putNonExistingDeviationPermitApplication() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        deviationPermitApplication.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeviationPermitApplicationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, deviationPermitApplication.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(deviationPermitApplication))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeviationPermitApplication in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDeviationPermitApplication() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        deviationPermitApplication.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeviationPermitApplicationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(deviationPermitApplication))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeviationPermitApplication in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDeviationPermitApplication() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        deviationPermitApplication.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeviationPermitApplicationMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(deviationPermitApplication)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the DeviationPermitApplication in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDeviationPermitApplicationWithPatch() throws Exception {
        // Initialize the database
        insertedDeviationPermitApplication = deviationPermitApplicationRepository.saveAndFlush(deviationPermitApplication);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the deviationPermitApplication using partial update
        DeviationPermitApplication partialUpdatedDeviationPermitApplication = new DeviationPermitApplication();
        partialUpdatedDeviationPermitApplication.setId(deviationPermitApplication.getId());

        partialUpdatedDeviationPermitApplication
            .applicationunit(UPDATED_APPLICATIONUNIT)
            .applicant(UPDATED_APPLICANT)
            .applicationdate(UPDATED_APPLICATIONDATE)
            .permitcontent(UPDATED_PERMITCONTENT)
            .contractinfluence(UPDATED_CONTRACTINFLUENCE)
            .remarks(UPDATED_REMARKS)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restDeviationPermitApplicationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeviationPermitApplication.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDeviationPermitApplication))
            )
            .andExpect(status().isOk());

        // Validate the DeviationPermitApplication in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDeviationPermitApplicationUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDeviationPermitApplication, deviationPermitApplication),
            getPersistedDeviationPermitApplication(deviationPermitApplication)
        );
    }

    @Test
    @Transactional
    void fullUpdateDeviationPermitApplicationWithPatch() throws Exception {
        // Initialize the database
        insertedDeviationPermitApplication = deviationPermitApplicationRepository.saveAndFlush(deviationPermitApplication);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the deviationPermitApplication using partial update
        DeviationPermitApplication partialUpdatedDeviationPermitApplication = new DeviationPermitApplication();
        partialUpdatedDeviationPermitApplication.setId(deviationPermitApplication.getId());

        partialUpdatedDeviationPermitApplication
            .wbsid(UPDATED_WBSID)
            .technicalfileid(UPDATED_TECHNICALFILEID)
            .applicationunit(UPDATED_APPLICATIONUNIT)
            .applicant(UPDATED_APPLICANT)
            .applicationdate(UPDATED_APPLICATIONDATE)
            .permitcontent(UPDATED_PERMITCONTENT)
            .permitreason(UPDATED_PERMITREASON)
            .projectinfluence(UPDATED_PROJECTINFLUENCE)
            .contractinfluence(UPDATED_CONTRACTINFLUENCE)
            .permitrange(UPDATED_PERMITRANGE)
            .implementationdate(UPDATED_IMPLEMENTATIONDATE)
            .remarks(UPDATED_REMARKS)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restDeviationPermitApplicationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeviationPermitApplication.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDeviationPermitApplication))
            )
            .andExpect(status().isOk());

        // Validate the DeviationPermitApplication in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDeviationPermitApplicationUpdatableFieldsEquals(
            partialUpdatedDeviationPermitApplication,
            getPersistedDeviationPermitApplication(partialUpdatedDeviationPermitApplication)
        );
    }

    @Test
    @Transactional
    void patchNonExistingDeviationPermitApplication() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        deviationPermitApplication.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeviationPermitApplicationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, deviationPermitApplication.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(deviationPermitApplication))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeviationPermitApplication in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDeviationPermitApplication() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        deviationPermitApplication.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeviationPermitApplicationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(deviationPermitApplication))
            )
            .andExpect(status().isBadRequest());

        // Validate the DeviationPermitApplication in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDeviationPermitApplication() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        deviationPermitApplication.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeviationPermitApplicationMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(deviationPermitApplication))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the DeviationPermitApplication in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDeviationPermitApplication() throws Exception {
        // Initialize the database
        insertedDeviationPermitApplication = deviationPermitApplicationRepository.saveAndFlush(deviationPermitApplication);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the deviationPermitApplication
        restDeviationPermitApplicationMockMvc
            .perform(delete(ENTITY_API_URL_ID, deviationPermitApplication.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return deviationPermitApplicationRepository.count();
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

    protected DeviationPermitApplication getPersistedDeviationPermitApplication(DeviationPermitApplication deviationPermitApplication) {
        return deviationPermitApplicationRepository.findById(deviationPermitApplication.getId()).orElseThrow();
    }

    protected void assertPersistedDeviationPermitApplicationToMatchAllProperties(
        DeviationPermitApplication expectedDeviationPermitApplication
    ) {
        assertDeviationPermitApplicationAllPropertiesEquals(
            expectedDeviationPermitApplication,
            getPersistedDeviationPermitApplication(expectedDeviationPermitApplication)
        );
    }

    protected void assertPersistedDeviationPermitApplicationToMatchUpdatableProperties(
        DeviationPermitApplication expectedDeviationPermitApplication
    ) {
        assertDeviationPermitApplicationAllUpdatablePropertiesEquals(
            expectedDeviationPermitApplication,
            getPersistedDeviationPermitApplication(expectedDeviationPermitApplication)
        );
    }
}

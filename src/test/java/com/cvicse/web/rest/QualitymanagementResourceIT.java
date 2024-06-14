package com.cvicse.web.rest;

import static com.cvicse.domain.QualitymanagementAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Qualitymanagement;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.QualitymanagementRepository;
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
 * Integration tests for the {@link QualitymanagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QualitymanagementResourceIT {

    private static final Long DEFAULT_QUALITYID = 1L;
    private static final Long UPDATED_QUALITYID = 2L;

    private static final LocalDate DEFAULT_CREATETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATETIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATORNAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATORNAME = "BBBBBBBBBB";

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/qualitymanagements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QualitymanagementRepository qualitymanagementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQualitymanagementMockMvc;

    private Qualitymanagement qualitymanagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Qualitymanagement createEntity(EntityManager em) {
        Qualitymanagement qualitymanagement = new Qualitymanagement()
            .qualityid(DEFAULT_QUALITYID)
            .createtime(DEFAULT_CREATETIME)
            .creatorname(DEFAULT_CREATORNAME)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return qualitymanagement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Qualitymanagement createUpdatedEntity(EntityManager em) {
        Qualitymanagement qualitymanagement = new Qualitymanagement()
            .qualityid(UPDATED_QUALITYID)
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return qualitymanagement;
    }

    @BeforeEach
    public void initTest() {
        qualitymanagement = createEntity(em);
    }

    @Test
    @Transactional
    void createQualitymanagement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Qualitymanagement
        var returnedQualitymanagement = om.readValue(
            restQualitymanagementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualitymanagement)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Qualitymanagement.class
        );

        // Validate the Qualitymanagement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertQualitymanagementUpdatableFieldsEquals(returnedQualitymanagement, getPersistedQualitymanagement(returnedQualitymanagement));
    }

    @Test
    @Transactional
    void createQualitymanagementWithExistingId() throws Exception {
        // Create the Qualitymanagement with an existing ID
        qualitymanagement.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQualitymanagementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualitymanagement)))
            .andExpect(status().isBadRequest());

        // Validate the Qualitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQualitymanagements() throws Exception {
        // Initialize the database
        qualitymanagementRepository.saveAndFlush(qualitymanagement);

        // Get all the qualitymanagementList
        restQualitymanagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qualitymanagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].qualityid").value(hasItem(DEFAULT_QUALITYID.intValue())))
            .andExpect(jsonPath("$.[*].createtime").value(hasItem(DEFAULT_CREATETIME.toString())))
            .andExpect(jsonPath("$.[*].creatorname").value(hasItem(DEFAULT_CREATORNAME)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getQualitymanagement() throws Exception {
        // Initialize the database
        qualitymanagementRepository.saveAndFlush(qualitymanagement);

        // Get the qualitymanagement
        restQualitymanagementMockMvc
            .perform(get(ENTITY_API_URL_ID, qualitymanagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(qualitymanagement.getId().intValue()))
            .andExpect(jsonPath("$.qualityid").value(DEFAULT_QUALITYID.intValue()))
            .andExpect(jsonPath("$.createtime").value(DEFAULT_CREATETIME.toString()))
            .andExpect(jsonPath("$.creatorname").value(DEFAULT_CREATORNAME))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingQualitymanagement() throws Exception {
        // Get the qualitymanagement
        restQualitymanagementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQualitymanagement() throws Exception {
        // Initialize the database
        qualitymanagementRepository.saveAndFlush(qualitymanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualitymanagement
        Qualitymanagement updatedQualitymanagement = qualitymanagementRepository.findById(qualitymanagement.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedQualitymanagement are not directly saved in db
        em.detach(updatedQualitymanagement);
        updatedQualitymanagement
            .qualityid(UPDATED_QUALITYID)
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restQualitymanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedQualitymanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedQualitymanagement))
            )
            .andExpect(status().isOk());

        // Validate the Qualitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQualitymanagementToMatchAllProperties(updatedQualitymanagement);
    }

    @Test
    @Transactional
    void putNonExistingQualitymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitymanagement.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualitymanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, qualitymanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualitymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQualitymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitymanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualitymanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualitymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQualitymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitymanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualitymanagementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualitymanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Qualitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQualitymanagementWithPatch() throws Exception {
        // Initialize the database
        qualitymanagementRepository.saveAndFlush(qualitymanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualitymanagement using partial update
        Qualitymanagement partialUpdatedQualitymanagement = new Qualitymanagement();
        partialUpdatedQualitymanagement.setId(qualitymanagement.getId());

        partialUpdatedQualitymanagement
            .qualityid(UPDATED_QUALITYID)
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .secretlevel(UPDATED_SECRETLEVEL);

        restQualitymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualitymanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualitymanagement))
            )
            .andExpect(status().isOk());

        // Validate the Qualitymanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualitymanagementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedQualitymanagement, qualitymanagement),
            getPersistedQualitymanagement(qualitymanagement)
        );
    }

    @Test
    @Transactional
    void fullUpdateQualitymanagementWithPatch() throws Exception {
        // Initialize the database
        qualitymanagementRepository.saveAndFlush(qualitymanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualitymanagement using partial update
        Qualitymanagement partialUpdatedQualitymanagement = new Qualitymanagement();
        partialUpdatedQualitymanagement.setId(qualitymanagement.getId());

        partialUpdatedQualitymanagement
            .qualityid(UPDATED_QUALITYID)
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restQualitymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualitymanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualitymanagement))
            )
            .andExpect(status().isOk());

        // Validate the Qualitymanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualitymanagementUpdatableFieldsEquals(
            partialUpdatedQualitymanagement,
            getPersistedQualitymanagement(partialUpdatedQualitymanagement)
        );
    }

    @Test
    @Transactional
    void patchNonExistingQualitymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitymanagement.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualitymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, qualitymanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualitymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQualitymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitymanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualitymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualitymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQualitymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualitymanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualitymanagementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(qualitymanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Qualitymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQualitymanagement() throws Exception {
        // Initialize the database
        qualitymanagementRepository.saveAndFlush(qualitymanagement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the qualitymanagement
        restQualitymanagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, qualitymanagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return qualitymanagementRepository.count();
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

    protected Qualitymanagement getPersistedQualitymanagement(Qualitymanagement qualitymanagement) {
        return qualitymanagementRepository.findById(qualitymanagement.getId()).orElseThrow();
    }

    protected void assertPersistedQualitymanagementToMatchAllProperties(Qualitymanagement expectedQualitymanagement) {
        assertQualitymanagementAllPropertiesEquals(expectedQualitymanagement, getPersistedQualitymanagement(expectedQualitymanagement));
    }

    protected void assertPersistedQualitymanagementToMatchUpdatableProperties(Qualitymanagement expectedQualitymanagement) {
        assertQualitymanagementAllUpdatablePropertiesEquals(
            expectedQualitymanagement,
            getPersistedQualitymanagement(expectedQualitymanagement)
        );
    }
}

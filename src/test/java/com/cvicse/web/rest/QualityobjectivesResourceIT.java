package com.cvicse.web.rest;

import static com.cvicse.domain.QualityobjectivesAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Qualityobjectives;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.QualityobjectivesRepository;
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
 * Integration tests for the {@link QualityobjectivesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class QualityobjectivesResourceIT {

    private static final Long DEFAULT_QUALITYOBJECTIVESID = 1L;
    private static final Long UPDATED_QUALITYOBJECTIVESID = 2L;

    private static final String DEFAULT_QUALITYOBJECTIVESNAME = "AAAAAAAAAA";
    private static final String UPDATED_QUALITYOBJECTIVESNAME = "BBBBBBBBBB";

    private static final Long DEFAULT_YEAR = 1L;
    private static final Long UPDATED_YEAR = 2L;

    private static final LocalDate DEFAULT_CREATETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATETIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATORNAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATORNAME = "BBBBBBBBBB";

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/qualityobjectives";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private QualityobjectivesRepository qualityobjectivesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQualityobjectivesMockMvc;

    private Qualityobjectives qualityobjectives;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Qualityobjectives createEntity(EntityManager em) {
        Qualityobjectives qualityobjectives = new Qualityobjectives()
            .qualityobjectivesid(DEFAULT_QUALITYOBJECTIVESID)
            .qualityobjectivesname(DEFAULT_QUALITYOBJECTIVESNAME)
            .year(DEFAULT_YEAR)
            .createtime(DEFAULT_CREATETIME)
            .creatorname(DEFAULT_CREATORNAME)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return qualityobjectives;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Qualityobjectives createUpdatedEntity(EntityManager em) {
        Qualityobjectives qualityobjectives = new Qualityobjectives()
            .qualityobjectivesid(UPDATED_QUALITYOBJECTIVESID)
            .qualityobjectivesname(UPDATED_QUALITYOBJECTIVESNAME)
            .year(UPDATED_YEAR)
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return qualityobjectives;
    }

    @BeforeEach
    public void initTest() {
        qualityobjectives = createEntity(em);
    }

    @Test
    @Transactional
    void createQualityobjectives() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Qualityobjectives
        var returnedQualityobjectives = om.readValue(
            restQualityobjectivesMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityobjectives)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Qualityobjectives.class
        );

        // Validate the Qualityobjectives in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertQualityobjectivesUpdatableFieldsEquals(returnedQualityobjectives, getPersistedQualityobjectives(returnedQualityobjectives));
    }

    @Test
    @Transactional
    void createQualityobjectivesWithExistingId() throws Exception {
        // Create the Qualityobjectives with an existing ID
        qualityobjectives.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restQualityobjectivesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityobjectives)))
            .andExpect(status().isBadRequest());

        // Validate the Qualityobjectives in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllQualityobjectives() throws Exception {
        // Initialize the database
        qualityobjectivesRepository.saveAndFlush(qualityobjectives);

        // Get all the qualityobjectivesList
        restQualityobjectivesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(qualityobjectives.getId().intValue())))
            .andExpect(jsonPath("$.[*].qualityobjectivesid").value(hasItem(DEFAULT_QUALITYOBJECTIVESID.intValue())))
            .andExpect(jsonPath("$.[*].qualityobjectivesname").value(hasItem(DEFAULT_QUALITYOBJECTIVESNAME)))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR.intValue())))
            .andExpect(jsonPath("$.[*].createtime").value(hasItem(DEFAULT_CREATETIME.toString())))
            .andExpect(jsonPath("$.[*].creatorname").value(hasItem(DEFAULT_CREATORNAME)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getQualityobjectives() throws Exception {
        // Initialize the database
        qualityobjectivesRepository.saveAndFlush(qualityobjectives);

        // Get the qualityobjectives
        restQualityobjectivesMockMvc
            .perform(get(ENTITY_API_URL_ID, qualityobjectives.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(qualityobjectives.getId().intValue()))
            .andExpect(jsonPath("$.qualityobjectivesid").value(DEFAULT_QUALITYOBJECTIVESID.intValue()))
            .andExpect(jsonPath("$.qualityobjectivesname").value(DEFAULT_QUALITYOBJECTIVESNAME))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR.intValue()))
            .andExpect(jsonPath("$.createtime").value(DEFAULT_CREATETIME.toString()))
            .andExpect(jsonPath("$.creatorname").value(DEFAULT_CREATORNAME))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingQualityobjectives() throws Exception {
        // Get the qualityobjectives
        restQualityobjectivesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingQualityobjectives() throws Exception {
        // Initialize the database
        qualityobjectivesRepository.saveAndFlush(qualityobjectives);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityobjectives
        Qualityobjectives updatedQualityobjectives = qualityobjectivesRepository.findById(qualityobjectives.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedQualityobjectives are not directly saved in db
        em.detach(updatedQualityobjectives);
        updatedQualityobjectives
            .qualityobjectivesid(UPDATED_QUALITYOBJECTIVESID)
            .qualityobjectivesname(UPDATED_QUALITYOBJECTIVESNAME)
            .year(UPDATED_YEAR)
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restQualityobjectivesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedQualityobjectives.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedQualityobjectives))
            )
            .andExpect(status().isOk());

        // Validate the Qualityobjectives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedQualityobjectivesToMatchAllProperties(updatedQualityobjectives);
    }

    @Test
    @Transactional
    void putNonExistingQualityobjectives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityobjectives.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualityobjectivesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, qualityobjectives.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualityobjectives))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualityobjectives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchQualityobjectives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityobjectives.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityobjectivesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(qualityobjectives))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualityobjectives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamQualityobjectives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityobjectives.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityobjectivesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(qualityobjectives)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Qualityobjectives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateQualityobjectivesWithPatch() throws Exception {
        // Initialize the database
        qualityobjectivesRepository.saveAndFlush(qualityobjectives);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityobjectives using partial update
        Qualityobjectives partialUpdatedQualityobjectives = new Qualityobjectives();
        partialUpdatedQualityobjectives.setId(qualityobjectives.getId());

        partialUpdatedQualityobjectives
            .qualityobjectivesid(UPDATED_QUALITYOBJECTIVESID)
            .year(UPDATED_YEAR)
            .createtime(UPDATED_CREATETIME)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restQualityobjectivesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualityobjectives.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualityobjectives))
            )
            .andExpect(status().isOk());

        // Validate the Qualityobjectives in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualityobjectivesUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedQualityobjectives, qualityobjectives),
            getPersistedQualityobjectives(qualityobjectives)
        );
    }

    @Test
    @Transactional
    void fullUpdateQualityobjectivesWithPatch() throws Exception {
        // Initialize the database
        qualityobjectivesRepository.saveAndFlush(qualityobjectives);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the qualityobjectives using partial update
        Qualityobjectives partialUpdatedQualityobjectives = new Qualityobjectives();
        partialUpdatedQualityobjectives.setId(qualityobjectives.getId());

        partialUpdatedQualityobjectives
            .qualityobjectivesid(UPDATED_QUALITYOBJECTIVESID)
            .qualityobjectivesname(UPDATED_QUALITYOBJECTIVESNAME)
            .year(UPDATED_YEAR)
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restQualityobjectivesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedQualityobjectives.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedQualityobjectives))
            )
            .andExpect(status().isOk());

        // Validate the Qualityobjectives in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertQualityobjectivesUpdatableFieldsEquals(
            partialUpdatedQualityobjectives,
            getPersistedQualityobjectives(partialUpdatedQualityobjectives)
        );
    }

    @Test
    @Transactional
    void patchNonExistingQualityobjectives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityobjectives.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQualityobjectivesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, qualityobjectives.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualityobjectives))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualityobjectives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchQualityobjectives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityobjectives.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityobjectivesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(qualityobjectives))
            )
            .andExpect(status().isBadRequest());

        // Validate the Qualityobjectives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamQualityobjectives() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        qualityobjectives.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restQualityobjectivesMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(qualityobjectives)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Qualityobjectives in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteQualityobjectives() throws Exception {
        // Initialize the database
        qualityobjectivesRepository.saveAndFlush(qualityobjectives);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the qualityobjectives
        restQualityobjectivesMockMvc
            .perform(delete(ENTITY_API_URL_ID, qualityobjectives.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return qualityobjectivesRepository.count();
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

    protected Qualityobjectives getPersistedQualityobjectives(Qualityobjectives qualityobjectives) {
        return qualityobjectivesRepository.findById(qualityobjectives.getId()).orElseThrow();
    }

    protected void assertPersistedQualityobjectivesToMatchAllProperties(Qualityobjectives expectedQualityobjectives) {
        assertQualityobjectivesAllPropertiesEquals(expectedQualityobjectives, getPersistedQualityobjectives(expectedQualityobjectives));
    }

    protected void assertPersistedQualityobjectivesToMatchUpdatableProperties(Qualityobjectives expectedQualityobjectives) {
        assertQualityobjectivesAllUpdatablePropertiesEquals(
            expectedQualityobjectives,
            getPersistedQualityobjectives(expectedQualityobjectives)
        );
    }
}

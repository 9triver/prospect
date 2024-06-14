package com.cvicse.web.rest;

import static com.cvicse.domain.PbssubmanageAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Pbssubmanage;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.PbssubmanageRepository;
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
 * Integration tests for the {@link PbssubmanageResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PbssubmanageResourceIT {

    private static final String DEFAULT_PBSSUBID = "AAAAAAAAAA";
    private static final String UPDATED_PBSSUBID = "BBBBBBBBBB";

    private static final String DEFAULT_PBSSUBNAME = "AAAAAAAAAA";
    private static final String UPDATED_PBSSUBNAME = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSIBLENAME = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSIBLENAME = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSIBLEDEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSIBLEDEPARTMENT = "BBBBBBBBBB";

    private static final String DEFAULT_RELEVANTDEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_RELEVANTDEPARTMENT = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/pbssubmanages";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PbssubmanageRepository pbssubmanageRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPbssubmanageMockMvc;

    private Pbssubmanage pbssubmanage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pbssubmanage createEntity(EntityManager em) {
        Pbssubmanage pbssubmanage = new Pbssubmanage()
            .pbssubid(DEFAULT_PBSSUBID)
            .pbssubname(DEFAULT_PBSSUBNAME)
            .responsiblename(DEFAULT_RESPONSIBLENAME)
            .responsibledepartment(DEFAULT_RESPONSIBLEDEPARTMENT)
            .relevantdepartment(DEFAULT_RELEVANTDEPARTMENT)
            .type(DEFAULT_TYPE)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return pbssubmanage;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pbssubmanage createUpdatedEntity(EntityManager em) {
        Pbssubmanage pbssubmanage = new Pbssubmanage()
            .pbssubid(UPDATED_PBSSUBID)
            .pbssubname(UPDATED_PBSSUBNAME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .responsibledepartment(UPDATED_RESPONSIBLEDEPARTMENT)
            .relevantdepartment(UPDATED_RELEVANTDEPARTMENT)
            .type(UPDATED_TYPE)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return pbssubmanage;
    }

    @BeforeEach
    public void initTest() {
        pbssubmanage = createEntity(em);
    }

    @Test
    @Transactional
    void createPbssubmanage() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Pbssubmanage
        var returnedPbssubmanage = om.readValue(
            restPbssubmanageMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(pbssubmanage)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Pbssubmanage.class
        );

        // Validate the Pbssubmanage in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertPbssubmanageUpdatableFieldsEquals(returnedPbssubmanage, getPersistedPbssubmanage(returnedPbssubmanage));
    }

    @Test
    @Transactional
    void createPbssubmanageWithExistingId() throws Exception {
        // Create the Pbssubmanage with an existing ID
        pbssubmanage.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPbssubmanageMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(pbssubmanage)))
            .andExpect(status().isBadRequest());

        // Validate the Pbssubmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPbssubmanages() throws Exception {
        // Initialize the database
        pbssubmanageRepository.saveAndFlush(pbssubmanage);

        // Get all the pbssubmanageList
        restPbssubmanageMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pbssubmanage.getId().intValue())))
            .andExpect(jsonPath("$.[*].pbssubid").value(hasItem(DEFAULT_PBSSUBID)))
            .andExpect(jsonPath("$.[*].pbssubname").value(hasItem(DEFAULT_PBSSUBNAME)))
            .andExpect(jsonPath("$.[*].responsiblename").value(hasItem(DEFAULT_RESPONSIBLENAME)))
            .andExpect(jsonPath("$.[*].responsibledepartment").value(hasItem(DEFAULT_RESPONSIBLEDEPARTMENT)))
            .andExpect(jsonPath("$.[*].relevantdepartment").value(hasItem(DEFAULT_RELEVANTDEPARTMENT)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getPbssubmanage() throws Exception {
        // Initialize the database
        pbssubmanageRepository.saveAndFlush(pbssubmanage);

        // Get the pbssubmanage
        restPbssubmanageMockMvc
            .perform(get(ENTITY_API_URL_ID, pbssubmanage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pbssubmanage.getId().intValue()))
            .andExpect(jsonPath("$.pbssubid").value(DEFAULT_PBSSUBID))
            .andExpect(jsonPath("$.pbssubname").value(DEFAULT_PBSSUBNAME))
            .andExpect(jsonPath("$.responsiblename").value(DEFAULT_RESPONSIBLENAME))
            .andExpect(jsonPath("$.responsibledepartment").value(DEFAULT_RESPONSIBLEDEPARTMENT))
            .andExpect(jsonPath("$.relevantdepartment").value(DEFAULT_RELEVANTDEPARTMENT))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingPbssubmanage() throws Exception {
        // Get the pbssubmanage
        restPbssubmanageMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPbssubmanage() throws Exception {
        // Initialize the database
        pbssubmanageRepository.saveAndFlush(pbssubmanage);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the pbssubmanage
        Pbssubmanage updatedPbssubmanage = pbssubmanageRepository.findById(pbssubmanage.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPbssubmanage are not directly saved in db
        em.detach(updatedPbssubmanage);
        updatedPbssubmanage
            .pbssubid(UPDATED_PBSSUBID)
            .pbssubname(UPDATED_PBSSUBNAME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .responsibledepartment(UPDATED_RESPONSIBLEDEPARTMENT)
            .relevantdepartment(UPDATED_RELEVANTDEPARTMENT)
            .type(UPDATED_TYPE)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restPbssubmanageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPbssubmanage.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedPbssubmanage))
            )
            .andExpect(status().isOk());

        // Validate the Pbssubmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPbssubmanageToMatchAllProperties(updatedPbssubmanage);
    }

    @Test
    @Transactional
    void putNonExistingPbssubmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pbssubmanage.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPbssubmanageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pbssubmanage.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(pbssubmanage))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pbssubmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPbssubmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pbssubmanage.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPbssubmanageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(pbssubmanage))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pbssubmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPbssubmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pbssubmanage.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPbssubmanageMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(pbssubmanage)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Pbssubmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePbssubmanageWithPatch() throws Exception {
        // Initialize the database
        pbssubmanageRepository.saveAndFlush(pbssubmanage);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the pbssubmanage using partial update
        Pbssubmanage partialUpdatedPbssubmanage = new Pbssubmanage();
        partialUpdatedPbssubmanage.setId(pbssubmanage.getId());

        partialUpdatedPbssubmanage
            .pbssubname(UPDATED_PBSSUBNAME)
            .responsibledepartment(UPDATED_RESPONSIBLEDEPARTMENT)
            .secretlevel(UPDATED_SECRETLEVEL);

        restPbssubmanageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPbssubmanage.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPbssubmanage))
            )
            .andExpect(status().isOk());

        // Validate the Pbssubmanage in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPbssubmanageUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPbssubmanage, pbssubmanage),
            getPersistedPbssubmanage(pbssubmanage)
        );
    }

    @Test
    @Transactional
    void fullUpdatePbssubmanageWithPatch() throws Exception {
        // Initialize the database
        pbssubmanageRepository.saveAndFlush(pbssubmanage);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the pbssubmanage using partial update
        Pbssubmanage partialUpdatedPbssubmanage = new Pbssubmanage();
        partialUpdatedPbssubmanage.setId(pbssubmanage.getId());

        partialUpdatedPbssubmanage
            .pbssubid(UPDATED_PBSSUBID)
            .pbssubname(UPDATED_PBSSUBNAME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .responsibledepartment(UPDATED_RESPONSIBLEDEPARTMENT)
            .relevantdepartment(UPDATED_RELEVANTDEPARTMENT)
            .type(UPDATED_TYPE)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restPbssubmanageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPbssubmanage.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPbssubmanage))
            )
            .andExpect(status().isOk());

        // Validate the Pbssubmanage in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPbssubmanageUpdatableFieldsEquals(partialUpdatedPbssubmanage, getPersistedPbssubmanage(partialUpdatedPbssubmanage));
    }

    @Test
    @Transactional
    void patchNonExistingPbssubmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pbssubmanage.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPbssubmanageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pbssubmanage.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(pbssubmanage))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pbssubmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPbssubmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pbssubmanage.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPbssubmanageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(pbssubmanage))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pbssubmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPbssubmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pbssubmanage.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPbssubmanageMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(pbssubmanage)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Pbssubmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePbssubmanage() throws Exception {
        // Initialize the database
        pbssubmanageRepository.saveAndFlush(pbssubmanage);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the pbssubmanage
        restPbssubmanageMockMvc
            .perform(delete(ENTITY_API_URL_ID, pbssubmanage.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return pbssubmanageRepository.count();
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

    protected Pbssubmanage getPersistedPbssubmanage(Pbssubmanage pbssubmanage) {
        return pbssubmanageRepository.findById(pbssubmanage.getId()).orElseThrow();
    }

    protected void assertPersistedPbssubmanageToMatchAllProperties(Pbssubmanage expectedPbssubmanage) {
        assertPbssubmanageAllPropertiesEquals(expectedPbssubmanage, getPersistedPbssubmanage(expectedPbssubmanage));
    }

    protected void assertPersistedPbssubmanageToMatchUpdatableProperties(Pbssubmanage expectedPbssubmanage) {
        assertPbssubmanageAllUpdatablePropertiesEquals(expectedPbssubmanage, getPersistedPbssubmanage(expectedPbssubmanage));
    }
}

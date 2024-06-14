package com.cvicse.web.rest;

import static com.cvicse.domain.WbssubmanageAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Wbssubmanage;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.WbssubmanageRepository;
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
 * Integration tests for the {@link WbssubmanageResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class WbssubmanageResourceIT {

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

    private static final String ENTITY_API_URL = "/api/wbssubmanages";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private WbssubmanageRepository wbssubmanageRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWbssubmanageMockMvc;

    private Wbssubmanage wbssubmanage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Wbssubmanage createEntity(EntityManager em) {
        Wbssubmanage wbssubmanage = new Wbssubmanage()
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
        return wbssubmanage;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Wbssubmanage createUpdatedEntity(EntityManager em) {
        Wbssubmanage wbssubmanage = new Wbssubmanage()
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
        return wbssubmanage;
    }

    @BeforeEach
    public void initTest() {
        wbssubmanage = createEntity(em);
    }

    @Test
    @Transactional
    void createWbssubmanage() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Wbssubmanage
        var returnedWbssubmanage = om.readValue(
            restWbssubmanageMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(wbssubmanage)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Wbssubmanage.class
        );

        // Validate the Wbssubmanage in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertWbssubmanageUpdatableFieldsEquals(returnedWbssubmanage, getPersistedWbssubmanage(returnedWbssubmanage));
    }

    @Test
    @Transactional
    void createWbssubmanageWithExistingId() throws Exception {
        // Create the Wbssubmanage with an existing ID
        wbssubmanage.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restWbssubmanageMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(wbssubmanage)))
            .andExpect(status().isBadRequest());

        // Validate the Wbssubmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllWbssubmanages() throws Exception {
        // Initialize the database
        wbssubmanageRepository.saveAndFlush(wbssubmanage);

        // Get all the wbssubmanageList
        restWbssubmanageMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wbssubmanage.getId().intValue())))
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
    void getWbssubmanage() throws Exception {
        // Initialize the database
        wbssubmanageRepository.saveAndFlush(wbssubmanage);

        // Get the wbssubmanage
        restWbssubmanageMockMvc
            .perform(get(ENTITY_API_URL_ID, wbssubmanage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(wbssubmanage.getId().intValue()))
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
    void getNonExistingWbssubmanage() throws Exception {
        // Get the wbssubmanage
        restWbssubmanageMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingWbssubmanage() throws Exception {
        // Initialize the database
        wbssubmanageRepository.saveAndFlush(wbssubmanage);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the wbssubmanage
        Wbssubmanage updatedWbssubmanage = wbssubmanageRepository.findById(wbssubmanage.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedWbssubmanage are not directly saved in db
        em.detach(updatedWbssubmanage);
        updatedWbssubmanage
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

        restWbssubmanageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedWbssubmanage.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedWbssubmanage))
            )
            .andExpect(status().isOk());

        // Validate the Wbssubmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedWbssubmanageToMatchAllProperties(updatedWbssubmanage);
    }

    @Test
    @Transactional
    void putNonExistingWbssubmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wbssubmanage.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWbssubmanageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, wbssubmanage.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(wbssubmanage))
            )
            .andExpect(status().isBadRequest());

        // Validate the Wbssubmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchWbssubmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wbssubmanage.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWbssubmanageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(wbssubmanage))
            )
            .andExpect(status().isBadRequest());

        // Validate the Wbssubmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamWbssubmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wbssubmanage.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWbssubmanageMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(wbssubmanage)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Wbssubmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateWbssubmanageWithPatch() throws Exception {
        // Initialize the database
        wbssubmanageRepository.saveAndFlush(wbssubmanage);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the wbssubmanage using partial update
        Wbssubmanage partialUpdatedWbssubmanage = new Wbssubmanage();
        partialUpdatedWbssubmanage.setId(wbssubmanage.getId());

        partialUpdatedWbssubmanage
            .pbssubid(UPDATED_PBSSUBID)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .type(UPDATED_TYPE)
            .starttime(UPDATED_STARTTIME)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restWbssubmanageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWbssubmanage.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedWbssubmanage))
            )
            .andExpect(status().isOk());

        // Validate the Wbssubmanage in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertWbssubmanageUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedWbssubmanage, wbssubmanage),
            getPersistedWbssubmanage(wbssubmanage)
        );
    }

    @Test
    @Transactional
    void fullUpdateWbssubmanageWithPatch() throws Exception {
        // Initialize the database
        wbssubmanageRepository.saveAndFlush(wbssubmanage);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the wbssubmanage using partial update
        Wbssubmanage partialUpdatedWbssubmanage = new Wbssubmanage();
        partialUpdatedWbssubmanage.setId(wbssubmanage.getId());

        partialUpdatedWbssubmanage
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

        restWbssubmanageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedWbssubmanage.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedWbssubmanage))
            )
            .andExpect(status().isOk());

        // Validate the Wbssubmanage in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertWbssubmanageUpdatableFieldsEquals(partialUpdatedWbssubmanage, getPersistedWbssubmanage(partialUpdatedWbssubmanage));
    }

    @Test
    @Transactional
    void patchNonExistingWbssubmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wbssubmanage.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWbssubmanageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, wbssubmanage.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(wbssubmanage))
            )
            .andExpect(status().isBadRequest());

        // Validate the Wbssubmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchWbssubmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wbssubmanage.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWbssubmanageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(wbssubmanage))
            )
            .andExpect(status().isBadRequest());

        // Validate the Wbssubmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamWbssubmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        wbssubmanage.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restWbssubmanageMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(wbssubmanage)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Wbssubmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteWbssubmanage() throws Exception {
        // Initialize the database
        wbssubmanageRepository.saveAndFlush(wbssubmanage);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the wbssubmanage
        restWbssubmanageMockMvc
            .perform(delete(ENTITY_API_URL_ID, wbssubmanage.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return wbssubmanageRepository.count();
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

    protected Wbssubmanage getPersistedWbssubmanage(Wbssubmanage wbssubmanage) {
        return wbssubmanageRepository.findById(wbssubmanage.getId()).orElseThrow();
    }

    protected void assertPersistedWbssubmanageToMatchAllProperties(Wbssubmanage expectedWbssubmanage) {
        assertWbssubmanageAllPropertiesEquals(expectedWbssubmanage, getPersistedWbssubmanage(expectedWbssubmanage));
    }

    protected void assertPersistedWbssubmanageToMatchUpdatableProperties(Wbssubmanage expectedWbssubmanage) {
        assertWbssubmanageAllUpdatablePropertiesEquals(expectedWbssubmanage, getPersistedWbssubmanage(expectedWbssubmanage));
    }
}

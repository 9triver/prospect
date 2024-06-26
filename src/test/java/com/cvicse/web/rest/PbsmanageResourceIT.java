package com.cvicse.web.rest;

import static com.cvicse.domain.PbsmanageAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Pbsmanage;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.PbsmanageRepository;
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
 * Integration tests for the {@link PbsmanageResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PbsmanageResourceIT {

    private static final String DEFAULT_PBSNAME = "AAAAAAAAAA";
    private static final String UPDATED_PBSNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ADMINISTRATORID = "AAAAAAAAAA";
    private static final String UPDATED_ADMINISTRATORID = "BBBBBBBBBB";

    private static final String DEFAULT_ADMINISTRATORNAME = "AAAAAAAAAA";
    private static final String UPDATED_ADMINISTRATORNAME = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSIBLENAME = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSIBLENAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENT = "BBBBBBBBBB";

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String DEFAULT_AUDIT_USERID = "AAAAAAAAAA";
    private static final String UPDATED_AUDIT_USERID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/pbsmanages";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private PbsmanageRepository pbsmanageRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPbsmanageMockMvc;

    private Pbsmanage pbsmanage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pbsmanage createEntity(EntityManager em) {
        Pbsmanage pbsmanage = new Pbsmanage()
            .pbsname(DEFAULT_PBSNAME)
            .number(DEFAULT_NUMBER)
            .type(DEFAULT_TYPE)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME)
            .administratorid(DEFAULT_ADMINISTRATORID)
            .administratorname(DEFAULT_ADMINISTRATORNAME)
            .responsiblename(DEFAULT_RESPONSIBLENAME)
            .department(DEFAULT_DEPARTMENT)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .auditStatus(DEFAULT_AUDIT_STATUS)
            .auditUserid(DEFAULT_AUDIT_USERID);
        return pbsmanage;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Pbsmanage createUpdatedEntity(EntityManager em) {
        Pbsmanage pbsmanage = new Pbsmanage()
            .pbsname(UPDATED_PBSNAME)
            .number(UPDATED_NUMBER)
            .type(UPDATED_TYPE)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .administratorid(UPDATED_ADMINISTRATORID)
            .administratorname(UPDATED_ADMINISTRATORNAME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .department(UPDATED_DEPARTMENT)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .auditUserid(UPDATED_AUDIT_USERID);
        return pbsmanage;
    }

    @BeforeEach
    public void initTest() {
        pbsmanage = createEntity(em);
    }

    @Test
    @Transactional
    void createPbsmanage() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Pbsmanage
        var returnedPbsmanage = om.readValue(
            restPbsmanageMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(pbsmanage)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Pbsmanage.class
        );

        // Validate the Pbsmanage in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertPbsmanageUpdatableFieldsEquals(returnedPbsmanage, getPersistedPbsmanage(returnedPbsmanage));
    }

    @Test
    @Transactional
    void createPbsmanageWithExistingId() throws Exception {
        // Create the Pbsmanage with an existing ID
        pbsmanage.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPbsmanageMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(pbsmanage)))
            .andExpect(status().isBadRequest());

        // Validate the Pbsmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPbsmanages() throws Exception {
        // Initialize the database
        pbsmanageRepository.saveAndFlush(pbsmanage);

        // Get all the pbsmanageList
        restPbsmanageMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pbsmanage.getId())))
            .andExpect(jsonPath("$.[*].pbsname").value(hasItem(DEFAULT_PBSNAME)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())))
            .andExpect(jsonPath("$.[*].administratorid").value(hasItem(DEFAULT_ADMINISTRATORID)))
            .andExpect(jsonPath("$.[*].administratorname").value(hasItem(DEFAULT_ADMINISTRATORNAME)))
            .andExpect(jsonPath("$.[*].responsiblename").value(hasItem(DEFAULT_RESPONSIBLENAME)))
            .andExpect(jsonPath("$.[*].department").value(hasItem(DEFAULT_DEPARTMENT)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].auditUserid").value(hasItem(DEFAULT_AUDIT_USERID)));
    }

    @Test
    @Transactional
    void getPbsmanage() throws Exception {
        // Initialize the database
        pbsmanageRepository.saveAndFlush(pbsmanage);

        // Get the pbsmanage
        restPbsmanageMockMvc
            .perform(get(ENTITY_API_URL_ID, pbsmanage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(pbsmanage.getId()))
            .andExpect(jsonPath("$.pbsname").value(DEFAULT_PBSNAME))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()))
            .andExpect(jsonPath("$.administratorid").value(DEFAULT_ADMINISTRATORID))
            .andExpect(jsonPath("$.administratorname").value(DEFAULT_ADMINISTRATORNAME))
            .andExpect(jsonPath("$.responsiblename").value(DEFAULT_RESPONSIBLENAME))
            .andExpect(jsonPath("$.department").value(DEFAULT_DEPARTMENT))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()))
            .andExpect(jsonPath("$.auditUserid").value(DEFAULT_AUDIT_USERID));
    }

    @Test
    @Transactional
    void getNonExistingPbsmanage() throws Exception {
        // Get the pbsmanage
        restPbsmanageMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingPbsmanage() throws Exception {
        // Initialize the database
        pbsmanageRepository.saveAndFlush(pbsmanage);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the pbsmanage
        Pbsmanage updatedPbsmanage = pbsmanageRepository.findById(pbsmanage.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedPbsmanage are not directly saved in db
        em.detach(updatedPbsmanage);
        updatedPbsmanage
            .pbsname(UPDATED_PBSNAME)
            .number(UPDATED_NUMBER)
            .type(UPDATED_TYPE)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .administratorid(UPDATED_ADMINISTRATORID)
            .administratorname(UPDATED_ADMINISTRATORNAME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .department(UPDATED_DEPARTMENT)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .auditUserid(UPDATED_AUDIT_USERID);

        restPbsmanageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedPbsmanage.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedPbsmanage))
            )
            .andExpect(status().isOk());

        // Validate the Pbsmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedPbsmanageToMatchAllProperties(updatedPbsmanage);
    }

    @Test
    @Transactional
    void putNonExistingPbsmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pbsmanage.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPbsmanageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, pbsmanage.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(pbsmanage))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pbsmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPbsmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pbsmanage.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPbsmanageMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(pbsmanage))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pbsmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPbsmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pbsmanage.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPbsmanageMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(pbsmanage)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Pbsmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePbsmanageWithPatch() throws Exception {
        // Initialize the database
        pbsmanageRepository.saveAndFlush(pbsmanage);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the pbsmanage using partial update
        Pbsmanage partialUpdatedPbsmanage = new Pbsmanage();
        partialUpdatedPbsmanage.setId(pbsmanage.getId());

        partialUpdatedPbsmanage
            .pbsname(UPDATED_PBSNAME)
            .number(UPDATED_NUMBER)
            .starttime(UPDATED_STARTTIME)
            .administratorid(UPDATED_ADMINISTRATORID)
            .department(UPDATED_DEPARTMENT)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .auditUserid(UPDATED_AUDIT_USERID);

        restPbsmanageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPbsmanage.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPbsmanage))
            )
            .andExpect(status().isOk());

        // Validate the Pbsmanage in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPbsmanageUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedPbsmanage, pbsmanage),
            getPersistedPbsmanage(pbsmanage)
        );
    }

    @Test
    @Transactional
    void fullUpdatePbsmanageWithPatch() throws Exception {
        // Initialize the database
        pbsmanageRepository.saveAndFlush(pbsmanage);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the pbsmanage using partial update
        Pbsmanage partialUpdatedPbsmanage = new Pbsmanage();
        partialUpdatedPbsmanage.setId(pbsmanage.getId());

        partialUpdatedPbsmanage
            .pbsname(UPDATED_PBSNAME)
            .number(UPDATED_NUMBER)
            .type(UPDATED_TYPE)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .administratorid(UPDATED_ADMINISTRATORID)
            .administratorname(UPDATED_ADMINISTRATORNAME)
            .responsiblename(UPDATED_RESPONSIBLENAME)
            .department(UPDATED_DEPARTMENT)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .auditUserid(UPDATED_AUDIT_USERID);

        restPbsmanageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPbsmanage.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedPbsmanage))
            )
            .andExpect(status().isOk());

        // Validate the Pbsmanage in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPbsmanageUpdatableFieldsEquals(partialUpdatedPbsmanage, getPersistedPbsmanage(partialUpdatedPbsmanage));
    }

    @Test
    @Transactional
    void patchNonExistingPbsmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pbsmanage.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPbsmanageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, pbsmanage.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(pbsmanage))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pbsmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPbsmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pbsmanage.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPbsmanageMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(pbsmanage))
            )
            .andExpect(status().isBadRequest());

        // Validate the Pbsmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPbsmanage() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        pbsmanage.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPbsmanageMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(pbsmanage)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Pbsmanage in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePbsmanage() throws Exception {
        // Initialize the database
        pbsmanageRepository.saveAndFlush(pbsmanage);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the pbsmanage
        restPbsmanageMockMvc
            .perform(delete(ENTITY_API_URL_ID, pbsmanage.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return pbsmanageRepository.count();
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

    protected Pbsmanage getPersistedPbsmanage(Pbsmanage pbsmanage) {
        return pbsmanageRepository.findById(pbsmanage.getId()).orElseThrow();
    }

    protected void assertPersistedPbsmanageToMatchAllProperties(Pbsmanage expectedPbsmanage) {
        assertPbsmanageAllPropertiesEquals(expectedPbsmanage, getPersistedPbsmanage(expectedPbsmanage));
    }

    protected void assertPersistedPbsmanageToMatchUpdatableProperties(Pbsmanage expectedPbsmanage) {
        assertPbsmanageAllUpdatablePropertiesEquals(expectedPbsmanage, getPersistedPbsmanage(expectedPbsmanage));
    }
}

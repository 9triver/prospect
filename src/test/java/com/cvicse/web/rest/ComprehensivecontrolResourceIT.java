package com.cvicse.web.rest;

import static com.cvicse.domain.ComprehensivecontrolAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Comprehensivecontrol;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.ProjectStatus;
import com.cvicse.repository.ComprehensivecontrolRepository;
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
 * Integration tests for the {@link ComprehensivecontrolResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ComprehensivecontrolResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMBER = 1;
    private static final Integer UPDATED_NUMBER = 2;

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ACTUALSTARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACTUALSTARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ACTUALENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ACTUALENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RESULT = "AAAAAAAAAA";
    private static final String UPDATED_RESULT = "BBBBBBBBBB";

    private static final String DEFAULT_ACCEPTANCETYPE = "AAAAAAAAAA";
    private static final String UPDATED_ACCEPTANCETYPE = "BBBBBBBBBB";

    private static final ProjectStatus DEFAULT_STATUS = ProjectStatus.NOTSTART;
    private static final ProjectStatus UPDATED_STATUS = ProjectStatus.IN_PROGRESS;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String DEFAULT_RESPONSIBLENAME = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSIBLENAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/comprehensivecontrols";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ComprehensivecontrolRepository comprehensivecontrolRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restComprehensivecontrolMockMvc;

    private Comprehensivecontrol comprehensivecontrol;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Comprehensivecontrol createEntity(EntityManager em) {
        Comprehensivecontrol comprehensivecontrol = new Comprehensivecontrol()
            .description(DEFAULT_DESCRIPTION)
            .number(DEFAULT_NUMBER)
            .type(DEFAULT_TYPE)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME)
            .actualstarttime(DEFAULT_ACTUALSTARTTIME)
            .actualendtime(DEFAULT_ACTUALENDTIME)
            .result(DEFAULT_RESULT)
            .acceptancetype(DEFAULT_ACCEPTANCETYPE)
            .status(DEFAULT_STATUS)
            .auditStatus(DEFAULT_AUDIT_STATUS)
            .responsiblename(DEFAULT_RESPONSIBLENAME);
        return comprehensivecontrol;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Comprehensivecontrol createUpdatedEntity(EntityManager em) {
        Comprehensivecontrol comprehensivecontrol = new Comprehensivecontrol()
            .description(UPDATED_DESCRIPTION)
            .number(UPDATED_NUMBER)
            .type(UPDATED_TYPE)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .actualstarttime(UPDATED_ACTUALSTARTTIME)
            .actualendtime(UPDATED_ACTUALENDTIME)
            .result(UPDATED_RESULT)
            .acceptancetype(UPDATED_ACCEPTANCETYPE)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .responsiblename(UPDATED_RESPONSIBLENAME);
        return comprehensivecontrol;
    }

    @BeforeEach
    public void initTest() {
        comprehensivecontrol = createEntity(em);
    }

    @Test
    @Transactional
    void createComprehensivecontrol() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Comprehensivecontrol
        var returnedComprehensivecontrol = om.readValue(
            restComprehensivecontrolMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(comprehensivecontrol)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Comprehensivecontrol.class
        );

        // Validate the Comprehensivecontrol in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertComprehensivecontrolUpdatableFieldsEquals(
            returnedComprehensivecontrol,
            getPersistedComprehensivecontrol(returnedComprehensivecontrol)
        );
    }

    @Test
    @Transactional
    void createComprehensivecontrolWithExistingId() throws Exception {
        // Create the Comprehensivecontrol with an existing ID
        comprehensivecontrol.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restComprehensivecontrolMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(comprehensivecontrol)))
            .andExpect(status().isBadRequest());

        // Validate the Comprehensivecontrol in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllComprehensivecontrols() throws Exception {
        // Initialize the database
        comprehensivecontrolRepository.saveAndFlush(comprehensivecontrol);

        // Get all the comprehensivecontrolList
        restComprehensivecontrolMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(comprehensivecontrol.getId())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].number").value(hasItem(DEFAULT_NUMBER)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())))
            .andExpect(jsonPath("$.[*].actualstarttime").value(hasItem(DEFAULT_ACTUALSTARTTIME.toString())))
            .andExpect(jsonPath("$.[*].actualendtime").value(hasItem(DEFAULT_ACTUALENDTIME.toString())))
            .andExpect(jsonPath("$.[*].result").value(hasItem(DEFAULT_RESULT)))
            .andExpect(jsonPath("$.[*].acceptancetype").value(hasItem(DEFAULT_ACCEPTANCETYPE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].responsiblename").value(hasItem(DEFAULT_RESPONSIBLENAME)));
    }

    @Test
    @Transactional
    void getComprehensivecontrol() throws Exception {
        // Initialize the database
        comprehensivecontrolRepository.saveAndFlush(comprehensivecontrol);

        // Get the comprehensivecontrol
        restComprehensivecontrolMockMvc
            .perform(get(ENTITY_API_URL_ID, comprehensivecontrol.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(comprehensivecontrol.getId()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.number").value(DEFAULT_NUMBER))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()))
            .andExpect(jsonPath("$.actualstarttime").value(DEFAULT_ACTUALSTARTTIME.toString()))
            .andExpect(jsonPath("$.actualendtime").value(DEFAULT_ACTUALENDTIME.toString()))
            .andExpect(jsonPath("$.result").value(DEFAULT_RESULT))
            .andExpect(jsonPath("$.acceptancetype").value(DEFAULT_ACCEPTANCETYPE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()))
            .andExpect(jsonPath("$.responsiblename").value(DEFAULT_RESPONSIBLENAME));
    }

    @Test
    @Transactional
    void getNonExistingComprehensivecontrol() throws Exception {
        // Get the comprehensivecontrol
        restComprehensivecontrolMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingComprehensivecontrol() throws Exception {
        // Initialize the database
        comprehensivecontrolRepository.saveAndFlush(comprehensivecontrol);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the comprehensivecontrol
        Comprehensivecontrol updatedComprehensivecontrol = comprehensivecontrolRepository
            .findById(comprehensivecontrol.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedComprehensivecontrol are not directly saved in db
        em.detach(updatedComprehensivecontrol);
        updatedComprehensivecontrol
            .description(UPDATED_DESCRIPTION)
            .number(UPDATED_NUMBER)
            .type(UPDATED_TYPE)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .actualstarttime(UPDATED_ACTUALSTARTTIME)
            .actualendtime(UPDATED_ACTUALENDTIME)
            .result(UPDATED_RESULT)
            .acceptancetype(UPDATED_ACCEPTANCETYPE)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .responsiblename(UPDATED_RESPONSIBLENAME);

        restComprehensivecontrolMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedComprehensivecontrol.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedComprehensivecontrol))
            )
            .andExpect(status().isOk());

        // Validate the Comprehensivecontrol in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedComprehensivecontrolToMatchAllProperties(updatedComprehensivecontrol);
    }

    @Test
    @Transactional
    void putNonExistingComprehensivecontrol() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        comprehensivecontrol.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComprehensivecontrolMockMvc
            .perform(
                put(ENTITY_API_URL_ID, comprehensivecontrol.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(comprehensivecontrol))
            )
            .andExpect(status().isBadRequest());

        // Validate the Comprehensivecontrol in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchComprehensivecontrol() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        comprehensivecontrol.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restComprehensivecontrolMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(comprehensivecontrol))
            )
            .andExpect(status().isBadRequest());

        // Validate the Comprehensivecontrol in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamComprehensivecontrol() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        comprehensivecontrol.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restComprehensivecontrolMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(comprehensivecontrol)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Comprehensivecontrol in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateComprehensivecontrolWithPatch() throws Exception {
        // Initialize the database
        comprehensivecontrolRepository.saveAndFlush(comprehensivecontrol);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the comprehensivecontrol using partial update
        Comprehensivecontrol partialUpdatedComprehensivecontrol = new Comprehensivecontrol();
        partialUpdatedComprehensivecontrol.setId(comprehensivecontrol.getId());

        partialUpdatedComprehensivecontrol
            .description(UPDATED_DESCRIPTION)
            .type(UPDATED_TYPE)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .actualstarttime(UPDATED_ACTUALSTARTTIME)
            .result(UPDATED_RESULT)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .responsiblename(UPDATED_RESPONSIBLENAME);

        restComprehensivecontrolMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedComprehensivecontrol.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedComprehensivecontrol))
            )
            .andExpect(status().isOk());

        // Validate the Comprehensivecontrol in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertComprehensivecontrolUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedComprehensivecontrol, comprehensivecontrol),
            getPersistedComprehensivecontrol(comprehensivecontrol)
        );
    }

    @Test
    @Transactional
    void fullUpdateComprehensivecontrolWithPatch() throws Exception {
        // Initialize the database
        comprehensivecontrolRepository.saveAndFlush(comprehensivecontrol);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the comprehensivecontrol using partial update
        Comprehensivecontrol partialUpdatedComprehensivecontrol = new Comprehensivecontrol();
        partialUpdatedComprehensivecontrol.setId(comprehensivecontrol.getId());

        partialUpdatedComprehensivecontrol
            .description(UPDATED_DESCRIPTION)
            .number(UPDATED_NUMBER)
            .type(UPDATED_TYPE)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .actualstarttime(UPDATED_ACTUALSTARTTIME)
            .actualendtime(UPDATED_ACTUALENDTIME)
            .result(UPDATED_RESULT)
            .acceptancetype(UPDATED_ACCEPTANCETYPE)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .responsiblename(UPDATED_RESPONSIBLENAME);

        restComprehensivecontrolMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedComprehensivecontrol.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedComprehensivecontrol))
            )
            .andExpect(status().isOk());

        // Validate the Comprehensivecontrol in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertComprehensivecontrolUpdatableFieldsEquals(
            partialUpdatedComprehensivecontrol,
            getPersistedComprehensivecontrol(partialUpdatedComprehensivecontrol)
        );
    }

    @Test
    @Transactional
    void patchNonExistingComprehensivecontrol() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        comprehensivecontrol.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restComprehensivecontrolMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, comprehensivecontrol.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(comprehensivecontrol))
            )
            .andExpect(status().isBadRequest());

        // Validate the Comprehensivecontrol in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchComprehensivecontrol() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        comprehensivecontrol.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restComprehensivecontrolMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(comprehensivecontrol))
            )
            .andExpect(status().isBadRequest());

        // Validate the Comprehensivecontrol in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamComprehensivecontrol() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        comprehensivecontrol.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restComprehensivecontrolMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(comprehensivecontrol)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Comprehensivecontrol in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteComprehensivecontrol() throws Exception {
        // Initialize the database
        comprehensivecontrolRepository.saveAndFlush(comprehensivecontrol);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the comprehensivecontrol
        restComprehensivecontrolMockMvc
            .perform(delete(ENTITY_API_URL_ID, comprehensivecontrol.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return comprehensivecontrolRepository.count();
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

    protected Comprehensivecontrol getPersistedComprehensivecontrol(Comprehensivecontrol comprehensivecontrol) {
        return comprehensivecontrolRepository.findById(comprehensivecontrol.getId()).orElseThrow();
    }

    protected void assertPersistedComprehensivecontrolToMatchAllProperties(Comprehensivecontrol expectedComprehensivecontrol) {
        assertComprehensivecontrolAllPropertiesEquals(
            expectedComprehensivecontrol,
            getPersistedComprehensivecontrol(expectedComprehensivecontrol)
        );
    }

    protected void assertPersistedComprehensivecontrolToMatchUpdatableProperties(Comprehensivecontrol expectedComprehensivecontrol) {
        assertComprehensivecontrolAllUpdatablePropertiesEquals(
            expectedComprehensivecontrol,
            getPersistedComprehensivecontrol(expectedComprehensivecontrol)
        );
    }
}

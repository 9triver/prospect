package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.ProjectpbsAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.Projectpbs;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.ProjectStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.repository.ProjectpbsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;
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
 * Integration tests for the {@link ProjectpbsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProjectpbsResourceIT {

    private static final String DEFAULT_PBSNAME = "AAAAAAAAAA";
    private static final String UPDATED_PBSNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PARENTPBSID = "AAAAAAAAAA";
    private static final String UPDATED_PARENTPBSID = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_STARTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_STARTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ENDTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ENDTIME = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_PROGRESS = 1;
    private static final Integer UPDATED_PROGRESS = 2;

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final Integer DEFAULT_PRIORTY = 1;
    private static final Integer UPDATED_PRIORTY = 2;

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final ProjectStatus DEFAULT_STATUS = ProjectStatus.NOTSTART;
    private static final ProjectStatus UPDATED_STATUS = ProjectStatus.IN_PROGRESS;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String DEFAULT_WBSID = "AAAAAAAAAA";
    private static final String UPDATED_WBSID = "BBBBBBBBBB";

    private static final Integer DEFAULT_WORKBAG = 1;
    private static final Integer UPDATED_WORKBAG = 2;

    private static final String ENTITY_API_URL = "/api/projectpbs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProjectpbsRepository projectpbsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectpbsMockMvc;

    private Projectpbs projectpbs;

    private Projectpbs insertedProjectpbs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Projectpbs createEntity(EntityManager em) {
        Projectpbs projectpbs = new Projectpbs()
            .pbsname(DEFAULT_PBSNAME)
            .parentpbsid(DEFAULT_PARENTPBSID)
            .description(DEFAULT_DESCRIPTION)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME)
            .progress(DEFAULT_PROGRESS)
            .type(DEFAULT_TYPE)
            .priorty(DEFAULT_PRIORTY)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .status(DEFAULT_STATUS)
            .auditStatus(DEFAULT_AUDIT_STATUS)
            .wbsid(DEFAULT_WBSID)
            .workbag(DEFAULT_WORKBAG);
        return projectpbs;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Projectpbs createUpdatedEntity(EntityManager em) {
        Projectpbs projectpbs = new Projectpbs()
            .pbsname(UPDATED_PBSNAME)
            .parentpbsid(UPDATED_PARENTPBSID)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .progress(UPDATED_PROGRESS)
            .type(UPDATED_TYPE)
            .priorty(UPDATED_PRIORTY)
            .secretlevel(UPDATED_SECRETLEVEL)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .wbsid(UPDATED_WBSID)
            .workbag(UPDATED_WORKBAG);
        return projectpbs;
    }

    @BeforeEach
    public void initTest() {
        projectpbs = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedProjectpbs != null) {
            projectpbsRepository.delete(insertedProjectpbs);
            insertedProjectpbs = null;
        }
    }

    @Test
    @Transactional
    void createProjectpbs() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Projectpbs
        var returnedProjectpbs = om.readValue(
            restProjectpbsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectpbs)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Projectpbs.class
        );

        // Validate the Projectpbs in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertProjectpbsUpdatableFieldsEquals(returnedProjectpbs, getPersistedProjectpbs(returnedProjectpbs));

        insertedProjectpbs = returnedProjectpbs;
    }

    @Test
    @Transactional
    void createProjectpbsWithExistingId() throws Exception {
        // Create the Projectpbs with an existing ID
        projectpbs.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectpbsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectpbs)))
            .andExpect(status().isBadRequest());

        // Validate the Projectpbs in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProjectpbs() throws Exception {
        // Initialize the database
        insertedProjectpbs = projectpbsRepository.saveAndFlush(projectpbs);

        // Get all the projectpbsList
        restProjectpbsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectpbs.getId())))
            .andExpect(jsonPath("$.[*].pbsname").value(hasItem(DEFAULT_PBSNAME)))
            .andExpect(jsonPath("$.[*].parentpbsid").value(hasItem(DEFAULT_PARENTPBSID)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())))
            .andExpect(jsonPath("$.[*].progress").value(hasItem(DEFAULT_PROGRESS)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].priorty").value(hasItem(DEFAULT_PRIORTY)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].wbsid").value(hasItem(DEFAULT_WBSID)))
            .andExpect(jsonPath("$.[*].workbag").value(hasItem(DEFAULT_WORKBAG)));
    }

    @Test
    @Transactional
    void getProjectpbs() throws Exception {
        // Initialize the database
        insertedProjectpbs = projectpbsRepository.saveAndFlush(projectpbs);

        // Get the projectpbs
        restProjectpbsMockMvc
            .perform(get(ENTITY_API_URL_ID, projectpbs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectpbs.getId()))
            .andExpect(jsonPath("$.pbsname").value(DEFAULT_PBSNAME))
            .andExpect(jsonPath("$.parentpbsid").value(DEFAULT_PARENTPBSID))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()))
            .andExpect(jsonPath("$.progress").value(DEFAULT_PROGRESS))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.priorty").value(DEFAULT_PRIORTY))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()))
            .andExpect(jsonPath("$.wbsid").value(DEFAULT_WBSID))
            .andExpect(jsonPath("$.workbag").value(DEFAULT_WORKBAG));
    }

    @Test
    @Transactional
    void getNonExistingProjectpbs() throws Exception {
        // Get the projectpbs
        restProjectpbsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProjectpbs() throws Exception {
        // Initialize the database
        insertedProjectpbs = projectpbsRepository.saveAndFlush(projectpbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectpbs
        Projectpbs updatedProjectpbs = projectpbsRepository.findById(projectpbs.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProjectpbs are not directly saved in db
        em.detach(updatedProjectpbs);
        updatedProjectpbs
            .pbsname(UPDATED_PBSNAME)
            .parentpbsid(UPDATED_PARENTPBSID)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .progress(UPDATED_PROGRESS)
            .type(UPDATED_TYPE)
            .priorty(UPDATED_PRIORTY)
            .secretlevel(UPDATED_SECRETLEVEL)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .wbsid(UPDATED_WBSID)
            .workbag(UPDATED_WORKBAG);

        restProjectpbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProjectpbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedProjectpbs))
            )
            .andExpect(status().isOk());

        // Validate the Projectpbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProjectpbsToMatchAllProperties(updatedProjectpbs);
    }

    @Test
    @Transactional
    void putNonExistingProjectpbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectpbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectpbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, projectpbs.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectpbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectpbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProjectpbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectpbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectpbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(projectpbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectpbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProjectpbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectpbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectpbsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectpbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Projectpbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProjectpbsWithPatch() throws Exception {
        // Initialize the database
        insertedProjectpbs = projectpbsRepository.saveAndFlush(projectpbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectpbs using partial update
        Projectpbs partialUpdatedProjectpbs = new Projectpbs();
        partialUpdatedProjectpbs.setId(projectpbs.getId());

        partialUpdatedProjectpbs.progress(UPDATED_PROGRESS).workbag(UPDATED_WORKBAG);

        restProjectpbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectpbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectpbs))
            )
            .andExpect(status().isOk());

        // Validate the Projectpbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectpbsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProjectpbs, projectpbs),
            getPersistedProjectpbs(projectpbs)
        );
    }

    @Test
    @Transactional
    void fullUpdateProjectpbsWithPatch() throws Exception {
        // Initialize the database
        insertedProjectpbs = projectpbsRepository.saveAndFlush(projectpbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectpbs using partial update
        Projectpbs partialUpdatedProjectpbs = new Projectpbs();
        partialUpdatedProjectpbs.setId(projectpbs.getId());

        partialUpdatedProjectpbs
            .pbsname(UPDATED_PBSNAME)
            .parentpbsid(UPDATED_PARENTPBSID)
            .description(UPDATED_DESCRIPTION)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .progress(UPDATED_PROGRESS)
            .type(UPDATED_TYPE)
            .priorty(UPDATED_PRIORTY)
            .secretlevel(UPDATED_SECRETLEVEL)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .wbsid(UPDATED_WBSID)
            .workbag(UPDATED_WORKBAG);

        restProjectpbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectpbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectpbs))
            )
            .andExpect(status().isOk());

        // Validate the Projectpbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectpbsUpdatableFieldsEquals(partialUpdatedProjectpbs, getPersistedProjectpbs(partialUpdatedProjectpbs));
    }

    @Test
    @Transactional
    void patchNonExistingProjectpbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectpbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectpbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, projectpbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectpbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectpbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProjectpbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectpbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectpbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectpbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectpbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProjectpbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectpbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectpbsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(projectpbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Projectpbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProjectpbs() throws Exception {
        // Initialize the database
        insertedProjectpbs = projectpbsRepository.saveAndFlush(projectpbs);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the projectpbs
        restProjectpbsMockMvc
            .perform(delete(ENTITY_API_URL_ID, projectpbs.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return projectpbsRepository.count();
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

    protected Projectpbs getPersistedProjectpbs(Projectpbs projectpbs) {
        return projectpbsRepository.findById(projectpbs.getId()).orElseThrow();
    }

    protected void assertPersistedProjectpbsToMatchAllProperties(Projectpbs expectedProjectpbs) {
        assertProjectpbsAllPropertiesEquals(expectedProjectpbs, getPersistedProjectpbs(expectedProjectpbs));
    }

    protected void assertPersistedProjectpbsToMatchUpdatableProperties(Projectpbs expectedProjectpbs) {
        assertProjectpbsAllUpdatablePropertiesEquals(expectedProjectpbs, getPersistedProjectpbs(expectedProjectpbs));
    }
}

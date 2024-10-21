package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.ProjectTotalwbsAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.ProjectTotalwbs;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.ProjectStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.repository.ProjectTotalwbsRepository;
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
 * Integration tests for the {@link ProjectTotalwbsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProjectTotalwbsResourceIT {

    private static final String DEFAULT_WBSNAME = "AAAAAAAAAA";
    private static final String UPDATED_WBSNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PARENTWBSID = "AAAAAAAAAA";
    private static final String UPDATED_PARENTWBSID = "BBBBBBBBBB";

    private static final String DEFAULT_PBSID = "AAAAAAAAAA";
    private static final String UPDATED_PBSID = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_BELONGFRONT = "AAAAAAAAAA";
    private static final String UPDATED_BELONGFRONT = "BBBBBBBBBB";

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
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.PUBLIC;

    private static final String DEFAULT_DELIVERABLES = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERABLES = "BBBBBBBBBB";

    private static final ProjectStatus DEFAULT_STATUS = ProjectStatus.NOTSTART;
    private static final ProjectStatus UPDATED_STATUS = ProjectStatus.IN_PROGRESS;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.NOT_AUDITED;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.IN_AUDIT;

    private static final Integer DEFAULT_WORKBAG = 1;
    private static final Integer UPDATED_WORKBAG = 2;

    private static final String ENTITY_API_URL = "/api/projecttotalwbs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProjectTotalwbsRepository projectTotalwbsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectTotalwbsMockMvc;

    private ProjectTotalwbs projectTotalwbs;

    private ProjectTotalwbs insertedProjectTotalwbs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectTotalwbs createEntity(EntityManager em) {
        ProjectTotalwbs projectTotalwbs = new ProjectTotalwbs()
            .wbsname(DEFAULT_WBSNAME)
            .parentwbsid(DEFAULT_PARENTWBSID)
            .pbsid(DEFAULT_PBSID)
            .description(DEFAULT_DESCRIPTION)
            .belongfront(DEFAULT_BELONGFRONT)
            .starttime(DEFAULT_STARTTIME)
            .endtime(DEFAULT_ENDTIME)
            .progress(DEFAULT_PROGRESS)
            .type(DEFAULT_TYPE)
            .priorty(DEFAULT_PRIORTY)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .deliverables(DEFAULT_DELIVERABLES)
            .status(DEFAULT_STATUS)
            .auditStatus(DEFAULT_AUDIT_STATUS)
            .workbag(DEFAULT_WORKBAG);
        return projectTotalwbs;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectTotalwbs createUpdatedEntity(EntityManager em) {
        ProjectTotalwbs projectTotalwbs = new ProjectTotalwbs()
            .wbsname(UPDATED_WBSNAME)
            .parentwbsid(UPDATED_PARENTWBSID)
            .pbsid(UPDATED_PBSID)
            .description(UPDATED_DESCRIPTION)
            .belongfront(UPDATED_BELONGFRONT)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .progress(UPDATED_PROGRESS)
            .type(UPDATED_TYPE)
            .priorty(UPDATED_PRIORTY)
            .secretlevel(UPDATED_SECRETLEVEL)
            .deliverables(UPDATED_DELIVERABLES)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .workbag(UPDATED_WORKBAG);
        return projectTotalwbs;
    }

    @BeforeEach
    public void initTest() {
        projectTotalwbs = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedProjectTotalwbs != null) {
            projectTotalwbsRepository.delete(insertedProjectTotalwbs);
            insertedProjectTotalwbs = null;
        }
    }

    @Test
    @Transactional
    void createProjectTotalwbs() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ProjectTotalwbs
        var returnedProjectTotalwbs = om.readValue(
            restProjectTotalwbsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectTotalwbs)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ProjectTotalwbs.class
        );

        // Validate the ProjectTotalwbs in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertProjectTotalwbsUpdatableFieldsEquals(returnedProjectTotalwbs, getPersistedProjectTotalwbs(returnedProjectTotalwbs));

        insertedProjectTotalwbs = returnedProjectTotalwbs;
    }

    @Test
    @Transactional
    void createProjectTotalwbsWithExistingId() throws Exception {
        // Create the ProjectTotalwbs with an existing ID
        projectTotalwbs.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectTotalwbsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectTotalwbs)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectTotalwbs in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProjectTotalwbs() throws Exception {
        // Initialize the database
        insertedProjectTotalwbs = projectTotalwbsRepository.saveAndFlush(projectTotalwbs);

        // Get all the projectTotalwbsList
        restProjectTotalwbsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectTotalwbs.getId())))
            .andExpect(jsonPath("$.[*].wbsname").value(hasItem(DEFAULT_WBSNAME)))
            .andExpect(jsonPath("$.[*].parentwbsid").value(hasItem(DEFAULT_PARENTWBSID)))
            .andExpect(jsonPath("$.[*].pbsid").value(hasItem(DEFAULT_PBSID)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].belongfront").value(hasItem(DEFAULT_BELONGFRONT)))
            .andExpect(jsonPath("$.[*].starttime").value(hasItem(DEFAULT_STARTTIME.toString())))
            .andExpect(jsonPath("$.[*].endtime").value(hasItem(DEFAULT_ENDTIME.toString())))
            .andExpect(jsonPath("$.[*].progress").value(hasItem(DEFAULT_PROGRESS)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].priorty").value(hasItem(DEFAULT_PRIORTY)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].deliverables").value(hasItem(DEFAULT_DELIVERABLES)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].workbag").value(hasItem(DEFAULT_WORKBAG)));
    }

    @Test
    @Transactional
    void getProjectTotalwbs() throws Exception {
        // Initialize the database
        insertedProjectTotalwbs = projectTotalwbsRepository.saveAndFlush(projectTotalwbs);

        // Get the projectTotalwbs
        restProjectTotalwbsMockMvc
            .perform(get(ENTITY_API_URL_ID, projectTotalwbs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectTotalwbs.getId()))
            .andExpect(jsonPath("$.wbsname").value(DEFAULT_WBSNAME))
            .andExpect(jsonPath("$.parentwbsid").value(DEFAULT_PARENTWBSID))
            .andExpect(jsonPath("$.pbsid").value(DEFAULT_PBSID))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.belongfront").value(DEFAULT_BELONGFRONT))
            .andExpect(jsonPath("$.starttime").value(DEFAULT_STARTTIME.toString()))
            .andExpect(jsonPath("$.endtime").value(DEFAULT_ENDTIME.toString()))
            .andExpect(jsonPath("$.progress").value(DEFAULT_PROGRESS))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.priorty").value(DEFAULT_PRIORTY))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.deliverables").value(DEFAULT_DELIVERABLES))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()))
            .andExpect(jsonPath("$.workbag").value(DEFAULT_WORKBAG));
    }

    @Test
    @Transactional
    void getNonExistingProjectTotalwbs() throws Exception {
        // Get the projectTotalwbs
        restProjectTotalwbsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProjectTotalwbs() throws Exception {
        // Initialize the database
        insertedProjectTotalwbs = projectTotalwbsRepository.saveAndFlush(projectTotalwbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectTotalwbs
        ProjectTotalwbs updatedProjectTotalwbs = projectTotalwbsRepository.findById(projectTotalwbs.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProjectTotalwbs are not directly saved in db
        em.detach(updatedProjectTotalwbs);
        updatedProjectTotalwbs
            .wbsname(UPDATED_WBSNAME)
            .parentwbsid(UPDATED_PARENTWBSID)
            .pbsid(UPDATED_PBSID)
            .description(UPDATED_DESCRIPTION)
            .belongfront(UPDATED_BELONGFRONT)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .progress(UPDATED_PROGRESS)
            .type(UPDATED_TYPE)
            .priorty(UPDATED_PRIORTY)
            .secretlevel(UPDATED_SECRETLEVEL)
            .deliverables(UPDATED_DELIVERABLES)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .workbag(UPDATED_WORKBAG);

        restProjectTotalwbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProjectTotalwbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedProjectTotalwbs))
            )
            .andExpect(status().isOk());

        // Validate the ProjectTotalwbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProjectTotalwbsToMatchAllProperties(updatedProjectTotalwbs);
    }

    @Test
    @Transactional
    void putNonExistingProjectTotalwbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectTotalwbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectTotalwbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, projectTotalwbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(projectTotalwbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectTotalwbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProjectTotalwbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectTotalwbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectTotalwbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(projectTotalwbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectTotalwbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProjectTotalwbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectTotalwbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectTotalwbsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectTotalwbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProjectTotalwbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProjectTotalwbsWithPatch() throws Exception {
        // Initialize the database
        insertedProjectTotalwbs = projectTotalwbsRepository.saveAndFlush(projectTotalwbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectTotalwbs using partial update
        ProjectTotalwbs partialUpdatedProjectTotalwbs = new ProjectTotalwbs();
        partialUpdatedProjectTotalwbs.setId(projectTotalwbs.getId());

        partialUpdatedProjectTotalwbs
            .pbsid(UPDATED_PBSID)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .priorty(UPDATED_PRIORTY)
            .secretlevel(UPDATED_SECRETLEVEL)
            .deliverables(UPDATED_DELIVERABLES)
            .status(UPDATED_STATUS);

        restProjectTotalwbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectTotalwbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectTotalwbs))
            )
            .andExpect(status().isOk());

        // Validate the ProjectTotalwbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectTotalwbsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProjectTotalwbs, projectTotalwbs),
            getPersistedProjectTotalwbs(projectTotalwbs)
        );
    }

    @Test
    @Transactional
    void fullUpdateProjectTotalwbsWithPatch() throws Exception {
        // Initialize the database
        insertedProjectTotalwbs = projectTotalwbsRepository.saveAndFlush(projectTotalwbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectTotalwbs using partial update
        ProjectTotalwbs partialUpdatedProjectTotalwbs = new ProjectTotalwbs();
        partialUpdatedProjectTotalwbs.setId(projectTotalwbs.getId());

        partialUpdatedProjectTotalwbs
            .wbsname(UPDATED_WBSNAME)
            .parentwbsid(UPDATED_PARENTWBSID)
            .pbsid(UPDATED_PBSID)
            .description(UPDATED_DESCRIPTION)
            .belongfront(UPDATED_BELONGFRONT)
            .starttime(UPDATED_STARTTIME)
            .endtime(UPDATED_ENDTIME)
            .progress(UPDATED_PROGRESS)
            .type(UPDATED_TYPE)
            .priorty(UPDATED_PRIORTY)
            .secretlevel(UPDATED_SECRETLEVEL)
            .deliverables(UPDATED_DELIVERABLES)
            .status(UPDATED_STATUS)
            .auditStatus(UPDATED_AUDIT_STATUS)
            .workbag(UPDATED_WORKBAG);

        restProjectTotalwbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectTotalwbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectTotalwbs))
            )
            .andExpect(status().isOk());

        // Validate the ProjectTotalwbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectTotalwbsUpdatableFieldsEquals(
            partialUpdatedProjectTotalwbs,
            getPersistedProjectTotalwbs(partialUpdatedProjectTotalwbs)
        );
    }

    @Test
    @Transactional
    void patchNonExistingProjectTotalwbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectTotalwbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectTotalwbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, projectTotalwbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectTotalwbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectTotalwbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProjectTotalwbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectTotalwbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectTotalwbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectTotalwbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectTotalwbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProjectTotalwbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectTotalwbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectTotalwbsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(projectTotalwbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProjectTotalwbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProjectTotalwbs() throws Exception {
        // Initialize the database
        insertedProjectTotalwbs = projectTotalwbsRepository.saveAndFlush(projectTotalwbs);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the projectTotalwbs
        restProjectTotalwbsMockMvc
            .perform(delete(ENTITY_API_URL_ID, projectTotalwbs.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return projectTotalwbsRepository.count();
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

    protected ProjectTotalwbs getPersistedProjectTotalwbs(ProjectTotalwbs projectTotalwbs) {
        return projectTotalwbsRepository.findById(projectTotalwbs.getId()).orElseThrow();
    }

    protected void assertPersistedProjectTotalwbsToMatchAllProperties(ProjectTotalwbs expectedProjectTotalwbs) {
        assertProjectTotalwbsAllPropertiesEquals(expectedProjectTotalwbs, getPersistedProjectTotalwbs(expectedProjectTotalwbs));
    }

    protected void assertPersistedProjectTotalwbsToMatchUpdatableProperties(ProjectTotalwbs expectedProjectTotalwbs) {
        assertProjectTotalwbsAllUpdatablePropertiesEquals(expectedProjectTotalwbs, getPersistedProjectTotalwbs(expectedProjectTotalwbs));
    }
}

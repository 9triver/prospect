package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.ProjectwbsAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.Projectwbs;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.ProjectStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.repository.ProjectwbsRepository;
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
 * Integration tests for the {@link ProjectwbsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProjectwbsResourceIT {

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
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final String DEFAULT_DELIVERABLES = "AAAAAAAAAA";
    private static final String UPDATED_DELIVERABLES = "BBBBBBBBBB";

    private static final ProjectStatus DEFAULT_STATUS = ProjectStatus.NOTSTART;
    private static final ProjectStatus UPDATED_STATUS = ProjectStatus.IN_PROGRESS;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final Integer DEFAULT_WORKBAG = 1;
    private static final Integer UPDATED_WORKBAG = 2;

    private static final String ENTITY_API_URL = "/api/projectwbs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProjectwbsRepository projectwbsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectwbsMockMvc;

    private Projectwbs projectwbs;

    private Projectwbs insertedProjectwbs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Projectwbs createEntity(EntityManager em) {
        Projectwbs projectwbs = new Projectwbs()
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
        return projectwbs;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Projectwbs createUpdatedEntity(EntityManager em) {
        Projectwbs projectwbs = new Projectwbs()
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
        return projectwbs;
    }

    @BeforeEach
    public void initTest() {
        projectwbs = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedProjectwbs != null) {
            projectwbsRepository.delete(insertedProjectwbs);
            insertedProjectwbs = null;
        }
    }

    @Test
    @Transactional
    void createProjectwbs() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Projectwbs
        var returnedProjectwbs = om.readValue(
            restProjectwbsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectwbs)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Projectwbs.class
        );

        // Validate the Projectwbs in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertProjectwbsUpdatableFieldsEquals(returnedProjectwbs, getPersistedProjectwbs(returnedProjectwbs));

        insertedProjectwbs = returnedProjectwbs;
    }

    @Test
    @Transactional
    void createProjectwbsWithExistingId() throws Exception {
        // Create the Projectwbs with an existing ID
        projectwbs.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectwbsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectwbs)))
            .andExpect(status().isBadRequest());

        // Validate the Projectwbs in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProjectwbs() throws Exception {
        // Initialize the database
        insertedProjectwbs = projectwbsRepository.saveAndFlush(projectwbs);

        // Get all the projectwbsList
        restProjectwbsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectwbs.getId())))
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
    void getProjectwbs() throws Exception {
        // Initialize the database
        insertedProjectwbs = projectwbsRepository.saveAndFlush(projectwbs);

        // Get the projectwbs
        restProjectwbsMockMvc
            .perform(get(ENTITY_API_URL_ID, projectwbs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectwbs.getId()))
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
    void getNonExistingProjectwbs() throws Exception {
        // Get the projectwbs
        restProjectwbsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProjectwbs() throws Exception {
        // Initialize the database
        insertedProjectwbs = projectwbsRepository.saveAndFlush(projectwbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectwbs
        Projectwbs updatedProjectwbs = projectwbsRepository.findById(projectwbs.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProjectwbs are not directly saved in db
        em.detach(updatedProjectwbs);
        updatedProjectwbs
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

        restProjectwbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProjectwbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedProjectwbs))
            )
            .andExpect(status().isOk());

        // Validate the Projectwbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProjectwbsToMatchAllProperties(updatedProjectwbs);
    }

    @Test
    @Transactional
    void putNonExistingProjectwbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectwbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectwbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, projectwbs.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectwbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectwbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProjectwbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectwbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectwbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(projectwbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectwbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProjectwbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectwbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectwbsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectwbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Projectwbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProjectwbsWithPatch() throws Exception {
        // Initialize the database
        insertedProjectwbs = projectwbsRepository.saveAndFlush(projectwbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectwbs using partial update
        Projectwbs partialUpdatedProjectwbs = new Projectwbs();
        partialUpdatedProjectwbs.setId(projectwbs.getId());

        partialUpdatedProjectwbs
            .wbsname(UPDATED_WBSNAME)
            .parentwbsid(UPDATED_PARENTWBSID)
            .pbsid(UPDATED_PBSID)
            .description(UPDATED_DESCRIPTION)
            .belongfront(UPDATED_BELONGFRONT)
            .endtime(UPDATED_ENDTIME)
            .type(UPDATED_TYPE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .deliverables(UPDATED_DELIVERABLES)
            .workbag(UPDATED_WORKBAG);

        restProjectwbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectwbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectwbs))
            )
            .andExpect(status().isOk());

        // Validate the Projectwbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectwbsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProjectwbs, projectwbs),
            getPersistedProjectwbs(projectwbs)
        );
    }

    @Test
    @Transactional
    void fullUpdateProjectwbsWithPatch() throws Exception {
        // Initialize the database
        insertedProjectwbs = projectwbsRepository.saveAndFlush(projectwbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectwbs using partial update
        Projectwbs partialUpdatedProjectwbs = new Projectwbs();
        partialUpdatedProjectwbs.setId(projectwbs.getId());

        partialUpdatedProjectwbs
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

        restProjectwbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectwbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectwbs))
            )
            .andExpect(status().isOk());

        // Validate the Projectwbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectwbsUpdatableFieldsEquals(partialUpdatedProjectwbs, getPersistedProjectwbs(partialUpdatedProjectwbs));
    }

    @Test
    @Transactional
    void patchNonExistingProjectwbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectwbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectwbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, projectwbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectwbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectwbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProjectwbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectwbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectwbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectwbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectwbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProjectwbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectwbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectwbsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(projectwbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Projectwbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProjectwbs() throws Exception {
        // Initialize the database
        insertedProjectwbs = projectwbsRepository.saveAndFlush(projectwbs);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the projectwbs
        restProjectwbsMockMvc
            .perform(delete(ENTITY_API_URL_ID, projectwbs.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return projectwbsRepository.count();
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

    protected Projectwbs getPersistedProjectwbs(Projectwbs projectwbs) {
        return projectwbsRepository.findById(projectwbs.getId()).orElseThrow();
    }

    protected void assertPersistedProjectwbsToMatchAllProperties(Projectwbs expectedProjectwbs) {
        assertProjectwbsAllPropertiesEquals(expectedProjectwbs, getPersistedProjectwbs(expectedProjectwbs));
    }

    protected void assertPersistedProjectwbsToMatchUpdatableProperties(Projectwbs expectedProjectwbs) {
        assertProjectwbsAllUpdatablePropertiesEquals(expectedProjectwbs, getPersistedProjectwbs(expectedProjectwbs));
    }
}

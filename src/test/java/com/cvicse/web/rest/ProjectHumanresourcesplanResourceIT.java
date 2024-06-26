package com.cvicse.web.rest;

import static com.cvicse.domain.ProjectHumanresourcesplanAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.ProjectHumanresourcesplan;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.ProjectHumanresourcesplanRepository;
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
 * Integration tests for the {@link ProjectHumanresourcesplanResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProjectHumanresourcesplanResourceIT {

    private static final String DEFAULT_PROJECTNAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENTNAME = "AAAAAAAAAA";
    private static final String UPDATED_CLIENTNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PLANDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PLANDATE = LocalDate.now(ZoneId.systemDefault());

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/project-humanresourcesplans";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProjectHumanresourcesplanRepository projectHumanresourcesplanRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectHumanresourcesplanMockMvc;

    private ProjectHumanresourcesplan projectHumanresourcesplan;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectHumanresourcesplan createEntity(EntityManager em) {
        ProjectHumanresourcesplan projectHumanresourcesplan = new ProjectHumanresourcesplan()
            .projectname(DEFAULT_PROJECTNAME)
            .clientname(DEFAULT_CLIENTNAME)
            .plandate(DEFAULT_PLANDATE)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return projectHumanresourcesplan;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectHumanresourcesplan createUpdatedEntity(EntityManager em) {
        ProjectHumanresourcesplan projectHumanresourcesplan = new ProjectHumanresourcesplan()
            .projectname(UPDATED_PROJECTNAME)
            .clientname(UPDATED_CLIENTNAME)
            .plandate(UPDATED_PLANDATE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return projectHumanresourcesplan;
    }

    @BeforeEach
    public void initTest() {
        projectHumanresourcesplan = createEntity(em);
    }

    @Test
    @Transactional
    void createProjectHumanresourcesplan() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ProjectHumanresourcesplan
        var returnedProjectHumanresourcesplan = om.readValue(
            restProjectHumanresourcesplanMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectHumanresourcesplan))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ProjectHumanresourcesplan.class
        );

        // Validate the ProjectHumanresourcesplan in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertProjectHumanresourcesplanUpdatableFieldsEquals(
            returnedProjectHumanresourcesplan,
            getPersistedProjectHumanresourcesplan(returnedProjectHumanresourcesplan)
        );
    }

    @Test
    @Transactional
    void createProjectHumanresourcesplanWithExistingId() throws Exception {
        // Create the ProjectHumanresourcesplan with an existing ID
        projectHumanresourcesplan.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectHumanresourcesplanMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectHumanresourcesplan)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectHumanresourcesplan in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProjectHumanresourcesplans() throws Exception {
        // Initialize the database
        projectHumanresourcesplanRepository.saveAndFlush(projectHumanresourcesplan);

        // Get all the projectHumanresourcesplanList
        restProjectHumanresourcesplanMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectHumanresourcesplan.getId())))
            .andExpect(jsonPath("$.[*].projectname").value(hasItem(DEFAULT_PROJECTNAME)))
            .andExpect(jsonPath("$.[*].clientname").value(hasItem(DEFAULT_CLIENTNAME)))
            .andExpect(jsonPath("$.[*].plandate").value(hasItem(DEFAULT_PLANDATE.toString())))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getProjectHumanresourcesplan() throws Exception {
        // Initialize the database
        projectHumanresourcesplanRepository.saveAndFlush(projectHumanresourcesplan);

        // Get the projectHumanresourcesplan
        restProjectHumanresourcesplanMockMvc
            .perform(get(ENTITY_API_URL_ID, projectHumanresourcesplan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectHumanresourcesplan.getId()))
            .andExpect(jsonPath("$.projectname").value(DEFAULT_PROJECTNAME))
            .andExpect(jsonPath("$.clientname").value(DEFAULT_CLIENTNAME))
            .andExpect(jsonPath("$.plandate").value(DEFAULT_PLANDATE.toString()))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingProjectHumanresourcesplan() throws Exception {
        // Get the projectHumanresourcesplan
        restProjectHumanresourcesplanMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProjectHumanresourcesplan() throws Exception {
        // Initialize the database
        projectHumanresourcesplanRepository.saveAndFlush(projectHumanresourcesplan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectHumanresourcesplan
        ProjectHumanresourcesplan updatedProjectHumanresourcesplan = projectHumanresourcesplanRepository
            .findById(projectHumanresourcesplan.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedProjectHumanresourcesplan are not directly saved in db
        em.detach(updatedProjectHumanresourcesplan);
        updatedProjectHumanresourcesplan
            .projectname(UPDATED_PROJECTNAME)
            .clientname(UPDATED_CLIENTNAME)
            .plandate(UPDATED_PLANDATE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restProjectHumanresourcesplanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProjectHumanresourcesplan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedProjectHumanresourcesplan))
            )
            .andExpect(status().isOk());

        // Validate the ProjectHumanresourcesplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProjectHumanresourcesplanToMatchAllProperties(updatedProjectHumanresourcesplan);
    }

    @Test
    @Transactional
    void putNonExistingProjectHumanresourcesplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectHumanresourcesplan.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectHumanresourcesplanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, projectHumanresourcesplan.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(projectHumanresourcesplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectHumanresourcesplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProjectHumanresourcesplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectHumanresourcesplan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectHumanresourcesplanMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(projectHumanresourcesplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectHumanresourcesplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProjectHumanresourcesplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectHumanresourcesplan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectHumanresourcesplanMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectHumanresourcesplan)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProjectHumanresourcesplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProjectHumanresourcesplanWithPatch() throws Exception {
        // Initialize the database
        projectHumanresourcesplanRepository.saveAndFlush(projectHumanresourcesplan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectHumanresourcesplan using partial update
        ProjectHumanresourcesplan partialUpdatedProjectHumanresourcesplan = new ProjectHumanresourcesplan();
        partialUpdatedProjectHumanresourcesplan.setId(projectHumanresourcesplan.getId());

        partialUpdatedProjectHumanresourcesplan.projectname(UPDATED_PROJECTNAME).clientname(UPDATED_CLIENTNAME);

        restProjectHumanresourcesplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectHumanresourcesplan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectHumanresourcesplan))
            )
            .andExpect(status().isOk());

        // Validate the ProjectHumanresourcesplan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectHumanresourcesplanUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProjectHumanresourcesplan, projectHumanresourcesplan),
            getPersistedProjectHumanresourcesplan(projectHumanresourcesplan)
        );
    }

    @Test
    @Transactional
    void fullUpdateProjectHumanresourcesplanWithPatch() throws Exception {
        // Initialize the database
        projectHumanresourcesplanRepository.saveAndFlush(projectHumanresourcesplan);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectHumanresourcesplan using partial update
        ProjectHumanresourcesplan partialUpdatedProjectHumanresourcesplan = new ProjectHumanresourcesplan();
        partialUpdatedProjectHumanresourcesplan.setId(projectHumanresourcesplan.getId());

        partialUpdatedProjectHumanresourcesplan
            .projectname(UPDATED_PROJECTNAME)
            .clientname(UPDATED_CLIENTNAME)
            .plandate(UPDATED_PLANDATE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restProjectHumanresourcesplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectHumanresourcesplan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectHumanresourcesplan))
            )
            .andExpect(status().isOk());

        // Validate the ProjectHumanresourcesplan in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectHumanresourcesplanUpdatableFieldsEquals(
            partialUpdatedProjectHumanresourcesplan,
            getPersistedProjectHumanresourcesplan(partialUpdatedProjectHumanresourcesplan)
        );
    }

    @Test
    @Transactional
    void patchNonExistingProjectHumanresourcesplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectHumanresourcesplan.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectHumanresourcesplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, projectHumanresourcesplan.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectHumanresourcesplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectHumanresourcesplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProjectHumanresourcesplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectHumanresourcesplan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectHumanresourcesplanMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectHumanresourcesplan))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectHumanresourcesplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProjectHumanresourcesplan() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectHumanresourcesplan.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectHumanresourcesplanMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(projectHumanresourcesplan))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProjectHumanresourcesplan in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProjectHumanresourcesplan() throws Exception {
        // Initialize the database
        projectHumanresourcesplanRepository.saveAndFlush(projectHumanresourcesplan);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the projectHumanresourcesplan
        restProjectHumanresourcesplanMockMvc
            .perform(delete(ENTITY_API_URL_ID, projectHumanresourcesplan.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return projectHumanresourcesplanRepository.count();
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

    protected ProjectHumanresourcesplan getPersistedProjectHumanresourcesplan(ProjectHumanresourcesplan projectHumanresourcesplan) {
        return projectHumanresourcesplanRepository.findById(projectHumanresourcesplan.getId()).orElseThrow();
    }

    protected void assertPersistedProjectHumanresourcesplanToMatchAllProperties(
        ProjectHumanresourcesplan expectedProjectHumanresourcesplan
    ) {
        assertProjectHumanresourcesplanAllPropertiesEquals(
            expectedProjectHumanresourcesplan,
            getPersistedProjectHumanresourcesplan(expectedProjectHumanresourcesplan)
        );
    }

    protected void assertPersistedProjectHumanresourcesplanToMatchUpdatableProperties(
        ProjectHumanresourcesplan expectedProjectHumanresourcesplan
    ) {
        assertProjectHumanresourcesplanAllUpdatablePropertiesEquals(
            expectedProjectHumanresourcesplan,
            getPersistedProjectHumanresourcesplan(expectedProjectHumanresourcesplan)
        );
    }
}

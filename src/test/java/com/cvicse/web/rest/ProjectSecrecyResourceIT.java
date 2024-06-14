package com.cvicse.web.rest;

import static com.cvicse.domain.ProjectSecrecyAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.ProjectSecrecy;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.repository.ProjectSecrecyRepository;
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
 * Integration tests for the {@link ProjectSecrecyResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProjectSecrecyResourceIT {

    private static final String DEFAULT_PROJECTNAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATETIME = LocalDate.now(ZoneId.systemDefault());

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/project-secrecies";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProjectSecrecyRepository projectSecrecyRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectSecrecyMockMvc;

    private ProjectSecrecy projectSecrecy;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectSecrecy createEntity(EntityManager em) {
        ProjectSecrecy projectSecrecy = new ProjectSecrecy()
            .projectname(DEFAULT_PROJECTNAME)
            .description(DEFAULT_DESCRIPTION)
            .createtime(DEFAULT_CREATETIME)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return projectSecrecy;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectSecrecy createUpdatedEntity(EntityManager em) {
        ProjectSecrecy projectSecrecy = new ProjectSecrecy()
            .projectname(UPDATED_PROJECTNAME)
            .description(UPDATED_DESCRIPTION)
            .createtime(UPDATED_CREATETIME)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return projectSecrecy;
    }

    @BeforeEach
    public void initTest() {
        projectSecrecy = createEntity(em);
    }

    @Test
    @Transactional
    void createProjectSecrecy() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ProjectSecrecy
        var returnedProjectSecrecy = om.readValue(
            restProjectSecrecyMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectSecrecy)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ProjectSecrecy.class
        );

        // Validate the ProjectSecrecy in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertProjectSecrecyUpdatableFieldsEquals(returnedProjectSecrecy, getPersistedProjectSecrecy(returnedProjectSecrecy));
    }

    @Test
    @Transactional
    void createProjectSecrecyWithExistingId() throws Exception {
        // Create the ProjectSecrecy with an existing ID
        projectSecrecy.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectSecrecyMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectSecrecy)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectSecrecy in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProjectSecrecies() throws Exception {
        // Initialize the database
        projectSecrecyRepository.saveAndFlush(projectSecrecy);

        // Get all the projectSecrecyList
        restProjectSecrecyMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectSecrecy.getId().intValue())))
            .andExpect(jsonPath("$.[*].projectname").value(hasItem(DEFAULT_PROJECTNAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].createtime").value(hasItem(DEFAULT_CREATETIME.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getProjectSecrecy() throws Exception {
        // Initialize the database
        projectSecrecyRepository.saveAndFlush(projectSecrecy);

        // Get the projectSecrecy
        restProjectSecrecyMockMvc
            .perform(get(ENTITY_API_URL_ID, projectSecrecy.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectSecrecy.getId().intValue()))
            .andExpect(jsonPath("$.projectname").value(DEFAULT_PROJECTNAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.createtime").value(DEFAULT_CREATETIME.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingProjectSecrecy() throws Exception {
        // Get the projectSecrecy
        restProjectSecrecyMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProjectSecrecy() throws Exception {
        // Initialize the database
        projectSecrecyRepository.saveAndFlush(projectSecrecy);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectSecrecy
        ProjectSecrecy updatedProjectSecrecy = projectSecrecyRepository.findById(projectSecrecy.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProjectSecrecy are not directly saved in db
        em.detach(updatedProjectSecrecy);
        updatedProjectSecrecy
            .projectname(UPDATED_PROJECTNAME)
            .description(UPDATED_DESCRIPTION)
            .createtime(UPDATED_CREATETIME)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restProjectSecrecyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProjectSecrecy.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedProjectSecrecy))
            )
            .andExpect(status().isOk());

        // Validate the ProjectSecrecy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProjectSecrecyToMatchAllProperties(updatedProjectSecrecy);
    }

    @Test
    @Transactional
    void putNonExistingProjectSecrecy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectSecrecy.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectSecrecyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, projectSecrecy.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(projectSecrecy))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectSecrecy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProjectSecrecy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectSecrecy.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectSecrecyMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(projectSecrecy))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectSecrecy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProjectSecrecy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectSecrecy.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectSecrecyMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectSecrecy)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProjectSecrecy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProjectSecrecyWithPatch() throws Exception {
        // Initialize the database
        projectSecrecyRepository.saveAndFlush(projectSecrecy);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectSecrecy using partial update
        ProjectSecrecy partialUpdatedProjectSecrecy = new ProjectSecrecy();
        partialUpdatedProjectSecrecy.setId(projectSecrecy.getId());

        partialUpdatedProjectSecrecy.auditStatus(UPDATED_AUDIT_STATUS);

        restProjectSecrecyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectSecrecy.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectSecrecy))
            )
            .andExpect(status().isOk());

        // Validate the ProjectSecrecy in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectSecrecyUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProjectSecrecy, projectSecrecy),
            getPersistedProjectSecrecy(projectSecrecy)
        );
    }

    @Test
    @Transactional
    void fullUpdateProjectSecrecyWithPatch() throws Exception {
        // Initialize the database
        projectSecrecyRepository.saveAndFlush(projectSecrecy);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectSecrecy using partial update
        ProjectSecrecy partialUpdatedProjectSecrecy = new ProjectSecrecy();
        partialUpdatedProjectSecrecy.setId(projectSecrecy.getId());

        partialUpdatedProjectSecrecy
            .projectname(UPDATED_PROJECTNAME)
            .description(UPDATED_DESCRIPTION)
            .createtime(UPDATED_CREATETIME)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restProjectSecrecyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectSecrecy.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectSecrecy))
            )
            .andExpect(status().isOk());

        // Validate the ProjectSecrecy in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectSecrecyUpdatableFieldsEquals(partialUpdatedProjectSecrecy, getPersistedProjectSecrecy(partialUpdatedProjectSecrecy));
    }

    @Test
    @Transactional
    void patchNonExistingProjectSecrecy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectSecrecy.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectSecrecyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, projectSecrecy.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectSecrecy))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectSecrecy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProjectSecrecy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectSecrecy.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectSecrecyMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectSecrecy))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectSecrecy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProjectSecrecy() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectSecrecy.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectSecrecyMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(projectSecrecy)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProjectSecrecy in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProjectSecrecy() throws Exception {
        // Initialize the database
        projectSecrecyRepository.saveAndFlush(projectSecrecy);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the projectSecrecy
        restProjectSecrecyMockMvc
            .perform(delete(ENTITY_API_URL_ID, projectSecrecy.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return projectSecrecyRepository.count();
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

    protected ProjectSecrecy getPersistedProjectSecrecy(ProjectSecrecy projectSecrecy) {
        return projectSecrecyRepository.findById(projectSecrecy.getId()).orElseThrow();
    }

    protected void assertPersistedProjectSecrecyToMatchAllProperties(ProjectSecrecy expectedProjectSecrecy) {
        assertProjectSecrecyAllPropertiesEquals(expectedProjectSecrecy, getPersistedProjectSecrecy(expectedProjectSecrecy));
    }

    protected void assertPersistedProjectSecrecyToMatchUpdatableProperties(ProjectSecrecy expectedProjectSecrecy) {
        assertProjectSecrecyAllUpdatablePropertiesEquals(expectedProjectSecrecy, getPersistedProjectSecrecy(expectedProjectSecrecy));
    }
}

package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.ProjectremitAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.Projectremit;
import com.cvicse.jy1.domain.enumeration.AuditStatus;
import com.cvicse.jy1.domain.enumeration.Secretlevel;
import com.cvicse.jy1.repository.ProjectremitRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link ProjectremitResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProjectremitResourceIT {

    private static final String DEFAULT_REMIT = "AAAAAAAAAA";
    private static final String UPDATED_REMIT = "BBBBBBBBBB";

    private static final String DEFAULT_OUTDEPORTMENT = "AAAAAAAAAA";
    private static final String UPDATED_OUTDEPORTMENT = "BBBBBBBBBB";

    private static final String DEFAULT_INDEPORTMENT = "AAAAAAAAAA";
    private static final String UPDATED_INDEPORTMENT = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECTNAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEPORTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPORTMENT = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECTLEADER = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTLEADER = "BBBBBBBBBB";

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.PUBLIC;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.NOT_AUDITED;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.IN_AUDIT;

    private static final String ENTITY_API_URL = "/api/projectremits";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProjectremitRepository projectremitRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectremitMockMvc;

    private Projectremit projectremit;

    private Projectremit insertedProjectremit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Projectremit createEntity(EntityManager em) {
        Projectremit projectremit = new Projectremit()
            .remit(DEFAULT_REMIT)
            .outdeportment(DEFAULT_OUTDEPORTMENT)
            .indeportment(DEFAULT_INDEPORTMENT)
            .projectname(DEFAULT_PROJECTNAME)
            .deportment(DEFAULT_DEPORTMENT)
            .projectleader(DEFAULT_PROJECTLEADER)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return projectremit;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Projectremit createUpdatedEntity(EntityManager em) {
        Projectremit projectremit = new Projectremit()
            .remit(UPDATED_REMIT)
            .outdeportment(UPDATED_OUTDEPORTMENT)
            .indeportment(UPDATED_INDEPORTMENT)
            .projectname(UPDATED_PROJECTNAME)
            .deportment(UPDATED_DEPORTMENT)
            .projectleader(UPDATED_PROJECTLEADER)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return projectremit;
    }

    @BeforeEach
    public void initTest() {
        projectremit = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedProjectremit != null) {
            projectremitRepository.delete(insertedProjectremit);
            insertedProjectremit = null;
        }
    }

    @Test
    @Transactional
    void createProjectremit() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Projectremit
        var returnedProjectremit = om.readValue(
            restProjectremitMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectremit)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Projectremit.class
        );

        // Validate the Projectremit in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertProjectremitUpdatableFieldsEquals(returnedProjectremit, getPersistedProjectremit(returnedProjectremit));

        insertedProjectremit = returnedProjectremit;
    }

    @Test
    @Transactional
    void createProjectremitWithExistingId() throws Exception {
        // Create the Projectremit with an existing ID
        projectremit.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectremitMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectremit)))
            .andExpect(status().isBadRequest());

        // Validate the Projectremit in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProjectremits() throws Exception {
        // Initialize the database
        insertedProjectremit = projectremitRepository.saveAndFlush(projectremit);

        // Get all the projectremitList
        restProjectremitMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectremit.getId())))
            .andExpect(jsonPath("$.[*].remit").value(hasItem(DEFAULT_REMIT)))
            .andExpect(jsonPath("$.[*].outdeportment").value(hasItem(DEFAULT_OUTDEPORTMENT)))
            .andExpect(jsonPath("$.[*].indeportment").value(hasItem(DEFAULT_INDEPORTMENT)))
            .andExpect(jsonPath("$.[*].projectname").value(hasItem(DEFAULT_PROJECTNAME)))
            .andExpect(jsonPath("$.[*].deportment").value(hasItem(DEFAULT_DEPORTMENT)))
            .andExpect(jsonPath("$.[*].projectleader").value(hasItem(DEFAULT_PROJECTLEADER)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getProjectremit() throws Exception {
        // Initialize the database
        insertedProjectremit = projectremitRepository.saveAndFlush(projectremit);

        // Get the projectremit
        restProjectremitMockMvc
            .perform(get(ENTITY_API_URL_ID, projectremit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectremit.getId()))
            .andExpect(jsonPath("$.remit").value(DEFAULT_REMIT))
            .andExpect(jsonPath("$.outdeportment").value(DEFAULT_OUTDEPORTMENT))
            .andExpect(jsonPath("$.indeportment").value(DEFAULT_INDEPORTMENT))
            .andExpect(jsonPath("$.projectname").value(DEFAULT_PROJECTNAME))
            .andExpect(jsonPath("$.deportment").value(DEFAULT_DEPORTMENT))
            .andExpect(jsonPath("$.projectleader").value(DEFAULT_PROJECTLEADER))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingProjectremit() throws Exception {
        // Get the projectremit
        restProjectremitMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProjectremit() throws Exception {
        // Initialize the database
        insertedProjectremit = projectremitRepository.saveAndFlush(projectremit);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectremit
        Projectremit updatedProjectremit = projectremitRepository.findById(projectremit.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProjectremit are not directly saved in db
        em.detach(updatedProjectremit);
        updatedProjectremit
            .remit(UPDATED_REMIT)
            .outdeportment(UPDATED_OUTDEPORTMENT)
            .indeportment(UPDATED_INDEPORTMENT)
            .projectname(UPDATED_PROJECTNAME)
            .deportment(UPDATED_DEPORTMENT)
            .projectleader(UPDATED_PROJECTLEADER)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restProjectremitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProjectremit.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedProjectremit))
            )
            .andExpect(status().isOk());

        // Validate the Projectremit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProjectremitToMatchAllProperties(updatedProjectremit);
    }

    @Test
    @Transactional
    void putNonExistingProjectremit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectremit.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectremitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, projectremit.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(projectremit))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectremit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProjectremit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectremit.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectremitMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(projectremit))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectremit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProjectremit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectremit.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectremitMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectremit)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Projectremit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProjectremitWithPatch() throws Exception {
        // Initialize the database
        insertedProjectremit = projectremitRepository.saveAndFlush(projectremit);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectremit using partial update
        Projectremit partialUpdatedProjectremit = new Projectremit();
        partialUpdatedProjectremit.setId(projectremit.getId());

        partialUpdatedProjectremit.indeportment(UPDATED_INDEPORTMENT).secretlevel(UPDATED_SECRETLEVEL);

        restProjectremitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectremit.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectremit))
            )
            .andExpect(status().isOk());

        // Validate the Projectremit in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectremitUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProjectremit, projectremit),
            getPersistedProjectremit(projectremit)
        );
    }

    @Test
    @Transactional
    void fullUpdateProjectremitWithPatch() throws Exception {
        // Initialize the database
        insertedProjectremit = projectremitRepository.saveAndFlush(projectremit);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectremit using partial update
        Projectremit partialUpdatedProjectremit = new Projectremit();
        partialUpdatedProjectremit.setId(projectremit.getId());

        partialUpdatedProjectremit
            .remit(UPDATED_REMIT)
            .outdeportment(UPDATED_OUTDEPORTMENT)
            .indeportment(UPDATED_INDEPORTMENT)
            .projectname(UPDATED_PROJECTNAME)
            .deportment(UPDATED_DEPORTMENT)
            .projectleader(UPDATED_PROJECTLEADER)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restProjectremitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectremit.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectremit))
            )
            .andExpect(status().isOk());

        // Validate the Projectremit in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectremitUpdatableFieldsEquals(partialUpdatedProjectremit, getPersistedProjectremit(partialUpdatedProjectremit));
    }

    @Test
    @Transactional
    void patchNonExistingProjectremit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectremit.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectremitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, projectremit.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectremit))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectremit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProjectremit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectremit.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectremitMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectremit))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectremit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProjectremit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectremit.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectremitMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(projectremit)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Projectremit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProjectremit() throws Exception {
        // Initialize the database
        insertedProjectremit = projectremitRepository.saveAndFlush(projectremit);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the projectremit
        restProjectremitMockMvc
            .perform(delete(ENTITY_API_URL_ID, projectremit.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return projectremitRepository.count();
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

    protected Projectremit getPersistedProjectremit(Projectremit projectremit) {
        return projectremitRepository.findById(projectremit.getId()).orElseThrow();
    }

    protected void assertPersistedProjectremitToMatchAllProperties(Projectremit expectedProjectremit) {
        assertProjectremitAllPropertiesEquals(expectedProjectremit, getPersistedProjectremit(expectedProjectremit));
    }

    protected void assertPersistedProjectremitToMatchUpdatableProperties(Projectremit expectedProjectremit) {
        assertProjectremitAllUpdatablePropertiesEquals(expectedProjectremit, getPersistedProjectremit(expectedProjectremit));
    }
}

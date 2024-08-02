package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.ProjectRiskAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.ProjectRisk;
import com.cvicse.jy1.domain.enumeration.Risklevel;
import com.cvicse.jy1.repository.ProjectRiskRepository;
import com.cvicse.jy1.service.ProjectRiskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ProjectRiskResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class ProjectRiskResourceIT {

    private static final Long DEFAULT_YEAR = 1L;
    private static final Long UPDATED_YEAR = 2L;

    private static final String DEFAULT_NODENAME = "AAAAAAAAAA";
    private static final String UPDATED_NODENAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_RISKTYPE = 1;
    private static final Integer UPDATED_RISKTYPE = 2;

    private static final Long DEFAULT_DECUMENTID = 1L;
    private static final Long UPDATED_DECUMENTID = 2L;

    private static final Integer DEFAULT_VERSION = 1;
    private static final Integer UPDATED_VERSION = 2;

    private static final LocalDate DEFAULT_USETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_USETIME = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_SYSTEMLEVEL = 1;
    private static final Integer UPDATED_SYSTEMLEVEL = 2;

    private static final Risklevel DEFAULT_RISKLEVEL = Risklevel.One;
    private static final Risklevel UPDATED_RISKLEVEL = Risklevel.Two;

    private static final String DEFAULT_LIMITATIONTIME = "AAAAAAAAAA";
    private static final String UPDATED_LIMITATIONTIME = "BBBBBBBBBB";

    private static final Integer DEFAULT_CLOSETYPE = 1;
    private static final Integer UPDATED_CLOSETYPE = 2;

    private static final String ENTITY_API_URL = "/api/project-risks";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProjectRiskRepository projectRiskRepository;

    @Mock
    private ProjectRiskRepository projectRiskRepositoryMock;

    @Mock
    private ProjectRiskService projectRiskServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectRiskMockMvc;

    private ProjectRisk projectRisk;

    private ProjectRisk insertedProjectRisk;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectRisk createEntity(EntityManager em) {
        ProjectRisk projectRisk = new ProjectRisk()
            .year(DEFAULT_YEAR)
            .nodename(DEFAULT_NODENAME)
            .risktype(DEFAULT_RISKTYPE)
            .decumentid(DEFAULT_DECUMENTID)
            .version(DEFAULT_VERSION)
            .usetime(DEFAULT_USETIME)
            .systemlevel(DEFAULT_SYSTEMLEVEL)
            .risklevel(DEFAULT_RISKLEVEL)
            .limitationtime(DEFAULT_LIMITATIONTIME)
            .closetype(DEFAULT_CLOSETYPE);
        return projectRisk;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ProjectRisk createUpdatedEntity(EntityManager em) {
        ProjectRisk projectRisk = new ProjectRisk()
            .year(UPDATED_YEAR)
            .nodename(UPDATED_NODENAME)
            .risktype(UPDATED_RISKTYPE)
            .decumentid(UPDATED_DECUMENTID)
            .version(UPDATED_VERSION)
            .usetime(UPDATED_USETIME)
            .systemlevel(UPDATED_SYSTEMLEVEL)
            .risklevel(UPDATED_RISKLEVEL)
            .limitationtime(UPDATED_LIMITATIONTIME)
            .closetype(UPDATED_CLOSETYPE);
        return projectRisk;
    }

    @BeforeEach
    public void initTest() {
        projectRisk = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedProjectRisk != null) {
            projectRiskRepository.delete(insertedProjectRisk);
            insertedProjectRisk = null;
        }
    }

    @Test
    @Transactional
    void createProjectRisk() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ProjectRisk
        var returnedProjectRisk = om.readValue(
            restProjectRiskMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectRisk)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ProjectRisk.class
        );

        // Validate the ProjectRisk in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertProjectRiskUpdatableFieldsEquals(returnedProjectRisk, getPersistedProjectRisk(returnedProjectRisk));

        insertedProjectRisk = returnedProjectRisk;
    }

    @Test
    @Transactional
    void createProjectRiskWithExistingId() throws Exception {
        // Create the ProjectRisk with an existing ID
        projectRisk.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectRiskMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectRisk)))
            .andExpect(status().isBadRequest());

        // Validate the ProjectRisk in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProjectRisks() throws Exception {
        // Initialize the database
        insertedProjectRisk = projectRiskRepository.saveAndFlush(projectRisk);

        // Get all the projectRiskList
        restProjectRiskMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectRisk.getId())))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR.intValue())))
            .andExpect(jsonPath("$.[*].nodename").value(hasItem(DEFAULT_NODENAME)))
            .andExpect(jsonPath("$.[*].risktype").value(hasItem(DEFAULT_RISKTYPE)))
            .andExpect(jsonPath("$.[*].decumentid").value(hasItem(DEFAULT_DECUMENTID.intValue())))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].usetime").value(hasItem(DEFAULT_USETIME.toString())))
            .andExpect(jsonPath("$.[*].systemlevel").value(hasItem(DEFAULT_SYSTEMLEVEL)))
            .andExpect(jsonPath("$.[*].risklevel").value(hasItem(DEFAULT_RISKLEVEL.toString())))
            .andExpect(jsonPath("$.[*].limitationtime").value(hasItem(DEFAULT_LIMITATIONTIME)))
            .andExpect(jsonPath("$.[*].closetype").value(hasItem(DEFAULT_CLOSETYPE)));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllProjectRisksWithEagerRelationshipsIsEnabled() throws Exception {
        when(projectRiskServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restProjectRiskMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(projectRiskServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllProjectRisksWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(projectRiskServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restProjectRiskMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(projectRiskRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getProjectRisk() throws Exception {
        // Initialize the database
        insertedProjectRisk = projectRiskRepository.saveAndFlush(projectRisk);

        // Get the projectRisk
        restProjectRiskMockMvc
            .perform(get(ENTITY_API_URL_ID, projectRisk.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectRisk.getId()))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR.intValue()))
            .andExpect(jsonPath("$.nodename").value(DEFAULT_NODENAME))
            .andExpect(jsonPath("$.risktype").value(DEFAULT_RISKTYPE))
            .andExpect(jsonPath("$.decumentid").value(DEFAULT_DECUMENTID.intValue()))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.usetime").value(DEFAULT_USETIME.toString()))
            .andExpect(jsonPath("$.systemlevel").value(DEFAULT_SYSTEMLEVEL))
            .andExpect(jsonPath("$.risklevel").value(DEFAULT_RISKLEVEL.toString()))
            .andExpect(jsonPath("$.limitationtime").value(DEFAULT_LIMITATIONTIME))
            .andExpect(jsonPath("$.closetype").value(DEFAULT_CLOSETYPE));
    }

    @Test
    @Transactional
    void getNonExistingProjectRisk() throws Exception {
        // Get the projectRisk
        restProjectRiskMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProjectRisk() throws Exception {
        // Initialize the database
        insertedProjectRisk = projectRiskRepository.saveAndFlush(projectRisk);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectRisk
        ProjectRisk updatedProjectRisk = projectRiskRepository.findById(projectRisk.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProjectRisk are not directly saved in db
        em.detach(updatedProjectRisk);
        updatedProjectRisk
            .year(UPDATED_YEAR)
            .nodename(UPDATED_NODENAME)
            .risktype(UPDATED_RISKTYPE)
            .decumentid(UPDATED_DECUMENTID)
            .version(UPDATED_VERSION)
            .usetime(UPDATED_USETIME)
            .systemlevel(UPDATED_SYSTEMLEVEL)
            .risklevel(UPDATED_RISKLEVEL)
            .limitationtime(UPDATED_LIMITATIONTIME)
            .closetype(UPDATED_CLOSETYPE);

        restProjectRiskMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProjectRisk.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedProjectRisk))
            )
            .andExpect(status().isOk());

        // Validate the ProjectRisk in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProjectRiskToMatchAllProperties(updatedProjectRisk);
    }

    @Test
    @Transactional
    void putNonExistingProjectRisk() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectRisk.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectRiskMockMvc
            .perform(
                put(ENTITY_API_URL_ID, projectRisk.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(projectRisk))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectRisk in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProjectRisk() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectRisk.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectRiskMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(projectRisk))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectRisk in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProjectRisk() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectRisk.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectRiskMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectRisk)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProjectRisk in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProjectRiskWithPatch() throws Exception {
        // Initialize the database
        insertedProjectRisk = projectRiskRepository.saveAndFlush(projectRisk);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectRisk using partial update
        ProjectRisk partialUpdatedProjectRisk = new ProjectRisk();
        partialUpdatedProjectRisk.setId(projectRisk.getId());

        partialUpdatedProjectRisk
            .nodename(UPDATED_NODENAME)
            .risktype(UPDATED_RISKTYPE)
            .decumentid(UPDATED_DECUMENTID)
            .version(UPDATED_VERSION)
            .systemlevel(UPDATED_SYSTEMLEVEL)
            .closetype(UPDATED_CLOSETYPE);

        restProjectRiskMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectRisk.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectRisk))
            )
            .andExpect(status().isOk());

        // Validate the ProjectRisk in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectRiskUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProjectRisk, projectRisk),
            getPersistedProjectRisk(projectRisk)
        );
    }

    @Test
    @Transactional
    void fullUpdateProjectRiskWithPatch() throws Exception {
        // Initialize the database
        insertedProjectRisk = projectRiskRepository.saveAndFlush(projectRisk);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectRisk using partial update
        ProjectRisk partialUpdatedProjectRisk = new ProjectRisk();
        partialUpdatedProjectRisk.setId(projectRisk.getId());

        partialUpdatedProjectRisk
            .year(UPDATED_YEAR)
            .nodename(UPDATED_NODENAME)
            .risktype(UPDATED_RISKTYPE)
            .decumentid(UPDATED_DECUMENTID)
            .version(UPDATED_VERSION)
            .usetime(UPDATED_USETIME)
            .systemlevel(UPDATED_SYSTEMLEVEL)
            .risklevel(UPDATED_RISKLEVEL)
            .limitationtime(UPDATED_LIMITATIONTIME)
            .closetype(UPDATED_CLOSETYPE);

        restProjectRiskMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectRisk.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectRisk))
            )
            .andExpect(status().isOk());

        // Validate the ProjectRisk in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectRiskUpdatableFieldsEquals(partialUpdatedProjectRisk, getPersistedProjectRisk(partialUpdatedProjectRisk));
    }

    @Test
    @Transactional
    void patchNonExistingProjectRisk() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectRisk.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectRiskMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, projectRisk.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectRisk))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectRisk in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProjectRisk() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectRisk.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectRiskMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectRisk))
            )
            .andExpect(status().isBadRequest());

        // Validate the ProjectRisk in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProjectRisk() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectRisk.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectRiskMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(projectRisk)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ProjectRisk in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProjectRisk() throws Exception {
        // Initialize the database
        insertedProjectRisk = projectRiskRepository.saveAndFlush(projectRisk);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the projectRisk
        restProjectRiskMockMvc
            .perform(delete(ENTITY_API_URL_ID, projectRisk.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return projectRiskRepository.count();
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

    protected ProjectRisk getPersistedProjectRisk(ProjectRisk projectRisk) {
        return projectRiskRepository.findById(projectRisk.getId()).orElseThrow();
    }

    protected void assertPersistedProjectRiskToMatchAllProperties(ProjectRisk expectedProjectRisk) {
        assertProjectRiskAllPropertiesEquals(expectedProjectRisk, getPersistedProjectRisk(expectedProjectRisk));
    }

    protected void assertPersistedProjectRiskToMatchUpdatableProperties(ProjectRisk expectedProjectRisk) {
        assertProjectRiskAllUpdatablePropertiesEquals(expectedProjectRisk, getPersistedProjectRisk(expectedProjectRisk));
    }
}

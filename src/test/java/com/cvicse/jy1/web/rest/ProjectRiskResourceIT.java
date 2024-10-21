package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.ProjectRiskAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.ProjectRisk;
import com.cvicse.jy1.repository.ProjectRiskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
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
 * Integration tests for the {@link ProjectRiskResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProjectRiskResourceIT {

    private static final Long DEFAULT_YEAR = 1L;
    private static final Long UPDATED_YEAR = 2L;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_RISKCONTENT = "AAAAAAAAAA";
    private static final String UPDATED_RISKCONTENT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_IDENTIFICATIONTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_IDENTIFICATIONTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RISKREASON = "AAAAAAAAAA";
    private static final String UPDATED_RISKREASON = "BBBBBBBBBB";

    private static final String DEFAULT_IMPORTANTRANGE = "AAAAAAAAAA";
    private static final String UPDATED_IMPORTANTRANGE = "BBBBBBBBBB";

    private static final String DEFAULT_MEASURESANDTIMELIMIT = "AAAAAAAAAA";
    private static final String UPDATED_MEASURESANDTIMELIMIT = "BBBBBBBBBB";

    private static final String DEFAULT_CONDITIONS = "AAAAAAAAAA";
    private static final String UPDATED_CONDITIONS = "BBBBBBBBBB";

    private static final String DEFAULT_CLOSEDLOOPINDICATOR = "AAAAAAAAAA";
    private static final String UPDATED_CLOSEDLOOPINDICATOR = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/project-risks";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProjectRiskRepository projectRiskRepository;

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
            .name(DEFAULT_NAME)
            .riskcontent(DEFAULT_RISKCONTENT)
            .identificationtime(DEFAULT_IDENTIFICATIONTIME)
            .riskreason(DEFAULT_RISKREASON)
            .importantrange(DEFAULT_IMPORTANTRANGE)
            .measuresandtimelimit(DEFAULT_MEASURESANDTIMELIMIT)
            .conditions(DEFAULT_CONDITIONS)
            .closedloopindicator(DEFAULT_CLOSEDLOOPINDICATOR);
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
            .name(UPDATED_NAME)
            .riskcontent(UPDATED_RISKCONTENT)
            .identificationtime(UPDATED_IDENTIFICATIONTIME)
            .riskreason(UPDATED_RISKREASON)
            .importantrange(UPDATED_IMPORTANTRANGE)
            .measuresandtimelimit(UPDATED_MEASURESANDTIMELIMIT)
            .conditions(UPDATED_CONDITIONS)
            .closedloopindicator(UPDATED_CLOSEDLOOPINDICATOR);
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
        projectRisk.setId(1);

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
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectRisk.getId().intValue())))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR.intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].riskcontent").value(hasItem(DEFAULT_RISKCONTENT)))
            .andExpect(jsonPath("$.[*].identificationtime").value(hasItem(DEFAULT_IDENTIFICATIONTIME.toString())))
            .andExpect(jsonPath("$.[*].riskreason").value(hasItem(DEFAULT_RISKREASON)))
            .andExpect(jsonPath("$.[*].importantrange").value(hasItem(DEFAULT_IMPORTANTRANGE)))
            .andExpect(jsonPath("$.[*].measuresandtimelimit").value(hasItem(DEFAULT_MEASURESANDTIMELIMIT)))
            .andExpect(jsonPath("$.[*].conditions").value(hasItem(DEFAULT_CONDITIONS)))
            .andExpect(jsonPath("$.[*].closedloopindicator").value(hasItem(DEFAULT_CLOSEDLOOPINDICATOR)));
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
            .andExpect(jsonPath("$.id").value(projectRisk.getId().intValue()))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR.intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.riskcontent").value(DEFAULT_RISKCONTENT))
            .andExpect(jsonPath("$.identificationtime").value(DEFAULT_IDENTIFICATIONTIME.toString()))
            .andExpect(jsonPath("$.riskreason").value(DEFAULT_RISKREASON))
            .andExpect(jsonPath("$.importantrange").value(DEFAULT_IMPORTANTRANGE))
            .andExpect(jsonPath("$.measuresandtimelimit").value(DEFAULT_MEASURESANDTIMELIMIT))
            .andExpect(jsonPath("$.conditions").value(DEFAULT_CONDITIONS))
            .andExpect(jsonPath("$.closedloopindicator").value(DEFAULT_CLOSEDLOOPINDICATOR));
    }

    @Test
    @Transactional
    void getNonExistingProjectRisk() throws Exception {
        // Get the projectRisk
        restProjectRiskMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
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
            .name(UPDATED_NAME)
            .riskcontent(UPDATED_RISKCONTENT)
            .identificationtime(UPDATED_IDENTIFICATIONTIME)
            .riskreason(UPDATED_RISKREASON)
            .importantrange(UPDATED_IMPORTANTRANGE)
            .measuresandtimelimit(UPDATED_MEASURESANDTIMELIMIT)
            .conditions(UPDATED_CONDITIONS)
            .closedloopindicator(UPDATED_CLOSEDLOOPINDICATOR);

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
        projectRisk.setId(intCount.incrementAndGet());

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
        projectRisk.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectRiskMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
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
        projectRisk.setId(intCount.incrementAndGet());

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
            .name(UPDATED_NAME)
            .identificationtime(UPDATED_IDENTIFICATIONTIME)
            .riskreason(UPDATED_RISKREASON)
            .importantrange(UPDATED_IMPORTANTRANGE)
            .measuresandtimelimit(UPDATED_MEASURESANDTIMELIMIT);

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
            .name(UPDATED_NAME)
            .riskcontent(UPDATED_RISKCONTENT)
            .identificationtime(UPDATED_IDENTIFICATIONTIME)
            .riskreason(UPDATED_RISKREASON)
            .importantrange(UPDATED_IMPORTANTRANGE)
            .measuresandtimelimit(UPDATED_MEASURESANDTIMELIMIT)
            .conditions(UPDATED_CONDITIONS)
            .closedloopindicator(UPDATED_CLOSEDLOOPINDICATOR);

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
        projectRisk.setId(intCount.incrementAndGet());

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
        projectRisk.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectRiskMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
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
        projectRisk.setId(intCount.incrementAndGet());

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

package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.ProjectdeliverablesAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.Projectdeliverables;
import com.cvicse.jy1.repository.ProjectdeliverablesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link ProjectdeliverablesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProjectdeliverablesResourceIT {

    private static final String DEFAULT_PARENTCODE = "AAAAAAAAAA";
    private static final String UPDATED_PARENTCODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_SUBMIT = false;
    private static final Boolean UPDATED_IS_SUBMIT = true;

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/projectdeliverables";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProjectdeliverablesRepository projectdeliverablesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectdeliverablesMockMvc;

    private Projectdeliverables projectdeliverables;

    private Projectdeliverables insertedProjectdeliverables;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Projectdeliverables createEntity(EntityManager em) {
        Projectdeliverables projectdeliverables = new Projectdeliverables()
            .parentcode(DEFAULT_PARENTCODE)
            .isSubmit(DEFAULT_IS_SUBMIT)
            .status(DEFAULT_STATUS);
        return projectdeliverables;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Projectdeliverables createUpdatedEntity(EntityManager em) {
        Projectdeliverables projectdeliverables = new Projectdeliverables()
            .parentcode(UPDATED_PARENTCODE)
            .isSubmit(UPDATED_IS_SUBMIT)
            .status(UPDATED_STATUS);
        return projectdeliverables;
    }

    @BeforeEach
    public void initTest() {
        projectdeliverables = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedProjectdeliverables != null) {
            projectdeliverablesRepository.delete(insertedProjectdeliverables);
            insertedProjectdeliverables = null;
        }
    }

    @Test
    @Transactional
    void createProjectdeliverables() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Projectdeliverables
        var returnedProjectdeliverables = om.readValue(
            restProjectdeliverablesMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectdeliverables)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Projectdeliverables.class
        );

        // Validate the Projectdeliverables in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertProjectdeliverablesUpdatableFieldsEquals(
            returnedProjectdeliverables,
            getPersistedProjectdeliverables(returnedProjectdeliverables)
        );

        insertedProjectdeliverables = returnedProjectdeliverables;
    }

    @Test
    @Transactional
    void createProjectdeliverablesWithExistingId() throws Exception {
        // Create the Projectdeliverables with an existing ID
        projectdeliverables.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectdeliverablesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectdeliverables)))
            .andExpect(status().isBadRequest());

        // Validate the Projectdeliverables in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProjectdeliverables() throws Exception {
        // Initialize the database
        insertedProjectdeliverables = projectdeliverablesRepository.saveAndFlush(projectdeliverables);

        // Get all the projectdeliverablesList
        restProjectdeliverablesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectdeliverables.getId().intValue())))
            .andExpect(jsonPath("$.[*].parentcode").value(hasItem(DEFAULT_PARENTCODE)))
            .andExpect(jsonPath("$.[*].isSubmit").value(hasItem(DEFAULT_IS_SUBMIT.booleanValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)));
    }

    @Test
    @Transactional
    void getProjectdeliverables() throws Exception {
        // Initialize the database
        insertedProjectdeliverables = projectdeliverablesRepository.saveAndFlush(projectdeliverables);

        // Get the projectdeliverables
        restProjectdeliverablesMockMvc
            .perform(get(ENTITY_API_URL_ID, projectdeliverables.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectdeliverables.getId().intValue()))
            .andExpect(jsonPath("$.parentcode").value(DEFAULT_PARENTCODE))
            .andExpect(jsonPath("$.isSubmit").value(DEFAULT_IS_SUBMIT.booleanValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS));
    }

    @Test
    @Transactional
    void getNonExistingProjectdeliverables() throws Exception {
        // Get the projectdeliverables
        restProjectdeliverablesMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProjectdeliverables() throws Exception {
        // Initialize the database
        insertedProjectdeliverables = projectdeliverablesRepository.saveAndFlush(projectdeliverables);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectdeliverables
        Projectdeliverables updatedProjectdeliverables = projectdeliverablesRepository.findById(projectdeliverables.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProjectdeliverables are not directly saved in db
        em.detach(updatedProjectdeliverables);
        updatedProjectdeliverables.parentcode(UPDATED_PARENTCODE).isSubmit(UPDATED_IS_SUBMIT).status(UPDATED_STATUS);

        restProjectdeliverablesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProjectdeliverables.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedProjectdeliverables))
            )
            .andExpect(status().isOk());

        // Validate the Projectdeliverables in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProjectdeliverablesToMatchAllProperties(updatedProjectdeliverables);
    }

    @Test
    @Transactional
    void putNonExistingProjectdeliverables() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectdeliverables.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectdeliverablesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, projectdeliverables.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(projectdeliverables))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectdeliverables in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProjectdeliverables() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectdeliverables.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectdeliverablesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(projectdeliverables))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectdeliverables in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProjectdeliverables() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectdeliverables.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectdeliverablesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectdeliverables)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Projectdeliverables in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProjectdeliverablesWithPatch() throws Exception {
        // Initialize the database
        insertedProjectdeliverables = projectdeliverablesRepository.saveAndFlush(projectdeliverables);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectdeliverables using partial update
        Projectdeliverables partialUpdatedProjectdeliverables = new Projectdeliverables();
        partialUpdatedProjectdeliverables.setId(projectdeliverables.getId());

        partialUpdatedProjectdeliverables.parentcode(UPDATED_PARENTCODE).isSubmit(UPDATED_IS_SUBMIT).status(UPDATED_STATUS);

        restProjectdeliverablesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectdeliverables.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectdeliverables))
            )
            .andExpect(status().isOk());

        // Validate the Projectdeliverables in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectdeliverablesUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProjectdeliverables, projectdeliverables),
            getPersistedProjectdeliverables(projectdeliverables)
        );
    }

    @Test
    @Transactional
    void fullUpdateProjectdeliverablesWithPatch() throws Exception {
        // Initialize the database
        insertedProjectdeliverables = projectdeliverablesRepository.saveAndFlush(projectdeliverables);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectdeliverables using partial update
        Projectdeliverables partialUpdatedProjectdeliverables = new Projectdeliverables();
        partialUpdatedProjectdeliverables.setId(projectdeliverables.getId());

        partialUpdatedProjectdeliverables.parentcode(UPDATED_PARENTCODE).isSubmit(UPDATED_IS_SUBMIT).status(UPDATED_STATUS);

        restProjectdeliverablesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectdeliverables.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectdeliverables))
            )
            .andExpect(status().isOk());

        // Validate the Projectdeliverables in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectdeliverablesUpdatableFieldsEquals(
            partialUpdatedProjectdeliverables,
            getPersistedProjectdeliverables(partialUpdatedProjectdeliverables)
        );
    }

    @Test
    @Transactional
    void patchNonExistingProjectdeliverables() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectdeliverables.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectdeliverablesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, projectdeliverables.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectdeliverables))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectdeliverables in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProjectdeliverables() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectdeliverables.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectdeliverablesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectdeliverables))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectdeliverables in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProjectdeliverables() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectdeliverables.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectdeliverablesMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(projectdeliverables)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Projectdeliverables in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProjectdeliverables() throws Exception {
        // Initialize the database
        insertedProjectdeliverables = projectdeliverablesRepository.saveAndFlush(projectdeliverables);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the projectdeliverables
        restProjectdeliverablesMockMvc
            .perform(delete(ENTITY_API_URL_ID, projectdeliverables.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return projectdeliverablesRepository.count();
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

    protected Projectdeliverables getPersistedProjectdeliverables(Projectdeliverables projectdeliverables) {
        return projectdeliverablesRepository.findById(projectdeliverables.getId()).orElseThrow();
    }

    protected void assertPersistedProjectdeliverablesToMatchAllProperties(Projectdeliverables expectedProjectdeliverables) {
        assertProjectdeliverablesAllPropertiesEquals(
            expectedProjectdeliverables,
            getPersistedProjectdeliverables(expectedProjectdeliverables)
        );
    }

    protected void assertPersistedProjectdeliverablesToMatchUpdatableProperties(Projectdeliverables expectedProjectdeliverables) {
        assertProjectdeliverablesAllUpdatablePropertiesEquals(
            expectedProjectdeliverables,
            getPersistedProjectdeliverables(expectedProjectdeliverables)
        );
    }
}

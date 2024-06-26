package com.cvicse.web.rest;

import static com.cvicse.domain.ProjectwbsAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Projectwbs;
import com.cvicse.repository.ProjectwbsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link ProjectwbsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProjectwbsResourceIT {

    private static final String DEFAULT_PROJECTWBSNAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTWBSNAME = "BBBBBBBBBB";

    private static final String DEFAULT_WBSSPARE_1 = "AAAAAAAAAA";
    private static final String UPDATED_WBSSPARE_1 = "BBBBBBBBBB";

    private static final String DEFAULT_WBSSPARE_2 = "AAAAAAAAAA";
    private static final String UPDATED_WBSSPARE_2 = "BBBBBBBBBB";

    private static final String DEFAULT_WBSSPARE_3 = "AAAAAAAAAA";
    private static final String UPDATED_WBSSPARE_3 = "BBBBBBBBBB";

    private static final String DEFAULT_WBSSPARE_4 = "AAAAAAAAAA";
    private static final String UPDATED_WBSSPARE_4 = "BBBBBBBBBB";

    private static final String DEFAULT_WBSSPARE_5 = "AAAAAAAAAA";
    private static final String UPDATED_WBSSPARE_5 = "BBBBBBBBBB";

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

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Projectwbs createEntity(EntityManager em) {
        Projectwbs projectwbs = new Projectwbs()
            .projectwbsname(DEFAULT_PROJECTWBSNAME)
            .wbsspare1(DEFAULT_WBSSPARE_1)
            .wbsspare2(DEFAULT_WBSSPARE_2)
            .wbsspare3(DEFAULT_WBSSPARE_3)
            .wbsspare4(DEFAULT_WBSSPARE_4)
            .wbsspare5(DEFAULT_WBSSPARE_5);
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
            .projectwbsname(UPDATED_PROJECTWBSNAME)
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);
        return projectwbs;
    }

    @BeforeEach
    public void initTest() {
        projectwbs = createEntity(em);
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
        projectwbsRepository.saveAndFlush(projectwbs);

        // Get all the projectwbsList
        restProjectwbsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectwbs.getId())))
            .andExpect(jsonPath("$.[*].projectwbsname").value(hasItem(DEFAULT_PROJECTWBSNAME)))
            .andExpect(jsonPath("$.[*].wbsspare1").value(hasItem(DEFAULT_WBSSPARE_1)))
            .andExpect(jsonPath("$.[*].wbsspare2").value(hasItem(DEFAULT_WBSSPARE_2)))
            .andExpect(jsonPath("$.[*].wbsspare3").value(hasItem(DEFAULT_WBSSPARE_3)))
            .andExpect(jsonPath("$.[*].wbsspare4").value(hasItem(DEFAULT_WBSSPARE_4)))
            .andExpect(jsonPath("$.[*].wbsspare5").value(hasItem(DEFAULT_WBSSPARE_5)));
    }

    @Test
    @Transactional
    void getProjectwbs() throws Exception {
        // Initialize the database
        projectwbsRepository.saveAndFlush(projectwbs);

        // Get the projectwbs
        restProjectwbsMockMvc
            .perform(get(ENTITY_API_URL_ID, projectwbs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectwbs.getId()))
            .andExpect(jsonPath("$.projectwbsname").value(DEFAULT_PROJECTWBSNAME))
            .andExpect(jsonPath("$.wbsspare1").value(DEFAULT_WBSSPARE_1))
            .andExpect(jsonPath("$.wbsspare2").value(DEFAULT_WBSSPARE_2))
            .andExpect(jsonPath("$.wbsspare3").value(DEFAULT_WBSSPARE_3))
            .andExpect(jsonPath("$.wbsspare4").value(DEFAULT_WBSSPARE_4))
            .andExpect(jsonPath("$.wbsspare5").value(DEFAULT_WBSSPARE_5));
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
        projectwbsRepository.saveAndFlush(projectwbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectwbs
        Projectwbs updatedProjectwbs = projectwbsRepository.findById(projectwbs.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProjectwbs are not directly saved in db
        em.detach(updatedProjectwbs);
        updatedProjectwbs
            .projectwbsname(UPDATED_PROJECTWBSNAME)
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

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
        projectwbsRepository.saveAndFlush(projectwbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectwbs using partial update
        Projectwbs partialUpdatedProjectwbs = new Projectwbs();
        partialUpdatedProjectwbs.setId(projectwbs.getId());

        partialUpdatedProjectwbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare5(UPDATED_WBSSPARE_5);

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
        projectwbsRepository.saveAndFlush(projectwbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectwbs using partial update
        Projectwbs partialUpdatedProjectwbs = new Projectwbs();
        partialUpdatedProjectwbs.setId(projectwbs.getId());

        partialUpdatedProjectwbs
            .projectwbsname(UPDATED_PROJECTWBSNAME)
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

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
        projectwbsRepository.saveAndFlush(projectwbs);

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

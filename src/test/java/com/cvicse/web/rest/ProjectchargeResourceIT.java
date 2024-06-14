package com.cvicse.web.rest;

import static com.cvicse.domain.ProjectchargeAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Projectcharge;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.ProjectchargeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
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
 * Integration tests for the {@link ProjectchargeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ProjectchargeResourceIT {

    private static final String DEFAULT_PROJECTNAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_FORMID = "AAAAAAAAAA";
    private static final String UPDATED_FORMID = "BBBBBBBBBB";

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final String DEFAULT_REQUESTDEPORTMENT = "AAAAAAAAAA";
    private static final String UPDATED_REQUESTDEPORTMENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_CHARGETYPE = 1;
    private static final Integer UPDATED_CHARGETYPE = 2;

    private static final String DEFAULT_CHARGECONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CHARGECONTENT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/projectcharges";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ProjectchargeRepository projectchargeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restProjectchargeMockMvc;

    private Projectcharge projectcharge;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Projectcharge createEntity(EntityManager em) {
        Projectcharge projectcharge = new Projectcharge()
            .projectname(DEFAULT_PROJECTNAME)
            .formid(DEFAULT_FORMID)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .requestdeportment(DEFAULT_REQUESTDEPORTMENT)
            .chargetype(DEFAULT_CHARGETYPE)
            .chargecontent(DEFAULT_CHARGECONTENT);
        return projectcharge;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Projectcharge createUpdatedEntity(EntityManager em) {
        Projectcharge projectcharge = new Projectcharge()
            .projectname(UPDATED_PROJECTNAME)
            .formid(UPDATED_FORMID)
            .secretlevel(UPDATED_SECRETLEVEL)
            .requestdeportment(UPDATED_REQUESTDEPORTMENT)
            .chargetype(UPDATED_CHARGETYPE)
            .chargecontent(UPDATED_CHARGECONTENT);
        return projectcharge;
    }

    @BeforeEach
    public void initTest() {
        projectcharge = createEntity(em);
    }

    @Test
    @Transactional
    void createProjectcharge() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Projectcharge
        var returnedProjectcharge = om.readValue(
            restProjectchargeMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectcharge)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Projectcharge.class
        );

        // Validate the Projectcharge in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertProjectchargeUpdatableFieldsEquals(returnedProjectcharge, getPersistedProjectcharge(returnedProjectcharge));
    }

    @Test
    @Transactional
    void createProjectchargeWithExistingId() throws Exception {
        // Create the Projectcharge with an existing ID
        projectcharge.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectchargeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectcharge)))
            .andExpect(status().isBadRequest());

        // Validate the Projectcharge in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllProjectcharges() throws Exception {
        // Initialize the database
        projectchargeRepository.saveAndFlush(projectcharge);

        // Get all the projectchargeList
        restProjectchargeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(projectcharge.getId().intValue())))
            .andExpect(jsonPath("$.[*].projectname").value(hasItem(DEFAULT_PROJECTNAME)))
            .andExpect(jsonPath("$.[*].formid").value(hasItem(DEFAULT_FORMID)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].requestdeportment").value(hasItem(DEFAULT_REQUESTDEPORTMENT)))
            .andExpect(jsonPath("$.[*].chargetype").value(hasItem(DEFAULT_CHARGETYPE)))
            .andExpect(jsonPath("$.[*].chargecontent").value(hasItem(DEFAULT_CHARGECONTENT)));
    }

    @Test
    @Transactional
    void getProjectcharge() throws Exception {
        // Initialize the database
        projectchargeRepository.saveAndFlush(projectcharge);

        // Get the projectcharge
        restProjectchargeMockMvc
            .perform(get(ENTITY_API_URL_ID, projectcharge.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(projectcharge.getId().intValue()))
            .andExpect(jsonPath("$.projectname").value(DEFAULT_PROJECTNAME))
            .andExpect(jsonPath("$.formid").value(DEFAULT_FORMID))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.requestdeportment").value(DEFAULT_REQUESTDEPORTMENT))
            .andExpect(jsonPath("$.chargetype").value(DEFAULT_CHARGETYPE))
            .andExpect(jsonPath("$.chargecontent").value(DEFAULT_CHARGECONTENT));
    }

    @Test
    @Transactional
    void getNonExistingProjectcharge() throws Exception {
        // Get the projectcharge
        restProjectchargeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingProjectcharge() throws Exception {
        // Initialize the database
        projectchargeRepository.saveAndFlush(projectcharge);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectcharge
        Projectcharge updatedProjectcharge = projectchargeRepository.findById(projectcharge.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedProjectcharge are not directly saved in db
        em.detach(updatedProjectcharge);
        updatedProjectcharge
            .projectname(UPDATED_PROJECTNAME)
            .formid(UPDATED_FORMID)
            .secretlevel(UPDATED_SECRETLEVEL)
            .requestdeportment(UPDATED_REQUESTDEPORTMENT)
            .chargetype(UPDATED_CHARGETYPE)
            .chargecontent(UPDATED_CHARGECONTENT);

        restProjectchargeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedProjectcharge.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedProjectcharge))
            )
            .andExpect(status().isOk());

        // Validate the Projectcharge in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedProjectchargeToMatchAllProperties(updatedProjectcharge);
    }

    @Test
    @Transactional
    void putNonExistingProjectcharge() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectcharge.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectchargeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, projectcharge.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(projectcharge))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectcharge in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchProjectcharge() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectcharge.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectchargeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(projectcharge))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectcharge in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamProjectcharge() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectcharge.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectchargeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(projectcharge)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Projectcharge in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateProjectchargeWithPatch() throws Exception {
        // Initialize the database
        projectchargeRepository.saveAndFlush(projectcharge);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectcharge using partial update
        Projectcharge partialUpdatedProjectcharge = new Projectcharge();
        partialUpdatedProjectcharge.setId(projectcharge.getId());

        partialUpdatedProjectcharge.requestdeportment(UPDATED_REQUESTDEPORTMENT).chargecontent(UPDATED_CHARGECONTENT);

        restProjectchargeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectcharge.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectcharge))
            )
            .andExpect(status().isOk());

        // Validate the Projectcharge in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectchargeUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedProjectcharge, projectcharge),
            getPersistedProjectcharge(projectcharge)
        );
    }

    @Test
    @Transactional
    void fullUpdateProjectchargeWithPatch() throws Exception {
        // Initialize the database
        projectchargeRepository.saveAndFlush(projectcharge);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the projectcharge using partial update
        Projectcharge partialUpdatedProjectcharge = new Projectcharge();
        partialUpdatedProjectcharge.setId(projectcharge.getId());

        partialUpdatedProjectcharge
            .projectname(UPDATED_PROJECTNAME)
            .formid(UPDATED_FORMID)
            .secretlevel(UPDATED_SECRETLEVEL)
            .requestdeportment(UPDATED_REQUESTDEPORTMENT)
            .chargetype(UPDATED_CHARGETYPE)
            .chargecontent(UPDATED_CHARGECONTENT);

        restProjectchargeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedProjectcharge.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedProjectcharge))
            )
            .andExpect(status().isOk());

        // Validate the Projectcharge in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertProjectchargeUpdatableFieldsEquals(partialUpdatedProjectcharge, getPersistedProjectcharge(partialUpdatedProjectcharge));
    }

    @Test
    @Transactional
    void patchNonExistingProjectcharge() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectcharge.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProjectchargeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, projectcharge.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectcharge))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectcharge in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchProjectcharge() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectcharge.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectchargeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(projectcharge))
            )
            .andExpect(status().isBadRequest());

        // Validate the Projectcharge in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamProjectcharge() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        projectcharge.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restProjectchargeMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(projectcharge)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Projectcharge in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteProjectcharge() throws Exception {
        // Initialize the database
        projectchargeRepository.saveAndFlush(projectcharge);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the projectcharge
        restProjectchargeMockMvc
            .perform(delete(ENTITY_API_URL_ID, projectcharge.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return projectchargeRepository.count();
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

    protected Projectcharge getPersistedProjectcharge(Projectcharge projectcharge) {
        return projectchargeRepository.findById(projectcharge.getId()).orElseThrow();
    }

    protected void assertPersistedProjectchargeToMatchAllProperties(Projectcharge expectedProjectcharge) {
        assertProjectchargeAllPropertiesEquals(expectedProjectcharge, getPersistedProjectcharge(expectedProjectcharge));
    }

    protected void assertPersistedProjectchargeToMatchUpdatableProperties(Projectcharge expectedProjectcharge) {
        assertProjectchargeAllUpdatablePropertiesEquals(expectedProjectcharge, getPersistedProjectcharge(expectedProjectcharge));
    }
}

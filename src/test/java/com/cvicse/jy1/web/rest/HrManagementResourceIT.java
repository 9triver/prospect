package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.HrManagementAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.HrManagement;
import com.cvicse.jy1.repository.HrManagementRepository;
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
 * Integration tests for the {@link HrManagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HrManagementResourceIT {

    private static final String DEFAULT_OFFICERSID = "AAAAAAAAAA";
    private static final String UPDATED_OFFICERSID = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICERSNAME = "AAAAAAAAAA";
    private static final String UPDATED_OFFICERSNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_PROJECTID = 1;
    private static final Integer UPDATED_PROJECTID = 2;

    private static final String DEFAULT_PROJECTNAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECTROLE = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTROLE = "BBBBBBBBBB";

    private static final String DEFAULT_JOBGRADE = "AAAAAAAAAA";
    private static final String UPDATED_JOBGRADE = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENTID = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENTID = "BBBBBBBBBB";

    private static final String DEFAULT_DEPARTMENTNAME = "AAAAAAAAAA";
    private static final String UPDATED_DEPARTMENTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_FRONTLINEID = "AAAAAAAAAA";
    private static final String UPDATED_FRONTLINEID = "BBBBBBBBBB";

    private static final String DEFAULT_FRONTLINENAME = "AAAAAAAAAA";
    private static final String UPDATED_FRONTLINENAME = "BBBBBBBBBB";

    private static final String DEFAULT_JOBDUTY = "AAAAAAAAAA";
    private static final String UPDATED_JOBDUTY = "BBBBBBBBBB";

    private static final Integer DEFAULT_ANNUALWORKTIME = 1;
    private static final Integer UPDATED_ANNUALWORKTIME = 2;

    private static final String DEFAULT_ANNUALTASKTARGET = "AAAAAAAAAA";
    private static final String UPDATED_ANNUALTASKTARGET = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/hr-managements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private HrManagementRepository hrManagementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHrManagementMockMvc;

    private HrManagement hrManagement;

    private HrManagement insertedHrManagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HrManagement createEntity(EntityManager em) {
        HrManagement hrManagement = new HrManagement()
            .officersid(DEFAULT_OFFICERSID)
            .officersname(DEFAULT_OFFICERSNAME)
            .projectid(DEFAULT_PROJECTID)
            .projectname(DEFAULT_PROJECTNAME)
            .projectrole(DEFAULT_PROJECTROLE)
            .jobgrade(DEFAULT_JOBGRADE)
            .departmentid(DEFAULT_DEPARTMENTID)
            .departmentname(DEFAULT_DEPARTMENTNAME)
            .frontlineid(DEFAULT_FRONTLINEID)
            .frontlinename(DEFAULT_FRONTLINENAME)
            .jobduty(DEFAULT_JOBDUTY)
            .annualworktime(DEFAULT_ANNUALWORKTIME)
            .annualtasktarget(DEFAULT_ANNUALTASKTARGET);
        return hrManagement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HrManagement createUpdatedEntity(EntityManager em) {
        HrManagement hrManagement = new HrManagement()
            .officersid(UPDATED_OFFICERSID)
            .officersname(UPDATED_OFFICERSNAME)
            .projectid(UPDATED_PROJECTID)
            .projectname(UPDATED_PROJECTNAME)
            .projectrole(UPDATED_PROJECTROLE)
            .jobgrade(UPDATED_JOBGRADE)
            .departmentid(UPDATED_DEPARTMENTID)
            .departmentname(UPDATED_DEPARTMENTNAME)
            .frontlineid(UPDATED_FRONTLINEID)
            .frontlinename(UPDATED_FRONTLINENAME)
            .jobduty(UPDATED_JOBDUTY)
            .annualworktime(UPDATED_ANNUALWORKTIME)
            .annualtasktarget(UPDATED_ANNUALTASKTARGET);
        return hrManagement;
    }

    @BeforeEach
    public void initTest() {
        hrManagement = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedHrManagement != null) {
            hrManagementRepository.delete(insertedHrManagement);
            insertedHrManagement = null;
        }
    }

    @Test
    @Transactional
    void createHrManagement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the HrManagement
        var returnedHrManagement = om.readValue(
            restHrManagementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hrManagement)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            HrManagement.class
        );

        // Validate the HrManagement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertHrManagementUpdatableFieldsEquals(returnedHrManagement, getPersistedHrManagement(returnedHrManagement));

        insertedHrManagement = returnedHrManagement;
    }

    @Test
    @Transactional
    void createHrManagementWithExistingId() throws Exception {
        // Create the HrManagement with an existing ID
        hrManagement.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHrManagementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hrManagement)))
            .andExpect(status().isBadRequest());

        // Validate the HrManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllHrManagements() throws Exception {
        // Initialize the database
        insertedHrManagement = hrManagementRepository.saveAndFlush(hrManagement);

        // Get all the hrManagementList
        restHrManagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hrManagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].officersid").value(hasItem(DEFAULT_OFFICERSID)))
            .andExpect(jsonPath("$.[*].officersname").value(hasItem(DEFAULT_OFFICERSNAME)))
            .andExpect(jsonPath("$.[*].projectid").value(hasItem(DEFAULT_PROJECTID)))
            .andExpect(jsonPath("$.[*].projectname").value(hasItem(DEFAULT_PROJECTNAME)))
            .andExpect(jsonPath("$.[*].projectrole").value(hasItem(DEFAULT_PROJECTROLE)))
            .andExpect(jsonPath("$.[*].jobgrade").value(hasItem(DEFAULT_JOBGRADE)))
            .andExpect(jsonPath("$.[*].departmentid").value(hasItem(DEFAULT_DEPARTMENTID)))
            .andExpect(jsonPath("$.[*].departmentname").value(hasItem(DEFAULT_DEPARTMENTNAME)))
            .andExpect(jsonPath("$.[*].frontlineid").value(hasItem(DEFAULT_FRONTLINEID)))
            .andExpect(jsonPath("$.[*].frontlinename").value(hasItem(DEFAULT_FRONTLINENAME)))
            .andExpect(jsonPath("$.[*].jobduty").value(hasItem(DEFAULT_JOBDUTY)))
            .andExpect(jsonPath("$.[*].annualworktime").value(hasItem(DEFAULT_ANNUALWORKTIME)))
            .andExpect(jsonPath("$.[*].annualtasktarget").value(hasItem(DEFAULT_ANNUALTASKTARGET)));
    }

    @Test
    @Transactional
    void getHrManagement() throws Exception {
        // Initialize the database
        insertedHrManagement = hrManagementRepository.saveAndFlush(hrManagement);

        // Get the hrManagement
        restHrManagementMockMvc
            .perform(get(ENTITY_API_URL_ID, hrManagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hrManagement.getId().intValue()))
            .andExpect(jsonPath("$.officersid").value(DEFAULT_OFFICERSID))
            .andExpect(jsonPath("$.officersname").value(DEFAULT_OFFICERSNAME))
            .andExpect(jsonPath("$.projectid").value(DEFAULT_PROJECTID))
            .andExpect(jsonPath("$.projectname").value(DEFAULT_PROJECTNAME))
            .andExpect(jsonPath("$.projectrole").value(DEFAULT_PROJECTROLE))
            .andExpect(jsonPath("$.jobgrade").value(DEFAULT_JOBGRADE))
            .andExpect(jsonPath("$.departmentid").value(DEFAULT_DEPARTMENTID))
            .andExpect(jsonPath("$.departmentname").value(DEFAULT_DEPARTMENTNAME))
            .andExpect(jsonPath("$.frontlineid").value(DEFAULT_FRONTLINEID))
            .andExpect(jsonPath("$.frontlinename").value(DEFAULT_FRONTLINENAME))
            .andExpect(jsonPath("$.jobduty").value(DEFAULT_JOBDUTY))
            .andExpect(jsonPath("$.annualworktime").value(DEFAULT_ANNUALWORKTIME))
            .andExpect(jsonPath("$.annualtasktarget").value(DEFAULT_ANNUALTASKTARGET));
    }

    @Test
    @Transactional
    void getNonExistingHrManagement() throws Exception {
        // Get the hrManagement
        restHrManagementMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingHrManagement() throws Exception {
        // Initialize the database
        insertedHrManagement = hrManagementRepository.saveAndFlush(hrManagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hrManagement
        HrManagement updatedHrManagement = hrManagementRepository.findById(hrManagement.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedHrManagement are not directly saved in db
        em.detach(updatedHrManagement);
        updatedHrManagement
            .officersid(UPDATED_OFFICERSID)
            .officersname(UPDATED_OFFICERSNAME)
            .projectid(UPDATED_PROJECTID)
            .projectname(UPDATED_PROJECTNAME)
            .projectrole(UPDATED_PROJECTROLE)
            .jobgrade(UPDATED_JOBGRADE)
            .departmentid(UPDATED_DEPARTMENTID)
            .departmentname(UPDATED_DEPARTMENTNAME)
            .frontlineid(UPDATED_FRONTLINEID)
            .frontlinename(UPDATED_FRONTLINENAME)
            .jobduty(UPDATED_JOBDUTY)
            .annualworktime(UPDATED_ANNUALWORKTIME)
            .annualtasktarget(UPDATED_ANNUALTASKTARGET);

        restHrManagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedHrManagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedHrManagement))
            )
            .andExpect(status().isOk());

        // Validate the HrManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedHrManagementToMatchAllProperties(updatedHrManagement);
    }

    @Test
    @Transactional
    void putNonExistingHrManagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hrManagement.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHrManagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, hrManagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hrManagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the HrManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHrManagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hrManagement.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHrManagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hrManagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the HrManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHrManagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hrManagement.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHrManagementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hrManagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the HrManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHrManagementWithPatch() throws Exception {
        // Initialize the database
        insertedHrManagement = hrManagementRepository.saveAndFlush(hrManagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hrManagement using partial update
        HrManagement partialUpdatedHrManagement = new HrManagement();
        partialUpdatedHrManagement.setId(hrManagement.getId());

        partialUpdatedHrManagement
            .officersid(UPDATED_OFFICERSID)
            .projectid(UPDATED_PROJECTID)
            .projectrole(UPDATED_PROJECTROLE)
            .departmentid(UPDATED_DEPARTMENTID)
            .departmentname(UPDATED_DEPARTMENTNAME)
            .frontlinename(UPDATED_FRONTLINENAME)
            .annualtasktarget(UPDATED_ANNUALTASKTARGET);

        restHrManagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHrManagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHrManagement))
            )
            .andExpect(status().isOk());

        // Validate the HrManagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHrManagementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedHrManagement, hrManagement),
            getPersistedHrManagement(hrManagement)
        );
    }

    @Test
    @Transactional
    void fullUpdateHrManagementWithPatch() throws Exception {
        // Initialize the database
        insertedHrManagement = hrManagementRepository.saveAndFlush(hrManagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hrManagement using partial update
        HrManagement partialUpdatedHrManagement = new HrManagement();
        partialUpdatedHrManagement.setId(hrManagement.getId());

        partialUpdatedHrManagement
            .officersid(UPDATED_OFFICERSID)
            .officersname(UPDATED_OFFICERSNAME)
            .projectid(UPDATED_PROJECTID)
            .projectname(UPDATED_PROJECTNAME)
            .projectrole(UPDATED_PROJECTROLE)
            .jobgrade(UPDATED_JOBGRADE)
            .departmentid(UPDATED_DEPARTMENTID)
            .departmentname(UPDATED_DEPARTMENTNAME)
            .frontlineid(UPDATED_FRONTLINEID)
            .frontlinename(UPDATED_FRONTLINENAME)
            .jobduty(UPDATED_JOBDUTY)
            .annualworktime(UPDATED_ANNUALWORKTIME)
            .annualtasktarget(UPDATED_ANNUALTASKTARGET);

        restHrManagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHrManagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHrManagement))
            )
            .andExpect(status().isOk());

        // Validate the HrManagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHrManagementUpdatableFieldsEquals(partialUpdatedHrManagement, getPersistedHrManagement(partialUpdatedHrManagement));
    }

    @Test
    @Transactional
    void patchNonExistingHrManagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hrManagement.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHrManagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, hrManagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(hrManagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the HrManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHrManagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hrManagement.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHrManagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(hrManagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the HrManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHrManagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hrManagement.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHrManagementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(hrManagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the HrManagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHrManagement() throws Exception {
        // Initialize the database
        insertedHrManagement = hrManagementRepository.saveAndFlush(hrManagement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the hrManagement
        restHrManagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, hrManagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return hrManagementRepository.count();
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

    protected HrManagement getPersistedHrManagement(HrManagement hrManagement) {
        return hrManagementRepository.findById(hrManagement.getId()).orElseThrow();
    }

    protected void assertPersistedHrManagementToMatchAllProperties(HrManagement expectedHrManagement) {
        assertHrManagementAllPropertiesEquals(expectedHrManagement, getPersistedHrManagement(expectedHrManagement));
    }

    protected void assertPersistedHrManagementToMatchUpdatableProperties(HrManagement expectedHrManagement) {
        assertHrManagementAllUpdatablePropertiesEquals(expectedHrManagement, getPersistedHrManagement(expectedHrManagement));
    }
}

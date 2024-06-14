package com.cvicse.web.rest;

import static com.cvicse.domain.ResourcemanagementAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Resourcemanagement;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.ResourcemanagementRepository;
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
 * Integration tests for the {@link ResourcemanagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResourcemanagementResourceIT {

    private static final Long DEFAULT_RESOURCEID = 1L;
    private static final Long UPDATED_RESOURCEID = 2L;

    private static final String DEFAULT_PROJECTNAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CLIENTNAME = "AAAAAAAAAA";
    private static final String UPDATED_CLIENTNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_PLANDATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_PLANDATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATORNAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATORNAME = "BBBBBBBBBB";

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/resourcemanagements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ResourcemanagementRepository resourcemanagementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResourcemanagementMockMvc;

    private Resourcemanagement resourcemanagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Resourcemanagement createEntity(EntityManager em) {
        Resourcemanagement resourcemanagement = new Resourcemanagement()
            .resourceid(DEFAULT_RESOURCEID)
            .projectname(DEFAULT_PROJECTNAME)
            .clientname(DEFAULT_CLIENTNAME)
            .plandate(DEFAULT_PLANDATE)
            .creatorname(DEFAULT_CREATORNAME)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return resourcemanagement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Resourcemanagement createUpdatedEntity(EntityManager em) {
        Resourcemanagement resourcemanagement = new Resourcemanagement()
            .resourceid(UPDATED_RESOURCEID)
            .projectname(UPDATED_PROJECTNAME)
            .clientname(UPDATED_CLIENTNAME)
            .plandate(UPDATED_PLANDATE)
            .creatorname(UPDATED_CREATORNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return resourcemanagement;
    }

    @BeforeEach
    public void initTest() {
        resourcemanagement = createEntity(em);
    }

    @Test
    @Transactional
    void createResourcemanagement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Resourcemanagement
        var returnedResourcemanagement = om.readValue(
            restResourcemanagementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resourcemanagement)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Resourcemanagement.class
        );

        // Validate the Resourcemanagement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertResourcemanagementUpdatableFieldsEquals(
            returnedResourcemanagement,
            getPersistedResourcemanagement(returnedResourcemanagement)
        );
    }

    @Test
    @Transactional
    void createResourcemanagementWithExistingId() throws Exception {
        // Create the Resourcemanagement with an existing ID
        resourcemanagement.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restResourcemanagementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resourcemanagement)))
            .andExpect(status().isBadRequest());

        // Validate the Resourcemanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllResourcemanagements() throws Exception {
        // Initialize the database
        resourcemanagementRepository.saveAndFlush(resourcemanagement);

        // Get all the resourcemanagementList
        restResourcemanagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resourcemanagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].resourceid").value(hasItem(DEFAULT_RESOURCEID.intValue())))
            .andExpect(jsonPath("$.[*].projectname").value(hasItem(DEFAULT_PROJECTNAME)))
            .andExpect(jsonPath("$.[*].clientname").value(hasItem(DEFAULT_CLIENTNAME)))
            .andExpect(jsonPath("$.[*].plandate").value(hasItem(DEFAULT_PLANDATE.toString())))
            .andExpect(jsonPath("$.[*].creatorname").value(hasItem(DEFAULT_CREATORNAME)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getResourcemanagement() throws Exception {
        // Initialize the database
        resourcemanagementRepository.saveAndFlush(resourcemanagement);

        // Get the resourcemanagement
        restResourcemanagementMockMvc
            .perform(get(ENTITY_API_URL_ID, resourcemanagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resourcemanagement.getId().intValue()))
            .andExpect(jsonPath("$.resourceid").value(DEFAULT_RESOURCEID.intValue()))
            .andExpect(jsonPath("$.projectname").value(DEFAULT_PROJECTNAME))
            .andExpect(jsonPath("$.clientname").value(DEFAULT_CLIENTNAME))
            .andExpect(jsonPath("$.plandate").value(DEFAULT_PLANDATE.toString()))
            .andExpect(jsonPath("$.creatorname").value(DEFAULT_CREATORNAME))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingResourcemanagement() throws Exception {
        // Get the resourcemanagement
        restResourcemanagementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingResourcemanagement() throws Exception {
        // Initialize the database
        resourcemanagementRepository.saveAndFlush(resourcemanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resourcemanagement
        Resourcemanagement updatedResourcemanagement = resourcemanagementRepository.findById(resourcemanagement.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedResourcemanagement are not directly saved in db
        em.detach(updatedResourcemanagement);
        updatedResourcemanagement
            .resourceid(UPDATED_RESOURCEID)
            .projectname(UPDATED_PROJECTNAME)
            .clientname(UPDATED_CLIENTNAME)
            .plandate(UPDATED_PLANDATE)
            .creatorname(UPDATED_CREATORNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restResourcemanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedResourcemanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedResourcemanagement))
            )
            .andExpect(status().isOk());

        // Validate the Resourcemanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedResourcemanagementToMatchAllProperties(updatedResourcemanagement);
    }

    @Test
    @Transactional
    void putNonExistingResourcemanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resourcemanagement.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourcemanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, resourcemanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(resourcemanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Resourcemanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchResourcemanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resourcemanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourcemanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(resourcemanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Resourcemanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamResourcemanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resourcemanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourcemanagementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(resourcemanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Resourcemanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateResourcemanagementWithPatch() throws Exception {
        // Initialize the database
        resourcemanagementRepository.saveAndFlush(resourcemanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resourcemanagement using partial update
        Resourcemanagement partialUpdatedResourcemanagement = new Resourcemanagement();
        partialUpdatedResourcemanagement.setId(resourcemanagement.getId());

        partialUpdatedResourcemanagement
            .resourceid(UPDATED_RESOURCEID)
            .projectname(UPDATED_PROJECTNAME)
            .plandate(UPDATED_PLANDATE)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restResourcemanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResourcemanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedResourcemanagement))
            )
            .andExpect(status().isOk());

        // Validate the Resourcemanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertResourcemanagementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedResourcemanagement, resourcemanagement),
            getPersistedResourcemanagement(resourcemanagement)
        );
    }

    @Test
    @Transactional
    void fullUpdateResourcemanagementWithPatch() throws Exception {
        // Initialize the database
        resourcemanagementRepository.saveAndFlush(resourcemanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the resourcemanagement using partial update
        Resourcemanagement partialUpdatedResourcemanagement = new Resourcemanagement();
        partialUpdatedResourcemanagement.setId(resourcemanagement.getId());

        partialUpdatedResourcemanagement
            .resourceid(UPDATED_RESOURCEID)
            .projectname(UPDATED_PROJECTNAME)
            .clientname(UPDATED_CLIENTNAME)
            .plandate(UPDATED_PLANDATE)
            .creatorname(UPDATED_CREATORNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restResourcemanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedResourcemanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedResourcemanagement))
            )
            .andExpect(status().isOk());

        // Validate the Resourcemanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertResourcemanagementUpdatableFieldsEquals(
            partialUpdatedResourcemanagement,
            getPersistedResourcemanagement(partialUpdatedResourcemanagement)
        );
    }

    @Test
    @Transactional
    void patchNonExistingResourcemanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resourcemanagement.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restResourcemanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, resourcemanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(resourcemanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Resourcemanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchResourcemanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resourcemanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourcemanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(resourcemanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Resourcemanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamResourcemanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        resourcemanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restResourcemanagementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(resourcemanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Resourcemanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteResourcemanagement() throws Exception {
        // Initialize the database
        resourcemanagementRepository.saveAndFlush(resourcemanagement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the resourcemanagement
        restResourcemanagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, resourcemanagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return resourcemanagementRepository.count();
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

    protected Resourcemanagement getPersistedResourcemanagement(Resourcemanagement resourcemanagement) {
        return resourcemanagementRepository.findById(resourcemanagement.getId()).orElseThrow();
    }

    protected void assertPersistedResourcemanagementToMatchAllProperties(Resourcemanagement expectedResourcemanagement) {
        assertResourcemanagementAllPropertiesEquals(expectedResourcemanagement, getPersistedResourcemanagement(expectedResourcemanagement));
    }

    protected void assertPersistedResourcemanagementToMatchUpdatableProperties(Resourcemanagement expectedResourcemanagement) {
        assertResourcemanagementAllUpdatablePropertiesEquals(
            expectedResourcemanagement,
            getPersistedResourcemanagement(expectedResourcemanagement)
        );
    }
}

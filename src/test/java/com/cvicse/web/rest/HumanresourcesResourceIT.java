package com.cvicse.web.rest;

import static com.cvicse.domain.HumanresourcesAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Humanresources;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.HumanresourcesRepository;
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
 * Integration tests for the {@link HumanresourcesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HumanresourcesResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OUTDEPORTMENT = "AAAAAAAAAA";
    private static final String UPDATED_OUTDEPORTMENT = "BBBBBBBBBB";

    private static final String DEFAULT_INDEPORTMENT = "AAAAAAAAAA";
    private static final String UPDATED_INDEPORTMENT = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ADJUSTTIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ADJUSTTIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_PROJECTNAME = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_DEPORTMENT = "AAAAAAAAAA";
    private static final String UPDATED_DEPORTMENT = "BBBBBBBBBB";

    private static final String DEFAULT_PROJECTLEADER = "AAAAAAAAAA";
    private static final String UPDATED_PROJECTLEADER = "BBBBBBBBBB";

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/humanresources";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private HumanresourcesRepository humanresourcesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHumanresourcesMockMvc;

    private Humanresources humanresources;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Humanresources createEntity(EntityManager em) {
        Humanresources humanresources = new Humanresources()
            .name(DEFAULT_NAME)
            .outdeportment(DEFAULT_OUTDEPORTMENT)
            .indeportment(DEFAULT_INDEPORTMENT)
            .adjusttime(DEFAULT_ADJUSTTIME)
            .projectname(DEFAULT_PROJECTNAME)
            .deportment(DEFAULT_DEPORTMENT)
            .projectleader(DEFAULT_PROJECTLEADER)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return humanresources;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Humanresources createUpdatedEntity(EntityManager em) {
        Humanresources humanresources = new Humanresources()
            .name(UPDATED_NAME)
            .outdeportment(UPDATED_OUTDEPORTMENT)
            .indeportment(UPDATED_INDEPORTMENT)
            .adjusttime(UPDATED_ADJUSTTIME)
            .projectname(UPDATED_PROJECTNAME)
            .deportment(UPDATED_DEPORTMENT)
            .projectleader(UPDATED_PROJECTLEADER)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return humanresources;
    }

    @BeforeEach
    public void initTest() {
        humanresources = createEntity(em);
    }

    @Test
    @Transactional
    void createHumanresources() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Humanresources
        var returnedHumanresources = om.readValue(
            restHumanresourcesMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(humanresources)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Humanresources.class
        );

        // Validate the Humanresources in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertHumanresourcesUpdatableFieldsEquals(returnedHumanresources, getPersistedHumanresources(returnedHumanresources));
    }

    @Test
    @Transactional
    void createHumanresourcesWithExistingId() throws Exception {
        // Create the Humanresources with an existing ID
        humanresources.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHumanresourcesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(humanresources)))
            .andExpect(status().isBadRequest());

        // Validate the Humanresources in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllHumanresources() throws Exception {
        // Initialize the database
        humanresourcesRepository.saveAndFlush(humanresources);

        // Get all the humanresourcesList
        restHumanresourcesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(humanresources.getId())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].outdeportment").value(hasItem(DEFAULT_OUTDEPORTMENT)))
            .andExpect(jsonPath("$.[*].indeportment").value(hasItem(DEFAULT_INDEPORTMENT)))
            .andExpect(jsonPath("$.[*].adjusttime").value(hasItem(DEFAULT_ADJUSTTIME.toString())))
            .andExpect(jsonPath("$.[*].projectname").value(hasItem(DEFAULT_PROJECTNAME)))
            .andExpect(jsonPath("$.[*].deportment").value(hasItem(DEFAULT_DEPORTMENT)))
            .andExpect(jsonPath("$.[*].projectleader").value(hasItem(DEFAULT_PROJECTLEADER)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getHumanresources() throws Exception {
        // Initialize the database
        humanresourcesRepository.saveAndFlush(humanresources);

        // Get the humanresources
        restHumanresourcesMockMvc
            .perform(get(ENTITY_API_URL_ID, humanresources.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(humanresources.getId()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.outdeportment").value(DEFAULT_OUTDEPORTMENT))
            .andExpect(jsonPath("$.indeportment").value(DEFAULT_INDEPORTMENT))
            .andExpect(jsonPath("$.adjusttime").value(DEFAULT_ADJUSTTIME.toString()))
            .andExpect(jsonPath("$.projectname").value(DEFAULT_PROJECTNAME))
            .andExpect(jsonPath("$.deportment").value(DEFAULT_DEPORTMENT))
            .andExpect(jsonPath("$.projectleader").value(DEFAULT_PROJECTLEADER))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingHumanresources() throws Exception {
        // Get the humanresources
        restHumanresourcesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingHumanresources() throws Exception {
        // Initialize the database
        humanresourcesRepository.saveAndFlush(humanresources);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the humanresources
        Humanresources updatedHumanresources = humanresourcesRepository.findById(humanresources.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedHumanresources are not directly saved in db
        em.detach(updatedHumanresources);
        updatedHumanresources
            .name(UPDATED_NAME)
            .outdeportment(UPDATED_OUTDEPORTMENT)
            .indeportment(UPDATED_INDEPORTMENT)
            .adjusttime(UPDATED_ADJUSTTIME)
            .projectname(UPDATED_PROJECTNAME)
            .deportment(UPDATED_DEPORTMENT)
            .projectleader(UPDATED_PROJECTLEADER)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restHumanresourcesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedHumanresources.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedHumanresources))
            )
            .andExpect(status().isOk());

        // Validate the Humanresources in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedHumanresourcesToMatchAllProperties(updatedHumanresources);
    }

    @Test
    @Transactional
    void putNonExistingHumanresources() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        humanresources.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHumanresourcesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, humanresources.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(humanresources))
            )
            .andExpect(status().isBadRequest());

        // Validate the Humanresources in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHumanresources() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        humanresources.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHumanresourcesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(humanresources))
            )
            .andExpect(status().isBadRequest());

        // Validate the Humanresources in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHumanresources() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        humanresources.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHumanresourcesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(humanresources)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Humanresources in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHumanresourcesWithPatch() throws Exception {
        // Initialize the database
        humanresourcesRepository.saveAndFlush(humanresources);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the humanresources using partial update
        Humanresources partialUpdatedHumanresources = new Humanresources();
        partialUpdatedHumanresources.setId(humanresources.getId());

        partialUpdatedHumanresources
            .outdeportment(UPDATED_OUTDEPORTMENT)
            .indeportment(UPDATED_INDEPORTMENT)
            .projectname(UPDATED_PROJECTNAME)
            .deportment(UPDATED_DEPORTMENT)
            .projectleader(UPDATED_PROJECTLEADER);

        restHumanresourcesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHumanresources.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHumanresources))
            )
            .andExpect(status().isOk());

        // Validate the Humanresources in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHumanresourcesUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedHumanresources, humanresources),
            getPersistedHumanresources(humanresources)
        );
    }

    @Test
    @Transactional
    void fullUpdateHumanresourcesWithPatch() throws Exception {
        // Initialize the database
        humanresourcesRepository.saveAndFlush(humanresources);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the humanresources using partial update
        Humanresources partialUpdatedHumanresources = new Humanresources();
        partialUpdatedHumanresources.setId(humanresources.getId());

        partialUpdatedHumanresources
            .name(UPDATED_NAME)
            .outdeportment(UPDATED_OUTDEPORTMENT)
            .indeportment(UPDATED_INDEPORTMENT)
            .adjusttime(UPDATED_ADJUSTTIME)
            .projectname(UPDATED_PROJECTNAME)
            .deportment(UPDATED_DEPORTMENT)
            .projectleader(UPDATED_PROJECTLEADER)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restHumanresourcesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHumanresources.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHumanresources))
            )
            .andExpect(status().isOk());

        // Validate the Humanresources in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHumanresourcesUpdatableFieldsEquals(partialUpdatedHumanresources, getPersistedHumanresources(partialUpdatedHumanresources));
    }

    @Test
    @Transactional
    void patchNonExistingHumanresources() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        humanresources.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHumanresourcesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, humanresources.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(humanresources))
            )
            .andExpect(status().isBadRequest());

        // Validate the Humanresources in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHumanresources() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        humanresources.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHumanresourcesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(humanresources))
            )
            .andExpect(status().isBadRequest());

        // Validate the Humanresources in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHumanresources() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        humanresources.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHumanresourcesMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(humanresources)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Humanresources in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHumanresources() throws Exception {
        // Initialize the database
        humanresourcesRepository.saveAndFlush(humanresources);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the humanresources
        restHumanresourcesMockMvc
            .perform(delete(ENTITY_API_URL_ID, humanresources.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return humanresourcesRepository.count();
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

    protected Humanresources getPersistedHumanresources(Humanresources humanresources) {
        return humanresourcesRepository.findById(humanresources.getId()).orElseThrow();
    }

    protected void assertPersistedHumanresourcesToMatchAllProperties(Humanresources expectedHumanresources) {
        assertHumanresourcesAllPropertiesEquals(expectedHumanresources, getPersistedHumanresources(expectedHumanresources));
    }

    protected void assertPersistedHumanresourcesToMatchUpdatableProperties(Humanresources expectedHumanresources) {
        assertHumanresourcesAllUpdatablePropertiesEquals(expectedHumanresources, getPersistedHumanresources(expectedHumanresources));
    }
}

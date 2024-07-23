package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.OfficersAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.Officers;
import com.cvicse.jy1.repository.OfficersRepository;
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
 * Integration tests for the {@link OfficersResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OfficersResourceIT {

    private static final String DEFAULT_OFFICERSNAME = "AAAAAAAAAA";
    private static final String UPDATED_OFFICERSNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Long DEFAULT_PHONE = 1L;
    private static final Long UPDATED_PHONE = 2L;

    private static final String ENTITY_API_URL = "/api/officers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OfficersRepository officersRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOfficersMockMvc;

    private Officers officers;

    private Officers insertedOfficers;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Officers createEntity(EntityManager em) {
        Officers officers = new Officers()
            .officersname(DEFAULT_OFFICERSNAME)
            .password(DEFAULT_PASSWORD)
            .email(DEFAULT_EMAIL)
            .phone(DEFAULT_PHONE);
        return officers;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Officers createUpdatedEntity(EntityManager em) {
        Officers officers = new Officers()
            .officersname(UPDATED_OFFICERSNAME)
            .password(UPDATED_PASSWORD)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE);
        return officers;
    }

    @BeforeEach
    public void initTest() {
        officers = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedOfficers != null) {
            officersRepository.delete(insertedOfficers);
            insertedOfficers = null;
        }
    }

    @Test
    @Transactional
    void createOfficers() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Officers
        var returnedOfficers = om.readValue(
            restOfficersMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(officers)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Officers.class
        );

        // Validate the Officers in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOfficersUpdatableFieldsEquals(returnedOfficers, getPersistedOfficers(returnedOfficers));

        insertedOfficers = returnedOfficers;
    }

    @Test
    @Transactional
    void createOfficersWithExistingId() throws Exception {
        // Create the Officers with an existing ID
        officers.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfficersMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(officers)))
            .andExpect(status().isBadRequest());

        // Validate the Officers in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOfficers() throws Exception {
        // Initialize the database
        insertedOfficers = officersRepository.saveAndFlush(officers);

        // Get all the officersList
        restOfficersMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(officers.getId())))
            .andExpect(jsonPath("$.[*].officersname").value(hasItem(DEFAULT_OFFICERSNAME)))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.intValue())));
    }

    @Test
    @Transactional
    void getOfficers() throws Exception {
        // Initialize the database
        insertedOfficers = officersRepository.saveAndFlush(officers);

        // Get the officers
        restOfficersMockMvc
            .perform(get(ENTITY_API_URL_ID, officers.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(officers.getId()))
            .andExpect(jsonPath("$.officersname").value(DEFAULT_OFFICERSNAME))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingOfficers() throws Exception {
        // Get the officers
        restOfficersMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOfficers() throws Exception {
        // Initialize the database
        insertedOfficers = officersRepository.saveAndFlush(officers);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the officers
        Officers updatedOfficers = officersRepository.findById(officers.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedOfficers are not directly saved in db
        em.detach(updatedOfficers);
        updatedOfficers.officersname(UPDATED_OFFICERSNAME).password(UPDATED_PASSWORD).email(UPDATED_EMAIL).phone(UPDATED_PHONE);

        restOfficersMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOfficers.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOfficers))
            )
            .andExpect(status().isOk());

        // Validate the Officers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOfficersToMatchAllProperties(updatedOfficers);
    }

    @Test
    @Transactional
    void putNonExistingOfficers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        officers.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfficersMockMvc
            .perform(
                put(ENTITY_API_URL_ID, officers.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(officers))
            )
            .andExpect(status().isBadRequest());

        // Validate the Officers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOfficers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        officers.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfficersMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(officers))
            )
            .andExpect(status().isBadRequest());

        // Validate the Officers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOfficers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        officers.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfficersMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(officers)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Officers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOfficersWithPatch() throws Exception {
        // Initialize the database
        insertedOfficers = officersRepository.saveAndFlush(officers);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the officers using partial update
        Officers partialUpdatedOfficers = new Officers();
        partialUpdatedOfficers.setId(officers.getId());

        partialUpdatedOfficers.officersname(UPDATED_OFFICERSNAME).password(UPDATED_PASSWORD).email(UPDATED_EMAIL);

        restOfficersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOfficers.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOfficers))
            )
            .andExpect(status().isOk());

        // Validate the Officers in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOfficersUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedOfficers, officers), getPersistedOfficers(officers));
    }

    @Test
    @Transactional
    void fullUpdateOfficersWithPatch() throws Exception {
        // Initialize the database
        insertedOfficers = officersRepository.saveAndFlush(officers);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the officers using partial update
        Officers partialUpdatedOfficers = new Officers();
        partialUpdatedOfficers.setId(officers.getId());

        partialUpdatedOfficers.officersname(UPDATED_OFFICERSNAME).password(UPDATED_PASSWORD).email(UPDATED_EMAIL).phone(UPDATED_PHONE);

        restOfficersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOfficers.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOfficers))
            )
            .andExpect(status().isOk());

        // Validate the Officers in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOfficersUpdatableFieldsEquals(partialUpdatedOfficers, getPersistedOfficers(partialUpdatedOfficers));
    }

    @Test
    @Transactional
    void patchNonExistingOfficers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        officers.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfficersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, officers.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(officers))
            )
            .andExpect(status().isBadRequest());

        // Validate the Officers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOfficers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        officers.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfficersMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(officers))
            )
            .andExpect(status().isBadRequest());

        // Validate the Officers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOfficers() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        officers.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOfficersMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(officers)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Officers in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOfficers() throws Exception {
        // Initialize the database
        insertedOfficers = officersRepository.saveAndFlush(officers);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the officers
        restOfficersMockMvc
            .perform(delete(ENTITY_API_URL_ID, officers.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return officersRepository.count();
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

    protected Officers getPersistedOfficers(Officers officers) {
        return officersRepository.findById(officers.getId()).orElseThrow();
    }

    protected void assertPersistedOfficersToMatchAllProperties(Officers expectedOfficers) {
        assertOfficersAllPropertiesEquals(expectedOfficers, getPersistedOfficers(expectedOfficers));
    }

    protected void assertPersistedOfficersToMatchUpdatableProperties(Officers expectedOfficers) {
        assertOfficersAllUpdatablePropertiesEquals(expectedOfficers, getPersistedOfficers(expectedOfficers));
    }
}

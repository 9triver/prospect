package com.cvicse.web.rest;

import static com.cvicse.domain.SecrecymanagementWbsAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.SecrecymanagementWbs;
import com.cvicse.repository.SecrecymanagementWbsRepository;
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
 * Integration tests for the {@link SecrecymanagementWbsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SecrecymanagementWbsResourceIT {

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

    private static final String ENTITY_API_URL = "/api/secrecymanagement-wbs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SecrecymanagementWbsRepository secrecymanagementWbsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSecrecymanagementWbsMockMvc;

    private SecrecymanagementWbs secrecymanagementWbs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SecrecymanagementWbs createEntity(EntityManager em) {
        SecrecymanagementWbs secrecymanagementWbs = new SecrecymanagementWbs()
            .wbsspare1(DEFAULT_WBSSPARE_1)
            .wbsspare2(DEFAULT_WBSSPARE_2)
            .wbsspare3(DEFAULT_WBSSPARE_3)
            .wbsspare4(DEFAULT_WBSSPARE_4)
            .wbsspare5(DEFAULT_WBSSPARE_5);
        return secrecymanagementWbs;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SecrecymanagementWbs createUpdatedEntity(EntityManager em) {
        SecrecymanagementWbs secrecymanagementWbs = new SecrecymanagementWbs()
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);
        return secrecymanagementWbs;
    }

    @BeforeEach
    public void initTest() {
        secrecymanagementWbs = createEntity(em);
    }

    @Test
    @Transactional
    void createSecrecymanagementWbs() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the SecrecymanagementWbs
        var returnedSecrecymanagementWbs = om.readValue(
            restSecrecymanagementWbsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(secrecymanagementWbs)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            SecrecymanagementWbs.class
        );

        // Validate the SecrecymanagementWbs in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertSecrecymanagementWbsUpdatableFieldsEquals(
            returnedSecrecymanagementWbs,
            getPersistedSecrecymanagementWbs(returnedSecrecymanagementWbs)
        );
    }

    @Test
    @Transactional
    void createSecrecymanagementWbsWithExistingId() throws Exception {
        // Create the SecrecymanagementWbs with an existing ID
        secrecymanagementWbs.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSecrecymanagementWbsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(secrecymanagementWbs)))
            .andExpect(status().isBadRequest());

        // Validate the SecrecymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSecrecymanagementWbs() throws Exception {
        // Initialize the database
        secrecymanagementWbsRepository.saveAndFlush(secrecymanagementWbs);

        // Get all the secrecymanagementWbsList
        restSecrecymanagementWbsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(secrecymanagementWbs.getId())))
            .andExpect(jsonPath("$.[*].wbsspare1").value(hasItem(DEFAULT_WBSSPARE_1)))
            .andExpect(jsonPath("$.[*].wbsspare2").value(hasItem(DEFAULT_WBSSPARE_2)))
            .andExpect(jsonPath("$.[*].wbsspare3").value(hasItem(DEFAULT_WBSSPARE_3)))
            .andExpect(jsonPath("$.[*].wbsspare4").value(hasItem(DEFAULT_WBSSPARE_4)))
            .andExpect(jsonPath("$.[*].wbsspare5").value(hasItem(DEFAULT_WBSSPARE_5)));
    }

    @Test
    @Transactional
    void getSecrecymanagementWbs() throws Exception {
        // Initialize the database
        secrecymanagementWbsRepository.saveAndFlush(secrecymanagementWbs);

        // Get the secrecymanagementWbs
        restSecrecymanagementWbsMockMvc
            .perform(get(ENTITY_API_URL_ID, secrecymanagementWbs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(secrecymanagementWbs.getId()))
            .andExpect(jsonPath("$.wbsspare1").value(DEFAULT_WBSSPARE_1))
            .andExpect(jsonPath("$.wbsspare2").value(DEFAULT_WBSSPARE_2))
            .andExpect(jsonPath("$.wbsspare3").value(DEFAULT_WBSSPARE_3))
            .andExpect(jsonPath("$.wbsspare4").value(DEFAULT_WBSSPARE_4))
            .andExpect(jsonPath("$.wbsspare5").value(DEFAULT_WBSSPARE_5));
    }

    @Test
    @Transactional
    void getNonExistingSecrecymanagementWbs() throws Exception {
        // Get the secrecymanagementWbs
        restSecrecymanagementWbsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSecrecymanagementWbs() throws Exception {
        // Initialize the database
        secrecymanagementWbsRepository.saveAndFlush(secrecymanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the secrecymanagementWbs
        SecrecymanagementWbs updatedSecrecymanagementWbs = secrecymanagementWbsRepository
            .findById(secrecymanagementWbs.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedSecrecymanagementWbs are not directly saved in db
        em.detach(updatedSecrecymanagementWbs);
        updatedSecrecymanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restSecrecymanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSecrecymanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedSecrecymanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the SecrecymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSecrecymanagementWbsToMatchAllProperties(updatedSecrecymanagementWbs);
    }

    @Test
    @Transactional
    void putNonExistingSecrecymanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecymanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecrecymanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, secrecymanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(secrecymanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the SecrecymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSecrecymanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecymanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecrecymanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(secrecymanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the SecrecymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSecrecymanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecymanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecrecymanagementWbsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(secrecymanagementWbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SecrecymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSecrecymanagementWbsWithPatch() throws Exception {
        // Initialize the database
        secrecymanagementWbsRepository.saveAndFlush(secrecymanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the secrecymanagementWbs using partial update
        SecrecymanagementWbs partialUpdatedSecrecymanagementWbs = new SecrecymanagementWbs();
        partialUpdatedSecrecymanagementWbs.setId(secrecymanagementWbs.getId());

        partialUpdatedSecrecymanagementWbs.wbsspare3(UPDATED_WBSSPARE_3);

        restSecrecymanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSecrecymanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSecrecymanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the SecrecymanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSecrecymanagementWbsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSecrecymanagementWbs, secrecymanagementWbs),
            getPersistedSecrecymanagementWbs(secrecymanagementWbs)
        );
    }

    @Test
    @Transactional
    void fullUpdateSecrecymanagementWbsWithPatch() throws Exception {
        // Initialize the database
        secrecymanagementWbsRepository.saveAndFlush(secrecymanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the secrecymanagementWbs using partial update
        SecrecymanagementWbs partialUpdatedSecrecymanagementWbs = new SecrecymanagementWbs();
        partialUpdatedSecrecymanagementWbs.setId(secrecymanagementWbs.getId());

        partialUpdatedSecrecymanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restSecrecymanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSecrecymanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSecrecymanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the SecrecymanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSecrecymanagementWbsUpdatableFieldsEquals(
            partialUpdatedSecrecymanagementWbs,
            getPersistedSecrecymanagementWbs(partialUpdatedSecrecymanagementWbs)
        );
    }

    @Test
    @Transactional
    void patchNonExistingSecrecymanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecymanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecrecymanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, secrecymanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(secrecymanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the SecrecymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSecrecymanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecymanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecrecymanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(secrecymanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the SecrecymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSecrecymanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecymanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecrecymanagementWbsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(secrecymanagementWbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SecrecymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSecrecymanagementWbs() throws Exception {
        // Initialize the database
        secrecymanagementWbsRepository.saveAndFlush(secrecymanagementWbs);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the secrecymanagementWbs
        restSecrecymanagementWbsMockMvc
            .perform(delete(ENTITY_API_URL_ID, secrecymanagementWbs.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return secrecymanagementWbsRepository.count();
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

    protected SecrecymanagementWbs getPersistedSecrecymanagementWbs(SecrecymanagementWbs secrecymanagementWbs) {
        return secrecymanagementWbsRepository.findById(secrecymanagementWbs.getId()).orElseThrow();
    }

    protected void assertPersistedSecrecymanagementWbsToMatchAllProperties(SecrecymanagementWbs expectedSecrecymanagementWbs) {
        assertSecrecymanagementWbsAllPropertiesEquals(
            expectedSecrecymanagementWbs,
            getPersistedSecrecymanagementWbs(expectedSecrecymanagementWbs)
        );
    }

    protected void assertPersistedSecrecymanagementWbsToMatchUpdatableProperties(SecrecymanagementWbs expectedSecrecymanagementWbs) {
        assertSecrecymanagementWbsAllUpdatablePropertiesEquals(
            expectedSecrecymanagementWbs,
            getPersistedSecrecymanagementWbs(expectedSecrecymanagementWbs)
        );
    }
}

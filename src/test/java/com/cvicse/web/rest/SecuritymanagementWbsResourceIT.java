package com.cvicse.web.rest;

import static com.cvicse.domain.SecuritymanagementWbsAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.SecuritymanagementWbs;
import com.cvicse.repository.SecuritymanagementWbsRepository;
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
 * Integration tests for the {@link SecuritymanagementWbsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SecuritymanagementWbsResourceIT {

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

    private static final String ENTITY_API_URL = "/api/securitymanagement-wbs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SecuritymanagementWbsRepository securitymanagementWbsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSecuritymanagementWbsMockMvc;

    private SecuritymanagementWbs securitymanagementWbs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SecuritymanagementWbs createEntity(EntityManager em) {
        SecuritymanagementWbs securitymanagementWbs = new SecuritymanagementWbs()
            .wbsspare1(DEFAULT_WBSSPARE_1)
            .wbsspare2(DEFAULT_WBSSPARE_2)
            .wbsspare3(DEFAULT_WBSSPARE_3)
            .wbsspare4(DEFAULT_WBSSPARE_4)
            .wbsspare5(DEFAULT_WBSSPARE_5);
        return securitymanagementWbs;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SecuritymanagementWbs createUpdatedEntity(EntityManager em) {
        SecuritymanagementWbs securitymanagementWbs = new SecuritymanagementWbs()
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);
        return securitymanagementWbs;
    }

    @BeforeEach
    public void initTest() {
        securitymanagementWbs = createEntity(em);
    }

    @Test
    @Transactional
    void createSecuritymanagementWbs() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the SecuritymanagementWbs
        var returnedSecuritymanagementWbs = om.readValue(
            restSecuritymanagementWbsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(securitymanagementWbs)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            SecuritymanagementWbs.class
        );

        // Validate the SecuritymanagementWbs in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertSecuritymanagementWbsUpdatableFieldsEquals(
            returnedSecuritymanagementWbs,
            getPersistedSecuritymanagementWbs(returnedSecuritymanagementWbs)
        );
    }

    @Test
    @Transactional
    void createSecuritymanagementWbsWithExistingId() throws Exception {
        // Create the SecuritymanagementWbs with an existing ID
        securitymanagementWbs.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSecuritymanagementWbsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(securitymanagementWbs)))
            .andExpect(status().isBadRequest());

        // Validate the SecuritymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSecuritymanagementWbs() throws Exception {
        // Initialize the database
        securitymanagementWbsRepository.saveAndFlush(securitymanagementWbs);

        // Get all the securitymanagementWbsList
        restSecuritymanagementWbsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(securitymanagementWbs.getId())))
            .andExpect(jsonPath("$.[*].wbsspare1").value(hasItem(DEFAULT_WBSSPARE_1)))
            .andExpect(jsonPath("$.[*].wbsspare2").value(hasItem(DEFAULT_WBSSPARE_2)))
            .andExpect(jsonPath("$.[*].wbsspare3").value(hasItem(DEFAULT_WBSSPARE_3)))
            .andExpect(jsonPath("$.[*].wbsspare4").value(hasItem(DEFAULT_WBSSPARE_4)))
            .andExpect(jsonPath("$.[*].wbsspare5").value(hasItem(DEFAULT_WBSSPARE_5)));
    }

    @Test
    @Transactional
    void getSecuritymanagementWbs() throws Exception {
        // Initialize the database
        securitymanagementWbsRepository.saveAndFlush(securitymanagementWbs);

        // Get the securitymanagementWbs
        restSecuritymanagementWbsMockMvc
            .perform(get(ENTITY_API_URL_ID, securitymanagementWbs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(securitymanagementWbs.getId()))
            .andExpect(jsonPath("$.wbsspare1").value(DEFAULT_WBSSPARE_1))
            .andExpect(jsonPath("$.wbsspare2").value(DEFAULT_WBSSPARE_2))
            .andExpect(jsonPath("$.wbsspare3").value(DEFAULT_WBSSPARE_3))
            .andExpect(jsonPath("$.wbsspare4").value(DEFAULT_WBSSPARE_4))
            .andExpect(jsonPath("$.wbsspare5").value(DEFAULT_WBSSPARE_5));
    }

    @Test
    @Transactional
    void getNonExistingSecuritymanagementWbs() throws Exception {
        // Get the securitymanagementWbs
        restSecuritymanagementWbsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSecuritymanagementWbs() throws Exception {
        // Initialize the database
        securitymanagementWbsRepository.saveAndFlush(securitymanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the securitymanagementWbs
        SecuritymanagementWbs updatedSecuritymanagementWbs = securitymanagementWbsRepository
            .findById(securitymanagementWbs.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedSecuritymanagementWbs are not directly saved in db
        em.detach(updatedSecuritymanagementWbs);
        updatedSecuritymanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restSecuritymanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSecuritymanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedSecuritymanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the SecuritymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSecuritymanagementWbsToMatchAllProperties(updatedSecuritymanagementWbs);
    }

    @Test
    @Transactional
    void putNonExistingSecuritymanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        securitymanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecuritymanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, securitymanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(securitymanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the SecuritymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSecuritymanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        securitymanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecuritymanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(securitymanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the SecuritymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSecuritymanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        securitymanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecuritymanagementWbsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(securitymanagementWbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SecuritymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSecuritymanagementWbsWithPatch() throws Exception {
        // Initialize the database
        securitymanagementWbsRepository.saveAndFlush(securitymanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the securitymanagementWbs using partial update
        SecuritymanagementWbs partialUpdatedSecuritymanagementWbs = new SecuritymanagementWbs();
        partialUpdatedSecuritymanagementWbs.setId(securitymanagementWbs.getId());

        partialUpdatedSecuritymanagementWbs.wbsspare2(UPDATED_WBSSPARE_2).wbsspare4(UPDATED_WBSSPARE_4);

        restSecuritymanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSecuritymanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSecuritymanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the SecuritymanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSecuritymanagementWbsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSecuritymanagementWbs, securitymanagementWbs),
            getPersistedSecuritymanagementWbs(securitymanagementWbs)
        );
    }

    @Test
    @Transactional
    void fullUpdateSecuritymanagementWbsWithPatch() throws Exception {
        // Initialize the database
        securitymanagementWbsRepository.saveAndFlush(securitymanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the securitymanagementWbs using partial update
        SecuritymanagementWbs partialUpdatedSecuritymanagementWbs = new SecuritymanagementWbs();
        partialUpdatedSecuritymanagementWbs.setId(securitymanagementWbs.getId());

        partialUpdatedSecuritymanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restSecuritymanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSecuritymanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSecuritymanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the SecuritymanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSecuritymanagementWbsUpdatableFieldsEquals(
            partialUpdatedSecuritymanagementWbs,
            getPersistedSecuritymanagementWbs(partialUpdatedSecuritymanagementWbs)
        );
    }

    @Test
    @Transactional
    void patchNonExistingSecuritymanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        securitymanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecuritymanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, securitymanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(securitymanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the SecuritymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSecuritymanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        securitymanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecuritymanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(securitymanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the SecuritymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSecuritymanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        securitymanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecuritymanagementWbsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(securitymanagementWbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SecuritymanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSecuritymanagementWbs() throws Exception {
        // Initialize the database
        securitymanagementWbsRepository.saveAndFlush(securitymanagementWbs);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the securitymanagementWbs
        restSecuritymanagementWbsMockMvc
            .perform(delete(ENTITY_API_URL_ID, securitymanagementWbs.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return securitymanagementWbsRepository.count();
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

    protected SecuritymanagementWbs getPersistedSecuritymanagementWbs(SecuritymanagementWbs securitymanagementWbs) {
        return securitymanagementWbsRepository.findById(securitymanagementWbs.getId()).orElseThrow();
    }

    protected void assertPersistedSecuritymanagementWbsToMatchAllProperties(SecuritymanagementWbs expectedSecuritymanagementWbs) {
        assertSecuritymanagementWbsAllPropertiesEquals(
            expectedSecuritymanagementWbs,
            getPersistedSecuritymanagementWbs(expectedSecuritymanagementWbs)
        );
    }

    protected void assertPersistedSecuritymanagementWbsToMatchUpdatableProperties(SecuritymanagementWbs expectedSecuritymanagementWbs) {
        assertSecuritymanagementWbsAllUpdatablePropertiesEquals(
            expectedSecuritymanagementWbs,
            getPersistedSecuritymanagementWbs(expectedSecuritymanagementWbs)
        );
    }
}

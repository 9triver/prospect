package com.cvicse.web.rest;

import static com.cvicse.domain.IntegratedmanagementWbsAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.IntegratedmanagementWbs;
import com.cvicse.repository.IntegratedmanagementWbsRepository;
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
 * Integration tests for the {@link IntegratedmanagementWbsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class IntegratedmanagementWbsResourceIT {

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

    private static final String ENTITY_API_URL = "/api/integratedmanagement-wbs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private IntegratedmanagementWbsRepository integratedmanagementWbsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restIntegratedmanagementWbsMockMvc;

    private IntegratedmanagementWbs integratedmanagementWbs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IntegratedmanagementWbs createEntity(EntityManager em) {
        IntegratedmanagementWbs integratedmanagementWbs = new IntegratedmanagementWbs()
            .wbsspare1(DEFAULT_WBSSPARE_1)
            .wbsspare2(DEFAULT_WBSSPARE_2)
            .wbsspare3(DEFAULT_WBSSPARE_3)
            .wbsspare4(DEFAULT_WBSSPARE_4)
            .wbsspare5(DEFAULT_WBSSPARE_5);
        return integratedmanagementWbs;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IntegratedmanagementWbs createUpdatedEntity(EntityManager em) {
        IntegratedmanagementWbs integratedmanagementWbs = new IntegratedmanagementWbs()
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);
        return integratedmanagementWbs;
    }

    @BeforeEach
    public void initTest() {
        integratedmanagementWbs = createEntity(em);
    }

    @Test
    @Transactional
    void createIntegratedmanagementWbs() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the IntegratedmanagementWbs
        var returnedIntegratedmanagementWbs = om.readValue(
            restIntegratedmanagementWbsMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(integratedmanagementWbs))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            IntegratedmanagementWbs.class
        );

        // Validate the IntegratedmanagementWbs in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertIntegratedmanagementWbsUpdatableFieldsEquals(
            returnedIntegratedmanagementWbs,
            getPersistedIntegratedmanagementWbs(returnedIntegratedmanagementWbs)
        );
    }

    @Test
    @Transactional
    void createIntegratedmanagementWbsWithExistingId() throws Exception {
        // Create the IntegratedmanagementWbs with an existing ID
        integratedmanagementWbs.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restIntegratedmanagementWbsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(integratedmanagementWbs)))
            .andExpect(status().isBadRequest());

        // Validate the IntegratedmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllIntegratedmanagementWbs() throws Exception {
        // Initialize the database
        integratedmanagementWbsRepository.saveAndFlush(integratedmanagementWbs);

        // Get all the integratedmanagementWbsList
        restIntegratedmanagementWbsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(integratedmanagementWbs.getId())))
            .andExpect(jsonPath("$.[*].wbsspare1").value(hasItem(DEFAULT_WBSSPARE_1)))
            .andExpect(jsonPath("$.[*].wbsspare2").value(hasItem(DEFAULT_WBSSPARE_2)))
            .andExpect(jsonPath("$.[*].wbsspare3").value(hasItem(DEFAULT_WBSSPARE_3)))
            .andExpect(jsonPath("$.[*].wbsspare4").value(hasItem(DEFAULT_WBSSPARE_4)))
            .andExpect(jsonPath("$.[*].wbsspare5").value(hasItem(DEFAULT_WBSSPARE_5)));
    }

    @Test
    @Transactional
    void getIntegratedmanagementWbs() throws Exception {
        // Initialize the database
        integratedmanagementWbsRepository.saveAndFlush(integratedmanagementWbs);

        // Get the integratedmanagementWbs
        restIntegratedmanagementWbsMockMvc
            .perform(get(ENTITY_API_URL_ID, integratedmanagementWbs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(integratedmanagementWbs.getId()))
            .andExpect(jsonPath("$.wbsspare1").value(DEFAULT_WBSSPARE_1))
            .andExpect(jsonPath("$.wbsspare2").value(DEFAULT_WBSSPARE_2))
            .andExpect(jsonPath("$.wbsspare3").value(DEFAULT_WBSSPARE_3))
            .andExpect(jsonPath("$.wbsspare4").value(DEFAULT_WBSSPARE_4))
            .andExpect(jsonPath("$.wbsspare5").value(DEFAULT_WBSSPARE_5));
    }

    @Test
    @Transactional
    void getNonExistingIntegratedmanagementWbs() throws Exception {
        // Get the integratedmanagementWbs
        restIntegratedmanagementWbsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingIntegratedmanagementWbs() throws Exception {
        // Initialize the database
        integratedmanagementWbsRepository.saveAndFlush(integratedmanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the integratedmanagementWbs
        IntegratedmanagementWbs updatedIntegratedmanagementWbs = integratedmanagementWbsRepository
            .findById(integratedmanagementWbs.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedIntegratedmanagementWbs are not directly saved in db
        em.detach(updatedIntegratedmanagementWbs);
        updatedIntegratedmanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restIntegratedmanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedIntegratedmanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedIntegratedmanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the IntegratedmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedIntegratedmanagementWbsToMatchAllProperties(updatedIntegratedmanagementWbs);
    }

    @Test
    @Transactional
    void putNonExistingIntegratedmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        integratedmanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIntegratedmanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, integratedmanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(integratedmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the IntegratedmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchIntegratedmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        integratedmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIntegratedmanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(integratedmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the IntegratedmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamIntegratedmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        integratedmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIntegratedmanagementWbsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(integratedmanagementWbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the IntegratedmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateIntegratedmanagementWbsWithPatch() throws Exception {
        // Initialize the database
        integratedmanagementWbsRepository.saveAndFlush(integratedmanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the integratedmanagementWbs using partial update
        IntegratedmanagementWbs partialUpdatedIntegratedmanagementWbs = new IntegratedmanagementWbs();
        partialUpdatedIntegratedmanagementWbs.setId(integratedmanagementWbs.getId());

        partialUpdatedIntegratedmanagementWbs.wbsspare4(UPDATED_WBSSPARE_4).wbsspare5(UPDATED_WBSSPARE_5);

        restIntegratedmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedIntegratedmanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedIntegratedmanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the IntegratedmanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertIntegratedmanagementWbsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedIntegratedmanagementWbs, integratedmanagementWbs),
            getPersistedIntegratedmanagementWbs(integratedmanagementWbs)
        );
    }

    @Test
    @Transactional
    void fullUpdateIntegratedmanagementWbsWithPatch() throws Exception {
        // Initialize the database
        integratedmanagementWbsRepository.saveAndFlush(integratedmanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the integratedmanagementWbs using partial update
        IntegratedmanagementWbs partialUpdatedIntegratedmanagementWbs = new IntegratedmanagementWbs();
        partialUpdatedIntegratedmanagementWbs.setId(integratedmanagementWbs.getId());

        partialUpdatedIntegratedmanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restIntegratedmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedIntegratedmanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedIntegratedmanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the IntegratedmanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertIntegratedmanagementWbsUpdatableFieldsEquals(
            partialUpdatedIntegratedmanagementWbs,
            getPersistedIntegratedmanagementWbs(partialUpdatedIntegratedmanagementWbs)
        );
    }

    @Test
    @Transactional
    void patchNonExistingIntegratedmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        integratedmanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIntegratedmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, integratedmanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(integratedmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the IntegratedmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchIntegratedmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        integratedmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIntegratedmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(integratedmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the IntegratedmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamIntegratedmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        integratedmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIntegratedmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(integratedmanagementWbs))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the IntegratedmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteIntegratedmanagementWbs() throws Exception {
        // Initialize the database
        integratedmanagementWbsRepository.saveAndFlush(integratedmanagementWbs);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the integratedmanagementWbs
        restIntegratedmanagementWbsMockMvc
            .perform(delete(ENTITY_API_URL_ID, integratedmanagementWbs.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return integratedmanagementWbsRepository.count();
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

    protected IntegratedmanagementWbs getPersistedIntegratedmanagementWbs(IntegratedmanagementWbs integratedmanagementWbs) {
        return integratedmanagementWbsRepository.findById(integratedmanagementWbs.getId()).orElseThrow();
    }

    protected void assertPersistedIntegratedmanagementWbsToMatchAllProperties(IntegratedmanagementWbs expectedIntegratedmanagementWbs) {
        assertIntegratedmanagementWbsAllPropertiesEquals(
            expectedIntegratedmanagementWbs,
            getPersistedIntegratedmanagementWbs(expectedIntegratedmanagementWbs)
        );
    }

    protected void assertPersistedIntegratedmanagementWbsToMatchUpdatableProperties(
        IntegratedmanagementWbs expectedIntegratedmanagementWbs
    ) {
        assertIntegratedmanagementWbsAllUpdatablePropertiesEquals(
            expectedIntegratedmanagementWbs,
            getPersistedIntegratedmanagementWbs(expectedIntegratedmanagementWbs)
        );
    }
}

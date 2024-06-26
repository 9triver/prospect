package com.cvicse.web.rest;

import static com.cvicse.domain.FundsmanagementWbsAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.FundsmanagementWbs;
import com.cvicse.repository.FundsmanagementWbsRepository;
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
 * Integration tests for the {@link FundsmanagementWbsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FundsmanagementWbsResourceIT {

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

    private static final String ENTITY_API_URL = "/api/fundsmanagement-wbs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FundsmanagementWbsRepository fundsmanagementWbsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFundsmanagementWbsMockMvc;

    private FundsmanagementWbs fundsmanagementWbs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FundsmanagementWbs createEntity(EntityManager em) {
        FundsmanagementWbs fundsmanagementWbs = new FundsmanagementWbs()
            .wbsspare1(DEFAULT_WBSSPARE_1)
            .wbsspare2(DEFAULT_WBSSPARE_2)
            .wbsspare3(DEFAULT_WBSSPARE_3)
            .wbsspare4(DEFAULT_WBSSPARE_4)
            .wbsspare5(DEFAULT_WBSSPARE_5);
        return fundsmanagementWbs;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FundsmanagementWbs createUpdatedEntity(EntityManager em) {
        FundsmanagementWbs fundsmanagementWbs = new FundsmanagementWbs()
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);
        return fundsmanagementWbs;
    }

    @BeforeEach
    public void initTest() {
        fundsmanagementWbs = createEntity(em);
    }

    @Test
    @Transactional
    void createFundsmanagementWbs() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the FundsmanagementWbs
        var returnedFundsmanagementWbs = om.readValue(
            restFundsmanagementWbsMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fundsmanagementWbs)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            FundsmanagementWbs.class
        );

        // Validate the FundsmanagementWbs in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertFundsmanagementWbsUpdatableFieldsEquals(
            returnedFundsmanagementWbs,
            getPersistedFundsmanagementWbs(returnedFundsmanagementWbs)
        );
    }

    @Test
    @Transactional
    void createFundsmanagementWbsWithExistingId() throws Exception {
        // Create the FundsmanagementWbs with an existing ID
        fundsmanagementWbs.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFundsmanagementWbsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fundsmanagementWbs)))
            .andExpect(status().isBadRequest());

        // Validate the FundsmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFundsmanagementWbs() throws Exception {
        // Initialize the database
        fundsmanagementWbsRepository.saveAndFlush(fundsmanagementWbs);

        // Get all the fundsmanagementWbsList
        restFundsmanagementWbsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fundsmanagementWbs.getId())))
            .andExpect(jsonPath("$.[*].wbsspare1").value(hasItem(DEFAULT_WBSSPARE_1)))
            .andExpect(jsonPath("$.[*].wbsspare2").value(hasItem(DEFAULT_WBSSPARE_2)))
            .andExpect(jsonPath("$.[*].wbsspare3").value(hasItem(DEFAULT_WBSSPARE_3)))
            .andExpect(jsonPath("$.[*].wbsspare4").value(hasItem(DEFAULT_WBSSPARE_4)))
            .andExpect(jsonPath("$.[*].wbsspare5").value(hasItem(DEFAULT_WBSSPARE_5)));
    }

    @Test
    @Transactional
    void getFundsmanagementWbs() throws Exception {
        // Initialize the database
        fundsmanagementWbsRepository.saveAndFlush(fundsmanagementWbs);

        // Get the fundsmanagementWbs
        restFundsmanagementWbsMockMvc
            .perform(get(ENTITY_API_URL_ID, fundsmanagementWbs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fundsmanagementWbs.getId()))
            .andExpect(jsonPath("$.wbsspare1").value(DEFAULT_WBSSPARE_1))
            .andExpect(jsonPath("$.wbsspare2").value(DEFAULT_WBSSPARE_2))
            .andExpect(jsonPath("$.wbsspare3").value(DEFAULT_WBSSPARE_3))
            .andExpect(jsonPath("$.wbsspare4").value(DEFAULT_WBSSPARE_4))
            .andExpect(jsonPath("$.wbsspare5").value(DEFAULT_WBSSPARE_5));
    }

    @Test
    @Transactional
    void getNonExistingFundsmanagementWbs() throws Exception {
        // Get the fundsmanagementWbs
        restFundsmanagementWbsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFundsmanagementWbs() throws Exception {
        // Initialize the database
        fundsmanagementWbsRepository.saveAndFlush(fundsmanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fundsmanagementWbs
        FundsmanagementWbs updatedFundsmanagementWbs = fundsmanagementWbsRepository.findById(fundsmanagementWbs.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFundsmanagementWbs are not directly saved in db
        em.detach(updatedFundsmanagementWbs);
        updatedFundsmanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restFundsmanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFundsmanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedFundsmanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the FundsmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFundsmanagementWbsToMatchAllProperties(updatedFundsmanagementWbs);
    }

    @Test
    @Transactional
    void putNonExistingFundsmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsmanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFundsmanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fundsmanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fundsmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the FundsmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFundsmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsmanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fundsmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the FundsmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFundsmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsmanagementWbsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fundsmanagementWbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FundsmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFundsmanagementWbsWithPatch() throws Exception {
        // Initialize the database
        fundsmanagementWbsRepository.saveAndFlush(fundsmanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fundsmanagementWbs using partial update
        FundsmanagementWbs partialUpdatedFundsmanagementWbs = new FundsmanagementWbs();
        partialUpdatedFundsmanagementWbs.setId(fundsmanagementWbs.getId());

        partialUpdatedFundsmanagementWbs.wbsspare1(UPDATED_WBSSPARE_1).wbsspare2(UPDATED_WBSSPARE_2).wbsspare4(UPDATED_WBSSPARE_4);

        restFundsmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFundsmanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFundsmanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the FundsmanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFundsmanagementWbsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFundsmanagementWbs, fundsmanagementWbs),
            getPersistedFundsmanagementWbs(fundsmanagementWbs)
        );
    }

    @Test
    @Transactional
    void fullUpdateFundsmanagementWbsWithPatch() throws Exception {
        // Initialize the database
        fundsmanagementWbsRepository.saveAndFlush(fundsmanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fundsmanagementWbs using partial update
        FundsmanagementWbs partialUpdatedFundsmanagementWbs = new FundsmanagementWbs();
        partialUpdatedFundsmanagementWbs.setId(fundsmanagementWbs.getId());

        partialUpdatedFundsmanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restFundsmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFundsmanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFundsmanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the FundsmanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFundsmanagementWbsUpdatableFieldsEquals(
            partialUpdatedFundsmanagementWbs,
            getPersistedFundsmanagementWbs(partialUpdatedFundsmanagementWbs)
        );
    }

    @Test
    @Transactional
    void patchNonExistingFundsmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsmanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFundsmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fundsmanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fundsmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the FundsmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFundsmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fundsmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the FundsmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFundsmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsmanagementWbsMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(fundsmanagementWbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the FundsmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFundsmanagementWbs() throws Exception {
        // Initialize the database
        fundsmanagementWbsRepository.saveAndFlush(fundsmanagementWbs);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the fundsmanagementWbs
        restFundsmanagementWbsMockMvc
            .perform(delete(ENTITY_API_URL_ID, fundsmanagementWbs.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return fundsmanagementWbsRepository.count();
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

    protected FundsmanagementWbs getPersistedFundsmanagementWbs(FundsmanagementWbs fundsmanagementWbs) {
        return fundsmanagementWbsRepository.findById(fundsmanagementWbs.getId()).orElseThrow();
    }

    protected void assertPersistedFundsmanagementWbsToMatchAllProperties(FundsmanagementWbs expectedFundsmanagementWbs) {
        assertFundsmanagementWbsAllPropertiesEquals(expectedFundsmanagementWbs, getPersistedFundsmanagementWbs(expectedFundsmanagementWbs));
    }

    protected void assertPersistedFundsmanagementWbsToMatchUpdatableProperties(FundsmanagementWbs expectedFundsmanagementWbs) {
        assertFundsmanagementWbsAllUpdatablePropertiesEquals(
            expectedFundsmanagementWbs,
            getPersistedFundsmanagementWbs(expectedFundsmanagementWbs)
        );
    }
}

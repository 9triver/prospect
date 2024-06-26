package com.cvicse.web.rest;

import static com.cvicse.domain.OutsourcingmanagementWbsAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.OutsourcingmanagementWbs;
import com.cvicse.repository.OutsourcingmanagementWbsRepository;
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
 * Integration tests for the {@link OutsourcingmanagementWbsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OutsourcingmanagementWbsResourceIT {

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

    private static final String ENTITY_API_URL = "/api/outsourcingmanagement-wbs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private OutsourcingmanagementWbsRepository outsourcingmanagementWbsRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOutsourcingmanagementWbsMockMvc;

    private OutsourcingmanagementWbs outsourcingmanagementWbs;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OutsourcingmanagementWbs createEntity(EntityManager em) {
        OutsourcingmanagementWbs outsourcingmanagementWbs = new OutsourcingmanagementWbs()
            .wbsspare1(DEFAULT_WBSSPARE_1)
            .wbsspare2(DEFAULT_WBSSPARE_2)
            .wbsspare3(DEFAULT_WBSSPARE_3)
            .wbsspare4(DEFAULT_WBSSPARE_4)
            .wbsspare5(DEFAULT_WBSSPARE_5);
        return outsourcingmanagementWbs;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OutsourcingmanagementWbs createUpdatedEntity(EntityManager em) {
        OutsourcingmanagementWbs outsourcingmanagementWbs = new OutsourcingmanagementWbs()
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);
        return outsourcingmanagementWbs;
    }

    @BeforeEach
    public void initTest() {
        outsourcingmanagementWbs = createEntity(em);
    }

    @Test
    @Transactional
    void createOutsourcingmanagementWbs() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the OutsourcingmanagementWbs
        var returnedOutsourcingmanagementWbs = om.readValue(
            restOutsourcingmanagementWbsMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingmanagementWbs))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            OutsourcingmanagementWbs.class
        );

        // Validate the OutsourcingmanagementWbs in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertOutsourcingmanagementWbsUpdatableFieldsEquals(
            returnedOutsourcingmanagementWbs,
            getPersistedOutsourcingmanagementWbs(returnedOutsourcingmanagementWbs)
        );
    }

    @Test
    @Transactional
    void createOutsourcingmanagementWbsWithExistingId() throws Exception {
        // Create the OutsourcingmanagementWbs with an existing ID
        outsourcingmanagementWbs.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOutsourcingmanagementWbsMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingmanagementWbs)))
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOutsourcingmanagementWbs() throws Exception {
        // Initialize the database
        outsourcingmanagementWbsRepository.saveAndFlush(outsourcingmanagementWbs);

        // Get all the outsourcingmanagementWbsList
        restOutsourcingmanagementWbsMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(outsourcingmanagementWbs.getId())))
            .andExpect(jsonPath("$.[*].wbsspare1").value(hasItem(DEFAULT_WBSSPARE_1)))
            .andExpect(jsonPath("$.[*].wbsspare2").value(hasItem(DEFAULT_WBSSPARE_2)))
            .andExpect(jsonPath("$.[*].wbsspare3").value(hasItem(DEFAULT_WBSSPARE_3)))
            .andExpect(jsonPath("$.[*].wbsspare4").value(hasItem(DEFAULT_WBSSPARE_4)))
            .andExpect(jsonPath("$.[*].wbsspare5").value(hasItem(DEFAULT_WBSSPARE_5)));
    }

    @Test
    @Transactional
    void getOutsourcingmanagementWbs() throws Exception {
        // Initialize the database
        outsourcingmanagementWbsRepository.saveAndFlush(outsourcingmanagementWbs);

        // Get the outsourcingmanagementWbs
        restOutsourcingmanagementWbsMockMvc
            .perform(get(ENTITY_API_URL_ID, outsourcingmanagementWbs.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(outsourcingmanagementWbs.getId()))
            .andExpect(jsonPath("$.wbsspare1").value(DEFAULT_WBSSPARE_1))
            .andExpect(jsonPath("$.wbsspare2").value(DEFAULT_WBSSPARE_2))
            .andExpect(jsonPath("$.wbsspare3").value(DEFAULT_WBSSPARE_3))
            .andExpect(jsonPath("$.wbsspare4").value(DEFAULT_WBSSPARE_4))
            .andExpect(jsonPath("$.wbsspare5").value(DEFAULT_WBSSPARE_5));
    }

    @Test
    @Transactional
    void getNonExistingOutsourcingmanagementWbs() throws Exception {
        // Get the outsourcingmanagementWbs
        restOutsourcingmanagementWbsMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOutsourcingmanagementWbs() throws Exception {
        // Initialize the database
        outsourcingmanagementWbsRepository.saveAndFlush(outsourcingmanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingmanagementWbs
        OutsourcingmanagementWbs updatedOutsourcingmanagementWbs = outsourcingmanagementWbsRepository
            .findById(outsourcingmanagementWbs.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedOutsourcingmanagementWbs are not directly saved in db
        em.detach(updatedOutsourcingmanagementWbs);
        updatedOutsourcingmanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restOutsourcingmanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedOutsourcingmanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedOutsourcingmanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedOutsourcingmanagementWbsToMatchAllProperties(updatedOutsourcingmanagementWbs);
    }

    @Test
    @Transactional
    void putNonExistingOutsourcingmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOutsourcingmanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, outsourcingmanagementWbs.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(outsourcingmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOutsourcingmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingmanagementWbsMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(outsourcingmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOutsourcingmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingmanagementWbsMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(outsourcingmanagementWbs)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OutsourcingmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOutsourcingmanagementWbsWithPatch() throws Exception {
        // Initialize the database
        outsourcingmanagementWbsRepository.saveAndFlush(outsourcingmanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingmanagementWbs using partial update
        OutsourcingmanagementWbs partialUpdatedOutsourcingmanagementWbs = new OutsourcingmanagementWbs();
        partialUpdatedOutsourcingmanagementWbs.setId(outsourcingmanagementWbs.getId());

        partialUpdatedOutsourcingmanagementWbs
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restOutsourcingmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOutsourcingmanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOutsourcingmanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingmanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOutsourcingmanagementWbsUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedOutsourcingmanagementWbs, outsourcingmanagementWbs),
            getPersistedOutsourcingmanagementWbs(outsourcingmanagementWbs)
        );
    }

    @Test
    @Transactional
    void fullUpdateOutsourcingmanagementWbsWithPatch() throws Exception {
        // Initialize the database
        outsourcingmanagementWbsRepository.saveAndFlush(outsourcingmanagementWbs);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the outsourcingmanagementWbs using partial update
        OutsourcingmanagementWbs partialUpdatedOutsourcingmanagementWbs = new OutsourcingmanagementWbs();
        partialUpdatedOutsourcingmanagementWbs.setId(outsourcingmanagementWbs.getId());

        partialUpdatedOutsourcingmanagementWbs
            .wbsspare1(UPDATED_WBSSPARE_1)
            .wbsspare2(UPDATED_WBSSPARE_2)
            .wbsspare3(UPDATED_WBSSPARE_3)
            .wbsspare4(UPDATED_WBSSPARE_4)
            .wbsspare5(UPDATED_WBSSPARE_5);

        restOutsourcingmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOutsourcingmanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedOutsourcingmanagementWbs))
            )
            .andExpect(status().isOk());

        // Validate the OutsourcingmanagementWbs in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertOutsourcingmanagementWbsUpdatableFieldsEquals(
            partialUpdatedOutsourcingmanagementWbs,
            getPersistedOutsourcingmanagementWbs(partialUpdatedOutsourcingmanagementWbs)
        );
    }

    @Test
    @Transactional
    void patchNonExistingOutsourcingmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmanagementWbs.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOutsourcingmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, outsourcingmanagementWbs.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(outsourcingmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOutsourcingmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(outsourcingmanagementWbs))
            )
            .andExpect(status().isBadRequest());

        // Validate the OutsourcingmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOutsourcingmanagementWbs() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        outsourcingmanagementWbs.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOutsourcingmanagementWbsMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(outsourcingmanagementWbs))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OutsourcingmanagementWbs in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOutsourcingmanagementWbs() throws Exception {
        // Initialize the database
        outsourcingmanagementWbsRepository.saveAndFlush(outsourcingmanagementWbs);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the outsourcingmanagementWbs
        restOutsourcingmanagementWbsMockMvc
            .perform(delete(ENTITY_API_URL_ID, outsourcingmanagementWbs.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return outsourcingmanagementWbsRepository.count();
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

    protected OutsourcingmanagementWbs getPersistedOutsourcingmanagementWbs(OutsourcingmanagementWbs outsourcingmanagementWbs) {
        return outsourcingmanagementWbsRepository.findById(outsourcingmanagementWbs.getId()).orElseThrow();
    }

    protected void assertPersistedOutsourcingmanagementWbsToMatchAllProperties(OutsourcingmanagementWbs expectedOutsourcingmanagementWbs) {
        assertOutsourcingmanagementWbsAllPropertiesEquals(
            expectedOutsourcingmanagementWbs,
            getPersistedOutsourcingmanagementWbs(expectedOutsourcingmanagementWbs)
        );
    }

    protected void assertPersistedOutsourcingmanagementWbsToMatchUpdatableProperties(
        OutsourcingmanagementWbs expectedOutsourcingmanagementWbs
    ) {
        assertOutsourcingmanagementWbsAllUpdatablePropertiesEquals(
            expectedOutsourcingmanagementWbs,
            getPersistedOutsourcingmanagementWbs(expectedOutsourcingmanagementWbs)
        );
    }
}

package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.DeliverablesAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.Deliverables;
import com.cvicse.jy1.repository.DeliverablesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
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
 * Integration tests for the {@link DeliverablesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DeliverablesResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PARENTCODE = "AAAAAAAAAA";
    private static final String UPDATED_PARENTCODE = "BBBBBBBBBB";

    private static final String DEFAULT_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_LEVEL = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/deliverables";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private DeliverablesRepository deliverablesRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDeliverablesMockMvc;

    private Deliverables deliverables;

    private Deliverables insertedDeliverables;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Deliverables createEntity(EntityManager em) {
        Deliverables deliverables = new Deliverables()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .parentcode(DEFAULT_PARENTCODE)
            .level(DEFAULT_LEVEL)
            .status(DEFAULT_STATUS)
            .description(DEFAULT_DESCRIPTION);
        return deliverables;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Deliverables createUpdatedEntity(EntityManager em) {
        Deliverables deliverables = new Deliverables()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .parentcode(UPDATED_PARENTCODE)
            .level(UPDATED_LEVEL)
            .status(UPDATED_STATUS)
            .description(UPDATED_DESCRIPTION);
        return deliverables;
    }

    @BeforeEach
    public void initTest() {
        deliverables = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedDeliverables != null) {
            deliverablesRepository.delete(insertedDeliverables);
            insertedDeliverables = null;
        }
    }

    @Test
    @Transactional
    void createDeliverables() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Deliverables
        var returnedDeliverables = om.readValue(
            restDeliverablesMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(deliverables)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Deliverables.class
        );

        // Validate the Deliverables in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertDeliverablesUpdatableFieldsEquals(returnedDeliverables, getPersistedDeliverables(returnedDeliverables));

        insertedDeliverables = returnedDeliverables;
    }

    @Test
    @Transactional
    void createDeliverablesWithExistingId() throws Exception {
        // Create the Deliverables with an existing ID
        deliverables.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDeliverablesMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(deliverables)))
            .andExpect(status().isBadRequest());

        // Validate the Deliverables in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDeliverables() throws Exception {
        // Initialize the database
        insertedDeliverables = deliverablesRepository.saveAndFlush(deliverables);

        // Get all the deliverablesList
        restDeliverablesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(deliverables.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].parentcode").value(hasItem(DEFAULT_PARENTCODE)))
            .andExpect(jsonPath("$.[*].level").value(hasItem(DEFAULT_LEVEL)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }

    @Test
    @Transactional
    void getDeliverables() throws Exception {
        // Initialize the database
        insertedDeliverables = deliverablesRepository.saveAndFlush(deliverables);

        // Get the deliverables
        restDeliverablesMockMvc
            .perform(get(ENTITY_API_URL_ID, deliverables.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(deliverables.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.parentcode").value(DEFAULT_PARENTCODE))
            .andExpect(jsonPath("$.level").value(DEFAULT_LEVEL))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }

    @Test
    @Transactional
    void getNonExistingDeliverables() throws Exception {
        // Get the deliverables
        restDeliverablesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDeliverables() throws Exception {
        // Initialize the database
        insertedDeliverables = deliverablesRepository.saveAndFlush(deliverables);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the deliverables
        Deliverables updatedDeliverables = deliverablesRepository.findById(deliverables.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedDeliverables are not directly saved in db
        em.detach(updatedDeliverables);
        updatedDeliverables
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .parentcode(UPDATED_PARENTCODE)
            .level(UPDATED_LEVEL)
            .status(UPDATED_STATUS)
            .description(UPDATED_DESCRIPTION);

        restDeliverablesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedDeliverables.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedDeliverables))
            )
            .andExpect(status().isOk());

        // Validate the Deliverables in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedDeliverablesToMatchAllProperties(updatedDeliverables);
    }

    @Test
    @Transactional
    void putNonExistingDeliverables() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        deliverables.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliverablesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, deliverables.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(deliverables))
            )
            .andExpect(status().isBadRequest());

        // Validate the Deliverables in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDeliverables() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        deliverables.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliverablesMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(deliverables))
            )
            .andExpect(status().isBadRequest());

        // Validate the Deliverables in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDeliverables() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        deliverables.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliverablesMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(deliverables)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Deliverables in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDeliverablesWithPatch() throws Exception {
        // Initialize the database
        insertedDeliverables = deliverablesRepository.saveAndFlush(deliverables);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the deliverables using partial update
        Deliverables partialUpdatedDeliverables = new Deliverables();
        partialUpdatedDeliverables.setId(deliverables.getId());

        partialUpdatedDeliverables.parentcode(UPDATED_PARENTCODE).description(UPDATED_DESCRIPTION);

        restDeliverablesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeliverables.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDeliverables))
            )
            .andExpect(status().isOk());

        // Validate the Deliverables in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDeliverablesUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedDeliverables, deliverables),
            getPersistedDeliverables(deliverables)
        );
    }

    @Test
    @Transactional
    void fullUpdateDeliverablesWithPatch() throws Exception {
        // Initialize the database
        insertedDeliverables = deliverablesRepository.saveAndFlush(deliverables);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the deliverables using partial update
        Deliverables partialUpdatedDeliverables = new Deliverables();
        partialUpdatedDeliverables.setId(deliverables.getId());

        partialUpdatedDeliverables
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .parentcode(UPDATED_PARENTCODE)
            .level(UPDATED_LEVEL)
            .status(UPDATED_STATUS)
            .description(UPDATED_DESCRIPTION);

        restDeliverablesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDeliverables.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedDeliverables))
            )
            .andExpect(status().isOk());

        // Validate the Deliverables in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertDeliverablesUpdatableFieldsEquals(partialUpdatedDeliverables, getPersistedDeliverables(partialUpdatedDeliverables));
    }

    @Test
    @Transactional
    void patchNonExistingDeliverables() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        deliverables.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDeliverablesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, deliverables.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(deliverables))
            )
            .andExpect(status().isBadRequest());

        // Validate the Deliverables in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDeliverables() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        deliverables.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliverablesMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(deliverables))
            )
            .andExpect(status().isBadRequest());

        // Validate the Deliverables in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDeliverables() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        deliverables.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDeliverablesMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(deliverables)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Deliverables in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDeliverables() throws Exception {
        // Initialize the database
        insertedDeliverables = deliverablesRepository.saveAndFlush(deliverables);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the deliverables
        restDeliverablesMockMvc
            .perform(delete(ENTITY_API_URL_ID, deliverables.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return deliverablesRepository.count();
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

    protected Deliverables getPersistedDeliverables(Deliverables deliverables) {
        return deliverablesRepository.findById(deliverables.getId()).orElseThrow();
    }

    protected void assertPersistedDeliverablesToMatchAllProperties(Deliverables expectedDeliverables) {
        assertDeliverablesAllPropertiesEquals(expectedDeliverables, getPersistedDeliverables(expectedDeliverables));
    }

    protected void assertPersistedDeliverablesToMatchUpdatableProperties(Deliverables expectedDeliverables) {
        assertDeliverablesAllUpdatablePropertiesEquals(expectedDeliverables, getPersistedDeliverables(expectedDeliverables));
    }
}

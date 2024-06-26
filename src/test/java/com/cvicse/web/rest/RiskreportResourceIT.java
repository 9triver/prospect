package com.cvicse.web.rest;

import static com.cvicse.domain.RiskreportAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Riskreport;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.repository.RiskreportRepository;
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
 * Integration tests for the {@link RiskreportResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RiskreportResourceIT {

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_RISKREPORTNAME = "AAAAAAAAAA";
    private static final String UPDATED_RISKREPORTNAME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_RELEASETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_RELEASETIME = LocalDate.now(ZoneId.systemDefault());

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/riskreports";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private RiskreportRepository riskreportRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRiskreportMockMvc;

    private Riskreport riskreport;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Riskreport createEntity(EntityManager em) {
        Riskreport riskreport = new Riskreport()
            .type(DEFAULT_TYPE)
            .riskreportname(DEFAULT_RISKREPORTNAME)
            .releasetime(DEFAULT_RELEASETIME)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return riskreport;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Riskreport createUpdatedEntity(EntityManager em) {
        Riskreport riskreport = new Riskreport()
            .type(UPDATED_TYPE)
            .riskreportname(UPDATED_RISKREPORTNAME)
            .releasetime(UPDATED_RELEASETIME)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return riskreport;
    }

    @BeforeEach
    public void initTest() {
        riskreport = createEntity(em);
    }

    @Test
    @Transactional
    void createRiskreport() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Riskreport
        var returnedRiskreport = om.readValue(
            restRiskreportMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskreport)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Riskreport.class
        );

        // Validate the Riskreport in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertRiskreportUpdatableFieldsEquals(returnedRiskreport, getPersistedRiskreport(returnedRiskreport));
    }

    @Test
    @Transactional
    void createRiskreportWithExistingId() throws Exception {
        // Create the Riskreport with an existing ID
        riskreport.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRiskreportMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskreport)))
            .andExpect(status().isBadRequest());

        // Validate the Riskreport in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRiskreports() throws Exception {
        // Initialize the database
        riskreportRepository.saveAndFlush(riskreport);

        // Get all the riskreportList
        restRiskreportMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(riskreport.getId())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].riskreportname").value(hasItem(DEFAULT_RISKREPORTNAME)))
            .andExpect(jsonPath("$.[*].releasetime").value(hasItem(DEFAULT_RELEASETIME.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getRiskreport() throws Exception {
        // Initialize the database
        riskreportRepository.saveAndFlush(riskreport);

        // Get the riskreport
        restRiskreportMockMvc
            .perform(get(ENTITY_API_URL_ID, riskreport.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(riskreport.getId()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.riskreportname").value(DEFAULT_RISKREPORTNAME))
            .andExpect(jsonPath("$.releasetime").value(DEFAULT_RELEASETIME.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingRiskreport() throws Exception {
        // Get the riskreport
        restRiskreportMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRiskreport() throws Exception {
        // Initialize the database
        riskreportRepository.saveAndFlush(riskreport);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskreport
        Riskreport updatedRiskreport = riskreportRepository.findById(riskreport.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedRiskreport are not directly saved in db
        em.detach(updatedRiskreport);
        updatedRiskreport
            .type(UPDATED_TYPE)
            .riskreportname(UPDATED_RISKREPORTNAME)
            .releasetime(UPDATED_RELEASETIME)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restRiskreportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedRiskreport.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedRiskreport))
            )
            .andExpect(status().isOk());

        // Validate the Riskreport in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedRiskreportToMatchAllProperties(updatedRiskreport);
    }

    @Test
    @Transactional
    void putNonExistingRiskreport() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskreport.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskreportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, riskreport.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskreport))
            )
            .andExpect(status().isBadRequest());

        // Validate the Riskreport in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRiskreport() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskreport.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskreportMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(riskreport))
            )
            .andExpect(status().isBadRequest());

        // Validate the Riskreport in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRiskreport() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskreport.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskreportMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(riskreport)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Riskreport in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRiskreportWithPatch() throws Exception {
        // Initialize the database
        riskreportRepository.saveAndFlush(riskreport);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskreport using partial update
        Riskreport partialUpdatedRiskreport = new Riskreport();
        partialUpdatedRiskreport.setId(riskreport.getId());

        partialUpdatedRiskreport.type(UPDATED_TYPE);

        restRiskreportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskreport.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskreport))
            )
            .andExpect(status().isOk());

        // Validate the Riskreport in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskreportUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedRiskreport, riskreport),
            getPersistedRiskreport(riskreport)
        );
    }

    @Test
    @Transactional
    void fullUpdateRiskreportWithPatch() throws Exception {
        // Initialize the database
        riskreportRepository.saveAndFlush(riskreport);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the riskreport using partial update
        Riskreport partialUpdatedRiskreport = new Riskreport();
        partialUpdatedRiskreport.setId(riskreport.getId());

        partialUpdatedRiskreport
            .type(UPDATED_TYPE)
            .riskreportname(UPDATED_RISKREPORTNAME)
            .releasetime(UPDATED_RELEASETIME)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restRiskreportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRiskreport.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedRiskreport))
            )
            .andExpect(status().isOk());

        // Validate the Riskreport in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertRiskreportUpdatableFieldsEquals(partialUpdatedRiskreport, getPersistedRiskreport(partialUpdatedRiskreport));
    }

    @Test
    @Transactional
    void patchNonExistingRiskreport() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskreport.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRiskreportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, riskreport.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskreport))
            )
            .andExpect(status().isBadRequest());

        // Validate the Riskreport in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRiskreport() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskreport.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskreportMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(riskreport))
            )
            .andExpect(status().isBadRequest());

        // Validate the Riskreport in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRiskreport() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        riskreport.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRiskreportMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(riskreport)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Riskreport in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRiskreport() throws Exception {
        // Initialize the database
        riskreportRepository.saveAndFlush(riskreport);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the riskreport
        restRiskreportMockMvc
            .perform(delete(ENTITY_API_URL_ID, riskreport.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return riskreportRepository.count();
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

    protected Riskreport getPersistedRiskreport(Riskreport riskreport) {
        return riskreportRepository.findById(riskreport.getId()).orElseThrow();
    }

    protected void assertPersistedRiskreportToMatchAllProperties(Riskreport expectedRiskreport) {
        assertRiskreportAllPropertiesEquals(expectedRiskreport, getPersistedRiskreport(expectedRiskreport));
    }

    protected void assertPersistedRiskreportToMatchUpdatableProperties(Riskreport expectedRiskreport) {
        assertRiskreportAllUpdatablePropertiesEquals(expectedRiskreport, getPersistedRiskreport(expectedRiskreport));
    }
}

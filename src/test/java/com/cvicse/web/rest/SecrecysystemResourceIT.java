package com.cvicse.web.rest;

import static com.cvicse.domain.SecrecysystemAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Secrecysystem;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.SecrecysystemRepository;
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
 * Integration tests for the {@link SecrecysystemResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SecrecysystemResourceIT {

    private static final String DEFAULT_PUBLISHEDBY = "AAAAAAAAAA";
    private static final String UPDATED_PUBLISHEDBY = "BBBBBBBBBB";

    private static final String DEFAULT_DOCUMENTNAME = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENTNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_DOCUMENTTYPE = 1;
    private static final Integer UPDATED_DOCUMENTTYPE = 2;

    private static final Long DEFAULT_DOCUMENTSIZE = 1L;
    private static final Long UPDATED_DOCUMENTSIZE = 2L;

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/secrecysystems";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SecrecysystemRepository secrecysystemRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSecrecysystemMockMvc;

    private Secrecysystem secrecysystem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Secrecysystem createEntity(EntityManager em) {
        Secrecysystem secrecysystem = new Secrecysystem()
            .publishedby(DEFAULT_PUBLISHEDBY)
            .documentname(DEFAULT_DOCUMENTNAME)
            .documenttype(DEFAULT_DOCUMENTTYPE)
            .documentsize(DEFAULT_DOCUMENTSIZE)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return secrecysystem;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Secrecysystem createUpdatedEntity(EntityManager em) {
        Secrecysystem secrecysystem = new Secrecysystem()
            .publishedby(UPDATED_PUBLISHEDBY)
            .documentname(UPDATED_DOCUMENTNAME)
            .documenttype(UPDATED_DOCUMENTTYPE)
            .documentsize(UPDATED_DOCUMENTSIZE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return secrecysystem;
    }

    @BeforeEach
    public void initTest() {
        secrecysystem = createEntity(em);
    }

    @Test
    @Transactional
    void createSecrecysystem() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Secrecysystem
        var returnedSecrecysystem = om.readValue(
            restSecrecysystemMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(secrecysystem)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Secrecysystem.class
        );

        // Validate the Secrecysystem in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertSecrecysystemUpdatableFieldsEquals(returnedSecrecysystem, getPersistedSecrecysystem(returnedSecrecysystem));
    }

    @Test
    @Transactional
    void createSecrecysystemWithExistingId() throws Exception {
        // Create the Secrecysystem with an existing ID
        secrecysystem.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSecrecysystemMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(secrecysystem)))
            .andExpect(status().isBadRequest());

        // Validate the Secrecysystem in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSecrecysystems() throws Exception {
        // Initialize the database
        secrecysystemRepository.saveAndFlush(secrecysystem);

        // Get all the secrecysystemList
        restSecrecysystemMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(secrecysystem.getId())))
            .andExpect(jsonPath("$.[*].publishedby").value(hasItem(DEFAULT_PUBLISHEDBY)))
            .andExpect(jsonPath("$.[*].documentname").value(hasItem(DEFAULT_DOCUMENTNAME)))
            .andExpect(jsonPath("$.[*].documenttype").value(hasItem(DEFAULT_DOCUMENTTYPE)))
            .andExpect(jsonPath("$.[*].documentsize").value(hasItem(DEFAULT_DOCUMENTSIZE.intValue())))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getSecrecysystem() throws Exception {
        // Initialize the database
        secrecysystemRepository.saveAndFlush(secrecysystem);

        // Get the secrecysystem
        restSecrecysystemMockMvc
            .perform(get(ENTITY_API_URL_ID, secrecysystem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(secrecysystem.getId()))
            .andExpect(jsonPath("$.publishedby").value(DEFAULT_PUBLISHEDBY))
            .andExpect(jsonPath("$.documentname").value(DEFAULT_DOCUMENTNAME))
            .andExpect(jsonPath("$.documenttype").value(DEFAULT_DOCUMENTTYPE))
            .andExpect(jsonPath("$.documentsize").value(DEFAULT_DOCUMENTSIZE.intValue()))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingSecrecysystem() throws Exception {
        // Get the secrecysystem
        restSecrecysystemMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSecrecysystem() throws Exception {
        // Initialize the database
        secrecysystemRepository.saveAndFlush(secrecysystem);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the secrecysystem
        Secrecysystem updatedSecrecysystem = secrecysystemRepository.findById(secrecysystem.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedSecrecysystem are not directly saved in db
        em.detach(updatedSecrecysystem);
        updatedSecrecysystem
            .publishedby(UPDATED_PUBLISHEDBY)
            .documentname(UPDATED_DOCUMENTNAME)
            .documenttype(UPDATED_DOCUMENTTYPE)
            .documentsize(UPDATED_DOCUMENTSIZE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restSecrecysystemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSecrecysystem.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedSecrecysystem))
            )
            .andExpect(status().isOk());

        // Validate the Secrecysystem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSecrecysystemToMatchAllProperties(updatedSecrecysystem);
    }

    @Test
    @Transactional
    void putNonExistingSecrecysystem() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecysystem.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecrecysystemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, secrecysystem.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(secrecysystem))
            )
            .andExpect(status().isBadRequest());

        // Validate the Secrecysystem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSecrecysystem() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecysystem.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecrecysystemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(secrecysystem))
            )
            .andExpect(status().isBadRequest());

        // Validate the Secrecysystem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSecrecysystem() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecysystem.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecrecysystemMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(secrecysystem)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Secrecysystem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSecrecysystemWithPatch() throws Exception {
        // Initialize the database
        secrecysystemRepository.saveAndFlush(secrecysystem);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the secrecysystem using partial update
        Secrecysystem partialUpdatedSecrecysystem = new Secrecysystem();
        partialUpdatedSecrecysystem.setId(secrecysystem.getId());

        partialUpdatedSecrecysystem.documentname(UPDATED_DOCUMENTNAME).documentsize(UPDATED_DOCUMENTSIZE).auditStatus(UPDATED_AUDIT_STATUS);

        restSecrecysystemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSecrecysystem.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSecrecysystem))
            )
            .andExpect(status().isOk());

        // Validate the Secrecysystem in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSecrecysystemUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSecrecysystem, secrecysystem),
            getPersistedSecrecysystem(secrecysystem)
        );
    }

    @Test
    @Transactional
    void fullUpdateSecrecysystemWithPatch() throws Exception {
        // Initialize the database
        secrecysystemRepository.saveAndFlush(secrecysystem);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the secrecysystem using partial update
        Secrecysystem partialUpdatedSecrecysystem = new Secrecysystem();
        partialUpdatedSecrecysystem.setId(secrecysystem.getId());

        partialUpdatedSecrecysystem
            .publishedby(UPDATED_PUBLISHEDBY)
            .documentname(UPDATED_DOCUMENTNAME)
            .documenttype(UPDATED_DOCUMENTTYPE)
            .documentsize(UPDATED_DOCUMENTSIZE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restSecrecysystemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSecrecysystem.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSecrecysystem))
            )
            .andExpect(status().isOk());

        // Validate the Secrecysystem in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSecrecysystemUpdatableFieldsEquals(partialUpdatedSecrecysystem, getPersistedSecrecysystem(partialUpdatedSecrecysystem));
    }

    @Test
    @Transactional
    void patchNonExistingSecrecysystem() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecysystem.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecrecysystemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, secrecysystem.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(secrecysystem))
            )
            .andExpect(status().isBadRequest());

        // Validate the Secrecysystem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSecrecysystem() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecysystem.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecrecysystemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(secrecysystem))
            )
            .andExpect(status().isBadRequest());

        // Validate the Secrecysystem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSecrecysystem() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecysystem.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecrecysystemMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(secrecysystem)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Secrecysystem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSecrecysystem() throws Exception {
        // Initialize the database
        secrecysystemRepository.saveAndFlush(secrecysystem);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the secrecysystem
        restSecrecysystemMockMvc
            .perform(delete(ENTITY_API_URL_ID, secrecysystem.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return secrecysystemRepository.count();
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

    protected Secrecysystem getPersistedSecrecysystem(Secrecysystem secrecysystem) {
        return secrecysystemRepository.findById(secrecysystem.getId()).orElseThrow();
    }

    protected void assertPersistedSecrecysystemToMatchAllProperties(Secrecysystem expectedSecrecysystem) {
        assertSecrecysystemAllPropertiesEquals(expectedSecrecysystem, getPersistedSecrecysystem(expectedSecrecysystem));
    }

    protected void assertPersistedSecrecysystemToMatchUpdatableProperties(Secrecysystem expectedSecrecysystem) {
        assertSecrecysystemAllUpdatablePropertiesEquals(expectedSecrecysystem, getPersistedSecrecysystem(expectedSecrecysystem));
    }
}

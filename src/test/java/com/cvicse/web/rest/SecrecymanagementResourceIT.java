package com.cvicse.web.rest;

import static com.cvicse.domain.SecrecymanagementAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Secrecymanagement;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.SecrecymanagementRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link SecrecymanagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SecrecymanagementResourceIT {

    private static final Long DEFAULT_SECRECYID = 1L;
    private static final Long UPDATED_SECRECYID = 2L;

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

    private static final String ENTITY_API_URL = "/api/secrecymanagements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SecrecymanagementRepository secrecymanagementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSecrecymanagementMockMvc;

    private Secrecymanagement secrecymanagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Secrecymanagement createEntity(EntityManager em) {
        Secrecymanagement secrecymanagement = new Secrecymanagement()
            .secrecyid(DEFAULT_SECRECYID)
            .publishedby(DEFAULT_PUBLISHEDBY)
            .documentname(DEFAULT_DOCUMENTNAME)
            .documenttype(DEFAULT_DOCUMENTTYPE)
            .documentsize(DEFAULT_DOCUMENTSIZE)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return secrecymanagement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Secrecymanagement createUpdatedEntity(EntityManager em) {
        Secrecymanagement secrecymanagement = new Secrecymanagement()
            .secrecyid(UPDATED_SECRECYID)
            .publishedby(UPDATED_PUBLISHEDBY)
            .documentname(UPDATED_DOCUMENTNAME)
            .documenttype(UPDATED_DOCUMENTTYPE)
            .documentsize(UPDATED_DOCUMENTSIZE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return secrecymanagement;
    }

    @BeforeEach
    public void initTest() {
        secrecymanagement = createEntity(em);
    }

    @Test
    @Transactional
    void createSecrecymanagement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Secrecymanagement
        var returnedSecrecymanagement = om.readValue(
            restSecrecymanagementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(secrecymanagement)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Secrecymanagement.class
        );

        // Validate the Secrecymanagement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertSecrecymanagementUpdatableFieldsEquals(returnedSecrecymanagement, getPersistedSecrecymanagement(returnedSecrecymanagement));
    }

    @Test
    @Transactional
    void createSecrecymanagementWithExistingId() throws Exception {
        // Create the Secrecymanagement with an existing ID
        secrecymanagement.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSecrecymanagementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(secrecymanagement)))
            .andExpect(status().isBadRequest());

        // Validate the Secrecymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSecrecymanagements() throws Exception {
        // Initialize the database
        secrecymanagementRepository.saveAndFlush(secrecymanagement);

        // Get all the secrecymanagementList
        restSecrecymanagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(secrecymanagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].secrecyid").value(hasItem(DEFAULT_SECRECYID.intValue())))
            .andExpect(jsonPath("$.[*].publishedby").value(hasItem(DEFAULT_PUBLISHEDBY)))
            .andExpect(jsonPath("$.[*].documentname").value(hasItem(DEFAULT_DOCUMENTNAME)))
            .andExpect(jsonPath("$.[*].documenttype").value(hasItem(DEFAULT_DOCUMENTTYPE)))
            .andExpect(jsonPath("$.[*].documentsize").value(hasItem(DEFAULT_DOCUMENTSIZE.intValue())))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getSecrecymanagement() throws Exception {
        // Initialize the database
        secrecymanagementRepository.saveAndFlush(secrecymanagement);

        // Get the secrecymanagement
        restSecrecymanagementMockMvc
            .perform(get(ENTITY_API_URL_ID, secrecymanagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(secrecymanagement.getId().intValue()))
            .andExpect(jsonPath("$.secrecyid").value(DEFAULT_SECRECYID.intValue()))
            .andExpect(jsonPath("$.publishedby").value(DEFAULT_PUBLISHEDBY))
            .andExpect(jsonPath("$.documentname").value(DEFAULT_DOCUMENTNAME))
            .andExpect(jsonPath("$.documenttype").value(DEFAULT_DOCUMENTTYPE))
            .andExpect(jsonPath("$.documentsize").value(DEFAULT_DOCUMENTSIZE.intValue()))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingSecrecymanagement() throws Exception {
        // Get the secrecymanagement
        restSecrecymanagementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSecrecymanagement() throws Exception {
        // Initialize the database
        secrecymanagementRepository.saveAndFlush(secrecymanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the secrecymanagement
        Secrecymanagement updatedSecrecymanagement = secrecymanagementRepository.findById(secrecymanagement.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedSecrecymanagement are not directly saved in db
        em.detach(updatedSecrecymanagement);
        updatedSecrecymanagement
            .secrecyid(UPDATED_SECRECYID)
            .publishedby(UPDATED_PUBLISHEDBY)
            .documentname(UPDATED_DOCUMENTNAME)
            .documenttype(UPDATED_DOCUMENTTYPE)
            .documentsize(UPDATED_DOCUMENTSIZE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restSecrecymanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSecrecymanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedSecrecymanagement))
            )
            .andExpect(status().isOk());

        // Validate the Secrecymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSecrecymanagementToMatchAllProperties(updatedSecrecymanagement);
    }

    @Test
    @Transactional
    void putNonExistingSecrecymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecymanagement.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecrecymanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, secrecymanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(secrecymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Secrecymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSecrecymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecymanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecrecymanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(secrecymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Secrecymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSecrecymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecymanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecrecymanagementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(secrecymanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Secrecymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSecrecymanagementWithPatch() throws Exception {
        // Initialize the database
        secrecymanagementRepository.saveAndFlush(secrecymanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the secrecymanagement using partial update
        Secrecymanagement partialUpdatedSecrecymanagement = new Secrecymanagement();
        partialUpdatedSecrecymanagement.setId(secrecymanagement.getId());

        restSecrecymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSecrecymanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSecrecymanagement))
            )
            .andExpect(status().isOk());

        // Validate the Secrecymanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSecrecymanagementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSecrecymanagement, secrecymanagement),
            getPersistedSecrecymanagement(secrecymanagement)
        );
    }

    @Test
    @Transactional
    void fullUpdateSecrecymanagementWithPatch() throws Exception {
        // Initialize the database
        secrecymanagementRepository.saveAndFlush(secrecymanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the secrecymanagement using partial update
        Secrecymanagement partialUpdatedSecrecymanagement = new Secrecymanagement();
        partialUpdatedSecrecymanagement.setId(secrecymanagement.getId());

        partialUpdatedSecrecymanagement
            .secrecyid(UPDATED_SECRECYID)
            .publishedby(UPDATED_PUBLISHEDBY)
            .documentname(UPDATED_DOCUMENTNAME)
            .documenttype(UPDATED_DOCUMENTTYPE)
            .documentsize(UPDATED_DOCUMENTSIZE)
            .secretlevel(UPDATED_SECRETLEVEL)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restSecrecymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSecrecymanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSecrecymanagement))
            )
            .andExpect(status().isOk());

        // Validate the Secrecymanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSecrecymanagementUpdatableFieldsEquals(
            partialUpdatedSecrecymanagement,
            getPersistedSecrecymanagement(partialUpdatedSecrecymanagement)
        );
    }

    @Test
    @Transactional
    void patchNonExistingSecrecymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecymanagement.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSecrecymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, secrecymanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(secrecymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Secrecymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSecrecymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecymanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecrecymanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(secrecymanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Secrecymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSecrecymanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        secrecymanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSecrecymanagementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(secrecymanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Secrecymanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSecrecymanagement() throws Exception {
        // Initialize the database
        secrecymanagementRepository.saveAndFlush(secrecymanagement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the secrecymanagement
        restSecrecymanagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, secrecymanagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return secrecymanagementRepository.count();
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

    protected Secrecymanagement getPersistedSecrecymanagement(Secrecymanagement secrecymanagement) {
        return secrecymanagementRepository.findById(secrecymanagement.getId()).orElseThrow();
    }

    protected void assertPersistedSecrecymanagementToMatchAllProperties(Secrecymanagement expectedSecrecymanagement) {
        assertSecrecymanagementAllPropertiesEquals(expectedSecrecymanagement, getPersistedSecrecymanagement(expectedSecrecymanagement));
    }

    protected void assertPersistedSecrecymanagementToMatchUpdatableProperties(Secrecymanagement expectedSecrecymanagement) {
        assertSecrecymanagementAllUpdatablePropertiesEquals(
            expectedSecrecymanagement,
            getPersistedSecrecymanagement(expectedSecrecymanagement)
        );
    }
}

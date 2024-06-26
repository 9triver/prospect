package com.cvicse.web.rest;

import static com.cvicse.domain.UnQualityAuditAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.UnQualityAudit;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.repository.UnQualityAuditRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
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
 * Integration tests for the {@link UnQualityAuditResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class UnQualityAuditResourceIT {

    private static final String DEFAULT_UNQUALITYNAME = "AAAAAAAAAA";
    private static final String UPDATED_UNQUALITYNAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_UNQUALITYTYPE = 1;
    private static final Integer UPDATED_UNQUALITYTYPE = 2;

    private static final String DEFAULT_BELONGUNITID = "AAAAAAAAAA";
    private static final String UPDATED_BELONGUNITID = "BBBBBBBBBB";

    private static final String DEFAULT_BELONGUNITNAME = "AAAAAAAAAA";
    private static final String UPDATED_BELONGUNITNAME = "BBBBBBBBBB";

    private static final String DEFAULT_AUDITTEAM = "AAAAAAAAAA";
    private static final String UPDATED_AUDITTEAM = "BBBBBBBBBB";

    private static final String DEFAULT_AUDITPERSON = "AAAAAAAAAA";
    private static final String UPDATED_AUDITPERSON = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_UNQUALITYNUM = new BigDecimal(1);
    private static final BigDecimal UPDATED_UNQUALITYNUM = new BigDecimal(2);

    private static final String DEFAULT_CREATORNAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATORNAME = "BBBBBBBBBB";

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/un-quality-audits";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private UnQualityAuditRepository unQualityAuditRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restUnQualityAuditMockMvc;

    private UnQualityAudit unQualityAudit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UnQualityAudit createEntity(EntityManager em) {
        UnQualityAudit unQualityAudit = new UnQualityAudit()
            .unqualityname(DEFAULT_UNQUALITYNAME)
            .unqualitytype(DEFAULT_UNQUALITYTYPE)
            .belongunitid(DEFAULT_BELONGUNITID)
            .belongunitname(DEFAULT_BELONGUNITNAME)
            .auditteam(DEFAULT_AUDITTEAM)
            .auditperson(DEFAULT_AUDITPERSON)
            .unqualitynum(DEFAULT_UNQUALITYNUM)
            .creatorname(DEFAULT_CREATORNAME)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return unQualityAudit;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UnQualityAudit createUpdatedEntity(EntityManager em) {
        UnQualityAudit unQualityAudit = new UnQualityAudit()
            .unqualityname(UPDATED_UNQUALITYNAME)
            .unqualitytype(UPDATED_UNQUALITYTYPE)
            .belongunitid(UPDATED_BELONGUNITID)
            .belongunitname(UPDATED_BELONGUNITNAME)
            .auditteam(UPDATED_AUDITTEAM)
            .auditperson(UPDATED_AUDITPERSON)
            .unqualitynum(UPDATED_UNQUALITYNUM)
            .creatorname(UPDATED_CREATORNAME)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return unQualityAudit;
    }

    @BeforeEach
    public void initTest() {
        unQualityAudit = createEntity(em);
    }

    @Test
    @Transactional
    void createUnQualityAudit() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the UnQualityAudit
        var returnedUnQualityAudit = om.readValue(
            restUnQualityAuditMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(unQualityAudit)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            UnQualityAudit.class
        );

        // Validate the UnQualityAudit in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertUnQualityAuditUpdatableFieldsEquals(returnedUnQualityAudit, getPersistedUnQualityAudit(returnedUnQualityAudit));
    }

    @Test
    @Transactional
    void createUnQualityAuditWithExistingId() throws Exception {
        // Create the UnQualityAudit with an existing ID
        unQualityAudit.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restUnQualityAuditMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(unQualityAudit)))
            .andExpect(status().isBadRequest());

        // Validate the UnQualityAudit in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllUnQualityAudits() throws Exception {
        // Initialize the database
        unQualityAuditRepository.saveAndFlush(unQualityAudit);

        // Get all the unQualityAuditList
        restUnQualityAuditMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(unQualityAudit.getId())))
            .andExpect(jsonPath("$.[*].unqualityname").value(hasItem(DEFAULT_UNQUALITYNAME)))
            .andExpect(jsonPath("$.[*].unqualitytype").value(hasItem(DEFAULT_UNQUALITYTYPE)))
            .andExpect(jsonPath("$.[*].belongunitid").value(hasItem(DEFAULT_BELONGUNITID)))
            .andExpect(jsonPath("$.[*].belongunitname").value(hasItem(DEFAULT_BELONGUNITNAME)))
            .andExpect(jsonPath("$.[*].auditteam").value(hasItem(DEFAULT_AUDITTEAM)))
            .andExpect(jsonPath("$.[*].auditperson").value(hasItem(DEFAULT_AUDITPERSON)))
            .andExpect(jsonPath("$.[*].unqualitynum").value(hasItem(sameNumber(DEFAULT_UNQUALITYNUM))))
            .andExpect(jsonPath("$.[*].creatorname").value(hasItem(DEFAULT_CREATORNAME)))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getUnQualityAudit() throws Exception {
        // Initialize the database
        unQualityAuditRepository.saveAndFlush(unQualityAudit);

        // Get the unQualityAudit
        restUnQualityAuditMockMvc
            .perform(get(ENTITY_API_URL_ID, unQualityAudit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(unQualityAudit.getId()))
            .andExpect(jsonPath("$.unqualityname").value(DEFAULT_UNQUALITYNAME))
            .andExpect(jsonPath("$.unqualitytype").value(DEFAULT_UNQUALITYTYPE))
            .andExpect(jsonPath("$.belongunitid").value(DEFAULT_BELONGUNITID))
            .andExpect(jsonPath("$.belongunitname").value(DEFAULT_BELONGUNITNAME))
            .andExpect(jsonPath("$.auditteam").value(DEFAULT_AUDITTEAM))
            .andExpect(jsonPath("$.auditperson").value(DEFAULT_AUDITPERSON))
            .andExpect(jsonPath("$.unqualitynum").value(sameNumber(DEFAULT_UNQUALITYNUM)))
            .andExpect(jsonPath("$.creatorname").value(DEFAULT_CREATORNAME))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingUnQualityAudit() throws Exception {
        // Get the unQualityAudit
        restUnQualityAuditMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingUnQualityAudit() throws Exception {
        // Initialize the database
        unQualityAuditRepository.saveAndFlush(unQualityAudit);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the unQualityAudit
        UnQualityAudit updatedUnQualityAudit = unQualityAuditRepository.findById(unQualityAudit.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedUnQualityAudit are not directly saved in db
        em.detach(updatedUnQualityAudit);
        updatedUnQualityAudit
            .unqualityname(UPDATED_UNQUALITYNAME)
            .unqualitytype(UPDATED_UNQUALITYTYPE)
            .belongunitid(UPDATED_BELONGUNITID)
            .belongunitname(UPDATED_BELONGUNITNAME)
            .auditteam(UPDATED_AUDITTEAM)
            .auditperson(UPDATED_AUDITPERSON)
            .unqualitynum(UPDATED_UNQUALITYNUM)
            .creatorname(UPDATED_CREATORNAME)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restUnQualityAuditMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedUnQualityAudit.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedUnQualityAudit))
            )
            .andExpect(status().isOk());

        // Validate the UnQualityAudit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedUnQualityAuditToMatchAllProperties(updatedUnQualityAudit);
    }

    @Test
    @Transactional
    void putNonExistingUnQualityAudit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        unQualityAudit.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUnQualityAuditMockMvc
            .perform(
                put(ENTITY_API_URL_ID, unQualityAudit.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(unQualityAudit))
            )
            .andExpect(status().isBadRequest());

        // Validate the UnQualityAudit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchUnQualityAudit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        unQualityAudit.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnQualityAuditMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(unQualityAudit))
            )
            .andExpect(status().isBadRequest());

        // Validate the UnQualityAudit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamUnQualityAudit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        unQualityAudit.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnQualityAuditMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(unQualityAudit)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UnQualityAudit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateUnQualityAuditWithPatch() throws Exception {
        // Initialize the database
        unQualityAuditRepository.saveAndFlush(unQualityAudit);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the unQualityAudit using partial update
        UnQualityAudit partialUpdatedUnQualityAudit = new UnQualityAudit();
        partialUpdatedUnQualityAudit.setId(unQualityAudit.getId());

        partialUpdatedUnQualityAudit
            .unqualitytype(UPDATED_UNQUALITYTYPE)
            .belongunitid(UPDATED_BELONGUNITID)
            .auditteam(UPDATED_AUDITTEAM)
            .creatorname(UPDATED_CREATORNAME);

        restUnQualityAuditMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUnQualityAudit.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedUnQualityAudit))
            )
            .andExpect(status().isOk());

        // Validate the UnQualityAudit in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertUnQualityAuditUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedUnQualityAudit, unQualityAudit),
            getPersistedUnQualityAudit(unQualityAudit)
        );
    }

    @Test
    @Transactional
    void fullUpdateUnQualityAuditWithPatch() throws Exception {
        // Initialize the database
        unQualityAuditRepository.saveAndFlush(unQualityAudit);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the unQualityAudit using partial update
        UnQualityAudit partialUpdatedUnQualityAudit = new UnQualityAudit();
        partialUpdatedUnQualityAudit.setId(unQualityAudit.getId());

        partialUpdatedUnQualityAudit
            .unqualityname(UPDATED_UNQUALITYNAME)
            .unqualitytype(UPDATED_UNQUALITYTYPE)
            .belongunitid(UPDATED_BELONGUNITID)
            .belongunitname(UPDATED_BELONGUNITNAME)
            .auditteam(UPDATED_AUDITTEAM)
            .auditperson(UPDATED_AUDITPERSON)
            .unqualitynum(UPDATED_UNQUALITYNUM)
            .creatorname(UPDATED_CREATORNAME)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restUnQualityAuditMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedUnQualityAudit.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedUnQualityAudit))
            )
            .andExpect(status().isOk());

        // Validate the UnQualityAudit in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertUnQualityAuditUpdatableFieldsEquals(partialUpdatedUnQualityAudit, getPersistedUnQualityAudit(partialUpdatedUnQualityAudit));
    }

    @Test
    @Transactional
    void patchNonExistingUnQualityAudit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        unQualityAudit.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUnQualityAuditMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, unQualityAudit.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(unQualityAudit))
            )
            .andExpect(status().isBadRequest());

        // Validate the UnQualityAudit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchUnQualityAudit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        unQualityAudit.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnQualityAuditMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(unQualityAudit))
            )
            .andExpect(status().isBadRequest());

        // Validate the UnQualityAudit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamUnQualityAudit() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        unQualityAudit.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restUnQualityAuditMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(unQualityAudit)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the UnQualityAudit in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteUnQualityAudit() throws Exception {
        // Initialize the database
        unQualityAuditRepository.saveAndFlush(unQualityAudit);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the unQualityAudit
        restUnQualityAuditMockMvc
            .perform(delete(ENTITY_API_URL_ID, unQualityAudit.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return unQualityAuditRepository.count();
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

    protected UnQualityAudit getPersistedUnQualityAudit(UnQualityAudit unQualityAudit) {
        return unQualityAuditRepository.findById(unQualityAudit.getId()).orElseThrow();
    }

    protected void assertPersistedUnQualityAuditToMatchAllProperties(UnQualityAudit expectedUnQualityAudit) {
        assertUnQualityAuditAllPropertiesEquals(expectedUnQualityAudit, getPersistedUnQualityAudit(expectedUnQualityAudit));
    }

    protected void assertPersistedUnQualityAuditToMatchUpdatableProperties(UnQualityAudit expectedUnQualityAudit) {
        assertUnQualityAuditAllUpdatablePropertiesEquals(expectedUnQualityAudit, getPersistedUnQualityAudit(expectedUnQualityAudit));
    }
}

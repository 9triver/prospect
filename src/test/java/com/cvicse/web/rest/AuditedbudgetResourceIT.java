package com.cvicse.web.rest;

import static com.cvicse.domain.AuditedbudgetAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Auditedbudget;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.AuditedbudgetRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
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
 * Integration tests for the {@link AuditedbudgetResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AuditedbudgetResourceIT {

    private static final LocalDate DEFAULT_CREATETIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATETIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATORNAME = "AAAAAAAAAA";
    private static final String UPDATED_CREATORNAME = "BBBBBBBBBB";

    private static final Secretlevel DEFAULT_SECRETLEVEL = Secretlevel.SECRET;
    private static final Secretlevel UPDATED_SECRETLEVEL = Secretlevel.NOSECTET_INTERNAL;

    private static final Long DEFAULT_YEAR = 1L;
    private static final Long UPDATED_YEAR = 2L;

    private static final BigDecimal DEFAULT_BUDGIT = new BigDecimal(1);
    private static final BigDecimal UPDATED_BUDGIT = new BigDecimal(2);

    private static final String DEFAULT_DAPARTMENTID = "AAAAAAAAAA";
    private static final String UPDATED_DAPARTMENTID = "BBBBBBBBBB";

    private static final Long DEFAULT_DRAFTAPPROVAL = 1L;
    private static final Long UPDATED_DRAFTAPPROVAL = 2L;

    private static final BigDecimal DEFAULT_TOTALBUDGETID = new BigDecimal(1);
    private static final BigDecimal UPDATED_TOTALBUDGETID = new BigDecimal(2);

    private static final BigDecimal DEFAULT_UNITBUDGETID = new BigDecimal(1);
    private static final BigDecimal UPDATED_UNITBUDGETID = new BigDecimal(2);

    private static final String DEFAULT_DOCUMENTID = "AAAAAAAAAA";
    private static final String UPDATED_DOCUMENTID = "BBBBBBBBBB";

    private static final String DEFAULT_MAINTAINERID = "AAAAAAAAAA";
    private static final String UPDATED_MAINTAINERID = "BBBBBBBBBB";

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/auditedbudgets";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AuditedbudgetRepository auditedbudgetRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAuditedbudgetMockMvc;

    private Auditedbudget auditedbudget;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Auditedbudget createEntity(EntityManager em) {
        Auditedbudget auditedbudget = new Auditedbudget()
            .createtime(DEFAULT_CREATETIME)
            .creatorname(DEFAULT_CREATORNAME)
            .secretlevel(DEFAULT_SECRETLEVEL)
            .year(DEFAULT_YEAR)
            .budgit(DEFAULT_BUDGIT)
            .dapartmentid(DEFAULT_DAPARTMENTID)
            .draftapproval(DEFAULT_DRAFTAPPROVAL)
            .totalbudgetid(DEFAULT_TOTALBUDGETID)
            .unitbudgetid(DEFAULT_UNITBUDGETID)
            .documentid(DEFAULT_DOCUMENTID)
            .maintainerid(DEFAULT_MAINTAINERID)
            .auditStatus(DEFAULT_AUDIT_STATUS);
        return auditedbudget;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Auditedbudget createUpdatedEntity(EntityManager em) {
        Auditedbudget auditedbudget = new Auditedbudget()
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .year(UPDATED_YEAR)
            .budgit(UPDATED_BUDGIT)
            .dapartmentid(UPDATED_DAPARTMENTID)
            .draftapproval(UPDATED_DRAFTAPPROVAL)
            .totalbudgetid(UPDATED_TOTALBUDGETID)
            .unitbudgetid(UPDATED_UNITBUDGETID)
            .documentid(UPDATED_DOCUMENTID)
            .maintainerid(UPDATED_MAINTAINERID)
            .auditStatus(UPDATED_AUDIT_STATUS);
        return auditedbudget;
    }

    @BeforeEach
    public void initTest() {
        auditedbudget = createEntity(em);
    }

    @Test
    @Transactional
    void createAuditedbudget() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Auditedbudget
        var returnedAuditedbudget = om.readValue(
            restAuditedbudgetMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(auditedbudget)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Auditedbudget.class
        );

        // Validate the Auditedbudget in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAuditedbudgetUpdatableFieldsEquals(returnedAuditedbudget, getPersistedAuditedbudget(returnedAuditedbudget));
    }

    @Test
    @Transactional
    void createAuditedbudgetWithExistingId() throws Exception {
        // Create the Auditedbudget with an existing ID
        auditedbudget.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAuditedbudgetMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(auditedbudget)))
            .andExpect(status().isBadRequest());

        // Validate the Auditedbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAuditedbudgets() throws Exception {
        // Initialize the database
        auditedbudgetRepository.saveAndFlush(auditedbudget);

        // Get all the auditedbudgetList
        restAuditedbudgetMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(auditedbudget.getId())))
            .andExpect(jsonPath("$.[*].createtime").value(hasItem(DEFAULT_CREATETIME.toString())))
            .andExpect(jsonPath("$.[*].creatorname").value(hasItem(DEFAULT_CREATORNAME)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR.intValue())))
            .andExpect(jsonPath("$.[*].budgit").value(hasItem(sameNumber(DEFAULT_BUDGIT))))
            .andExpect(jsonPath("$.[*].dapartmentid").value(hasItem(DEFAULT_DAPARTMENTID)))
            .andExpect(jsonPath("$.[*].draftapproval").value(hasItem(DEFAULT_DRAFTAPPROVAL.intValue())))
            .andExpect(jsonPath("$.[*].totalbudgetid").value(hasItem(sameNumber(DEFAULT_TOTALBUDGETID))))
            .andExpect(jsonPath("$.[*].unitbudgetid").value(hasItem(sameNumber(DEFAULT_UNITBUDGETID))))
            .andExpect(jsonPath("$.[*].documentid").value(hasItem(DEFAULT_DOCUMENTID)))
            .andExpect(jsonPath("$.[*].maintainerid").value(hasItem(DEFAULT_MAINTAINERID)))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getAuditedbudget() throws Exception {
        // Initialize the database
        auditedbudgetRepository.saveAndFlush(auditedbudget);

        // Get the auditedbudget
        restAuditedbudgetMockMvc
            .perform(get(ENTITY_API_URL_ID, auditedbudget.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(auditedbudget.getId()))
            .andExpect(jsonPath("$.createtime").value(DEFAULT_CREATETIME.toString()))
            .andExpect(jsonPath("$.creatorname").value(DEFAULT_CREATORNAME))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR.intValue()))
            .andExpect(jsonPath("$.budgit").value(sameNumber(DEFAULT_BUDGIT)))
            .andExpect(jsonPath("$.dapartmentid").value(DEFAULT_DAPARTMENTID))
            .andExpect(jsonPath("$.draftapproval").value(DEFAULT_DRAFTAPPROVAL.intValue()))
            .andExpect(jsonPath("$.totalbudgetid").value(sameNumber(DEFAULT_TOTALBUDGETID)))
            .andExpect(jsonPath("$.unitbudgetid").value(sameNumber(DEFAULT_UNITBUDGETID)))
            .andExpect(jsonPath("$.documentid").value(DEFAULT_DOCUMENTID))
            .andExpect(jsonPath("$.maintainerid").value(DEFAULT_MAINTAINERID))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAuditedbudget() throws Exception {
        // Get the auditedbudget
        restAuditedbudgetMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAuditedbudget() throws Exception {
        // Initialize the database
        auditedbudgetRepository.saveAndFlush(auditedbudget);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the auditedbudget
        Auditedbudget updatedAuditedbudget = auditedbudgetRepository.findById(auditedbudget.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAuditedbudget are not directly saved in db
        em.detach(updatedAuditedbudget);
        updatedAuditedbudget
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .year(UPDATED_YEAR)
            .budgit(UPDATED_BUDGIT)
            .dapartmentid(UPDATED_DAPARTMENTID)
            .draftapproval(UPDATED_DRAFTAPPROVAL)
            .totalbudgetid(UPDATED_TOTALBUDGETID)
            .unitbudgetid(UPDATED_UNITBUDGETID)
            .documentid(UPDATED_DOCUMENTID)
            .maintainerid(UPDATED_MAINTAINERID)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restAuditedbudgetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAuditedbudget.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAuditedbudget))
            )
            .andExpect(status().isOk());

        // Validate the Auditedbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAuditedbudgetToMatchAllProperties(updatedAuditedbudget);
    }

    @Test
    @Transactional
    void putNonExistingAuditedbudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        auditedbudget.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAuditedbudgetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, auditedbudget.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(auditedbudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the Auditedbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAuditedbudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        auditedbudget.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAuditedbudgetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(auditedbudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the Auditedbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAuditedbudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        auditedbudget.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAuditedbudgetMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(auditedbudget)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Auditedbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAuditedbudgetWithPatch() throws Exception {
        // Initialize the database
        auditedbudgetRepository.saveAndFlush(auditedbudget);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the auditedbudget using partial update
        Auditedbudget partialUpdatedAuditedbudget = new Auditedbudget();
        partialUpdatedAuditedbudget.setId(auditedbudget.getId());

        partialUpdatedAuditedbudget
            .createtime(UPDATED_CREATETIME)
            .budgit(UPDATED_BUDGIT)
            .draftapproval(UPDATED_DRAFTAPPROVAL)
            .unitbudgetid(UPDATED_UNITBUDGETID)
            .maintainerid(UPDATED_MAINTAINERID)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restAuditedbudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAuditedbudget.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAuditedbudget))
            )
            .andExpect(status().isOk());

        // Validate the Auditedbudget in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAuditedbudgetUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAuditedbudget, auditedbudget),
            getPersistedAuditedbudget(auditedbudget)
        );
    }

    @Test
    @Transactional
    void fullUpdateAuditedbudgetWithPatch() throws Exception {
        // Initialize the database
        auditedbudgetRepository.saveAndFlush(auditedbudget);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the auditedbudget using partial update
        Auditedbudget partialUpdatedAuditedbudget = new Auditedbudget();
        partialUpdatedAuditedbudget.setId(auditedbudget.getId());

        partialUpdatedAuditedbudget
            .createtime(UPDATED_CREATETIME)
            .creatorname(UPDATED_CREATORNAME)
            .secretlevel(UPDATED_SECRETLEVEL)
            .year(UPDATED_YEAR)
            .budgit(UPDATED_BUDGIT)
            .dapartmentid(UPDATED_DAPARTMENTID)
            .draftapproval(UPDATED_DRAFTAPPROVAL)
            .totalbudgetid(UPDATED_TOTALBUDGETID)
            .unitbudgetid(UPDATED_UNITBUDGETID)
            .documentid(UPDATED_DOCUMENTID)
            .maintainerid(UPDATED_MAINTAINERID)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restAuditedbudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAuditedbudget.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAuditedbudget))
            )
            .andExpect(status().isOk());

        // Validate the Auditedbudget in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAuditedbudgetUpdatableFieldsEquals(partialUpdatedAuditedbudget, getPersistedAuditedbudget(partialUpdatedAuditedbudget));
    }

    @Test
    @Transactional
    void patchNonExistingAuditedbudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        auditedbudget.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAuditedbudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, auditedbudget.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(auditedbudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the Auditedbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAuditedbudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        auditedbudget.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAuditedbudgetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(auditedbudget))
            )
            .andExpect(status().isBadRequest());

        // Validate the Auditedbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAuditedbudget() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        auditedbudget.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAuditedbudgetMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(auditedbudget)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Auditedbudget in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAuditedbudget() throws Exception {
        // Initialize the database
        auditedbudgetRepository.saveAndFlush(auditedbudget);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the auditedbudget
        restAuditedbudgetMockMvc
            .perform(delete(ENTITY_API_URL_ID, auditedbudget.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return auditedbudgetRepository.count();
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

    protected Auditedbudget getPersistedAuditedbudget(Auditedbudget auditedbudget) {
        return auditedbudgetRepository.findById(auditedbudget.getId()).orElseThrow();
    }

    protected void assertPersistedAuditedbudgetToMatchAllProperties(Auditedbudget expectedAuditedbudget) {
        assertAuditedbudgetAllPropertiesEquals(expectedAuditedbudget, getPersistedAuditedbudget(expectedAuditedbudget));
    }

    protected void assertPersistedAuditedbudgetToMatchUpdatableProperties(Auditedbudget expectedAuditedbudget) {
        assertAuditedbudgetAllUpdatablePropertiesEquals(expectedAuditedbudget, getPersistedAuditedbudget(expectedAuditedbudget));
    }
}

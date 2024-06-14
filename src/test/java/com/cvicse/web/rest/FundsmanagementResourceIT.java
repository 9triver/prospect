package com.cvicse.web.rest;

import static com.cvicse.domain.FundsmanagementAsserts.*;
import static com.cvicse.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.IntegrationTest;
import com.cvicse.domain.Fundsmanagement;
import com.cvicse.domain.enumeration.AuditStatus;
import com.cvicse.domain.enumeration.Secretlevel;
import com.cvicse.repository.FundsmanagementRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
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
 * Integration tests for the {@link FundsmanagementResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FundsmanagementResourceIT {

    private static final Long DEFAULT_FUNDSID = 1L;
    private static final Long UPDATED_FUNDSID = 2L;

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

    private static final Long DEFAULT_DOCUMENTID = 1L;
    private static final Long UPDATED_DOCUMENTID = 2L;

    private static final Long DEFAULT_MAINTAINERID = 1L;
    private static final Long UPDATED_MAINTAINERID = 2L;

    private static final AuditStatus DEFAULT_AUDIT_STATUS = AuditStatus.Not_Audited;
    private static final AuditStatus UPDATED_AUDIT_STATUS = AuditStatus.In_Audit;

    private static final String ENTITY_API_URL = "/api/fundsmanagements";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FundsmanagementRepository fundsmanagementRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFundsmanagementMockMvc;

    private Fundsmanagement fundsmanagement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fundsmanagement createEntity(EntityManager em) {
        Fundsmanagement fundsmanagement = new Fundsmanagement()
            .fundsid(DEFAULT_FUNDSID)
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
        return fundsmanagement;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fundsmanagement createUpdatedEntity(EntityManager em) {
        Fundsmanagement fundsmanagement = new Fundsmanagement()
            .fundsid(UPDATED_FUNDSID)
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
        return fundsmanagement;
    }

    @BeforeEach
    public void initTest() {
        fundsmanagement = createEntity(em);
    }

    @Test
    @Transactional
    void createFundsmanagement() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Fundsmanagement
        var returnedFundsmanagement = om.readValue(
            restFundsmanagementMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fundsmanagement)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Fundsmanagement.class
        );

        // Validate the Fundsmanagement in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertFundsmanagementUpdatableFieldsEquals(returnedFundsmanagement, getPersistedFundsmanagement(returnedFundsmanagement));
    }

    @Test
    @Transactional
    void createFundsmanagementWithExistingId() throws Exception {
        // Create the Fundsmanagement with an existing ID
        fundsmanagement.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFundsmanagementMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fundsmanagement)))
            .andExpect(status().isBadRequest());

        // Validate the Fundsmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFundsmanagements() throws Exception {
        // Initialize the database
        fundsmanagementRepository.saveAndFlush(fundsmanagement);

        // Get all the fundsmanagementList
        restFundsmanagementMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fundsmanagement.getId().intValue())))
            .andExpect(jsonPath("$.[*].fundsid").value(hasItem(DEFAULT_FUNDSID.intValue())))
            .andExpect(jsonPath("$.[*].createtime").value(hasItem(DEFAULT_CREATETIME.toString())))
            .andExpect(jsonPath("$.[*].creatorname").value(hasItem(DEFAULT_CREATORNAME)))
            .andExpect(jsonPath("$.[*].secretlevel").value(hasItem(DEFAULT_SECRETLEVEL.toString())))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR.intValue())))
            .andExpect(jsonPath("$.[*].budgit").value(hasItem(sameNumber(DEFAULT_BUDGIT))))
            .andExpect(jsonPath("$.[*].dapartmentid").value(hasItem(DEFAULT_DAPARTMENTID)))
            .andExpect(jsonPath("$.[*].draftapproval").value(hasItem(DEFAULT_DRAFTAPPROVAL.intValue())))
            .andExpect(jsonPath("$.[*].totalbudgetid").value(hasItem(sameNumber(DEFAULT_TOTALBUDGETID))))
            .andExpect(jsonPath("$.[*].unitbudgetid").value(hasItem(sameNumber(DEFAULT_UNITBUDGETID))))
            .andExpect(jsonPath("$.[*].documentid").value(hasItem(DEFAULT_DOCUMENTID.intValue())))
            .andExpect(jsonPath("$.[*].maintainerid").value(hasItem(DEFAULT_MAINTAINERID.intValue())))
            .andExpect(jsonPath("$.[*].auditStatus").value(hasItem(DEFAULT_AUDIT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getFundsmanagement() throws Exception {
        // Initialize the database
        fundsmanagementRepository.saveAndFlush(fundsmanagement);

        // Get the fundsmanagement
        restFundsmanagementMockMvc
            .perform(get(ENTITY_API_URL_ID, fundsmanagement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fundsmanagement.getId().intValue()))
            .andExpect(jsonPath("$.fundsid").value(DEFAULT_FUNDSID.intValue()))
            .andExpect(jsonPath("$.createtime").value(DEFAULT_CREATETIME.toString()))
            .andExpect(jsonPath("$.creatorname").value(DEFAULT_CREATORNAME))
            .andExpect(jsonPath("$.secretlevel").value(DEFAULT_SECRETLEVEL.toString()))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR.intValue()))
            .andExpect(jsonPath("$.budgit").value(sameNumber(DEFAULT_BUDGIT)))
            .andExpect(jsonPath("$.dapartmentid").value(DEFAULT_DAPARTMENTID))
            .andExpect(jsonPath("$.draftapproval").value(DEFAULT_DRAFTAPPROVAL.intValue()))
            .andExpect(jsonPath("$.totalbudgetid").value(sameNumber(DEFAULT_TOTALBUDGETID)))
            .andExpect(jsonPath("$.unitbudgetid").value(sameNumber(DEFAULT_UNITBUDGETID)))
            .andExpect(jsonPath("$.documentid").value(DEFAULT_DOCUMENTID.intValue()))
            .andExpect(jsonPath("$.maintainerid").value(DEFAULT_MAINTAINERID.intValue()))
            .andExpect(jsonPath("$.auditStatus").value(DEFAULT_AUDIT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingFundsmanagement() throws Exception {
        // Get the fundsmanagement
        restFundsmanagementMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFundsmanagement() throws Exception {
        // Initialize the database
        fundsmanagementRepository.saveAndFlush(fundsmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fundsmanagement
        Fundsmanagement updatedFundsmanagement = fundsmanagementRepository.findById(fundsmanagement.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFundsmanagement are not directly saved in db
        em.detach(updatedFundsmanagement);
        updatedFundsmanagement
            .fundsid(UPDATED_FUNDSID)
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

        restFundsmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFundsmanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedFundsmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Fundsmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFundsmanagementToMatchAllProperties(updatedFundsmanagement);
    }

    @Test
    @Transactional
    void putNonExistingFundsmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsmanagement.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFundsmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, fundsmanagement.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fundsmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fundsmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFundsmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsmanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsmanagementMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fundsmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fundsmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFundsmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsmanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsmanagementMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fundsmanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Fundsmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFundsmanagementWithPatch() throws Exception {
        // Initialize the database
        fundsmanagementRepository.saveAndFlush(fundsmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fundsmanagement using partial update
        Fundsmanagement partialUpdatedFundsmanagement = new Fundsmanagement();
        partialUpdatedFundsmanagement.setId(fundsmanagement.getId());

        partialUpdatedFundsmanagement
            .fundsid(UPDATED_FUNDSID)
            .createtime(UPDATED_CREATETIME)
            .year(UPDATED_YEAR)
            .totalbudgetid(UPDATED_TOTALBUDGETID)
            .documentid(UPDATED_DOCUMENTID)
            .auditStatus(UPDATED_AUDIT_STATUS);

        restFundsmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFundsmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFundsmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Fundsmanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFundsmanagementUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedFundsmanagement, fundsmanagement),
            getPersistedFundsmanagement(fundsmanagement)
        );
    }

    @Test
    @Transactional
    void fullUpdateFundsmanagementWithPatch() throws Exception {
        // Initialize the database
        fundsmanagementRepository.saveAndFlush(fundsmanagement);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fundsmanagement using partial update
        Fundsmanagement partialUpdatedFundsmanagement = new Fundsmanagement();
        partialUpdatedFundsmanagement.setId(fundsmanagement.getId());

        partialUpdatedFundsmanagement
            .fundsid(UPDATED_FUNDSID)
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

        restFundsmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFundsmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFundsmanagement))
            )
            .andExpect(status().isOk());

        // Validate the Fundsmanagement in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFundsmanagementUpdatableFieldsEquals(
            partialUpdatedFundsmanagement,
            getPersistedFundsmanagement(partialUpdatedFundsmanagement)
        );
    }

    @Test
    @Transactional
    void patchNonExistingFundsmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsmanagement.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFundsmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fundsmanagement.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fundsmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fundsmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFundsmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsmanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsmanagementMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fundsmanagement))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fundsmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFundsmanagement() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fundsmanagement.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFundsmanagementMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(fundsmanagement)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Fundsmanagement in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFundsmanagement() throws Exception {
        // Initialize the database
        fundsmanagementRepository.saveAndFlush(fundsmanagement);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the fundsmanagement
        restFundsmanagementMockMvc
            .perform(delete(ENTITY_API_URL_ID, fundsmanagement.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return fundsmanagementRepository.count();
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

    protected Fundsmanagement getPersistedFundsmanagement(Fundsmanagement fundsmanagement) {
        return fundsmanagementRepository.findById(fundsmanagement.getId()).orElseThrow();
    }

    protected void assertPersistedFundsmanagementToMatchAllProperties(Fundsmanagement expectedFundsmanagement) {
        assertFundsmanagementAllPropertiesEquals(expectedFundsmanagement, getPersistedFundsmanagement(expectedFundsmanagement));
    }

    protected void assertPersistedFundsmanagementToMatchUpdatableProperties(Fundsmanagement expectedFundsmanagement) {
        assertFundsmanagementAllUpdatablePropertiesEquals(expectedFundsmanagement, getPersistedFundsmanagement(expectedFundsmanagement));
    }
}

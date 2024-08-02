package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.CostControlSystemAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.CostControlSystem;
import com.cvicse.jy1.domain.enumeration.ContractSubject;
import com.cvicse.jy1.repository.CostControlSystemRepository;
import com.cvicse.jy1.service.CostControlSystemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link CostControlSystemResource} REST controller.
 */
@IntegrationTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
class CostControlSystemResourceIT {

    private static final Integer DEFAULT_TYPE = 1;
    private static final Integer UPDATED_TYPE = 2;

    private static final ContractSubject DEFAULT_SUBJECT = ContractSubject.Materialfee;
    private static final ContractSubject UPDATED_SUBJECT = ContractSubject.Specialfee;

    private static final BigDecimal DEFAULT_IMPLEMENTEDAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_IMPLEMENTEDAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_APPROVEDAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_APPROVEDAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PENDINGIMPLEMENTATIONAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PENDINGIMPLEMENTATIONAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CONTRACTPAYMENTAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONTRACTPAYMENTAMOUNT = new BigDecimal(2);

    private static final Integer DEFAULT_MANAGEMENTREGISTRATIONNUMBER = 1;
    private static final Integer UPDATED_MANAGEMENTREGISTRATIONNUMBER = 2;

    private static final Integer DEFAULT_FINANCIALREGISTRATIONNUMBER = 1;
    private static final Integer UPDATED_FINANCIALREGISTRATIONNUMBER = 2;

    private static final BigDecimal DEFAULT_CONTRACTBUDGETAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONTRACTBUDGETAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CONTRACTSIGNINGAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONTRACTSIGNINGAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CONTRACTSETTLEMENTAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CONTRACTSETTLEMENTAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_UNFORESEEABLEAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_UNFORESEEABLEAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_INVOICEPAYMENTAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_INVOICEPAYMENTAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_LOANPAYMENTAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_LOANPAYMENTAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ACCOUNTOUTSTANDINGAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ACCOUNTOUTSTANDINGAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PENDINGPAYMENTAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PENDINGPAYMENTAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_PENDINGINVOICEAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PENDINGINVOICEAMOUNT = new BigDecimal(2);

    private static final String ENTITY_API_URL = "/api/cost-control-systems";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CostControlSystemRepository costControlSystemRepository;

    @Mock
    private CostControlSystemRepository costControlSystemRepositoryMock;

    @Mock
    private CostControlSystemService costControlSystemServiceMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCostControlSystemMockMvc;

    private CostControlSystem costControlSystem;

    private CostControlSystem insertedCostControlSystem;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CostControlSystem createEntity(EntityManager em) {
        CostControlSystem costControlSystem = new CostControlSystem()
            .type(DEFAULT_TYPE)
            .subject(DEFAULT_SUBJECT)
            .implementedamount(DEFAULT_IMPLEMENTEDAMOUNT)
            .approvedamount(DEFAULT_APPROVEDAMOUNT)
            .pendingimplementationamount(DEFAULT_PENDINGIMPLEMENTATIONAMOUNT)
            .contractpaymentamount(DEFAULT_CONTRACTPAYMENTAMOUNT)
            .managementregistrationnumber(DEFAULT_MANAGEMENTREGISTRATIONNUMBER)
            .financialregistrationnumber(DEFAULT_FINANCIALREGISTRATIONNUMBER)
            .contractbudgetamount(DEFAULT_CONTRACTBUDGETAMOUNT)
            .contractsigningamount(DEFAULT_CONTRACTSIGNINGAMOUNT)
            .contractsettlementamount(DEFAULT_CONTRACTSETTLEMENTAMOUNT)
            .unforeseeableamount(DEFAULT_UNFORESEEABLEAMOUNT)
            .invoicepaymentamount(DEFAULT_INVOICEPAYMENTAMOUNT)
            .loanpaymentamount(DEFAULT_LOANPAYMENTAMOUNT)
            .accountoutstandingamount(DEFAULT_ACCOUNTOUTSTANDINGAMOUNT)
            .pendingpaymentamount(DEFAULT_PENDINGPAYMENTAMOUNT)
            .pendinginvoiceamount(DEFAULT_PENDINGINVOICEAMOUNT);
        return costControlSystem;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CostControlSystem createUpdatedEntity(EntityManager em) {
        CostControlSystem costControlSystem = new CostControlSystem()
            .type(UPDATED_TYPE)
            .subject(UPDATED_SUBJECT)
            .implementedamount(UPDATED_IMPLEMENTEDAMOUNT)
            .approvedamount(UPDATED_APPROVEDAMOUNT)
            .pendingimplementationamount(UPDATED_PENDINGIMPLEMENTATIONAMOUNT)
            .contractpaymentamount(UPDATED_CONTRACTPAYMENTAMOUNT)
            .managementregistrationnumber(UPDATED_MANAGEMENTREGISTRATIONNUMBER)
            .financialregistrationnumber(UPDATED_FINANCIALREGISTRATIONNUMBER)
            .contractbudgetamount(UPDATED_CONTRACTBUDGETAMOUNT)
            .contractsigningamount(UPDATED_CONTRACTSIGNINGAMOUNT)
            .contractsettlementamount(UPDATED_CONTRACTSETTLEMENTAMOUNT)
            .unforeseeableamount(UPDATED_UNFORESEEABLEAMOUNT)
            .invoicepaymentamount(UPDATED_INVOICEPAYMENTAMOUNT)
            .loanpaymentamount(UPDATED_LOANPAYMENTAMOUNT)
            .accountoutstandingamount(UPDATED_ACCOUNTOUTSTANDINGAMOUNT)
            .pendingpaymentamount(UPDATED_PENDINGPAYMENTAMOUNT)
            .pendinginvoiceamount(UPDATED_PENDINGINVOICEAMOUNT);
        return costControlSystem;
    }

    @BeforeEach
    public void initTest() {
        costControlSystem = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedCostControlSystem != null) {
            costControlSystemRepository.delete(insertedCostControlSystem);
            insertedCostControlSystem = null;
        }
    }

    @Test
    @Transactional
    void createCostControlSystem() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the CostControlSystem
        var returnedCostControlSystem = om.readValue(
            restCostControlSystemMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(costControlSystem)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            CostControlSystem.class
        );

        // Validate the CostControlSystem in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCostControlSystemUpdatableFieldsEquals(returnedCostControlSystem, getPersistedCostControlSystem(returnedCostControlSystem));

        insertedCostControlSystem = returnedCostControlSystem;
    }

    @Test
    @Transactional
    void createCostControlSystemWithExistingId() throws Exception {
        // Create the CostControlSystem with an existing ID
        costControlSystem.setId("existing_id");

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCostControlSystemMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(costControlSystem)))
            .andExpect(status().isBadRequest());

        // Validate the CostControlSystem in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCostControlSystems() throws Exception {
        // Initialize the database
        insertedCostControlSystem = costControlSystemRepository.saveAndFlush(costControlSystem);

        // Get all the costControlSystemList
        restCostControlSystemMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(costControlSystem.getId())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].subject").value(hasItem(DEFAULT_SUBJECT.toString())))
            .andExpect(jsonPath("$.[*].implementedamount").value(hasItem(sameNumber(DEFAULT_IMPLEMENTEDAMOUNT))))
            .andExpect(jsonPath("$.[*].approvedamount").value(hasItem(sameNumber(DEFAULT_APPROVEDAMOUNT))))
            .andExpect(jsonPath("$.[*].pendingimplementationamount").value(hasItem(sameNumber(DEFAULT_PENDINGIMPLEMENTATIONAMOUNT))))
            .andExpect(jsonPath("$.[*].contractpaymentamount").value(hasItem(sameNumber(DEFAULT_CONTRACTPAYMENTAMOUNT))))
            .andExpect(jsonPath("$.[*].managementregistrationnumber").value(hasItem(DEFAULT_MANAGEMENTREGISTRATIONNUMBER)))
            .andExpect(jsonPath("$.[*].financialregistrationnumber").value(hasItem(DEFAULT_FINANCIALREGISTRATIONNUMBER)))
            .andExpect(jsonPath("$.[*].contractbudgetamount").value(hasItem(sameNumber(DEFAULT_CONTRACTBUDGETAMOUNT))))
            .andExpect(jsonPath("$.[*].contractsigningamount").value(hasItem(sameNumber(DEFAULT_CONTRACTSIGNINGAMOUNT))))
            .andExpect(jsonPath("$.[*].contractsettlementamount").value(hasItem(sameNumber(DEFAULT_CONTRACTSETTLEMENTAMOUNT))))
            .andExpect(jsonPath("$.[*].unforeseeableamount").value(hasItem(sameNumber(DEFAULT_UNFORESEEABLEAMOUNT))))
            .andExpect(jsonPath("$.[*].invoicepaymentamount").value(hasItem(sameNumber(DEFAULT_INVOICEPAYMENTAMOUNT))))
            .andExpect(jsonPath("$.[*].loanpaymentamount").value(hasItem(sameNumber(DEFAULT_LOANPAYMENTAMOUNT))))
            .andExpect(jsonPath("$.[*].accountoutstandingamount").value(hasItem(sameNumber(DEFAULT_ACCOUNTOUTSTANDINGAMOUNT))))
            .andExpect(jsonPath("$.[*].pendingpaymentamount").value(hasItem(sameNumber(DEFAULT_PENDINGPAYMENTAMOUNT))))
            .andExpect(jsonPath("$.[*].pendinginvoiceamount").value(hasItem(sameNumber(DEFAULT_PENDINGINVOICEAMOUNT))));
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCostControlSystemsWithEagerRelationshipsIsEnabled() throws Exception {
        when(costControlSystemServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCostControlSystemMockMvc.perform(get(ENTITY_API_URL + "?eagerload=true")).andExpect(status().isOk());

        verify(costControlSystemServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({ "unchecked" })
    void getAllCostControlSystemsWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(costControlSystemServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restCostControlSystemMockMvc.perform(get(ENTITY_API_URL + "?eagerload=false")).andExpect(status().isOk());
        verify(costControlSystemRepositoryMock, times(1)).findAll(any(Pageable.class));
    }

    @Test
    @Transactional
    void getCostControlSystem() throws Exception {
        // Initialize the database
        insertedCostControlSystem = costControlSystemRepository.saveAndFlush(costControlSystem);

        // Get the costControlSystem
        restCostControlSystemMockMvc
            .perform(get(ENTITY_API_URL_ID, costControlSystem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(costControlSystem.getId()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.subject").value(DEFAULT_SUBJECT.toString()))
            .andExpect(jsonPath("$.implementedamount").value(sameNumber(DEFAULT_IMPLEMENTEDAMOUNT)))
            .andExpect(jsonPath("$.approvedamount").value(sameNumber(DEFAULT_APPROVEDAMOUNT)))
            .andExpect(jsonPath("$.pendingimplementationamount").value(sameNumber(DEFAULT_PENDINGIMPLEMENTATIONAMOUNT)))
            .andExpect(jsonPath("$.contractpaymentamount").value(sameNumber(DEFAULT_CONTRACTPAYMENTAMOUNT)))
            .andExpect(jsonPath("$.managementregistrationnumber").value(DEFAULT_MANAGEMENTREGISTRATIONNUMBER))
            .andExpect(jsonPath("$.financialregistrationnumber").value(DEFAULT_FINANCIALREGISTRATIONNUMBER))
            .andExpect(jsonPath("$.contractbudgetamount").value(sameNumber(DEFAULT_CONTRACTBUDGETAMOUNT)))
            .andExpect(jsonPath("$.contractsigningamount").value(sameNumber(DEFAULT_CONTRACTSIGNINGAMOUNT)))
            .andExpect(jsonPath("$.contractsettlementamount").value(sameNumber(DEFAULT_CONTRACTSETTLEMENTAMOUNT)))
            .andExpect(jsonPath("$.unforeseeableamount").value(sameNumber(DEFAULT_UNFORESEEABLEAMOUNT)))
            .andExpect(jsonPath("$.invoicepaymentamount").value(sameNumber(DEFAULT_INVOICEPAYMENTAMOUNT)))
            .andExpect(jsonPath("$.loanpaymentamount").value(sameNumber(DEFAULT_LOANPAYMENTAMOUNT)))
            .andExpect(jsonPath("$.accountoutstandingamount").value(sameNumber(DEFAULT_ACCOUNTOUTSTANDINGAMOUNT)))
            .andExpect(jsonPath("$.pendingpaymentamount").value(sameNumber(DEFAULT_PENDINGPAYMENTAMOUNT)))
            .andExpect(jsonPath("$.pendinginvoiceamount").value(sameNumber(DEFAULT_PENDINGINVOICEAMOUNT)));
    }

    @Test
    @Transactional
    void getNonExistingCostControlSystem() throws Exception {
        // Get the costControlSystem
        restCostControlSystemMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCostControlSystem() throws Exception {
        // Initialize the database
        insertedCostControlSystem = costControlSystemRepository.saveAndFlush(costControlSystem);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the costControlSystem
        CostControlSystem updatedCostControlSystem = costControlSystemRepository.findById(costControlSystem.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCostControlSystem are not directly saved in db
        em.detach(updatedCostControlSystem);
        updatedCostControlSystem
            .type(UPDATED_TYPE)
            .subject(UPDATED_SUBJECT)
            .implementedamount(UPDATED_IMPLEMENTEDAMOUNT)
            .approvedamount(UPDATED_APPROVEDAMOUNT)
            .pendingimplementationamount(UPDATED_PENDINGIMPLEMENTATIONAMOUNT)
            .contractpaymentamount(UPDATED_CONTRACTPAYMENTAMOUNT)
            .managementregistrationnumber(UPDATED_MANAGEMENTREGISTRATIONNUMBER)
            .financialregistrationnumber(UPDATED_FINANCIALREGISTRATIONNUMBER)
            .contractbudgetamount(UPDATED_CONTRACTBUDGETAMOUNT)
            .contractsigningamount(UPDATED_CONTRACTSIGNINGAMOUNT)
            .contractsettlementamount(UPDATED_CONTRACTSETTLEMENTAMOUNT)
            .unforeseeableamount(UPDATED_UNFORESEEABLEAMOUNT)
            .invoicepaymentamount(UPDATED_INVOICEPAYMENTAMOUNT)
            .loanpaymentamount(UPDATED_LOANPAYMENTAMOUNT)
            .accountoutstandingamount(UPDATED_ACCOUNTOUTSTANDINGAMOUNT)
            .pendingpaymentamount(UPDATED_PENDINGPAYMENTAMOUNT)
            .pendinginvoiceamount(UPDATED_PENDINGINVOICEAMOUNT);

        restCostControlSystemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCostControlSystem.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCostControlSystem))
            )
            .andExpect(status().isOk());

        // Validate the CostControlSystem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCostControlSystemToMatchAllProperties(updatedCostControlSystem);
    }

    @Test
    @Transactional
    void putNonExistingCostControlSystem() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        costControlSystem.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCostControlSystemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, costControlSystem.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(costControlSystem))
            )
            .andExpect(status().isBadRequest());

        // Validate the CostControlSystem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCostControlSystem() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        costControlSystem.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCostControlSystemMockMvc
            .perform(
                put(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(costControlSystem))
            )
            .andExpect(status().isBadRequest());

        // Validate the CostControlSystem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCostControlSystem() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        costControlSystem.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCostControlSystemMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(costControlSystem)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CostControlSystem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCostControlSystemWithPatch() throws Exception {
        // Initialize the database
        insertedCostControlSystem = costControlSystemRepository.saveAndFlush(costControlSystem);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the costControlSystem using partial update
        CostControlSystem partialUpdatedCostControlSystem = new CostControlSystem();
        partialUpdatedCostControlSystem.setId(costControlSystem.getId());

        partialUpdatedCostControlSystem
            .subject(UPDATED_SUBJECT)
            .implementedamount(UPDATED_IMPLEMENTEDAMOUNT)
            .pendingimplementationamount(UPDATED_PENDINGIMPLEMENTATIONAMOUNT)
            .contractbudgetamount(UPDATED_CONTRACTBUDGETAMOUNT)
            .unforeseeableamount(UPDATED_UNFORESEEABLEAMOUNT)
            .accountoutstandingamount(UPDATED_ACCOUNTOUTSTANDINGAMOUNT);

        restCostControlSystemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCostControlSystem.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCostControlSystem))
            )
            .andExpect(status().isOk());

        // Validate the CostControlSystem in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCostControlSystemUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCostControlSystem, costControlSystem),
            getPersistedCostControlSystem(costControlSystem)
        );
    }

    @Test
    @Transactional
    void fullUpdateCostControlSystemWithPatch() throws Exception {
        // Initialize the database
        insertedCostControlSystem = costControlSystemRepository.saveAndFlush(costControlSystem);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the costControlSystem using partial update
        CostControlSystem partialUpdatedCostControlSystem = new CostControlSystem();
        partialUpdatedCostControlSystem.setId(costControlSystem.getId());

        partialUpdatedCostControlSystem
            .type(UPDATED_TYPE)
            .subject(UPDATED_SUBJECT)
            .implementedamount(UPDATED_IMPLEMENTEDAMOUNT)
            .approvedamount(UPDATED_APPROVEDAMOUNT)
            .pendingimplementationamount(UPDATED_PENDINGIMPLEMENTATIONAMOUNT)
            .contractpaymentamount(UPDATED_CONTRACTPAYMENTAMOUNT)
            .managementregistrationnumber(UPDATED_MANAGEMENTREGISTRATIONNUMBER)
            .financialregistrationnumber(UPDATED_FINANCIALREGISTRATIONNUMBER)
            .contractbudgetamount(UPDATED_CONTRACTBUDGETAMOUNT)
            .contractsigningamount(UPDATED_CONTRACTSIGNINGAMOUNT)
            .contractsettlementamount(UPDATED_CONTRACTSETTLEMENTAMOUNT)
            .unforeseeableamount(UPDATED_UNFORESEEABLEAMOUNT)
            .invoicepaymentamount(UPDATED_INVOICEPAYMENTAMOUNT)
            .loanpaymentamount(UPDATED_LOANPAYMENTAMOUNT)
            .accountoutstandingamount(UPDATED_ACCOUNTOUTSTANDINGAMOUNT)
            .pendingpaymentamount(UPDATED_PENDINGPAYMENTAMOUNT)
            .pendinginvoiceamount(UPDATED_PENDINGINVOICEAMOUNT);

        restCostControlSystemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCostControlSystem.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCostControlSystem))
            )
            .andExpect(status().isOk());

        // Validate the CostControlSystem in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCostControlSystemUpdatableFieldsEquals(
            partialUpdatedCostControlSystem,
            getPersistedCostControlSystem(partialUpdatedCostControlSystem)
        );
    }

    @Test
    @Transactional
    void patchNonExistingCostControlSystem() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        costControlSystem.setId(UUID.randomUUID().toString());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCostControlSystemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, costControlSystem.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(costControlSystem))
            )
            .andExpect(status().isBadRequest());

        // Validate the CostControlSystem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCostControlSystem() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        costControlSystem.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCostControlSystemMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, UUID.randomUUID().toString())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(costControlSystem))
            )
            .andExpect(status().isBadRequest());

        // Validate the CostControlSystem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCostControlSystem() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        costControlSystem.setId(UUID.randomUUID().toString());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCostControlSystemMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(costControlSystem)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the CostControlSystem in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCostControlSystem() throws Exception {
        // Initialize the database
        insertedCostControlSystem = costControlSystemRepository.saveAndFlush(costControlSystem);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the costControlSystem
        restCostControlSystemMockMvc
            .perform(delete(ENTITY_API_URL_ID, costControlSystem.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return costControlSystemRepository.count();
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

    protected CostControlSystem getPersistedCostControlSystem(CostControlSystem costControlSystem) {
        return costControlSystemRepository.findById(costControlSystem.getId()).orElseThrow();
    }

    protected void assertPersistedCostControlSystemToMatchAllProperties(CostControlSystem expectedCostControlSystem) {
        assertCostControlSystemAllPropertiesEquals(expectedCostControlSystem, getPersistedCostControlSystem(expectedCostControlSystem));
    }

    protected void assertPersistedCostControlSystemToMatchUpdatableProperties(CostControlSystem expectedCostControlSystem) {
        assertCostControlSystemAllUpdatablePropertiesEquals(
            expectedCostControlSystem,
            getPersistedCostControlSystem(expectedCostControlSystem)
        );
    }
}

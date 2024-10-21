package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.ContractPaymentAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.ContractPayment;
import com.cvicse.jy1.domain.enumeration.PaymentType;
import com.cvicse.jy1.repository.ContractPaymentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
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
 * Integration tests for the {@link ContractPaymentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ContractPaymentResourceIT {

    private static final String DEFAULT_WORKBAGID = "AAAAAAAAAA";
    private static final String UPDATED_WORKBAGID = "BBBBBBBBBB";

    private static final String DEFAULT_WORKBAGNAME = "AAAAAAAAAA";
    private static final String UPDATED_WORKBAGNAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTCODE = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTCODE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTRACTNAME = "AAAAAAAAAA";
    private static final String UPDATED_CONTRACTNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PLANPAYMENTNODE = "AAAAAAAAAA";
    private static final String UPDATED_PLANPAYMENTNODE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_PLANPAYMENTAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_PLANPAYMENTAMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ACTUALPAYMENTAMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_ACTUALPAYMENTAMOUNT = new BigDecimal(2);

    private static final PaymentType DEFAULT_PAYMENTTYPE = PaymentType.BORROWING;
    private static final PaymentType UPDATED_PAYMENTTYPE = PaymentType.ACCOUNTING;

    private static final String DEFAULT_FINANCIALVOUCHERID = "AAAAAAAAAA";
    private static final String UPDATED_FINANCIALVOUCHERID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/contract-payments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ContractPaymentRepository contractPaymentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restContractPaymentMockMvc;

    private ContractPayment contractPayment;

    private ContractPayment insertedContractPayment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContractPayment createEntity(EntityManager em) {
        ContractPayment contractPayment = new ContractPayment()
            .workbagid(DEFAULT_WORKBAGID)
            .workbagname(DEFAULT_WORKBAGNAME)
            .contractcode(DEFAULT_CONTRACTCODE)
            .contractname(DEFAULT_CONTRACTNAME)
            .planpaymentnode(DEFAULT_PLANPAYMENTNODE)
            .planpaymentamount(DEFAULT_PLANPAYMENTAMOUNT)
            .actualpaymentamount(DEFAULT_ACTUALPAYMENTAMOUNT)
            .paymenttype(DEFAULT_PAYMENTTYPE)
            .financialvoucherid(DEFAULT_FINANCIALVOUCHERID);
        return contractPayment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContractPayment createUpdatedEntity(EntityManager em) {
        ContractPayment contractPayment = new ContractPayment()
            .workbagid(UPDATED_WORKBAGID)
            .workbagname(UPDATED_WORKBAGNAME)
            .contractcode(UPDATED_CONTRACTCODE)
            .contractname(UPDATED_CONTRACTNAME)
            .planpaymentnode(UPDATED_PLANPAYMENTNODE)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT)
            .actualpaymentamount(UPDATED_ACTUALPAYMENTAMOUNT)
            .paymenttype(UPDATED_PAYMENTTYPE)
            .financialvoucherid(UPDATED_FINANCIALVOUCHERID);
        return contractPayment;
    }

    @BeforeEach
    public void initTest() {
        contractPayment = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedContractPayment != null) {
            contractPaymentRepository.delete(insertedContractPayment);
            insertedContractPayment = null;
        }
    }

    @Test
    @Transactional
    void createContractPayment() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the ContractPayment
        var returnedContractPayment = om.readValue(
            restContractPaymentMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contractPayment)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            ContractPayment.class
        );

        // Validate the ContractPayment in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertContractPaymentUpdatableFieldsEquals(returnedContractPayment, getPersistedContractPayment(returnedContractPayment));

        insertedContractPayment = returnedContractPayment;
    }

    @Test
    @Transactional
    void createContractPaymentWithExistingId() throws Exception {
        // Create the ContractPayment with an existing ID
        contractPayment.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restContractPaymentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contractPayment)))
            .andExpect(status().isBadRequest());

        // Validate the ContractPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllContractPayments() throws Exception {
        // Initialize the database
        insertedContractPayment = contractPaymentRepository.saveAndFlush(contractPayment);

        // Get all the contractPaymentList
        restContractPaymentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contractPayment.getId().intValue())))
            .andExpect(jsonPath("$.[*].workbagid").value(hasItem(DEFAULT_WORKBAGID)))
            .andExpect(jsonPath("$.[*].workbagname").value(hasItem(DEFAULT_WORKBAGNAME)))
            .andExpect(jsonPath("$.[*].contractcode").value(hasItem(DEFAULT_CONTRACTCODE)))
            .andExpect(jsonPath("$.[*].contractname").value(hasItem(DEFAULT_CONTRACTNAME)))
            .andExpect(jsonPath("$.[*].planpaymentnode").value(hasItem(DEFAULT_PLANPAYMENTNODE)))
            .andExpect(jsonPath("$.[*].planpaymentamount").value(hasItem(sameNumber(DEFAULT_PLANPAYMENTAMOUNT))))
            .andExpect(jsonPath("$.[*].actualpaymentamount").value(hasItem(sameNumber(DEFAULT_ACTUALPAYMENTAMOUNT))))
            .andExpect(jsonPath("$.[*].paymenttype").value(hasItem(DEFAULT_PAYMENTTYPE.toString())))
            .andExpect(jsonPath("$.[*].financialvoucherid").value(hasItem(DEFAULT_FINANCIALVOUCHERID)));
    }

    @Test
    @Transactional
    void getContractPayment() throws Exception {
        // Initialize the database
        insertedContractPayment = contractPaymentRepository.saveAndFlush(contractPayment);

        // Get the contractPayment
        restContractPaymentMockMvc
            .perform(get(ENTITY_API_URL_ID, contractPayment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(contractPayment.getId().intValue()))
            .andExpect(jsonPath("$.workbagid").value(DEFAULT_WORKBAGID))
            .andExpect(jsonPath("$.workbagname").value(DEFAULT_WORKBAGNAME))
            .andExpect(jsonPath("$.contractcode").value(DEFAULT_CONTRACTCODE))
            .andExpect(jsonPath("$.contractname").value(DEFAULT_CONTRACTNAME))
            .andExpect(jsonPath("$.planpaymentnode").value(DEFAULT_PLANPAYMENTNODE))
            .andExpect(jsonPath("$.planpaymentamount").value(sameNumber(DEFAULT_PLANPAYMENTAMOUNT)))
            .andExpect(jsonPath("$.actualpaymentamount").value(sameNumber(DEFAULT_ACTUALPAYMENTAMOUNT)))
            .andExpect(jsonPath("$.paymenttype").value(DEFAULT_PAYMENTTYPE.toString()))
            .andExpect(jsonPath("$.financialvoucherid").value(DEFAULT_FINANCIALVOUCHERID));
    }

    @Test
    @Transactional
    void getNonExistingContractPayment() throws Exception {
        // Get the contractPayment
        restContractPaymentMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingContractPayment() throws Exception {
        // Initialize the database
        insertedContractPayment = contractPaymentRepository.saveAndFlush(contractPayment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the contractPayment
        ContractPayment updatedContractPayment = contractPaymentRepository.findById(contractPayment.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedContractPayment are not directly saved in db
        em.detach(updatedContractPayment);
        updatedContractPayment
            .workbagid(UPDATED_WORKBAGID)
            .workbagname(UPDATED_WORKBAGNAME)
            .contractcode(UPDATED_CONTRACTCODE)
            .contractname(UPDATED_CONTRACTNAME)
            .planpaymentnode(UPDATED_PLANPAYMENTNODE)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT)
            .actualpaymentamount(UPDATED_ACTUALPAYMENTAMOUNT)
            .paymenttype(UPDATED_PAYMENTTYPE)
            .financialvoucherid(UPDATED_FINANCIALVOUCHERID);

        restContractPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedContractPayment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedContractPayment))
            )
            .andExpect(status().isOk());

        // Validate the ContractPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedContractPaymentToMatchAllProperties(updatedContractPayment);
    }

    @Test
    @Transactional
    void putNonExistingContractPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contractPayment.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, contractPayment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(contractPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchContractPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contractPayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(contractPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamContractPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contractPayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractPaymentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(contractPayment)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContractPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateContractPaymentWithPatch() throws Exception {
        // Initialize the database
        insertedContractPayment = contractPaymentRepository.saveAndFlush(contractPayment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the contractPayment using partial update
        ContractPayment partialUpdatedContractPayment = new ContractPayment();
        partialUpdatedContractPayment.setId(contractPayment.getId());

        partialUpdatedContractPayment
            .workbagname(UPDATED_WORKBAGNAME)
            .contractcode(UPDATED_CONTRACTCODE)
            .contractname(UPDATED_CONTRACTNAME)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT)
            .financialvoucherid(UPDATED_FINANCIALVOUCHERID);

        restContractPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedContractPayment))
            )
            .andExpect(status().isOk());

        // Validate the ContractPayment in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertContractPaymentUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedContractPayment, contractPayment),
            getPersistedContractPayment(contractPayment)
        );
    }

    @Test
    @Transactional
    void fullUpdateContractPaymentWithPatch() throws Exception {
        // Initialize the database
        insertedContractPayment = contractPaymentRepository.saveAndFlush(contractPayment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the contractPayment using partial update
        ContractPayment partialUpdatedContractPayment = new ContractPayment();
        partialUpdatedContractPayment.setId(contractPayment.getId());

        partialUpdatedContractPayment
            .workbagid(UPDATED_WORKBAGID)
            .workbagname(UPDATED_WORKBAGNAME)
            .contractcode(UPDATED_CONTRACTCODE)
            .contractname(UPDATED_CONTRACTNAME)
            .planpaymentnode(UPDATED_PLANPAYMENTNODE)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT)
            .actualpaymentamount(UPDATED_ACTUALPAYMENTAMOUNT)
            .paymenttype(UPDATED_PAYMENTTYPE)
            .financialvoucherid(UPDATED_FINANCIALVOUCHERID);

        restContractPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedContractPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedContractPayment))
            )
            .andExpect(status().isOk());

        // Validate the ContractPayment in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertContractPaymentUpdatableFieldsEquals(
            partialUpdatedContractPayment,
            getPersistedContractPayment(partialUpdatedContractPayment)
        );
    }

    @Test
    @Transactional
    void patchNonExistingContractPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contractPayment.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContractPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, contractPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(contractPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchContractPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contractPayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(contractPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the ContractPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamContractPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        contractPayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restContractPaymentMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(contractPayment)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the ContractPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteContractPayment() throws Exception {
        // Initialize the database
        insertedContractPayment = contractPaymentRepository.saveAndFlush(contractPayment);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the contractPayment
        restContractPaymentMockMvc
            .perform(delete(ENTITY_API_URL_ID, contractPayment.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return contractPaymentRepository.count();
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

    protected ContractPayment getPersistedContractPayment(ContractPayment contractPayment) {
        return contractPaymentRepository.findById(contractPayment.getId()).orElseThrow();
    }

    protected void assertPersistedContractPaymentToMatchAllProperties(ContractPayment expectedContractPayment) {
        assertContractPaymentAllPropertiesEquals(expectedContractPayment, getPersistedContractPayment(expectedContractPayment));
    }

    protected void assertPersistedContractPaymentToMatchUpdatableProperties(ContractPayment expectedContractPayment) {
        assertContractPaymentAllUpdatablePropertiesEquals(expectedContractPayment, getPersistedContractPayment(expectedContractPayment));
    }
}

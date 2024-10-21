package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.TransactionPaymentAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.TransactionPayment;
import com.cvicse.jy1.domain.enumeration.PaymentType;
import com.cvicse.jy1.repository.TransactionPaymentRepository;
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
 * Integration tests for the {@link TransactionPaymentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TransactionPaymentResourceIT {

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

    private static final String ENTITY_API_URL = "/api/transaction-payments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TransactionPaymentRepository transactionPaymentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTransactionPaymentMockMvc;

    private TransactionPayment transactionPayment;

    private TransactionPayment insertedTransactionPayment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransactionPayment createEntity(EntityManager em) {
        TransactionPayment transactionPayment = new TransactionPayment()
            .planpaymentnode(DEFAULT_PLANPAYMENTNODE)
            .planpaymentamount(DEFAULT_PLANPAYMENTAMOUNT)
            .actualpaymentamount(DEFAULT_ACTUALPAYMENTAMOUNT)
            .paymenttype(DEFAULT_PAYMENTTYPE)
            .financialvoucherid(DEFAULT_FINANCIALVOUCHERID);
        return transactionPayment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TransactionPayment createUpdatedEntity(EntityManager em) {
        TransactionPayment transactionPayment = new TransactionPayment()
            .planpaymentnode(UPDATED_PLANPAYMENTNODE)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT)
            .actualpaymentamount(UPDATED_ACTUALPAYMENTAMOUNT)
            .paymenttype(UPDATED_PAYMENTTYPE)
            .financialvoucherid(UPDATED_FINANCIALVOUCHERID);
        return transactionPayment;
    }

    @BeforeEach
    public void initTest() {
        transactionPayment = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedTransactionPayment != null) {
            transactionPaymentRepository.delete(insertedTransactionPayment);
            insertedTransactionPayment = null;
        }
    }

    @Test
    @Transactional
    void createTransactionPayment() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the TransactionPayment
        var returnedTransactionPayment = om.readValue(
            restTransactionPaymentMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(transactionPayment)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            TransactionPayment.class
        );

        // Validate the TransactionPayment in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertTransactionPaymentUpdatableFieldsEquals(
            returnedTransactionPayment,
            getPersistedTransactionPayment(returnedTransactionPayment)
        );

        insertedTransactionPayment = returnedTransactionPayment;
    }

    @Test
    @Transactional
    void createTransactionPaymentWithExistingId() throws Exception {
        // Create the TransactionPayment with an existing ID
        transactionPayment.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTransactionPaymentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(transactionPayment)))
            .andExpect(status().isBadRequest());

        // Validate the TransactionPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTransactionPayments() throws Exception {
        // Initialize the database
        insertedTransactionPayment = transactionPaymentRepository.saveAndFlush(transactionPayment);

        // Get all the transactionPaymentList
        restTransactionPaymentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(transactionPayment.getId().intValue())))
            .andExpect(jsonPath("$.[*].planpaymentnode").value(hasItem(DEFAULT_PLANPAYMENTNODE)))
            .andExpect(jsonPath("$.[*].planpaymentamount").value(hasItem(sameNumber(DEFAULT_PLANPAYMENTAMOUNT))))
            .andExpect(jsonPath("$.[*].actualpaymentamount").value(hasItem(sameNumber(DEFAULT_ACTUALPAYMENTAMOUNT))))
            .andExpect(jsonPath("$.[*].paymenttype").value(hasItem(DEFAULT_PAYMENTTYPE.toString())))
            .andExpect(jsonPath("$.[*].financialvoucherid").value(hasItem(DEFAULT_FINANCIALVOUCHERID)));
    }

    @Test
    @Transactional
    void getTransactionPayment() throws Exception {
        // Initialize the database
        insertedTransactionPayment = transactionPaymentRepository.saveAndFlush(transactionPayment);

        // Get the transactionPayment
        restTransactionPaymentMockMvc
            .perform(get(ENTITY_API_URL_ID, transactionPayment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(transactionPayment.getId().intValue()))
            .andExpect(jsonPath("$.planpaymentnode").value(DEFAULT_PLANPAYMENTNODE))
            .andExpect(jsonPath("$.planpaymentamount").value(sameNumber(DEFAULT_PLANPAYMENTAMOUNT)))
            .andExpect(jsonPath("$.actualpaymentamount").value(sameNumber(DEFAULT_ACTUALPAYMENTAMOUNT)))
            .andExpect(jsonPath("$.paymenttype").value(DEFAULT_PAYMENTTYPE.toString()))
            .andExpect(jsonPath("$.financialvoucherid").value(DEFAULT_FINANCIALVOUCHERID));
    }

    @Test
    @Transactional
    void getNonExistingTransactionPayment() throws Exception {
        // Get the transactionPayment
        restTransactionPaymentMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingTransactionPayment() throws Exception {
        // Initialize the database
        insertedTransactionPayment = transactionPaymentRepository.saveAndFlush(transactionPayment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the transactionPayment
        TransactionPayment updatedTransactionPayment = transactionPaymentRepository.findById(transactionPayment.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedTransactionPayment are not directly saved in db
        em.detach(updatedTransactionPayment);
        updatedTransactionPayment
            .planpaymentnode(UPDATED_PLANPAYMENTNODE)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT)
            .actualpaymentamount(UPDATED_ACTUALPAYMENTAMOUNT)
            .paymenttype(UPDATED_PAYMENTTYPE)
            .financialvoucherid(UPDATED_FINANCIALVOUCHERID);

        restTransactionPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedTransactionPayment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedTransactionPayment))
            )
            .andExpect(status().isOk());

        // Validate the TransactionPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedTransactionPaymentToMatchAllProperties(updatedTransactionPayment);
    }

    @Test
    @Transactional
    void putNonExistingTransactionPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transactionPayment.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransactionPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, transactionPayment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(transactionPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the TransactionPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTransactionPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transactionPayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransactionPaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(transactionPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the TransactionPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTransactionPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transactionPayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransactionPaymentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(transactionPayment)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TransactionPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTransactionPaymentWithPatch() throws Exception {
        // Initialize the database
        insertedTransactionPayment = transactionPaymentRepository.saveAndFlush(transactionPayment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the transactionPayment using partial update
        TransactionPayment partialUpdatedTransactionPayment = new TransactionPayment();
        partialUpdatedTransactionPayment.setId(transactionPayment.getId());

        partialUpdatedTransactionPayment.actualpaymentamount(UPDATED_ACTUALPAYMENTAMOUNT).paymenttype(UPDATED_PAYMENTTYPE);

        restTransactionPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTransactionPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTransactionPayment))
            )
            .andExpect(status().isOk());

        // Validate the TransactionPayment in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTransactionPaymentUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedTransactionPayment, transactionPayment),
            getPersistedTransactionPayment(transactionPayment)
        );
    }

    @Test
    @Transactional
    void fullUpdateTransactionPaymentWithPatch() throws Exception {
        // Initialize the database
        insertedTransactionPayment = transactionPaymentRepository.saveAndFlush(transactionPayment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the transactionPayment using partial update
        TransactionPayment partialUpdatedTransactionPayment = new TransactionPayment();
        partialUpdatedTransactionPayment.setId(transactionPayment.getId());

        partialUpdatedTransactionPayment
            .planpaymentnode(UPDATED_PLANPAYMENTNODE)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT)
            .actualpaymentamount(UPDATED_ACTUALPAYMENTAMOUNT)
            .paymenttype(UPDATED_PAYMENTTYPE)
            .financialvoucherid(UPDATED_FINANCIALVOUCHERID);

        restTransactionPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTransactionPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedTransactionPayment))
            )
            .andExpect(status().isOk());

        // Validate the TransactionPayment in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertTransactionPaymentUpdatableFieldsEquals(
            partialUpdatedTransactionPayment,
            getPersistedTransactionPayment(partialUpdatedTransactionPayment)
        );
    }

    @Test
    @Transactional
    void patchNonExistingTransactionPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transactionPayment.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTransactionPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, transactionPayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(transactionPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the TransactionPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTransactionPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transactionPayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransactionPaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(transactionPayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the TransactionPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTransactionPayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        transactionPayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTransactionPaymentMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(transactionPayment)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the TransactionPayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTransactionPayment() throws Exception {
        // Initialize the database
        insertedTransactionPayment = transactionPaymentRepository.saveAndFlush(transactionPayment);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the transactionPayment
        restTransactionPaymentMockMvc
            .perform(delete(ENTITY_API_URL_ID, transactionPayment.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return transactionPaymentRepository.count();
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

    protected TransactionPayment getPersistedTransactionPayment(TransactionPayment transactionPayment) {
        return transactionPaymentRepository.findById(transactionPayment.getId()).orElseThrow();
    }

    protected void assertPersistedTransactionPaymentToMatchAllProperties(TransactionPayment expectedTransactionPayment) {
        assertTransactionPaymentAllPropertiesEquals(expectedTransactionPayment, getPersistedTransactionPayment(expectedTransactionPayment));
    }

    protected void assertPersistedTransactionPaymentToMatchUpdatableProperties(TransactionPayment expectedTransactionPayment) {
        assertTransactionPaymentAllUpdatablePropertiesEquals(
            expectedTransactionPayment,
            getPersistedTransactionPayment(expectedTransactionPayment)
        );
    }
}

package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.SharePaymentAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.SharePayment;
import com.cvicse.jy1.domain.enumeration.PaymentType;
import com.cvicse.jy1.repository.SharePaymentRepository;
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
 * Integration tests for the {@link SharePaymentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SharePaymentResourceIT {

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

    private static final String ENTITY_API_URL = "/api/share-payments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SharePaymentRepository sharePaymentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSharePaymentMockMvc;

    private SharePayment sharePayment;

    private SharePayment insertedSharePayment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SharePayment createEntity(EntityManager em) {
        SharePayment sharePayment = new SharePayment()
            .planpaymentnode(DEFAULT_PLANPAYMENTNODE)
            .planpaymentamount(DEFAULT_PLANPAYMENTAMOUNT)
            .actualpaymentamount(DEFAULT_ACTUALPAYMENTAMOUNT)
            .paymenttype(DEFAULT_PAYMENTTYPE)
            .financialvoucherid(DEFAULT_FINANCIALVOUCHERID);
        return sharePayment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SharePayment createUpdatedEntity(EntityManager em) {
        SharePayment sharePayment = new SharePayment()
            .planpaymentnode(UPDATED_PLANPAYMENTNODE)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT)
            .actualpaymentamount(UPDATED_ACTUALPAYMENTAMOUNT)
            .paymenttype(UPDATED_PAYMENTTYPE)
            .financialvoucherid(UPDATED_FINANCIALVOUCHERID);
        return sharePayment;
    }

    @BeforeEach
    public void initTest() {
        sharePayment = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedSharePayment != null) {
            sharePaymentRepository.delete(insertedSharePayment);
            insertedSharePayment = null;
        }
    }

    @Test
    @Transactional
    void createSharePayment() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the SharePayment
        var returnedSharePayment = om.readValue(
            restSharePaymentMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(sharePayment)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            SharePayment.class
        );

        // Validate the SharePayment in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertSharePaymentUpdatableFieldsEquals(returnedSharePayment, getPersistedSharePayment(returnedSharePayment));

        insertedSharePayment = returnedSharePayment;
    }

    @Test
    @Transactional
    void createSharePaymentWithExistingId() throws Exception {
        // Create the SharePayment with an existing ID
        sharePayment.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSharePaymentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(sharePayment)))
            .andExpect(status().isBadRequest());

        // Validate the SharePayment in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSharePayments() throws Exception {
        // Initialize the database
        insertedSharePayment = sharePaymentRepository.saveAndFlush(sharePayment);

        // Get all the sharePaymentList
        restSharePaymentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sharePayment.getId().intValue())))
            .andExpect(jsonPath("$.[*].planpaymentnode").value(hasItem(DEFAULT_PLANPAYMENTNODE)))
            .andExpect(jsonPath("$.[*].planpaymentamount").value(hasItem(sameNumber(DEFAULT_PLANPAYMENTAMOUNT))))
            .andExpect(jsonPath("$.[*].actualpaymentamount").value(hasItem(sameNumber(DEFAULT_ACTUALPAYMENTAMOUNT))))
            .andExpect(jsonPath("$.[*].paymenttype").value(hasItem(DEFAULT_PAYMENTTYPE.toString())))
            .andExpect(jsonPath("$.[*].financialvoucherid").value(hasItem(DEFAULT_FINANCIALVOUCHERID)));
    }

    @Test
    @Transactional
    void getSharePayment() throws Exception {
        // Initialize the database
        insertedSharePayment = sharePaymentRepository.saveAndFlush(sharePayment);

        // Get the sharePayment
        restSharePaymentMockMvc
            .perform(get(ENTITY_API_URL_ID, sharePayment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sharePayment.getId().intValue()))
            .andExpect(jsonPath("$.planpaymentnode").value(DEFAULT_PLANPAYMENTNODE))
            .andExpect(jsonPath("$.planpaymentamount").value(sameNumber(DEFAULT_PLANPAYMENTAMOUNT)))
            .andExpect(jsonPath("$.actualpaymentamount").value(sameNumber(DEFAULT_ACTUALPAYMENTAMOUNT)))
            .andExpect(jsonPath("$.paymenttype").value(DEFAULT_PAYMENTTYPE.toString()))
            .andExpect(jsonPath("$.financialvoucherid").value(DEFAULT_FINANCIALVOUCHERID));
    }

    @Test
    @Transactional
    void getNonExistingSharePayment() throws Exception {
        // Get the sharePayment
        restSharePaymentMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSharePayment() throws Exception {
        // Initialize the database
        insertedSharePayment = sharePaymentRepository.saveAndFlush(sharePayment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the sharePayment
        SharePayment updatedSharePayment = sharePaymentRepository.findById(sharePayment.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedSharePayment are not directly saved in db
        em.detach(updatedSharePayment);
        updatedSharePayment
            .planpaymentnode(UPDATED_PLANPAYMENTNODE)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT)
            .actualpaymentamount(UPDATED_ACTUALPAYMENTAMOUNT)
            .paymenttype(UPDATED_PAYMENTTYPE)
            .financialvoucherid(UPDATED_FINANCIALVOUCHERID);

        restSharePaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSharePayment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedSharePayment))
            )
            .andExpect(status().isOk());

        // Validate the SharePayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSharePaymentToMatchAllProperties(updatedSharePayment);
    }

    @Test
    @Transactional
    void putNonExistingSharePayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sharePayment.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSharePaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sharePayment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(sharePayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the SharePayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSharePayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sharePayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSharePaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(sharePayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the SharePayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSharePayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sharePayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSharePaymentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(sharePayment)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SharePayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSharePaymentWithPatch() throws Exception {
        // Initialize the database
        insertedSharePayment = sharePaymentRepository.saveAndFlush(sharePayment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the sharePayment using partial update
        SharePayment partialUpdatedSharePayment = new SharePayment();
        partialUpdatedSharePayment.setId(sharePayment.getId());

        partialUpdatedSharePayment.planpaymentnode(UPDATED_PLANPAYMENTNODE);

        restSharePaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSharePayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSharePayment))
            )
            .andExpect(status().isOk());

        // Validate the SharePayment in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSharePaymentUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSharePayment, sharePayment),
            getPersistedSharePayment(sharePayment)
        );
    }

    @Test
    @Transactional
    void fullUpdateSharePaymentWithPatch() throws Exception {
        // Initialize the database
        insertedSharePayment = sharePaymentRepository.saveAndFlush(sharePayment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the sharePayment using partial update
        SharePayment partialUpdatedSharePayment = new SharePayment();
        partialUpdatedSharePayment.setId(sharePayment.getId());

        partialUpdatedSharePayment
            .planpaymentnode(UPDATED_PLANPAYMENTNODE)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT)
            .actualpaymentamount(UPDATED_ACTUALPAYMENTAMOUNT)
            .paymenttype(UPDATED_PAYMENTTYPE)
            .financialvoucherid(UPDATED_FINANCIALVOUCHERID);

        restSharePaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSharePayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSharePayment))
            )
            .andExpect(status().isOk());

        // Validate the SharePayment in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSharePaymentUpdatableFieldsEquals(partialUpdatedSharePayment, getPersistedSharePayment(partialUpdatedSharePayment));
    }

    @Test
    @Transactional
    void patchNonExistingSharePayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sharePayment.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSharePaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sharePayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(sharePayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the SharePayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSharePayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sharePayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSharePaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(sharePayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the SharePayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSharePayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sharePayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSharePaymentMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(sharePayment)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SharePayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSharePayment() throws Exception {
        // Initialize the database
        insertedSharePayment = sharePaymentRepository.saveAndFlush(sharePayment);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the sharePayment
        restSharePaymentMockMvc
            .perform(delete(ENTITY_API_URL_ID, sharePayment.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return sharePaymentRepository.count();
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

    protected SharePayment getPersistedSharePayment(SharePayment sharePayment) {
        return sharePaymentRepository.findById(sharePayment.getId()).orElseThrow();
    }

    protected void assertPersistedSharePaymentToMatchAllProperties(SharePayment expectedSharePayment) {
        assertSharePaymentAllPropertiesEquals(expectedSharePayment, getPersistedSharePayment(expectedSharePayment));
    }

    protected void assertPersistedSharePaymentToMatchUpdatableProperties(SharePayment expectedSharePayment) {
        assertSharePaymentAllUpdatablePropertiesEquals(expectedSharePayment, getPersistedSharePayment(expectedSharePayment));
    }
}

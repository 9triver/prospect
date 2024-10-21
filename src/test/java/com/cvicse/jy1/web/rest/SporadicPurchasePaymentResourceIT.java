package com.cvicse.jy1.web.rest;

import static com.cvicse.jy1.domain.SporadicPurchasePaymentAsserts.*;
import static com.cvicse.jy1.web.rest.TestUtil.createUpdateProxyForBean;
import static com.cvicse.jy1.web.rest.TestUtil.sameNumber;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.cvicse.jy1.IntegrationTest;
import com.cvicse.jy1.domain.SporadicPurchasePayment;
import com.cvicse.jy1.domain.enumeration.PaymentType;
import com.cvicse.jy1.repository.SporadicPurchasePaymentRepository;
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
 * Integration tests for the {@link SporadicPurchasePaymentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SporadicPurchasePaymentResourceIT {

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

    private static final String ENTITY_API_URL = "/api/sporadic-purchase-payments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private SporadicPurchasePaymentRepository sporadicPurchasePaymentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSporadicPurchasePaymentMockMvc;

    private SporadicPurchasePayment sporadicPurchasePayment;

    private SporadicPurchasePayment insertedSporadicPurchasePayment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SporadicPurchasePayment createEntity(EntityManager em) {
        SporadicPurchasePayment sporadicPurchasePayment = new SporadicPurchasePayment()
            .planpaymentnode(DEFAULT_PLANPAYMENTNODE)
            .planpaymentamount(DEFAULT_PLANPAYMENTAMOUNT)
            .actualpaymentamount(DEFAULT_ACTUALPAYMENTAMOUNT)
            .paymenttype(DEFAULT_PAYMENTTYPE)
            .financialvoucherid(DEFAULT_FINANCIALVOUCHERID);
        return sporadicPurchasePayment;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SporadicPurchasePayment createUpdatedEntity(EntityManager em) {
        SporadicPurchasePayment sporadicPurchasePayment = new SporadicPurchasePayment()
            .planpaymentnode(UPDATED_PLANPAYMENTNODE)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT)
            .actualpaymentamount(UPDATED_ACTUALPAYMENTAMOUNT)
            .paymenttype(UPDATED_PAYMENTTYPE)
            .financialvoucherid(UPDATED_FINANCIALVOUCHERID);
        return sporadicPurchasePayment;
    }

    @BeforeEach
    public void initTest() {
        sporadicPurchasePayment = createEntity(em);
    }

    @AfterEach
    public void cleanup() {
        if (insertedSporadicPurchasePayment != null) {
            sporadicPurchasePaymentRepository.delete(insertedSporadicPurchasePayment);
            insertedSporadicPurchasePayment = null;
        }
    }

    @Test
    @Transactional
    void createSporadicPurchasePayment() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the SporadicPurchasePayment
        var returnedSporadicPurchasePayment = om.readValue(
            restSporadicPurchasePaymentMockMvc
                .perform(
                    post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(sporadicPurchasePayment))
                )
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            SporadicPurchasePayment.class
        );

        // Validate the SporadicPurchasePayment in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertSporadicPurchasePaymentUpdatableFieldsEquals(
            returnedSporadicPurchasePayment,
            getPersistedSporadicPurchasePayment(returnedSporadicPurchasePayment)
        );

        insertedSporadicPurchasePayment = returnedSporadicPurchasePayment;
    }

    @Test
    @Transactional
    void createSporadicPurchasePaymentWithExistingId() throws Exception {
        // Create the SporadicPurchasePayment with an existing ID
        sporadicPurchasePayment.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSporadicPurchasePaymentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(sporadicPurchasePayment)))
            .andExpect(status().isBadRequest());

        // Validate the SporadicPurchasePayment in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSporadicPurchasePayments() throws Exception {
        // Initialize the database
        insertedSporadicPurchasePayment = sporadicPurchasePaymentRepository.saveAndFlush(sporadicPurchasePayment);

        // Get all the sporadicPurchasePaymentList
        restSporadicPurchasePaymentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sporadicPurchasePayment.getId().intValue())))
            .andExpect(jsonPath("$.[*].planpaymentnode").value(hasItem(DEFAULT_PLANPAYMENTNODE)))
            .andExpect(jsonPath("$.[*].planpaymentamount").value(hasItem(sameNumber(DEFAULT_PLANPAYMENTAMOUNT))))
            .andExpect(jsonPath("$.[*].actualpaymentamount").value(hasItem(sameNumber(DEFAULT_ACTUALPAYMENTAMOUNT))))
            .andExpect(jsonPath("$.[*].paymenttype").value(hasItem(DEFAULT_PAYMENTTYPE.toString())))
            .andExpect(jsonPath("$.[*].financialvoucherid").value(hasItem(DEFAULT_FINANCIALVOUCHERID)));
    }

    @Test
    @Transactional
    void getSporadicPurchasePayment() throws Exception {
        // Initialize the database
        insertedSporadicPurchasePayment = sporadicPurchasePaymentRepository.saveAndFlush(sporadicPurchasePayment);

        // Get the sporadicPurchasePayment
        restSporadicPurchasePaymentMockMvc
            .perform(get(ENTITY_API_URL_ID, sporadicPurchasePayment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(sporadicPurchasePayment.getId().intValue()))
            .andExpect(jsonPath("$.planpaymentnode").value(DEFAULT_PLANPAYMENTNODE))
            .andExpect(jsonPath("$.planpaymentamount").value(sameNumber(DEFAULT_PLANPAYMENTAMOUNT)))
            .andExpect(jsonPath("$.actualpaymentamount").value(sameNumber(DEFAULT_ACTUALPAYMENTAMOUNT)))
            .andExpect(jsonPath("$.paymenttype").value(DEFAULT_PAYMENTTYPE.toString()))
            .andExpect(jsonPath("$.financialvoucherid").value(DEFAULT_FINANCIALVOUCHERID));
    }

    @Test
    @Transactional
    void getNonExistingSporadicPurchasePayment() throws Exception {
        // Get the sporadicPurchasePayment
        restSporadicPurchasePaymentMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSporadicPurchasePayment() throws Exception {
        // Initialize the database
        insertedSporadicPurchasePayment = sporadicPurchasePaymentRepository.saveAndFlush(sporadicPurchasePayment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the sporadicPurchasePayment
        SporadicPurchasePayment updatedSporadicPurchasePayment = sporadicPurchasePaymentRepository
            .findById(sporadicPurchasePayment.getId())
            .orElseThrow();
        // Disconnect from session so that the updates on updatedSporadicPurchasePayment are not directly saved in db
        em.detach(updatedSporadicPurchasePayment);
        updatedSporadicPurchasePayment
            .planpaymentnode(UPDATED_PLANPAYMENTNODE)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT)
            .actualpaymentamount(UPDATED_ACTUALPAYMENTAMOUNT)
            .paymenttype(UPDATED_PAYMENTTYPE)
            .financialvoucherid(UPDATED_FINANCIALVOUCHERID);

        restSporadicPurchasePaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedSporadicPurchasePayment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedSporadicPurchasePayment))
            )
            .andExpect(status().isOk());

        // Validate the SporadicPurchasePayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedSporadicPurchasePaymentToMatchAllProperties(updatedSporadicPurchasePayment);
    }

    @Test
    @Transactional
    void putNonExistingSporadicPurchasePayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sporadicPurchasePayment.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSporadicPurchasePaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, sporadicPurchasePayment.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(sporadicPurchasePayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the SporadicPurchasePayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSporadicPurchasePayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sporadicPurchasePayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSporadicPurchasePaymentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(sporadicPurchasePayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the SporadicPurchasePayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSporadicPurchasePayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sporadicPurchasePayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSporadicPurchasePaymentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(sporadicPurchasePayment)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SporadicPurchasePayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSporadicPurchasePaymentWithPatch() throws Exception {
        // Initialize the database
        insertedSporadicPurchasePayment = sporadicPurchasePaymentRepository.saveAndFlush(sporadicPurchasePayment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the sporadicPurchasePayment using partial update
        SporadicPurchasePayment partialUpdatedSporadicPurchasePayment = new SporadicPurchasePayment();
        partialUpdatedSporadicPurchasePayment.setId(sporadicPurchasePayment.getId());

        partialUpdatedSporadicPurchasePayment.planpaymentnode(UPDATED_PLANPAYMENTNODE);

        restSporadicPurchasePaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSporadicPurchasePayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSporadicPurchasePayment))
            )
            .andExpect(status().isOk());

        // Validate the SporadicPurchasePayment in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSporadicPurchasePaymentUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedSporadicPurchasePayment, sporadicPurchasePayment),
            getPersistedSporadicPurchasePayment(sporadicPurchasePayment)
        );
    }

    @Test
    @Transactional
    void fullUpdateSporadicPurchasePaymentWithPatch() throws Exception {
        // Initialize the database
        insertedSporadicPurchasePayment = sporadicPurchasePaymentRepository.saveAndFlush(sporadicPurchasePayment);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the sporadicPurchasePayment using partial update
        SporadicPurchasePayment partialUpdatedSporadicPurchasePayment = new SporadicPurchasePayment();
        partialUpdatedSporadicPurchasePayment.setId(sporadicPurchasePayment.getId());

        partialUpdatedSporadicPurchasePayment
            .planpaymentnode(UPDATED_PLANPAYMENTNODE)
            .planpaymentamount(UPDATED_PLANPAYMENTAMOUNT)
            .actualpaymentamount(UPDATED_ACTUALPAYMENTAMOUNT)
            .paymenttype(UPDATED_PAYMENTTYPE)
            .financialvoucherid(UPDATED_FINANCIALVOUCHERID);

        restSporadicPurchasePaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSporadicPurchasePayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedSporadicPurchasePayment))
            )
            .andExpect(status().isOk());

        // Validate the SporadicPurchasePayment in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertSporadicPurchasePaymentUpdatableFieldsEquals(
            partialUpdatedSporadicPurchasePayment,
            getPersistedSporadicPurchasePayment(partialUpdatedSporadicPurchasePayment)
        );
    }

    @Test
    @Transactional
    void patchNonExistingSporadicPurchasePayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sporadicPurchasePayment.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSporadicPurchasePaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, sporadicPurchasePayment.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(sporadicPurchasePayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the SporadicPurchasePayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSporadicPurchasePayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sporadicPurchasePayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSporadicPurchasePaymentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(sporadicPurchasePayment))
            )
            .andExpect(status().isBadRequest());

        // Validate the SporadicPurchasePayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSporadicPurchasePayment() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        sporadicPurchasePayment.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSporadicPurchasePaymentMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(sporadicPurchasePayment))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SporadicPurchasePayment in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSporadicPurchasePayment() throws Exception {
        // Initialize the database
        insertedSporadicPurchasePayment = sporadicPurchasePaymentRepository.saveAndFlush(sporadicPurchasePayment);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the sporadicPurchasePayment
        restSporadicPurchasePaymentMockMvc
            .perform(delete(ENTITY_API_URL_ID, sporadicPurchasePayment.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return sporadicPurchasePaymentRepository.count();
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

    protected SporadicPurchasePayment getPersistedSporadicPurchasePayment(SporadicPurchasePayment sporadicPurchasePayment) {
        return sporadicPurchasePaymentRepository.findById(sporadicPurchasePayment.getId()).orElseThrow();
    }

    protected void assertPersistedSporadicPurchasePaymentToMatchAllProperties(SporadicPurchasePayment expectedSporadicPurchasePayment) {
        assertSporadicPurchasePaymentAllPropertiesEquals(
            expectedSporadicPurchasePayment,
            getPersistedSporadicPurchasePayment(expectedSporadicPurchasePayment)
        );
    }

    protected void assertPersistedSporadicPurchasePaymentToMatchUpdatableProperties(
        SporadicPurchasePayment expectedSporadicPurchasePayment
    ) {
        assertSporadicPurchasePaymentAllUpdatablePropertiesEquals(
            expectedSporadicPurchasePayment,
            getPersistedSporadicPurchasePayment(expectedSporadicPurchasePayment)
        );
    }
}
